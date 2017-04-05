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
<title>添加菜单</title>
<%@ include file="/jsp/common_style.jsp"%>
<script type="text/javascript"
	src="<%=path%>/jsp/FunctionPage/js/page.js"></script>
</head>
<body>
	<div>菜单管理 > 添加菜单</div>
	<hr
		style="margin-top: 0px; margin-bottom: 5px; border: 0; border-top: 1px solid #eee; width: 100%;"></hr>
	<div class="addcontent">
		<div>
			<form id="functionForm" class="form-horizontal"
				action="<%=path%>/function/Add.action" onsubmit="return check()"
				method="post">
				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						菜单名称： </label>
					<div class="col-sm-3">
						<input id="functionName" name="vo.functionName"
							class="form-control" value="" />
					</div>
					<span style="color: red;">*</span><span id="functionName_s"
						style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						菜单级别： </label>
					<div class="col-sm-3">
						<select id="functionLevel" name="vo.functionLevel"
							class="form-control">
							<option value="0">一级菜单</option>
							<option value="1">二级菜单</option>
						</select>
					</div>
					<span style="color: red;">*</span><span id="functionLevel_s"
						style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						上级菜单： </label>
					<div class="col-sm-3">
						<s:select cssClass="form-control"
							name="vo.parentFunctionId" id="parentFunctionId" label="部门"
							list="functionList" listKey="id" value="functionName"
							listValue="functionName" headerKey="0" headerValue="无"></s:select>
					</div>
					<span style="position: relative; color: red; line-height: 40px;">*</span><span
						id="parentFunctionId_s" style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">
						可操作角色：</label>
					<div class="col-sm-3">
						管理员：<input id="role1" type="checkbox" name="vo.roleString"
							value="admin" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						主持人：<input
							id="role2" type="checkbox" name="vo.roleString" value="moderator" />
					</div>
					<span style="position: relative; color: red; line-height: 40px;">*</span>
					<span id="roleString_s" style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">
						菜单链接：</label>
					<div class="col-sm-3">
						<input id="functionUrl"
							name="vo.functionUrl" class="form-control" value="" />
					</div>
					<span style="position: relative; color: red; line-height: 40px;">*</span>
					<span id="functionUrl_s" style="color: red;"></span>
				</div>
				<div class="btn_div">
					<button type="submit" class="btn btn-default  btn-primary btn-sm">提交</button>
					<button class="btn btn-default  btn-primary btn-sm" type="button"
						onclick="window.history.go(-1);">返回</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>