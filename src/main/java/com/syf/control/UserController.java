package com.syf.control;

import com.syf.bean.Address;
import com.syf.bean.Task;
import com.syf.bean.User;
import com.syf.service.AddressService;
import com.syf.service.TaskService;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;
    private TaskService taskService;
    private AddressService addressService;

    @Autowired
    public UserController(UserService service1, TaskService service2, AddressService service3) {
        this.userService = service1;
        this.taskService = service2;
        this.addressService = service3;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setAccount(request.getParameter("account"));
        user.setNickname(request.getParameter("nickname"));
        user.setPassword(request.getParameter("password"));
        user.setIp(request.getRemoteAddr());
        user.setEmail(request.getParameter("email"));
        userService.save(user);
        response.setStatus(200);
    }

    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public ModelAndView manage(){
        ModelAndView mav = new ModelAndView("userManage");
        mav.addObject("users", userService.getAllAccounts());
        return mav;
    }

    @RequestMapping(value = "/user-{account}", method = RequestMethod.GET)
    public ModelAndView userInfo(@PathVariable("account") String account){
        ModelAndView mav = new ModelAndView("userInfo");
        User user = userService.getUserByAccount(account);
        List<Task> tasks = taskService.getTaskByAccount(account);
        List<String> addresses = new ArrayList<>();
        for(Task task: tasks){
            addresses.add(addressService.getAddress(task.getAddressID()).getAddress());
        }
        mav.addObject("user", user);
        mav.addObject("tasks", tasks);
        mav.addObject("addresses", addresses);
        return mav;
    }

}
