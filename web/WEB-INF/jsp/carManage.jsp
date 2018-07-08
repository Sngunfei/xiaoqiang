<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="A layout example that shows off a responsive email layout.">
    <title>车辆管理</title>
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../../resources/css/dashboard.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <!--<![endif]-->
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="${pageContext.request.contextPath}/task/taskManage">任务管理 <span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/car/carManage">车辆管理</a></li>
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
            <h1 class="page-header">Dashboard</h1>
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>所有车辆</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>空闲车辆</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>正在进行</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>故障车辆</h4>
                    <span class="text-muted">Something else</span>
                </div>
            </div>

            <div class="row">
                <div class="form-inline">
                    <h2 class="sub-header">车辆信息</h2>
                    <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addTask">添加小车</button>
                </div>
            </div>

            <!-- 模态框（Modal） -->
            <form method="post" action="" class="form-horizontal" role="form" id="form_data" onsubmit="return check_form()" style="margin: 20px;">
                <div class="modal fade" id="addTask" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    新建任务
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="user" class="col-sm-3 control-label">用户</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="user" name="user" value=""
                                                   placeholder="请输入顾客账户">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="address" class="col-sm-3 control-label">地址</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="address" value="" id="address"
                                                   placeholder="地址">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="remark" class="col-sm-3 control-label">备注</label>
                                        <div class="col-sm-9">
                                <textarea  class="form-control"  name="remark" id="remark"
                                           placeholder="备注信息">

                                </textarea>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <button type="submit" class="btn btn-primary">
                                    提交
                                </button><span id="tip"> </span>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal -->
                </div>
            </form>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th class="text-center">#</th>
                        <th class="text-center">用户</th>
                        <th class="text-center">收货地址</th>
                        <th class="text-center">类别</th>
                        <th class="text-center">开始时间</th>
                        <th class="text-center">结束时间</th>
                        <th class="text-center">状态</th>
                        <th class="text-center">详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${tasks}" var="task" varStatus="loop">
                        <tr>
                            <th class="text-center">${task.id}</th>
                            <th class="text-center">${task.userID}</th>
                            <th class="text-center">${task.destination}</th>
                            <th class="text-center">顺丰快递</th>
                            <th class="text-center">${task.startTime}</th>
                            <th class="text-center">${task.finishTime}</th>
                            <th class="text-center">${task.status}</th>
                            <th class="text-center">
                                <button type="button" class="btn btn-link"><a href="${pageContext.request.contextPath}/task/task${task.id}">详细信息</a></button>
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
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
