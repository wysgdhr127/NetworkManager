window.onload = function () {
	$(".liwidth:eq(0)").addClass("active");
	$(".tab-pane:eq(0)").addClass("active");
}

function check() {
	var policy = $("#policy").val();
	var delay_time = $("#delay_time").val();
	var jitter = $("#jitter").val();

	if (policy == '') {
		alertFailure("时延不能为空！");
		return false;
	}
	
	if (delay_time == '') {
		alertFailure("时延不能为空！");
		return false;
	}
	
	if (jitter == '') {
		alertFailure("时延不能为空！");
		return false;
	}
	
	return true;
}