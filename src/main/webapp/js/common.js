//侧边栏JS
$(function() {

	var mainNav = $("#side-menu li#firstMenu");
	var subNavEventA = $("#secondMenu a");

	var setNavTion = function(mainNavNum, subNavNum, thrNavNum, scrollTop) {
		if (mainNavNum != -1) {
			mainNav.eq(mainNavNum).addClass("active");
			mainNav.eq(mainNavNum).find("ul").eq(subNavNum).addClass("in");
			mainNav.eq(mainNavNum).find("ul").eq(subNavNum).find("li").eq(
					thrNavNum).addClass("active");
			$("#mainNav").scrollTop(scrollTop);
		}
	};
	setNavTion(window.name.split(',')[0], window.name.split(',')[1],
			window.name.split(',')[2], window.name.split(',')[3]);

	// 页面刷新前获取侧边栏的激活信息
	var navNum = [];
	$(window)
			.on(
					'beforeunload',
					function() {
						navNum[0] = $("#side-menu li#firstMenu.active")
								.prevAll().length - 1;
						navNum[1] = $("#side-menu li#firstMenu.active ul.in")
								.prevAll().length - 1;
						navNum[2] = $(
								"#side-menu li#firstMenu.active ul.in li.active")
								.prevAll().length;
						navNum[3] = $("#side-menu").scrollTop();
						window.name = navNum;
					});

	// 二级菜单点击
	var clickA = function() {
		subNavEventA.parents("li").removeClass("active");
		$(this).parents("li").addClass("active");
	}
	subNavEventA.on("click", clickA)

})

$(function() {
	
	toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "progressBar": true,
			  "preventDuplicates": false,
			  "positionClass": "toast-top-center",
			  "onclick": null,
			  "showDuration": "400",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
	
	if ($('#errorSuccess').val() == "true") {
		toastr["success"]($('#errorErrorMessage').val());
	}
	if ($('#errorSuccess').val() == "false") {
		toastr["error"]($('#errorErrorMessage').val());
	}
})

// DataTable插件JS
$(document).ready(
		function() {
			if ($("#searchtab").length != 0) {
				/*$('.dataTables-example').DataTable(
						{
							pageLength : 25,
							responsive : true,
							dom : '<"html5buttons"B>lTfgitp',
							buttons : [
									{
										extend : 'copy'
									},
									{
										extend : 'csv'
									},
									{
										extend : 'excel',
										title : 'ExampleFile'
									},
									{
										extend : 'pdf',
										title : 'ExampleFile'
									},
									{
										extend : 'print',
										customize : function(win) {
											$(win.document.body).addClass(
													'white-bg');
											$(win.document.body).css(
													'font-size', '10px');
											$(win.document.body).find('table')
													.addClass('compact').css(
															'font-size',
															'inherit');
										}
									} ]
						});
			}*/
			var t = $('.dataTables-example').DataTable( {
		        "columnDefs": [ {
		            "searchable": false,
		            "orderable": false,
		            "targets": 0
		        } ],
		        "order": [[ 1, 'asc' ]],
		        pageLength : 25,
				responsive : true,
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
						{
							extend : 'copy'
						},
						{
							extend : 'csv'
						},
						{
							extend : 'excel',
							title : 'ExampleFile'
						},
						{
							extend : 'pdf',
							title : 'ExampleFile'
						},
						{
							extend : 'print',
							customize : function(win) {
								$(win.document.body).addClass(
										'white-bg');
								$(win.document.body).css(
										'font-size', '10px');
								$(win.document.body).find('table')
										.addClass('compact').css(
												'font-size',
												'inherit');
							}
						} ]
			});
		 
		    t.on( 'order.dt search.dt', function () {
		        t.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
		            cell.innerHTML = i+1;
		        } );
		    } ).draw();
		}});