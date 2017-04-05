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
<title>菜单列表详情</title>
<%@ include file="/jsp/common_style.jsp"%>
<script type="text/javascript"
	src="<%=path%>/jsp/FunctionPage/js/page.js"></script>
</head>
<body>
	<div>菜单管理 > 菜单列表 > 详情</div>
	<hr
		style="margin-top: 0px; margin-bottom: 5px; border: 0; border-top: 1px solid #eee; width: 100%;"></hr>
	<div class="addcontent">
		<div>
			<form id="moderatorForm" class="form-horizontal"
				action="<%=path%>/moderator/Update.action" onsubmit="return check()"
				method="post">
				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						主持人编号： </label>
					<div class="col-sm-4">
						<input name="vo.id" class="form-control" value="${vo.id}"
							readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						菜单名称： </label>
					<div class="col-sm-4">
						<input class="form-control" value="${vo.functionName}"
							readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						菜单级别： </label>
					<div class="col-sm-4">
						<input class="form-control" value="${vo.functionLevelStr}"
							readonly="readonly" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						上级菜单： </label>
					<div class="col-sm-4">
						<input class="form-control" value="${vo.parentStr}"
							readonly="readonly" />
					</div>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						可操作角色： </label>
					<div class="col-sm-4">
						<input class="form-control" value="${vo.roleStr}"
							readonly="readonly" />
					</div>
				</div>
				<c:if test="${vo.functionUrl!=null}">
					<div class="form-group">
						<label for="inputName3" class="col-sm-2 control-label">
							菜单链接： </label>
						<div class="col-sm-4">
							<input class="form-control" value="${vo.functionUrl}"
								readonly="readonly" />
						</div>
					</div>
				</c:if>
				<div class="form-group">
					<label for="inputPassword3" class="col-sm-2 control-label">
						菜单状态：</label>
					<div class="col-sm-4">
						<input class="form-control" value="${vo.status}"
							readonly="readonly" />
					</div>
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