<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="bean.UserBean"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet" />
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />

<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>

<style>
#map {
	height: 717px;
}
</style>

</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
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
							<%=bean.getUserName()%>
							<small> Superadmin </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">

					<li class="active"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <i
							class="icon-note"></i> <span>New Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="AdvanceBooking"> <i
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
			<!-- begin page-header -->
			<h1 class="page-header">Dashboard</h1>
			<!-- end page-header -->
			<!-- begin row -->
			<div class="row">
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats bg-green"
						id="active_appliances_widget">
						<div class="stats-icon stats-icon-sm">
							<i class=""></i>
						</div>
						<div class="stats-number">0</div>

						<div class="stats-progress progress">
							<div class="progress-bar"></div>
						</div>
						<div class="stats-desc">0 %</div>
						<div class="stats-title"></div>

					</div>
				</div>



				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats bg-blue" id="loan_payment_widget">
						<div class="stats-icon stats-icon-sm">
							<i class=""></i>
						</div>

						<div class="stats-number">0</div>

						<div class="stats-progress progress">
							<div class="progress-bar"></div>
						</div>
						<div class="stats-desc">0 %</div>
						<div class="stats-title"></div>

					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats " id="mobile_money_widget"
						style="background: green">
						<div class="stats-icon stats-icon-sm">
							<i class=""></i>
						</div>

						<div class="stats-number">0</div>
						<div class="stats-progress progress">
							<div class="progress-bar"></div>
						</div>
						<div class="stats-desc">0 %</div>
						<div class="stats-title">
							<!-- Total Deployments -->
						</div>

					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3">
					<div class="widget widget-stats bg-black"
						id="sold_appliances_widget" style="height: 117px;">
						<div class="stats-icon stats-icon-sm">
							<i class=""></i>
						</div>
						<div class="stats-number">0 PKR</div>
						<div class="stats-progress progress">
							<div class="progress-bar"></div>
						</div>
						<div class="stats-desc ">0% Total Portfolio</div>
					</div>
				</div>
				<!-- end col-3 -->
			</div>
			<!-- end row -->

			<!-- begin row -->
			<div class="row">

				<!-- map content start-->

				<!-- begin col-8 -->
				<div class="col-md-8 ">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Map</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive">
								<div id="map">
									<canvas height="300" width="683"
										style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 683px; height: 400px;"
										class="flot-base"></canvas>
								</div>
								<div class=""></div>
							</div>
						</div>
					</div>
				</div>



				<div class="col-md-4 ">
					<div class="panel panel-inverse" data-sortable-id="index-6">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Pipeline Details</h4>
						</div>
						<div class="panel-body p-t-0" style="padding: inherit;">
							<div class="table-responsive">
								<table class="table table-valign-middle m-b-0 table-striped">
									<thead>
										<tr>
											<th>Status</th>
											<th>Customers</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><label class="label"
												style="background-color: #bda3c7; color: white; font-weight: bold;">CC
													Approvals</label></td>

											<td><span id="toVerifybyCC" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</label></td>

											<td><span id="countPendingCustomers"
												class="text-success"></span></td>
										</tr>

										<tr>
											<td><label class="label "
												style="background-color: #2980b9; color: white; font-weight: bold;">Accepted</label></td>

											<td><span id="countAcceptedCustomers"
												class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #9b59b6; color: white; font-weight: bold;">Ready
													To Assign</label></td>

											<td><span id="ReadyToAssign" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #2ecc71; color: white; font-weight: bold;">Verified</label></td>

											<td><span id="countVarifiedCustomers"
												class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #1abc9c; color: white; font-weight: bold;">Awaiting
													Down Payment</label></td>

											<td><span id="AwaitingDownPayment" class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label"
												style="background-color: green; color: white; font-weight: bold;">RDP</label></td>

											<td><span id="RDP" class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label"
												style="background-color: green; color: white; font-weight: bold;">Installed</label></td>

											<td><span id="Installed" class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label"
												style="background-color: #d35400; color: white; font-weight: bold;">Returned</label></td>

											<td><span id="returned" class="text-primary"></span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="panel panel-inverse" data-sortable-id="morris-chart-4">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Loanbook Detail</h4>
						</div>

						<div class="panel-body p-t-0" style="padding: inherit;">
							<div class="table-responsive">
								<table class="table table-valign-middle m-b-0 table-striped">
									<thead>
										<tr>
											<th>Loanbook Status</th>
											<th>Customers</th>
										</tr>
									</thead>
									<tbody>

										<tr>
											<td><label class="label" style="background-color: green">Cash
											</label></td>

											<td><span id="cash" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label"
												style="background-color: #7f8c8d">Owned </label></td>

											<td><span id="Owned" class="text-primary"></span></td>
										</tr>
										<tr>
											<td><label class="label " style="background-color: blue">Maintained</label></td>

											<td><span id="Maintain" class="text-primary"></span></td>
										</tr>

										<tr>
											<td><label class="label" style="background-color: red">Late</label></td>

											<td><span id="total_late" class="text-primary"></span></td>
										</tr>


										<tr>
											<td><label class="label"
												style="background-color: #34495e;">Defaulter</label></td>

											<td><span id="Defaulter" class="text-primary"></span></td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>


			</div>
			<!-- end col-8 -->

			<!-- map content end-->
			<div class="row">
				<div class="col-md-12 ">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">PortFolio Health</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>10 days before due</th>
											<th>B/W 6-10 days before due</th>
											<th>B/W 1-5 days before due</th>
											<th>On due</th>
											<th>B/W 1-5 days after due</th>
											<th>B/W 6-10 days after due</th>
											<th>10 days after due</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td id="BeforeTen"></td>
											<td id="BeforeSixToTen"></td>
											<td id="BeforeOneToFive"></td>
											<td id="zero"></td>
											<td id="OneToFive"></td>
											<td id="SixtoTen"></td>
											<td id="AfterTen"></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- begin col-4 -->


				<div class="col-md-6 ">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Top Five Nizam Dost</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">

								<table class="table table-striped">
									<thead>
										<tr>
											<th>Nizam Dost Name</th>
											<th>District</th>
											<th>Current Month Sales</th>
											<th>Total Sales</th>
										</tr>
									</thead>
									<tbody id="super_top_five_nds">

									</tbody>


								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Top Five Field Officer</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">

								<table class="table table-striped">
									<thead>
										<tr>
											<th>Field Officer Name</th>
											<th>District</th>
											<th>Current Month Sales</th>
											<th>Total Sales</th>
										</tr>
									</thead>
									<tbody id="super_top_five_fos">

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
							<h4 class="panel-title">Current Month Performance</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>District</th>
											<th>Last Month Downpayments</th>
											<th>Current Month Downpayments</th>
											<th>Sales / FO</th>
											<th>Total Sales</th>
											<th>Average Credit Score</th>
											<th>Recovery Rate</th>
										</tr>
									</thead>
									<tbody id="current_perf">
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
							<h4 class="panel-title">Current Month Recoveries</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>District Name</th>
											<th>All Previous Sales</th>
											<th>Total to be recovered</th>
											<th>Percentage recovered to date</th>
											<th>Percentage yet to be recovered</th>
											<th>Percentage recoveries late</th>
											<th>All previous Late</th>
											<th>All previous recovered this month</th>
										</tr>
									</thead>
									<tbody id="get_recovery_details">
									</tbody>
									<tr>
										<th colspan="2">Total Amount</th>
										<td id="total"></td>
										<td id="amount"></td>
										<td></td>
										<td></td>
										<td id="late_recovery"></td>
										<td id="late_recovery_paid"></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>

				<%
					} // session else closed
				%>

			</div>

			<%
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new Date());
			
			%>
			<span style="padding-left: 33%"> <%=year %> Nizam Energy ©- All
				Rights Reserved </span>

		</div>
		<!-- end #content -->

		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->

		<!-- end page container -->

		<!-- ================== BEGIN BASE JS ================== -->
		<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
		<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
		<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<!-- ================== END BASE JS ================== -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script src="assets/plugins/morris/raphael.min.js"></script>
		<script src="assets/plugins/morris/morris.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->

		<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>

		<script
			src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>

		<script src="assets/js/apps.min.js"></script>
		<script type="text/javascript" src="assets/async/dashboard.js"></script>
		<script>
			$(document).ready(function() {
				App.init();
			});
		</script>
</body>


</html>
