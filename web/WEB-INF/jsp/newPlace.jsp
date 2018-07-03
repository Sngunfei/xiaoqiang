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
        <jsp:text >添加新地址</jsp:text>
    </div>
    <form action="${pageContext.request.contextPath}/place/addPlace.do" method="post">
        <div class="login-input-box">
            <input type="text" name="descrip" placeholder="详细地址">
        </div>
        <div class="login-input-box">
            <input type="text" name="locx" placeholder="x坐标">
        </div>
        <div class="login-input-box">
            <input type="text" name="locy" placeholder="y坐标">
        </div>
        <div class="login-input-box">
            <input type="text" name="locz" placeholder="z坐标">
        </div>
        <div class="login-input-box">
            <input type="text" name="angx" placeholder="x角度">
        </div>
        <div class="login-input-box">
            <input type="text" name="angy" placeholder="y角度">
        </div>
        <div class="login-input-box">
            <input type="text" name="angz" placeholder="z角度">
        </div>
        <div class="login-input-box">
            <input type="text" name="angw" placeholder="w角度">
        </div>
        <div class="login-button-box">
            <button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
        </div>
    </form>

    <div>
        <h5>地址信息用于在启动任务时构造出string格式的命令，发送给当前可用小车</h5>
    </div>


</div>

</body>
</html>