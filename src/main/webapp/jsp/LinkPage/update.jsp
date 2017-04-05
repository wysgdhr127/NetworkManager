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
<title>NetworkManager | LinkUpdate</title>
<%@ include file="/jsp/common_style.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>添加节点</h2>
					<ol class="breadcrumb">
						<li><a href="index-2.html">Home</a></li>
						<li><a>服务器管理</a></li>
						<li class="active"><strong>添加节点</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>添加节点</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
									</a><a class="close-link"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<form id="moderatorForm" class="form-horizontal"
									action="<%=path%>/node/Update.action" onsubmit="return check()"
									method="post">
									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											节点代码： </label>
										<div class="col-sm-4">
											<input name="vo.id" class="form-control"
												value="${vo.nodeCode}" readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											所在地： </label>
										<div class="col-sm-4">
											<input class="form-control" value="${vo.location}"
												readonly="readonly" />
										</div>
									</div>
									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											节点IP： </label>
										<div class="col-sm-4">
											<input class="form-control" value="${vo.nodeIp}" />
										</div>
									</div>

									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">
											状态：</label>
										<div class="col-sm-4">
											<input class="form-control"
												value=" <s:if test="vo.status=='valid'">有效</s:if>
                        <s:if test="vo.status=='deleted'"> 已删除</s:if>"
												readonly="readonly" />
										</div>
									</div>
									<div class="btn_div">
										<button type="submit"
											class="btn btn-default  btn-primary btn-sm">提交</button>
										<button class="btn btn-default  btn-primary btn-sm"
											type="button" onclick="window.history.go(-1);">返回</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>