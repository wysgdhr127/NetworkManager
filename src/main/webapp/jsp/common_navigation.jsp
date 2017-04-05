<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav metismenu" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span>
						<a href="${ctx}/jsp/index.jsp">
							<i class="fa fa-user-circle-o fa-5x"></i>
						</a>
					</span>
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<span class="clear">
							<span class="block m-t-xs">
								<strong class="font-bold">${sessionScope.userinfo.realName}</strong>
							</span>
							<span class="text-muted text-xs block">${sessionScope.userinfo.functionPrivilege}<b class="caret"></b>
							</span>
						</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li>
							<a href="profile.html">个人信息修改</a>
						</li>
						<li class="divider"></li>
						<li>
							<a id="logoutnav" href="#">退出</a>
						</li>
					</ul>
				</div>
				<div class="logo-element">
					<a href="${ctx}/jsp/index.jsp">Algoblu</a>
				</div>
			</li>
			<c:if test="${!empty sessionScope.menulist}">
				<c:forEach items="${sessionScope.menulist}" var="menu1" varStatus="status">
					<li id="firstMenu">
						<a href="#">
							<i class="fa fa-${menu1.type}"></i>
							<span class="nav-label">${menu1.name}</span>
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<c:forEach items="${menu1.nextLevelMenu}" var="menu2" varStatus="status">
								<c:if test="${fn:endsWith(menu2.url,'.action')}">
									<li id="secondMenu">
										<a href="${ctx}${menu2.url}">${menu2.name}</a>
									</li>
								</c:if>
								<c:if test="${!fn:endsWith(menu2.url,'.action')}">
									<li>
										<a href="#">${menu2.name}</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</nav>