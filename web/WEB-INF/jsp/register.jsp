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
    <div class="login-header" style="text-align: center">
        <jsp:text >注册</jsp:text>
    </div>
    <form name = "form1" action="${pageContext.request.contextPath}/register.do" method="post">
        <div class="login-input-box">
            <span class="icon icon-user"></span>
            <input id = "acc" type="text" name="account" placeholder="请输入账户">
        </div>
        <div class="login-input-box">
            <span class="icon icon-password"></span>
            <input id = "pwd" type="password" name="password" placeholder="请输入密码">
        </div>
        <div class="login-input-box">
            <span class="icon icon-password"></span>
            <input id = "pwd1" type="password" name="passwordConfirm" onchange="checkPWD()" placeholder="请确认密码">
        </div>
        <div id="msg" style="color:red"></div>
        <div class="login-button-box">
            <button id = "sub" class="btn btn-lg btn-primary btn-block" type="submit">确认</button>
        </div>
    </form>
    <div>
        <h4>${errorMessage}</h4>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    function checkPWD(){
        var password = document.getElementById("pwd");
        var passwordConfirm = document.getElementById("pwd1");
        if(password.value !== passwordConfirm.value)
            document.getElementById("msg").innerHTML="两次输入密码不一致，请重新输入";
        else
            document.getElementById("msg").innerHTML="密码一致"
    }
</script>