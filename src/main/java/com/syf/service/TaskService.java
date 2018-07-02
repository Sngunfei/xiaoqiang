package com.syf.service;

import com.syf.XQExceptions.TaskNotFoundException;
import com.syf.dao.TaskDao;
import com.syf.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskDao dao;

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

//    public void startTask(int id) throws TaskNotFoundException {
//        Task task = dao.getTask(id);
//        if(task == null) {
//            throw new TaskNotFoundException("Can't find the task");
//        }
//        if(!task.getStatus().equals("ready")){
//
//        }
//        if()
//        task.setStatus("delivering");
//
//    }
}
