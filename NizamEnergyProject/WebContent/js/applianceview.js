var appId = document.getElementById("appID").value;
var customerId = document.getElementById("customer_id").value;
var curr = document.getElementById("current").value;
var solar = document.getElementById("SolarCurrent").value;
var GSM = document.getElementById("gsmNumber");

function staticsDateWise() {
	var dates = document.getElementById("dateSet").value;
	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var dbGraphs = [];
	var dbGraphSolar = [];
	var flg = 0;
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates,
			flag : flg
		},
		success : function(data) {
			dbGraph = data[0].dateWise
			dbGraphSolar = (data[0].solarLoad)
			$('#powerproduced').text(data[0].power)
			$('#loadConsumed').text(data[0].loadConsumed)
			$('#hours').text(data[0].hours)
			$('#mins').text(data[0].mins)
			if (data[0].today == 1) {
				$('#powertag').text("Previous Day Power Produced")
				$('#loadtag').text("Previous Day Laod Consumed")
				$('#chargingtag').text("Previous Day Charging Time:")
			} else if (data[0].today == 0) {
				$('#powertag').text("Power Produced")
				$('#loadtag').text("Laod Consumed")
				$('#chargingtag').text("Charging Time:")
			}
			AmCharts.makeChart("chartdiv2", {
				"type" : "serial",
				"categoryField" : "datetime",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Load",
					"valueField" : "solarWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Battery",
					"valueField" : "batteryWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				}, {
					"id" : "AmGraph-3",
					"title" : "Solar",
					"valueField" : "lodWatt",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#f09609",
					"bullet" : "circle",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Power (watts)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				"dataProvider" : dbGraph

			});// 

		}
	});// ///finished ajax

}

function liveStatics() {
	var now = $.datepicker.formatDate('yy-mm-dd', new Date())
	$('#dates').attr("max", now);
	var dbGraphs = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dates : now
		},
		success : function(data) {
			console.log(data)
			console.log(data[0].dateWise)
			dbGraph = data[0].dateWise
			AmCharts.makeChart("chartdiv2", {
				"type" : "serial",
				"categoryField" : "datetime",
				"dataDateFormat" : "YYYY-MM-DD HH:MM",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "square",
					"id" : "AmGraph-1",
					"title" : "Load watt",
					"valueField" : "solarWatt",
					"lineThickness" : 2
				}, {
					"bullet" : "square",
					"id" : "AmGraph-2",
					"title" : "Battery Watt",
					"valueField" : "batteryWatt",
					"lineThickness" : 2
				}, {
					"id" : "AmGraph-3",
					"title" : "Solar Watt",
					"valueField" : "lodWatt",
					"bullet" : "square",
					"lineThickness" : 2
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Power (watts)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				"titles" : [ {
					"id" : "Title-1",
					"size" : 20,
					"text" : "Power Usage Graph"
				} ],
				// "dataProvider": AmCharts.loadJSON('data.jsp'),

				"dataProvider" : dbGraph

			});// /// amChart finished
		}
	});// ///finished ajax
}
function loadSoalrAmpere() {

	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var dbGraphLoad = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			dbGraphLoad = data[0].solarLoad
			console.log(data[0])
			dbGraphSolar = (data[0].solarLoad)
			AmCharts.makeChart("chartdiv3", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Solar",
					"valueField" : "solar",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Load",
					"valueField" : "load",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Current (ampere)",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				"dataProvider" : dbGraphLoad

			});// /// amChart finished

		}
	});// ///finished ajax

}

