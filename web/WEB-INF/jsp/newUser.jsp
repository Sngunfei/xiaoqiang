<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>addTask</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/login.css"/>">
</head>
<body>

<div id="content">
    <div class="login-header" style="text-align: center">
        <jsp:text >添加新用户</jsp:text>
    </div>
    <form action="${pageContext.request.contextPath}/user/addUser.do" method="post">
        <div class="login-input-box">
            <input type="text" name="account" placeholder="账户">
        </div>
        <div class="login-input-box">
            <input type="text" name="password" placeholder="密码">
        </div>
        <div class="login-button-box">
            <button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
        </div>
    </form>
    <!--添加任务出错，用户/目的地不存在-->
    <div>
        <h5>用户须知：***************************************************************</h5>
    </div>
    <div>
        <h4>${errorMessage}</h4>
    </div>

</div>

</body>
</html>