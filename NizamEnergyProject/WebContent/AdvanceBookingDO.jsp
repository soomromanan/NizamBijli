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
<script>
	function setNotIntrested(appid, customerId) {

		var appId = appid;
		var csId = customerId;
		///	console.log(text_message + " ..... " + applianceId);
		$.ajax({
			url : 'ApplianceController',
			method : 'POST',

			data : {
				action : "setNotIntrested",
				applianceId : appId

			},
			success : function(data) {
				console.log("added");
				var myUrl = window.location;
				window.location.replace(myUrl)
				//location.reload();
			}
		}) // end

	}

	function setNotIntrestedModal(appliance_id) {
		document.getElementById('cancelRequest').onclick = function() {

			setNotIntrested(appliance_id);

		};
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

	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-sidebar-fixed page-header-fixed page-with-light-sidebar page-with-wide-sidebar">

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
							<small> District Officer </small>
						</div>
					</li>
				</ul>
				<!-- end sidebar user -->

				<!-- Start Model Dialogue -->



				<!-- begin sidebar nav -->
				<ul class="nav">
					<li class="has-sub "><a href="DistrictOfficerDashboard">
							<i class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> 
							<i class="icon-note"></i> <span>Loan Request</span> 
							<span class="badge pull-right" id="do_unseen_loan_request_count"></span>
					</a></li>
					<li class="has-sub active"><a href="DoWiseAdvanceBooking"><i
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

		<div class="modal fade" id="consumerEdit">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Edit Consumer</h4>
					</div>

					<div class="modal-body">
						<form onsubmit="return false;" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<label for="#amount" class="col-sm-3 control-label">Consumer
										Number</label>

									<div class="col-sm-9">
										<span style="color: red;" id="consumerEditErrorMsg"></span> <input
											type="number" class="form-control" id="consumerNumEdit"
											onChange="checkConsumerEdit()" maxlength="13"
											placeholder="Customer Cnic" />
										<!-- onKeyUp="getValueAmount(this.value);"/> -->
									</div>
								</div>

							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<div class="col-sm-8">
							<a href="javascript:;" class="btn btn-block btn-success"
								style="pointer-events: none; cursor: default; opacity: 0.6;"
								onClick="editConsumer()" id="editConsumer">Done</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:;" class="btn btn-block btn-default"
								data-dismiss="modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="advanceMoodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Assign Consumer</h4>
					</div>

					<div class="modal-body">
						<form onsubmit="return false;" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<label for="#amount" class="col-sm-3 control-label">Consumer
										Number</label>

									<div class="col-sm-9">
										<span style="color: red;" id="consumerErrorMsg"></span> <input
											type="number" class="form-control" id="consumerNum"
											onChange="checkConsumer()" maxlength="13"
											placeholder="Customer Cnic" />
										<!-- onKeyUp="getValueAmount(this.value);"/> -->
									</div>
								</div>

							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<div class="col-sm-8">
							<a href="javascript:;" class="btn btn-block btn-success"
								style="pointer-events: none; cursor: default; opacity: 0.6;"
								onClick="validate()" id="payAmounttt">Done</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:;" class="btn btn-block btn-default"
								data-dismiss="modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="modal fade" id="modalNotIntrested">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header success">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Cancel Request</h4>
					</div>
					<div class="modal-body">
						<div class="alert m-b-0">
							<p>Are you sure you want to Cancel Request ?</p>
						</div>

					</div>
					<div class="modal-footer">
						<a href="javascript:;" class="btn btn-sm btn-white"
							data-dismiss="modal">NO</a> <a id="cancelRequest"
							class="btn btn-sm btn-success">YES</a>
					</div>
				</div>
			</div>
		</div>


		<!-- Start Model Dialogue -->
		<div class="modal fade" id="BookingMoodal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Assign appliance</h4>
					</div>

					<div class="modal-body">
						<form onsubmit="return false;" class="form-horizontal">
							<fieldset>

								<div class="form-group">
									<label for="#amount" class="col-sm-3 control-label">Appliance
										IMEI</label>
									<div class="col-sm-9">
										<span style="color: red;" id="imeiErrorMsg"></span> <input
											type="number" class="form-control" id="imei"
											onChange="checkImei()" maxlength="15"
											placeholder="Appliance IMEI number" />
										<!-- onKeyUp="getValueAmount(this.value);"/> -->
									</div>
								</div>
								<div class="form-group">
									<label for="#amount" class="col-sm-3 control-label">Appliance
										GSM</label>
									<div class="col-sm-9">
										<span style="color: red;" id="gsmErrorMsg"></span> <input
											type="number" class="form-control" id="gsm" maxlength="12"
											placeholder="923XXXXXXXXX" onChange="checkGsm()" />
										<!--  onKeyUp="setGSMNumber(this.value);" -->
									</div>
								</div>

							</fieldset>
						</form>
					</div>
					<div class="modal-footer">
						<div class="col-sm-8">
							<a href="javascript:;" class="btn btn-block btn-success"
								style="pointer-events:"
								"; cursor: ""; opacity: "";"
								onClick="validateBooking()"
								id="doneBooking">Done</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:;" class="btn btn-block btn-default"
								data-dismiss="modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</div>







		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin page-header -->
			<div class="panel-heading">
				<h1 class="page-header">
					Pre Payments<span id="loan_apps_count"
						style="font-size: 24px; margin-left: 10px; font-weight: bold"></span>
				</h1>
			</div>
			<!-- end page-header -->

			<div class="row" style="padding-top: 2%;">
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">

						<div class="panel-heading">
							<h4 class="panel-title">Pre Payments</h4>
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
								</span> <label for="accepted_apps">Accepted/RTA</label>
							</a></li>

							<li><a class="m-0 p-5 m-r-15" id="varified_apps_li"> <input
									type="radio" id="varified_apps" name="filterApps"> <span
									class="label"
									style="background-color: green; color: black; font-weight: bold;">
								</span> <label for="varified_apps">Verified/ADP</label>
							</a></li>
							<li><a class="m-0 p-5 m-r-15" id="booked_apps_li"> <input
									type="radio" id="booked_apps" name="filterApps"> <span
									class="label"
									style="background-color: green; color: black; font-weight: bold;">
								</span> <label for="booked_apps">BOOKED</label>
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
											<th>CNIC</th>
											<th>Monthly Installment</th>
											<th>Installment Scheme</th>
											<th>City, District</th>
											<th>Nizam Dost</th>
											<th>Field Officer</th>
											<th>View</th>
											<th>Action</th>
											<th>Status</th>
											<th>Cancel Request</th>
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

	<script type="text/javascript" src="assets/js/sendSaDoMessage.js"></script>

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
		var value = "";
		var cID = "";

		function getvalue(appid, customerId) {
			clearText();
			value = appid;
			cID = customerId;

		}

		function validate() {

			var c = document.getElementById("consumerErrorMsg").innerHTML;

			consumerNum = document.getElementById("consumerNum").value;

			if ((consumerFlag == 1)) {

				document.getElementById("payAmounttt").href = "LoanPaymentController?click=assignConsumerDO&applianceId="
						+ value
						+ "&customerId="
						+ cID
						+ "&consumerNum="
						+ consumerNum; //do new link

				var myUrl = window.location;
				window.location.replace(myUrl)

			} else if (consumerFlag == 0) {

				alert("kindly Refill data!");

			}

		}

		function editConsumer() {

			var c = document.getElementById("consumerEditErrorMsg").innerHTML;

			consumerNum = document.getElementById("consumerNumEdit").value;

			if ((consumerFlag == 1)) {

				document.getElementById("editConsumer").href = "LoanPaymentController?click=editConsumerDO&applianceId="
						+ value
						+ "&customerId="
						+ cID
						+ "&consumerNum="
						+ consumerNum;

				var myUrl = window.location;
				window.location.replace(myUrl)

			} else if (consumerFlag == 0) {

				alert("kindly Refill data!");

			}

		}
		function validateBooking() {

			var g = document.getElementById("gsmErrorMsg").innerHTML;
			var i = document.getElementById("imeiErrorMsg").innerHTML;

			var imei = document.getElementById("imei").value;
			var gsm = document.getElementById("gsm").value;

			if ((imeiFlag == 1) && (gsmFlag == 1)) {

				document.getElementById("doneBooking").href = "LoanPaymentController?click=assignBookingDO&applianceId="
						+ value + "&imei=" + imei + "&gsm=" + gsm;

				var myUrl = window.location;
				window.location.replace(myUrl)

			} else if ((imeiFlag == 0) || (gsmFlag == 0)) {

				alert("kindly Refill data!");

			}

		}

		function clearText() {

			document.getElementById('consumerNum').value = "";

			document.getElementById("consumerErrorMsg").innerHTML = "";

		}

		function checkConsumer() {
			var consumer = document.getElementById('consumerNum').value;
			$
					.ajax({
						url : 'LoanPaymentController',
						method : 'POST',
						data : {
							click : "checkConsumer",
							Consumer : consumer
						},
						success : function(data) {
							console.log("data check consumer:" + data);
							if (data == 1) {
								document.getElementById("consumerErrorMsg").innerHTML = "";
								consumerFlag = 1;
							}
							if (data == 0) {
								document.getElementById("consumerErrorMsg").innerHTML = "Consumer Already Exists!";
								consumerFlag = 0;
							}
						}
					});
		}

		function checkConsumerEdit() {
			var consumer = document.getElementById('consumerNumEdit').value;
			$
					.ajax({
						url : 'LoanPaymentController',
						method : 'POST',
						data : {
							click : "checkConsumer",
							Consumer : consumer
						},
						success : function(data) {
							if (data == 1) {
								document.getElementById("consumerEditErrorMsg").innerHTML = "";
								consumerFlag = 1;
							}
							if (data == 0) {
								document.getElementById("consumerEditErrorMsg").innerHTML = "Consumer Already Exists!";
								consumerFlag = 0;
							}
						}
					});
		}

		function checkImei() {
			var imei = document.getElementById('imei').value;

			$
					.ajax({

						url : 'LoanPaymentController',
						method : 'POST',

						data : {
							click : "checkImei",
							Imei : imei
						},

						success : function(data) {

							if (data == 1) {

								document.getElementById("imeiErrorMsg").innerHTML = "";

								imeiFlag = 1;
							}
							if (data == 0) {
								document.getElementById("imeiErrorMsg").innerHTML = "Imei Already Exixts!";
								imeiFlag = 0;
							}
						}

					});
		}

		function checkGsm() {
			var gsm = document.getElementById('gsm').value;

			$
					.ajax({

						url : 'LoanPaymentController',
						method : 'POST',

						data : {
							click : "checkGsm",
							GSM : gsm
						},

						success : function(data) {

							if (data == 1) {

								document.getElementById("gsmErrorMsg").innerHTML = "";

								gsmFlag = 1;
							}
							if (data == 0) {
								document.getElementById("gsmErrorMsg").innerHTML = "Gsm Already Exists!";
								gsmFlag = 0;
							}
						}
					});
		}

		$("#consumerNum").on(
				"keyup keypress blur change",
				function(e) {
					if (e.which != 8 && e.which != 0
							&& (e.which<48 || e.which>57)) {
						return false;
					} else {
						if ($(this).val().length >= parseInt($(this).attr(
								'maxlength'))
								&& (e.which != 8 && e.which != 0)) {
							return false;
						}
					}
					if ($(this).val().length == 13) {
						$.ajax({
							url : "AddApplianceController",
							type : "post",
							data : {
								action : "testConsumer",
								testConsumer : ($("#consumerNum").val())
							},
							success : function(obj) {
								if ((obj.length >= 7)) {

									$("#payAmounttt").css({
										"pointer-events" : "",
										"cursor" : "",
										"opacity" : ""
									})
								}
								console.log("enable this shit" + obj);

								/* else{alert("IMEI is not valid")} */
								//						alert(obj.Name)
								console.log(obj.length)
							}
						})
					} else if ($(this).val().length < 12) {
						$("#payAmounttt").css({
							"pointer-events" : "none",
							"cursor" : "default",
							"opacity" : "0.6"

						})
					}
				});

		$("#consumerNumEdit").on(
				"keyup keypress blur change",
				function(e) {
					if (e.which != 8 && e.which != 0
							&& (e.which<48 || e.which>57)) {
						return false;
					} else {
						if ($(this).val().length >= parseInt($(this).attr(
								'maxlength'))
								&& (e.which != 8 && e.which != 0)) {
							return false;
						}
					}
					if ($(this).val().length == 13) {
						$.ajax({
							url : "AddApplianceController",
							type : "post",
							data : {
								action : "testConsumer",
								testConsumer : ($("#consumerNumEdit").val())
							},
							success : function(obj) {
								if ((obj.length >= 7)) {

									$("#editConsumer").css({
										"pointer-events" : "",
										"cursor" : "",
										"opacity" : ""
									})
								}
								console.log("enable this shit" + obj);

								/* else{alert("IMEI is not valid")} */
								//						alert(obj.Name)
								console.log(obj.length)
							}
						})
					} else if ($(this).val().length < 12) {
						$("#editConsumer").css({
							"pointer-events" : "none",
							"cursor" : "default",
							"opacity" : "0.6"

						})
					}
				});

		var imeiBoolean = "0";

		$("#imei").on(
				'keyup keypress blur change',
				function(e) {
					if ((e.which != 8) && (e.which != 0)
							&& (e.which<48 || e.which>57)) {
						return false;
					} else {
						if ($(this).val().length >= parseInt($(this).attr(
								'maxlength'))
								&& (e.which != 8 && e.which != 0)) {
							return false;
						}
					}
					if ($(this).val().length == 15) {

						$.ajax({
							url : "AddApplianceController",
							type : "post",
							data : {
								action : "testImei",
								testImeiValue : ($(this).val())
							},
							success : function(obj) {
								console.log(obj + "imei obj")
								if (obj.length <= 4) {
									imeiBoolean = "1"
								}
								console.log(obj.length + "imei obj.length");
							}
						})

					} else if ($(this).val().length < 15) {
						$("#doneBooking").css({
							"pointer-events" : "none",
							"cursor" : "default",
							"opacity" : "0.6"

						})

						imeiBoolean = "0";
					}
				});

		$("#gsm").on(
				"keyup keypress blur change",
				function(e) {
					if (e.which != 8 && e.which != 0
							&& (e.which<48 || e.which>57)) {
						return false;
					} else {
						if ($(this).val().length >= parseInt($(this).attr(
								'maxlength'))
								&& (e.which != 8 && e.which != 0)) {
							return false;
						}
					}
					if ($(this).val().length == 11) {
						$.ajax({
							url : "AddApplianceController",
							type : "post",
							data : {
								action : "testGsmNumber",
								testNumber : ($("#gsm").val())
							},
							success : function(obj) {
								console.log(obj + "gsm obj")
								if ((obj.length <= 4) && imeiBoolean == "1") {

									$("#doneBooking").css({
										"pointer-events" : "",
										"cursor" : "",
										"opacity" : ""
									})
								}
								console.log(obj.length + "gsm obj.length");
								console.log("enable this shit");

								/* else{alert("IMEI is not valid")} */
								//						alert(obj.Name)
								console.log(obj.length)
							}
						})
					} else if ($(this).val().length < 12) {
						$("#doneBooking").css({
							"pointer-events" : "none",
							"cursor" : "default",
							"opacity" : "0.6"

						})
					}
				});

		// 		$(function() {
		function filterDOAdvanceApplications() {
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
											d.action = "getPrePaymentDO"
										}

										if (filter != undefined) {
											d.filter = filter
										}
									},
									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.loan_apps_count)
										for ( var i = 0; i <= json.data.lenght; i++) {
											if (json.data[i].instalment) {
												json.data[i].instalment = json.data[i].instalment
														+ "<b> PKR</b>"
											}
										}
										$.each(json.data,
														function(e) {

															if (json.data[e].status == 10) {
																text = 'Pending';
																color = '#bdc3c7';
																console

																.log(json.data);

																json.data[e].assign = 'N/A';
															
																		json.data[e].view = '<a href="ViewServlet?click=doCustomerView&cnic='
																			+ json.data[e].customer_cnic
																			+ '&id='
																			+ json.data[e].customer_id
																			+ '&applianceId='
																			+ json.data[e].eligibility_id
																			+ '">VIEW'
																			+ '</a>';
																		
																json.data[e].cancelRequest = ' <a href="#modalNotIntrested"'
																		+ 'onclick="setNotIntrestedModal('
																		+ json.data[e].appliance_id

																		+ ')"'
																		+ 'style="text-decoration: none;" data-toggle="modal"'
																		+ 'style="width:70%;">Cancel<span> </span></a>';

															} else if (json.data[e].status == 11) {
																text = 'Accepted/RTA';
																color = '#2980b9';
																json.data[e].view = '<a href="ViewServlet?click=doCustomerView&cnic='
																	+ json.data[e].customer_cnic
																	+ '&id='
																	+ json.data[e].customer_id
																	+ '&applianceId='
																	+ json.data[e].eligibility_id
																	+ '">VIEW'
																	+ '</a>';
																json.data[e].assign = '<button class="btn btn-primary btn-xs m-r-5 m-b-5" style="width:100%" margin-left: 190%;" data-target="#advanceMoodal" data-toggle="modal" onclick=getvalue('
																		+ json.data[e].appliance_id
																		+ ','
																		+ json.data[e].customer_id
																		+ ') data-applianceid='
																		+ json.data[e].appliance_id
																		+ '>Assign</button>';

																json.data[e].cancelRequest = ' <a href="#modalNotIntrested"'
																		+ 'onclick="setNotIntrestedModal('
																		+ json.data[e].appliance_id
																		+ ')"'
																		+ 'style="text-decoration: none;" data-toggle="modal"'
																		+ 'style="width:70%;">Cancel<span> </span></a>';
															} else if (json.data[e].status == 12) {
																text = 'Verified/ADP';
																color = '#2ecc71';
																json.data[e].view = '<a href="ViewServlet?click=doCustomerView&cnic='
																	+ json.data[e].customer_cnic
																	+ '&id='
																	+ json.data[e].customer_id
																	+ '&applianceId='
																	+ json.data[e].eligibility_id
																	+ '">VIEW'
																	+ '</a>';
																json.data[e].assign = '<button class="btn btn-primary btn-xs m-r-5 m-b-5" style="width:100%" margin-left: 190%;" data-target="#consumerEdit" data-toggle="modal" onclick=getvalue('
																		+ json.data[e].appliance_id
																		+ ','
																		+ json.data[e].customer_id
																		+ ') data-applianceid='
																		+ json.data[e].appliance_id
																		+ '>Edit</button>';

																json.data[e].cancelRequest = ' <a href="#modalNotIntrested"'
																		+ 'onclick="setNotIntrestedModal('
																		+ json.data[e].appliance_id
																		+ ')">Cancel'
																		+ '</a>';
															} else if (json.data[e].status == 9) {
																text = 'CC Verification';
																color = '#2ecc71';
																json.data[e].view = '<a href="ViewServlet?click=doCustomerView&cnic='
																	+ json.data[e].customer_cnic
																	+ '&id='
																	+ json.data[e].customer_id
																	+ '&applianceId='
																	+ json.data[e].eligibility_id
																	+ '">VIEW'
																	+ '</a>';
																json.data[e].assign = 'N/A';

																json.data[e].cancelRequest = ' <a href="#modalNotIntrested"'
																		+ 'onclick="setNotIntrestedModal('
																		+ json.data[e].appliance_id
																		+ ')"'
																		+ 'style="text-decoration: none;" data-toggle="modal"'
																		+ 'style="width:70%;">Cancel<span> </span></a>';
																//json.data[e].assign = '<span class="fa fa-spinner fa-lg m-r-5 text-primary"> Applied</span>';
															} else if (json.data[e].status == 13) {
																text = 'Booked';
																color = '#2ecc71';
																json.data[e].view = '<a href="ViewServlet?click=doCustomerView&cnic='
																	+ json.data[e].customer_cnic
																	+ '&id='
																	+ json.data[e].customer_id
																	+ '&applianceId='
																	+ json.data[e].eligibility_id
																	+ '">VIEW'
																	+ '</a>';
																json.data[e].assign = ' <a href="#BookingMoodal"'
																		+ 'onclick="getvalue('
																		+ json.data[e].appliance_id
																		+ ', '
																		+ json.data[e].customer_id
																		+ ')"'
																		+ 'style="text-decoration: none;" data-toggle="modal"'
																		+ 'style="width:70%;">Assign<span> </span></a>';

																json.data[e].cancelRequest = ' <a href="#modalNotIntrested"'
																		+ 'onclick="setNotIntrestedModal('
																		+ json.data[e].appliance_id
																		+ ')"'
																		+ 'style="text-decoration: none;" data-toggle="modal"'
																		+ 'style="width:70%;">Cancel<span> </span></a>';

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
									"data" : "customer_cnic"
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
									"data" : "view"
								}, {
									"data" : "assign"
								}, {
									"data" : "status",
									"orderable" : false,
									"searchable" : false

								}, {
									"data" : "cancelRequest"
								} ],

								"rowCallback" : function(row, data) {
									//					if($.inArray(data.DT_RowId, selected) !== -1){

									$(row).data(
											'link',
											'ViewServlet?click=viewRequest&cnic='
													+ data.customer_cnic
													+ '&id=' + data.customer_id
													+ '&appName='
													+ data.appliance_name
													+ '&appId='
													+ data.eligibility_id);
									$(row).addClass('link-not-last-cell');
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
				if (id == "booked_apps") {
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
					action : "countPPFiltersDO"
				},
				dataType : 'json',
				success : function(data) {
					console.log("count filters")
					console.log(data)

					if (data.ccVerification !== undefined
							|| data.ccVerification !== 0) {
						$('#cc_verify_apps_li span').text(data.ccVerification)

					}
					if (data.pending !== undefined || data.pending !== 0) {
						$('#pending_apps_li span').text(data.pending)
					}
					if (data.accepted !== undefined || data.accepted !== 0) {
						$('#accepted_apps_li span').text(data.accepted)
					}
					if (data.varified !== undefined || data.varified !== 0) {
						$('#varified_apps_li span').text(data.varified)
					}
					if (data.booked !== undefined || data.booked !== 0) {
						$('#booked_apps_li span').text(data.booked)
					}

				}

			})

			// Begin Quick Filters

			$('[name="filterApps"]').on('ifChecked', function() {
				if ($(this).attr('id') === 'all_apps') {
					action = "getPrePaymentDO"
				}

				if ($(this).attr('id') === 'pending_apps') {
					action = "filterPrePaymentsDO"
					filter = "pending"

				} else if ($(this).attr('id') === 'accepted_apps') {
					action = "filterPrePaymentsDO"
					filter = "accepted"
				} else if ($(this).attr('id') === 'varified_apps') {
					action = "filterPrePaymentsDO"
					filter = "varified"
				} else if ($(this).attr('id') === 'booked_apps') {
					action = "filterPrePaymentsDO"
					filter = "booked"
				} else if ($(this).attr('id') === 'cc_verify_apps') {
					action = "filterPrePaymentsDO"
					filter = "ccVerification"
				} else {
					filter = undefined
				}

				filterDOAdvanceApplications();
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
			filterDOAdvanceApplications();
		});
	</script>


</body>


</html>
