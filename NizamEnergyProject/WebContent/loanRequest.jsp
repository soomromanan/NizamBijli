<%@page import="bal.CustomerBal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="error.jsp"%>
<%@page import="bal.CustomerBal"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>

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

<script>
	function setMoodValue(appliance_id, customer_name) {
		document.getElementById("customer_name").innerHTML = customer_name;
		document.getElementById('updateURL').onclick = function() {
			helloWorld(appliance_id);
		};
	}

	function helloWorld(applianceValue) {
		var link = "RevokeController?click=doRevoke&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;

	}
</script>
<body>
	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		int userId = bean.getUserId();
		if (bean == null) {
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

					<li class="has-sub active"><a href="LoanRequest"> <i
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
			<h1 class="page-header">
				<br> Loan Request<span id="loan_apps_count"
					style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
			</h1>
			<div class="row"
				style="padding-top: 2%; padding-left: 1%; width: 100%;">
				<ul id="ioniconsTab" class="nav nav-tabs">
					<li class="active"><a href="#default" data-toggle="tab"><div
								class="text-center"></div> <span class="hidden-xs m-l-3">Loan
								Requests <span class="badge badge-inverse m-l-3"></span>
						</span></a></li>

					<li><a href="#notinterested" data-toggle="tab"><div
								class="text-center"></div> <span class="hidden-xs m-l-3">Not
								Interested Customers<span class="badge badge-inverse m-l-3"></span>
						</span></a></li>
				</ul>

				<div id="ioniconsTabContenta" class="tab-content">
					<div class="tab-pane fade in active" id="default">
						<div class="table-responsive" style="font-size: 13px;">

							<div class="panel-body">
								<ul class="nav nav-pills f-s-15 inline bordered round-corner">
									<li><a class="m-0 p-5 m-r-15" id="cc_verify_li"> <input
											type="radio" name="filterr" id="cc_verify"> <span
											class="label"
											style="background-color: grey; color: white; font-weight: bold;">
										</span> <label for="cc_verify_apps">CC Verification</label>
									</a></li>
									<li><a class="m-0 p-5 m-r-15" id="pending_li"> <input
											type="radio" name="filterr" id="pending"> <span
											class="label"
											style="background-color: grey; color: white; font-weight: bold;">
										</span> <label for="pending">Pending</label>
									</a></li>

									<li><a class="m-0 p-5 m-r-15" id="accepted_li"> <input
											type="radio" id="accepted" name="filterr"> <span
											class="label"
											style="background-color: blue; color: white; font-weight: bold;">
										</span> <label for="accepted">Accepted</label>
									</a></li>

									<li><a class="m-0 p-5 m-r-15" id="varified_li"> <input
											type="radio" id="varified" name="filterr"> <span
											class="label"
											style="background-color: green; color: black; font-weight: bold;">
										</span> <label for="varified">Verified</label>
									</a></li>

									<li><a class="m-0 p-5 m-r-15"> <input type="radio"
											id="all_applications" name="filterr"> <label
											for="all_applications">All</label>
									</a></li>
								</ul>

								<div class="table-responsive" style="font-size: 13px">
									<table id="do-data-table"
										class="table table-hover table-striped">
										<thead>
											<tr>
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
					</div>

					<!-- Not Interested Customers begin here -->

					<div class="tab-pane fade" id="notinterested">
						<div class="table-responsive">

							<br> <label class="label" for="verified"
								style="background-color: #1abc9c; color: white; font-weight: bold;">Verified</label>
							Not interested since 30 days<br> <br> <label
								class="label" for="accepted1"
								style="background-color: #2980b9; color: white; font-weight: bold;">Accepted</label>
							Not interested since 10 days<br> <br>


							<table class="table table-hover table-striped">
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
													.getDoNotInterstedCustomers(userId);
											for (HashMap<String, String> hashMap : list) {
												int last_status = Integer.parseInt(hashMap
														.get("last_status"));
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

					<!-- Not Interested Customers end here -->

				</div>

			</div>

			<%
				}
			%>


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
	<script src="assets/js/apps.min.js"></script>
	<script src="assets/plugins/icheck/icheck.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->

	<script src="js/validation.js"></script>
	<script src="assets/js/numeral.js"></script>

	<script type="text/javascript">
		var text, color;

		function filterLoanApplications() {
			$('#do-data-table')
					.DataTable(
							{
								destroy : true,
								'processing' : true,
								'serverSide' : true,

								'ajax' : {
									"type" : "POST",
									"url" : "RequestServlet",
									"data" : function(d) {
										d.action = action
										if (action === undefined) {
											d.action = "getDoRequest"
										}

										if (filter != undefined) {
											d.filter = filter
										}
									},
									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.loan_apps_count)
										console.log(json.data)
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
																console
																		.log(json.data);
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
									"data" : "appliance_name",
									"orderable" : false
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
									"data" : "user_name",
									"orderable" : false
								}, {
									"data" : "status",
									"orderable" : false,
									"searchable" : false
								} ],
								"rowCallback" : function(row, data) {
									$(row).data(
											'link',
											'ViewServlet?click=doview&id='+data.customer_id+'&cnic='
													+ data.customer_cnic
													+ '&applianceId='
													+ data.eligibility_id);
									$(row).addClass('link')
									//					}
								},

							})

			$("#do-data-table tbody").on('click', 'tr.link>td',
					function(event) {
						link = $(this).parent().data('link');
						if (link != undefined) {
							window.location = link;
						}
					});
		}

		$(function() {

			//		 	Begin iCheck for filters
			$('[name="filterr"]').each(function() {
				var id = $(this).attr('id')
				var radioClass;
				if (id == "cc_verify") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "pending") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "accepted") {
					radioClass = 'icheckbox_square-red'
				}
				if (id == "varified") {
					radioClass = 'icheckbox_square-orange'
				}
				if (id == "all_applications") {
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
					action : "countFilters"
				},
				dataType : 'json',
				success : function(data) {
					console.log("count filters")
					console.log(data)
					if (data.pending !== undefined || data.pending !== 0) {
						$('#pending_li span').text(data.pending)
					}
					if (data.accepted !== undefined || data.accepted !== 0) {
						$('#accepted_li span').text(data.accepted)
					}
					if (data.varified !== undefined || data.varified !== 0) {
						$('#varified_li span').text(data.varified)
					}
					if (data.ccVerification !== undefined
							|| data.cc_varified !== 0) {
						$('#cc_verify_li span').text(data.cc_varified)
					}
				}

			})

			// Begin Quick Filters

			$('[name="filterr"]').on('ifChecked', function() {
				if ($(this).attr('id') === 'all_applications') {
					action = "getDoRequest"
				}

				if ($(this).attr('id') === 'pending') {
					action = "filterLoanApplications"
					filter = "pending"

				} else if ($(this).attr('id') === 'accepted') {
					action = "filterLoanApplications"
					filter = "accepted"
				} else if ($(this).attr('id') === 'varified') {
					action = "filterLoanApplications"
					filter = "varified"
				} else if ($(this).attr('id') === 'cc_verify') {
					action = "filterLoanApplications"
					filter = "ccVerification"
				} else {
					filter = undefined
				}

				filterLoanApplications()
			})

			// End Quick Filters
			$('#all_applications').iCheck('check')

			$("#do-data-table tbody").on('click', 'tr.link>td',
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
			filterLoanApplications();
		});
	</script>
</body>


</html>



