<%@page import="bal.CustomerBal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="bal.DoFoNdBal"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.DistrictOfficerBean"%>


<!DOCTYPE html>
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

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->
<!-- 	<script type='text/javascript' src="http://maps.google.com/maps/api/js?sensor=false"></script> -->

<%
	DistrictOfficerBean do_officer = (DistrictOfficerBean) request
			.getAttribute("bean");
%>

</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");

		if (bean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			Integer dist_id = (Integer) request.getAttribute("district");
			
			ArrayList<HashMap<String, String>> list = DoFoNdBal
					.getFieldOfficers(dist_id);

			String primary = CustomerBal.getFormattedPhoneNumber(do_officer.getPhone());
			String secondary_phone = CustomerBal.getFormattedPhoneNumber(do_officer
							.getSecondary_phone());
			String refferal_number1 = CustomerBal
					.getFormattedPhoneNumber(do_officer
							.getRefferal_number1());
			String refferal_number2 = CustomerBal
					.getFormattedPhoneNumber(do_officer
							.getRefferal_number2());
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
					<li class="has-sub active"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li class="active"><a href="DistrictOfficerr">District Officer</a></li>
							<li><a href="FieldOfficer">Field Officer</a></li>
							<li><a href="NizamDost">Nizam Dost</a></li>
						</ul></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Finance</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="Finance">Payments</a></li>
							<li><a href="Loan">Loan books</a></li>
							<li><a href="commissionPayout.jsp">Commissions</a></li>
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
			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header"><%=do_officer.getDo_name()%></span> <span
				style="float: right; margin-left: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Date Of Joining/<small>
						&nbsp;<%=do_officer.getDateofjoining()%></small>
			</a> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Date Of Birth /<small>
						&nbsp;<%=do_officer.getDateofbirth()%></small>
			</a>
			</span> <span style="float: left;"></span>

			<div class="row" style="padding-top: 9%">
				<!-- begin row -->

				<!-- begin col-6 -->
				<div class="col-md-6">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">District Officer Detail</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
										<th>CNIC</th>
										<td><%=do_officer.getCnic()%></td>
									</tr>

									<tr>
										<th>Basic Salary</th>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(do_officer.getSallery()))%><b>
												PKR</b></td>
									</tr>
									<tr>
										<th>Address</th>
										<td><%=do_officer.getAddress()%></td>
									</tr>

									<tr>
										<th>User Gender</th>
										<td><%=do_officer.getUser_gender()%></td>
									</tr>

									<tr>
										<th>Education</th>
										<td><%=do_officer.getEducation()%></td>
									</tr>

									<tr>
										<th>Marrital Status</th>
										<td><%=do_officer.getMarrital_status()%></td>
									</tr>

								</table>
							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>


				<div class="col-md-6">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">DO Contact Detail</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
										<th>Primary Phone Number</th>
										<td><%=primary%></td>
									</tr>

									<tr>
										<th>Secondary Phone Number</th>
										<td><%=secondary_phone%></td>
									</tr>

									<tr>
										<th>Email</th>
										<td><%=do_officer.getUseremail()%></td>
									</tr>

									<tr>
										<th>Refferal Number 1</th>
										<td><%=refferal_number1%></td>
									</tr>
									<tr>
										<th>Refferal Number 2</th>
										<td><%=refferal_number2%></td>
									</tr>
									<tr>
										<th>District</th>
										<td><%=do_officer.getDistrict_name()%></td>
									</tr>

								</table>
							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>

				<!-- end col-6 -->

			</div>
			<!-- end row -->


			<!-- start row -->

			<div class="row">

				<div class="col-md-12">

					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officers</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="data-table" class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Number</th>
											<th>CNIC</th>
											<th>Address</th>
											<th>Salary</th>
											<th>Date of Joining</th>
											<th>Update</th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < list.size(); i++) {
										%>
										<tr class="link"
											data-link="fieldOfficerProfile.jsp?fo_id=<%=list.get(i).get("foid")%>">

											<td><%=list.get(i).get("foName")%></td>
											<td>
												<%
													String foNumber2 = CustomerBal.getFormattedPhoneNumber(list
																	.get(i).get("foPhone"));
												%> <%=foNumber2%>
											</td>
											<td><%=list.get(i).get("cnic")%></td>
											<td><%=list.get(i).get("address")%></td>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							Double.parseDouble(list.get(i).get("salary")))%><b>
													PKR</b></td>
											<td><%=list.get(i).get("joiningDate")%></td>
											<td><a
												href="updatefo.jsp?foId=<%=list.get(i).get("foid")%>"
												class="btn btn-primary btn-xs pull-left">Update
													Profile</a></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>

							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>
			</div>

			<!-- end row -->

		</div>
		<!-- end #content -->

		<%
			} // session else closed
		%>

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
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
		$(document).ready(function() {
			App.init();
			//TableManageColVis.init();
		});
	</script>

</body>
</html>