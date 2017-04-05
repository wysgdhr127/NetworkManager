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
<title>NetworkManager | NodeUpdate</title>
<%@ include file="/jsp/common_style.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>修改用户信息</h2>
					<ol class="breadcrumb">
						<li><a href="index-2.html">Home</a></li>
						<li><a>用户管理</a></li>
						<li class="active"><strong>修改用户信息</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>修改用户信息</h5>
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
									<input type="hidden" name="vo.id" class="form-control"
										value="${vo.id}" />
									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											用户名： </label>
										<div class="col-sm-3">
											<input id="username" name="vo.username" class="form-control"
												value="${vo.username}" />
										</div>
										<span style="color: red;">*</span><span id="username_s"
											style="color: red;"></span>
									</div>
									<div class="hr-line-dashed"></div>

									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											客户名称： </label>
										<div class="col-sm-3">
											<input id="realName" name="vo.realName" class="form-control"
												value="${vo.realName}" />
										</div>
										<span style="color: red;">*</span><span id="realName_s"
											style="color: red;"></span>
									</div>
									<div class="hr-line-dashed"></div>

									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											用户邮箱： </label>
										<div class="col-sm-3">
											<input id="adminEmail" name="vo.adminEmail"
												class="form-control" value="${vo.adminEmail}" />
										</div>
										<span id="adminEmail_s" style="color: red;"></span>
									</div>
									<div class="hr-line-dashed"></div>

									<div class="form-group">
										<label for="inputName3" class="col-sm-2 control-label">
											用户权限： </label>
										<div class="col-sm-3">
											<s:if test="vo.functionPrivilege=='admin'">
												<input class="form-control" value="管理员" readonly="readonly" />
											</s:if>
											<s:if test="vo.functionPrivilege=='customer'">
												<input class="form-control" value="客户" readonly="readonly" />
											</s:if>
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-2 control-label">
											状态：</label>
										<div class="col-sm-3">
											<s:if test="vo.status=='valid'">
												<input class="form-control" value="有效" readonly="readonly" />
											</s:if>
											<s:if test="vo.status=='invalid'">
												<input class="form-control" value="无效" readonly="readonly" />
											</s:if>
										</div>
									</div>
									<div class="hr-line-dashed"></div>
									
									<div class="form-group">
										<div class="col-sm-4 col-sm-offset-2">
											<button class="btn btn-white btn-sm" type="button"
												onclick="window.history.go(-1);">返回</button>
											<button type="submit" class="btn btn-primary btn-sm">提交</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="/jsp/common_footer.jsp"%>
		</div>
	</div>
</body>
</html>