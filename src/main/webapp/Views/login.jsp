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
    <title>Login</title>
</head>
<body class="body">
    <div class="Log_Res_container">
        <form action="login" method="post" class="log_res_Form">
            <span>Please Sign In</span>
            <input type="email" name="email" placeholder="E-mail" required/>
            <input type="password" name="password" placeholder="Password" required/>
            <div>
                <input type="checkbox" name="rememberMe" id="rememberMe"/>
                <label for="rememberMe">Remember Me</label>
                <a href="forgotPass.tiles">forgot password</a>
            </div>
            <button type="submit" name="login">Login</button>
            <a href="register.tiles">Click here to register</a>
        </form>
        <%
        	String result = (String)request.getAttribute(LogRes.requestResult);
            if(result != null){
            	if(result.equals(LogRes.wrongAccount)){
        %>
                	<p style="color: red; text-align: center; margin-top: 20px;">Your email or password is incorrect!</p>
        <%    		
            	}
            }
        %>
    </div>
</body>
</html>