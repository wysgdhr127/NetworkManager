function login() {
	$("#span_msg").html("");
	var username = $("#username").val().trim();
	var password = $("#password").val().trim();
	var ischeck = true;
	if (username == '') {
		$("#span_msg").html("用户名为空!");
		ischeck = false;
		return;
	}
	if (password == '') {
		$("#span_msg").html("密码为空!");
		ischeck = false;
		return;
	}
	var data={};
	data['vo.username']=username;
	data['vo.password']=password;
	// 点击搜索
	$.ajax({
		type : "POST",
		url : contextPath+"/login.action",
		data : data,
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.success) {				
				window.location.href = contextPath + "/jsp/index.jsp";								
			} else {				
				alert(data.msg);
			}
		},
		error : function() {
			alert("系统发生错误，请联系管理员。");
		}
	})

}
