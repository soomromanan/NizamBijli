<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.SalesManBean"%>
<%@page import="java.util.ArrayList"%>

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
<!-- <script type='text/javascript' -->
<!-- 	src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->




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
							<%=bean.getUserName()%>
							<small>District Officer</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="has-sub "><a href="DistrictOfficerDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> <i
							class="icon-note"></i> <span>Loan Request</span> <span
							class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="DoWiseAdvanceBooking"><i
							class="fa fa-star"></i><span>Advance Booking</span> <span
							class="badge pull-right" id="do_unseen_bookings_count"></span></a></li>

					<li class="has-sub "><a href="DoAppliances"><i
							class="fa fa-suitcase"></i><span>Pipeline</span></a></li>

					<li class="has-sub"><a href="DoLoanBooks"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub active"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DoWiseFieldOfficers">Field Officer</a></li>
							<li class="active"><a href="DoWiseNizamDost">Nizam Dost</a></li>
						</ul></li>


					<li class="has-sub"><a href="DoAlarmsPage"> <i
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
			<h1 class="page-header">
				Nizam Dost <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
					<%-- <%=bean.size() %> --%>
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->

			<div class="row" style="padding-top: 2%">

				<div class="row">

					<!-- begin col-12 -->
					<div class="col-md-12">
						<!-- begin panel -->
						<div class="panel panel-inverse">
							<div class="panel-heading">
								<h4 class="panel-title">Nizam Dost Table</h4>
							</div>

							<div class="panel-body">
								<div class="table-responsive" style="font-size: 13px">
									<table id="salesman-table"
										class="table table-hover table-striped">
										<thead>
											<tr>
												<th>Nizam Dost Name</th>
												<th>Phone Number</th>
												<th>Fo Name</th>
												<th>Last Sale</th>
												<th>Total Apps</th>
												<th>Total Installs</th>
												<th>Monthly Apps</th>
												<th>Monthly Installs</th>
												<th>Weekly Apps</th>
												<th>Weekly Installs</th>
												<th>Recovery Rate</th>
												<th>Status</th>

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
			</div>
			<!-- end #content -->

			<%
				}
			%>

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
		<script src="assets/js/myscript.js"></script>
		<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
		<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
		<script src="assets/js/table-manage-colvis.demo.min.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->

		<script>
			$(function() {

				$('#salesman-table')
						.DataTable(
								{

									"processing" : true,
									"serverSide" : true,
									"order" : [ [ 0, "asc" ] ],
									"ajax" : {
										"url" : "SalesmanTableControl",
										"type" : "post",
										"data" : function(d) {
											d.action = "getSalesmanTableById";

										},
										"dataSrc" : function(json) {
											console
													.log("========================");
											console.log(json);
											console.log(json.data);

											$('.page-header span').text(
													json.salesmansCount);
											for ( var i = 0; i < json.data.length; i++) {
												json.data[i].recovery1 = json.data[i].recovery
														+ "%";
											}

											return json.data;
										}
									},
									"columns" : [ {
										"data" : "salesmanName"
									}, {
										"data" : "salesmanNumber"
									}, {
										"data" : "foName"
									}, {
										"data" : "last_sale",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "total_apps"
									}, {
										"data" : "total_installs"
									}, {
										"data" : "monthly_apps"
									}, {
										"data" : "monthly_installs"
									}, {
										"data" : "weekly_apps"
									}, {
										"data" : "weekly_installs"
									}, {
										"data" : "recovery1"
									}, {
										"data" : "salesman_status",
										"orderable" : false,
										"searchable" : false
									} ],

									"rowCallback" : function(row, data) {
										$(row).data(
												'link',
												'NizamDostViewController?click=vleprofile&salesman_id='
														+ data.salesmanId
														+ '&salesmanGsm='
														+ data.salesmanNumber);
										$(row).addClass('link');
									},

								});

				$("#salesman-table tbody").on('click', 'tr.link>td',
						function(event) {
							link = $(this).parent().data('link');
							if (link != undefined) {
								window.location = link;
							}
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
