<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="bean.UserBean"%>
<%@page import="bean.ApplianceBean"%>
<%@page import="bal.ApplianceBal"%>
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
<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/switchery/switchery.min.css" rel="stylesheet" />
<link href="assets/plugins/powerange/powerange.min.css" rel="stylesheet" />
<!-- ================== END PAGE CSS STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<!-- ================== BEGIN BASE JS ================== -->
<!--   <script src="http://www.amcharts.com/lib/3/amcharts.js" type="text/javascript"></script>  -->
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<!-- ================== END BASE JS ================== -->
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>

<script>
	function setMoodValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		document.getElementById('updateURL').onclick = function() {
			helloWorld(appliance_id);
		};
	}

	function helloWorld(applianceValue) {
		var link = "DeviceReturnController?click=doReturn&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;
	}

	function setMoodalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		document.getElementById('updateInstallURL').onclick = function() {
			hello(appliance_id);
		};
	}

	function hello(applianceValue) {
		var link = "DOInstallController?click=install&applianceId="
				+ applianceValue;
		document.getElementById('updateInstallURL').href = link;
	}
	
	function setMoodalNoSignalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		document.getElementById('updateNoSignalURL').onclick = function() {
			helloNoSignal(appliance_id);
		};
	}

	function helloNoSignal(applianceValue) {
		var link = "DOInstallController?click=varifySignals&applianceId="
				+ applianceValue;
		document.getElementById('updateNoSignalURL').href = link;
	}
</script>

<style type="text/css">
#appTable,thead,tr,th {
	border-top: #252830;
}

#appTable tbody tr th {
	/*   background: #eee; */
	/*   border-top: #252830; */
	border-style: none;
}

#appTable tbody tr td {
	border-style: none;
	/* 	border-top: #252830; */
}

.my-fancy-container {
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 6px;
	margin: 5px;
	/*     padding: 5px; */
	width: 40%;
}

.my-text {
	font-family: "Courier-new";
	color: white;
	padding: inherit;
	display: inline-block;
	text-align: center;
	width: 99%;
}

.mask {
	border: 0;
	height: 1px;
	background: white;
	background-image: linear-gradient(to right, #ccc, #ffffff, #ccc);
}

.divider-inside-top-bottom {
	position: relative;
	overflow: hidden;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}

.divider-inside-top-bottom:before {
	content: "";
	position: absolute;
	z-index: 1;
	width: 96%;
	top: -10px;
	height: 10px;
	left: 2%;
	border-radius: 100px/5px;
	box-shadow: 0 0 18px rgba(0, 0, 0, 0.6);
}

.divider-inside-top-bottom:after {
	content: "";
	position: absolute;
	z-index: 1;
	width: 96%;
	bottom: -10px;
	height: 10px;
	left: 2%;
	border-radius: 100px/5px;
	box-shadow: 0 0 18px rgba(0, 0, 0, 0.6);
}

#setImage {
	width: 100%;
	color: white;
	margin-top: 10px;
}

#headingSet {
	color: white;
	text-align: center;
}
</style>


