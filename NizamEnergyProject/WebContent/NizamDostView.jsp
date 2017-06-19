<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bal.CustomerBal"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.SalesManBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nizam Energy</title>


<!-- ================== BEGIN BASE CSS STYLE ================== -->
<link rel="shortcut icon" href="assets/icons/favicon.png" />
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

<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />

<!-- Load jQuery from Google's CDN -->
<!-- Load jQuery UI CSS  -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet"
	href="DateTimepicker/jquery-ui-timepicker-addon.css">
<link rel="stylesheet"
	href="DateTimepicker/jquery-ui-timepicker-addon.min.css">

<link
	href="assets/plugins/bootstrap-combobox/css/bootstrap-combobox.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<link href="assets/plugins/icheck/skins/line/_all.css" rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<!-- ================== END BASE CSS STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>
<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<!-- <script src="assets/plugins/pace/pace.min.js"></script -->
<!-- ================== END BASE JS ================== -->

<Script>
	function setMoodValue(ndId, nd_name) {
		var id = document.getElementById("ndId").innerHTML = ndId;
		var name = document.getElementById("ndId").innerHTML = nd_name;
		document.getElementById('updateURL').onclick = function() {
			$
					.ajax({
						url : 'FoRemovingController',
						method : 'POST',
						dataType : 'json',
						data : {
							click : "removeNd",
							ndId : id
						},
						success : function(data) {
							$('#hello').text(data.Have_Network)
							$("#modal-remove").modal('hide');
							$("#modal-list").modal('show');
							$("#ndIdd").text(data.ndName)
							if (data.bulkNd == "null") {
								$("#bulk")
										.text(
												'Alert! there is no Bulk Nizamdost of his District so you can not convert or remove this ND. Kindly create Bulk ND');
							} else {
								$("#district").text(data.bulkNd)
							}
						}
					})
		}
		document.getElementById('update').onclick = function() {
			helloWorld(ndId);
		}
	}

	function helloWorld(ndId) {
		var link = "FoRemovingController?click=removNd&ndId=" + ndId;
		document.getElementById('update').href = link;
	}
</Script>

</head>


<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		SalesManBean salesman = (SalesManBean) request
				.getAttribute("salesman");
		String primary_phone = CustomerBal.getFormattedPhoneNumber(salesman
				.getPhone_number());
		String secondary_phone = CustomerBal.getFormattedPhoneNumber(salesman
				.getPhone_number2());
		String salesmanSalary = NumberFormat.getNumberInstance(Locale.US)
				.format((salesman.getSallery()));
	%>


	<div class="modal fade" id="modal-remove">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Remove Fo</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you would like to remove Nizam Dost <b><i
								id="ndId"> </i></b> ?
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
	<div class="modal fade" id="modal-list">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Remove Fo</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							<b><i id="hello"> </i></b> Would you still like to proceed and
							transfer Nizam Dost <b><i id="ndIdd"> </i></b>'s network to <b><i
								id="district"> </i></b>?
						</p>
						<b><p id="bulk"></p></b>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="update"
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
							<%=bean.getUserName()%>
							<small>District Officer </small>
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
							<li><a href="DistrictOfficerr">District Officer</a></li>
							<li><a href="FieldOfficer">Field Officer</a></li>
							<li class="active"><a href="NizamDost">Nizam Dost</a></li>
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
			<!-- begin breadcrumb -->

			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header"><%=salesman.getName()%></span> <span
				style="float: right; margin-left: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Date Of Joining/<small>
						&nbsp;<%=salesman.getDatejoin()%></small>
			</a> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Date Of Birth /<small>
						&nbsp;<%=salesman.getDate_of_birth()%></small>
			</a>
			</span>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row" style="padding-top: 9%">
				<div class="col-md-6">

					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Nizam Dost Details</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">

									<tbody>
										<tr>
											<th>Field Officer</th>
											<td><b><a
													href="fieldOfficerProfile.jsp?fo_id=<%=salesman.getFoid()%>"><%=salesman.getFoname()%></a></b>
											</td>
										</tr>

										<tr>
											<th>District Officer</th>
											<td><b><a
													href="DistrictOfficer?do_id=<%=salesman.getDoid()%>"><%=salesman.getDoname()%></a></b></td>
										</tr>

										<tr>
											<th>Nizam Dost CNIC</th>
											<td><%=salesman.getCnic()%></td>
										</tr>

										<tr>
											<th>District /Tehsil</th>
											<td><%=salesman.getTahsel()%> /<b><%=salesman.getDistrict_name()%></b></td>

										</tr>

										<tr>
											<th>Per Sale Commission</th>
											<%
												if (salesman.getPer_sale() == 0) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(salesman.getPer_sale()))%><b> PKR</b></td>
											<%
												}
											%>

										</tr>

										<tr>
											<th>Nizam Dost Account Number</th>
											<%
												if (salesman.getVle_acount_no() == ""
														|| salesman.getVle_acount_no() == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=salesman.getVle_acount_no()%></td>
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
							<h4 class="panel-title">Contact Details</h4>
						</div>

						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>
										<tr>
											<th>Primary Phone</th>
											<td><%=primary_phone%></td>
										</tr>

										<tr>
											<th>Secondary Phone</th>
											<td><%=secondary_phone%></td>
										</tr>

										<tr>
											<th>Nizam Dost Email</th>
											<td><%=salesman.getSalesman_email()%></td>
										</tr>

										<tr>
											<th>Address</th>
											<td><%=salesman.getAddress()%></td>
										</tr>

										<tr>
											<th>Update Profile</th>
											<td><a
												href="updateNizamDost.jsp?vleId=<%=salesman.getSalesmanId()%>"
												class="btn btn-primary btn-xs pull-left"><b>Update
														Nizam Dost</b></a></td>
										</tr>

										<tr>
											<th>Remove Nizam Dost</th>
											<td>
												<button type="button"
													class="btn btn-danger btn-xs pull-left" data-toggle="modal"
													data-target="#modal-remove" id="foId"
													onclick="setMoodValue('<%=salesman.getSalesmanId()%>','<%=salesman.getName()%>')">
													<b>Remove Nizam Dost</b>
												</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
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
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->

	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<!-- Load jQuery UI Main JS  -->
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script src="DateTimepicker/jquery-ui-timepicker-addon.js"></script>
	<script src="DateTimepicker/jquery-ui-timepicker-addon.min.js"></script>

	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/plugins/chart-js/chart.js"></script>
	<!-- ================== END BASE JS ================== -->
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>

	<script
		src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>

	<script src="assets/plugins/select2/dist/js/select2.min.js"></script>
	<script src="assets/js/form-plugins.demo.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- 	<script src="assets/js/chart-js.demo.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script src="assets/plugins/icheck/icheck.js"></script>
	<script src="assets/js/myscript.js"></script>

	<script>
		$(document).ready(function() {
			App.init();
		});
	</script>
</body>
</html>