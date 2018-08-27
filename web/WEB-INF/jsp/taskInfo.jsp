<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../resources/icon/task.png">

    <title>任务管理</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../resources/css/dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<script>

    function startTask()
    {
        let id = $.trim($('#taskID').val());

        if(!id)
        {
            return false;
        }

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/task/startTask.do',
                data:{"id":id},
                type: "post",

                success:function()
                {
                    alert("开始运行");
                    location.reload();
                },
                error:function()
                {
                },
                complete:function()
                {
                }
            });
        return false;
    }
</script>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/main">首页</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">控制台</a></li>
                <li><a href="#">设置</a></li>
                <li><a href="#">个人信息</a></li>
                <li><a href="#">帮助</a></li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="${pageContext.request.contextPath}/task/taskManage">任务管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/car/carManage">车辆管理</a></li>
                <li><a href="${pageContext.request.contextPath}/user/userManage">用户管理</a></li>
                <li><a href="#">蜂巢管理</a></li>
                <li><a href="#">其他设置</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="${pageContext.request.contextPath}/user/newUser">添加用户</a></li>
                <li><a href="${pageContext.request.contextPath}/place/newPlace">添加地址</a></li>
                <li><a href="${pageContext.request.contextPath}/car/newCar">添加小车</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="row">
                <div class="form-inline">
                    <h2 class="sub-header">任务信息</h2>
                </div>
            </div>


            <div>
                <h4>任务ID：${task.id}</h4>
                <h4>状态：<c:choose>
                            <c:when test="${task.status==0}">已就绪</c:when>
                            <c:when test="${task.status==1}">正在配送</c:when>
                            <c:when test="${task.status==2}">已完成</c:when>
                            <c:when test="${task.status==3}">失败</c:when>
                         </c:choose>
                </h4>
                <h4>收货地址：${place}</h4>
                <h4>收货人：${account}</h4>
                <h4>开始时间：${task.startTime}</h4>
                <h4>运送时间：${task.deliverTime}</h4>
                <h4>结束时间：${task.finishTime}</h4>
            </div>
            <input type="hidden" name="taskID" id="taskID" value="${task.id}">
            <button class="btn btn-primary btn-sm" onclick="startTask()">Run!</button>

        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../resources/css/bootstrap-3.3.7-dist/js/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="../../resources/css/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
</body>
</html>
