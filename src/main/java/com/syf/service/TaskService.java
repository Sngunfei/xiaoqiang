package com.syf.service;

import com.syf.XQExceptions.CarException;
import com.syf.XQExceptions.TaskException;
import com.syf.bean.Car;
import com.syf.bean.Place;
import com.syf.control.LoginController;
import com.syf.dao.TaskDao;
import com.syf.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketAddress;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskService {

    private TaskDao dao;
    private CarService carService;
    private PlaceService placeService;
    private UserService userService;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginController.class);

    @Autowired
    public TaskService(TaskDao dao, CarService service1, PlaceService service2, UserService service3){
        this.dao = dao;
        this.carService = service1;
        this.placeService = service2;
        this.userService = service3;
    }

    // 获取任务数量信息，主页展示
    public int[] getTaskNumInfo(){
        int[] info = new int[5];
        List<Task> allTasks = dao.getAllTask();
        info[0] = allTasks.size();
        for(Task task: allTasks){
            int tmp = task.getStatus();
            switch (tmp){
                case 0: info[1]++; break;  // ready
                case 1: info[2]++; break;  // doing
                case 2: info[3]++; break;  // done
                case 3: info[4]++; break;  // err
                default: // ..
            }
        }
        return info;
    }

    // 因为task里存的是用户+小车+地址的id，所以在页面显示的时候需要再转回来
    public String[] getTaskInfo(int taskId){
        String[] taskInfo = new String[3];
        Task task = this.getTask(taskId);
        taskInfo[0] = userService.getAccountById(task.getUserID());
        taskInfo[1] = placeService.getDescripById(task.getDestination());
        taskInfo[2] = "顺丰公司";
        return taskInfo;
    }

    public List<String[]> fillInfo(List<Task> tasks){
        ArrayList<String[]> info = new ArrayList<>();
        for(Task task: tasks){
            info.add(getTaskInfo(task.getId()));
        }
        return info;
    }

    public List<Task> getTaskByAccount(String account){
        return dao.getTaskByAccount(account);
    }

    public List<Task> getTaskById(int userId){
        return dao.getTasksById(userId);
    }

    public List<Task> getAllTask(){
        return dao.getAllTask();
    }

    public Task getTask(int id){
        return dao.getTask(id);
    }

    public void addTask(Task task){
        dao.addTask(task);
    }

    public void updateTask(Task task){
        dao.updateTask(task);
    }

    public void deleteTask(int id){
        dao.deleteTask(id);
    }


    public void startTask(int id) throws TaskException, CarException {
        Task task = dao.getTask(id);
        if(task == null) {
            throw new TaskException("Can't find the task");
        }
        if(task.getStatus() > 0){
            throw new TaskException("The task already started.");
        }
        Car availCar = carService.getAvailableCar();
        if(availCar == null){
            throw new CarException("There is no cars available now.");
        }
        task.setCarID(availCar.getCarID());
        task.setStatus(1);
        availCar.setStatus(1);
        task.setDeliverTime(new Date(System.currentTimeMillis()));

        updateTask(task);             // 更新任务和小车进度
        carService.updateCar(availCar);

        // send string command to car.
        new Thread(() -> sendCmd(task, availCar)).start();
    }

    private void sendCmd(Task task, Car availCar){
        try {
            Socket socket = new Socket(availCar.getIp(),availCar.getPort());
            System.out.println(String.format("connect to car(id: %d, ip: %s, port: %d)",availCar.getCarID(),availCar.getIp(),availCar.getPort()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            Place destination = placeService.getPlaceById(task.getDestination());
            String loc_info = destination.getLoc();
            System.out.println("目的地信息: " + loc_info);
            pw.println(loc_info);
            pw.flush();
            logger.info("send command successfully");
            pw.close();
            socket.close();
        } catch (IOException e) {
            logger.error("send command failed.");
            e.printStackTrace();
        }
    }
}
