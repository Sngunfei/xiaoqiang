package com.syf.control;

import com.syf.bean.Task;
import com.syf.bean.User;
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
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    UserService userService;
    TaskService taskService;

    @Autowired
    public UserController(UserService service1, TaskService service2) {
        this.userService = service1;
        this.taskService = service2;
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


    @RequestMapping(value = "/userinfo-{id}", method = RequestMethod.GET)
    public ModelAndView userInfo(@PathVariable("id") int userID){
        /*
            展示用户详情，包括用户信息，用户添加的地址信息，用户所有任务
         */
        ModelAndView mav = new ModelAndView("userInfo");

        User user = userService.getUserById(userID);
        List<Task> tasks = taskService.getTaskById(userID);
        mav.addObject("user", user);
        mav.addObject("tasks", tasks);

        return mav;
    }

}
