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

<link href="assets/css/mystyle.css" rel="stylesheet">

<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />
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
							<li ><a href="FieldOfficer">Field Officer</a></li>
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

					<li class="has-sub "><a href="javascript:;"> <b
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
				Loan Books<span id="loanbooks"
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">
				<!-- begin col-12 -->
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
							<h4 class="panel-title">Loan Books Table</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive">
								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li><a class="m-0 p-5 m-r-15" id="maintenance_li"> <input
											type="radio" name="filter" id="maintenance_rb"> <span
											class="label"
											style="background-color: blue; color: white; font-weight: bold;">
												<%-- <%= paymentList.get(0).getMaintained() %> --%>
										</span> <label for="maintenance_rb">Maintained</label>
									</a></li>
									<li><a class="m-0 p-5 m-r-15" id="late_li"> <input
											type="radio" name="filter" id="super_late_rb"> <span
											class="label"
											style="background-color: Red; color: white; font-weight: bold;">
										</span> <label for="super_late_rb">Late</label>
									</a></li>
									<li><a class="m-0 p-5 m-r-15" id="defaulter_li"> <input
											type="radio" id="defaulter_rb" name="filter"> <span
											class="label"
											style="background-color: #34495e; color: white; font-weight: bold;">
												<%-- <%=paymentList.get(0).getDefaulted() %> --%>
										</span> <label for="defaulter_rb">Defaulter</label>
									</a></li>

									<li><a class="m-0 p-5 m-r-15" id="owned_li"> <input
											type="radio" id="owned_rb" name="filter"> <span
											class="label"
											style="background-color: #7f8c8d; color: black; font-weight: bold;">
												<%-- <%=paymentList.get(0).getOwned() %> --%>
										</span> <label for="owned_rb">Owned</label>
									</a></li>
									
									<li><a class="m-0 p-5 m-r-15" id="cash_li"> <input
											type="radio" id="cash_rb" name="filter"> <span
											class="label"
											style="background-color: #7f8c8d; color: black; font-weight: bold;">
												<%-- <%=paymentList.get(0).getOwned() %> --%>
										</span> <label for="cash_rb">Cash Sales</label>
									</a></li>
									<li><a class="m-0 p-5 m-r-15"> <input type="radio"
											id="all_rb" name="filter"> <label for="all_rb">All</label>
									</a></li>
								</ul>
								<div class="table-responsive" style="font-size: 13px">
									<div id="msgid" style="padding-top: 12px">

										<table id="super_admin_loan_table"
											class="table table-hover table-striped">
											<thead>
												<tr>
													<th>Remaining Days</th>
													<th>Customer Name</th>
													<th>Credit Rating</th>
													<th>ND Name</th>
													<th>FO Name</th>
													<th>DO Name</th>
													<th>Appliance IMEI</th>
													<th>City</th>
													<th>Loan Outstanding</th>
													<th>Loan Due</th>
													<th>Paid Amount</th>
													<th>Remaining Installments</th>
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
	<!-- 			 <script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script> -->
	<!-- ================== END BASE JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>

	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>

	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<!-- 			 <script src="assets/js/chart-js.demo.min.js"></script> -->
	<script src="assets/js/apps.min.js"></script>
	<script src="assets/plugins/icheck/icheck.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		var filter;
		var action;

		function filterLoanBook() {

			$('#super_admin_loan_table')
					.dataTable(
							{
								destroy : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "SuperAdminLoanBookController",
									"type" : "post",
									"data" : function(d) {

										d.action = action

										if (action === undefined) {
											d.action = "getLoanBookTable"
										}

										if (filter != undefined) {
											d.filter = filter
										}
									},

									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.loanBooksCount)
										console.log("+++++++++++++++");
										console.log(json);

										for ( var i = 0; i < json.data.length; i++) {

											console
													.log("--------------- +++++ ------------")
											if (json.data[i].applianceNumber == undefined
													|| json.data[i].applianceNumber == null
													|| json.data[i].applianceNumber == ' ') {
												json.data[i].applianceNumber = "Not Assigned";
											}
											console.log(json.data[i].statusGet)
											var days = json.data[i].remaining_days;
											if (json.data[i].statusGet == "maintend") {
												json.data[i].statusGet = '<span class="label" style="background-color:blue; color:white;font-weight: bold;">Maintained</span>'
											} else if (json.data[i].statusGet == "owned") {
												json.data[i].statusGet = '<span class="label" style="background-color:#7f8c8d; color:black;font-weight: bold;">Owned</span>'
											} else if (json.data[i].statusGet == "late") {
												json.data[i].statusGet = '<span class="label" style="background-color:Red; color:black;font-weight: bold;">Late</span>'
											} else if (json.data[i].statusGet == "defaulter") {
												json.data[i].statusGet = '<span class="label" style="background-color:#34495e; color:white;font-weight: bold;">Defaulter</span>'
											}else if (json.data[i].statusGet == "cash") {
												json.data[i].statusGet = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Cash</span>'
											}

											if (json.data[i].remaining_days >= -10
													&& json.data[i].remaining_days <= -1) {
												json.data[i].remaining_days = '<span style="background-color:Red;color:white">'
														+ days + '</span>';
											} else if (json.data[i].remaining_days == 0) {
												json.data[i].remaining_days = '<span style="background-color:#ecf0f1;color:white">'
														+ days + '</span>'
											} else if (json.data[i].remaining_days >= 1
													&& json.data[i].remaining_days <= 4) {
												json.data[i].remaining_days = '<span style="background-color:#1abc9c;color:white">'
														+ days + '</span>'
											} else if (json.data[i].remaining_days >= 5
													&& json.data[i].remaining_days <= 10) {
												json.data[i].remaining_days = '<span style="background-color:#f1c40f;color:white">'
														+ days + '</span>'
											} else if (json.data[i].remaining_days >= 11
													&& json.data[i].remaining_days <= 29) {
												json.data[i].remaining_days = '<span style="background-color:#8e44ad;color:white">'
														+ days + '</span>'
											} else if (json.data[i].remaining_days <= -10) {
												json.data[i].remaining_days = '<span style="background-color:#34495e;color:white">'
														+ days + '</span>'
											} else if (json.data[i].remaining_days >= 30) {
												json.data[i].remaining_days = '<span style="background-color:Green;color:white">'
														+ days + '</span>'
											} else {
												json.data[i].remaining_days = '<span style="background-color:#ff7f00;color:black">'
														+ days + '</span>'
											}

											if (json.data[i].remainingBalance) {
												json.data[i].remainingBalance = json.data[i].remainingBalance
														+ "<b> PKR</b>";
											}
											if (json.data[i].monthlyPay) {
												json.data[i].monthlyPay = json.data[i].monthlyPay
														+ "<b> PKR</b>";
											}
											if (json.data[i].totalPaid) {
												json.data[i].totalPaid = json.data[i].totalPaid
														+ "<b> PKR</b>";
											}

										}

										return json.data;
									}

								},

								"columns" : [ {
									"data" : "remaining_days"
								}, {
									"data" : "customerName"
								}, {
									"data" : "customer_rating"
								}, {
									"data" : "NdName"
								}, {
									"data" : "foName"
								}, {
									"data" : "doName"
								}, {
									"data" : "applianceNumber",
									"orderable" : false
								}, {
									"data" : "cityName"
								},

								{
									"data" : "remainingBalance"
								},

								{
									"data" : "monthlyPay"
								}, {
									"data" : "totalPaid"
								},

								{
									"data" : "installmentScheme"
								}, {
									"data" : "statusGet",
									"orderable" : false,
									"searchable" : false
								}

								],

								"rowCallback" : function(row, data) {
									//			if($.inArray(data.DT_RowId, selected) !== -1){
									$(row).data(
											'link',
											'SuperAdminLoanBookController?appliace_key='
													+ data.applianceId);
									$(row).addClass('link');
									//			}
								},

							});

			$("#super_admin_loan_table tbody").on('click', 'tr.link>td',
					function(event) {
						link = $(this).parent().data('link');
						if (link != undefined) {
							window.location = link;
						}
					});

		}

		$(function() {

			// 	Begin iCheck for filters
			$('[name="filter"]').each(function() {
				var id = $(this).attr('id')
				var radioClass;
				if (id == "maintenance_rb") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "defaulter_rb") {
					radioClass = 'icheckbox_square-red'
				}
				if (id == "super_late_rb") {
					radioClass = 'icheckbox_square-red'
				}
				if (id == "owned_rb") {
					radioClass = 'icheckbox_square-orange'
				}
				if (id == "cash_rb") {
					radioClass = 'icheckbox_square-orange'
				}
				if (id == "all_rb") {
					radioClass = 'icheckbox_square-grey'
				}
				$(this).iCheck({
					radioClass : radioClass
				})
			})
			// 	End iCheck for filters

			// Begin Counting of All Filters

			$
					.ajax({
						url : 'SuperAdminLoanBookController',
						method : 'POST',
						data : {
							action : "countFilters"
						},
						dataType : 'json',
						success : function(data) {
							console.log("count filters")
							console.log(data)
							if (data.Maintained !== undefined
									|| data.Maintained !== 0) {
								$('#maintenance_li span').text(data.Maintained)
							}
							if (data.Defaulter !== undefined
									|| data.Defaulter !== 0) {
								$('#defaulter_li span').text(data.Defaulter)
							}
							if (data.Owned !== undefined || data.Owned !== 0) {
								$('#owned_li span').text(data.Owned)
							}
							if (data.Late !== undefined || data.Late !== 0) {
								$('#late_li span').text(data.Late)
							}if (data.cash !== undefined || data.cash !== 0) {
								$('#cash_li span').text(data.cash)
							}

						}

					})

			// End Counting of All Filters
			// Begin On Loan Page initialize DataTable

			// 	End On Loan Page initialize DataTable

			// Begin Quick Filters

			$('[name="filter"]').on('ifChecked', function() {

				// 		All
				if ($(this).attr('id') === 'all_rb') {
					action = "getLoanBookTable"
				}

				// 		Maintained
				else if ($(this).attr('id') === 'maintenance_rb') {
					action = "filterLoanBook"
					filter = "maintained"
				}

				else if ($(this).attr('id') === 'super_late_rb') {
					action = "filterLoanBook"
					filter = "late"
				}
				// 		Defaulter
				else if ($(this).attr('id') === 'defaulter_rb') {
					action = "filterLoanBook"
					filter = "defaulter"
				}
				// 		Owned
				else if ($(this).attr('id') === 'owned_rb') {
					action = "filterLoanBook"
					filter = "owned"
				}else if ($(this).attr('id') === 'cash_rb') {
					action = "filterLoanBook"
						filter = "cash"
					}
				// 		Reset Filter
				else {
					filter = undefined
				}

				filterLoanBook()
			})

			// End Quick Filters
			$('#all_rb').iCheck('check')

			$("#super_admin_loan_table tbody").on('click', 'tr.link>td',
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
			filterLoanBook();
		});
	</script>


</body>


</html>
