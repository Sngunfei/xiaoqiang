package com.syf.service;

import com.syf.Const.Config;
import com.syf.Const.Status;
import com.syf.bean.Car;
import com.syf.bean.Address;
import com.syf.biz.logger;
import com.syf.dao.TaskDao;
import com.syf.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Tuple;
import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Scope("singleton")
public class TaskService {

    private TaskDao taskDao;
    private CarService carService;
    private AddressService addressService;
    private UserService userService;

    private SimpleDateFormat dateFormat;

    @Autowired
    public TaskService(TaskDao dao, CarService service1, AddressService service2, UserService service3){
        this.taskDao = dao;
        this.carService = service1;
        this.addressService = service2;
        this.userService = service3;
    }

    public String selectRandomCP(){
        Random random = new Random(System.currentTimeMillis());
        int feed = random.nextInt(6537);
        switch (feed % 6){
            case 0: return "顺丰";
            case 1: return "中通";
            case 2: return "圆通";
            case 3: return "EMS";
            case 4: return "申通";
            case 5: return "韵达";
        }
        return "EMS";
    }

    /**
     * @return task 统计数据
     */
    public int[] getTaskStatistics(){
        int[] info = new int[5];
        List<Task> tasks = taskDao.getAllTask();
        info[0] = tasks.size();
        for(Task task: tasks){
            if(task.isReady())
                info[0]++;
            else if(task.isRunning())
                info[1]++;
            else if(task.isDone())
                info[2]++;
            else if(task.isFail())
                info[3]++;
        }
        return info;
    }

    public List<Task> getTaskByAccount(String account){
        return taskDao.getTaskByAccount(account);
    }

    public List<Task> getTaskByStatus(Status status){
        if(Utils.checkStatus(status))
            return taskDao.getTaskByStatus(status.content);
        return null;
    }

    public List<Task> getAllTask(){
        return taskDao.getAllTask();
    }

    public Task getTask(int taskID){
        return taskDao.get(taskID);
    }

    private boolean checkTask(Task task){
        if(task == null)
            return false;
        if(task.getID() < 0)
            return false;
        if(!Utils.checkStatus(task.getStatus()))
            return false;
        return true;
    }

    public void addTask(Task task){
        taskDao.save(task);
    }

    public void updateTask(Task task){
        taskDao.update(task);
    }

    public void deleteTask(int taskID){
        taskDao.delete(taskID);
    }

    public void deleteTask(Task task){
        taskDao.delete(task);
    }

    /**
     * @param task 将要启动的任务
     * @return 任务启动前的状态，用来提示启动情况
     */
    public String startTask(Task task){
        if(task == null)
            return "订单为空";
        Status status = task.getStatus();
        if(!Utils.checkStatus(status))
            return "订单状态非法！";
        if(task.isRunning())
            return "该订单已经开始配送了，请耐心等待。";
        if(task.isDone())
            return "该订单已经完成配送了，请及时取件";
        if(task.isFail())
            return "该订单已经失效，详细情况请咨询工作人员";

        Car car;
        if(task.isAssign()) {
            car = carService.getCar(task.getCarID());
            if(car == null || !car.isReady()){
                return "您为该订单指定的快递车不在空闲状态，请稍后重试";
            }
        } else {
            car = carService.getAvailableCar();
            if(car == null || !car.isReady()){
                return "当前没有空闲快递车了，已经添加到任务队列中";
            }
        }
        new Thread(() -> start(task, car)).start();
        return "启动成功！该订单马上开始配送，请耐心等待";
    }

    public String startTask(int id){
        Task task = getTask(id);
        return startTask(task);
    }

    public void start(Task task, Car car){
        task.setCarID(car.getID());
        task.setStatus(Status.Running);
        task.setDeliverTime(new Timestamp(System.currentTimeMillis()));
        car.setStatus(Status.Running);
        car.setCurTask(task.getID());
        new Thread(() -> sendCmd(task, car, false)).start();
        new Thread(() -> listen(task, car)).start();
    }

    private void sendCmd(Task task, Car availCar, boolean isReturn){
        try {
            String[] inetAddress = availCar.getIp().split(":");
            Socket socket = new Socket(inetAddress[0], Integer.valueOf(inetAddress[1]));
            logger.debug(String.format("Connect to car(id: %d, ip: %s)", availCar.getID(), availCar.getIp()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String destination;
            if(!isReturn) {
                Address address = addressService.getAddress(task.getAddressID());
                destination = address.getLocationInfo();
                logger.info("Destination location: " + destination);
            }else{
                destination = Config.Origin_Location;
            }
            pw.println(destination);
            pw.flush();
            logger.info("Send command successfully");
            pw.close();
            socket.close();
        } catch (IOException e) {
            logger.error("Send command failed.");
            e.printStackTrace();
        }
    }

    public void deliverProcess(int taskID, String record){
        if(taskID <= 0) return;
        taskDao.addTaskProcess(taskID, record);
    }

    public List<List<String>> getTaskProcess(int taskID){
        List<List<String>> infos = new ArrayList<>();
        if(taskID <= 0)
            return infos;
        Set<Tuple> processes = taskDao.getTaskProcess(taskID);
        List<String> record;
        for(Tuple tuple: processes){
            record = formatRecord(tuple.getElement());
            record.add(formatTime((long) tuple.getScore()));
            infos.add(record);
        }
        return infos;
    }

    private SimpleDateFormat getDateFormat(){
        if(this.dateFormat == null)
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return this.dateFormat;
    }

    private String formatTime(long time){
        SimpleDateFormat format = getDateFormat();
        return format.format(time);
    }

    private List<String> formatRecord(String record){
        String[] msgs = record.split(";");
        ArrayList<String> records = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        double num;
        for(String msg: msgs){
            for(String str : msg.split(",")){
                num = Double.valueOf(str);
                buffer.append(num).append(",");
            }
            buffer.trimToSize();
            buffer.insert(buffer.length() - 1, ")");
            buffer.insert(0, "(");
            records.add(buffer.toString());
            buffer.setLength(0);
        }
        return records;
    }

    // 小车回传
    private void listen(Task task, Car car){
        int taskID = task.getID();
        int carID = car.getID();
        try{
            ServerSocket serverSocket = new ServerSocket(Config.Listen_Port);
            Socket socket = serverSocket.accept();
            logger.info(String.format("任务%d,车辆%d开始回传信息, ip地址：%s", taskID, carID socket.getInetAddress()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            char[] buffer = new char[1024];
            String msg;
            while(br.read(buffer) != 0){
                msg = String.valueOf(buffer);
                logger.info(msg);
                taskDao.addTaskProcess(taskID, msg);
                if("done".equals(msg)) {
                    logger.info("任务配送完成!");
                    break;
                }
            }
            br.close();
            socket.close();
            serverSocket.close();
            sendCmd(task, car, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshTask(Task task){
        if(task.isReady())
            return;
        task.setStatus(Status.Ready);
        task.setDeliverTime(null);
        int carID = task.getCarID();
        if(task.isAssign())
            task.setCarID(-1);
        taskDao.clearTaskProcess(task.getID());
        taskDao.update(task);

        carService.refreshCar(carID);
    }
}
