//package com.syf.control;
//
//import com.syf.bean.Administer;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class BaseController {
//
//    protected static final String ERROR_MSG_KEY = "errorMsg";
//
//    // 获取保存在Session中的用户对象
//    protected Administer getSessionAdmin(HttpServletRequest request){
//        return (Administer) request.getSession().getAttribute("ADMIN_CONTEXT");
//    }
//
//    // 将用户对象保存到session中
//    protected void setSessionAdmin(HttpServletRequest request, Administer admin){
//        request.getSession().setAttribute("ADMIN_CONTEXT", admin);
//    }
//
//    public final String getAppbaseUrl(HttpServletRequest request, String url){
//        return request.getContextPath() + url;
//    }
//}