// Alarm history table
$(function() {

	$('#alarmsTable')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"order" : [ [ 0, "asc" ] ],
						"ajax" : {
							"url" : "ApplianceAlarmsControl",
							"type" : "post",
							"data" : function(d) {
								d.action = "getAlarmsTable", d.appId = appId
							},
							"dataSrc" : function(json) {
								console.log(json);
								for ( var i = 0; i < json.data.length; i++) {
									json.data[i].alert = ""
									json.data[i].status = ""
									json.data[i].resTime = ""
									if (json.data[i].solar_power == "true") {
										json.data[i].alert += " Solar Power"
									} else if (json.data[i].battery == "true") {
										json.data[i].alert += " Battery "
									} else if (json.data[i].temprature == "true") {
										json.data[i].alert += " Temprature "
									} else if (json.data[i].current_load == "true") {
										json.data[i].alert += " Current Load "
									} else if (json.data[i].lid_connectivity == "true") {
										json.data[i].alert += "Lid Connectivity"
									} else if (json.data[i].gsmSignal == "1"
											|| json.data[i].gsmSignal == "true") {
										json.data[i].alert += "Signal Strength"
									} else if (json.data[i].gsm_suicide == "true") {
										json.data[i].alert += "GSM Suicide"
									} else if (json.data[i].common_fault == "true") {
										json.data[i].alert += "Common Fault"
									} else if (json.data[i].battery_overcharge == "true") {
										json.data[i].alert += "Battery Overcharge"
									}
									if (json.data[i].ticketStatus == "0") {
										json.data[i].status = "Pending"
									} else if (json.data[i].ticketStatus == "1") {
										json.data[i].status = "Resolved"
									}
									if (json.data[i].resolvedTime == "null"
											|| json.data[i].resolvedTime == null
											|| json.data[i].resolvedTime == 0) {
										json.data[i].resTime = "N/A"
									} else {
										json.data[i].resTime = json.data[i].resolvedTime
									}
								}
								return json.data;
							}

						},

						"columns" : [ {
							"data" : "alarmTime"
						}, {
							"data" : "alert"
						}, {
							"data" : "status"
						},
						// {
						// "data" : "ticketNo"
						// },
						{
							"data" : "resTime"
						}
						// , {
						// "data" : "userName"
						// }
						],

					});
});// function complete

$(document)
		.ready(
				function() {

					$('#editConsumer')
							.on(
									'click',
									function() {
										$('input:text').removeAttr("disabled");
										$('input:text').removeAttr("style");
										$("#imeinumber")
												.attr("onChange",
														"existingData('imeinumber','imeiresult',this);");
										$('#editConsumer').hide();
										$('#cancelEditConsumer').show();
										$('#saveConsumer').show();
									});

					$('#cancelEditConsumer').on(
							'click',
							function() {

								$('input:text').attr("disabled", "disabled");
								$('input:text').attr("style",
										"border:none;background-color:white")
								$('#cancelEditConsumer').hide();
								$('#editConsumer').show();
								$('#saveConsumer').hide();
							});

					$('#saveConsumer')
							.on(
									'click',
									function() {
										$('input:text').attr("disabled",
												"disabled");
										$('input:text')
												.attr("style",
														"border:none;background-color:white")
										$('#cancelEditConsumer').hide();
										$('#editConsumer').show();
										$('#saveConsumer').hide();
										$
												.ajax({
													url : "UpdateAppliance",
													method : "post",
													type : "json",
													data : {

														action : "updateCredential",
														appImei : $(
																"#imeinumber")
																.val(),
														appGsm : $("#gsm")
																.val(),
														consumer : $(
																"#consumer")
																.val(),
														appId : appId,

													},
													success : function(data) {
														console.log("data.data"
																+ data.data)
														if (data.data > 1) {
															console.log(data)
															$(
																	'#cloader span.fa-spin')
																	.fadeOut(
																			1000,
																			'linear',
																			function() {
																				$(
																						'#cloader')
																						.append(
																								'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
																			});
															window.location = "ViewServlet?click=viewAppliance&id="
																	+ data.data;
														}
														if (data.data < 1) {
															$(
																	'##cloader span.fa-spin')
																	.fadeOut(
																			10000,
																			'linear',
																			function() {
																				$(
																						'##cloader')
																						.append(
																								'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
																			});
														}

													}

												});

									});

					$('#editApp')
							.on(
									'click',
									function() {

										$('input:text').removeAttr("disabled");
										$('input:text').removeAttr("style");
										$("#imeinumber")
												.attr("onChange",
														"existingData('imeinumber','imeiresult',this);");

										$('#editApp').hide();
										$('#cancelEdit').show();
										$('#saveApp').show();
									});
					$('#cancelEdit').on(
							'click',
							function() {

								$('input:text').attr("disabled", "disabled");
								$('input:text').attr("style",
										"border:none;background-color:white")
								$('#cancelEdit').hide();
								$('#editApp').show();
								$('#saveApp').hide();
							});

					$('#saveApp')
							.on(
									'click',
									function() {

										$('input:text').attr("disabled",
												"disabled");
										$('input:text')
												.attr("style",
														"border:none;background-color:white")
										$('#cancelEdit').hide();
										$('#editApp').show();
										$('#saveApp').hide();
										$
												.ajax({
													url : "UpdateAppliance",
													method : "post",
													type : "json",
													beforeSend : function() {
														$('#loader')
																.append(
																		'<span class="fa fa-circle-o-notch fa-spin"></span>')
													},
													data : {
														action : "updateAppliance",
														appImei : $(
																"#imeinumber")
																.val(),
														appGsm : $("#gsm")
																.val(),
														appId : appId,
														customerId : customerId

													},
													success : function(data) {
														console.log("data.data"
																+ data.data)
														console
																.log("customerId"
																		+ customerId)
														console.log()
														if (data.data > 1) {
															console.log(data)
															$(
																	'#loader span.fa-spin')
																	.fadeOut(
																			1000,
																			'linear',
																			function() {
																				$(
																						'#loader')
																						.append(
																								'<span class="fa fa-check" style="color:green;font-size:18px" ><b>Success</b></span>')
																			});
															window.location = "ViewServlet?click=viewAppliance&id="
																	+ data.data;
														}
														if (data.data < 1) {
															$(
																	'##loader span.fa-spin')
																	.fadeOut(
																			10000,
																			'linear',
																			function() {
																				$(
																						'##loader')
																						.append(
																								'<span class="fa fa-times" style="color:red;font-size:18px" ><b>Server Respond with an error</b></span>')
																			});
														}

													}

												});

									});
				});
function loadBatteryAndSolarVoltage() {

	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var batteryGraphLoadVoltage = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			batteryGraphLoadVoltage = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv4", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Battery voltage",
					"valueField" : "batteryVoltage",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Solar voltage",
					"valueField" : "solarVoltage",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Voltage",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : batteryGraphLoadVoltage

			});// /// amChart finished

		}
	});// ///finished ajax

}

