<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="bean.MonthlyWisePayment"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.Date"%>
<%@page import="bean.CustomerLoanBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.CustomerBal" %>

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
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-multiple-select/css/bootstrap-multiple-select.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />
</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
			CustomerLoanBean appliance = (CustomerLoanBean) request
					.getAttribute("appliance");
			String appGms = CustomerBal.getFormattedPhoneNumber(appliance.getGsmNumber());
			List<MonthlyWisePayment> loans1 = (List<MonthlyWisePayment>) request
					.getAttribute("loanBook");
	%>

	<script>
		var appId =
	<%=appliance.getAppliance_id()%>
		var userIdd =
	<%=userbean.getUserId()%>
		
	</Script>
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

					<li class="has-sub active"><a href="javascript:;"> <b
							class="caret pull-right"></b> <i class="fa fa-money "></i> <span>Finance</span>
					</a>
						<ul class="sub-menu active">
							<li><a href="Finance">Payments</a></li>
							<li class="active"><a href="Loan">Loan books</a></li>
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
											href="ViewServlet?click=view&id=<%=appliance.getCustomerId()%>&cnic=<%=appliance.getCnicNo()%>&appId=<%=appliance.getAppliance_id()%>">
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
									<tr>
										<th>Recovery Rating</th>
										<td><%=appliance.getCustomer_Rating()%></td>

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
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Appliance Summary</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tr>
										<th>Appliance Name</th>
										<td><a
											href="ViewServlet?click=viewAppliance&id=<%=appliance.getAppliance_id()%>">
												<%=appliance.getApplianceName()%>
										</a></td>
									</tr>
									<tr>
										<th>Appliance IMEI</th>
										<td><%=appliance.getImeiNumber()%></td>
									</tr>
									<tr>
										<th>GSM Number</th>
										<td> <%=appGms%></td>
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
											href="NizamDostViewController?click=ok&salesman_id=<%=appliance.getSalesmanId()%>&salesmanGsm=<%=appliance.getSalemanPhone()%>"><%=appliance.getSalesmanName()%>
										</a></td>
									</tr>
									<tr>
										<th>Field Officer Name</th>
										<td><a
											href="fieldOfficerProfile.jsp?fo_id=<%=appliance.getFoid()%>"><%=appliance.getFieldOfficer()%></a></td>
									</tr>
									<tr>
										<th>District Officer Name</th>
										<td><a
											href="DistrictOfficer?do_id=<%=appliance.getDoid()%>"><%=appliance.getUserName()%></a>
										</td>
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

								<span>Recovery Schedule:</span> <span class="label"
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

						</div>
					</div>
					<!-- end panel -->



				</div>
			</div>

			<!-- end row -->

			<div class="row">
				<div class="col-md-6" class="bg-silver">
					<form data-comments class="form-horizontal">
						<!-- 						<legend>Comments</legend> -->
						<h4 class="title">Comments</h4>
						<div class="form-group has-feedback">
							<div class="col-md-6">
								<textarea class="form-control" name="message" id="message"
									cols="40" rows="5" placeholder="write message here"
									style="width: 320px; height: 35px;" required></textarea>
								<input type="hidden" name="appId" id="appId"
									value="<%=appliance.getAppliance_id()%>">
							</div>
							<div class="col-md-4">
								<!-- 								<button type="submit" name="click" id="clcik" onclick="insertComments()"> -->
								<!-- 									Send</button> -->
								<button type="button" class="btn btn-primary pull-right"
									id="add_comment">Send</button>
							</div>
						</div>
					</form>
				</div>

				<!-- begin col-4 -->
				<div class="col-md-6">
					<ul class="nav nav-pills f-s-15 inline bordered round-corner">
						<li>
							<div class="row">
								<div class="col-xs-5">
									<label for="ex1">From:</label> <input class="form-control"
										type="date" name="from" id="from" onchange="checkDate();">
								</div>

								<div class="col-xs-5 input-control text" data-role="datepicker">
									<label for="ex1">To:</label> <input class="form-control too"
										type="date" name="to" id="to" onchange="checkDateTo();">

								</div>

								<div class="col-xs-5 input-control text">
									<p id="date_message" style="display: none; color: red">
										Select date Less than today's date</p>

								</div>

								<input class="btn btn-default" type="button"
									style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
									class="btn btn-sm btn-success" id="comments" value="Filter"
									onclick="getComments()">
							</div>
						</li>
					</ul>
					<!-- begin scrollbar -->

					<div data-scrollbar="true" data-height="280px" class="bg-silver">

						<ul class="chats" id="list">

						</ul>
					</div>
					<!-- end scrollbar -->
				</div>
				<!-- end col-4 -->
			</div>
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

	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/plugins/parsley/dist/parsley.js"></script>

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<Script>
		function checkDate() {
			var EnteredDate = document.getElementById("from").value; //for javascript

			var EnteredDate = $("#from").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}
		}

		function checkDateTo() {
			var EnteredDate = document.getElementById("to").value; //for javascript

			var EnteredDate = $("#to").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}

		}

		function getComments() {
			var from = document.getElementById("from").value;
			var to = document.getElementById("to").value;
			$.ajax({
						url : 'CommentsController',
						method : 'POST',
						dataType : 'json',
						data : {
							click : "getComments",
							appId : appId,
							from : from,
							to : to
						},
						success : function(data) {
							console.log(from + " " + to)
							$('#list').empty();
							if (data.length > 0) {
								$
										.each(
												data,
												function(e) {
													if (userIdd != data[e].userId) {
														var lis = '<li class="left"> <span class="date-time">'
																+ data[e].dateTime
																+ '</span>'
																+ '<a href="" class="name">'
																+ data[e].userName
																+ '</a>'
																+ '<div class="message" rows="5">'
																+ data[e].text
																+ '</div>'
																+ '</li>';
													} else {
														lis = '<li class="right"> <span class="date-time">'
																+ data[e].dateTime
																+ '</span>'
																+ '<a href="" class="name">'
																+ data[e].userName
																+ '</a>'
																+ '<div class="message" rows="5">'
																+ data[e].text
																+ '</div>'
																+ '</li>';
													}
													$("#list").append(lis);

												})

							}
						}
					})
		} // end of getComments

		// insert comments into database.
		$(function() {
			$('#add_comment').click(function() {
				if ($('[data-comments]').data('comments') != "true") {
					var text_message = $('[name="message"]').val();
					var applianceId = $('[name="appId"]').val();
					console.log(text_message + " ..... " + applianceId);
					$.ajax({
						url : 'CommentsController',
						method : 'POST',
						dataType : 'json',
						data : {
							click : "insertComments",
							appId : applianceId,
							message : text_message
						},
						success : function(data) {
							console.log("added");
						}
					})
				}
				getComments();
				clearText();
			})

		}) // end

		// clearing textbox
		function clearText() {
			document.getElementById('message').value = "";
		} // end
	</Script>

	<script>
		$(document).ready(function() {
			App.init();
			getComments();
		});
	</script>
</body>


</html>
