// STATIC[翻页]
pageMark = function() {
	// 分页信息
	$('#currentPage').val(PAGE.currentPage);
	// 去除为空搜索项
	$('#search_form *[name]').each(function() {
		if ("" == $(this).val()) {
			$(this).attr("name", "");
		}
	});
	$('#search_form').submit();
}

function check() {
	var functionName = $("#functionName").val();
	var functionLevel = $("#functionLevel").val();
	var parentFunctionId = $("#parentFunctionId").val();
	var role1 = $("#role1").is(':checked');
	var role2 = $("#role2").is(':checked');
	var functionUrl = $("#functionUrl").val();
	$("#functionName_s").html("");
	$("#parentFunctionId_s").html("");
	$("#roleString_s").html("");
	$("#functionUrl_s").html("");
	if (functionName == '') {
		$("#functionName_s").html("请输入菜单名称！");
		return false;
	}
	if (parentFunctionId == 0 && functionLevel == 1) {
		$("#parentFunctionId_s").html("请输入选择上级菜单！");
		return false;
	}
	if (parentFunctionId != 0 && functionLevel == 0) {
		$("#parentFunctionId_s").html("一级菜单不能有上级菜单！");
		return false;
	}
	if (!(role1||role2)) {
		$("#roleString_s").html("请至少选择一个可操作角色！");
		return false;
	}
	if (functionUrl == '') {
		$("#functionUrl_s").html("请输入菜单链接！");
		return false;
	}
	return true;
}