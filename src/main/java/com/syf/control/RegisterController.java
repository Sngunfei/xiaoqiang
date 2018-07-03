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
public class RegisterController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    private final AdministerService administerService;

    @Autowired
    public RegisterController(AdministerService administerService) {
        this.administerService = administerService;
    }

    @RequestMapping(value = "/register.do", method = RequestMethod.POST)
    public ModelAndView registerDo(HttpServletRequest request, @RequestParam("account") String account,
                                   @RequestParam("password") String password,
                                   @RequestParam("passwordConfirm") String passwordConfirm){
        ModelAndView mav = new ModelAndView("login");
        try{
            Administer admin = administerService.getAdminister(account);
            if(admin == null){
                logger.info("建立新账户"+account);
                admin = new Administer();
                admin.setAccount(account);
                admin.setPassword(password);
                admin.setIp(request.getRemoteAddr());
                admin.setPort(request.getRemotePort());
                administerService.register(admin);
            }
        }catch (Exception e){
            logger.info(account+" 账户已存在，注册失败");
            mav.addObject("errorMessage","账户已存在，请重试");
            mav.setViewName("register");
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(){
        return "register";
    }
}
