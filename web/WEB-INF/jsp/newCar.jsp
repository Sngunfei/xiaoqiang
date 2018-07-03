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
        <jsp:text >添加小车</jsp:text>
    </div>
    <form action="${pageContext.request.contextPath}/car/addCar.do" method="post">
        <div class="login-input-box">
            <input type="text" name="host" placeholder="ip地址">
        </div>
        <div class="login-input-box">
            <input type="text" name="port" placeholder="端口号">
        </div>
        <div class="login-button-box">
            <button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
        </div>
    </form>
</div>

</body>
</html>