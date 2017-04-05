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
<title>NetworkManager | NodeList</title>
<%@ include file="/jsp/common_style.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/plugins/dataTables/datatables.min.css" rel="stylesheet" />
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>节点列表</h2>
					<ol class="breadcrumb">
						<li>
							<a href="index-2.html">Home</a>
						</li>
						<li>
							<a>服务器管理</a>
						</li>
						<li class="active">
							<strong>节点管理</strong>
						</li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>节点信息列表</h5>
								<div class="ibox-tools">
									<a class="collapse-link">
										<i class="fa fa-chevron-up"></i>
									</a>
									<a class="close-link">
										<i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<input type="button" class="btn btn-primary btn-sm" id="addButton" onclick='javascript:location.href="<%=path%>/node/InAdd.action"' value="添加" />
								<input type="button" class="btn btn-primary btn-sm" id="updateButton" onclick='javascript:location.href="<%=path%>/node/UpdateNodeInfo.action"' value="更新节点信息" />
								<div class="table-responsive">
									<table id="searchtab" class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th></th>
												<th>所在地</th>
												<th>节点ID</th>
												<th>节点代码</th>
												<th>节点IP</th>
												<th>状态</th>
												<th style="text-align: center;">操作</th>
											</tr>
										</thead>
										<tbody id="listbody">
											<s:iterator value="resultList" id="show">
												<tr>
													<td></td>
													<td><s:property value="#show.location" /></td>
													<td><s:property value="#show.nodeCode" /></td>
													<td><s:property value="#show.nodeName" /></td>
													<td><s:property value="#show.nodeIp" /></td>
													<td><s:if test="status=='valid'">有效</s:if> <s:if test="status=='invalid'">无效</s:if></td>
													<td style="text-align: center;"><s:a action="InUpdate" namespace="/node">
															<s:param name="vo.id">${id}</s:param>
															<span class="glyphicon glyphicon-pencil"></span>
														</s:a> <s:a action="Delete" namespace="/node">
															<s:param name="vo.id">${id}</s:param>
															<i class="fa fa-trash"></i>
														</s:a></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@ include file="/jsp/common_footer.jsp"%>
		</div>
	</div>

	<!-- JS -->
	<script type="text/javascript" src="${ctx}/js/plugins/dataTables/datatables.min.js"></script>
	<script type="text/javascript" src="${ctx}/jsp/NodePage/js/page.js"></script>
</body>
</html>