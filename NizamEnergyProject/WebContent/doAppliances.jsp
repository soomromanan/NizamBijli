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

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/DataTables/css/data-table.css"
	rel="stylesheet" />
<link href="assets/plugins/icheck/skins/square/_all.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->


</head>
<body>
	<%--  data-salesman="<%=request.getParameter("salesman_id")%>" --%>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
		if (bean == null) {
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
					<li class="has-sub "><a href="DistrictOfficerDashboard">
							<i class="fa fa-laptop"></i> <span>Dashboard</span>
					</a></li>

					<li class="has-sub"><a href="LoanRequest"> 
							<i class="icon-note"></i> <span>Loan Request</span> 
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

		<!-- Start revoke Model Dialogue -->

		<div class="modal fade" id="modal-revoke-imei">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header success">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">Revoke IMEI</h4>
					</div>
					<div class="modal-body">
						<div class="alert m-b-0">
							<p>Are you sure you want to revoke imei ?</p>
						</div>

					</div>
					<div class="modal-footer">
						<a href="javascript:;" class="btn btn-sm btn-white"
							data-dismiss="modal">Close</a> <a id="revokeImei"
							class="btn btn-sm btn-success">Revoke</a>
					</div>
				</div>
			</div>
		</div>





		<!-- Start Model Dialogue -->
		<div class="modal fade" id="payingModal">
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
								style="pointer-events: none; cursor: default; opacity: 0.6;"
								onClick="validate()" id="payAmount">Done</a>
						</div>
						<div class="col-sm-4">
							<a href="javascript:;" class="btn btn-block btn-default"
								data-dismiss="modal">Close</a>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->

		<!-- begin #content -->
		<div id="content" class="content">
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">
				Deployment<span id="deployment_count"
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
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Deployment Table</h4>
						</div>
						<div class="panel-body">
							<ul class="nav nav-pills f-s-15 inline bordered round-corner">
								<li><a class="m-0 p-5 m-r-15" id="pendingg_li"> <input
										type="radio" name="filterrr" id="pending_pipeline"> <span
										class="label"
										style="background-color: #bdc3c7; color: white; font-weight: bold;">
									</span> <label for="pending_pipeline">Pending</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15" id="ready_to_asssign_li">
										<input type="radio" id="ready_to_asssign" name="filterrr">
										<span class="label"
										style="background-color: #2980b9; color: white; font-weight: bold;">
									</span> <label for="ready_to_asssign">Ready To Assign</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15" id="adp_li"> <input
										type="radio" id="adp" name="filterrr"> <span
										class="label"
										style="background-color: #1abc9c; color: black; font-weight: bold;">
									</span> <label for="adp">ADP</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15" id="rdp_li"> <input
										type="radio" id="rdp" name="filterrr"> <span
										class="label"
										style="background-color: #42a4f4; color: black; font-weight: bold;">
									</span> <label for="rdp">RDP</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15" id="installed_ns_li"> <input
										type="radio" id="installed_ns" name="filterrr"> <span
										class="label"
										style="background-color: #2ecc71; color: black; font-weight: bold;">
									</span> <label for="installed_ns">Installed-NS</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15" id="installed_li"> <input
										type="radio" id="installed" name="filterrr"> <span
										class="label"
										style="background-color: #2ecc71; color: black; font-weight: bold;">
									</span> <label for="installed">Installed</label>
								</a></li>
								<li><a class="m-0 p-5 m-r-15" id="returned_li"> <input
										type="radio" id="returned" name="filterrr"> <span
										class="label"
										style="background-color: #d35400; color: black; font-weight: bold;">
									</span> <label for="returned">Returned</label>
								</a></li>

								<li><a class="m-0 p-5 m-r-15"> <input type="radio"
										id="all_pipeline_apps" name="filterrr"> <label
										for="all_pipeline_apps">All</label>
								</a></li>
							</ul>
							<div class="table-responsive" style="font-size: 13px;">
								<table id="do-deployment-table"
									class="table table-hover table-striped not-last">
									<thead>
										<tr>
											<th>Customer Name</th>
											<th>Field Officer</th>
											<th>Nizam Dost Name</th>
											<th>Customer Number</th>
											<th>Appliance Name</th>
											<th>Appliance IMEI</th>
											<th>Appliance Status</th>
											<th>Transfer Status</th>
											<th>Action</th>
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

				<%
					} // session else closed
				%>

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
	<!--[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]-->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/DataTables/js/jquery.dataTables.js"></script>
	<script src="assets/plugins/DataTables/js/dataTables.colVis.js"></script>
	<script src="assets/js/table-manage-colvis.demo.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<script src="assets/plugins/icheck/icheck.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		var imeiFlag;
		var gsmFlag;
		var consumerFlag;
		var linkget;
		var value = "";
		var cID = "";
		var consumerNum = "";
		function getvalue(appid, customerId) {
			clearText();
			value = appid;
			cID = customerId;

		}

		function getValueAmount(imei) {
		}

		function setGSMNumber(gsm) {
			var imei = document.getElementById("imei").value;
			consumerNum = document.getElementById("consumerNum").value;

			document.getElementById("payAmount").href = "LoanPaymentController?click=assignImei&applianceId="
					+ value
					+ "&imei="
					+ imei
					+ "&gsm="
					+ gsm
					+ "&customerId="
					+ cID + "&consumerNum=" + consumerNum;

		}

		function validate() {

			var g = document.getElementById("gsmErrorMsg").innerHTML;
			var i = document.getElementById("imeiErrorMsg").innerHTML;
			var c = document.getElementById("consumerErrorMsg").innerHTML;

			var imei = document.getElementById("imei").value;
			var gsm = document.getElementById("gsm").value;
			consumerNum = document.getElementById("consumerNum").value;

			if ((imeiFlag == 1) && (gsmFlag == 1) && (consumerFlag == 1)) {

				document.getElementById("payAmount").href = "LoanPaymentController?click=assignImei&applianceId="
						+ value
						+ "&imei="
						+ imei
						+ "&gsm="
						+ gsm
						+ "&customerId=" + cID + "&consumerNum=" + consumerNum;

				var myUrl = window.location;
				window.location.replace(myUrl)

			} else if ((imeiFlag == 0) || (gsmFlag == 0) || (consumerFlag == 0)) {

				alert("kindly Refill data!");

			}

		}

		function clearText() {

			document.getElementById('consumerNum').value = "";
			document.getElementById('gsm').value = "";
			document.getElementById('imei').value = "";

			document.getElementById("gsmErrorMsg").innerHTML = "";
			document.getElementById("imeiErrorMsg").innerHTML = "";
			document.getElementById("consumerErrorMsg").innerHTML = "";

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

		function setMoodValue(appliance_id, customerId) {
			document.getElementById('revokeImei').onclick = function() {
				revokeConsumerNum(appliance_id, customerId);
			};
		}

		function revokeConsumerNum(appid, customerId) {

			var appId = appid;
			var csId = customerId;
			///	console.log(text_message + " ..... " + applianceId);
			$.ajax({
				url : 'LoanPaymentController',
				method : 'POST',

				data : {
					click : "revoke",
					applianceId : appId,
					customerId : csId
				},
				success : function(data) {
					console.log("added");
					var myUrl = window.location;
					window.location.replace(myUrl)
					//location.reload();
				}
			}) // end

		}

		var table;
		var cell;
		function filterPipeline() {
			table = $("#do-deployment-table")
					.DataTable(
							{
								destroy : true,
								"processing" : true,
								"serverSide" : true,
								"order" : [ [ 0, "asc" ] ],
								"ajax" : {
									"url" : "DoDeployment",
									"method" : "post",
									"data" : function(d) {
										d.action = action
										if (action === undefined) {
											d.action = "doDeployment"
										}

										if (filter != undefined) {
											d.filter = filter
										}
									},

									"dataSrc" : function(json) {
										$('.page-header span').text(
												json.deploymentCount)
										console.log('before');
										console.log(json.data);
										for ( var i = 0; i < json.data.length; i++) {
											json.data[i].eli = 'cvd'
											if (json.data[i].applianceActiveStatus == 1) {
												json.data[i].applianceActiveStatus = '<span class="label" style="background-color:#16a085; color:white;font-weight: bold;">Active</span>'
											} else if (json.data[i].applianceActiveStatus == 0) {
												json.data[i].applianceActiveStatus = '<span class="label" style="background-color:Red; color:white;font-weight: bold;">Inactive</span>'
											}

											if (json.data[i].applianceGivenStatus == 0) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#bdc3c7; color:white;font-weight: bold;">Pending</span>';
												json.data[i].assign = '<span class="fa fa-spinner fa-lg m-r-5 text-primary"> Applied</span>';
											} else if (json.data[i].applianceGivenStatus == 1) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#2980b9; color:white;font-weight: bold;">Ready To Assign</span>';
												json.data[i].assign = '<button class="btn btn-primary btn-xs m-r-5 m-b-5" style="width:100%" margin-left: 190%;" data-target="#payingModal" data-toggle="modal" onclick=getvalue('
														+ json.data[i].applianceId
														+ ','
														+ json.data[i].customerid
														+ ') data-applianceid='
														+ json.data[i].applianceId
														+ '>Assign</button>';
											} else if (json.data[i].applianceGivenStatus == 3) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Owned</span>';
												json.data[i].assign = '<span class="fa fa-ban fa-lg m-r-5 text-orange"> Assigned</span>';
											} else if (json.data[i].applianceGivenStatus == 4) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#d35400; color:black;font-weight: bold;">Returned</span>'
												json.data[i].assign = '<span class="fa fa-check fa-lg m-r-5 text-success"> Assigned</span>';
											} else if (json.data[i].applianceGivenStatus == 2) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">RDP</span>'
												json.data[i].assign = '<span class="fa fa-check fa-lg m-r-5 text-success"> Assigned</span>';
											}

											else if (json.data[i].applianceGivenStatus == 5) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#1abc9c; color:white;font-weight: bold;">Awaiting Downpayment</span>';
												json.data[i].assign = ' <a href="#modal-revoke-imei"'
														+ 'onclick="setMoodValue('
														+ json.data[i].applianceId
														+ ', '
														+ json.data[i].customerid
														+ ')"'
														+ 'style="text-decoration: none;" data-toggle="modal"'
														+ 'style="width:70%;">Revoke<span> </span></a>'

											} else if (json.data[i].applianceGivenStatus == 6) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:#2ecc71; color:white;font-weight: bold;">Installed</span>';
												json.data[i].assign = '<a href="ViewServlet?click=viewDoAppliance&id='
														+ json.data[i].applianceId
														+ '">View' + '</a>';

											} else if (json.data[i].applianceGivenStatus == 7) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Installed and NoSignal</span>';
												json.data[i].assign = '<a href="ViewServlet?click=viewDoAppliance&id='
														+ json.data[i].applianceId
														+ '">View' + '</a>';

											} else if (json.data[i].applianceGivenStatus == 10) {
												json.data[i].applianceGivenStatus = '<span class="label" style="background-color:green; color:white;font-weight: bold;">Pre-Payments</span>';
												json.data[i].assign = '<a href="ViewServlet?click=viewDoAppliance&id='
														+ json.data[i].applianceId
														+ '">View' + '</a>';

											}
											
											json.data[i].appliance = '<a href="ViewServlet?click=viewDoAppliance&id='
													+ json.data[i].applianceId
													+ '">'
													+ json.data[i].applianceName
													+ '</a>';
										}
										return json.data;
									}

								},

								"columns" : [ {
									"data" : "customerName"
								}, {
									"data" : "foName"
								}, {
									"data" : "salesmanName"
								}, {
									"data" : "customerNumber",
									"orderable" : false
								}, {
									"data" : "appliance",
									"orderable" : false
								}, {
									"data" : "applianceNumber",
									"orderable" : false
								}, {
									"data" : "applianceActiveStatus",
									"orderable" : false
								}, {
									"data" : "applianceGivenStatus",
									"orderable" : false
								}, {
									"data" : "assign",
									"orderable" : false
								} ],
								"rowCallback" : function(row, data) {
									//				if($.inArray(data.DT_RowId, selected) !== -1){
									$(row).data(
											'',
											'ViewServlet?click=viewDoAppliance&id='
													+ data.applianceId);
									$(row).addClass('link-not-last-cell')

									//				}
								},
							})

			$("#do-deployment-table tbody").on('click', 'td', function(event) {
				link = $(this).parent().data('link');
				if (link != undefined) {
					window.location = link;
				}
			});

			cell = $('td.details-control');

			$('#do-deployment-table tbody')
					.on(
							'click',
							'td.details-control',
							function() {
								var tr = $(this).closest('tr');
								var row = table.row(tr);
								console.log(row)

								if (row.child.isShown()) {
									// This row is already open - close it
									$(this)
											.html(
													'<a href="#"><span class="label label-success">+</span></a>')
									row.child.hide();
									tr.removeClass('shown');
								} else {
									// Open this row
									row.child(format(row.data())).show();
									$(this)
											.html(
													'<a href="#"><span class="label label-danger">-</span></a>')
									tr.addClass('shown');
								}
							});

		} //);  //  end function

		$(function() {

			//		 	Begin iCheck for filters
			$('[name="filterrr"]').each(function() {
				var id = $(this).attr('id')
				var radioClass;
				if (id == "pending_pipeline") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "ready_to_asssign") {
					radioClass = 'icheckbox_square-red'
				}
				if (id == "adp") {
					radioClass = 'icheckbox_square-orange'
				}
				if (id == "rdp") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "installed_ns") {
					radioClass = 'icheckbox_square-green'
				}
				if (id == "installed") {
					radioClass = 'icheckbox_square-purple'
				}
				if (id == "all_pipeline_apps") {
					radioClass = 'icheckbox_square-grey'
				}
				if (id == "returned") {
					radioClass = 'icheckbox_square-orange'
				}
				$(this).iCheck({
					radioClass : radioClass
				})
			})
			//		 	End iCheck for filters

			// Begin Counting of All Filters

			$.ajax({
				url : 'DoDeployment',
				method : 'POST',
				data : {
					action : "countFilters"
				},
				dataType : 'json',
				success : function(data) {
					console.log("count filters")
					console.log(data)
					if (data.pending !== undefined || data.pending !== 0) {
						$('#pendingg_li span').text(data.pending)
					}
					if (data.readyToAssign !== undefined
							|| data.readyToAssign !== 0) {
						$('#ready_to_asssign_li span').text(data.readyToAssign)
					}
					if (data.awaitingDownpayment !== undefined
							|| data.awaitingDownpayment !== 0) {
						$('#adp_li span').text(data.awaitingDownpayment)
					}
					if (data.installedNoSignal !== undefined
							|| data.installedNoSignal !== 0) {
						$('#installed_ns_li span').text(data.installedNoSignal)
					}
					if (data.rdp !== undefined || data.rdp !== 0) {
						$('#rdp_li span').text(data.rdp)
					}
					if (data.installed !== undefined || data.installed !== 0) {
						$('#installed_li span').text(data.installed)
					}
					if (data.returned !== undefined || data.installed !== 0) {
						$('#returned_li span').text(data.returned)
					}
				}

			})

			// Begin Quick Filters

			$('[name="filterrr"]').on('ifChecked', function() {
				if ($(this).attr('id') === 'all_pipeline_apps') {
					action = "doDeployment"
				}

				if ($(this).attr('id') === 'pending_pipeline') {
					action = "filterPipeline"
					filter = "pending"

				} else if ($(this).attr('id') === 'ready_to_asssign') {
					action = "filterPipeline"
					filter = "readyToAssign"

				} else if ($(this).attr('id') === 'adp') {
					action = "filterPipeline"
					filter = "awaitingDownpayment"

				} else if ($(this).attr('id') === 'installed') {
					action = "filterPipeline"
					filter = "installed"
				} else if ($(this).attr('id') === 'installed_ns') {
					action = "filterPipeline"
					filter = "installed_ns"
				} else if ($(this).attr('id') === 'rdp') {
					action = "filterPipeline"
					filter = "rdp"
				} else if ($(this).attr('id') === 'returned') {
					action = "filterPipeline"
					filter = "returned"
				} else {
					filter = undefined
				}

				filterPipeline()
			})

			// End Quick Filters
			$('#all_pipeline_apps').iCheck('check')

			$("#do-deployment-table tbody").on('click', 'tr.link>td',
					function(event) {
						link = $(this).parent().data('link');
						if (link != undefined) {
							window.location = link;
						}
					});

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
					if ($(this).val().length == 14) {

						$.ajax({
							url : "AddApplianceController",
							type : "post",
							data : {
								action : "testImei",
								testImeiValue : ($(this).val())
							},
							success : function(obj) {
								if (obj.length <= 4) {
									imeiBoolean = "1"
								}

							}
						})

					} else if ($(this).val().length < 15) {
						$("#payAmount").css({
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
								if ((obj.length <= 4) && imeiBoolean == "1") {

									$("#payAmount").css({
										"pointer-events" : "",
										"cursor" : "",
										"opacity" : ""
									})
								}
							}
						})
					} else if ($(this).val().length < 12) {
						$("#payAmount").css({
							"pointer-events" : "none",
							"cursor" : "default",
							"opacity" : "0.6"

						})
					}
				});
	</script>

	<script>
		$(document).ready(function() {
			App.init();
			filterPipeline();
		});
	</script>

</body>


</html>