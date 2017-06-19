<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="bean.MonthlyWisePayment"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.Date"%>
<%@page import="bean.CustomerLoanBean"%>
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

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->



</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			CustomerLoanBean appliance = (CustomerLoanBean) request
					.getAttribute("appliance");
			List<MonthlyWisePayment> loans1 = (List<MonthlyWisePayment>) request
					.getAttribute("loanBook");
			// 			String terminatAt = (String) request.getAttribute("terminatAt");
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
							<%=userbean.getUserName()%>
							<small> Superadmin </small>
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

					<li class="has-sub active"><a href="DoLoanBooks"><i
							class="fa fa-star"></i><span>Loan Books</span></a></li>

					<li class="has-sub"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-th"></i> <span>Sales
								Force</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="DoWiseFieldOfficers">Field Officer</a></li>
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

		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin page-header -->
			<h1 class="page-header">
				Loan Book-
				<%=appliance.getApplianceName()%>/
				<%=appliance.getCustomerName()%></h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-6 -->
				<div class="col-md-6 ">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Customer Detail</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">

									<tr>
										<th>Name</th>
										<td><a
											href="ViewServlet?click=doview&id=<%=appliance.getCustomerId()%>&cnic=<%=appliance.getCnicNo()%>&applianceId=<%=appliance.getAppliance_id()%>">
												<%=appliance.getCustomerName()%>
										</a></td>
									</tr>

									<tr>
										<th>Created On</th>
										<td><%=appliance.getCreated_on()%></td>
									</tr>
									<tr>
										<th>Loan Scheme</th>
										<td><%=appliance.getTotal_installments()%> Months</td>
									</tr>
									<tr>
										<th>Monthly Amount</th>
										<%
											if (loans1.isEmpty()) {
										%>
										<td>Awaiting down payment</td>
										<%
											} else {
										%>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(
							appliance.getMonthly_amount())%><b> PKR</b></td>
										<%
											}
										%>
									</tr>
									<tr>
										<th>Loan Outstanding</th>
										<%
											
										%>
										<td id="loanOutStanding"><%=NumberFormat.getNumberInstance(Locale.US).format(
						(appliance.getRemaining_balanse()))%><b> PKR</b></td>
									</tr>
									<tr>
										<th>Down Payment</th>
										<td>
											<%
												double down = appliance.getDownPayment();
													if (down != 0.0) {
											%> <span class="fa fa-check fa-lg m-r-5 text-success"
											id="downPayment"></span><%=NumberFormat.getNumberInstance(Locale.US).format(
							Math.round(down))%><b> PKR</b> <%
 	} else {
 %> <span class="label label-warning">waiting</span> <%
 	}
 %>
										</td>
									</tr>
									<tr>
										<th>Terminate At</th>
										<%
											if (appliance.getTerminate_date() != null) {
										%>
										<td id="terminateAt"><%=appliance.getTerminate_date()%></td>
										<%
											} else {
										%>
										<td id="terminateAt">N/A</td>
										<%
											}
										%>
									</tr>

									<tr>
										<th>Loan Status</th>
										<td>
											<%
												if (appliance.getLoanStatus().equals("owned")) {
											%> <span class="label"
											style="background-color: #d35400; color: white; font-weight: bold;">Paid
												Off and Owned</span> <%
 	} else if (appliance.getLoanStatus().equals("maintained")) {
 %> <span class="label"
											style="background-color: blue; color: white; font-weight: bold;">Maintained
										</span> <%
 	} else if (appliance.getLoanStatus().equals("defaulter")) {
 %> <span class="label"
											style="background-color: purple; color: black; font-weight: bold;">Defaulted</span>
											<%
												}
											%>
										</td>
									</tr>
								</table>
							</div>

						</div>
					</div>
					<!-- end panel -->

				</div>
				<!-- end col-6 -->

				<!-- begin col-6 -->
				<div class="col-md-6 ">

					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!--                                 <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a title="" data-original-title="" href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
 -->
							</div>
							<h4 class="panel-title">Appliance Summary</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
										<th>Appliance Name</th>
										<td><a
											href="ViewServlet?click=viewDoAppliance&id=<%=appliance.getAppliance_id()%>">
												<%=appliance.getApplianceName()%>
										</a></td>
									</tr>
									<tr>
										<th>Appliance IMEI</th>
										<td><%=appliance.getImeiNumber()%></td>
									</tr>
									<tr>
										<th>GSM Number</th>
										<td>
											<%
												StringBuilder appGms = new StringBuilder(appliance
															.getGsmNumber().replace("92", ""));
													appGms = appGms.insert(3, "-");
											%> <span>(+92)</span> <%=appGms%></td>
									</tr>
									<tr>
										<th>Appliance Price</th>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						(appliance.getAppliancePrice()))%><b> PKR</b></td>

									</tr>
									<tr>
										<th>Appliance Status</th>
										<td>
											<%
												int applianceStatus = appliance.getApplianceStatus();
													if (applianceStatus == 1) {
											%> <span class="label"
											style="background-color: #16a085; color: white; font-weight: bold;">Active</span>
											<%
												} else {
											%> <span class="label"
											style="background-color: Red; color: white; font-weight: bold;">
												Inactive</span> <%
 	}
 %>
										</td>
									</tr>
									<tr>
										<th>Nizam Dost Name</th>
										<td><a
											href="NizamDostViewController?click=vleprofile&salesman_id=<%=appliance.getSalesmanId()%>&salesmanGsm=<%=appliance.getSalemanPhone()%>"><%=appliance.getSalesmanName()%>
										</a></td>
									</tr>
									<tr>
										<th>Field Officer Name</th>
										<td><a
											href="doFieldOfficerProfile.jsp?fo_id=<%=appliance.getFoid()%>"><%=appliance.getFieldOfficer()%></a></td>
									</tr>
									<tr>
										<th>District Officer Name</th>
										<td><%=appliance.getUserName()%></td>
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
							<div class="panel-heading-btn">

								<span>COLOR SCHEME:</span> <span class="label"
									style="background-color: #595959; color: white"> Black
									List</span> <span class="label"
									style="background-color: Red; color: white"> Late</span> <span
									class="label" style="background-color: #ecf0f1; color: black">
									Last Day</span> <span class="label"
									style="background-color: #1abc9c; color: black"> B/W 1
									to 4</span> <span class="label"
									style="background-color: #f1c40f; color: black"> B/W 5
									to 10</span> <span class="label"
									style="background-color: #8e44ad; color: white"> B/W 11
									to 29</span> <span class="label"
									style="background-color: Green; color: white"> Above One
									Month</span>
							</div>
							<h4 class="panel-title">Loan Book</h4>

						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<thead>
										<tr>
											<th>Due Date</th>
											<th>Paid Amount</th>
											<th>Paid Date</th>
											<th>Days Activate</th>
											<th>Transaction Id</th>
											<th>Consumer Number</th>
										</tr>
									</thead>
									<tbody id="loanBook">
										<%
											String color = "white";
												String tcolor = "black";
												for (int i = 0; i < loans1.size(); i++) {
										%>
										<%
											if (loans1.get(i).getPaid_date() != null
															&& loans1.get(i).getPaid_date()
																	.after(loans1.get(i).getDue_date())) {
										%><tr style="background-color: red; color: white;">
											<%
												} else if (loans1.get(i).getPaid_date() == null
																&& new Date().after(loans1.get(i).getDue_date())) {
											%>
										
										<tr style="background-color: red; color: white;">
											<%
												} else {
															if (i == loans1.size() - 1) {
																//            
																if (loans1.get(i).getRemaining_days() >= -7
																		&& loans1.get(i).getRemaining_days() <= -1) {
																	color = "Red";
																	tcolor = "black";
																} else if (loans1.get(i).getRemaining_days() == 0) {
																	color = "#ecf0f1";
																	tcolor = "black";
																} else if (loans1.get(i).getRemaining_days() >= 1
																		&& loans1.get(i).getRemaining_days() <= 4) {
																	color = "#1abc9c";
																	tcolor = "black";
																} else if (loans1.get(i).getRemaining_days() >= 5
																		&& loans1.get(i).getRemaining_days() <= 10) {
																	color = "#f1c40f";
																	tcolor = "black";
																} else if (loans1.get(i).getRemaining_days() >= 11
																		&& loans1.get(i).getRemaining_days() <= 29) {
																	color = "#8e44ad";
																	tcolor = "white";
																} else if (loans1.get(i).getRemaining_days() >= 30) {
																	color = "Green";
																	tcolor = "white";
																} else if (loans1.get(i).getRemaining_days() <= -8) {
																	color = "#595959";
																	tcolor = "white";
																}
											%>
										
										<tr
											style="background-color: <%=i == 0 ? "white" : color%>; color: <%=i == 0 ? "black" : tcolor%>;">
											<%
												}
														}
											%>

											<td><%=i == 0 ? "N/A" : loans1.get(i).getDue_date()%></td>
											<%
												if (!loans1.get(i).getPaid_amount().equals("0")) {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US)
								.format(Integer.parseInt(loans1.get(i)
										.getPaid_amount()))%><b> PKR</b></td>
											<td><%=loans1.get(i).getPaid_date()%></td>
											<td><%=loans1.get(i).getDays_activated()%></td>
											<td><%=loans1.get(i).getTrasnction_id()%></td>
											<td><%=loans1.get(i).getAppliance_imei()%></td>
											<%
												} else {
											%>
											<td>N/A</td>
											<td>N/A</td>
											<td>N/A</td>
											<td>N/A</td>
											<td>N/A</td>
											<%
												}
											%>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>

							</div>
							<%
								}
							%>
						</div>
					</div>
					<!-- end panel -->



				</div>
			</div>

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
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>

	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<script>
		$(document).ready(function() {
			App.init();
			// 			TableManageColVis.init();
		});
	</script>

</body>


</html>
