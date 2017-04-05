<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NetworkManager | Login</title>
<%@ include file="/jsp/common_style.jsp"%>
</head>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>
                <img class="logo-name" src="<%=path%>/img/logo2.png"></img>
            </div>
            <h3>Welcome to NetworkManager</h3>
            <form class="m-t" role="form">
                <div class="form-group">
                    <input id="username" type="username" class="form-control" placeholder="Username" required=""/>
                </div>
                <div class="form-group">
                    <input id="password" type="password" class="form-control" placeholder="Password" required=""/>
                </div>
                <input type="button" class="btn btn-primary block full-width m-b" onclick="login();" value="Login"></input>
            </form>
            <p class="m-t"> <small>Algoblu we app framework base on Bootstrap 3 &copy; 2016</small> </p>
        </div>
    </div>
	<script type="text/javascript" src="<%=path%>/jsp/LoginPage/js/login.js"></script>
</body>
</html>