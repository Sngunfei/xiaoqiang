<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="../../resources/css/login.css"/>">
</head>
<body>

<div id="content">
    <div class="login-header">
        <img src="../../resources/icon/logo.png">
    </div>
    <form action="${pageContext.request.contextPath}/login.do" method="post">
        <div class="login-input-box">
            <span class="icon icon-user"></span>
            <input type="text" name="account" placeholder="Please enter your email/phone">
        </div>
        <div class="login-input-box">
            <span class="icon icon-password"></span>
            <input type="password" name="password" placeholder="Please enter your password">
        </div>
        <div class="remember-box">
            <label>
                <input type="checkbox">&nbsp;Remember Me
            </label>
        </div>
        <div class="login-button-box">
            <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
        </div>
        <div class="logon-box">
            <a href="">Forgot?</a>
            <a href="${pageContext.request.contextPath}/register">Register</a>
        </div>
    </form>
    <div>
        <h4>${errorMessage}</h4>
    </div>

</div>

</body>
</html>