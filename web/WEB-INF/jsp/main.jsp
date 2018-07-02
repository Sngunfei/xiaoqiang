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
    <title>主页</title>

    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-" crossorigin="anonymous">

    <link rel="stylesheet" href="../../resources/css/main.css">
    <!--<![endif]-->
</head>
<body>

<div id="layout" class="content pure-g">
    <div id="nav" class="pure-u">
        <a href="#" class="nav-menu-button">Menu</a>

        <div class="nav-inner">
            <button class="primary-button pure-button">注销</button>
            <button class="primary-button pure-button"><a href="/index" style="color:#FFFFFF">刷新</a></button>
            <div class="pure-menu">
                <ul class="pure-menu-list">
                    <li class="pure-menu-item"><a href="/task/newTask" class="pure-menu-link">新任务</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">正在派送</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">已完成</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">地址管理</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">用户管理</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link">智能车</a></li>
                    <li class="pure-menu-heading">管理</li>
                    <li class="pure-menu-item"><a href="/user/newUser" class="pure-menu-link"><span class="email-label-personal"></span>添加用户(测试用)</a></li>
                    <li class="pure-menu-item"><a href="/place/newPlace" class="pure-menu-link"><span class="email-label-work"></span>添加地址(测试用)</a></li>
                    <li class="pure-menu-item"><a href="#" class="pure-menu-link"><span class="email-label-travel"></span>待加</a></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="list" class="pure-u-1">
        <%--<div class="email-item email-item-selected pure-g">--%>
            <%--<div class="pure-u">--%>
                <%--<img width="64" height="64" alt="Tilo Mitra&#x27;s avatar" class="email-avatar" src="img/common/tilo-avatar.png">--%>
            <%--</div>--%>

            <%--<div class="pure-u-3-4">--%>
                <%--<h5 class="email-name">标题1</h5>--%>
                <%--<h4 class="email-subject">副标题</h4>--%>
                <%--<p class="email-desc">--%>
                    <%--叙述--%>
                <%--</p>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--@elvariable id="tasks" type="java.util.List"--%>
        <c:forEach items="${tasks}" var="task">
        <div class="email-item email-item-unread pure-g">

            <div class="pure-u-3-4">
                <h5 class="email-name">${task.id}</h5>
                <h4 class="email-subject">${task.destination}</h4>
                <h4 class="email-subject">${task.userID}</h4>
                <h4 class="email-desc">
                    ${task.startTime}
                </h4>
                <p>
                    详细描述。。。。。。。
                </p>
            </div>
        </div>
        </c:forEach>
        </div>
    </div>
</div>

<script src="https://yui-s.yahooapis.com/3.18.1/build/yui/yui-min.js"></script>
<script>
    YUI().use('node-base', 'node-event-delegate', function (Y) {

        var menuButton = Y.one('.nav-menu-button'),
            nav        = Y.one('#nav');

        // Setting the active class name expands the menu vertically on small screens.
        menuButton.on('click', function (e) {
            nav.toggleClass('active');
        });

        // Your application code goes here...

    });
</script>

</body>
</html>
