<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="bean.UserBean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type">
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
<!-- ================== END PAGE CSS STYLE ================== -->


<!-- ================== BEGIN BASE JS ================== -->
<script type="text/javascript" src="js/superAdminDOForm.js"></script>
<!-- ================== END BASE JS ================== -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://maps.google.com/maps/api/js?sensor=false"></script> -->
<!-- <script type='text/javascript' -->
<!-- 	src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/infobox/src/infobox.js"></script> -->
<!-- <script src="http://maps.googleapis.com/maps/api/js"></script> -->

<!-- ================== BEGIN BASE JS ================== -->

<script src="js/validation.js"></script>

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
			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" style="margin-top: 3%;">
						<div class="panel-heading">
							<h4 class="panel-title">DO Form</h4>
						</div>
						<div class="panel-body">

							<div id="wizard">
								<!--Add District Officer -->
								<h3>
									<span id="add">Add District Officer</span>
								</h3>
								<div>
									<!-- begin row -->
									<div class="row">

										<form class="form-horizontal" action="" data-do_form
											method="post" id="DO_form" name="DO_form"
											enctype="multipart/form-data">
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
															onchange="nameValidation('firstname','doFirstNameResult','Invalid name'), this.setCustomValidity(''), nameCharahterValidation('firstname')"
															onkeypress="nameCharahterValidation('firstname')" /> <span
															style="color: red; font-size: 12px;"
															id="doFirstNameResult"></span>
													</div>
												</div>

												<div class="form-group">
													<label class="col-md-4 control-label"><i
														style="color: red;">*</i> User Type:</label>
													<div class="col-md-5">
														<select class="form-control selectpicker" id="userType"
															data-live-search="true" required="required"
															name="userType" data-parsley-trigger="blur"
															data-style="btn-white">
															<option selected disabled>Select User Type</option>
															<option value="100">Admin</option>
															<option value="102">Regional Officer</option>
															<option value="101">District Officer</option>
														</select>
													</div>
												</div>

												<div class="form-group">
													<label for="inputEmail3" class="control-label col-md-4"><i
														style="color: red;">*</i> Date of Birth:</label>
													<div class="col-md-5">

														<input type="date" class="form-control" id="dateOfBirth"
															name="dateOfBirth"
															onChange="ageValidationDO(this, 'dateOfBirthResult','doCnic','cnicLabel')"
															onkeydown="ageValidationDO(this, 'dateOfBirthResult','doCnic','cnicLabel')"
															required="required" onchange="checkDateOfBirthDo(this)"
															oninvalid="this.setCustomValidity('Please enter the Date of Birth')">
														<span style="color: red; font-size: 12px;"
															id="dateOfBirthResult"></span>

													</div>
												</div>

												<div class="form-group">
													<div id="messagenicvalid" name="messagenicvalid"
														style="display: none; color: red">CNIC exist already</div>
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
															name="do_marital_status" value="Single" checked="checked">
															Single
														</label> <label class="radio-inline"> <input type="radio"
															name="do_marital_status" value="Married"> Married
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
															onchange="selectDOEducation(this)"
															data-live-search="true" required="required"
															name="education" data-parsley-trigger="blur"
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
														style="display: none; color: red">Please Enter Email
														already Exist on our system</div>
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

												<div class="form-group">
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
														onkeypress="numericValidation('salary','salaryResult','Invalid Number')"
														onkeyup="numericValidation('salary','salaryResult','Invalid Number')"
														onchange="numericValidation('salary','salaryResult','Invalid Number')">
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
													<input type="bu" name="do_form_submit" id="submit"
														onclick="insertDo();" value="Add District Officer"
														class="btn btn-block btn-info" />
												</div>
											</div>
										</form>
									</div>
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
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
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

	<script src="assets/plugins/sparkline/jquery.sparkline.js"></script>

	<script src="assets/js/dashboard.min.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="assets/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script src="assets/js/apps.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
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
					$.each(json.data, function(e) {
						var option = document.createElement('option');
						$(option).val(json.data[e].district_id);
						$(option).text(json.data[e].district_name);
						$('#district').append(option);
					});
				}
			});
		}
	</script>


	<script>
		$(document)
				.ready(
						function() {
							App.init();
							getDistricts();
							FormWizard.init();
							doFormSubmit();

							$(
									'#firstname, #joiningDate, #doemail, #dateOfBirth, #doCnic, #education, #dophone1, #salary, #address, #district')
									.change(doFormSubmit);
						});
	</script>

</body>


</html>
