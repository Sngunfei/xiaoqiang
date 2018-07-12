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
        var descrip = $.trim($('#descrip').val());
        var x = $.trim($('#x').val());
        var y = $.trim($('#y').val());
        var z = $.trim($('#z').val());
        var x1 = $.trim($('#x1').val());
        var y1 = $.trim($('#y1').val());
        var z1 = $.trim($('#z1').val());
        var w1 = $.trim($('#w1').val());

        if(!descrip || !x || !y || !z || !x1 || !y1 || !z1 || !w1)
        {
            alert('坐标信息不能为空！');
            return false;
        }

        $.ajax(
            {
                url: '${pageContext.request.contextPath}/place/addPlace.do',
                data:{"descrip":descrip, "x":x, "y": y, "z": z, "x1": x1, "y1": y1, "z1": z1, "w1": w1},
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
                    alert('错误！');
                },
                complete:function()
                {
                    $('#acting_tips').hide();
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
                <li><a href="${pageContext.request.contextPath}/task/taskManage">任务管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="${pageContext.request.contextPath}/car/carManage">车辆管理</a></li>
                <li><a href="${pageContext.request.contextPath}/user/userManage">用户管理</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/place/placeManage">蜂巢管理</a></li>
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
                    <h2 class="sub-header">地址信息</h2>
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addUser">添加新地址</button>
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
                                    新地址
                                </h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" role="form">
                                    <div class="form-group">
                                        <label for="descrip" class="col-sm-3 control-label">地址</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" id="descrip" name="descrip" value=""
                                                   placeholder="详细地址">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="x" class="col-sm-3 control-label">X坐标</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="x" value="" id="x"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="y" class="col-sm-3 control-label">Y坐标</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="y" value="" id="y"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="z" class="col-sm-3 control-label">Z坐标</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="z" value="" id="z"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="x1" class="col-sm-3 control-label">X角度</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="x1" value="" id="x1"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="y1" class="col-sm-3 control-label">Y角度</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="y1" value="" id="y1"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="z1" class="col-sm-3 control-label">Z角度</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="z1" value="" id="z1"
                                                   placeholder="">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="w1" class="col-sm-3 control-label">W角度</label>
                                        <div class="col-sm-9">
                                            <input type="text" class="form-control" name="w1" value="" id="w1"
                                                   placeholder="">
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
                        <th class="text-center">描述</th>
                        <th class="text-center">X坐标</th>
                        <th class="text-center">Y坐标</th>
                        <th class="text-center">Z坐标</th>
                        <th class="text-center">X角度</th>
                        <th class="text-center">Y角度</th>
                        <%--<th class="text-center">Z角度</th>--%>
                        <%--<th class="text-center">W角度</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${places}" var="place" varStatus="loop">
                        <tr>
                            <th class="text-center">${place.id}</th>
                            <th class="text-center">${place.locX}</th>
                            <th class="text-center">${place.locY}</th>
                            <th class="text-center">${place.locZ}</th>
                            <th class="text-center">${place.angX}</th>
                            <th class="text-center">${place.angY}</th>
                            <%--<th class="text-center">${place.angZ}</th>--%>
                            <%--<th class="text-center">${place.angW}</th>--%>
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
