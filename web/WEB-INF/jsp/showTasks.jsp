<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>任务</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>任务管理</h2>
    <p> 相关任务信息 </p>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>用户</th>
            <th>地址</th>
            <th>开始时间</th>
            <th>状态</th>
            <th>详情</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${tasks}" var="task">
            <tr>
                <th>${task.id}</th>
                <th>${task.userID}</th>
                <th>${task.destination}</th>
                <th>${task.startTime}</th>
                <th>${task.status}</th>
                <th>
                    <button type="button" class="btn btn-link"><a href="${pageContext.request.contextPath}/task/task${task.id}">详细信息</a></button>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>