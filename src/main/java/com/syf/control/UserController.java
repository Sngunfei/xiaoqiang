package com.syf.control;

import com.syf.bean.User;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService service){
        this.userService = service;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String addUser(){
        return "newUser";
    }

    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    public String addUserDo(HttpServletRequest request){
        User user = new User();
        user.setAccount(request.getParameter("account"));
        user.setPassword(request.getParameter("password"));
        user.setIp(request.getRemoteAddr());
        userService.save(user);
        return "main";
    }

}
