<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统信息列表</title>
<%@ include file="/jsp/common_style.jsp"%>
<script type="text/javascript"
	src="<%=path%>/jsp/FunctionPage/js/page.js"></script>
</head>
<body>
	<div class="control_div">系统设置 > 系统信息列表</div>
	<hr
		style="margin-top: 10px; margin-bottom: 10px; border: 0; width: 100%; border-top: 1px solid #eee;" />

	<div id="findpatient" class="control_div">
		<form id="search_form" class="form-inline" method="post">
			<%-- 页面指针 --%>
			<input id="currentPage" type="hidden" name="svo.currentPage"
				value="<s:property value="svo.currentPage" />" />
		</form>
		<div style="width: 95%; margin-left: 23px;">
			<table id="searchtab" class="table table-bordered table-hover"
				style="table-layout: fixed">
				<thead>
					<tr class="tr2">
						<td width="5%">编号</td>
						<td width="15%">菜单名称</td>
						<td width="8%">菜单级别</td>
						<td width="15%">上级菜单</td>
						<td width="15%">可操作角色</td>
						<td width="20%">菜单链接</td>
						<td width="5%">状态</td>
						<td style="text-align: center;" width="8%">操作</td>
					</tr>
				</thead>
				<tbody id="listbody">
					<s:iterator value="resultList" id="show">
						<tr>
							<td><s:property value="#show.id" /></td>
							<td><s:property value="#show.functionName" /></td>
							<td><s:property value="#show.functionLevelStr" /></td>
							<td><s:property value="#show.parentStr" /></td>
							<td><s:property value="#show.roleStr" /></td>
							<td><s:property value="#show.functionUrl" /></td>
							<td><s:property value="#show.status" /></td>
							<td style="text-align: center;"><s:a action="InUpdate"
									namespace="/function">
									<s:param name="vo.id">${id}</s:param><span class="glyphicon glyphicon-pencil"></span></s:a></td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot id="tfoot"></tfoot>
			</table>
		</div>

		<div class="listControll">
			<%@ include file="/jsp/common_pagenum.jsp"%>
		</div>
	</div>
</body>
</html>