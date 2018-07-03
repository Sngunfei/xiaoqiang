package com.syf.control;

import com.syf.bean.Task;
import com.syf.service.PlaceService;
import com.syf.service.TaskService;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

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
    public ModelAndView addTask(@RequestParam("place") String place, @RequestParam("user") String user){
        ModelAndView mav = new ModelAndView("forward:newTask");
        if(!userService.isExist(user)){
            mav.addObject("errorMessage", "用户不存在");
            return mav;
        }else if(!placeService.isExist(place)){
            mav.addObject("errorMessage", "地址不存在");
            return mav;
        }else{
            mav.setViewName("main");
            Task task = new Task();
            task.setUserID(userService.getUserByAccount(user).getId());
            task.setDestination(placeService.addrToId(place));
            task.setStatus("ready");
            task.setStartTime(new Date(System.currentTimeMillis()));
            taskService.addTask(task);
            return mav;
        }
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.GET)
    public String newTask(){
        return "newTask";
    }

    @RequestMapping(value = "/startTask.do", method = RequestMethod.POST)
    public String startTask(@RequestParam("taskid") int id){
        try {
            taskService.startTask(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "main";
    }
}
