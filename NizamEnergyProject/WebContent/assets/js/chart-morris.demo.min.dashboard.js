function handleMorrisDonutChart() {

	var applist = new Array();
	$
			.ajax({

				url : 'SuperAdminDashboardController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getSchemePlansDonutChart'
				},
				success : function(json) {
					console.log(json);

					for (var i = 0; i < json.scheme.length; i++) {
						applist.push({
							label : json.scheme[i].scheme + ' Months',
							value : json.scheme[i].noOfInstalledCustomers
									+ ' Customers'
						});
					}
					Morris.Donut({
						element : "morris-donut-chart",
						data : applist,
						formatter : function(e) {
							return e
						},
						resize : true,
						colors : [ "#9CC4E4", "#3A89C9", "#F26C4F", "#532F15",
								"#373D2F", "#03120F", "#4A3733", "#7882A5",
								"#3D6E3F", "#001068", "#006845" ]
					});

					jQuery(document).ready(function() {
					});

				}

			})
};

var MorrisChart = function() {
	return {
		init : function() {
			handleMorrisDonutChart();
		}
	}
}()