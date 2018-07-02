<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login</title>
</head>

<body class="login-bg">
<div class="login-body">
    <div class="login-heading">
        <h1>Login</h1>
    </div>
    <div class="login-info">

            <input type = "hidden" name ="userId" id = "Id"/>

            <div class="form-group">
                <label for="userPwd" class="control-label">Password</label>
                <input type="password" maxlength="10" class="form-control" id="userPwd" name="user.password" required>
            </div>
    </div>
</div>


</body>
</html>