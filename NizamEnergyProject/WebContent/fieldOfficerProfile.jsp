<!DOCTYPE html>
<%@page import="bal.DoFoNdBal" %>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bal.CustomerBal"%>

<html>
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
	rel="stylesheet" />
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
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<link href="assets/plugins/ionicons/css/ionicons.min.css"
	rel="stylesheet" />
<link href="assets/plugins/simple-line-icons/simple-line-icons.css"
	rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet">

<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<link rel="stylesheet" href="style.css" type="text/css">
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script src="amcharts/serial.js" type="text/javascript"></script>

<script>
 function setMoodValue(foId, fo_name) {
  var id = document.getElementById("foId").innerHTML = foId;
  var name = document.getElementById("foId").innerHTML = fo_name;
   document.getElementById('updateURL').onclick = function() {
	$.ajax({
		url : 'FoRemovingController',
		method : 'POST',
		dataType : 'json',
		data : {
			click : "remove",
			foId : id
		},
		success : function(data) {
			$('#hello').text(data.Have_Network)
			$("#modal-remove").modal('hide');	
			$("#modal-list").modal('show');	
			$("#foIdd").text(data.foName)
			if(data.bulkFo == "null"){
				$("#bulk").text('Alert ! there is no Bulk FO of his District so you can not convert or remove this Fo. Kindly create Bulk FO');
			}else {
				$("#district").text(data.bulkFo)
			}
			}
	})
 }
   document.getElementById('update').onclick = function() {
	   console.log("foId "+foId)
	   helloWorld(foId);
	 }
}
 
 function helloWorld(foId) {
	 console.log("foId :"+foId)
		var link = "FoRemovingController?click=ok&foId="+ foId;
		document.getElementById('update').href = link;
	}
</script>

</head>

<body>
	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		int foID = Integer.parseInt(request.getParameter("fo_id"));
		ArrayList<HashMap<String, String>> list = DoFoNdBal
				.getFoSalesman(foID);
		HashMap<String, String> maps = DoFoNdBal
				.getFieldOfficerDetail(foID);
		String priamary_phone = CustomerBal.getFormattedPhoneNumber(maps
				.get("priamary_phone"));
		String secondary_phone = CustomerBal.getFormattedPhoneNumber(maps
				.get("secondary_phone"));
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
							Are you sure you would like to remove FO <b><i id="foId">
							</i></b> ?
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
							transfer FO <b><i id="foIdd"> </i></b>'s network to <b><i
								id="district"> </i></b> ?
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

	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

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
				<ul class="nav">

					<li class="has-sub"><a href="SuperAdminDashboard"> <i
							class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="Request"> <i
							class="icon-note"></i> <span>New Loan Request</span> <span
							class="badge pull-right" id="unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub active"><a href="AdvanceBooking"> <i
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
							<li class="active"><a href="FieldOfficer">Field Officer</a></li>
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
		<div id="content" class="content">


			<span style="float: left; color: black; margin-top: 2%;"
				class="page-header"><%=maps.get("foName")%></span> <span
				style="float: right; margin-left: 9px;"> <a
				href="javascript:;" class="btn btn-lg btn-block btn-success"> <i
					class="fa fa-calendar pull-left"></i>&nbsp;Date Of Joining/<small>
						&nbsp;<%=maps.get("date_of_joining")%></small>
			</a> <a href="javascript:;" class="btn btn-lg btn-block btn-success">
					<i class="fa fa-calendar pull-left"></i>&nbsp;Date Of Birth /<small>
						&nbsp;<%=maps.get("date_of_birth")%></small>
			</a>
			</span> <span style="float: right;"></span>

			<div class="row" style="padding-top: 9%">

				<!-- 		<div class="col-md-12"> -->
				<div class="col-md-6">
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officer Details</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>
										<tr>
											<th>CNIC</th>
											<td><%=maps.get("Cnic")%></td>
										</tr>

										<tr>
											<th>Address</th>
											<td><%=maps.get("address")%></td>
										</tr>
										<tr>
											<th>District Officer</th>
											<td><b><a
													href="DistrictOfficer?do_id=<%=maps.get("user_id")%>"><%=maps.get("DistrictOfficer")%></a></b></td>
										</tr>

										<tr>
											<th>District/Tehsil</th>
											<td><%=maps.get("District")%><b> /<%=maps.get("city")%></b></td>
										</tr>

										<tr>
											<th>Per Sale Commission</th>
											<%
												if (maps.get("per_sale") == "" || maps.get("per_sale") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(Integer.parseInt(maps.get("per_sale"))))%><b>
													PKR</b></td>
											<%
												}
											%>

										</tr>

										<tr>
											<th>Field Officer Acc Number</th>

											<%
												if (maps.get("fo_acount_no") == ""
														|| maps.get("fo_acount_no") == null) {
											%>
											<td>N/A</td>
											<%
												} else {
											%>
											<td><%=maps.get("fo_acount_no")%></td>
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
							<h4 class="panel-title">Field Officer Contact</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									<tbody>

										<tr>
											<th>Primary Phone Number</th>
											<td><%=priamary_phone%></td>
										</tr>

										<tr>
											<th>Secondary Phone Number</th>
											<td><%=secondary_phone%></td>
										</tr>

										<tr>
											<th>E-mail</th>
											<td><%=maps.get("fo_email")%></td>
										</tr>

										<tr>
											<th>Update Profile</th>
											<td><a href="updatefo.jsp?foId=<%=maps.get("fo_id")%>"
												class="btn btn-primary btn-xs pull-left"><b>Update
														Field Officer</b></a></td>
										</tr>
										<tr>
											<th>Remove</th>
											<td>
												<button type="button"
													class="btn btn-danger btn-xs pull-left" data-toggle="modal"
													data-target="#modal-remove" id="foId"
													onclick="setMoodValue('<%=foID%>','<%=maps.get("foName")%>')">
													<b>Remove Field Officer</b>
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
		<script src="assets/js/myscript.js"></script>

		<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
		<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
		<!-- ================== END BASE JS ================== -->

		<!-- ================== BEGIN PAGE LEVEL JS ================== -->
		<script src="assets/plugins/morris/raphael.min.js"></script>
		<script src="assets/plugins/morris/morris.js"></script>
		<script src="assets/js/chart-morris.demo.min.dashboard.js"></script>
		<script src="assets/js/apps.min.js"></script>
		<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
		<script
			src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;sensor=false"></script>
		<script src="assets/js/apps.min.js"></script>
		<script type='text/javascript'
			src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script>
		<script type="text/javascript" src="assets/async/dashboard.js"></script>
		<script>
	<!-- ================== END PAGE LEVEL JS ================== -->
		$(document).ready(function() {
 			App.init();
		});
	</script>
</body>
</html>