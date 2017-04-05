<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>NetworkManager | LinkQualityReport</title>
<%@ include file="/jsp/common_style.jsp"%>
</head>
<body>
	<div id="wrapper">
		<%@ include file="/jsp/common_navigation.jsp"%>
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<%@ include file="/jsp/common_top.jsp"%>
			<div class="row wrapper border-bottom white-bg page-heading">
				<div class="col-lg-10">
					<h2>链路质量报告列表</h2>
					<ol class="breadcrumb">
						<li>
							<a href="index-2.html">Home</a>
						</li>
						<li>
							<a>网络质量报告</a>
						</li>
						<li class="active">
							<strong>链路质量报告列表</strong>
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
								<h5>链路质量报告列表</h5>
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
								<div class="row">
									<s:iterator value="nodeVos" id="show">
										<div class="col-lg-3">
											<div class="widget navy-bg">
												<div class="p-m text-center p-lg">
													<h2 class="m-xs">
														<s:property value="#show.location" />节点
													</h2>
													<h2 class="m-xs">链路报告</h2>
												</div>
												<s:iterator value="#show.links" id="show2">
													<div style="padding-top: 10px; padding-bottom: 10px;">
														<div class="col-sm-3 m-t-xs">
															<s:if test="#show2.linkName =='main'">
																<span class="label label-danger">主链路</span>
															</s:if>
															<s:if test="#show2.linkName =='backup'">
																<span class="label label-muted">备链路</span>
															</s:if>
															<s:if test="#show2.linkName =='sdn'">
																<span class="label label-warning">SDN</span>
															</s:if>
														</div>
														<label class="col-sm-6"><s:property value="#show2.bridgeDesc" /></label>
														<s:a cssClass="btn btn-success btn-sm" action="RealTimeReport" namespace="/linkQualityReport">
															<i class="fa fa-line-chart" aria-hidden="true"></i>
														</s:a>
														<s:a cssClass="btn btn-success btn-sm" action="HistoryReport" namespace="/linkQualityReport">
															<i class="fa fa-history" aria-hidden="true"></i>
														</s:a>
													</div>
												</s:iterator>
											</div>
										</div>
									</s:iterator>
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
	<%-- <script type="text/javascript" src="${ctx}/jsp/LinkQualityReportPage/js/page.js"></script> --%>
</body>
</html>