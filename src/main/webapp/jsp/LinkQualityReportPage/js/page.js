$(function() {
	Highcharts.setOptions({
		global : {
			useUTC : false
		}
	});

	// Create the chart
	Highcharts.stockChart('container', {
		chart : {
			type : "areaspline",
			events : {
				load : function() {
					var series = this.series;
					// set up the updating of the chart each second
						setInterval(function() {
							var x = (new Date()).getTime(), // current time
							y = Math.round(Math.random() * 100);
							for (var i = 0; i < series.length; i++) {
								var seriesline = series[i];
								seriesline.addPoint([ x, y ], true, true);
							}
						}, 5000);
				}
			}
		},
		legend: {                                                                    
            enabled: true ,
            align: 'right',  
            x: -70,  
            verticalAlign: 'top',  
            y: 0,  
            floating: true,  
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',  
            borderColor: '#CCC',  
            borderWidth: 0,  
            shadow: false  
        },  	
		rangeSelector : {
			buttons : [ {
				count : 1,
				type : 'minute',
				text : '1分钟'
			}, {
				count : 5,
				type : 'minute',
				text : '5分钟'
			}, {
				type : 'all',
				text : '全部'
			} ],
			inputEnabled : false,
			selected : 0
		},

		exporting : {
			enabled : true	
		},

		credits : {
			enabled : false
		},

		series : [
				{
					name : '丢包',
					data : (function() {
						// generate an array of random data
						var data = [], time = (new Date()).getTime(), i;

						for (i = -999; i <= 0; i += 1) {
							data.push([ time + i * 5000,
									Math.round(Math.random() * 100) ]);
						}
						return data;
					}())
				},
				{
					name : '抖动',
					data : (function() {
						// generate an array of random data
						var data = [], time = (new Date()).getTime(), i;

						for (i = -999; i <= 0; i += 1) {
							data.push([ time + i * 5000,
									Math.round(Math.random() * 100) ]);
						}
						return data;
					}())
				},
				{
					name : '时延',
					data : (function() {
						// generate an array of random data
						var data = [], time = (new Date()).getTime(), i;

						for (i = -999; i <= 0; i += 1) {
							data.push([ time + i * 5000,
									Math.round(Math.random() * 100) ]);
						}
						return data;
					}())
				} ]

	});
});