function loadBatteryAndSolarCurrent() {
	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var batteryGraphLoadCurrent = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			batteryGraphLoadCurrent = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv5", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Battery ampere",
					"valueField" : "batteryAmpere",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				}, {
					"bullet" : "circle",
					"id" : "AmGraph-2",
					"title" : "Solar ampere",
					"valueField" : "solarAmpere",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#a200ff",
					"lineThickness" : 2,
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true
				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Ampere",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : batteryGraphLoadCurrent

			});// /// amChart finished

		}
	});// ///finished ajax
}

function loadApplianceTemperature() {
	var dates = document.getElementById("dateSet").value;

	if (!dates) {
		var d = new Date();
		var date = ("0" + d.getDate()).slice(-2)
		var month = ("0" + (d.getMonth() + 1)).slice(-2)
		var year = d.getFullYear();
		var dateget = (year + "-" + month + "-" + date)
		dates = dateget;
	}

	var applianceTemperature = [];
	// var dbGraphSolar = [];
	$.ajax({
		url : 'ApplianceGraphController',
		dataType : 'json',
		method : 'Post',
		data : {
			applianceId : appId,
			dateSet : dates
		},
		success : function(data) {
			// console.log(solarLoad)
			// console.log(data[0].dateWise)
			applianceTemperature = data[0].batteryPerformance
			console.log("============= solar data =============")
			console.log(data[0])
			dbGraphSolar = (data[0].batteryPerformance)
			console.log("dbGraphSolar ===== " + dbGraphSolar)
			AmCharts.makeChart("chartdiv6", {
				"type" : "serial",
				"categoryField" : "datetime2",
				"dataDateFormat" : "YYYY-MM-DD HH:NN",
				"categoryAxis" : {
					"minPeriod" : "mm",
					"parseDates" : true,
					"gridCount" : 3,
					"gridPosition" : "start"
				},
				"chartCursor" : {
					"enabled" : true,
					"categoryBalloonDateFormat" : "JJ:NN:SS"
				},
				"chartScrollbar" : {
					"enabled" : true
				},
				"trendLines" : [],
				"graphs" : [ {
					"bullet" : "round",
					"id" : "AmGraph-1",
					"title" : "Temperature",
					"valueField" : "applianceTemperature",
					"bulletColor" : "#FFFFFF",
					"lineColor" : "#ee4035",
					"lineThickness" : 2,
					// "type" : "smoothedLine",
					"bulletBorderAlpha" : 1,
					"bulletBorderThickness" : 2,
					"useLineColorForBulletBorder" : true

				} ],
				"guides" : [],
				"valueAxes" : [ {
					"id" : "ValueAxis-1",
					"title" : "Temperature",
					"gridCount" : 3
				} ],
				"allLabels" : [],
				"balloon" : {},
				"legend" : {
					"enabled" : true,
					"useGraphSettings" : true
				},
				/*
				 * "titles": [ { "id": "Title-1", "size": 20, "text": "Power
				 * Usage Graph" } ],
				 */
				// "dataProvider": AmCharts.loadJSON('data.jsp'),
				"dataProvider" : applianceTemperature

			});// /// amChart finished

		}
	});// ///finished ajax
}

