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
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainF(){
        return "main";
    }


}
