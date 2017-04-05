<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NetworkManager | Dashboard</title>
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
					<h2>端口列表</h2>
					<ol class="breadcrumb">
						<li><a href="index-2.html">Home</a></li>
						<li><a>服务器管理</a></li>
						<li class="active"><strong>端口管理</strong></li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>端口信息列表</h5>
								<div class="ibox-tools">
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
									<a class="close-link"> <i class="fa fa-times"></i></a>
								</div>
							</div>
							<div class="ibox-content">
								<input type="button"
									class="btn btn-primary btn-sm" id="updateButton"
									onclick='javascript:location.href="<%=path%>/port/UpdatePortInfo.action"'
									value="更新端口信息" />
								<div class="table-responsive">
									<table id="searchtab" class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<th></th>
												<th>所在地</th>
												<th>端口代码</th>
												<th>网桥代码</th>
												<th>端口IP</th>
												<th>MAC地址</th>
												<th>状态</th>
												<th style="text-align: center;">操作</th>
											</tr>
										</thead>
										<tbody id="listbody">
											<s:iterator value="resultList" id="show">
												<tr>
													<td></td>
													<td><s:property value="#show.location" /></td>
													<td><s:property value="#show.portCode" /></td>
													<td><s:property value="#show.BridgeCode" /></td>
													<td><s:property value="#show.portIp" /></td>
													<td><s:property value="#show.macAddress" /></td>
													<td><s:if test="status=='valid'">有效</s:if> <s:if
															test="status=='deleted'">无效</s:if></td>
													<td style="text-align: center;">														
														<s:a action="InUpdate" namespace="/port">
															<s:param name="vo.id">${id}</s:param>
															<span class="glyphicon glyphicon-pencil"></span>
														</s:a>
														<%-- <s:a action="Delete" namespace="/port">
															<s:param name="vo.id">${id}</s:param>
															<i class="fa fa-trash"></i>
														</s:a> --%>
													</td>
												</tr>
											</s:iterator>
										</tbody>
										<tfoot id="tfoot"></tfoot>
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
	<script type="text/javascript" src="${ctx}/jsp/PortPage/js/page.js"></script>
</body>
</html>