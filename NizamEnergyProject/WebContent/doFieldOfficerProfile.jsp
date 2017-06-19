<!DOCTYPE html>
<%@page import="bal.DoFoNdBal"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bal.CustomerBal"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<html lang="en">

<head>
<meta charset="utf-8" />
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />


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
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet">

<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>

</head>

<body>
	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		int foID = Integer.parseInt(request.getParameter("fo_id"));
		ArrayList<HashMap<String, String>> list = DoFoNdBal
				.getFoSalesman(foID);
		HashMap<String, String> maps = DoFoNdBal
				.getFieldOfficerDetail(foID);
		String priamary_phone = CustomerBal.getFormattedPhoneNumber(maps
				.get("priamary_phone"));
		String secondary_phone = CustomerBal.getFormattedPhoneNumber(maps
				.get("secondary_phone"));
	%>

	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
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
							<small>District Officer</small>
						</div>
					</li>
				</ul>
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
							<li class="active"><a href="DoWiseFieldOfficers">Field
									Officer</a></li>
							<li><a href="DoWiseNizamDost">Nizam Dost</a></li>
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
		<div id="content" class="content">


			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header"><%=maps.get("foName")%></span> <span
				style="float: right; margin-left: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Date Of Joining/<small>
						&nbsp;<%=maps.get("date_of_joining")%></small>
			</a> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Date Of Birth /<small>
						&nbsp;<%=maps.get("date_of_birth")%></small>
			</a>
			</span> <span style="float: right;"></span>

			<div class="row" style="padding-top: 9%">

				<!-- 		<div class="col-md-12"> -->
				<div class="col-md-6">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officer Details</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>
										<tr>
											<th>CNIC</th>
											<td><%=maps.get("Cnic")%></td>
										</tr>

										<tr>
											<th>Address</th>
											<td><%=maps.get("address")%></td>
										</tr>
										<tr>
											<th>District Officer</th>
											<td><b><%=maps.get("DistrictOfficer")%></b></td>
										</tr>

										<tr>
											<th>District/Tehsil</th>
											<td><%=maps.get("District")%><b> /<%=maps.get("city")%></b></td>
										</tr>

										<tr>
											<th>Per Sale Commission</th>
											<%
												if (maps.get("per_sale") == "" || maps.get("per_sale") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(Integer.parseInt(maps.get("per_sale"))))%><b> PKR</b></td>
											<%
												}
											%>

										</tr>

										<tr>
											<th>Field Officer Acc Number</th>

											<%
												if (maps.get("fo_acount_no") == ""
														|| maps.get("fo_acount_no") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=maps.get("fo_acount_no")%></td>
											<%
												}
											%>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officer Contact</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>

										<tr>
											<th>Primary Phone Number</th>
											<td><%=priamary_phone%></td>
										</tr>

										<tr>
											<th>Secondary Phone Number</th>
											<td><%=secondary_phone%></td>
										</tr>

										<tr>
											<th>E-mail</th>
											<td><%=maps.get("fo_email")%></td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- begin scroll to top btn -->
			<a href="javascript:;"
				class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
				data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
			<!-- end scroll to top btn -->
		</div>
		<!-- end page container -->

		<!-- ================== BEGIN BASE JS ================== -->
		<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
		<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
		<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
		<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
		<script src="assets/js/myscript.js"></script>

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<!-- ================== END BASE JS ================== -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script src="assets/plugins/morris/raphael.min.js"></script>
		<script src="assets/plugins/morris/morris.js"></script>
		<script src="assets/js/chart-morris.demo.min.dashboard.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
		<script
			src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
		<script src="assets/js/apps.min.js"></script>
		<script type='text/javascript'
			src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
		<script type="text/javascript" src="assets/async/dashboard.js"></script>
		<script>
		<!-- ================== END PAGE LEVEL JS ================== -->
			$(document).ready(function() {
				App.init();
			});
		</script>
</body>
</html>