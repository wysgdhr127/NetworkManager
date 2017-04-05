<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NetworkManager | TopoGraph</title>
<%@ include file="/jsp/common_style.jsp"%>
<link href="<%=path%>/css/plugins/d3/dashboard.css" rel="stylesheet" />
<link href="<%=path%>/css/plugins/d3/d3-context-menu.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="<%=path%>/jsp/DashBoardPage/js/dashboard.js"></script>
<script type="text/javascript" src="<%=path%>/js/plugins/d3/d3.min.js"
	charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/js/plugins/d3/d3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="<%=path%>/js/plugins/d3/d3-context-menu.js" charset="utf-8"></script>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>仪表板</h2>
					<ol class="breadcrumb">
						<li><a href="index-2.html">Home</a></li>
						<li><a>拓扑展示</a></li>
						<li class="active"><strong>仪表板</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>仪表板</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
									<a class="close-link"> <i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="ibox-content">
								<input id="errorSuccess" type="hidden"
									value="<s:property value="error.success" />" /> <input
									id="errorErrorCod" type="hidden"
									value="<s:property value="error.errorCode" />" /> <input
									id="errorErrorMessage" type="hidden"
									value="<s:property value="error.errorMessage"/>" />
								<div id="display"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
