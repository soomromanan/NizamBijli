<%@page import="bal.ApplianceBal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
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
<script src="js/validation.js"></script>
<script src="amcharts/amcharts.js" type="text/javascript"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=true"></script>

<script>
	function setMoodValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		helloWorld(appliance_id);
	}

	function helloWorld(applianceValue) {
		var link = "DeviceReturnController?click=return&applianceId="
				+ applianceValue;
		document.getElementById('updateURL').href = link;
	}

	function setMoodalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		hello(appliance_id);
	}

	function hello(applianceValue) {
		var link = "DOInstallController?click=SAInstall&applianceId="
				+ applianceValue;
		document.getElementById('updateInstallURL').href = link;
	}
	function setMoodalNoSignalValue(appliance_id, customer_name) {
		var id = document.getElementById("applianceid").innerHTML = appliance_id;
		var name = document.getElementById("applianceid").innerHTML = customer_name;
		helloNoSignal(appliance_id);
	}

	function helloNoSignal(applianceValue) {
		var link = "DOInstallController?click=SAVarifySignals&applianceId="
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
	UserBean userbean = (UserBean) session.getAttribute("email");
	ApplianceBean bean = (ApplianceBean) request.getAttribute("bean");
	if (userbean == null) {
		response.sendRedirect("SolarHomeSystemLogin");
	} else {
		ApplianceBal.updateApplianceAlerts(bean.getApplianceId());
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
					<a href="javascript:;" class="btn btn-xs btn-white"
						data-dismiss="modal">Close</a> <a id="updateURL"
						onclick="setMoodValue('<%=bean.getApplianceId()%>', '<%=bean.getApplianceName()%>')"
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
					<a href="javascript:;" class="btn btn-xs btn-white"
						data-dismiss="modal">Close</a> <a id="updateInstallURL"
						onclick="setMoodalValue('<%=bean.getApplianceId()%>', '<%=bean.getApplianceName()%>')"
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
					<a href="javascript:;" class="btn btn-xs btn-white"
						data-dismiss="modal">Close</a> <a id="updateNoSignalURL"
						onclick="setMoodalNoSignalValue('<%=bean.getApplianceId()%>', '<%=bean.getApplianceName()%>')"
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
	<%
		
	%>
	<input type="hidden" id="appID" value="<%=bean.getApplianceId()%>">
	<input type="hidden" id="customer_id" value="<%=bean.getCustomerId()%>">
	<input type="hidden" id="gsmNumber"
		value="<%=bean.getApplianceGsmNo()%>">
	<!-- begin #page-container -->
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
			<h1 class="page-header" id="pageHeader"></h1>
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">

				<!-- begin col-12 -->
				<div class="col-md-12">
					<div class="col-md-8 ui-sortable" style="margin-top: 2%">
						<!-- begin panel -->
						<div class="panel panel-inverse" data-sortable-id="table-basic-7">
							<div class="panel-heading">
								<div class="panel-heading-btn"></div>
								<h4 class="panel-title">
									Appliance Detail/<span id="appName"></span>
								</h4>
							</div>

							<div class="panel-body">
								<div class="table-responsive" style="font-size: 13px">
									<table class="table">
										<tr>
											<th>Appliance IMEI Number</th>
											<!-- <td id="imeinumber"></td> -->
											<td><span id="spanimeinumber"></span> <input
												type="hidden" id="imeinumber" value="N/A" disabled
												style="background-color: white; border: none;" /></td>

										</tr>
										<tr>
											<th>GSM Number</th>
											<!-- <td id="gsm"></td> -->
											<td><span id="spangsm"></span> <input type="hidden"
												id="gsm" value="N/A" maxlength="12" disabled
												style="background-color: white; border: none;" /></td>
										</tr>

										<tr>
											<th>Consumer Number</th>
											<!-- <td id="consumer"></td> -->
											<td><span id="spanconsumer"></span> <input type="hidden"
												id="consumer" value="N/A" disabled
												style="background-color: white; border: none;" /> <span
												style="color: red" id="consumerNum"></span> <input
												type="button" class="btn btn-sm btn-success"
												id="saveConsumer" style="display: none" value="Save">
												<button class="btn  btn-danger" id="cancelEditConsumer"
													style="display: none">
													<i class="fa fa-times"></i>
												</button>

												<button class="btn btn-sm btn-success" id="editConsumer"
													style="margin-left: 13px;">
													<i class="fa fa-pencil"></i>
												</button> <span id="Consumerloader"></span></td>
										</tr>
										<tr>
											<th>Charging State</th>
											<td id="charging"></td>
										</tr>
										<tr>
											<th>Price</th>
											<td id="price"><b> PKR</b></td>
										</tr>
										<tr>
											<th>Customer Name</th>
											<td id="customerName"></td>
										</tr>
										<tr>
											<th>Hand over At</th>
											<td id="handover"></td>

										</tr>

										<tr id="returnedDate">
											<th>Returned Date</th>
											<td id="returned"></td>
										</tr>

										<tr>
											<th>District Officer</th>
											<td id="do"></td>

										</tr>
										<tr>
											<th>Field Officer</th>
											<td id="fo"></td>
										</tr>
										<tr>
											<th>Nizam Dost</th>
											<td id="nd"></td>
										</tr>

										<tr>
											<th>Transferred Status</th>
											<td id="transferStatus"></td>

										</tr>

										<tr>
											<th>Health Check</th>
											<td id="healthStatus"></td>

										</tr>

										<tr>
											<th>Replace Appliance</th>

											<td><input type="button" class="btn btn-sm btn-success"
												id="saveApp" style="display: none" value="Save">
												<button class="btn  btn-danger" id="cancelEdit"
													style="display: none">
													<i class="fa fa-times"></i>
												</button>

												<button class="btn btn-sm btn-success" id="editApp">
													<i class="fa fa-pencil"></i>
												</button> <span id="loader"></span></td>

										</tr>

										<tr id="rdpButton">
											<td id="installButton"></td>
											<td id="noSignalButton"></td>
										</tr>


										<tr id="returnDevice">

										</tr>



									</table>
								</div>
							</div>
						</div>
						<!-- end panel -->
					</div>



					<div class="col-md-4">

						<div class="example divider-inside-top-bottom"
							style="margin-top: 6%">

							<h4 style="text-align: center; margin-top: 4%;">Appliance
								Summary</h4>

							<div style="text-align: center;">

								<div class="my-fancy-container"
									style="background-color: #00aba9">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-clock-o fa-stack-2x"></i></span>

									<h4 id="headingSet">Last Update</h4>

									<div class="mask"></div>
									<span class="my-text" id="lastupdated" style="font-size: 12px;"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #ee4035">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-bolt fa-stack-2x"></i></span>

									<h4 id="headingSet">Battery</h4>

									<div class="mask"></div>
									<span class="my-text" id="batteryvoltage"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #a200ff">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-bolt fa-stack-2x"></i></span>
									<h4 id="headingSet">Battery</h4>
									<div class="mask"></div>
									<span class="my-text" id="batteryampere"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #1ba1e2">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-sun-o fa-stack-2x"></i></span>
									<h4 id="headingSet">Solar</h4>
									<div class="mask"></div>
									<span class="my-text" id="solarvoltage"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #f09609">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-sun-o fa-stack-2x"></i></span>
									<h4 id="headingSet">Solar</h4>
									<div class="mask"></div>
									<span class="my-text" id="solarampere"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #00aba9">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-tachometer fa-stack-2x"></i></span>
									<h4 id="headingSet">Load</h4>
									<div class="mask"></div>
									<span class="my-text" id="loadvoltage"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #ee4035">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-tachometer fa-stack-2x"></i></span>
									<h4 id="headingSet">Load</h4>
									<div class="mask"></div>
									<span class="my-text" id="loadcurrent"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #a200ff">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-fire fa-stack-2x"></i></span>
									<h4 id="headingSet">Temperature</h4>
									<div class="mask"></div>
									<span class="my-text" id="temprature"></span>
								</div>

								<div class="my-fancy-container"
									style="background-color: #1ba1e2">
									<span id="setImage" class="fa-stack fa-lg"> <i
										class="fa fa-signal fa-stack-2x"></i></span>
									<h4 id="headingSet">Signal Strength</h4>
									<div class="mask"></div>
									<span class="my-text" id="signalsrength"></span>
								</div>

							</div>

						</div>
					</div>
				</div>



				<div class="col-md-12 ui-sortable" style="margin-top: 2%">
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-heading">

							<h4 class="panel-title">MAP</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<div class="map-canvas-container " id="map" style="">
									<div id="googleMap" style="width: 100%; height: 384px;"></div>
								</div>
							</div>

						</div>
					</div>

				</div>





				<div class="col-md-12 ">

					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">District Summary</h4>
						</div>
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">

								<table class="table table-bordered">
									<thead>
										<tr>
											<th>District</th>
											<th>Avg Power Produced</th>
											<th>Avg Load Consumed</th>
											<th>Standard Avg Load Consumption</th>
											<th>Standard Avg Power Production</th>

										</tr>
									</thead>
									<tbody id="district_summary">

									</tbody>
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
						<li><a href="#batteryperformance" data-toggle="tab">
								<div class="text-center"></div> <span class="hidden-xs m-l-3">
									Battery Performance <span class="badge badge-inverse m-l-3"></span>
							</span>
						</a></li>
						<span><input
							style="height: 30px; margin: 5px; float: right;" type="date"
							name="dateSet" id="dateSet"
							onchange="loadSoalrAmpere(), staticsDateWise(), loadBatteryAndSolarVoltage(), loadBatteryAndSolarCurrent(), loadApplianceTemperature()"></span>
					</ul>



					<div id="ioniconsTabContenta" class="tab-content">
						<div class="tab-pane fade in active" id="default">

							<div>
								<input type="hidden" id="current" value=""> <input
									type="hidden" id="SolarCurrent" value=""> <span
									id="powertag"></span> <span id="powerproduced"></span> WH
								&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <span id="loadtag"></span>
								<span id="loadConsumed"></span> WH &nbsp; &nbsp; &nbsp; &nbsp;
								&nbsp; &nbsp; <span id="chargingtag"></span> <span id="hours"></span>

								Hours <span id="mins"></span> Mins


								<!--         <input type="date" name="dates" id="dates" onchange="staticsDateWise();"> -->
							</div>


							<div id="chartdiv2"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
						<div class="tab-pane fade" id="ios">
							<div id="chartdiv3"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>

						<div class="tab-pane fade" id="batteryperformance">
							<div id="chartdiv4"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>

							<div id="chartdiv5"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>

							<div id="chartdiv6"
								style="width: 100%; height: 400px; background-color: #FFFFFF;"></div>
						</div>
					</div>
				</div>
			</div>

			<!-- appliance summary div class row ended -->

			<!-- start row -->

			<div class="row">

				<div class="col-md-12">

					<!-- begin panel -->
					<div class="panel panel-inverse" data-sortable-id="table-basic-7">
						<div class="panel-body">
							<div class="table-responsive" style="font-size: 13px">
								<table id="alarmsTable" class="table table-bordered">
									<thead>
										<tr>
											<th>DateTime</th>
											<th>Alerts</th>
											<th>Status</th>
											<th>Resolved Time</th>
										</tr>
									</thead>
								</table>
							</div>
							<%
								} //session close
							%>
						</div>
					</div>
					<!-- end panel -->



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
	<script src="js/applianceview.js"></script>

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- second am chart  -->

	<script>
		$(document).ready(function() {
			App.init();
			loadSoalrAmpere();
			staticsDateWise();
			loadBatteryAndSolarVoltage();
			loadBatteryAndSolarCurrent();
			loadApplianceTemperature();
			FormSliderSwitcher.init();
			getDistrictSummary();
			getApplianceSummary();
			getApplianceStatus();
		});
	</script>
</body>
</html>
