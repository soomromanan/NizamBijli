<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
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

<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->

<!-- ==================BEGIN USER DEFINED STYLE===================== -->
<link href="assets/css/mystyle.css" rel="stylesheet">
<!-- ==================END USER DEFINED STYLE===================== -->

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link href="assets/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />

<!-- ================== END BASE CSS STYLE ================== -->

<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />

</head>
<body>
	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("shsLogin.jsp");
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
					<li class="has-sub active"><a href="Appliances"> <i
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
				Appliances <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 2%">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" id="appliancePanel">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Appliances</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px;">

								<span id="basic_filter">

									<ul
										class="nav nav-pills f-s-15 inline bordered round-corner m-r-5">

										<li class=""><a class="m-0 p-5 m-r-15" id="active_rb_li">
												<input type="radio" name="appliance_status_rb"
												id="active_rb" value="1"> <span class="label"
												style="background-color: #16a085; color: white; font-weight: bold;"></span>
												<label for="active_rb">Active</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15"
											id="inactive_rb_li"> <input type="radio"
												name="appliance_status_rb" id="inactive_rb" value="0">
												<span class="label"
												style="background-color: red; color: white; font-weight: bold;"></span>
												<label for="inactive_rb">Inactive</label>
										</a></li>
									</ul>


									<ul class="nav nav-pills f-s-15 inline bordered round-corner">
										<li class=""><a class="m-0 p-5 m-r-15" id="dumb_cb_li">
												<input type="checkbox" name="health_status_cb" id="dumb_cb"
												value="0"> <span class="label label-default"></span>
												<label for="dumb_cb">Dumb</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="dead_cb_li">
												<input type="checkbox" name="health_status_cb" id="dead_cb"
												value="1"> <span class="label bg-purple"></span> <label
												for="dead_cb">DEAD</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="dead_since_cb_li"> <input type="checkbox"
												name="health_status_cb" id="dead_since_cb" value="2">
												<span class="label"
												style="background-color: #7f8c8d; color: white; font-weight: bold;"></span>
												<label for="dead_since_cb">DEAD Since</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15" id="alive_cb_li">
												<input type="checkbox" name="health_status_cb" id="alive_cb"
												value="3"> <span class="label"
												style="background-color: #42a4f4; color: white; font-weight: bold;"></span>
												<label for="alive_cb">ALIVE</label>
										</a></li>




									</ul>




									<ul class="nav nav-pills f-s-15 inline bordered round-corner">
										<li class=""><a class="m-0 p-5 m-r-15" id="pending_cb_li">
												<input type="checkbox" name="sold_status_cb" id="pending_cb"
												value="0"> <span class="label label-default"></span>
												<label for="pending_cb">Pending</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="rta_cb_li">
												<input type="checkbox" name="sold_status_cb" id="rta_cb"
												value="1"> <span class="label bg-purple"></span> <label
												for="rta_cb">Ready to assign</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15" id="afd_cb_li">
												<input type="checkbox" name="sold_status_cb" id="afd_cb"
												value="5"> <span class="label"
												style="background-color: #7f8c8d; color: white; font-weight: bold;"></span>
												<label for="afd_cb" title="Awaiting For Downpayment">ADP</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15" id="rdp_cb_li">
												<input type="checkbox" name="sold_status_cb" id="rdp_cb"
												value="2"> <span class="label"
												style="background-color: #42a4f4; color: white; font-weight: bold;"></span>
												<label for="rdp_cb">RDP</label>
										</a></li>
										<li class=""><a class="m-0 p-5 m-r-15"
											id="installed_ns_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="installed_ns_cb" value="7">
												<span class="label bg-green"></span> <label
												for="installed_ns_cb">Installed-NS</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="installed_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="installed_cb" value="6"> <span
												class="label bg-green"></span> <label for="installed_cb">Installed</label>
										</a></li>

										<li class=""><a class="m-0 p-5 m-r-15"
											id="returned_cb_li"> <input type="checkbox"
												name="sold_status_cb" id="returned_cb" value="4"> <span
												class="label"
												style="background-color: #d35400; color: white; font-weight: bold;"></span>
												<label for="returned_cb">Returned</label>
										</a></li>



									</ul>


								</span>

								<div id="msgid" style="padding-top: 12px">

									<table id="appliance-table"
										class="table table-hover table-striped">
										<thead>
											<tr>
												<th>Name</th>
												<th>IMEI</th>
												<th>Charging Status</th>
												<th>District</th>
												<th>Customer Name</th>
												<th>Nizam Dost</th>
												<th>Field Officer</th>
												<th>Transferred Status</th>
												<th>Health Check</th>
												<th>Options</th>
											</tr>
										</thead>

									</table>
								</div>
							</div>
						</div>
						<!-- end panel -->
					</div>
					<!-- end col-10 -->
				</div>
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->



		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"> <i class="fa fa-angle-up"></i>
		</a>
		<!-- end scroll to top btn -->


		<!-- footer start -->

		<!-- footer end -->

		<%
			}
		%>

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

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/dashboard-v2.min.js"></script>
	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script type="text/javascript" src="assets/js/myscript.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>

	<script src="assets/js/chart-morris.demo.minAppliance.js"></script>

	<script src="assets/plugins/icheck/icheck.min.js"></script>


	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- appliance table async process -->


	<script>
		var table;
		$(function() {
			$.ajax({
				url : "ApplianceController",
				type : 'POST',
				dataType : 'json',
				data : {
					action : "countApplianceAndSoldStatus"
				},
				success : function(data) {
					console.log("All Count")
					console.log(data)

					if (data.active != undefined && data.active != 0) {
						$('#active_rb_li span').text(data.active)
					} else {
						$('#active_rb_li span').hide();
					}

					// 					InActive
					if (data.inactive != undefined && data.inactive != 0) {
						$('#inactive_rb_li span').text(data.inactive)
					} else {
						$('#inactive_rb_li span').hide();
					}
					if (data.pending != undefined && data.pending != 0) {
						$('#pending_cb_li span').text(data.pending)
					} else {
						$('#pending_cb_li span').hide();
					}

					// 					Ready To Assign
					if (data.readyToAssign != undefined
							&& data.readyToAssign != 0) {
						$('#rta_cb_li span').text(data.readyToAssign)
					} else {
						$('#rta_cb_li span').hide();
					}
					if (data.returned != undefined && data.returned != 0) {
						$('#returned_cb_li span').text(data.returned)
					} else {
						$('#returned_cb_li span').hide();
					}
					if (data.awaitingForDownpayment != undefined
							&& data.awaitingForDownpayment != 0) {
						$('#afd_cb_li span').text(data.awaitingForDownpayment)
					} else {
						$('#afd_cb_li span').hide();
					}
					if (data.installed != undefined && data.installed != 0) {
						$('#installed_cb_li span').text(data.installed)
					} else {
						$('#installed_cb_li span').hide();
					}
					if (data.installedNoSignal != undefined
							&& data.installedNoSignal != 0) {
						$('#installed_ns_cb_li span').text(
								data.installedNoSignal)
					} else {
						$('#installed_ns_cb_li span').hide();
					}
					if (data.rdp != undefined && data.rdp != 0) {
						$('#rdp_cb_li span').text(data.rdp)
					} else {
						$('#rdp_cb_li span').hide();
					}
					if (data.dumb != undefined && data.dumb != 0) {
						$('#dumb_cb_li span').text(data.dumb)
					} else {
						$('#dumb_cb_li span').hide();
					}

					if (data.dead != undefined && data.dead != 0) {
						$('#dead_cb_li span').text(data.dead)
					} else {
						$('#dead_cb_li span').hide();
					}
					if (data.dead_since != undefined && data.dead_since != 0) {
						$('#dead_since_cb_li span').text(data.dead_since)
					} else {
						$('#dead_since_cb_li span').hide();
					}
					if (data.alive != undefined && data.alive != 0) {
						$('#alive_cb_li span').text(data.alive)
					} else {
						$('#alive_cb_li span').hide();

					}

				}

			})
			$(
					'[name="appliance_status_rb"], [name="sold_status_cb"],[name="health_status_cb"] ')
					.each(function() {
						var id = $(this).attr('id');
						var inputClass
						if (id == 'active_rb') {
							inputClass = 'icheckbox_square-blue'
						} else if (id == 'inactive_rb') {
							inputClass = 'icheckbox_square-red'
						} else if (id == 'pending_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'rta_cb') {
							inputClass = 'icheckbox_square-purple'
						} else if (id == 'installed_cb') {
							inputClass = 'icheckbox_square-green'
						} else if (id == 'handovered_cb') {
							inputClass = 'icheckbox_square-orange'
						} else if (id == 'afd_cb') {
							inputClass = 'icheckbox_square-yellow'
						} else if (id == 'returned_cb') {
							inputClass = 'icheckbox_square-orange'
						} else if (id == 'rdp_cb') {
							inputClass = 'icheckbox_square-blue'
						} else if (id == 'installed_ns_cb') {
							inputClass = 'icheckbox_square-green'
						} else if (id == 'dumb_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'dead_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'dead_since_cb') {
							inputClass = 'icheckbox_square-grey'
						} else if (id == 'alive_cb') {
							inputClass = 'icheckbox_square-blue'
						}

						$(this).iCheck({
							radioClass : inputClass,
							checkboxClass : inputClass
						})

					})
			var applianceStatus = -1;
			$('[name="appliance_status_rb"]').on('ifClicked', function() {
				if ($(this).is(":checked")) {
					$(this).iCheck('uncheck')
					applianceStatus = -1;
				} else {
					applianceStatus = $(this).val()
				}
				quickFilters()
			})
			var soldStatus = [];
			$('[name="sold_status_cb"]').on('ifChanged', function() {
				if ($.inArray($(this).val(), soldStatus) != -1) {
					soldStatus.splice($.inArray($(this).val(), soldStatus), 1)
				} else {
					soldStatus.push($(this).val())
				}
				quickFilters()

			})

			var healthStatus = [];
			$('[name="health_status_cb"]').on(
					'ifChanged',
					function() {
						if ($.inArray($(this).val(), healthStatus) != -1) {
							healthStatus.splice($.inArray($(this).val(),
									healthStatus), 1)
						} else {
							healthStatus.push($(this).val())
						}
						quickFilters()
					})
			// 			Begin Quick Filters
			function quickFilters() {
				table.clear()
				$('#appliance-table')
						.DataTable(
								{
									dom : "lftipr",
									destroy : true,
									"processing" : true,
									"serverSide" : true,
									"order" : [ [ 0, "asc" ] ],
									"ajax" : {
										"url" : "QuickFilterController",
										"type" : "post",
										"data" : function(d) {
											d.action = "getApplianceByQuickFilters"
											d.applianceStatus = applianceStatus
											d.soldStatus = soldStatus
											d.healthStatus = healthStatus
										},
										"dataSrc" : function(json) {
											$('.page-header span').text(
													json.applianceCount)
											for ( var i = 0; i < json.data.length; i++) {
												if (json.data[i].imeiNumber == undefined
														|| json.data[i].imeiNumber == null
														|| json.data[i].imeiNumber == ' ') {
													json.data[i].imeiNumber = "Not Assigned";
												}
												if (json.data[i].applianceStatusActive == 0) {
													json.data[i].applianceStatusActive = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
												} else if (json.data[i].applianceStatusActive == 1) {
													json.data[i].applianceStatusActive = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
												}
												if (json.data[i].applianceName == "60 W"
														|| json.data[i].applianceName == "P-60"
														|| json.data[i].applianceName == "7 W") {

													if (json.data[i].applianceStatus == 0) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
													} else if (json.data[i].applianceStatus == 1) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
													} else if (json.data[i].applianceStatus == 2) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">RDP</span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'

													} else if (json.data[i].applianceStatus == 3) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#7f8c8d; color:white;font-weight: bold;">Owner</span>'
													} else if (json.data[i].applianceStatus == 4) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
													} else if (json.data[i].applianceStatus == 5) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
													} else if (json.data[i].applianceStatus == 6) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
														json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
													}
												} else {
													if (json.data[i].applianceStatus == 0) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
													} else if (json.data[i].applianceStatus == 1) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
													} else if (json.data[i].applianceStatus == 2) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">RDP</span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'

													} else if (json.data[i].applianceStatus == 3) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#7f8c8d; color:white;font-weight: bold;">Owner</span>'
													} else if (json.data[i].applianceStatus == 4) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
													} else if (json.data[i].applianceStatus == 5) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
													} else if (json.data[i].applianceStatus == 6) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
														if (json.data[i].healthStatus == 3) {
															json.data[i].islive = '<span class="label"  style="background-color:#2980b9; color:white;font-weight: bold;">ALIVE</span>'
														}
														if (json.data[i].healthStatus == 2) {
															if (json.data[i].deadSince == 0) {
																json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Today</span>'

															} else if (json.data[i].deadSince == 1) {

																json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Yesterday</span>'
															} else {

																json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since '
																		+ json.data[i].deadSince
																		+ ' days</span>'
															}

														}

													} else if (json.data[i].applianceStatus == 7) {
														json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed and NoSignal</span>'
														json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD</span>'
													}

												}

												json.data[i].add = '<a href="UpdateCustomerForm?customerid='
														+ json.data[i].customerid
														+ '&id='
														+ json.data[i].applianceId
														+ '"><span class="btn btn-info btn-xs">Add/Update <i class="fa fa-plus"></i></span></a>';

											}
											return json.data;
										},
									},

									"columns" : [ {
										"data" : "applianceName"
									}, {
										"data" : "imeiNumber",
										"orderable" : false,
									}, {
										"data" : "applianceStatusActive",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "salesmanDistrict"
									}, {
										"data" : "customerName"
									},

									{
										"data" : "salesmanName"
									}, {
										"data" : "foName"
									}, {
										"data" : "applianceStatus",
										"orderable" : false,
										"searchable" : false
									}, {
										"data" : "islive",
										"orderable" : false,
										"searchable" : false

									}, {
										"data" : "add",
										"orderable" : false
									} ],

									"rowCallback" : function(row, data) {
										$(row).data(
												'link',
												'ViewServlet?click=viewAppliance&id='
														+ data.applianceId)
										$(row).addClass('link-not-first-child')
									},

									"columnsDefs" : [ {
										"targets" : 0,
										"orderable" : false
									}, {
										"targets" : 3,
										"orderable" : false
									}, {
										"targets" : 7,
										"orderable" : false
									} ]

								});

			}
			table = $('#appliance-table')
					.DataTable(
							{
								dom : "lftirps",
								"destroy" : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "ApplianceController",
									"type" : "post",
									"data" : function(d) {
										d.action = "getApplianceTable"
									},

									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.applianceCount)

										for ( var i = 0; i < json.data.length; i++) {
											if (json.data[i].imeiNumber == undefined
													|| json.data[i].imeiNumber == null
													|| json.data[i].imeiNumber == ' ') {
												json.data[i].imeiNumber = "Not Assigned";
											}

											if (json.data[i].applianceStatusActive == 0) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
											} else if (json.data[i].applianceStatusActive == 1) {
												json.data[i].applianceStatusActive = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
											}

											if (json.data[i].applianceName == "60 W"
													|| json.data[i].applianceName == "P-60"
													|| json.data[i].applianceName == "7 W") {

												if (json.data[i].applianceStatus == 0) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
												} else if (json.data[i].applianceStatus == 1) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
												} else if (json.data[i].applianceStatus == 2) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">RDP</span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'

												} else if (json.data[i].applianceStatus == 4) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
												} else if (json.data[i].applianceStatus == 5) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
												} else if (json.data[i].applianceStatus == 6) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
													json.data[i].islive = '<span class="label" style="background-color:black; color:white;font-weight: bold;">DUMB</span>'
												}

											} else {

												if (json.data[i].applianceStatus == 0) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 1) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#9b59b6; color:white;font-weight: bold;">Ready To Assign</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 2) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">RDP</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'

												} else if (json.data[i].applianceStatus == 4) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 5) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment </span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">N/A</span>'
												} else if (json.data[i].applianceStatus == 6) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed </span>'
													if (json.data[i].healthStatus == 3) {
														json.data[i].islive = '<span class="label"  style="background-color:#2980b9; color:white;font-weight: bold;">ALIVE</span>'
													}
													if (json.data[i].healthStatus == 2) {
														if (json.data[i].deadSince == 0) {
															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Today</span>'

														} else if (json.data[i].deadSince == 1) {

															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since Yesterday</span>'
														} else {

															json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD Since '
																	+ json.data[i].deadSince
																	+ ' days</span>'
														}

													}

												} else if (json.data[i].applianceStatus == 7) {
													json.data[i].applianceStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed and NoSignal</span>'
													json.data[i].islive = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">DEAD</span>'
												}

											}

											json.data[i].add = '<a href="UpdateCustomerForm?customerid='
													+ json.data[i].customerid
													+ '&id='
													+ json.data[i].applianceId
													+ '"><span class="btn btn-info btn-xs">Add/Update <i class="fa fa-plus"></i></span></a>';

										}

										return json.data;
									},
								},

								"columns" : [ {
									"data" : "applianceName"
								}, {
									"data" : "imeiNumber",
									"orderable" : false
								}, {
									"data" : "applianceStatusActive",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "salesmanDistrict"
								}, {
									"data" : "customerName"
								}, {
									"data" : "salesmanName"
								}, {
									"data" : "foName"
								}, {
									"data" : "applianceStatus",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "islive",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "add",
									"orderable" : false
								} ],

								"rowCallback" : function(row, data) {
									$(row).data(
											'link',
											'ViewServlet?click=viewAppliance&id='
													+ data.applianceId)

									$(row).addClass('link-not-first-child')
								},

								"columnsDefs" : [ {
									"targets" : 0,
									"orderable" : false
								}, {
									"targets" : 3,
									"orderable" : false
								}, {
									"targets" : 7,
									"orderable" : false
								} ]
							});

			$("#appliance-table tbody").on('click',
					'tr.link-not-first-child>td:not(:first-child)',
					function(event) {
						link = $(this).parent().data('link');
						if (link != undefined) {
							window.location = link;
						}
					});
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			App.init();
		})
	</script>

</body>


</html>