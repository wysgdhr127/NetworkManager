<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>NetworkManager | PolicyList</title>
<%@ include file="/jsp/common_style.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/plugins/dataTables/datatables.min.css"
	rel="stylesheet" />
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
						<li><a href="index-2.html">Home</a></li>
						<li><a>服务器管理</a></li>
						<li class="active"><strong>节点管理</strong></li>
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
									<a class="collapse-link"> <i class="fa fa-chevron-up"></i></a>
									<a class="close-link"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<div class="table-responsive">
									<table id="searchtab"
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr class="tr2">
												<th></th>
												<th>策略名称</th>
												<th>当前链路名称</th>
												<th>备用链路名称</th>
												<th>时延(ms)</th>
												<th>抖动</th>
												<th>丢包(%)</th>
												<th>策略状态</th>
												<th style="text-align: center;">操作</th>
											</tr>
										</thead>
										<tbody id="listbody">
											<s:iterator value="resultList" id="show">
												<tr>
													<td></td>
													<td><s:property value="#show.bridgeDesc" /></td>
													<td><s:if test="linkName =='main'">主链路</s:if> 
														<s:if test="linkName =='backup'">备用链路</s:if>
														<s:if test="linkName =='sdn'">SDN</s:if>
													</td>
													<td><s:if test="backupLinkName =='main'">主链路</s:if>
														<s:if test="backupLinkName =='backup'">备用链路</s:if>
														<s:if test="backupLinkName =='sdn'">SDN</s:if>
													</td>
													<td><s:property value="#show.delayTime" /></td>
													<td><s:property value="#show.jitter" /></td>
													<td><s:property value="#show.lossPackageRate" /></td>
													<td><s:if test="status=='valid'">有效</s:if> 
														<s:if test="status=='deleted'">已删除</s:if>
													</td>
													<td style="text-align: center;">
														<s:a action="InUpdate" namespace="/node">
															<s:param name="vo.id">${id}</s:param>
															<span class="glyphicon glyphicon-pencil"></span>
														</s:a>
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
		</div>
	</div>
	
	<!-- JS -->
	<script type="text/javascript"
		src="${ctx}/js/plugins/dataTables/datatables.min.js"></script>
	<script type="text/javascript" src="${ctx}/jsp/PolicyPage/js/page.js"></script>
</body>
</html>