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

<script>
    // 提交表单
    import * as $ from "./jquery";

    function check_form()
    {
        var ip = $.trim($('#ip').val());
        var port = $.trim($('#port').val());

        if(!ip)
        {
            alert('IP地址不能为空！');
            return false;
        }

        if(!port)
        {
            alert('端口号不能为空！');
            return false;
        }

        var form_data = $('#form_data').serialize();

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/car/addCar.do',
                data:{"ip":ip, "port": port},
                type: "post",
                beforeSend:function()
                {
                    $("#tip").html("<span style='color:blue'>正在添加...</span>");
                    return true;
                },
                success:function()
                {
                    alert("添加成功");
                    location.reload();
                },
                error:function()
                {
                    alert('添加失败');
                },
                complete:function()
                {
                    $('#acting_tips').hide();
                }
            });
        return false;
    }

    function testConnect() {
        var ip = $.trim($('#ip').val());
        var port = $.trim($('#port').val());

        if(!ip)
        {
            alert('IP地址不能为空！');
            return false;
        }

        if(!port)
        {
            alert('端口号不能为空！');
            return false;
        }

        let form_data = $('#form_data').serialize();

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/car/testConnect.do',
                data:{"ip":ip, "port": port},
                type: "post",
                beforeSend:function()
                {
                    // $("#tip").html("<span style='color:blue'>正在添加...</span>");
                    return true;
                },
                success:function()
                {
                    alert("连接成功！");
                    location.reload();
                },
                error:function()
                {
                    alert('无法连接！');
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
    1<div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="${pageContext.request.contextPath}/task/taskManage">任务管理 <span class="sr-only">(current)</span></a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/car/carManage">车辆管理</a></li>
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
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>所有车辆</h4>
                    <span class="text-muted">${carInfo[0]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>空闲车辆</h4>
                    <span class="text-muted">${carInfo[1]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>正在进行</h4>
                    <span class="text-muted">${carInfo[2]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>正在返回</h4>
                    <span class="text-muted">${carInfo[3]}</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" width="100" height="100" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>故障车辆</h4>
                    <span class="text-muted">${carInfo[4]}</span>
                </div>
            </div>

            <div class="row">
                <div class="form-inline">
                    <h2 class="sub-header">车辆信息</h2>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addTask">添加小车</button>
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
                                    添加新车
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="ip" class="col-sm-3 control-label">IP</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="ip" name="ip" value=""
                                                   placeholder="IP地址">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="port" class="col-sm-3 control-label">Port</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="port" value="" id="port"
                                                   placeholder="端口号">
                                        </div>
                                    </div>
                                </form>
                            </div>


                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" class="btn btn-default" onclick="testConnect()">测试</button>
                                <button type="submit" class="btn btn-primary">提交</button><span id="tip"></span>
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
                        <th class="text-center">ip地址</th>
                        <th class="text-center">端口号</th>
                        <th class="text-center">状态</th>
                        <th class="text-center">Test</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cars}" var="car" varStatus="loop">
                        <tr>
                            <th class="text-center">${car.carID}</th>
                            <th class="text-center">${car.ip}</th>
                            <th class="text-center">${car.port}</th>
                            <th class="text-center">${car.status}</th>
                            <th class="text-center">
                                <button type="button" class="btn btn-link"><a href="${pageContext.request.contextPath}/car/car-${car.carID}-test">详细信息</a></button>
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
