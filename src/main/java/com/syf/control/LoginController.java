package com.syf.control;

import com.syf.bean.Administer;
import com.syf.service.AdministerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    private final AdministerService administerService;

    @Autowired
    public LoginController(AdministerService administerService) {
        this.administerService = administerService;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ModelAndView l111(HttpServletRequest request, @RequestParam("account") String account, @RequestParam("password") String password) {
        Administer dbAdmin = administerService.getAdminister(account);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("errorMessage", "wrong!");
        if (dbAdmin == null) {
            System.out.println("null!!!!!!!!!!!!!!!!!!!1");
            mav.addObject("errorMessage", "账户不存在！");
        } else if (!dbAdmin.getPassword().equals(password)) {
            mav.addObject("errorMessage", "密码不正确！");
        } else {
            dbAdmin.setIp(request.getRemoteAddr());
            administerService.updateAdminister(dbAdmin);
            mav.setViewName("main");
        }
        System.out.println(account);
        System.out.println(password);
        return mav;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        //session.removeAttribute();
        return "login";
    }

}
