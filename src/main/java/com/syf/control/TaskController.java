package com.syf.control;

import com.syf.bean.Address;
import com.syf.bean.Task;
import com.syf.biz.logger;
import com.syf.service.AddressService;
import com.syf.service.TaskService;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/task", method = {RequestMethod.POST, RequestMethod.GET})
public class TaskController {

    private TaskService taskService;
    private UserService userService;
    private AddressService addressService;

    @Autowired
    public TaskController(TaskService service1, UserService service2, AddressService service3){
        this.taskService = service1;
        this.userService = service2;
        this.addressService = service3;
    }

    @RequestMapping(value = "/addTask.action", method = RequestMethod.POST)
    public String doAddTask(@RequestParam("user") String user,
                            @RequestParam("address") String place,
                            HttpServletRequest request,
                            HttpServletResponse response){
        if(!userService.isExist(user) || !addressService.isExist(place)){
            response.setStatus(400);     // 请求参数有误
            return "用户 or 地址不存在， 请先添加";
        }else{
            Task task = new Task();
            task.setUser(user);
            task.setAddressID(addressService.getAddressID(place));
            task.setCp(taskService.selectRandomCP());
            boolean isAssign = Boolean.valueOf(request.getParameter("assign"));
            task.setAssign(isAssign);
            if(isAssign){
                task.setCarID(Integer.valueOf(request.getParameter("carID")));
            }
            taskService.addTask(task);
            response.setStatus(200);    // ok
            return "添加成功！";
        }
    }

    @RequestMapping(value = "/startTask.do", method = RequestMethod.POST)
    public String startTask(@RequestParam("id") int id, HttpServletResponse response){
        logger.debug(String.format("任务 %d 启动", id));
        String res = taskService.startTask(id);
        response.setStatus(200);
        return res;
    }

    @RequestMapping(value = "/task{id}", method = RequestMethod.GET)
    public ModelAndView showTaskInfo(@PathVariable("id") int taskID){
        ModelAndView mav = new ModelAndView("taskInfo");
        Task task = taskService.getTask(taskID);
        mav.addObject("task", task);
        mav.addObject("destination", addressService.getAddress(task.getAddressID()));
        mav.addObject("process", taskService.getTaskProcess(taskID));
        return mav;
    }

    @RequestMapping(value = "/taskManage", method = RequestMethod.GET)
    public ModelAndView taskManage(){
        ModelAndView mav = new ModelAndView("taskManage");
        int[] statistics = taskService.getTaskStatistics();
        List<Task> tasks = taskService.getAllTask();
        List<Address> addresses = new ArrayList<>(tasks.size());
        for(Task task :tasks){
            addresses.add(addressService.getAddress(task.getAddressID()));
        }
        mav.addObject("addresses", addresses);
        mav.addObject("statistics", statistics);
        mav.addObject("tasks", tasks);
        return mav;
    }

    @RequestMapping(value = "/task/taskOf{account}", method = RequestMethod.GET)
    public ModelAndView listTasksOfUser(@PathVariable String account){
        ModelAndView mav = new ModelAndView("/listTaskOfUser");
        List<Task> tasks = taskService.getTaskByAccount(account);
        mav.addObject("tasks", tasks);
        return mav;
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String refreshTask(){
        List<Task> tasks = taskService.getAllTask();
        for(Task task: tasks){
            taskService.refreshTask(task);
        }
        return "forward:taskManage";
    }

    @RequestMapping(value = "/task{status}", method = RequestMethod.GET)
    public ModelAndView showTaskByStatus(@PathVariable String status){
        ModelAndView mav = new ModelAndView("/classifyTaskByStatus");
        List<Task> tasks = taskService.getTaskByStatus(status);
        List<Address> addresses = new ArrayList<>(tasks.size());
        for(Task task: tasks){
            addresses.add(addressService.getAddress(task.getAddressID()));
        }
        mav.addObject("tasks", tasks);
        mav.addObject("status", status);
        mav.addObject("addresses", addresses);
        return mav;
    }

}
