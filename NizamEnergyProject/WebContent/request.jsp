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
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />
<!-- ==================END USER DEFINED STYLE===================== -->

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

					<li class=""><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub active"><a href="Request"> <i
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
			<h1 class="page-header">
				Loan Requests<span id="sa_loan_apps_counts"
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<!-- end page-header -->

			<div class="row" style="padding-top: 2%;">
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<h4 class="panel-title">Loan Request Records</h4>
						</div>
						<ul class="nav nav-pills f-s-15 inline bordered round-corner">
							<li><a class="m-0 p-5 m-r-15" id="cc_verify_apps_li"> <input
									type="radio" name="filterApps" id="cc_verify_apps"> <span
									class="label"
									style="background-color: grey; color: white; font-weight: bold;">
								</span> <label for="cc_verify_apps">CC Verification</label>
							</a></li>
							<li><a class="m-0 p-5 m-r-15" id="pending_apps_li"> <input
									type="radio" name="filterApps" id="pending_apps"> <span
									class="label"
									style="background-color: grey; color: white; font-weight: bold;">
								</span> <label for="pending_apps">Pending</label>
							</a></li>

							<li><a class="m-0 p-5 m-r-15" id="accepted_apps_li"> <input
									type="radio" id="accepted_apps" name="filterApps"> <span
									class="label"
									style="background-color: blue; color: white; font-weight: bold;">
								</span> <label for="accepted_apps">Accepted</label>
							</a></li>

							<li><a class="m-0 p-5 m-r-15" id="varified_apps_li"> <input
									type="radio" id="varified_apps" name="filterApps"> <span
									class="label"
									style="background-color: green; color: black; font-weight: bold;">
								</span> <label for="varified_apps">Verified</label>
							</a></li>

							<li><a class="m-0 p-5 m-r-15"> <input type="radio"
									id="all_apps" name="filterApps"> <label for="all_apps">All</label>
							</a></li>
						</ul>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="data-table" class="table table-hover table-striped">
									<thead>
										<tr role="row">
											<th>Requested Date</th>
											<th>Customer</th>
											<th>Appliance</th>
											<th>Monthly Installment</th>
											<th>Installment Scheme</th>
											<th>City, District</th>
											<th>Nizam Dost</th>
											<th>Field Officer</th>
											<th>District Officer</th>
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
	<script type="text/javascript" src="assets/js/myscript.js"></script>

	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/chart-js/chart.js"></script>
	<script type="text/javascript" src="assets/js/notification-chat.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/js/table-manage-default.demo.min.js"></script>

	<script src="assets/plugins/icheck/icheck.js"></script>

	<script src="assets/js/apps.min.js"></script>

	<script src="js/validation.js"></script>
	<script src="assets/js/numeral.js"></script>


	<!-- ================== END PAGE LEVEL JS ================== -->

	<script type="text/javascript">
		var filter;
		var action;
		var text, color;
		function filterSALoanApplications() {
			$('#data-table')
					.DataTable(
							{
								destroy : true,
								"processing" : true,
								"serverSide" : true,

								'ajax' : {
									"type" : "POST",
									"url" : "RequestServlet",
									"data" : function(d) {
										d.action = action
										if (action === undefined) {
											d.action = "getRequest"
										}

										if (filter != undefined) {
											d.filter = filter
										}
									},
									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.sa_loan_apps_counts)
										for ( var i = 0; i <= json.data.lenght; i++) {
											if (json.data[i].instalment) {
												json.data[i].instalment = json.data[i].instalment
														+ "<b> PKR</b>"
											}
										}
										$
												.each(
														json.data,
														function(e) {

															if (json.data[e].status == 0) {
																text = 'Pending';
																color = '#bdc3c7';
																console
																		.log(json.data);
															} else if (json.data[e].status == 1) {
																text = 'Accepted';
																color = '#2980b9';
																console
																		.log(json.data);
															} else if (json.data[e].status == 7) {
																text = 'Verified';
																color = '#2ecc71';

															} else if (json.data[e].status == 4) {
																text = 'To be verify by CC';
																color = '#2ecc71';

															}
															json.data[e].status = '<span class="label" style="background-color:' + color + '; color:white;font-weight: bold;">'
																	+ text
																	+ '</span>';
															json.data[e].cityDist = json.data[e].city_name
																	+ ', '
																	+ json.data[e].district_name;
															json.data[e].installment_scheme = json.data[e].installment_scheme
																	+ ' Months';
															json.data[e].instalment = numeral(
																	json.data[e].instalment)
																	.format(
																			'000,000')
																	+ "<b> PKR</b>";
															var datee = new Date(
																	json.data[e].date);
															console
																	.log('Requested date === '
																			+ json.data[e].date)
															json.data[e].date = (datee
																	.getDate())
																	+ '/'
																	+ (datee
																			.getMonth() + 1)
																	+ '/'
																	+ datee
																			.getFullYear();

														})
										return json.data;
									}
								},
								"columns" : [ {
									"data" : "date"
								}, {
									"data" : "customer_name"
								}, {
									"data" : "appliance_name"
								}, {
									"data" : "instalment"
								}, {
									"data" : "installment_scheme"
								}, {
									"data" : "cityDist"
								}, {
									"data" : "salesman_name"
								}, {
									"data" : "fo_name"
								}, {
									"data" : "user_name"
								}, {
									"data" : "status",
									"orderable" : false,
									"searchable" : false

								} ],
								"rowCallback" : function(row, data) {
									//					if($.inArray(data.DT_RowId, selected) !== -1){

									$(row).data(
											'link',
											'ViewServlet?click=viewRequest&cnic='
													+ data.customer_cnic
													+ '&id=' + data.customer_id
													+ '&appId='
													+ data.eligibility_id);
									$(row).addClass('link')
									//					}
								},

							})
		}

		$("#data-table tbody").on('click', 'tr.link>td', function(event) {
			link = $(this).parent().data('link');
			if (link != undefined) {
				window.location = link;
			}
		});

		$(function() {

			//		 	Begin iCheck for filters
			$('[name="filterApps"]').each(function() {
				var id = $(this).attr('id')
				var radioClass;
				if (id == "cc_verify_apps") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "pending_apps") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "accepted_apps") {
					radioClass = 'icheckbox_square-red'
				}
				if (id == "varified_apps") {
					radioClass = 'icheckbox_square-orange'
				}
				if (id == "all_apps") {
					radioClass = 'icheckbox_square-grey'
				}
				$(this).iCheck({
					radioClass : radioClass
				})
			})
			//		 	End iCheck for filters

			// Begin Counting of All Filters

			$.ajax({
				url : 'RequestServlet',
				method : 'POST',
				data : {
					action : "countSAFilters"
				},
				dataType : 'json',
				success : function(data) {
					console.log("count filters")
					console.log(data)
					if (data.pending !== undefined || data.pending !== 0) {
						$('#pending_apps_li span').text(data.pending)
					}
					if (data.accepted !== undefined || data.accepted !== 0) {
						$('#accepted_apps_li span').text(data.accepted)
					}
					if (data.varified !== undefined || data.varified !== 0) {
						$('#varified_apps_li span').text(data.varified)
					}
					if (data.ccVerification !== undefined
							|| data.ccVerification !== 0) {
						$('#cc_verify_apps_li span').text(data.ccVerification)
					}
				}

			})

			// Begin Quick Filters

			$('[name="filterApps"]').on('ifChecked', function() {
				if ($(this).attr('id') === 'all_apps') {
					action = "getRequest"
				}

				if ($(this).attr('id') === 'pending_apps') {
					action = "filterSALoanApplications"
					filter = "pending"

				} else if ($(this).attr('id') === 'accepted_apps') {
					action = "filterSALoanApplications"
					filter = "accepted"
				} else if ($(this).attr('id') === 'varified_apps') {
					action = "filterSALoanApplications"
					filter = "varified"
				} else if ($(this).attr('id') === 'cc_verify_apps') {
					action = "filterSALoanApplications"
					filter = "ccVerification"
				} else {
					filter = undefined
				}

				filterSALoanApplications()
			})

			// End Quick Filters
			$('#all_apps').iCheck('check')

			$("#data-table tbody").on('click', 'tr.link>td', function(event) {
				link = $(this).parent().data('link');
				if (link != undefined) {
					window.location = link;
				}
			});

		});

		$(document).ready(function() {
			App.init();
			filterSALoanApplications();
		});
	</script>


</body>


</html>
