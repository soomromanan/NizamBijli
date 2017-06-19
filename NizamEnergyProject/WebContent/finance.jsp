<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

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
							<li class="active"><a href="Finance">Payments</a></li>
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

			<h1 class="page-header" id="">Payments</h1>
			<h4 class="" id="curdate"></h4>
			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-6 -->
				<div class="col-md-5">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<h4 class="panel-title">Payment Highlights</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="" class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Total installments Payments</th>
											<td id="total_istallments" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total Downpayments</th>
											<td id="down_payments" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total Amount</th>
											<td id="total_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th style="color: red">Total Net Income</th>
											<td id="total_net_income"
												style="padding: 12px 50px; color: red">0</td>
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
				<div class="col-md-5">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<h4 class="panel-title">Commission Payout Highlights</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="" class="table table-hover table-striped">
									<thead>
										<tr>
											<th>Total FO payout</th>
											<td id="total_fo_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total ND payout</th>
											<td id="total_nd_amount" style="padding: 12px 50px">0</td>
										</tr>
										<tr>
											<th>Total payout</th>
											<td id="total_commission" style="padding: 12px 50px">0</td>
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

			<!-- begin row -->


			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<h4 class="panel-title">Payments Table</h4>
						</div>

						<div class="panel-body">
							<div id="datepick" class="table-responsive"
								style="font-size: 13px">

								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li>
										<div class="row">

											<%
												Date myDate = new Date();
													SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

													System.out.println("Current Date: " + ft.format(myDate));
											%>
											<div class="col-xs-6">
												<label for="ex1">From:</label> <input class="form-control"
													type="date" name="from" id="from" onchange="checkDate();">
											</div>
											<div class="col-xs-6 input-control text"
												data-role="datepicker">
												<label for="ex1">To:</label> <input class="form-control too"
													type="date" name="to" id="to" onchange="checkDateTo();">
											</div>


										</div>

										<div class="col-xs-12 input-control text">
											<p id="date_message" style="display: none; color: red">
												Select date Less than today's date</p>
										</div>

									</li>

									<li><input class="btn btn-default" type="button"
										style="background-color: #AFB1A2; margin-top: 26px; border-color: greenyellow;"
										class="btn btn-sm btn-success" id="filterid" value="Filter"
										onclick="filter()"></li>

									<li style="margin-top: 26px"><a class="m-0 p-5 m-r-15"
										id="installments_li"> <input type="radio"
											name="paymentStatus" id="installments_rb"> <span
											class="label" id="installments"
											style="background-color: green; color: white; font-weight: bold;">
										</span> <label for="installments_rb">Installments</label>
									</a></li>
									<li style="margin-top: 26px"><a class="m-0 p-5 m-r-15"
										id="downpayments_li"> <input type="radio"
											name="paymentStatus" id="downpayments_rb"> <span
											class="label" id="downPayments"
											style="background-color: red; color: white; font-weight: bold;">
										</span> <label for="downpayments_rb">DownPayments</label>
									</a></li>
								</ul>



								<table id="fiance_table"
									class="table table-hover table-striped">
									<thead>
										<tr>
											<th style="background: none">Paid Date</th>
											<th>District</th>
											<th>Customer Name</th>
											<th>Appliance IMEI</th>
											<th>Installment Amount</th>
											<th>Transaction ID</th>
											<th>Bank Name</th>
											<th>FO Name</th>
											<th>FO Commission</th>
											<th>ND Name</th>
											<th>ND Commission</th>
											<th>Payment Type</th>
										</tr>
									</thead>
									<tbody id="fff">
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<!-- end panel -->
				</div>
				<!-- end col-10 -->
			</div>
		</div>
	</div>
	<!-- end row -->
	<%
		} //end for
			// session else closed
	%>






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
	<!-- <script src="assets/js/jquery-ui-1.8.18.custom.js"></script> -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/plugins/icheck/icheck.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->
	<script>
		var paymentStatus;
		var filter;
		var action;
		var radioClass;

		function formatDollar(num) {
			var p = num.toFixed(2).split(".");
			return p[0].split("").reverse().reduce(function(acc, num, i, orig) {
				return num + (i && !(i % 3) ? "," : "") + acc;
			}, "")/*  + "." + p[1] */;
		}

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
			} else {
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

		function filter() {

			// uncheck checkboxes if checked before button click			
			if ($('#installments_rb').is(':checked')
					|| $('#downpayments_rb').is(':checked')) {
				$('input').iCheck('uncheck');
			}
			// end		

			var table = $('#fiance_table').DataTable();

			table.clear();
			table.destroy();
			var to = document.getElementById("to").value;
			var from = document.getElementById("from").value;
			if (to == null || to === '') {

				var currentDt = new Date();
				var mm = currentDt.getMonth() + 1;
				mm = (mm < 10) ? '0' + mm : mm;
				var dd = currentDt.getDate();
				var yyyy = currentDt.getFullYear();
				var date = yyyy + '-' + mm + '-' + dd;
				to = date;
				from = date;
			}

			$("#fiance_table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],

								"ajax" : {
									"url" : "FinanceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "getpaymentorderby",
												d.to = to, d.from = from
									},
									"dataSrc" : function(json) {

										$
												.each(
														json.data,
														function(e) {

															if (json.data[e].installment_paid) {
																json.data[e].installment_paid = formatDollar(json.data[e].installment_paid)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].fo_commission_per_device) {
																json.data[e].fo_commission_per_device = formatDollar(json.data[e].fo_commission_per_device)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].nd_commission_per_device) {
																json.data[e].nd_commission_per_device = formatDollar(json.data[e].nd_commission_per_device)
																		+ "<b> PKR </b>"
															}
														});

										$('#curdate').html(json.curdate);
										$("#total_istallments")
												.html(
														formatDollar(json.total_istallments)
																+ "<b> PKR</b>");
										$("#down_payments")
												.html(
														formatDollar(json.down_payments)
																+ "<b> PKR</b>");
										$("#total_amount").html(
												formatDollar(json.total_amount)
														+ "<b> PKR</b>");
										$("#total_net_income")
												.html(
														formatDollar(parseInt(json.total_amount)
																- parseInt(json.total_commission))
																+ "<b> PKR</b>");

										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");

										$("#installments").html(
												(json.installmentCounts));
										$("#downPayments").html(
												(json.downPaymentCounts));

										console
												.log('filter() after alteration');
										console.log(json);
										console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "date",
									"orderable" : false
								}, {
									"data" : "district_name",
									"orderable" : false
								}, {
									"data" : "customer_name",
									"orderable" : false
								}, {
									"data" : "imei_number",
									"orderable" : false
								}, {
									"data" : "installment_paid",
									"orderable" : false
								}, {
									"data" : "transaction_id",
									"orderable" : false
								}, {
									"data" : "bank_name",
									"orderable" : false
								}, {
									"data" : "fo_name",
									"orderable" : false
								}, {
									"data" : "fo_commission_per_device",
									"orderable" : false
								}, {
									"data" : "nd_name",
									"orderable" : false
								}, {
									"data" : "nd_commission_per_device",
									"orderable" : false
								}, {
									"data" : "payment_status",
									"orderable" : false
								} ]
							});
		}

		$(function() {

			// 	Begin iCheck for paymentfilters
			$('[name="paymentStatus"]').each(function() {
				var id = $(this).attr('id')

				if (id == "installments_rb") {
					radioClass = 'icheckbox_square-green'
				}

				if (id == "downpayments_rb") {
					radioClass = 'icheckbox_square-red'
				}

				$(this).iCheck({
					radioClass : radioClass
				})
			})

			$('[name="paymentStatus"]').on('ifChecked', function() {

				// 		Installments
				if ($(this).attr('id') === 'installments_rb') {
					installments();
				}
				// 		downpayments
				else if ($(this).attr('id') === 'downpayments_rb') {
					downPayments();
				} else {
					filter();
				}
			})
			// 	End iCheck for paymentfilters

			// uncheck iCheck for paymentfilters
			$('[name="paymentStatus"]').on('ifClicked', function() {
				if ($(this).is(":checked")) {
					$(this).iCheck('uncheck');

					filter(); //will call after unckeck any checkbox
				}
			})
			// end iCheck for paymentfilters
		});

		function installments() {

			var table = $('#fiance_table').DataTable();

			table.clear();
			table.destroy();
			var to = document.getElementById("to").value;
			var from = document.getElementById("from").value;
			if (to == null || to === '') {

				var currentDt = new Date();
				var mm = currentDt.getMonth() + 1;
				mm = (mm < 10) ? '0' + mm : mm;
				var dd = ((currentDt.getDate()) >= 10) ? (currentDt.getDate())
						: '0' + (currentDt.getDate());
				var yyyy = currentDt.getFullYear();
				var date = yyyy + '-' + mm + '-' + dd;
				to = date;
				from = date;
			}

			$("#fiance_table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],

								"ajax" : {
									"url" : "FinanceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "getinstallments",
												d.to = to, d.from = from,
												d.paymentStatus = 0
									},
									"dataSrc" : function(json) {

										$
												.each(
														json.data,
														function(e) {
															console
																	.log(json.data[e].installment_paid);
															if (json.data[e].installment_paid) {
																json.data[e].installment_paid = formatDollar(json.data[e].installment_paid)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].fo_commission_per_device) {
																json.data[e].fo_commission_per_device = formatDollar(json.data[e].fo_commission_per_device)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].nd_commission_per_device) {
																json.data[e].nd_commission_per_device = formatDollar(json.data[e].nd_commission_per_device)
																		+ "<b> PKR </b>"
															}
														});

										$('#curdate').html(json.curdate);
										$("#total_istallments")
												.html(
														formatDollar(json.total_istallments)
																+ "<b> PKR</b>");
										$("#down_payments")
												.html(
														formatDollar(json.down_payments)
																+ "<b> PKR</b>");
										$("#total_amount").html(
												formatDollar(json.total_amount)
														+ "<b> PKR</b>");
										$("#total_net_income")
												.html(
														formatDollar(parseInt(json.total_amount)
																- parseInt(json.total_commission))
																+ "<b> PKR</b>");

										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");
										$("#installments").html(
												(json.installmentCounts));
										$("#downPayments").html(
												(json.downPaymentCounts));
										console
												.log('Installments after alteration');
										console.log(json);
										//console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "date",
									"orderable" : false
								}, {
									"data" : "district_name",
									"orderable" : false
								}, {
									"data" : "customer_name",
									"orderable" : false
								}, {
									"data" : "imei_number",
									"orderable" : false
								}, {
									"data" : "installment_paid",
									"orderable" : false
								}, {
									"data" : "transaction_id",
									"orderable" : false
								}, {
									"data" : "bank_name",
									"orderable" : false
								}, {
									"data" : "fo_name",
									"orderable" : false
								}, {
									"data" : "fo_commission_per_device",
									"orderable" : false
								}, {
									"data" : "nd_name",
									"orderable" : false
								}, {
									"data" : "nd_commission_per_device",
									"orderable" : false
								}, {
									"data" : "payment_status",
									"orderable" : false
								} ]
							});

		}

		function downPayments() {

			var table = $('#fiance_table').DataTable();
			table.clear();
			table.destroy();
			var to = document.getElementById("to").value;
			var from = document.getElementById("from").value;
			if (to == null || to === '') {

				var currentDt = new Date();
				var mm = currentDt.getMonth() + 1;
				mm = (mm < 10) ? '0' + mm : mm;
				var dd = ((currentDt.getDate()) >= 10) ? (currentDt.getDate())
						: '0' + (currentDt.getDate());
				var yyyy = currentDt.getFullYear();
				var date = yyyy + '-' + mm + '-' + dd;
				to = date;
				from = date;
			}
			$("#fiance_table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],

								"ajax" : {
									"url" : "FinanceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "getdownpayments",
												d.to = to, d.from = from,
												d.paymentStatus = 1
									},
									"dataSrc" : function(json) {

										$
												.each(
														json.data,
														function(e) {

															if (json.data[e].installment_paid) {
																json.data[e].installment_paid = formatDollar(json.data[e].installment_paid)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].fo_commission_per_device) {
																json.data[e].fo_commission_per_device = formatDollar(json.data[e].fo_commission_per_device)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].nd_commission_per_device) {
																json.data[e].nd_commission_per_device = formatDollar(json.data[e].nd_commission_per_device)
																		+ "<b> PKR </b>"
															}
														});

										$('#curdate').html(json.curdate);
										$("#total_istallments")
												.html(
														formatDollar(json.total_istallments)
																+ "<b> PKR</b>");
										$("#down_payments")
												.html(
														formatDollar(json.down_payments)
																+ "<b> PKR</b>");
										$("#total_amount").html(
												formatDollar(json.total_amount)
														+ "<b> PKR</b>");
										$("#total_net_income")
												.html(
														formatDollar(parseInt(json.total_amount)
																- parseInt(json.total_commission))
																+ "<b> PKR</b>");

										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");
										$("#installments").html(
												(json.installmentCounts));
										$("#downPayments").html(
												(json.downPaymentCounts));
										console
												.log('DownPayments after alteration');
										console.log(json);
										//console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "date",
									"orderable" : false
								}, {
									"data" : "district_name",
									"orderable" : false
								}, {
									"data" : "customer_name",
									"orderable" : false
								}, {
									"data" : "imei_number",
									"orderable" : false
								}, {
									"data" : "installment_paid",
									"orderable" : false
								}, {
									"data" : "transaction_id",
									"orderable" : false
								}, {
									"data" : "bank_name",
									"orderable" : false
								}, {
									"data" : "fo_name",
									"orderable" : false
								}, {
									"data" : "fo_commission_per_device",
									"orderable" : false
								}, {
									"data" : "nd_name",
									"orderable" : false
								}, {
									"data" : "nd_commission_per_device",
									"orderable" : false
								}, {
									"data" : "payment_status",
									"orderable" : false
								} ]
							});
		}

		function table_load() {

			var currentDt = new Date();
			var mm = currentDt.getMonth() + 1;
			mm = (mm < 10) ? '0' + mm : mm;
			var dd = ((currentDt.getDate()) >= 10) ? (currentDt.getDate())
					: '0' + (currentDt.getDate());
			var yyyy = currentDt.getFullYear();
			var date = yyyy + '-' + mm + '-' + dd;
			var to = date;
			var from = date;

			$("#fiance_table")
					.dataTable(
							{
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "FinanceController",
									"type" : "get",
									"data" : function(d) {
										d.action = "getpaymentorderby",
												d.to = to, d.from = from
									},
									"dataSrc" : function(json) {
										console
												.log('**********Table load*******');

										console.log(json);
										console.log(json.data);

										$
												.each(
														json.data,
														function(e) {
															console
																	.log(json.data[e].installment_paid);
															if (json.data[e].installment_paid) {
																json.data[e].installment_paid = formatDollar(json.data[e].installment_paid)
																		+ "<b> PKR </b>"
															}

															if (json.data[e].fo_commission_per_device) {
																json.data[e].fo_commission_per_device = formatDollar(json.data[e].fo_commission_per_device)
																		+ "<b> PKR </b>"
															}
															if (json.data[e].nd_commission_per_device) {
																json.data[e].nd_commission_per_device = formatDollar(json.data[e].nd_commission_per_device)
																		+ "<b> PKR </b>"
															}
														});

										$('#curdate').html(json.curdate);
										$("#total_istallments")
												.html(
														formatDollar(json.total_istallments)
																+ "<b> PKR</b>");
										$("#down_payments")
												.html(
														formatDollar(json.down_payments)
																+ "<b> PKR</b>");
										$("#total_amount").html(
												formatDollar(json.total_amount)
														+ "<b> PKR</b>");
										$("#total_net_income")
												.html(
														formatDollar(parseInt(json.total_amount)
																- parseInt(json.total_commission))
																+ "<b> PKR</b>");

										$("#total_fo_amount")
												.html(
														formatDollar(json.fo_commission)
																+ "<b> PKR</b>");
										$("#total_nd_amount")
												.html(
														formatDollar(json.nd_commission)
																+ "<b> PKR</b>");
										$("#total_commission")
												.html(
														formatDollar(json.total_commission)
																+ "<b> PKR</b>");
										$("#installments").html(
												(json.installmentCounts));
										$("#downPayments").html(
												(json.downPaymentCounts));

										console
												.log('table_load() after alteration');
										console.log(json);
										//console.log(json.data);
										return json.data;
									},
								},
								"columns" : [ {
									"data" : "date",
									"orderable" : false
								},

								{
									"data" : "district_name",
									"orderable" : false
								}, {
									"data" : "customer_name",
									"orderable" : false
								}, {
									"data" : "imei_number",
									"orderable" : false
								}, {
									"data" : "installment_paid",
									"orderable" : false
								}, {
									"data" : "transaction_id",
									"orderable" : false
								}, {
									"data" : "bank_name",
									"orderable" : false
								}, {
									"data" : "fo_name",
									"orderable" : false
								}, {
									"data" : "fo_commission_per_device",
									"orderable" : false
								}, {
									"data" : "nd_name",
									"orderable" : false
								}, {
									"data" : "nd_commission_per_device",
									"orderable" : false
								}, {
									"data" : "payment_status",
									"orderable" : false
								} ]
							});
		}
	</script>

	<script>
		function valueChecker() {
			$("#content").on("change", "#to, #from", function() {
				var toValue = $("#to").val();
				var fromValue = $("#from").val();
				if (toValue != "" && fromValue != "") {
					$('#filterid').css("background-color", "Blue");
					$('#filterid').prop("disabled", false);
				}
			});
			$('#filterid').prop("disabled", true);
		}
	</script>

	<script>
		$(document).ready(function() {
			App.init();
			valueChecker();
			table_load();
		});
	</script>

</body>


</html>
