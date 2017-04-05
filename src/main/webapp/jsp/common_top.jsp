<script type="text/javascript" src="<%=path%>/jsp/LoginPage/js/logout.js"></script>
<div class="row border-bottom">
	<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header">
			<a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
				<i class="fa fa-bars"></i>
			</a>
			<form role="search" class="navbar-form-custom" action="http://webapplayers.com/inspinia_admin-v2.7/search_results.html">
				<div class="form-group">
					<input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
				</div>
			</form>
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li>
				<span class="m-r-sm text-muted welcome-message">Welcome to NetworkManager System.</span>
			</li>
			<li>
				<a href="#" id="logout">
					<i class="fa fa-sign-out"></i> Log out
				</a>
			</li>
		</ul>
		<input id="errorSuccess" type="hidden" value="<s:property value="error.success" />" />
		<input id="errorErrorCod" type="hidden" value="<s:property value="error.errorCode" />" />
		<input id="errorErrorMessage" type="hidden" value="<s:property value="error.errorMessage"/>" />

	</nav>
</div>