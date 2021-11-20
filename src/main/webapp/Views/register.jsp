<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="common.LogRes" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Views/css/style.css">
    <title>Register</title>
</head>
<body class="body">
    <div class="Log_Res_container">
        <form action="register" method="post" class="log_res_Form">
            <span>Register</span>
            <input type="text" name="username" placeholder="User name" required/>
            <input type="email" name="email" placeholder="E-mail" required/>
            <input type="password" name="password" placeholder="Password" required/>
            <input type="password" name="repassword" placeholder="Re Password" required/>
            <button type="submit" name="register">Register</button>
            <a href="login.tiles">Click here to login</a>
        </form>
        <%
        	String result = (String)request.getAttribute(LogRes.requestResult);
            if(result != null){
            	if(result.equals(LogRes.registerFailed)){
        %>
                	<p style="color: red; text-align: center; margin-top: 20px;">Register failed. Try again!</p>
        <%    		
            	}
            	else if(result.equals(LogRes.rePassNotMatch)){
        %>
                	<p style="color: red; text-align: center; margin-top: 20px;">Confirm password is not match!</p>
        <%
            	}
            }
        %>
    </div>
</body>
</html>