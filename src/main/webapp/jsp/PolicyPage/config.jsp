<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
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
					<h2>${nodeVo.location}链路切换策略配置</h2>
					<ol class="breadcrumb">
						<li>
							<a href="index-2.html">Home</a>
						</li>
						<li>
							<a>策略管理</a>
						</li>
						<li class="active">
							<strong>${nodeVo.location}链路切换策略配置</strong>
						</li>
					</ol>
				</div>
				<div class="col-lg-2"></div>
			</div>
			<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-2">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>当前节点策略</h5>
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
							</div>
						</div>
					</div>
					<div class="col-lg-10">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<h5>${nodeVo.location}链路切换策略配置</h5>
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
								<ul class="nav nav-tabs" role="tablist">
									<s:iterator id="show" value="nodeVoList">
										<li role="presentation" class="liwidth">
											<a href="#<s:property value="#show.nodeCode"/>" aria-controls="<s:property value="#show.nodeCode"/>" role="tab" data-toggle="tab">
												To
												<s:property value="#show.location" />
												链路策略
											</a>
										</li>
									</s:iterator>
								</ul>

								<div class="tab-content">
									<s:iterator id="show" value="nodeVoList">
										<div role="tabpanel" class="tab-pane" id="<s:property value="#show.nodeCode"/>">
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>链路名称</th>
															<th>时延(ms)</th>
															<th>抖动</th>
															<th>丢包率(%)</th>
															<th>备用链路</th>
														</tr>
													</thead>
													<tbody id="listbody">
														<form id="policyForm" action="<%=path%>/policy/Update.action" method="post" onsubmit="return check()">
															<s:iterator id="link" value="%{#show.links}">
																<tr>
																	<input name="policyVoList[<s:property value="#link.id"/>].linkId" value="<s:property
																value="#link.id" />" type="hidden" />
																	<td><select name="policyVoList[<s:property value="#link.id"/>].linkName" id="linkId" class="form-control">
																			<s:if test="#link.linkName=='main'">
																				<option value="main">主链路</option>
																			</s:if>
																			<s:if test="#link.linkName=='backup'">
																				<option value="backup">备用链路</option>
																			</s:if>
																			<s:if test="#link.linkName=='sdn'">
																				<option value="sdn">SDN</option>
																			</s:if>
																		</select></td>
																	<td><input name="policyVoList[<s:property value="#link.id"/>].delayTime" id="delayTime" value="<s:property value="#link.policy.delayTime"/>" type="text" class="form-control" /></td>
																	<td><input type="text" id="jitter" name="policyVoList[<s:property value="#link.id"/>].jitter" value="<s:property value="#link.policy.jitter"/>" class="form-control" /></td>
																	<td><input name="policyVoList[<s:property value="#link.id"/>].lossPackageRate" value="<s:property value="#link.policy.lossPackageRate"/>" id="lossPackageRate" type="text" class="form-control" /></td>
																	<td><select name="policyVoList[<s:property value="#link.id"/>].backupLinkId" id="backupLinkId" class="form-control">
																			<s:if test="#link.optLinkName[0]=='main'">
																				<option value="<s:property value="#link.optPortCode[0]"/>">主链路</option>
																			</s:if>
																			<s:if test="#link.optLinkName[0]=='backup'">
																				<option value="<s:property value="#link.optPortCode[0]"/>">备用链路</option>
																			</s:if>
																			<s:if test="#link.optLinkName[0]=='sdn'">
																				<option value="<s:property value="#link.optPortCode[0]"/>">SDN</option>
																			</s:if>
																			<s:if test="#link.optLinkName[1]=='main'">
																				<option value="<s:property value="#link.optPortCode[1]"/>">主链路</option>
																			</s:if>
																			<s:if test="#link.optLinkName[1]=='backup'">
																				<option value="<s:property value="#link.optPortCode[1]"/>">备用链路</option>
																			</s:if>
																			<s:if test="#link.optLinkName[1]=='sdn'">
																				<option value="<s:property value="#link.optPortCode[1]"/>">SDN</option>
																			</s:if>
																		</select>
																	</td>
																</tr>
															</s:iterator>
														</form>
													</tbody>
												</table>
												<div class="form-group">
													<div class="col-sm-4 col-sm-offset-5">
														<button type="submit" class="btn btn-primary">激活策略</button>
													</div>
												</div>
											</div>
										</div>
									</s:iterator>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- JS -->
	<script type="text/javascript" src="${ctx}/jsp/PolicyPage/js/page.js"></script>
</body>
</html>
