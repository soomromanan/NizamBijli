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

<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<script src="js/validation.js"></script>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<!-- 	<link href="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" /> -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->


<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.css"
	rel="stylesheet" />
<link
	href="assets/plugins/ionRangeSlider/css/ion.rangeSlider.skinNice.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"
	rel="stylesheet" />
<link
	href="assets/plugins/password-indicator/css/password-indicator.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-combobox/css/bootstrap-combobox.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->
<script src="assets/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="js/superAdminDOForm.js"></script>
<!-- ================== END BASE JS ================== -->

<script>


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
}
	function getFOCity(doid) {

		console.log('getTehsilFO() called.. ');

		$.ajax({
			url : 'AddUsersServlet',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'getFOCities',
				DoId : doid
			},
			success : function(json) {
				$.each(json.data, function(e) {
					var option = document.createElement('option');
					$(option).val(json.data[e].city_id);
					$(option).text(json.data[e].city_name);
					$('[name="foCity"]').append(option);
				});
				$('[name="foCity"]').selectpicker('refresh');
			}
		});
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

			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" style="margin-top: 3%;">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Field Officer Form</h4>
						</div>
						<div class="panel-body">

							<div id="wizard">

								<!-- begin wizard step-1 -->

								<!-- end wizard step-1 -->
								<!-- begin wizard step 4 -->
								<h3>
									<span id="add">Update Field Officer</span>
								</h3>
								<div>
									<fieldset>
										<!-- begin row -->
										<div class="row">

											<form method="post" class="form-horizontal" data-fo_form
												data-parsley-validate="true" id="fieldofficer_form"
												name="fieldofficer_form">
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
															<input type="text" class="form-control" id="sfirstname"
																data-parsley-required="true" name="sfirstname"
																placeholder=""
																onchange="nameValidation('sfirstname','sfoFirstNameResult','Invalid name'); "
																onkeypress="nameCharahterValidation('sfirstname')" /> <span
																style="color: red; font-size: 12px;"
																id="sfoFirstNameResult"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Birth:</label>
														<div class="col-md-5">
															<input type="date" class="form-control" id="sdateOfBirth"
																name="sdateOfBirth"
																onkeydown="ageValidationNewFO(this, 'sdateOfBirthResult','sdoCnic','cnicFoLabel')"
																onChange="ageValidationNewFO(this, 'sdateOfBirthResult','sdoCnic','cnicFoLabel')"
																onchange="checkDateOfBirthFo(this)"
																oninvalid="this.setCustomValidity('Please enter the Date of Birth')"
																required="required"> <span
																style="color: red; font-size: 12px;"
																id="sdateOfBirthResult"></span>

														</div>
													</div>
													<div class="form-group" id="cnic">
														<div id="messagenicvalid" name="messagenicvalid"
															style="display: none; color: red">CNIC exist
															already</div>
														<label for="inputPassword3" class="col-md-4 control-label"
															id="cnicFoLabel"><i style="color: red;">*</i>
															CNIC:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="sdoCnic"
																placeholder="xxxx-xxxxxxx-x" name="sdoCnic"
																maxlength="15" data-table="field_officer"
																data-column="fo_cnic" required="required"
																data-type="cnic"
																onchange="cnicValidationforFo('sdoCnic','scnicMsg'), existingData('sdoCnic','scnicMsg',this)"
																onkeypress="setNicDash(event,'sdoCnic')" /> <span
																style="color: red; font-size: 12px;" id="scnicMsg"></span>
														</div>
													</div>
													<div class="form-group has-feedback">
														<label class="col-md-4 control-label">Gender: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="foGender" value="Male" checked="checked">
																Male
															</label> <label class="radio-inline"> <input type="radio"
																name="foGender" value="Female"> Female
															</label>
														</div>
													</div>

													<div class="form-group has-feedback" id="fo_marital_status">
														<label class="col-md-4 control-label radio"
															data-name="fo_marital_status">Marital Status: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="fo_marital_status" value="Single"
																checked="checked"> Single
															</label> <label class="radio-inline"> <input type="radio"
																name="fo_marital_status" value="Married">
																Married
															</label><label class="radio-inline"> <input type="radio"
																name="fo_marital_status" value="Widows"> Widow
															</label><label class="radio-inline"> <input type="radio"
																name="fo_marital_status" value="Divorced">
																Divorced
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Joining:</label>
														<div class="col-md-5">

															<input type="date" class="form-control" id="sjoiningDate"
																name="sjoiningDate" required="required"
																oninvalid="this.setCustomValidity('Please enter the date of joining')"
																onchange="checkJoiningDateFO(this, 'sjoiningDateResult')"
																onkeydown="checkJoiningDateFO(this, 'sjoiningDateResult')"
																onchange="checkJoiningDateFO(this, 'sjoiningDateResult')">
															<span style="color: red; font-size: 12px;"
																id="sjoiningDateResult"></span>
														</div>
													</div>
													<span style="color: red; font-size: 12px; display: none;"
														id="checkdate"> Select today's date or less than
														today's date </span>
													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Education:</label>
														<div class="col-md-5">
															<select class="form-control selectpicker" id="seducation"
																onchange="selectFOEducation(this)"
																data-live-search="true" required="required"
																name="seducation" data-parsley-trigger="blur"
																data-style="btn-white">
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

													<div class="form-group">
														<div id="messagephonevalid" name="messagephonevalid"
															style="display: none; color: red">Mobile number
															already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Phone number (Primary) :</label>

														<div class="col-md-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	type="text" class="form-control" id="foPrimaryPhone"
																	placeholder="xxx-xxxxxxx" data-table="field_officer"
																	data-column="fo_priamary_phone" name="foPrimaryPhone"
																	data-type="phone" maxlength="11" required="required"
																	onkeypress="setMobileDash(event, 'foPrimaryPhone')"
																	onchange="mobileNumberValidationforFo('foPrimaryPhone','sdophoneResult1'), existingData('foPrimaryPhone','sdophoneResult1',this);" />
																<span style="color: red; font-size: 12px;"
																	id="sdophoneResult1"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<div id="messagephonevalid" name="messagephonevalid"
															style="display: none; color: red">Mobile number
															already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label">
															Phone number (Secondary): </label>
														<div class="col-md-5">
															<div class="input-group">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control" id="foSecondaryNumber"
																	placeholder="xxx-xxxxxxx" data-table="field_officer"
																	data-column="fo_secondary_phone"
																	name="foSecondaryNumber" data-type="phone"
																	maxlength="11"
																	onkeypress="setMobileDash(event, 'foSecondaryNumber')"
																	onchange="numberValidation('foSecondaryNumber','sdophoneResult2'), existingData('foSecondaryNumber','sdophoneResult2',this);" />
																<span style="color: red; font-size: 12px;"
																	id="sdophoneResult2"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label">
															Account Number: </label>
														<div class="col-md-5">
															<input type="number" id="foAccountNo"
																class="form-control" name="foAccountNo"
																onblur="numericValidationAC('foAccountNo','accnumberResult','Invalid Number')">
															<span style="color: red; font-size: 12px;"
																id="accnumberResult"></span>
														</div>
													</div>

													<div class="form-group">
														<div id="messageemailvalid" name="messageemailvalid"
															style="display: none; color: red">Please Enter
															Email already Exist on our system</div>
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;"> </i>Email:</label>
														<div class="col-md-5">
															<input type="email" class="form-control" id="foEmail"
																name="foEmail" data-table="field_officer"
																onkeydown="emailValidationUsers('foEmail','foemailResult');"
																onchange="emailValidationUsers('foEmail','foemailResult');"
																onchange="existingData('foEmail','foemailResult',this);">
															<span style="color: red; font-size: 12px;"
																id="foemailResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Address:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="foAddress"
																name="foAddress" required="required"
																oninvalid="this.setCustomValidity('Please Enter Address')"
																onchange="addressValidation('foAddress','saddressResult'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
																id="saddressResult"></span>
														</div>
													</div>

