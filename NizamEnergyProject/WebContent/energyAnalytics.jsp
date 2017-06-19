<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.bootstrap.min.css"
	rel="stylesheet">

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />


<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<!-- ==================END USER DEFINED STYLE===================== -->

<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->

<!-- ================== END PAGE LEVEL STYLE ================== -->



<style>
span.bigcheck-target {
	font-family: FontAwesome; /* use an icon font for the checkbox */
}

input[type='checkbox'].bigcheck {
	position: relative;
	left: -999em; /* hide the real checkbox */
}

input[type='checkbox'].bigcheck+span.bigcheck-target:after {
	content: "\f096"; /* In fontawesome, is an open square (fa-square-o) */
}

input[type='checkbox'].bigcheck:checked+span.bigcheck-target:after {
	content: "\f046"; /* fontawesome checked box (fa-check-square-o) */
}
</style>

</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
	%>

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div data-scrollbar="true" data-height="100%">
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="has-sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <i
							class="icon-note"></i> <span>New Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub "><a href="AdvanceBooking"> <i
							class="icon-support"></i> <span>Advance Booking</span><span
							class="badge pull-right" id="unseen_booking_count"></span>
					</a></li>
					<li class="has-sub"><a href="Customer"> <i
							class="ion-ios-people"></i> <span>Customers</span>
					</a></li>
					<li class="has-sub"><a href="Appliances"> <i
							class="ion-cube"></i> <span>Pipeline</span>
					</a></li>
					<li class="has-sub "><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DistrictOfficerr">District Officer</a></li>
							<li><a href="FieldOfficer">Field Officer</a></li>
							<li><a href="NizamDost">Nizam Dost</a></li>
						</ul></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Finance</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="Finance">Payments</a></li>
							<li><a href="Loan">Loan books</a></li>
							<li><a href="CommissionPayout">Commissions</a></li>
						</ul></li>
					<li class="has-sub"><a href="DeviceAlarms"> <i
							class="icon-support"></i> <span>Alarms</span>
					</a></li>

					<li class="has-sub active"><a href="EnergyAnalytics"> <i
							class="ion-podium"></i> <span>Energy Analytics</span>
					</a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="LateAndDefaulterReport">Late/Defaulter
									Report</a></li>
							<li><a href="FutureLoanBooks">Future loan book</a></li>
							<li><a href="DataAndReports">Reports</a></li>
						</ul></li>
					<li><a href="javascript:;" class="sidebar-minify-btn"
						data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
					<!-- end sidebar minify button -->
		</ul>
				<!-- end sidebar nav -->
			</div>
			<!-- end sidebar scrollbar -->
		</div>
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">


			<!-- end page-header -->

			<!-- begin row -->

			<div class="row">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->




					<div id="customerPanel" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Energy Analysis</h4>
						</div>
						<div class="panel-body">
							<h5>*All data presented in this section are from the ALIVE
								B1's ONLY.</h5>

							<div class="table-responsive" style="font-size: 13px;">








								<div class="row" style="padding-top: 2%; width: 100%;">
									<ul id="ioniconsTab" class="nav nav-tabs">
										<li class="active"><a href="#topProductionTab"
											data-toggle="tab"><div class="text-center"></div> <span
												class="hidden-xs m-l-3">TOP 20 Production<span
													class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#leastProductionTab" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Least
													20 Production <span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#topConsumptionTab" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Top
													20 Consumption<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#leastConsumptionTab" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Least
													20 Consumption <span class="badge badge-inverse m-l-3"></span>

											</span></a></li>
										<li><a href="#totalAnalysisTab" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Total
													Statistics<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
									</ul>

									<div id="ioniconsTabContenta" class="tab-content">

										<div class="tab-pane fade in active" id="topProductionTab">
											<div class="table-responsive" style="font-size: 13px;">
												<table class="table table-striped">
													<thead>
														<tr>

															<th>Customer Name</th>
															<th>District</th>
															<th>Appliance Name</th>
															<th>Power Produced</th>

															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody id="topProduction">
													</tbody>
												</table>

											</div>
										</div>
										<div class="tab-pane fade " id="leastProductionTab">
											<div class="table-responsive" style="font-size: 13px;">

												<table class="table table-striped">
													<thead>
														<tr>

															<th>Customer Name</th>
															<th>District</th>
															<th>Appliance Name</th>
															<th>Power Produced</th>

															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody id="leastProduction">
													</tbody>
												</table>

											</div>
										</div>
										<div class="tab-pane fade" id="topConsumptionTab">
											<div class="table-responsive" style="font-size: 13px;">

												<table class="table table-striped">
													<thead>
														<tr>

															<th>Customer Name</th>
															<th>District</th>
															<th>Appliance Name</th>
															<th>Load Consumed</th>

															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody id="topConsumption">
													</tbody>
												</table>

											</div>
										</div>
										<div class="tab-pane fade" id="leastConsumptionTab">
											<div class="table-responsive" style="font-size: 13px;">
												<table class="table table-striped">
													<thead>
														<tr>
															<th>Customer Name</th>
															<th>District</th>
															<th>Appliance Name</th>
															<th>Load Consumed</th>
															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody id="leastConsumption">
													</tbody>
												</table>
											</div>
										</div>

										<div class="tab-pane fade" id="totalAnalysisTab">
											<div class="table-responsive" style="font-size: 13px;">

												<table class="table table-striped">

													<thead>
														<tr>
															<th>District</th>
															<th>Devices</th>
															<th>Total Produced</th>
															<th>Total Consumed</th>

														</tr>
													</thead>
													<tbody id="totalEnergy">

													</tbody>
													<tr>
														<th>Total</th>
														<td id="totaldevices"></td>
														<td id="totalProduced"></td>
														<td id="totalConsumed"></td>

													</tr>
												</table>

											</div>
										</div>


									</div>
								</div>



								<%
									} //session else closed
								%>





							</div>
							<!-- end panel -->
						</div>
						<!-- end col-10 -->


					</div>
					<!-- end row -->
				</div>
				<!-- end #content -->

				<a href="javascript:;"
					class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
					data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
				<!-- end scroll to top btn -->
			</div>


			<!-- footer start -->

			<!-- footer end -->

		</div>
		<!-- end page container -->

		<!-- ================== BEGIN BASE JS ================== -->
		<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
		<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
		<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<script type="text/javascript" src="assets/js/myscript.js"></script>
		<!-- ================== END BASE JS ================== -->


		<!-- ================== BEGIN PAGE LEVEL JS ================== -->



		<script src="assets/plugins/chart-js/chart.js"></script>
		<!--  <script src="assets/js/apps.min.js"></script> -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
		<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
		<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
		<script src="assets/js/table-manage-default.demo.min.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.bootstrap.min.js"></script>
		<script src="assets/js/table-manage-colvis.demo.min.js"></script>
		<script src="assets/js/chart-js.demo.min.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->
		<script src="assets/plugins/icheck/icheck.min.js"></script>



		<!-- Begin Initialize Customer Table -->
		<script type="text/javascript">
			function getTopProductions() {
				console.log("hhhh")
				$.ajax({
					url : 'EnergyAnalyticsController',
					method : 'POST',
					dataType : 'json',
					data : {
						action : "topProduction"
					},
					success : function(data) {
						console.log(data)
						$('#topProduction').empty();
						if (data.length > 0) {
							$.each(data, function(e) {
								var tds = '<tr>' + '<td>'
										+ data[e].customerName + '</td>'
										+ '<td>' + data[e].district_name
										+ '</td>' + '<td>' + data[e].name
										+ '</td>' + '<td>' + data[e].power
										+ ' WH </td>' + '</tr>';
								$("#topProduction").append(tds);
							})

						}
					}
				})
			}

			function getLeastProductions() {
				console.log("hhhh")
				$.ajax({
					url : 'EnergyAnalyticsController',
					method : 'POST',
					dataType : 'json',
					data : {
						action : "leastProduction"
					},
					success : function(data) {
						console.log(data)
						$('#leastProduction').empty();
						if (data.length > 0) {
							$.each(data, function(e) {
								var tds = '<tr>' + '<td>'
										+ data[e].customerName + '</td>'
										+ '<td>' + data[e].district_name
										+ '</td>' + '<td>' + data[e].name
										+ '</td>' + '<td>' + data[e].power
										+ ' WH </td>' + '</tr>';
								$("#leastProduction").append(tds);
							})

						}
					}
				})
			}

			function getTopConsumptions() {
				console.log("hhhh")
				$.ajax({
					url : 'EnergyAnalyticsController',
					method : 'POST',
					dataType : 'json',
					data : {
						action : "topConsumption"
					},
					success : function(data) {
						console.log(data)
						$('#topConsumption').empty();
						if (data.length > 0) {
							$.each(data, function(e) {
								var tds = '<tr>' + '<td>'
										+ data[e].customerName + '</td>'
										+ '<td>' + data[e].district_name
										+ '</td>' + '<td>' + data[e].name
										+ '</td>' + '<td>' + data[e].load
										+ ' WH </td>' + '</tr>';
								$("#topConsumption").append(tds);
							})

						}
					}
				})
			}

			function getLeastConsumption() {
				console.log("hhhh")
				$.ajax({
					url : 'EnergyAnalyticsController',
					method : 'POST',
					dataType : 'json',
					data : {
						action : "leastConsumption"
					},
					success : function(data) {
						console.log(data)
						$('#leastConsumption').empty();
						if (data.length > 0) {
							$.each(data, function(e) {
								var tds = '<tr>' + '<td>'
										+ data[e].customerName + '</td>'
										+ '<td>' + data[e].district_name
										+ ' </td>' + '<td>' + data[e].name
										+ '</td>' + '<td>' + data[e].load
										+ ' WH </td>' + '</tr>';
								$("#leastConsumption").append(tds);
							})

						}
					}
				})
			}

			function getTotalEnergy() {
				console.log("hhhh")
				$.ajax({
					url : 'EnergyAnalyticsController',
					method : 'POST',
					dataType : 'json',
					data : {
						action : "totalEnergy"
					},
					success : function(data) {
						console.log(data)
						$('#totalEnergy').empty();
						var totalDevices = 0;
						var totalProduction = 0;
						var totalConsumption = 0;
						if (data.length > 0) {
							$.each(data, function(e) {
								var tds = '<tr>' + '<td>'
										+ data[e].district_name + '</td>'
										+ '<td>' + data[e].devices + '</td>'
										+ '<td>'
										+ parseInt(data[e].totalProduced)
										/ 1000 + ' KWH </td>' + '<td>'
										+ parseInt(data[e].total_consumed)
										/ 1000 + ' KWH </td>' + '</tr>';
								totalDevices = totalDevices
										+ parseInt(data[e].devices);
								totalProduction = totalProduction
										+ parseInt(data[e].totalProduced);
								totalConsumption = totalConsumption
										+ parseInt(data[e].total_consumed);

								$("#totalEnergy").append(tds);

							})
							totalProduction = totalProduction / 1000;
							totalConsumption = totalConsumption / 1000;
							$("#totaldevices").append(totalDevices);
							$("#totalProduced")
									.append(totalProduction + " KWH");
							$("#totalConsumed").append(
									totalConsumption + " KWH");
						}
					}
				})
			}
		</script>
		<!-- End Initialize Customer Table -->





		<script>
			$(document).ready(function() {
				App.init();
				TableManageDefault.init();
				getTopProductions();
				getLeastProductions();
				getTopConsumptions();
				getLeastConsumption();
				getTotalEnergy();
			});
		</script>
</body>

</html>