<%
	String current = "";
	UserBean userbean = (UserBean) session.getAttribute("email");

	ApplianceBean bean = (ApplianceBean) request.getAttribute("bean");

	if (userbean == null) {
		response.sendRedirect("SolarHomeSystemLogin");
	} else {
		ApplianceBal.updateApplianceAlerts(bean.getApplianceId());	
		boolean state = bean.getState();
%>


</head>

<body>

	<div class="modal fade" id="modal-return">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Return Customer</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to returned the device <b><i
								id="applianceid"> </i></b> ?
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


	<div class="modal fade" id="modal-install">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Install Device</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to install the device <b><i
								id="applianceid"> </i></b> ?
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateInstallURL"
						class="btn btn-sm btn-success">Accept</a>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modal-nosignal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header success">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">Install Device</h4>
				</div>
				<div class="modal-body">
					<div class="alert m-b-0">
						<p>
							Are you sure you want to install the device <b><i
								id="applianceid"> </i></b> ?
						</p>
					</div>
				</div>
				<div class="modal-footer">
					<a href="javascript:;" class="btn btn-sm btn-white"
						data-dismiss="modal">Close</a> <a id="updateNoSignalURL"
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
	<input type="hidden" id="customer_id" value="<%=bean.getCustomerId()%>">
	<input type="hidden" id="appID" value="<%=bean.getApplianceId()%>">
	<input type="hidden" id="gsmNumber"
		value="<%=bean.getApplianceGsmNo()%>">
	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar">
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
							<small>District Officer</small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->
				<!-- begin sidebar nav -->
				<ul class="nav">
					<!-- 					<li class="nav-header">Navigation</li> -->
					<li class="has-sub "><a href="DistrictOfficerDashboard">
							<i class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> <%-- <span class="badge pull-right"><%=countRequests%></span> --%>
							<i class="icon-note"></i> <span>Loan Request</span> <!-- 						Begin	SuperAdminHeader.js UnseenRequest() -- Jeevan -->
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub"><a href="DoWiseAdvanceBooking"><i
							class="fa fa-star"></i><span>Advance Booking</span> <span
							class="badge pull-right" id="do_unseen_bookings_count"></span></a></li>

					<li class="has-sub active"><a href="DoAppliances"><i
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
				Appliance:
				<%=bean.getApplianceName()%></h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">
			
			
			<div class="col-md-6 ui-sortable" >
					<!-- begin panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">
								Appliance Detail / <b><%=bean.getApplianceName()%></b></h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
									
									<tr>
										<th>Charging State</th>
										<td>
											<%
												if (state == true) {
											%> <span class="label" style="background-color: #2ecc71">Active</span>
											<%
												} else {
											%> <span class="label label-danger">Inactive</span> <%
 	}
 %>
										</td>
									</tr>
									<tr>
										<th>Price</th>
										<td><%=NumberFormat.getNumberInstance(Locale.US).format(
						Math.round(bean.getPrice()))%><b> PKR</b></td>
									</tr>
									<tr>
										<th>Customer Name</th>
										<td><a
											href="ViewServlet?click=doview&id=<%=bean.getCustomerId() %>&cnic=<%=bean.getCustomerCnic()%>&applianceId=<%=bean.getApplianceId()%>">
												<%=bean.getCustomerName()%>
										</a></td>
									</tr>
									<tr>
										<th>Hand over At</th>
										<td>
											<%
												if (bean.getHandoverDate() == null) {
											%> N/A <%
												} else {
											%> <%=bean.getHandoverDate()%></td>
										<%
											}
										%>
									</tr>
									<%
										if (bean.getStatus() == 4) {
									%>
									<tr>
										<th>Returned Date</th>
										<td>
											<%
												if (bean.getReturnedDate() == null) {
											%> N/A <%
												} else {
											%> <%=bean.getReturnedDate()%></td>
										<%
											}
										%>
									</tr>
									<%
										}
									%>
									<tr>
										<th>District Officer</th>
										<td><b><%=bean.getUserName()%></b></td>
									</tr>
									<tr>
										<th>Field Officer</th>
										<td><a
											href="doFieldOfficerProfile.jsp?fo_id=<%=bean.getFoid()%>"><%=bean.getFoName()%>
										</a></td>
									</tr>
									<tr>
										<th>Nizam Dost</th>
										<td><a
											href="NizamDostViewController?click=vleprofile&salesman_id=<%=bean.getSalesmanId()%>&salesmanGsm=<%=bean.getSalesmanPhone()%>"><%=bean.getSalesmanName()%>
										</a></td>
									</tr>

									
	
								</table>
							</div>
						</div>
					</div>

				</div>

				<!-- begin col-6 -->
				<div class="col-md-6 ui-sortable" >
					<!-- begin panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">
								Appliance Detail / <b><%=bean.getApplianceName()%></b> </h4>
						</div>
						<div class="panel-body" style="padding-bottom: 0px;">
							<div class="table-responsive" style="font-size: 13px">
								<table class="table">
								
								<tr>
										<th>Transferred Status</th>
										<%
											if (bean.getStatus() == 0) {
										%>
										<td><span class="label"
											style="background-color: #bdc3c7; color: white; font-weight: bold;">Pending</span></td>
										<%
											} else if (bean.getStatus() == 1) {
										%>
										<td><span class="label"
											style="background-color: #9b59b6; color: white; font-weight: bold;">Ready
												To Assign</span></td>
										<%
											} else if (bean.getStatus() == 2) {
										%>
										<td><span class="label"
											style="background-color: #1abc9c; color: white; font-weight: bold;">RDP</span></td>
										<%
											} else if (bean.getStatus() == 3) {
										%>
										<td><span class="label"
											style="background-color: #7f8c8d; color: white; font-weight: bold;">Owner</span></td>
										<%
											} else if (bean.getStatus() == 4) {
										%>
										<td><span class="label"
											style="background-color: #d35400; color: black; font-weight: bold;">Returned</span></td>
										<%
											} else if (bean.getStatus() == 5) {
										%>
										<td><span class="label"
											style="background-color: #1abc9c; color: white; font-weight: bold;">Awaiting
												Downpayment </span></td>
										<%
											} else if (bean.getStatus() == 6) {
										%>
										<td><span class="label"
											style="background-color: green; color: white; font-weight: bold;">Installed
										</span></td>
										<%
											} else if (bean.getStatus() == 7) {
										%>
										<td><span class="label"
											style="background-color: green; color: white; font-weight: bold;">Installed
												and NoSignal</span></td>
										<%
											}
										%>
									</tr>

									<tr>
										<th>Health Check</th>
										<%
											if (bean.getApplianceName().equals("60 W")
														|| bean.getApplianceName().equals("P-60")
														|| bean.getApplianceName().equals("7 W")) {
													if (bean.getStatus() == 0) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 1) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 2) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 3) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 4) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 5) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											} else if (bean.getStatus() == 6) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">DUMB</span></td>
										<%
											}
										%>

										<%
											} else {
													if (bean.getStatus() == 0) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">N/A</span></td>
										<%
											} else if (bean.getStatus() == 1) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">N/A</span></td>
										<%
											} else if (bean.getStatus() == 2) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">N/A</span></td>
										<%
											} else if (bean.getStatus() == 3) {
										%>
										<td></td>
										<%
											} else if (bean.getStatus() == 4) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">N/A</span></td>
										<%
											} else if (bean.getStatus() == 5) {
										%>
										<td><span class="label"
											style="background-color: black; color: white; font-weight: bold;">N/A</span></td>
										<%
											} else if (bean.getStatus() == 6) {
														if (bean.getHealthStatus() == 3) {
										%>
										<td><span class="label"
											style="background-color: #2980b9; color: white; font-weight: bold;">ALIVE</span></td>
										<%
											} else if (bean.getHealthStatus() == 2) {
															if (bean.getDeadSince() == 0) {
										%>
										<td><span class="label"
											style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD
												Since Today</span></td>
										<%
											} else if (bean.getDeadSince() == 1) {
										%>
										<td><span class="label"
											style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD
												Since Yesterday</span></td>
										<%
											} else {
										%>
										<td><span class="label"
											style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD
												Since <%=bean.getDeadSince()%> days
										</span></td>
										<%
											}
										%>
										<%
											}
										%>
										<%
											} else if (bean.getStatus() == 7) {
										%>
										<td><span class="label"
											style="background-color: #bdc3c7; color: white; font-weight: bold;">DEAD</span></td>
										<%
											}
										%>

										<%
											}
										%>
									</tr>
								
									<tr>
										<th>Appliance IMEI Number</th>
										<td>
											<%
												String imei = "";
													if (bean.getImiNumber() == null || bean.getImiNumber() == "") {
														imei = "N/A";
													} else {
														imei = bean.getImiNumber();

													}
											%> <input type="text" id="imeinumber" data-table="appliance"
											data-type="imei" data-column="imei_number" value="<%=imei%>"
											maxlength="15" disabled
											style="background-color: white; border: none" /> 
											<span style="color: red" id="imeiresult"></span>
									</tr>
									<tr>
										<th>GSM Number</th>
										<td>
											<%
												String gsm = "";

													if (bean.getApplianceGsmNo() == null
															|| bean.getApplianceGsmNo() == "") {
											%> N/A <%
												} else {
														gsm = bean.getApplianceGsmNo().substring(2);
														StringBuilder buldgsm = new StringBuilder(gsm);
											%> <span>(+92) </span><input type="text" id="gsm"
											value="<%=buldgsm.insert(3, "-")%>" maxlength="11"
											onkeypress="setMobileDash(event,'dophone1')" disabled
											style="background-color: white; border: none">

										</td>
										<%
											}
										%>
									</tr>

									<tr>
										<th>Consumer Number</th>

										<%
											if (bean.getStatus() == 5 || bean.getStatus() == 2) {
										%>
										<td><input type="text" id="ConsumerNumber"
											data-table="appliance" data-type="consumer"
											data-column="consumer" value="<%=bean.getConsumerNumber()%>"
											maxlength="15" disabled
											style="background-color: white; border: none" /> <span
											style="color: red" id="consumerNum"></span> <input
											type="button" class="btn btn-sm btn-success"
											id="saveConsumer" style="display: none" value="Save">
											<button class="btn  btn-danger" id="cancelEditConsumer"
												style="display: none">
												<i class="fa fa-times"></i>
											</button>


											<button class="btn btn-sm btn-success" id="editConsumer">
												<i class="fa fa-pencil"></i>
											</button> <span id="Consumerloader"></span></td>
										<%
											} else {

													if (bean.getConsumerNumber() == null) {
										%>

										<td>N/A</td>
										<%
											} else {
										%>
										<td><%=bean.getConsumerNumber()%></td>

										<%
											}
												}
										%>
									</tr>
									
									<tr>
										<th>Replace Appliance</th>
										<td><input type="button" class="btn btn-sm btn-success"
											id="saveApp" style="display: none" value="Save">
											<button class="btn  btn-danger" id="cancelEdit"
												style="display: none">
												<i class="fa fa-times"></i>
											</button> <%
									 	if (bean.getImiNumber() == null
									 				|| (bean.getApplianceGsmNo() == null || bean
									 						.getApplianceGsmNo() == "")) {
									 %>
											<button style="background-color: gainsboro"
												class="btn btn-sm btn-success" id="editApp" disabled>
												<i class="fa fa-pencil"></i>
											</button> <span id="loader"></span> <%
									 	} else {
									 %>
											<button class="btn btn-sm btn-success" id="editApp">
												<i class="fa fa-pencil"></i>
											</button> <span id="loader"></span> <%
									 	}
									 %></td>

									</tr>
									<%
										if (bean.getStatus() == 2) {
									%>
									<tr>
										<td>

											<button type="button" class="btn btn-success pull-left btn-xs"
												data-toggle="modal" data-target="#modal-install"
												id="applianceid"
												onclick="setMoodalValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')"><b>Insatll Device</b></button>
										</td>
										<td>
											<button type="button" class="btn btn-warning pull-left btn-xs"
												data-toggle="modal" data-target="#modal-nosignal"
												id="applianceid"
												onclick="setMoodalNoSignalValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')"><b>Installed and NoSignal</b> </button>
										</td>
									</tr>
									<%
										}
									%>
									<%
									if (bean.getStatus() == 6 || bean.getStatus() == 7) {
									%>
									<tr>
										<td>
											<button type="button" class="btn btn-danger pull-left btn-xs" 
												data-toggle="modal" data-target="#modal-return"
												id="applianceid"
												onclick="setMoodValue('<%=bean.getApplianceId()%>','<%=bean.getCustomerName()%>')"><b>Return Device</b></button> 
										</td>
										<th><a
											href="DoLoanBookController?action=viewloan&applianceId=<%=bean.getApplianceId()%>"
											style="text-decoration: none"> Loan Book View</a></th>
									</tr>
									<%
 									}%>
									
								</table>
							</div>
						</div>
					</div>

				</div>
				
				
				
				

			</div>
			<!-- end row -->

			<div class="row" style="padding-top: 2%">
				<div class="col-md-12">
					<ul id="ioniconsTab" class="nav nav-tabs">
						<li class="active"><a href="#default" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">Power
									Usage Graph <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<li><a href="#ios" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">
									Load and Solar Current <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<span><input
							style="height: 30px; margin: 5px; float: right;" type="date"
							name="dateSet" id="dateSet"
							onchange="loadSoalrAmpere(), staticsDateWise()"></span>
					</ul>



					<div id="ioniconsTabContenta" class="tab-content">

						<div class="tab-pane fade in active" id="default">
							<div>
								<input type="hidden" id="current" value="<%=current%>">
								<input type="hidden" id="SolarCurrent" value="<%=current%>">
								<!-- 								<input type="date" name="dates" id="dates" onchange="staticsDateWise();"> -->
							</div>

							<div id="chartdiv2"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
						<div class="tab-pane fade" id="ios">
							<!-- 							<input type="date" name="dateSolar" id="dateSolar" onchange="loadSoalrAmpere();"> -->
							<div id="chartdiv3"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<% } //session close
		%>

			
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
			<script src="assets/plugins/switchery/switchery.min.js"></script>
			<!-- 	<script src="assets/plugins/powerange/powerange.min.js"></script> -->
			<script src="assets/js/form-slider-switcher.demo.min.js"></script>
			<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
			<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
			<script src="assets/js/table-manage-colvis.demo.min.js"></script>
			<script src="assets/js/apps.min.js"></script>
			<!-- ================== END PAGE LEVEL JS ================== -->

			<script src="amcharts/gauge.js" type="text/javascript"></script>

			<script src="amcharts/serial.js" type="text/javascript"></script>
			<script src="js/doApplianceView.js"></script>
			<!-- ================== BEGIN PAGE LEVEL JS ================== -->
			<script src="assets/js/apps.min.js"></script>
			<!-- ================== END PAGE LEVEL JS ================== -->
			<script>
				$(document).ready(function() {
					App.init();
					loadSoalrAmpere();
					staticsDateWise();
				});
			</script>
</body>
</html>
