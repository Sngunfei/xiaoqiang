package com.syf.control;

import com.syf.bean.Task;
import com.syf.service.PlaceService;
import com.syf.service.TaskService;
import com.syf.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/task", method = {RequestMethod.POST, RequestMethod.GET})
public class TaskController {

    private TaskService taskService;
    private UserService userService;
    private PlaceService placeService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService, PlaceService placeService){
        this.taskService = taskService;
        this.userService = userService;
        this.placeService = placeService;
    }

    @RequestMapping(value = "/addTask.do", method = RequestMethod.POST)
    public String addTask(@RequestParam("user") String user, @RequestParam("address") String place,
                          HttpServletResponse response){
        if(!userService.isExist(user) || !placeService.isExist(place)){
            return "error";
        }else{
            Task task = new Task();
            task.setUserID(userService.getUserByAccount(user).getId());
            task.setDestination(placeService.addrToId(place));
            task.setStatus(0);
            task.setStartTime(new Date(System.currentTimeMillis()));
            task.setCp(taskService.randomCp());
            taskService.addTask(task);
            String json = "{\"success\":true}";
            try{
                response.getWriter().print(json);
                response.getWriter().flush();
                response.getWriter().close();
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @RequestMapping(value = "/startTask.do", method = RequestMethod.POST)
    public void startTask(@RequestParam("id") int id, HttpServletResponse response){
        try {
            System.out.println("将要启动的任务:" + id);
            taskService.startTask(id);
            response.getWriter().println("{\"success\":true}");
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/task{id}", method = RequestMethod.GET)
    public ModelAndView showTaskInfo(@PathVariable("id") int taskId){
        ModelAndView mav = new ModelAndView("taskInfo");
        Task task = taskService.getTask(taskId);
        mav.addObject("task", task);
        mav.addObject("account", userService.getAccountById(task.getUserID()));
        mav.addObject("place", placeService.getDescripById(task.getDestination()));
        return mav;
    }

    @RequestMapping(value = "/taskManage", method = RequestMethod.GET)
    public ModelAndView taskManager(){
        ModelAndView mav = new ModelAndView("taskManage");
        List<Task> tasks = taskService.getAllTask();
        int[] info = taskService.getTaskNumInfo();
        String[] accounts = new String[tasks.size()];
        String[] places = new String[tasks.size()];
        for(int i=0; i< tasks.size(); i++){
            accounts[i] = userService.getAccountById(tasks.get(i).getUserID());
            places[i] = placeService.getDescripById(tasks.get(i).getDestination());
        }
        mav.addObject("accounts", accounts);
        mav.addObject("places", places);
        mav.addObject("info", info);
        mav.addObject("tasks", tasks);
        return mav;
    }

    @RequestMapping(value = "/task/tasks-of-{account}", method = RequestMethod.GET)
    public ModelAndView listTasksOfUser(@PathVariable String account){
        ModelAndView mav = new ModelAndView();
        List<Task> tasks = taskService.getTaskByAccount(account);
        mav.addObject("tasks", tasks);
        mav.setViewName("/listTaskOfUser");
        return mav;
    }


    // 利用ssdb存储任务进度
}
