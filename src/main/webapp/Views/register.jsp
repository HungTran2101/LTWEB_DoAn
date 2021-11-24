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
        <form id="resform" action="register" method="post" class="log_res_Form">
            <span>Register</span>
            <input id="username" type="text" name="username" placeholder="User name"/>
            <input id="email" type="text" name="email" placeholder="E-mail"/>
            <input id="password" type="password" name="password" placeholder="Password"/>
            <input id="repassword" type="password" name="repassword" placeholder="Re Password"/>
            <button type="button" name="register" onclick="myRegister()">Register</button>
            <a href="login.tiles">Click here to login</a>
        </form>
        <p id="warning" style="display: none; color: red; text-align: center; margin-top: 20px;"></p>
        <%
        	String result = (String)request.getAttribute(LogRes.requestResult);
            if(result != null){
            	if(result.equals(LogRes.registerFailed)){
        %>
                	<p id="warning2" style="color: red; text-align: center; margin-top: 20px;">Register failed. Try again!</p>
        <%    		
            	}
            }
        %>
    </div>
</body>
<script type="text/javascript" src="Views/assets/logresValid.js"></script>
</html>