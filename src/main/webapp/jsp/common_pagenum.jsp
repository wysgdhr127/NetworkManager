<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<p class="pageTrans" class="pull-right inlineblock"
	style="text-align: right; margin-right: 40px;">
	<strong>总条数:${svo.totalResult} &nbsp;&nbsp;</strong><strong><em>1/1</em>&nbsp;&nbsp;</strong> <input
		type="button"  class="btn btn-default btn-sm back" value="&laquo;" /> <input
		type="button" class="btn btn-default btn-sm forward" value="&raquo;" /> <span>跳转</span>
	<input type="text" class="btn btn-default btn-sm num" size="1" /> <input
		type="button" class="btn btn-default btn-sm sure" value="确定" />
</p>

<script type="text/javascript">
	$(function() {
		PAGE.totalPage = '${svo.totalPage}';
		PAGE.showSize = '${svo.showSize}';
		PAGE.currentPage = '${svo.currentPage}';

		if (PAGE.totalPage > 0) {
			$(".pageTrans").show();
			ALGOBLU.pageSize();
		} else {
			$(".pageTrans").show();
			var thead = $("thead").find("tr").children().length;
			$("#tfoot").html("<tr><td colspan="+thead+" style=\"text-align:center\">未找到符合查询条件的选项</td></tr>");
		}

	});
</script>