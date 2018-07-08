package com.syf.control;

import com.syf.bean.User;
import com.syf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    public void addUserDo(HttpServletRequest request, HttpServletResponse response){
        User user = new User();
        user.setAccount(request.getParameter("user"));
        user.setNickname(request.getParameter("nickname"));
        user.setPassword(request.getParameter("pwd"));
        user.setIp(request.getRemoteAddr());
        user.setEmail(request.getParameter("email"));
        userService.save(user);
        try{
            response.getWriter().print("{\"success\":true}");
            response.getWriter().flush();
            response.getWriter().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/userManage", method = RequestMethod.GET)
    public ModelAndView userManager(){
        ModelAndView mav = new ModelAndView("userManage");
        mav.addObject("users", userService.getAllAccounts());
        return mav;
    }

}