function getDistrictSummary() {
	$.ajax({
		url : 'ApplianceController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "districtSummary",
			appId : appId
		},
		success : function(data) {
			$('#district_summary').empty();
			var tds = '<tr>' + '<td>' + (data.name)
					+ '</td>' + '<td>'
					+ (isNaN(data.produced) ? 'N/A' : data.produced) + ' WH'
					+ '</td>' + '<td>'
					+ (isNaN(data.consumed) ? 'N/A' : data.consumed) + ' WH'
					+ '</td>' + '<td>'
					+ (isNaN(data.StdLoad) ? 'N/A' : data.StdLoad) + 'WH'
					+ '</td>' + '<td>'
					+ (isNaN(data.StdProduced) ? 'N/A' : data.StdProduced)
					+ 'WH' + '</td>' + '</tr>';
			$("#district_summary").append(tds);
		}
	})
}

function getApplianceSummary() {
	$
			.ajax({
				url : 'ApplianceController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : "applianceData",
					appId : appId
				},
				success : function(data) {
					console.log("getApplianceSummarasdsay()" + data.isSignal);
					console.log("getApplianceSummary()" + data);

					$("#panelTittle").append(
							'Appliance Alert History /' + data.applianceName);
					if (data.isSignal == "0") {
						$("#pageHeader").append(
								'Appliance <b>' + data.applianceName
										+ " No Signal" + '</b>');

					} else {
						$("#pageHeader").append(
								'Appliance <b>' + data.applianceName + '</b>');
					}
					var status = data.status;
					console.log("getStatus" + status);
					var state = data.state;
					console.log("getState" + state);

					$("#appName").text(data.applianceName);
					if (data.IMEInumber == "" || data.IMEInumber == null
							|| data.IMEInumber == "null") {
						$('#imeinumber').val("N/A");
						$("#spanimeinumber").text("N/A");
					} else {
						$('#imeinumber').val(data.IMEInumber);
						$("#spanimeinumber").text(data.IMEInumber);
					}
					console.log("imeinumber" + data.IMEInumber + " "
							+ data.gsmNumber);
					var gsm = data.gsmNumber;
					if (gsm == "" || gsm == null || gsm == "null") {
						$('#gsm').val("N/A");
						$("#spangsm").text("N/A");
					} else {
						// gsm = gsm.substring(2);
						// gsm = [ gsm.slice(0, 3), '-', gsm.slice(3)
						// ].join('');
						$('#gsm').val(gsm);
						$("#spangsm").text(gsm);
					}

					if (data.consumer == "" || data.consumer == null
							|| data.consumer == "null") {
						$('#consumer').val("N/A");
						$("#spanconsumer").text("N/A");
					} else {

						$('#consumer').val(consumer);
						$("#spanconsumer").text(consumer);
					}

					if (data.status == 0 || data.status == 1
							|| data.status == 4) {
						$('#editConsumer').addClass('disabled');
						$('#editConsumer').attr("data-toggle", "modal");
					} else {
						$('#editConsumer').removeAttr('data-toggle');
					}

					if (data.status == 0 || data.status == 1
							|| data.status == 4) {
						$('#editApp').addClass('disabled');
						$('#editApp').attr("data-toggle", "modal");

					} else {
						// $('#editConsumer').removeClass('disabled');
						$('#editApp').removeAttr('data-toggle');
					}

					$('#consumer').val(data.consumer);
					$("#spanconsumer").text(data.consumer);

					if (state == "true") {

						$("#charging")
								.append(
										'<span class="label" style="background-color: #2ecc71">Active</span>');
					} else {
						$("#charging")
								.append(
										'<span class="label label-danger">Inactive</span>');
					}
					var price = data.price;
					$("#price").text(price.toLocaleString('en-US')); // "en-US"

					$("#customerName").append(
							'<a href="ViewServlet?click=view&id='
									+ data.customerId + '&cnic='
									+ data.customercnic + '&appId='
									+ data.applianceIdd + '">' + data.customer
									+ '</a>');
					var handover = data.handover;
					if (handover == "" || handover == null
							|| handover == "null") {
						$("#handover").text("N/A");
					} else {
						$("#handover").text(handover);
					}
					$("#consumer").text(data.consumer);
					$("#do").append(
							'<a href="DistrictOfficer?do_id=' + data.doid
									+ '">' + data.userName + '</a>');

					$("#fo").append(
							'<a href="fieldOfficerProfile.jsp?fo_id='
									+ data.foid + '">' + data.foName + '</a>');

					$("#nd").append(
							'<a href="NizamDostViewController?click=ok&salesman_id='
									+ data.salesmanId + '&salesmanGsm='
									+ data.salesmanPhone + '">' + data.salesman
									+ '</a>');

					if (status == 0) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();
						$("#returnDevice").hide();
					} else if (status == 1) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: #9b59b6; color: white; font-weight: bold;">Ready To Assign</span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();
						$("#returnDevice").hide();
					} else if (status == 2) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: #1abc9c; color: white; font-weight: bold;">RDP</span>')
						$("#returnedDate").hide();
						$("#rdpButton").show();
						$("#returnDevice").hide();

						$("#installButton")
								.append(
										' <button type="button" class="btn btn-primary pull-left" '
												+ 'data-toggle="modal" data-target="#modal-install"'
												+ 'id="applianceid"'
												+ 'onclick="setMoodalValue('
												+ data.applianceIdd + ','
												+ data.customer
												+ ')">Insatll Device</button>');

						$("#noSignalButton")
								.append(
										'<button type="button" class="btn btn-primary pull-left"'
												+ 'data-toggle="modal" data-target="#modal-nosignal"'
												+ 'id="applianceid"'
												+ 'onclick="setMoodalNoSignalValue('
												+ data.applianceIdd
												+ ','
												+ data.customer
												+ ')">Installed NoSignal</button>');

					} else if (status == 3) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: #7f8c8d; color: white; font-weight: bold;">Owned</span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();
						$("#returnDevice").hide();

					} else if (status == 4) {
						$("#transferStatus")
								.append(
										'<span class="label"style="background-color: #d35400; color: black; font-weight: bold;">Returned</span>');
						$("#rdpButton").hide();
						$("#returnDevice").hide();
						$("#returnedDate").show();
						var returned = data.returned;
						if (returned == "" || returned == null
								|| returned == "null") {

							$("#returned").text("N/A");
						} else {
							$("#returned").text(returned);
						}

					} else if (status == 5) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: #1abc9c; color: white; font-weight: bold;">Awaiting Downpayment </span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();
						$("#returnDevice").hide();
					} else if (status == 6) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: green; color: white; font-weight: bold;">Installed </span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();

						$("#returnDevice").show();
						$("#returnDevice")
								.append(
										'<td> <button type="button" class="btn btn-primary pull-left"'
												+ 'data-toggle="modal" data-target="#modal-return"'
												+ 'id="applianceid"'
												+ 'onclick="setMoodValue('
												+ data.applianceIdd
												+ ','
												+ data.customer
												+ ')">Return Device</button> </td>'
												+ '<th><a href="SuperAdminLoanBookController?appliace_key='
												+ data.applianceIdd
												+ '" style="text-decoration: none"> Loan Book View</a></th> ');

					} else if (status == 7) {
						$("#transferStatus")
								.append(
										'<span class="label" style="background-color: green; color: white; font-weight: bold;">Installed and NoSignal</span>');
						$("#returnedDate").hide();
						$("#rdpButton").hide();
						$("#returnDevice").show();
						$("#returnDevice")
								.append(
										'<td> <button type="button" class="btn btn-primary pull-left"'
												+ 'data-toggle="modal" data-target="#modal-return"'
												+ 'id="applianceid"'
												+ 'onclick="setMoodValue('
												+ data.applianceIdd
												+ ','
												+ data.customer
												+ ')">Return Device</button> </td>'
												+ '<th><a href="SuperAdminLoanBookController?appliace_key='
												+ data.applianceIdd
												+ '" style="text-decoration: none"> Loan Book View</a></th> ');

					}

					if (data.applianceName == "60 W"
							|| data.applianceName == "7 W") {

						$("#healthStatus")
								.append(
										'<span class="label" style="background-color: black; color: white; font-weight: bold;">DUMB</span>');

					} else {

						if (status == 6) {
							if (data.healthstatus == 3) {
								$("#healthStatus")
										.append(
												'<span class="label" style="background-color: #2980b9; color: white; font-weight: bold;">ALIVE</span>');

							} else if (data.healthstatus == 2) {
								if (data.deadsince == 0) {
									$("#healthStatus")
											.append(
													'<span class="label" style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD Since Today</span>');

								} else if (data.deadsince == 1) {

									$("#healthStatus")
											.append(
													' <span class="label" style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD Since Yesterday</span>');

								} else {
									$("#healthStatus")
											.append(
													'<span class="label" style="background-color: #bdc3c7; color: white; font-weight: bold;">DEADSince '
															+ data.deadsince
															+ ' days</span>');

								}

							}
						} else if (status == 7) {

							$("#healthStatus")
									.append(
											'<span class="label" style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD</span>');

						} else {
							$("#healthStatus")
									.append(
											'<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>')

						}

					}

				}
			})
}

