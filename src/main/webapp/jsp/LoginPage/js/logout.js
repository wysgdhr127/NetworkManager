$(function(){
	
	var loginOutHandler = {};
	
	loginOutHandler.logout=function(){
		
		$.ajax({
			type : "POST",
			url : contextPath + "/logout.action",
			data : {},
			async : false,
			success : function(jsonStr) {
				if (jsonStr.success) {
					window.top.frames.location.href = contextPath + "/jsp/LoginPage/login.jsp";
				} else {
					alert(json.errorMsg);
				}
			},
			error : function() {
				alert("系统发生错误，请联系管理员。");
			}
		});
	}
	$("#logout").on("click",loginOutHandler.logout);
	$("#logoutnav").on("click",loginOutHandler.logout);
})