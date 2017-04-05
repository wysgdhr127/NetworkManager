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
<title>NetworkManager | Dashboard</title>
<%@ include file="/jsp/common_style.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/plugins/toastr/toastr.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<script>
	window.name = [ -1 ];
</script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row  border-bottom white-bg dashboard-header">
				<div class="col-md-3">
					<h2>Welcome ${sessionScope.userinfo.realName}</h2>
					<small>欢迎登陆NetworkManager.</small>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