$('#editConsumer').click(function() {

	$('#spanconsumer').css("display", "none");
	$('#consumer').prop("type", "text");
	$('#consumer').css("display", "block");

	$('#spanimeinumber').css("display", "none");
	$('#imeinumber').prop("type", "text");
	$('#imeinumber').css("display", "block");

	$('#spangsm').css("display", "none");
	$('#gsm').prop("type", "text");
	$('#gsm').css("display", "block");

	$("#consumer").removeAttr("disabled")
	$("#consumer").removeAttr("style")

	$("#imeinumber").removeAttr("disabled")
	$("#imeinumber").removeAttr("style")

	$("#gsm").removeAttr("disabled")
	$("#gsm").removeAttr("style")

});

$('#editApp').click(function() {

	$('#spanimeinumber').css("display", "none");
	$('#imeinumber').prop("type", "text");
	$('#imeinumber').css("display", "block");

	$('#spangsm').css("display", "none");
	$('#gsm').prop("type", "text");
	$('#gsm').css("display", "block");

	// $('#editAppliance').hide();
	// $('#saveconsumer').show();
	// $('#cancelconsumer').show();

	$("#imeinumber").removeAttr("disabled")
	$("#imeinumber").removeAttr("style")

	$("#gsm").removeAttr("disabled")
	$("#gsm").removeAttr("style")

});

