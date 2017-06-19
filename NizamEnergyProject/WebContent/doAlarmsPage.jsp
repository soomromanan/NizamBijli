<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->
<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->

<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->

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
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<div id="sidebar" class="sidebar">
			<!-- begin sidebar scrollbar -->
			<div>
				<!-- begin sidebar user -->
				<ul class="nav">
					<li class="nav-profile">
						<div class="image">
							<a href="javascript:;"><img src="assets/img/user-13.jpg"
								alt="" /></a>
						</div>
						<div class="info">
							<%=userbean.getUserName()%>
							<small>District Officer</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="has-sub "><a href="DistrictOfficerDashboard">
							<i class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> 
							<i class="icon-note"></i> <span>Loan Request</span> 
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="DoWiseAdvanceBooking"><i
							class="fa fa-star"></i><span>Advance Booking</span> <span
							class="badge pull-right" id="do_unseen_bookings_count"></span></a></li>

					<li class="has-sub "><a href="DoAppliances"><i
							class="fa fa-suitcase"></i><span>Pipeline</span></a></li>

					<li class="has-sub"><a href="DoLoanBooks"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DoWiseFieldOfficers">Field Officer</a></li>
							<li><a href="DoWiseNizamDost">Nizam Dost</a></li>
						</ul></li>


					<li class="active"><a href="DoAlarmsPage"> <i
							class="fa fa-star"></i> <span>Live Alerts</span>
					</a></li>
					
					<li class="has-sub"><a href="DoFutureLoanBooks"> <i
							class="fa fa-star"></i> <span>Future Loan Books</span>
					</a></li>
					<!-- begin sidebar minify button -->
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
			<!-- begin page-header -->
			<h1 class="page-header">Alarms</h1>
			<!-- end page-header -->

			<!-- begin row -->


			<div class="row">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								
							</div>
							<h4 class="panel-title">District Wise Alarms</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="do_alerts-table"
									class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Alarm Time</th>
											<th>Alarms</th>
											<th>Resolved Time</th>
											<th>Appliance IMEI</th>
											<th>Customer</th>
											<th>Status</th>
											<th>Salesman</th>
											<th>Field Officer</th>
											<th>District Officer</th>
											<th>District</th>
										</tr>
									</thead>
									<tbody>
									</tbody>

								</table>
							</div>
						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-10 -->
			</div>
			<!-- end row -->
			<%
				}
			%>
		</div>
		<!-- end #content -->



		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->

		<!-- footer start -->

		<!-- footer end -->

	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->



	<script>
		$(function() {

			$('#do_alerts-table')
					.DataTable(
							{

								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "ApplianceAlarms",
									"type" : "post",
									"data" : function(d) {
										d.action = "getAlarmsForDo"
									},
									"dataSrc" : function(json) {
										console.log("========================");
										console.log(json);
										console.log(json.data);
										for (var i = 0; i < json.data.length; i++) {
											json.data[i].alert = "";
											json.data[i].status = "";
											json.data[i].resTime = "";
											if (json.data[i].solar_power == "true") {
												json.data[i].alert += " Solar Power ";
											}
											if (json.data[i].battery == "true") {
												json.data[i].alert += " Battery ";
											}
											if (json.data[i].temprature == "true") {
												json.data[i].alert += " Temprature ";
											}
											if (json.data[i].current_load == "true") {
												json.data[i].alert += " Current Load ";
											}
											if (json.data[i].lid_connectivity == "true") {
												json.data[i].alert += "Lid Connectivity";
											}

											if (json.data[i].ticketStatus == "0") {
												json.data[i].status = "Pending";
											} else if (json.data[i].ticketStatus == "1") {
												json.data[i].status = "Resolved";
											}

											if (json.data[i].resolvedTime == "null"
													|| json.data[i].resolvedTime == null) {
												json.data[i].resTime = "N/A";
											} else {
												json.data[i].resTime = json.data[i].resolvedTime
											}

											if (json.data[i].imeiNumber == "null") {
												json.data[i].imeiNumber = "N/A";
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
									"data" : "resTime"
								}, {
									"data" : "imeiNumber"
								}, {
									"data" : "customerName"
								}, {
									"data" : "status"
								}, {
									"data" : "salesman"
								}, {
									"data" : "foName"
								}, {
									"data" : "userName"
								}, {
									"data" : "district"
								} ]

							});
		});
	</script>





	<script>
		$(document).ready(function() {
			App.init();

		});
	</script>

</body>


</html>
