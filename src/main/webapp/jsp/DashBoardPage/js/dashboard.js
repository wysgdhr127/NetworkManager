window.onload = function() {
	var nodes = {};
	var links = [];
	var activelinks = {};
	var data = {};
	$.ajax({
		type : "POST",
		url : contextPath + "/dashboard/AjaxSearch.action",
		data : data,
		async : false,
		success : function(data) {
			nodes = data.node;
			activelinks = data.link;
			links = data.allLinks;
		}
	})

	// sort links by source, then target
	links.sort(function(a, b) {
		if (a.source > b.source) {
			return 1;
		} else if (a.source < b.source) {
			return -1;
		} else {
			if (a.target > b.target) {
				return 1;
			}
			if (a.target < b.target) {
				return -1;
			} else {
				return 0;
			}
		}
	});
	activelinks.sort(function(a, b) {
		if (a.source > b.source) {
			return 1;
		} else if (a.source < b.source) {
			return -1;
		} else {
			if (a.target > b.target) {
				return 1;
			}
			if (a.target < b.target) {
				return -1;
			} else {
				return 0;
			}
		}
	});
	// any links with duplicate source and target get an incremented
	// 'linknum'
	for (var i = 0; i < links.length; i++) {
		if (links[i].source > links[i].target) {
			if (links[i].type == "main") {
				links[i].linknum = 0;
				links[i].direction = " 0 0 0 ";
				links[i].mark = 1;
			} else if (links[i].type == "backup") {
				links[i].linknum = 1;
				links[i].direction = " 0 0 0 ";
				links[i].mark = 1;
			} else if (links[i].type == "sdn") {
				links[i].linknum = 1;
				links[i].direction = " 0 0 1 ";
				links[i].mark = 1;
			}
		} else {
			if (links[i].type == "main") {
				links[i].linknum = 0;
				links[i].direction = " 0 0 0 ";
				links[i].mark = -1;
			} else if (links[i].type == "backup") {
				links[i].linknum = 1;
				links[i].direction = " 0 0 1 ";
				links[i].mark = -1;
			} else if (links[i].type == "sdn") {
				links[i].linknum = 1;
				links[i].direction = " 0 0 0 ";
				links[i].mark = -1;
			}
		}

	}
	for (var i = 0; i < activelinks.length; i++) {
		if (activelinks[i].source < activelinks[i].target) {
			if (activelinks[i].type == "main") {
				activelinks[i].linknum = 0;
			} else if (activelinks[i].type == "backup") {
				activelinks[i].linknum = 1;
			} else if (activelinks[i].type == "sdn") {
				activelinks[i].linknum = -1;
			}
		} else {
			if (activelinks[i].type == "main") {
				activelinks[i].linknum = 0;
			} else if (activelinks[i].type == "backup") {
				activelinks[i].linknum = 1;
			} else if (activelinks[i].type == "sdn") {
				activelinks[i].linknum = -1;
			}
		}
	}
	var menu = [
			{
				title : '节点配置',
				action : function(elm, d, i) {
					console.log('Item #1 clicked!');
					console.log('The data for this circle is: ' + d);
				}
			},
			{
				title : '策略配置',
				action : function(elm, d, i) {
					console.log('You have clicked the second item!');
					console.log('The data for this circle is: ' + d.id);
					window.location.href = contextPath
							+ "/policy/InConfig.action?svo.nodeId=" + d.id;
				}
			}, {
				title : '链路配置',
				action : function(elm, d, i) {
					console.log('You have clicked the second item!');
					console.log('The data for this circle is: ' + d);
				}
			} ]

	var w = 960, h = 500;

	var force = d3.layout.force().nodes(d3.values(nodes)).links(links).size(
			[ w, h ]).linkDistance(450).charge(-100).on("tick", tick).start();

	var svg = d3.select("#display").append("svg:svg").attr("width", w).attr(
			"height", h);
	var path = svg.append("svg:g").selectAll("path").data(force.links())
			.enter().append("svg:path").style("stroke", "#ddd").attr("class",
					function(d) {
						return "link " + d.type;
					}).style("stroke-width", 14).on(
					"mouseover",
					function(d) {
						var htm = '';
						if (d.type == 'main') {
							htm = "主链路";
						} else if (d.type == 'backup') {
							htm = "备用链路";
						} else if (d.type == 'sdn') {
							htm = "SDN";
						}
						div.transition().duration(200).style("opacity", .9);
						div.html(
								"<div style='text-align:center;font-size:16px;font-weight:bold;'>"
										+ htm + "</div>"
										+ "<div style='text-align:center;'>"
										+ "右键点击节点选择配置" + "</div>").style(
								"left", (d3.event.pageX) + "px").style("top",
								(d3.event.pageY - 28) + "px");
					}).on("mouseout", function() {
				div.transition().duration(500).style("opacity", 0);
			});

	var activeforcelink = [];

	var num = 0;
	for (var i = 0; i < force.links().length; i++) {
		for (var j = 0; j < activelinks.length; j++) {
			if (activelinks[j].source == force.links()[i].source.id
					&& activelinks[j].target == force.links()[i].target.id
					&& activelinks[j].type == force.links()[i].type) {
				activeforcelink[num] = force.links()[i];
				num++;
			}
		}
	}

	var pathactive;
	pathactive = svg.append("svg:g").attr("id", "g_path").selectAll("path")
			.data(activeforcelink).enter().append("svg:path").attr("id",
					"pathactive").attr("class", function(d) {
				return "link " + d.type;
			}).style("stroke-width", 4);
	pathactive.call(transition);

	var div = d3.select("body").append("div").attr("class", "tooltip").style(
			"opacity", 0)

	var circle = svg
			.append("svg:g")
			.attr("id", "g_circle")
			.selectAll("image")
			.data(force.nodes())
			.enter()
			.append("image")
			.attr("xlink:href", contextPath + "/img/node.png")
			.attr("id", "node")
			.style("height", "100")
			.style("width", "100")
			.call(force.drag)
			.on('contextmenu', d3.contextMenu(menu))
			.on(
					"mouseover",
					function(d) {
						var htm = "";
						for (var i = 0; i < d.portVos.length; i++) {
							if (i % 3 == 0) {
								htm += "<div style='text-align:left;'>&nbsp;&nbsp;"
										+ d.portVos[i].bridgeDesc
										+ "("
										+ d.portVos[i].bridgeCode
										+ ")"
										+ "</div>";
							}
							htm += "<div style='text-align:center;font-weight:normal;'>"
									+ d.portVos[i].portCode
									+ "端口"
									+ "  Mac地址:"
									+ d.portVos[i].macAddress + "</div>";
						}
						div.transition().duration(200).style("opacity", .9);
						div
								.html(
										"<div style='text-align:center;font-size:16px;font-weight:bold;'>"
												+ d.location
												+ "节点  "
												+ "</div>"
												+ "<div style='text-align:center;'>"
												+ "IP地址："
												+ d.nodeIp
												+ "</div>"
												+ "<div style='text-align:left;font-weight:bold;'>"
												+ "端口信息："
												+ htm
												+ "</div>"
												+ "<div style='text-align:center;'>"
												+ "右键点击节点选择配置" + "</div>")
								.style("left", (d3.event.pageX) + "px").style(
										"top", (d3.event.pageY - 28) + "px");
					}).on("mouseout", function() {
				div.transition().duration(500).style("opacity", 0);
			});

	var text = svg.append("svg:g").selectAll("g").data(force.nodes()).enter()
			.append("svg:g");
	// A copy of the text with a thick white stroke for legibility.

	text.append("svg:text").attr("x", 8).attr("y", ".31em").attr("class",
			"shadow").text(function(d) {
		return d.location;
	});

	text.append("svg:text").attr("x", 8).attr("y", ".31em").text(function(d) {
		return d.location;
	});

	// Use elliptical arc path segments to doubly-encode directionality.
	function tick() {
		path.attr("d", function(d) {
			var dr = 600 * d.linknum; // linknum is defined above
			return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr
					+ d.direction + d.target.x + "," + d.target.y;
		});

		pathactive.attr("d", function(d) {
			var dr = (600 + d.mark * 50) * d.linknum; // linknum is defined
			// above
			return "M" + d.source.x + "," + d.source.y + "A" + dr + "," + dr
					+ d.direction + d.target.x + "," + d.target.y;
		});

		circle.attr("transform", function(d) {
			return "translate(" + (d.x - 50) + "," + (d.y - 50) + ")";
		});

		text.attr("transform", function(d) {
			return "translate(" + (d.x + 50) + "," + d.y + ")";
		});

	}

	function transition(path) {
		path.transition().duration(1500).attrTween(
				"stroke-dasharray",
				function(a) {
					var l = this.getTotalLength(), i = d3.interpolateString(
							"0," + l, l + "," + l);
					return function(t) {
						return i(t);
					};
				}).each("end", function() {
			d3.select(this).call(transition);
		});
	}

	setInterval(
			function() {
				$.ajax({
					type : "POST",
					url : contextPath + "/dashboard/AjaxSearch.action",
					data : data,
					async : false,
					success : function(data) {
						refreshlinks = data.link;
					}
				})
				activeforcelink = [];
				var num = 0;
				for (var i = 0; i < force.links().length; i++) {
					for (var j = 0; j < refreshlinks.length; j++) {
						if (refreshlinks[j].source == force.links()[i].source.id
								&& refreshlinks[j].target == force.links()[i].target.id
								&& refreshlinks[j].type == force.links()[i].type) {
							activeforcelink[num] = force.links()[i];
							num++;
						}
					}
				}

				svg.selectAll("#g_path").remove();
				svg.selectAll("#g_circle").remove();

				pathactive = svg.append("svg:g").attr("id", "g_path")
						.selectAll("#pathactive").data(activeforcelink).enter()
						.append("svg:path").attr("id", "pathactive").attr(
								"class", function(d) {
									return "link " + d.type;
								}).style("stroke-width", 4);
				pathactive.call(transition);

				pathactive.attr("d", function(d) {
					var dr = (600 + d.mark * 50) * d.linknum; // linknum is
					// defined above
					return "M" + d.source.x + "," + d.source.y + "A" + dr + ","
							+ dr + d.direction + d.target.x + "," + d.target.y;
				});

				circle = svg
						.append("svg:g")
						.attr("id", "g_circle")
						.selectAll("image")
						.data(force.nodes())
						.enter()
						.append("image")
						.attr("xlink:href", contextPath + "/img/node.png")
						.attr("id", "node")
						.style("height", "100")
						.style("width", "100")
						.call(force.drag)
						.on('contextmenu', d3.contextMenu(menu))
						.on(
								"mouseover",
								function(d) {
									var htm = "";
									for (var i = 0; i < d.portVos.length; i++) {
										if (i % 3 == 0) {
											htm += "<div style='text-align:left;'>&nbsp;&nbsp;"
													+ d.portVos[i].bridgeDesc
													+ "("
													+ d.portVos[i].bridgeCode
													+ ")" + "</div>";
										}
										htm += "<div style='text-align:center;font-weight:normal;'>"
												+ d.portVos[i].portCode
												+ "端口"
												+ "  Mac地址:"
												+ d.portVos[i].macAddress
												+ "</div>";
									}
									div.transition().duration(200).style(
											"opacity", .9);
									div
											.html(
													"<div style='text-align:center;font-size:16px;font-weight:bold;'>"
															+ d.location
															+ "节点  "
															+ "</div>"
															+ "<div style='text-align:center;'>"
															+ "IP地址："
															+ d.nodeIp
															+ "</div>"
															+ "<div style='text-align:left;font-weight:bold;'>"
															+ "端口信息："
															+ htm
															+ "</div>"
															+ "<div style='text-align:center;'>"
															+ "右键点击节点选择配置"
															+ "</div>").style(
													"left",
													(d3.event.pageX) + "px")
											.style(
													"top",
													(d3.event.pageY - 28)
															+ "px");
								}).on("mouseout", function() {
							div.transition().duration(500).style("opacity", 0);
						});
				circle.attr("transform", function(d) {
					return "translate(" + (d.x - 50) + "," + (d.y - 50) + ")";
				});
			}, 6000);

	var svgcontent = d3.select("#content").selectAll("div").append("div").html(
			"123").data(force.links()).enter().append("div");

	svgcontent
			.html(function(d) {
				console.info(d)
				return "<div style='font-size:16px;font-weight:bold;'>"
						+ d.source.location
						+ "去"
						+ d.target.location
						+ d.type
						+ "</div>";
			})
}