function getApplianceStatus() {
	$.ajax({
		url : 'ApplianceController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "applianceSummary",
			appId : appId
		},
		success : function(data) {

			$("#lastupdated").text(data.date);
			$("#batteryvoltage").text(
					isNaN(data.batteryVolt) ? 'N/A' : data.batteryVolt + " V");
			$("#batteryampere").text(
					isNaN(data.batteryAmp) ? 'N/A' : data.batteryAmp + " A");
			$("#solarvoltage").text(
					isNaN(data.solarVolt) ? 'N/A' : data.solarVolt + " V");
			$("#solarampere").text(
					isNaN(data.solarAmp) ? 'N/A' : data.solarAmp + " A");
			$("#loadvoltage").text(
					isNaN(data.loadVolt) ? 'N/A' : data.loadVolt + "V");
			$("#loadcurrent").text(
					isNaN(data.loadAmp) ? 'N/A' : data.loadAmp + "A");
			$("#temprature").text(
					isNaN(data.temprature) ? 'N/A' : data.temprature + " �C");
			$("#signalsrength").text(data.gsmSignal);
		}
	})
}

function initialize() {
	$
			.ajax({
				url : 'MapController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : "mapData",
					appId : appId
				},
				success : function(data) {
					var status = data.type;
					var a = data.lati;
					var b = data.longi;
					var icons;
					if (status == "1") {
						icons = "http://maps.google.com/mapfiles/ms/icons/green-dot.png";
					}
					if (status == "0") {
						icons = "http://maps.google.com/mapfiles/ms/icons/red-dot.png";
					}
					var karachi = new google.maps.LatLng(a, b);
					var karach = new google.maps.LatLng(a, b);

					var mapProp = {
						center : karachi,
						zoom : 18,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					};

					var map = new google.maps.Map(document
							.getElementById("googleMap"), mapProp);
					var marker = new google.maps.Marker({
						position : karach,
						icon : icons

					});
					marker.setMap(map);
				}
			});
}
google.maps.event.addDomListener(window, 'load', initialize);
