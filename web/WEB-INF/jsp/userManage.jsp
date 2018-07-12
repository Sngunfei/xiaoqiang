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
    function check_form()
    {
        var user = $.trim($('#user').val());
        var pwd = $.trim($('#pwd').val());
        var nickname = $.trim($('#nickname').val());
        var email = $.trim($('#email').val());

        if(!user)
        {
            alert('账户不能为空！');
            return false;
        }

        if(!pwd)
        {
            alert('密码不能为空！');
            return false;
        }

        var form_data = $('#form_data').serialize();

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/user/addUser.do',
                data:{"user":user, "pwd": pwd, "email": email, "nickname": nickname},
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
                    alert('错误！');
                },
                complete:function()
                {
                    $('#acting_tips').hide();
                }
            });

        return false;
    }

    // $(function () { $('#addUserModal').on('hide.bs.modal', function () {
    //     // 关闭时清空edit状态为add
    //     location.reload();
    // })
    // });
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
                <li><a href="${pageContext.request.contextPath}/task/taskManage">任务管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/car/carManage">车辆管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/user/userManage">用户管理</a></li>
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
            <div class="row">
                <div class="form-inline">
                    <h2 class="sub-header">用户信息</h2>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addUser">添加用户</button>
                </div>
            </div>

            <!-- 模态框（Modal） -->
            <form method="post" action="" class="form-horizontal" role="form" id="form_data" onsubmit="return check_form()" style="margin: 20px;">
                <div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                </button>
                                <h4 class="modal-title" id="myModalLabel">
                                    新用户
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="user" class="col-sm-3 control-label">账户</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="user" name="user" value=""
                                                   placeholder="账户">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="pwd" class="col-sm-3 control-label">密码</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="pwd" value="" id="pwd"
                                                   placeholder="密码">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="pwd" class="col-sm-3 control-label">昵称</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="nickname" value="" id="nickname"
                                                   placeholder="昵称">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="pwd" class="col-sm-3 control-label">邮箱</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="email" value="" id="email"
                                                   placeholder="邮箱">
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
                        <th class="text-center">账户</th>
                        <th class="text-center">密码</th>
                        <th class="text-center">昵称</th>
                        <th class="text-center">邮箱</th>
                        <th class="text-center">上次登录地址</th>
                        <th class="text-center">详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user" varStatus="loop">
                        <tr>
                            <th class="text-center">${user.id}</th>
                            <th class="text-center">${user.account}</th>
                            <th class="text-center">${user.password}</th>
                            <th class="text-center">${user.nickname}</th>
                            <th class="text-center">${user.email}</th>
                            <th class="text-center">${user.ip}:${user.port}</th>
                            <th class="text-center">
                                <button type="button" class="btn btn-link"><a href="${pageContext.request.contextPath}/user/userinfo-${user.id}">详细信息</a></button>
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