<!-- 													<div class="form-group has-feedback"> -->
<!-- 														<label for="inputEmail3" class="control-label col-md-4"><i -->
<!-- 															style="color: red;">* </i> Tehsil:</label> -->
<!-- 														<div class="col-md-5"> -->
<!-- 															<select class="form-control selectpicker" id="foCity" -->
<!-- 																data-live-search="true" name="foCity" -->
<!-- 																required="required" -->
<!-- 																oninvalid="this.setCustomValidity('Please enter the valid city name')" -->
<!-- 																onchange="this.setCustomValidity(''); checkTehsil(this)" -->
<!-- 																data-parsley-trigger="blur" data-style="btn-white"> -->
<!-- 																<option selected disabled>Select Tehsil</option> -->
<!-- 																<option class="divider2"> -->
<!-- 																<option> -->
<!-- 															</select> -->
<!-- 														</div> -->
<!-- 													</div> -->
												</fieldset>
												<fieldset>

													<legend> Commission info </legend>
													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Basic Salary:</label>
														<div class="col-md-5">
															<input type="number" id="foSalary" class="form-control"
																name="foSalary" required="required"
																oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
																onkeyup="numericValidation('foSalary','ssalaryResult','Invalid Number')"
																onkeypress="numericValidation('foSalary','ssalaryResult','Invalid Number')"
																onchange="numericValidation('foSalary','ssalaryResult','Invalid Number')">
															<span style="color: red; font-size: 12px;"
																id="ssalaryResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Per Sell Commission: </label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">Rs:</span> <input
																	type="number" id="percellcomm" class="form-control"
																	name="percellcomm" required="required" maxlength="15"
																	oninvalid="this.setCustomValidity('Please Enter percellcomm')"
																	onkeypress="numericValidationAT('percellcomm','percellcommTimeResult','Invalid Number')"
																	onkeyup="numericValidationAT('percellcomm','percellcommTimeResult','Invalid Number')"
																	onchange="numericValidationAT('percellcomm','percellcommTimeResult','Invalid Number')">
																<span style="color: red; font-size: 12px;"
																	id="percellcommTimeResult"></span>
															</div>
														</div>
													</div>
												</fieldset>
												<input type="hidden" name="sdistrict"
													value="<%=bean.getUser_district()%>"> <input
													type="hidden" name="sdoId" value="<%=bean.getUserId()%>">

												<input id="foid" name="foid" type="hidden" value="">
												<script>
													document.getElementById("foid").value = (getUrlParameter('foId'));
												</script>
												<div class="form-group">
													<div class="col-md-4 col-md-offset-4" id="fo_added"
														style="color: red; font-size: 12px; font-weight: bold;">
													</div>
													<div class="col-sm-offset-4 col-md-3"></div>
													<div class="col-md-2">
														<input type="button" id="foSubmit" name="update_fo"
															onclick="updateFo();" value="Update Field Officer"
															class="btn btn-block btn-info">


													</div>
												</div>


											</form>



										</div>
										<!-- end row -->
									</fieldset>
								</div>
								<!-- end wizard step-4 -->

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
	<!-- 	<script src="assets/plugins/gritter/js/jquery.gritter.js"></script> -->
	<script src="assets/plugins/flot/jquery.flot.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.time.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.resize.min.js"></script>
	<script src="assets/plugins/flot/jquery.flot.pie.min.js"></script>
	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>
	<script
		src="assets/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="assets/plugins/jquery-jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script>
	<script
		src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/js/dashboard.min.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script
		src="assets/plugins/ionRangeSlider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script>
	<script
		src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<script src="assets/plugins/masked-input/masked-input.min.js"></script>
	<script
		src="assets/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
	<script
		src="assets/plugins/password-indicator/js/password-indicator.js"></script>
	<script
		src="assets/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>
	<script
		src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
	<script
		src="assets/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.js"></script>
	<script src="assets/plugins/jquery-tag-it/js/tag-it.min.js"></script>
	<script src="assets/plugins/bootstrap-daterangepicker/moment.js"></script>
	<script
		src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script src="assets/plugins/select2/dist/js/select2.min.js"></script>
	<script src="assets/js/form-plugins.demo.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>
	<script src="assets/plugins/parsley/dist/parsley.js"></script>
	<script src="assets/js/form-wizards.demo.min.js"></script>

	<script src="assets/js/apps.min.js"></script>
	<script type="text/javascript" src="js/superAdminDOForm.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		$(document)
				.ready(
						function() {
							App.init();
							FormWizard.init();
							FormPlugins.init();
							$(
									'#sfirstname, #sdateOfBirth, #sdoCnic, #sjoiningDate, #seducation, #foPrimaryPhone, #foAddress, #foCity, #foSalary, #percellcomm')
									.change(FOformUpdate);

						});
	</script>

	<script>
		window.onload = loadDataIntoFields();
		function loadDataIntoFields() {

			var foId = getUrlParameter('foId');
			$
					.ajax({
						url : 'UserFormsDataController',
						dataType : 'json',
						method : 'POST',
						data : {
							action : "FOInfo",
							id : foId
						},
						success : function(data) {
							console.log(data);

							getFOCity(data.FODetail.do_id);

							$('#sfirstname').val(data.FODetail.fo_name)
							$('#sjoiningDate').val(
									data.FODetail.fo_date_of_joining)
							$('#sdateOfBirth').val(
									data.FODetail.fo_date_of_birth)

							$('#seducation').selectpicker();
							$('#seducation').selectpicker('val',
									data.FODetail.education);

							$('#seducation').val(data.FODetail.education);

						//	$('#foCity').val(data.FODetail.fo_city);

							$('#sdoCnic').val(data.FODetail.fo_cnic);
							$('#sdoCnic').show();
							$('#cnicFoLabel').show();

							RadionButtonSelectedValueSet('foGender',
									data.FODetail.fo_gender);
							RadionButtonSelectedValueSet('fo_marital_status',
									data.FODetail.fo_marital_status);

							if (data.FODetail.fo_priamary_phone.length > 2) {
								data.FODetail.fo_priamary_phone = data.FODetail.fo_priamary_phone
										.substring(2);
								data.FODetail.fo_priamary_phone = [
										data.FODetail.fo_priamary_phone.slice(
												0, 3),
										'-',
										data.FODetail.fo_priamary_phone
												.slice(3) ].join('');
								$('#foPrimaryPhone').val(
										data.FODetail.fo_priamary_phone);
							}

							if (data.FODetail.fo_secondary_phone.length > 2) {
								data.FODetail.fo_secondary_phone = data.FODetail.fo_secondary_phone
										.substring(2);
								data.FODetail.fo_secondary_phone = [
										data.FODetail.fo_secondary_phone.slice(
												0, 3),
										'-',
										data.FODetail.fo_secondary_phone
												.slice(3) ].join('');
								$('#foSecondaryNumber').val(
										data.FODetail.fo_secondary_phone);
							}
							$('#foAccountNo').val(data.FODetail.fo_acount_no);
							$('#foEmail').val(data.FODetail.fo_email);

							$('#foAddress').val(data.FODetail.fo_address);

							$('#foSalary').val(data.FODetail.basic_salary);

							$('#percellcomm').val(data.FODetail.per_sale);

						}
					});
		}

		function RadionButtonSelectedValueSet(name, SelectdValue) {
			$('input[name="' + name + '"][value="' + SelectdValue + '"]').prop(
					'checked', true);
		}
	</script>
</body>


</html>