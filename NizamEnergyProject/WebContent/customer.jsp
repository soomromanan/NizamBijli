<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="bal.CustomerBal"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.bootstrap.min.css"
	rel="stylesheet">
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
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />

<link href="assets/css/mystyle.css" rel="stylesheet">

<style>
span.bigcheck-target {
	font-family: FontAwesome; /* use an icon font for the checkbox */
}

input[type='checkbox'].bigcheck {
	position: relative;
	left: -999em; /* hide the real checkbox */
}

input[type='checkbox'].bigcheck+span.bigcheck-target:after {
	content: "\f096"; /* In fontawesome, is an open square (fa-square-o) */
}

input[type='checkbox'].bigcheck:checked+span.bigcheck-target:after {
	content: "\f046"; /* fontawesome checked box (fa-check-square-o) */
}
</style>

<script>
	function setMoodValue(appliance_id, customer_name) {
		document.getElementById("customer_name").innerHTML = customer_name;
		document.getElementById('updateURL').onclick = function() {
			helloWorld(appliance_id);
		};
	}

	function helloWorld(applianceValue) {
		var link = "RevokeController?click=revoke&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;

	}
</script>


</head>
<body>

	<%
		UserBean userbean = (UserBean) session.getAttribute("email");
		if (userbean == null) {
			response.sendRedirect("SolarHomeSystemLogin");
		} else {
	%>

	<div class="modal fade" id="modal-revoke-customer">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Revoke Customer</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to revoke <b><i id="customer_name">
							</i></b> ?
						</p>
						</p>
					</div>

				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateURL"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>

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
					<li class="has-sub active"><a href="Customer"> <i
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
				Customers <span
					style="font-size: 24px; margin-left: 10px; font-weight: bold">
					<%-- <%=bean.size() %> --%>
				</span>
			</h1>
			<!-- end page-header -->

			<!-- begin row -->

			<div class="row">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->

					<div id="customerPanel" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Customers Table</h4>
						</div>
						<div class="panel-body">


							<div class="table-responsive" style="font-size: 13px;">
								<div class="row" style="padding-top: 2%; width: 100%;">
									<ul id="ioniconsTab" class="nav nav-tabs">
										<li class="active"><a href="#default" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Accepted
													Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#ios" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Rejected
													Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
										<li><a href="#notinterested" data-toggle="tab"><div
													class="text-center"></div> <span class="hidden-xs m-l-3">Not
													Interested Customers<span class="badge badge-inverse m-l-3"></span>
											</span></a></li>
									</ul>

									<div id="ioniconsTabContenta" class="tab-content">

										<div class="tab-pane fade in active" id="default">
											<div class="table-responsive" style="font-size: 13px;">

												<table id="customer_table"
													class="table table-hover table-striped">
													<thead>
														<tr>
															<th>Appliances In Account</th>
															<th>Name</th>
															<th>Customer Rating</th>
															<th>City, District</th>
															<!-- 															<th>Monthly Income</th> -->
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>

											</div>
										</div>
										<div class="tab-pane fade" id="ios">
											<div class="table-responsive" style="font-size: 13px;">
												<table id="customer"
													class="table table-hover table-striped">
													<thead>
														<tr>
															<th>Appliances In Account</th>
															<th>Name</th>
															<th>City, District</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>

										<div class="tab-pane fade" id="notinterested">
											<div class="table-responsive" style="font-size: 13px;">
												<table class="table">
													<tr>
														<td><label class="label" for="verified"
															style="background-color: #1abc9c; color: white;">Verified</label>
															Not interested since 30 days</td>

													</tr>

													<tr>
														<td><label class="label" for="accepted"
															style="background-color: blue; color: white;">Accepted</label>
															Not interested since 10 days</td>

													</tr>
												</table>
												<table id="data_table"
													class="table table-hover table-striped">
													<thead>
														<tr>
															<th>Name</th>
															<th>Appliance</th>
															<th>Last status</th>
															<th>Action</th>
															<!--System.err.println(hashMap.get("customer_id")); -->

														</tr>
													</thead>
													<tbody>
														<%
															ArrayList<HashMap<String, String>> list = CustomerBal
																		.getNotInterstedCustomers();
																for (HashMap<String, String> hashMap : list) {
																	int last_status = Integer.parseInt(hashMap
																			.get("last_status"));
																	String name = hashMap.get("appliance_name");
														%>
														<tr>
															<td><%=hashMap.get("customer_name")%></td>
															<td><%=hashMap.get("appliance_name")%></td>

															<%
																if (last_status == 1) {
															%>
															<td><label class="label"
																style="background-color: #2980b9; color: white; font-weight: bold">Accepted</label></td>
															<%
																} else if (last_status == 7) {
															%>
															<td><label class="label"
																style="background-color: #1abc9c; color: white; font-weight: bold">Verified</label></td>
															<%
																}
															%>

															<td><a href="#modal-revoke-customer"
																onclick="setMoodValue('<%=hashMap.get("appliance_id")%>', '<%=hashMap.get("customer_name")%>')"
																style="text-decoration: none;" data-toggle="modal"
																style="width:70%;">Revoke<span> </span></a></td>

														</tr>
														<%
															}
														%>
													</tbody>
												</table>

											</div>
										</div>

									</div>
								</div>

								<%
									} //session else closed
								%>

							</div>
							<!-- end panel -->
						</div>
						<!-- end col-10 -->
					</div>
					<!-- end row -->
				</div>
				<!-- end #content -->

				<a href="javascript:;"
					class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
					data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
				<!-- end scroll to top btn -->
			</div>


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
		<!-- ================== END BASE JS ================== -->


		<script src="assets/plugins/chart-js/chart.js"></script>
		<!--  <script src="assets/js/apps.min.js"></script> -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>
		<script src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
		<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
		<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
		<script src="assets/js/table-manage-default.demo.min.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
		<script
			src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.bootstrap.min.js"></script>
		<script src="assets/js/table-manage-colvis.demo.min.js"></script>
		<script src="assets/js/chart-js.demo.min.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<!-- ================== END PAGE LEVEL JS ================== -->
		<script src="assets/plugins/icheck/icheck.min.js"></script>



		<!-- Begin Initialize Customer Table -->
		<script type="text/javascript">
			var selected = [];

			var customertable = $('#customer_table')
					.DataTable(
							{
								"destroy" : true,
								"order" : [ [ 1, "asc" ] ],
								"processing" : true,
								"serverSide" : true,
								"ajax" : {
									"url" : 'CustomerController',
									"type" : 'POST',
									"data" : function(d) {
										d.action = "getCustomersByLimitAndRange";
									},
									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.recordsTotal);
										console.log(json.data)
										for ( var i = 0; i < json.data.length; i++) {

											json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
											json.data[i].select = '<span class="label label-info">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
													+ json.data[i].applianceName
													+ '</span>'

											json.data[i].customerName = "<a href='ViewServlet?click=view&id="
													+ json.data[i].customerId
													+ "&cnic="
													+ json.data[i].customerCnic
													+ "&appId="
													+ json.data[i].applianceId
													+ "'>"
													+ json.data[i].customerName
													+ "</a>";

											if (json.data[i].eligibilityStatus == 0) {
												json.data[i].status = '<span class="label label-primary">Applied</span>'
											} else if (json.data[i].eligibilityStatus == 1
													|| json.data[i].eligibilityStatus == 6) {
												json.data[i].status = '<span class="label label-success">Accepted</span>'
											} else if (json.data[i].eligibilityStatus == 3) {
												json.data[i].status = '<span class="label bg-orange">Not Intrested</span>'
											}
											if (json.data[i].applianceStatus == 0) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
											} else if (json.data[i].applianceStatus == 1) {
												json.data[i].applianceStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
											}
										}
										return json.data;
									}
								},
								"rowCallback" : function(row, data) {
									$(row).data(
											'link',
											'ViewServlet?click=view&id='
													+ data.customerId
													+ '&cnic='
													+ data.customerCnic
													+ '&applianceId='
													+ data.applianceId);
									$(row).addClass('link-not-first-child');
								},
								"columns" : [ {
									"data" : "select",
									"orderable" : false,
									"searchable" : false
								}, {
									"data" : "customerName"
								}, {
									"data" : "customer_rating"
								}, {
									"data" : "districtName"
								} ],
								"columnsDefs" : []
							});

			var selected = [];

			var table = $('#customer')
					.DataTable(
							{
								"destroy" : true,
								"order" : [ [ 2, "asc" ] ], 
								"processing" : true,
								"serverSide" : true,
								"ajax" : {
									"url" : 'CustomerController',
									"type" : 'POST',
									"data" : function(d) {
										d.action = "getRejectedCustomers"
									},
									"dataSrc" : function(json) {
										for ( var i = 0; i < json.data.length; i++) {
											json.data[i].selectRow = '<input type="checkbox" class="case" value="'+json.data[i].customerPhoneNumber+'">'
											json.data[i].select = '<span class="label label-danger">  <span class="fa fa-plus-circle fa-lg" value="'+json.data[i].customerId+'"> '
													+ json.data[i].applianceName
													+ '</span>'
										}
										return json.data;
									}
								}

								,
								"rowCallback" : function(row, data, index) {
									$(row).data(
											'link',
											'ViewServlet?click=view&id='
													+ data.customerId
													+ '&cnic='
													+ data.customerCnic
													+ '&appId='
													+ data.applianceId)
									$(row).addClass('link-not-first-child')
								},
								"columns" : [ {
									"data" : "select",
									"orderable" : false,
									"searchable" : false
								},

								{
									"data" : "customerName"
								}, {
									"data" : "districtName"
								} ],
								"columnsDefs" : []
							});

			$("#customer_table tbody").on('click', 'tr>td', function(event) {
				console.log($(this).text())
				if ($(this).eq(0).html() != "") {		
				}
			});

			$("#customer tbody").on('click', 'tr>td', function(event) {
				console.log($(this).text())
				if ($(this).eq(0).html() != "") {

				}
			});

			$("#customer_table tbody").on('click', 'tr>td>span>span.fa',
					function(event) {
						var span = $(this)
						var tr = $(this).closest('tr');
						var row = customertable.row(tr);
						console.log("tr : ");
						console.log(tr)
						console.log("Row : ");
						console.log(row)
						console.log("Row Data : ");
						console.log(row.data())
						if (row.child.isShown()) {
							row.child.hide();
							tr.removeClass('shown');
							$(span).addClass('fa-plus-circle')
							$(span).removeClass('fa-minus-circle')
						} else {
							row.child(format(row.data())).show();
							tr.addClass('shown');
							$(span).addClass('fa-minus-circle')
							$(span).removeClass('fa-plus-circle')
						}
					});

			$("#customer tbody").on('click', 'tr>td>span>span.fa',
					function(event) {
						var span = $(this)
						var tr = $(this).closest('tr');
						var row = table.row(tr);
						if (row.child.isShown()) {
							row.child.hide();
							tr.removeClass('shown');
							$(span).addClass('fa-plus-circle')
							$(span).removeClass('fa-minus-circle')
						} else {
							row.child(formatRejection(row.data())).show();
							tr.addClass('shown');
							$(span).addClass('fa-minus-circle')
							$(span).removeClass('fa-plus-circle')
						}
					});

			function format(d) {
				var id = "inner_table_" + d.customerId
				$('#' + id).remove();

				var innerTable = "<table id='"+id+"'>";
				$
						.getJSON(
								"CustomerController?action=getExpansion&id="
										+ d.customerId,
								{},
								function(result, response) {
									$
											.each(
													result,
													function(key, value) {
														console.log(result)
														if (value.length <= 0) {
															$('#' + id)
																	.append(
																			"Record Not Found");
														} else {
															$('#' + id)
																	.append(
																			'<th class="p-10">Appliance Name</th>'
																					+ '<th class="p-10">Nizam Dost Name</th>'
																					+ '<th class="p-10">Charging Status</th>'
																					+ '<th class="p-10">Loan Status</th>')
															for ( var i = 0; i < value.length; ++i) {
																console
																		.log(value[i].applianceName)
																var row;
																row += '<tr>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].applianceName
																		+ '</td>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].salesmanName
																		+ '</td>';

																if (value[i].applianceStatus == 1) {
																	row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#16a085; color:white;font-weight: bold;">'
																			+ 'active'
																			+ '</span></td>'
																} else {
																	row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:Red; color:white;font-weight: bold;">'
																			+ 'inActive'
																			+ '</span></td>'
																}

																if (value[i].loanStatus == 'defaulter') {
																	row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#34495e; color:white;font-weight: bold;">'
																			+ value[i].loanStatus
																			+ '</span></td>'
																} else if (value[i].loanStatus == 'maintained') {
																	row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:blue; color:white;font-weight: bold;">'
																			+ value[i].loanStatus
																			+ '</span></td>'
																} else if (value[i].loanStatus == 'owned') {
																	row += '<td class="p-r-10 p-l-10 p-b-5"><span class="label" style="background-color:#7f8c8d; color:black;font-weight: bold;">'
																			+ value[i].loanStatus
																			+ '</span></td>'
																}

																row += '</tr>'
																		+ '';
															}
															$('#' + id).append(
																	row)
														}
													})

								})
				return innerTable + "</table>";
			}

			function formatRejection(d) {
				var id = "inner_table_" + d.customerId
				$('#' + id).remove();

				var innerTable = "<table id='"+id+"'>";
				$
						.getJSON(
								"CustomerController?action=getRejectedExpansion&id="
										+ d.customerId,
								{},
								function(result, response) {
									$
											.each(
													result,
													function(key, value) {

														if (value.length <= 0) {
															$('#' + id)
																	.append(
																			"Record Not Found");
														} else {
															$('#' + id)
																	.append(
																			'<th class="p-10">Appliance Name</th>'
																					+ '<th class="p-10">Nizam Dost Name</th>'
																					+ '<th class="p-10">Rejection Purpose</th>')
															for ( var i = 0; i < value.length; i++) {
																// 	                	   alert(value.length)
																var row;
																row += '<tr>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].applianceName
																		+ '</td>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].salesmanName
																		+ '</td>'
																		+ '<td class="p-r-10 p-l-10 p-b-5">'
																		+ value[i].purpose
																		+ '</td>';

																row += '</tr>'
																		+ '';
															}
															$('#' + id).append(
																	row)
														}
													})

								})
				return innerTable + "</table>";
			}
		</script>
		<!-- End Initialize Customer Table -->

		<script>
			$(document).ready(function() {
				$('a[href="#notinterested"]').click(function() {

					$("#clickMe").hide();
				});

				$('a[href="#ios"]').click(function() {
					$("#default").find('input[type=checkbox]').each(function() {
						this.checked = false;
					});

					$("#clickMe").show();
				});

				$('a[href="#default"]').click(function() {
					$("#ios").find('input[type=checkbox]').each(function() {
						this.checked = false;
					});
					$("#clickMe").show();
				});
				App.init();
				TableManageDefault.init();
			});
		</script>
</body>

</html>
