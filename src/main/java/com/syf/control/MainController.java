package com.syf.control;

import com.syf.bean.Task;
import com.syf.service.AdministerService;
import com.syf.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.List;

@Controller
public class MainController{

    private TaskService taskService;

    @Autowired
    public MainController(TaskService service) {
        this.taskService = service;
    }

    // 展示所有任务
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView listAllTasks(){
        ModelAndView mav = new ModelAndView("main");
        List<Task> tasks = taskService.getAllTask();
        mav.addObject("tasks", tasks);
        for(Task task:tasks){
            System.out.println(task.toString());
        }
        return mav;
    }

    // 添加新任务
    @RequestMapping(value = "/index/addTask", method = RequestMethod.POST)
    public String addTask(Task task){
        task.setStartTime(new Date(System.currentTimeMillis()));
        taskService.addTask(task);
        return "/main.jsp";
    }

    // 列出某个用户的任务
    @RequestMapping(value = "/index/listTasksOf-{account}", method = RequestMethod.GET)
    public ModelAndView listTasksOfUser(@PathVariable String account){
        ModelAndView mav = new ModelAndView();
        List<Task> tasks = taskService.getTaskByAccount(account);
        mav.addObject("tasks", tasks);
        mav.setViewName("/listTaskOfUser");
        return mav;
    }

    // 删除任务
    @RequestMapping(value = "/index/deleteTask-{taskID}", method = RequestMethod.GET)
    public String deleteTask(@PathVariable int taskID){
        taskService.deleteTask(taskID);
        return "/main.jsp";
    }
}
