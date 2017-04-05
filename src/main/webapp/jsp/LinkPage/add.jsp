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
	src="<%=path%>/jsp/NodePage/js/page.js"></script>
</head>
<body>
	<div>节点配置 > 添加节点</div>
	<hr
		style="margin-top: 0px; margin-bottom: 5px; border: 0; border-top: 1px solid #eee; width: 100%;"></hr>
	<div class="addcontent">
		<div>
			<form id="functionForm" class="form-horizontal"
				action="<%=path%>/node/Add.action" onsubmit="return check()"
				method="post">
				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						节点代码： </label>
					<div class="col-sm-3">
						<input id="nodeCode" name="vo.nodeCode"
							class="form-control" value="" />
					</div>
					<span style="color: red;">*</span><span id="nodeCode_s"
						style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						所在地： </label>
					<div class="col-sm-3">
						<input id="location" name="vo.location"
							   class="form-control" value="" />
					</div>
					<span style="color: red;">*</span><span id="location_s"
						style="color: red;"></span>
				</div>

				<div class="form-group">
					<label for="inputName3" class="col-sm-2 control-label">
						节点IP： </label>
					<div class="col-sm-3">
						<input id="nodeIp" name="vo.nodeIp"
							   class="form-control" value="" />
					</div>
					<span style="color: red;">*</span><span id="nodeIp_s"
															style="color: red;"></span>
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