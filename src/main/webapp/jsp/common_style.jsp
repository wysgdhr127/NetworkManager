<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%-- 页面公共样式 编码 --%>
<!-- Common-JS  -->
<script type="text/javascript" src="${ctx}/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript"
	src="${ctx}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script type="text/javascript" src="${ctx}/js/inspinia.js"></script>
<script type="text/javascript" src="${ctx}/js/plugins/pace/pace.min.js"></script>

<!-- jQuery UI -->
<script type="text/javascript"
	src="${ctx}/js/plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- GITTER -->
<script type="text/javascript"
	src="${ctx}/js/plugins/gritter/jquery.gritter.min.js"></script>

<!-- Toastr -->
<script type="text/javascript"
	src="${ctx}/js/plugins/toastr/toastr.min.js"></script>
	
	
<!-- Common-CSS  -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/animate.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/plugins/toastr/toastr.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/font-awesome/css/font-awesome.css" rel="stylesheet" />
<script type="text/javascript">
	var contextPath = '${ctx}';
</script>



