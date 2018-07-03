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
import java.util.List;
import java.util.logging.Logger;

@Service
public class TaskService {

    private TaskDao dao;
    private CarService carService;
    private PlaceService placeService;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoginController.class);

    @Autowired
    public TaskService(TaskDao dao, CarService service1, PlaceService service2){
        this.dao = dao;
        this.carService = service1;
        this.placeService = service2;
    }

    public List<Task> getTaskByAccount(String account){
        return dao.getTaskByAccount(account);
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
        if(!task.getStatus().equals("ready")){
            throw new TaskException("The task already started.");
        }
        Car availCar = carService.getAvailCar();
        if(availCar == null){
            throw new CarException("There is no cars available now.");
        }
        task.setCarID(availCar.getCarID());
        task.setStatus("delivering");
        task.setDeliverTime(new Date(System.currentTimeMillis()));

        // send string command to car.
        sendCmd(task, availCar);
    }

    private void sendCmd(Task task, Car availCar){
        try {
            Socket socket = new Socket(availCar.getIp(),availCar.getPort());
            logger.info(String.format("connect to car(id: %d, ip: %s, port: %d)",availCar.getCarID(),availCar.getIp(),availCar.getPort()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            Place destination = placeService.getPlaceById(task.getDestination());
            String loc_info = destination.getLoc();

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
