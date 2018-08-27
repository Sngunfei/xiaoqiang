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
    // 提交表单
    import * as $ from "./jquery";

    function check_form()
    {
        let user = $.trim($('#user').val());
        let address = $.trim($('#address').val());

        if(!user)
        {
            alert('用户不能为空！');
            return false;
        }

        if(!address)
        {
            alert('地址不能为空！');
            return false;
        }

        var form_data = $('#form_data').serialize();

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/task/addTask.do',
                data:{"user":user, "address": address},
                type: "post",
                beforeSend:function()
                {
                    $("#tip").html("<span style='color:blue'>正在处理...</span>");
                    return true;
                },
                success:function()
                {
                    alert("添加成功");
                    location.reload();
                },
                error:function()
                {
                    alert('用户/地址不存在，请先添加');
                },
                complete:function()
                {
                    $('#acting_tips').hide();
                }
            });

        return false;
    }

    function statusTranslateToString(status){
        if(1 === status) {
            return "正在运送中"
        }
        if(0 === status){
            return "已就绪"
        }
        if(2 === status){
            return "已完成"
        }
        if(3 === status){
            return "失败"
        }
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
                <li><a href="${pageContext.request.contextPath}/place/placeManage">蜂巢管理</a></li>
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
                    <a href="${pageContext.request.contextPath}/task/ready">
                    <img src= "../../resources/icon/ready.png" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    </a>
                    <h4>尚未进行</h4>
                    <span class="text-muted">${info[1]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href = "${pageContext.request.contextPath}/task/done">
                    <img src="../../resources/icon/done.png" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    </a>
                    <h4>已完成</h4>
                    <span class="text-muted">${info[3]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href = "${pageContext.request.contextPath}/task/doing">
                    <img src="../../resources/icon/doing.png" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    </a>
                    <h4>进行中</h4>
                    <span class="text-muted">${info[2]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href = "${pageContext.request.contextPath}/task/error">
                    <img src="../../resources/icon/error.png" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    </a>
                    <h4>故障信息</h4>
                    <span class="text-muted">${info[4]}</span>
                </div>
            </div>

            <div class="row">
                <div class="form-inline">
                    <h2 class="sub-header">所有任务</h2>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addTask">新建任务</button>
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
                            <th class="text-center">
                                <c:choose>
                                    <c:when test="${task.status==0}">已就绪</c:when>
                                    <c:when test="${task.status==1}">正在配送</c:when>
                                    <c:when test="${task.status==2}">已完成</c:when>
                                    <c:when test="${task.status==3}">失败</c:when>
                                </c:choose>
                            </th>
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
