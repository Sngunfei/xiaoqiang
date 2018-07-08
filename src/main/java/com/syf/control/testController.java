package com.syf.control;

import com.syf.bean.Task;
import com.syf.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/test")
public class testController {

    private TaskService taskService;

    @Autowired
    public testController(TaskService service){
        this.taskService = service;
    }

    @RequestMapping(value = "/showTasks", method = RequestMethod.GET)
    public ModelAndView test(){
        List<Task> tasks = taskService.getAllTask();
        ModelAndView mav = new ModelAndView("showTasks");
        mav.addObject("tasks", tasks);
        return mav;
    }
}
