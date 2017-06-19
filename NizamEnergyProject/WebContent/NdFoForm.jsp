<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>

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

<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />
<link href="assets/plugins/gritter/css/jquery.gritter.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<script src="js/superAdminDOForm.js"></script>

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
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />

<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<script>
	$(document).ready(function() {

		$('#member').change(function() {
			$(this).find("option:selected").each(function() {
				if ($(this).attr("value") == "vle") {

					$(".box").not(".vle").hide();
					$(".vle").show();
				} else if ($(this).attr("value") == "Field") {

					$(".box").not(".feildofficer").hide();
					$(".feildofficer").show();
				}

				else {
					$(".box").hide();
				}
			});
		}).change();

	});
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
	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/doHeader.jsp"%>
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin row -->
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-inverse" style="margin-top: 3%;">
						<div class="panel-heading">
							<h4 class="panel-title">Add Team</h4>
						</div>
						<div class="panel-body">
							<div>
								<select id="member" class="form-control">
									<option selected disabled>--Select member--</option>
									<option value="vle">Nizam Dost</option>
									<option value="Field">Field officer</option>
								</select>
							</div>
							<div class="box vle" style="margin-top: 1px">
								<fieldset>
									<h3>
										<span>Add Nizam Dost</span>
									</h3>
									<!-- begin row -->
									<div>
										<div class="row well">
											<!-- begin col-6 -->
											<form method="post" class="form-horizontal"
												data-parsley-validate="true" id="salesman" name="salesman">
												<h4>
													<span style="color: red;">Fields marked with <span
														class="glyphicon glyphicon-asterisk" style="color: red;"></span>
														are required
													</span>
												</h4>
												<fieldset>
													<legend> Personal info </legend>
													<div class="form-group">
														<label for="inputEmail3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Full Name:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="ndFullName"
																data-parsley-required="true" name="ndFullName"
																placeholder=""
																onchange="nameValidation('ndFullName','doFirstNameResult','Invalid name'); "
																onkeypress="nameCharahterValidation('ndFullName')" /> <span
																style="color: red; font-size: 12px;"
																id="doFirstNameResult"></span>
														</div>
													</div>
													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Birth:</label>
														<div class="col-md-5">

															<input type="date" class="form-control"
																id="ndDateOfBirth" name="ndDateOfBirth"
																required="required"
																onChange="ageValidationNewVLE(this, 'dateOfBirthResult','ndCnic','cnicNdLabel')"
																onkeydown="ageValidationNewVLE(this, 'dateOfBirthResult','ndCnic','cnicNdLabel')"
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
															id="cnicNdLabel"><i style="color: red;">*</i>
															CNIC:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="ndCnic"
																placeholder="xxxx-xxxxxxx-x" name="ndCnic"
																maxlength="15" data-table="salesman"
																data-column="salesman_cnic" required="required"
																data-type="cnic"
																onchange="cnicValidation('ndCnic','ndCnicMsg'), existingData('ndCnic','ndCnicMsg',this)"
																onkeypress="setNicDash(event,'ndCnic'), cnicValidation('ndCnic','ndCnicMsg')" />
															<span style="color: red; font-size: 12px;" id="ndCnicMsg"></span>
														</div>
													</div>

													<div class="form-group has-feedback">
														<label class="col-md-4 control-label">Gender: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="ndGender" value="Male" checked="checked">
																Male
															</label> <label class="radio-inline"> <input type="radio"
																name="ndGender" value="Female"> Female
															</label>
														</div>
													</div>

													<div class="form-group has-feedback">
														<label class="col-md-4 control-label radio">Marital
															Status: </label>
														<div class="col-md-5">
															<label class="radio-inline"> <input type="radio"
																name="nd_marital_status" value="Single"
																checked="checked"> Single
															</label> <label class="radio-inline"> <input type="radio"
																name="nd_marital_status" value="Married">
																Married
															</label><label class="radio-inline"> <input type="radio"
																name="nd_marital_status" value="Widows"> Widow
															</label><label class="radio-inline"> <input type="radio"
																name="nd_marital_status" value="Divorced">
																Divorced
															</label>
														</div>
													</div>

													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Date of Joining:</label>
														<div class="col-md-5">

															<input type="date" class="form-control" id="joiningDate"
																required="required" name="joiningDate"
																oninvalid="this.setCustomValidity('Please enter the nd date of joining')"
																onchange="checkJoiningDateND(this, 'joiningDateResult')"
																onkeydown="checkJoiningDateND(this, 'joiningDateResult')"
																onchange="checkJoiningDateND(this, 'joiningDateResult')">
															<span style="color: red; font-size: 12px;"
																id="joiningDateResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label">Education:</label>
														<div class="col-md-5">
															<select class="form-control selectpicker"
																id="ndEducation" data-live-search="true"
																name="ndEducation" data-parsley-trigger="blur"
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
															style="display: none; color: red; margin-left: 385px;">Mobile
															number already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Phone number (Primary):</label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control phone mask"
																	id="ndPrimaryPhone" data-mask="xxx-xxxxxxx"
																	data-table="salesman" required="required"
																	data-column="salesman_primary_phone" data-type="phone"
																	name="ndPrimaryPhone" maxlength="11"
																	onkeypress="setMobileDash(event, 'ndPrimaryPhone');"
																	onkeyup="mobileNumberValidation('ndPrimaryPhone','dophoneResult1');"
																	onchange="mobileNumberValidation('ndPrimaryPhone','dophoneResult1'); existingData('ndPrimaryPhone','dophoneResult1',this)"
																	placeholder="xxx-xxxxxxx"> <span
																	style="color: red; font-size: 12px;"
																	id="dophoneResult1"></span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<div id="messagephonevalid" name="messagephonevalid"
															style="display: none; color: red">Mobile number
															already exist</div>
														<label for="inputPassword3" class="col-md-4 control-label">
															Phone number (Secondary):</label>
														<div class="col-md-5">
															<div class="input-group" data-parsley-trigger="change">
																<span class="input-group-addon">92</span><input
																	type="text" class="form-control" id="ndSecondaryPhone"
																	data-type="phone" data-table="salesman"
																	data-column="salesman_secondary_phone"
																	placeholder="xxx-xxxxxxx" maxlength="11"
																	onkeypress="setMobileDash(event, 'ndSecondaryPhone')"
																	name="ndSecondaryPhone" maxlength="11"
																	onkeypress="setMobileDash(event, 'ndSecondaryPhone'), numberValidation('ndSecondaryPhone','dophoneResult2')"
																	onchange="numberValidation('ndSecondaryPhone','dophoneResult2'),existingData('ndSecondaryPhone','dophoneResult2',this);">
																<span style="color: red; font-size: 12px;"
																	id="dophoneResult2"></span>
															</div>
														</div>
													</div>

													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label">
															Account Number </label>
														<div class="col-md-5">
															<input type="number" id="ndAccNumber"
																class="form-control" name="ndAccNumber"
																oninvalid="this.setCustomValidity('Please Enter account number')"
																onchange="numericValidationAC('ndAccNumber','accnumberResult','Invalid Number')">
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
															<input type="email" class="form-control" id="ndEmail"
																name="ndEmail" placeholder="" data-type="email"
																data-table="salesman" data-column="salesman_email"
																onkeydown="emailValidationUsers('ndEmail','ndemailResult')"
																onchange="emailValidationUsers('ndEmail','ndemailResult')"
																onchange="existingData('ndEmail','ndemailResult',this);">
															<span style="color: red; font-size: 12px;"
																id="ndemailResult"></span>
														</div>
													</div>



													<div class="form-group">
														<label for="inputPassword3" class="col-md-4 control-label"><i
															style="color: red;">*</i> Address:</label>
														<div class="col-md-5">
															<input type="text" class="form-control" id="ndAddress"
																name="ndAddress" required="required"
																oninvalid="this.setCustomValidity('Please Enter Address')"
																onkeypress="this.setCustomValidity('')"
																onchange="addressValidation('ndAddress','addressResult'), this.setCustomValidity('')">
															<span style="color: red; font-size: 12px;"
																id="addressResult"></span>
														</div>
													</div>

													<div class="form-group">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">*</i> Field Officer:</label>
														<div class="col-md-5">
															<select class="form-control" name="foList" id="foList"
																required="required" onchange="selectFieldOfficer(this)"
																data-live-search="true"
																oninvalid="this.setCustomValidity('Please select field officer')"
																onchange="this.setCustomValidity(''), selectFO(this);"
																onclick="selectFO(this)" onkeydown="selectFO(this)"
																data-parsley-trigger="blur" data-style="btn-white">
																<option selected disabled>Select Field Officer</option>
															</select>

														</div>
													</div>
													<div class="form-group has-feedback" id="ndCity">
														<label for="inputEmail3" class="control-label col-md-4"><i
															style="color: red;">* </i> Tehsil:</label>
														<div class="col-md-5">
															<select class="form-control selectpicker" id="ndCity"
																data-live-search="true" name="ndCity"
																required="required"
																oninvalid="this.setCustomValidity('Please enter the valid city name')"
																onchange="this.setCustomValidity(''), checkTehsilND(this);"
																onclick="checkTehsilND(this)"
																onkeydown="checkTehsilND(this)"
																data-parsley-trigger="blur" data-style="btn-white">
																<option selected disabled>Select Tehsil</option>
																<option class="divider2">
																<option>
															</select>
														</div>
													</div>

												</fieldset>

												<div class="form-group" id="loop"></div>
												<legend> Commission info </legend>

												<div class="form-group">
													<label for="inputPassword3" class="col-md-4 control-label"><i
														style="color: red;">*</i> Basic Salary:</label>
													<div class="col-md-5">
														<input type="number" id="ndSalary" class="form-control"
															name="ndSalary" required="required"
															oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
															onblur="numericValidation('ndSalary','ssalaryResult','Invalid Number')"
															onkeyup="numericValidation('ndSalary','ssalaryResult','Invalid Number')">
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
																type="number" id="ndPerSellCommission"
																class="form-control" name="ndPerSellCommission"
																required="required" maxlength="15"
																oninvalid="this.setCustomValidity('Please Enter ndpercellcomm')"
																onblur="numericValidationAT('ndPerSellCommission','percellcommTimeResult','Invalid Number')"
																onchange="numericValidationAT('ndPerSellCommission','percellcommTimeResult','Invalid Number')"
																onkeyup="numericValidationAT('ndPerSellCommission','percellcommTimeResult','Invalid Number')">
															<span style="color: red; font-size: 12px;"
																id="percellcommTimeResult"></span>
														</div>
													</div>
												</div>
												<input type="hidden" name="district"
													value="<%=bean.getUser_district()%>"> <input
													type="hidden" name="doId" value="<%=bean.getUserId()%>">

												<div class="form-group">
													<div class="col-md-4 col-md-offset-4" id="nd_added"
														style="color: red; font-size: 12px; font-weight: bold;">
													</div>
													<div class="col-sm-offset-4 col-md-3"></div>
													<div class="col-md-2">
														<input type="button" name="insert_nd" id="ndSubmit"
															onclick="insertNd();" value="Add Salesman"
															class="btn btn-block btn-info ">
													</div>
												</div>

											</form>
											<!-- end col-6 -->
										</div>
									</div>
									<!-- end row -->
								</fieldset>
							</div>
							<!-- end wizard step-2 -->


							<!-- begin wizard step 4 -->
							<div class="box feildofficer" style="margin-top: 1px">
								<h3>
									<span>Add Field Officer</span>
								</h3>
								<!-- begin row -->
								<div>
									<div class="row well">
										<!-- begin col-6 -->

										<form class="form-horizontal" data-fosubmit method="post"
											id="fieldofficer_form" name="fieldofficer_form">

											<!-- <form method="post" class="form-horizontal" data-fo_form
											data-parsley-validate="true" id="fieldofficer_form"
											name="fieldofficer_form"> -->
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
														style="display: none; color: red">CNIC exist already</div>
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
															name="fo_marital_status" value="Single" checked="checked">
															Single
														</label> <label class="radio-inline"> <input type="radio"
															name="fo_marital_status" value="Married"> Married
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
															name="sjoiningDate" onchange="checkDate(this)"
															required="required"
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
														<input type="number" id="foAccountNo" class="form-control"
															name="foAccountNo"
															onblur="numericValidationAT('foAccountNo','accnumberResult','Invalid Number')">
														<span style="color: red; font-size: 12px;"
															id="accnumberResult"></span>
													</div>
												</div>

												<div class="form-group">
													<div id="messageemailvalid" name="messageemailvalid"
														style="display: none; color: red">Please Enter Email
														already Exist on our system</div>
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

												<div class="form-group has-feedback" id="foCity">
													<label for="inputEmail3" class="control-label col-md-4"><i
														style="color: red;">* </i> Tehsil:</label>
													<div class="col-md-5">
														<select class="form-control selectpicker" id="foCity"
															data-live-search="true" name="foCity" required="required"
															oninvalid="this.setCustomValidity('Please enter the valid city name')"
															onchange="this.setCustomValidity(''); checkTehsil(this)"
															data-parsley-trigger="blur" data-style="btn-white">
															<option selected disabled>Select Tehsil</option>
															<option class="divider2">
															<option>
														</select>
													</div>
												</div>
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
															onchange="numericValidation('foSalary','ssalaryResult','Invalid Number'), this.setCustomValidity('')">
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
															<span
																style="color: red; font-size: 12px; position: absolute;"
																id="percellcommTimeResult"></span>
														</div>
													</div>
												</div>
											</fieldset>
											<input type="hidden" name="sdistrict"
												value="<%=bean.getUser_district()%>"> <input
												type="hidden" name="sdoId" value="<%=bean.getUserId()%>">

											<div class="form-group">
												<div class="col-md-4 col-md-offset-4" id="fo_added"
													style="color: red; font-size: 12px; font-weight: bold;">
												</div>
												<div class="col-sm-offset-4 col-md-3"></div>
												<div class="col-md-2">
													<input type="button" id="foSubmit" name="insert_fo"
														onclick="insertFo();" value="Add Field officer"
														class="btn btn-block btn-info">
												</div>
											</div>

										</form>
										<!-- end col-6 -->
									</div>
								</div>
								<!-- end row -->
							</div>

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
	<script src="js/superAdminDOForm.js"></script>
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
	<!-- ================== END PAGE LEVEL JS ================== -->


	<script>
		$(document)
				.ready(
						function() {
							App.init();
							FormWizard.init();
							FormPlugins.init();
							FOformSubmit();
							SalesmanformSubmit();
							getTehsilFO();
							getTehsilND();
							getFOList();
							$(
									'#sfirstname, #sdateOfBirth, #sdoCnic, #sjoiningDate, #seducation, #foPrimaryPhone, #foAddress, #foCity, #foSalary, #percellcomm')
									.change(FOformSubmit);

							$(
									'#ndFullName, #ndDateOfBirth, #ndCnic, #joiningDate, #ndPrimaryPhone, #ndAddress, #foList, #ndCity, #ndSalary, #ndPerSellCommission')
									.change(SalesmanformSubmit);

						});
	</script>

	<script>
		var doId =
	<%=bean.getUserId()%>
		function getTehsilFO() {

			console.log('getTehsilFO() called.. ');

			$.ajax({
				url : 'AddUsersServlet',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getFOCities',
					DoId : doId
				},
				success : function(json) {
					//console.log('cities ... ' + json.data);
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

		function getTehsilND() {

			console.log('getTehsilND() called.. ');

			$.ajax({
				url : 'AddUsersServlet',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getAllCities'
				},
				success : function(json) {
					//console.log('cities ... ' + json.data);
					$.each(json.data, function(e) {
						var option = document.createElement('option');
						$(option).val(json.data[e].city_id);
						$(option).text(json.data[e].city_name);
						$('[name="ndCity"]').append(option);
					});
					$('[name="ndCity"]').selectpicker('refresh');
				}
			});
		}

		function getFOList() {

			console.log('getFOList() called.. ');

			$.ajax({
				url : 'AddUsersServlet',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getAllFO'
				},
				success : function(json) {
					//console.log('getFOList ... ' + json.data);
					$.each(json.data, function(e) {
						var option = document.createElement('option');
						$(option).val(json.data[e].foid);
						$(option).text(json.data[e].foName);
						$('[name="foList"]').append(option);
					});
					$('[name="foList"]').selectpicker('refresh');
				}
			});
		}
	</script>

</body>
</html>