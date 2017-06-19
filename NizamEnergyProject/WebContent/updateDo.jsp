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
<!-- ================== END BASE CSS STYLE ================== -->
<script src="assets/plugins/parsley/dist/parsley.js"></script>
<!-- ================== BEGIN PAGE CSS STYLE ================== -->
<link href="assets/plugins/morris/morris.css" rel="stylesheet" />
<script type="text/javascript" src="js/superAdminDOForm.js"></script>
<!-- ================== END PAGE CSS STYLE ================== -->


<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->

<!-- ================== BEGIN BASE JS ================== -->



<!-- <script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
[if lt IE 9]>
		<script src="assets/crossbrowserjs/html5shiv.js"></script>
		<script src="assets/crossbrowserjs/respond.min.js"></script>
		<script src="assets/crossbrowserjs/excanvas.min.js"></script>
	<![endif]
<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script> -->


<script src="js/validation.js"></script>

<script>
	$(document).ready(
			function() {
				$('input').on(
						'keydown',
						function(event) {
							if (this.selectionStart == 0 && event.keyCode >= 65
									&& event.keyCode <= 90 && !(event.shiftKey)
									&& !(event.ctrlKey) && !(event.metaKey)
									&& !(event.altKey)) {
								var $t = $(this);
								event.preventDefault();
								var char = String.fromCharCode(event.keyCode);
								$t
										.val(char
												+ $t.val().slice(
														this.selectionEnd));
								this.setSelectionRange(1, 1);
							}
						});
			});

	function doContactCall() {

		var mob = document.getElementById("dophone");
		var rmob = document.getElementById("dophoneResult");
		var patt4 = /^[0-9]{11}$/;
		if (!patt4.test(mob.value)) {
			rmob.innerHTML = "Invalid number";
			ContactChecker = 0;
		} else {
			rmob.innerHTML = "";
			ContactChecker = 1;
		}

	}

	function doFirstNameCall() {

		var mob = document.getElementById("firstname");
		var rmob = document.getElementById("doFirstNameResult");
		var patt4 = /^[a-z A-Z]+$/;
		if (!patt4.test(mob.value)) {
			rmob.innerHTML = "Invalid name";
		} else {
			rmob.innerHTML = "";
		}

	}

	function doAddressCall() {
		alert("doAddressCall");
		var mob = document.getElementById("address");
		var rmob = document.getElementById("addressResult");
		//var patt4=/^[a-z A-Z]+$/;
		alert(mob.value);
		if (mob.value == "" || mob.value.length < 5) {
			alert("InValid");
			rmob.innerHTML = "Invalid Address";
		} else {
			alert("valid");
			rmob.innerHTML = "";
		}

	}

	function doEmailCall() {

		var mob = document.getElementById("doemail");
		var rmob = document.getElementById("doemailResult");
		var patt4 = /^[a-zA-Z0-9.!#$%&'*+=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
		if (!patt4.test(mob.value)) {
			rmob.innerHTML = "Invalid email";
			ContactChecker = 0;
		} else {
			rmob.innerHTML = "";
			ContactChecker = 1;
		}

	}
</script>




<script>
	var doId;

	var request;
	var requestphone;
	function emailcheck() {

		var v = document.DO_form.doemail.value;
		var url = "checkemail.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfo;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfo() {

		if (request.readyState == 4) {

			var val = request.responseText;

			if (val >= 1) {
				$('#messageemailvalid').show();
				$('#messageemailvalid').delay(5000).fadeOut('slow');
				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function emailphone() {

		var v = document.DO_form.dophone.value;
		var url = "checkphone.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfophone;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfophone() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#messagephonevalid').show();
				$('#messagephonevalid').delay(5000).fadeOut('slow');

				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function chechcnic() {

		var v = document.DO_form.docnic.value;
		var url = "chechcnicDo.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfocnic;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnic() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#messagenicvalid').show();
				$('#messagenicvalid').delay(5000).fadeOut('slow');

				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	//SALESMAN SCRIPT

	function emailchecknoc() {

		var v = document.Salesman_form.email_noc.value;
		var url = "checkemail.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfonoc;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfonoc() {

		if (request.readyState == 4) {

			var val = request.responseText;

			if (val >= 1) {
				$('#messageemailvalid_noc').show();
				$('#messageemailvalid_noc').delay(5000).fadeOut('slow');
				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function emailphonenoc() {

		var v = document.Salesman_form.phone_noc.value;

		var url = "checkphone.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfophonenoc;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfophonenoc() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#messagephonevalid_noc').show();
				$('#messagephonevalid_noc').delay(5000).fadeOut('slow');

				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function chechcnicnoc() {

		var v = document.Salesman_form.cnic_noc.value;
		var url = "chechcnicDo.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfocnicnoc;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnicnoc() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#messagenicvalid_noc').show();
				$('#messagenicvalid_noc').delay(5000).fadeOut('slow');

				$('#submit').prop('disabled', true);

			} else {
				$('#submit').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function checkphone() {

		var v = document.salesman.salesphone.value;
		var size = document.salesman.salesphone.value.length;
		if (size < 11) {
			$('#salesphonnumber').show();
			$('#salesphonnumber').delay(5000).fadeOut('slow');
			$('#click').attr('disabled', 'disabled');

		}
		var url = "salesmanphonecheck.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfophone;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfophone() {

		if (request.readyState == 4) {

			var val = request.responseText;

			if (val >= 1) {
				$('#messagephonevalid').show();

				$('#messagephonevalid').delay(5000).fadeOut('slow');
				$('#click_Salesman').attr('disabled', 'disabled');
			} else {
				$('#click_Salesman').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function getDistricts() {

		console.log('getDistricts() called.. ');

		$.ajax({
			url : 'AddUsersServlet',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'getAllDistricts',
				districtid : $('#district').val()
			},
			success : function(json) {
				//		console.log('districts ... ' + json.data);
				$.each(json.data, function(e) {
					var option = document.createElement('option');
					$(option).val(json.data[e].district_id);
					$(option).text(json.data[e].district_name);
					$('#district').append(option);
				});
			}
		});
	}

	function changecity() {
		var district_id = $('#district_so').val();

		/*         	$.ajax({
		 type : 'GET',
		 url : 'getCitySo.jsp',
		 dataType: 'application/json',
		 data : ({
		 district_id : district_id
		 }),
		 success : function(data, event) {
		 alert(data);
		
		 }

		 }); */
		$.ajax({
			type : "GET",
			url : 'getCitySo.jsp',
			data : ({
				district_id : district_id
			}),
			success : function(data) {

				var myData = JSON.parse(data);

				$(document).ready(
						function() {
							var $grouplist = $('#city');
							$.each(myData, function() {
								$(
										'<option value='+this.City_id+'>'
												+ this.City_name + '</option>')
										.appendTo($grouplist);
							});
						});

			}
		});

	}

	function getUrlParameter(sParam) {
		var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL
				.split('&'), sParameterName, i;

		for (i = 0; i < sURLVariables.length; i++) {
			sParameterName = sURLVariables[i].split('=');

			if (sParameterName[0] === sParam) {
				return sParameterName[1] === undefined ? true
						: sParameterName[1];
			}
		}
	};

	function getUploadedData() {

		doId = getUrlParameter('doId');

		$
				.ajax({
					url : 'UserFormsDataController',
					dataType : 'json',
					method : 'POST',
					data : {
						action : "doInfo",
						id : doId
					},
					success : function(data) {
						$('#firstname').val(data.doDetail.user_name);
						$('#joiningDate').val(data.doDetail.joining_date);
						$('#dateOfBirth').val(data.doDetail.date_of_birth)
						$('#doCnic').val(data.doDetail.user_cnic)

						$('#district').val(data.doDetail.user_district)
						$('#education').val(data.doDetail.education)
						RadionButtonSelectedValueSet('gender',
								data.doDetail.user_gender);
						RadionButtonSelectedValueSet('do_marital_status',
								data.doDetail.marrital_status);

						// 				alert(data.doDetail.user_phone);
						// 				alert(data.doDetail.user_mobile);

						if (data.doDetail.primary_phone_number.length > 2) {
							data.doDetail.primary_phone_number = data.doDetail.primary_phone_number
									.substring(2);
							data.doDetail.primary_phone_number = [
									data.doDetail.primary_phone_number.slice(0,
											3), '-',
									data.doDetail.primary_phone_number.slice(3) ]
									.join('');
							$('#dophone1').val(
									data.doDetail.primary_phone_number);
						}

						var dophone2 = data.doDetail.secondary_phone_number;

						if (dophone2 != '') {
							console.log('dophone2 ' + dophone2 + '  l  '
									+ data.doDetail.secondary_phone_number);
							dophone2 = dophone2.substring(2);
							dophone2 = [ dophone2.slice(0, 3), '-',
									dophone2.slice(3) ].join('');
							$('#dophone2').val(dophone2);
						}

						var dorefferal1 = data.doDetail.refferal_number1;

						if (dorefferal1 != '') {
							console.log('dophone2 l' + dorefferal1 + 'l');
							dorefferal1 = dorefferal1.substring(2);
							dorefferal1 = [ dorefferal1.slice(0, 3), '-',
									dorefferal1.slice(3) ].join('');
							$('#doRefNumber1').val(dorefferal1);
						}

						var dorefferal2 = data.doDetail.refferal_number2;

						if (dorefferal2 != '') {
							console.log('dophone2 l' + dorefferal2 + 'l');
							dorefferal2 = dorefferal2.substring(2);
							dorefferal2 = [ dorefferal2.slice(0, 3), '-',
									dorefferal2.slice(3) ].join('');
							$('#doRefNumber2').val(dorefferal2);
						}

						$('#doemail').val(data.doDetail.user_email);

						$('#address').val(data.doDetail.user_address);

						RadionButtonSelectedValueSet('education',
								data.doDetail.educated);

						$('#salary').val(data.doDetail.user_basic_salary);

						$('<input>').attr({
							type : 'hidden',
							id : 'doId',
							name : 'doId',
							value : doId
						}).appendTo('#DO_form');

					}
				});
	}

	function RadionButtonSelectedValueSet(name, SelectdValue) {
		$('input[name="' + name + '"][value="' + SelectdValue + '"]').prop(
				'checked', true);
	}
</script>


</head>
<body>

	<%
		UserBean bean = (UserBean) session.getAttribute("email");
	%>
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
		<!-- end #header -->


		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->

			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<!-- 			<h1 class="page-header"> -->
			<!-- 				SuperAdmin Form -->
			<!-- 			</h1> -->
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" style="margin-top: 3%;">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<!-- <a href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-default"
									data-click="panel-expand"><i class="fa fa-expand"></i></a>  <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-success"
									data-click="panel-reload"><i class="fa fa-repeat"></i></a>  <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-warning"
									data-click="panel-collapse"><i class="fa fa-minus"></i></a> <a
									href="javascript:;"
									class="btn btn-xs btn-icon btn-circle btn-danger"
									data-click="panel-remove"><i class="fa fa-times"></i></a> -->
							</div>

							<%-- 								
// 					String err = (String)request.getAttribute("msg");
<%-- 					if(err != "" || err != null){ %> --%>
							<%-- 						<span><%=err %></span> --%>
							<%-- 						<%}else{ %> --%>
							<%-- <%-- 						<span><%=err %></span> --%>
							<%-- 						<%} %> --%>


							<h4 class="panel-title">Super Admin Form</h4>
						</div>
						<div class="panel-body">

							<div id="wizard">
								<!-- 								<ol style="padding-right: 5%;"> -->
								<!-- 									<li>District Officer</li> -->
								<!-- 									<li>Salesman</li> -->
								<!-- 									<li>Service Operation Controller</li> -->

								<!-- 								</ol> -->
								<!-- begin wizard step-1 -->

								<!--Add District Officer -->
								<h3>
									<span id="add">Update District Officer</span>
								</h3>
								<div>
									<fieldset>
										<!-- begin row -->
										<div class="row">


											<form class="form-horizontal" action="" method="post"
												id="DO_form" name="DO_form" enctype="multipart/form-data">

												<h4>
													<span style="color: red;">Fields marked with <span
														class="glyphicon glyphicon-asterisk" style="color: red;"></span>
														are required
													</span>
												</h4>
												<fieldset>
													<legend>Personal info</legend>
													<div class="form-group">
														<label for="inputEmail3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Full Name:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="firstname"
																name="firstname" placeholder="" required="required"
																oninvalid="this.setCustomValidity('Please enter the full name')"
																onchange="nameValidation('firstname','doFirstNameResult','Invalid name'), this.setCustomValidity('');"
																onkeypress="nameCharahterValidation('firstname')" /> <span
																style="color: red; font-size: 12px;"
																id="doFirstNameResult"></span>
														</div>
													</div>
													<input id="doid" name="doid" type="hidden" value="">
													<script>
														document
																.getElementById("doid").value = (getUrlParameter('doId'));
													</script>
													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Birth:</label>
														<div class="col-md-5">

															<input type="date" class="form-control" id="dateOfBirth"
																name="dateOfBirth"
																onChange="ageValidationDO(this, 'dateOfBirthResult','doCnic','cnicLabel')"
																required="required" onchange="checkDateOfBirthDo(this)"
																oninvalid="this.setCustomValidity('Please enter the Date of Birth')">
															<span style="color: red; font-size: 12px;"
																id="dateOfBirthResult"></span>

														</div>
													</div>

													<div class="form-group">
														<div id="messagenicvalid" name="messagenicvalid"
															style="display: none; color: red">CNIC exist
															already</div>
														<label for="inputPassword3" class="col-md-4 control-label"
															id="cnicLabel"><i style="color: red;">*</i> CNIC:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="doCnic"
																data-table="user" data-column="user_cnic"
																data-type="cnic" name="cnic" maxlength="15"
																placeholder="xxxxx-xxxxxxx-x"
																onchange="cnicValidation('doCnic','cnicMsg'), existingData('doCnic','cnicMsg',this)"
																onkeypress="setNicDash(event,'doCnic')" /> <span
																style="color: red; font-size: 12px;" id="cnicMsg"></span>
														</div>
													</div>

													<div class="form-group has-feedback">
														<label class="col-md-4 control-label">Gender: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="gender" value="Male" checked="checked"
																oninvalid="this.setCustomValidity('Please choose gender')"
																onchange="this.setCustomValidity('')"> Male
															</label> <label class="radio-inline"> <input type="radio"
																name="gender" value="Female"> Female
															</label>
														</div>
													</div>

													<div class="form-group has-feedback" id="do_marital_status">
														<label class="col-md-4 control-label radio"
															data-name="do_marital_status">Marital Status: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="do_marital_status" value="Single"
																checked="checked"> Single
															</label> <label class="radio-inline"> <input type="radio"
																name="do_marital_status" value="Married">
																Married
															</label><label class="radio-inline"> <input type="radio"
																name="do_marital_status" value="Widows"> Widow
															</label><label class="radio-inline"> <input type="radio"
																name="do_marital_status" value="Divorced">
																Divorced
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Joining:</label>
														<div class="col-md-5">

															<input type="date" class="form-control" id="joiningDate"
																name="joiningDate"
																oninvalid="this.setCustomValidity('Please enter the date of joining')"
																onchange="this.setCustomValidity(''); checkJoiningDateDO(this, 'joiningDateResult')"
																onkeyup="this.setCustomValidity(''); checkJoiningDateDO(this, 'joiningDateResult')">
															<span style="color: red; font-size: 12px;"
																id="joiningDateResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-4 control-label"><i
															style="color: red;">*</i> Education:</label>
														<div class="col-md-5">
															<select class="form-control selectpicker" id="education"
																data-live-search="true" name="education"
																onchange="selectDOEducation()"
																data-parsley-trigger="blur" data-style="btn-white">
																<option selected disabled>Select Education</option>
																<option value="1">Never Attended School</option>
																<option value="2">(1-5) Primary</option>
																<option value="3">(6-8) Middle</option>
																<option value="4">(9-10) Secondary</option>
																<option value="5">(11-12) Higher</option>
																<option value="6">(12-14) Diploma/BA/BSc</option>
																<option value="7">(14-16) Graduate</option>
																<option value="8">(16+) Post Graduate</option>
															</select>
														</div>

													</div>

												</fieldset>

												<fieldset>
													<legend>Contact info</legend>

													<div class="form-group has-feedback">
														<div id="messagephonevalid" name="messagephonevalid"
															style="display: none; color: red; margin-left: 385px;">Mobile
															number already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Phone number (Primary):</label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control mask" id="dophone1"
																	data-type="phone" data-table="user"
																	data-column="primary_phone_number"
																	placeholder="xxx-xxxxxxx" name="phone1" maxlength="11"
																	required="required"
																	onkeypress="setMobileDash(event,'dophone1')"
																	onchange="mobileNumberValidation('dophone1','dophoneResult1'); existingData('dophone1','dophoneResult1',this)" />
																<span style="color: red; font-size: 12px;"
																	id="dophoneResult1"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<div id="messagephonevalid" name="messagephonevalid"
															style="display: none; color: red">Mobile number
															already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label">Phone
															number (Secondary): </label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control" id="dophone2"
																	data-type="phone" data-table="user"
																	data-column="secondary_phone_number"
																	placeholder="xxx-xxxxxxx" name="phone2" maxlength="11"
																	onkeypress="setMobileDash(event,'dophone2')"
																	onchange="numberValidation('dophone2','dophoneResult2'); existingData('dophone2','dophoneResult2',this)">
																<span style="color: red; font-size: 12px;"
																	id="dophoneResult2"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label">Referral
															Number1:</label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control" id="doRefNumber1"
																	placeholder="xxx-xxxxxxx" name="refNumber1"
																	maxlength="11"
																	onkeypress="setMobileDash(event,'doRefNumber1')"
																	onchange="numberValidation('doRefNumber1','doRefNumber1Result')">
																<span style="color: red; font-size: 12px;"
																	id="doRefNumber1Result"></span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label">Referral
															Number2:</label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control" id="doRefNumber2"
																	placeholder="xxx-xxxxxxx" name="refNumber2"
																	maxlength="11"
																	onkeypress="setMobileDash(event,'doRefNumber2')"
																	onchange="numberValidation('doRefNumber2','doRefNumber1Result2')">
																<span style="color: red; font-size: 12px;"
																	id="doRefNumber1Result2"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<div id="messageemailvalid" name="messageemailvalid"
															style="display: none; color: red">Please Enter
															Email already Exist on our system</div>
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Email:</label>
														<div class="col-md-5">
															<input type="email" class="form-control" id="doemail"
																name="email" data-table="user" data-column="user_email"
																data-type="email"
																onkeypress="emailValidation('doemail','doemailResult')"
																onchange="emailValidation('doemail','doemailResult'), existingData('doemail','doemailResult',this)">
															<span style="color: red; font-size: 12px;"
																id="doemailResult"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Address:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="address"
																name="address" required="required"
																oninvalid="this.setCustomValidity('Please Enter Address')"
																onchange="addressValidation('address','addressResult'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
																id="addressResult"></span>
														</div>
													</div>

													<div class="form-group has-feedback">
														<label class="col-md-4 control-label"><i
															style="color: red;">* </i>District: </label>
														<div class="col-md-5">
															<select class="form-control selectpicker" id="district"
																name="district" required="required"
																oninvalid="this.setCustomValidity('Please select DO')"
																onchange="this.setCustomValidity(''), selectDODistrict(this)">
																<option selected disabled>Select District</option>
															</select>
														</div>
													</div>
												</fieldset>

												<fieldset>
													<legend></legend>


												</fieldset>
												<div class="form-group">
													<label for="inputPassword3" class="col-md-4 control-label"><i
														style="color: red;">*</i> Salary:</label>
													<div class="col-md-5">
														<input type="number" id="salary" class="form-control"
															name="salary" required="required"
															oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
															onchange="numericValidation('salary','salaryResult','Invalid Number'), this.setCustomValidity('')">
														<span style="color: red; font-size: 12px;"
															id="salaryResult"></span>
													</div>
												</div>

												<div class="form-group">
													<div class="col-md-4 col-md-offset-4" id="do_added"
														style="color: red; font-size: 12px; font-weight: bold;">
													</div>
													<div class="col-sm-offset-4 col-md-3"></div>
													<div class="col-md-2">
														<input type="button" name="do_form_update" id="submit"
															onclick="updateDo();" value="Update"
															class="btn btn-block btn-info" />
													</div>
												</div>
											</form>
										</div>
									</fieldset>
								</div>
								<!-- end wizard step-1 -->

							</div>

						</div>
					</div>
					<!-- end panel -->
				</div>
				<!-- end col-12 -->

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


	<!-- ================== END BASE JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/plugins/morris/raphael.min.js"></script>
	<script src="assets/plugins/morris/morris.js"></script>
	<script src="assets/js/chart-morris.demo.min.js"></script>
	<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<!-- ================== END PAGE LEVEL JS ================== -->

	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<!-- 	 -->
	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>
	<script src="assets/js/form-wizards.demo.min.js"></script>
	<script type="text/javascript" src="js/superAdminDOForm.js"></script>

	<!-- ================== END PAGE LEVEL JS ================== -->


	<script src="assets/plugins/parsley/dist/parsley.js"></script>


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<!-- 	<script src="assets/plugins/gritter/js/jquery.gritter.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script> -->
	<!-- 	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script> -->
	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>


	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script> -->
	<!-- 	<script src="assets/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script> -->
	<!-- 	<script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script> -->
	<script src="assets/js/dashboard.min.js"></script>
	<!-- 	<script src="assets/js/apps.min.js"></script> -->
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		$(document)
				.ready(
						function() {
							App.init();
							FormWizard.init();
							$(
									'#firstname, #joiningDate, #doemail, #dateOfBirth, #doCnic, #education, #dophone1, #salary, #address, #district')
									.change(doFormUpdate);
						});
		window.onload = getDistricts();
		window.onload = getUploadedData();
	</script>

</body>


</html>
