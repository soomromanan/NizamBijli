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

<!-- ================== BEGIN BASE JS ================== -->
<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
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

					<li class="has-sub"><a href="EnergyAnalytics"> <i
							class="ion-podium"></i> <span>Energy Analytics</span>
					</a></li>

					<li class="has-sub active"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Reports</span>
					</a>
						<ul class="sub-menu active">
							<li class="active"><a href="LateAndDefaulterReport">Late/Defaulter
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
			<!-- begin page-header -->
			<h1 class="page-header"></h1>
			<!-- end page-header -->

			<div class="col-md-12 ">
				<div class="panel panel-inverse" data-sortable-id="index-1">
					<div class="panel-heading">
						<div class="panel-heading-btn"></div>
						<h4 class="panel-title">DO Wise Late Defaulter</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" style="font-size: 13px">

							<table class="table table-striped">
								<thead>
									<tr>
										<th>DO Name</th>
										<th>District Name</th>
										<th>Late</th>
										<th>Defaulter</th>
										
										<th>Defaulter 2</th>
									</tr>
								</thead>
								<tbody id="do_wise_late_defaulters">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-12 ">
				<div class="panel panel-inverse" data-sortable-id="index-1">
					<div class="panel-heading">
						<div class="panel-heading-btn"></div>
						<h4 class="panel-title">FO Wise Late Defaulter</h4>
					</div>
					<div class="panel-body">
						<div class="table-responsive" style="font-size: 13px">

							<table class="table table-striped" id="fo_wise_late_defaulters">
								<thead>
									<tr>
										<th>FO Name</th>
										<th>District Name</th>
										<th>Late</th>
										<th>Defaulter</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>


			<!-- begin row -->
			<%
				}
			%>
			<!-- end row -->
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
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		function getDoWiseLateDefaulters() {
			console.log("getDoWiseLateDefaulters")
			$.ajax({
				url : 'ReportAnalyticsController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : "getDoWiseLateDefaulters"
				},
				success : function(data) {
					console.log(data);

					$('#do_wise_late_defaulters').empty();
					if (data.length > 0) {
						$.each(data, function(e) {
							var tds = '<tr>' + '<td>' + data[e].user_name
									+ '</td>' + '<td>' + data[e].district_name
									+ '</td>' + '<td>' + data[e].late + '</td>'
									+ '<td>' + data[e].defaulter + '</td>'
									+ '</tr>';
							$("#do_wise_late_defaulters").append(tds);
						})

					}
				}
			})
		}

		function getFoWiseLateDefaulters() {
			console.log('getFoWiseLateDefaulters');

			$("#fo_wise_late_defaulters").dataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"order" : [ [ 0, "asc" ] ],
						"ajax" : {
							"url" : "ReportAnalyticsController",
							"type" : "post",
							"data" : function(d) {
								d.action = "getFoWiseLateDefaulters"
							},
							"dataSrc" : function(json) {
								console.log('**********dataSrc*******');

								$.each(json.data, function(e) {
									console.log('Hello people..... '
											+ json.data);
									var tds = '<tr>' + '<td>'
											+ json.data[e].fo_name + '</td>'
											+ '<td>'
											+ json.data[e].district_name
											+ '</td>' + '<td>'
											+ json.data[e].late + '</td>'
											+ '<td>' + json.data[e].defaulter
											+ '</td>' + '</tr>';
									$("#fo_wise_late_defaulters").append(tds);

								});

								console.log(json.data);
								return json.data;
							},
						},
						"columns" : [ {
							"data" : "fo_name"
						}, {
							"data" : "district_name"
						}, {
							"data" : "late"
						}, {
							"data" : "defaulter"
						} ]
					});

		}
		$(document).ready(function() {
			App.init();
			getDoWiseLateDefaulters();
			getFoWiseLateDefaulters();
		});
	</script>

</body>


</html>
