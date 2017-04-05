<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!-- alert -->
<div class="alert alert-danger" id="alert"
	style="display:none;position: absolute;width: 400px;left: 32%;top: 0px;text-align: center;" role="alert">
	<span class="glyphicon glyphicon-exclamation-sign" id="mark" aria-hidden="true"></span>
	<strong id="mistake_title">错误！</strong><span id="mistake_content"></span>
</div>