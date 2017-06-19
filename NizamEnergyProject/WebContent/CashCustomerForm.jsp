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

<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<script src="js/validation.js"></script>

<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
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
<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />

<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" />
<link href="assets/plugins/select2/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- ================== END PAGE LEVEL STYLE ================== -->

<!-- ================== BEGIN BASE JS ================== -->

<!-- ================== END BASE JS ================== -->


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
		<%@include file="/doHeader.jsp"%>
		<!-- begin #content -->
		<div id="content" class="content">


			<!-- begin row -->
			<div class="row">

				<div class="col-md-12">
					<div class="panel panel-inverse"
						style="margin-left: 7%; margin-right: 7%; margin-top: 3%;">


						<div class="panel-heading">
							<h4 class="panel-title">Add Cash Customer</h4>
						</div>
						<!-- begin wizard step 4 -->
						<div class="box feildofficer">
							<!-- begin row -->
							<div class="row well">
								<form class="form-horizontal" data-parsley-validate="true"
									method="post">

									<legend>
										<center>
											Customer Information (<span
												style="color: red; font-size: 16px;">Add Cash
												Customer</span>)
										</Center>
									</legend>


									<div class="form-group has-feedback" id="ndCity">
										<label for="inputEmail3" class="control-label col-md-4"><i
											style="color: red;">* </i> Tehsil:</label>
										<div class="col-md-5">
											<select class="form-control selectpicker" id="CashCCity"
												data-live-search="true" name="CashCCity" required="required"
												data-size="5"
												oninvalid="this.setCustomValidity('Please enter the valid city name')"
												onchange="this.setCustomValidity(''), checkTehsilND(this);"
												onclick="checkTehsilND(this)"
												onkeydown="checkTehsilND(this)" data-parsley-trigger="blur"
												data-style="btn-white">
												<option selected disabled>Select Tehsil</option>
												<option class="divider2">
												<option>
											</select>
										</div>
									</div>

									<div class="form-group">
										<label for="inputEmail3" class="col-md-4 control-label"><i
											style="color: red;">*</i> Customer Name:</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="cfirstname"
												data-parsley-required="true" name="cfirstname"
												placeholder=""
												onchange="nameValidation('cfirstname','cFirstNameResult','Invalid name'); "
												onkeypress="nameCharahterValidation('cfirstname')" /> <span
												style="color: red; font-size: 12px;" id="cFirstNameResult"></span>
										</div>
									</div>
									<div class="form-group" id="cnic">
										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> CNIC:</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="cashCustomerCnic"
												data-table="user" data-column="user_cnic" data-type="cnic"
												name="cashCustomerCnic" maxlength="15"
												placeholder="xxxxx-xxxxxxx-x"
												onchange="cnicValidation('cashCustomerCnic','cashCustomerCnicMsg')"
												onkeypress="setNicDash(event,'cashCustomerCnic')" /> <span
												style="color: red; font-size: 12px;"
												id="cashCustomerCnicMsg"></span>
										</div>
									</div>



									<div class="form-group">
										<div id="messagephonevalid" name="messagephonevalid"
											style="display: none; color: red; margin-left: 385px;">Mobile
											number already exist</div>

										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Primary Phone:</label>
										<div class="col-sm-5">
											<div class="input-group" data-parsley-trigger="change">
												<span class="input-group-addon">92</span> <input type="text"
													class="form-control" id="cashCPhone" data-type="phone"
													data-table="user" data-column="user_phone"
													placeholder="xxx-xxxxxxx" name="cashCPhone" maxlength="11"
													required="required"
													onkeypress="setMobileDash(event,'cashCPhone')"
													onchange="mobileNumberValidation('cashCPhone','cashCPhoneResult'); ">
												<span style="color: red; font-size: 12px;"
													id="cashCPhoneResult"></span>
											</div>
										</div>
									</div>


									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Address:</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="cashCAddress"
												name="cashCAddress" required="required"
												oninvalid="this.setCustomValidity('Please Enter Address')"
												onchange="addressValidation('cashCAddress','cashCAddressResult'), this.setCustomValidity('')">
											<span style="color: red; font-size: 12px;"
												id="cashCAddressResult"></span>
										</div>
									</div>



									<div class="form-group">
										<label for="inputEmail3" class="control-label col-md-4"><i
											style="color: red;">*</i> Field Officer:</label>
										<div class="col-md-5">
											<select class="form-control" id="CashCFoId" name="CashCFoId"
												required="required" onchange="selectFieldOfficer(this)"
												data-live-search="true" data-size="5"
												oninvalid="this.setCustomValidity('Please select field officer')"
												onchange="this.setCustomValidity(''), selectFO(this);"
												onclick="selectFO(this)" onkeydown="selectFO(this)"
												data-parsley-trigger="blur" data-style="btn-white">
												<option selected disabled>Select Field Officer</option>
											</select>

										</div>
									</div>


									<div class="form-group has-feedback"
										id="customer_vle_guarantor">
										<label class="col-md-4 control-label"> <i
											style="color: red;">*</i> Nizam Dost, FO
										</label>
										<div class="col-md-5">
											<select class="form-control selectpicker" id="cashCNdId"
												name="cashCNdId" data-size="5"
												data-form=".customer_vle_guarantor" required="required"
												data-live-search="true" data-size="5"
												data-parsley-trigger="blur" data-style="btn-white">
												<option value="" selected>Select Nizam Dost</option>
												<option class="divider2">
												<option>
											</select>
										</div>
									</div>



									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Appliance/Device Name:</label>
										<div class="col-sm-5">
											<select class="form-control" id="cashCAppliance"
												name="cashCAppliance" required="required">
												<option value="">Select Appliance</option>
												<option value="7 W">7 W</option>
												<option value="60 W">60 W</option>
												<option value="B1-Basic 1">B1-Basic 1</option>
												<option value="B1-Basic 2">B1-Basic 2</option>
												<option value="B1-Roshni">B1-Roshni</option>
												<option value="B1-Power PC">B1-Power PC</option>
												<option value="B1-Power P">B1-Power P</option>
												<option value="B1-Power C">B1-Power C</option>
												<option value="B1-Other">B1-Other</option>
											</select>
										</div>
									</div>


									<div class="form-group">
										<label for="inputPassword3" class="col-md-4 control-label"><i
											style="color: red;">*</i> Price:</label>
										<div class="col-md-5">
											<input type="number" id="cashCPrice" class="form-control"
												name="cashCPrice" required="required"
												oninvalid="this.setCustomValidity('Please Enter Basic Salary')"
												onchange="numericValidation('cashCPrice','cashCPriceResult','Invalid Number'), this.setCustomValidity('')">
											<span style="color: red; font-size: 12px;"
												id="cashCPriceResult"></span>
										</div>
									</div>


									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Consumer Number:</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="cashCConsumerNo"
												data-parsley-trigger="change" data-table="consumer_list"
												data-column="consumer_number" data-type="consumer"
												name="cashCConsumerNo" required="required" maxlength="13"
												oninvalid="this.setCustomValidity('Please Enter Address')"
												onchange="existingData('cashCConsumerNo','cashCConsumerNoResult',this), this.setCustomValidity('')"
												onchange="numericValidation('cashCConsumerNo','cashCConsumerNoResult','Invalid Number');">
											<span style="color: red; font-size: 12px;"
												id="cashCConsumerNoResult"></span>
										</div>
									</div>

									<div class="form-group">

										<div id="messagephonevalid" name="messagephonevalid"
											style="display: none; color: red; margin-left: 385px;">Mobile
											number already exist</div>

										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Device GSM Number:</label>

										<div class="col-sm-5">
											<div class="input-group" data-parsley-trigger="change">
												<span class="input-group-addon">92</span> <input type="text"
													class="form-control" id="cashCGsmNumber"
													name="cashCGsmNumber" required="required" maxlength="11"
													oninvalid="this.setCustomValidity('Please Enter Address')"
													placeholder="xxx-xxxxxxx"
													onkeypress="setMobileDash(event,'cashCGsmNumber')"
													onchange="mobileNumberValidation('cashCGsmNumber','cashCGsmNumberResult'); ">
												<span style="color: red; font-size: 12px;"
													id="cashCGsmNumberResult"></span>
											</div>
										</div>
									</div>

									<div class="form-group">
										<label for="inputPassword3" class="col-sm-4 control-label"><i
											style="color: red;">*</i> Device IMEI Number:</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" id="cashCImeiNumber"
												name="cashCImeiNumber" required="required" maxlength="15"
												oninvalid="this.setCustomValidity('Please Enter Address')"
												onchange="numericValidation('cashCImeiNumber','cashCImeiNumberResult','Invalid Number'), this.setCustomValidity('')">
											<span style="color: red; font-size: 12px;"
												id="cashCImeiNumberResult"></span>
										</div>
									</div>


									<div class="form-group has-feedback" id="customer_karyana">
										<label class="col-md-4 control-label" class="radio"><i
											style="color: red;">*</i> Payment Method </label>

										<div class="col-md-5">
											<label class="radio-inline"> <input type="radio"
												name="cashCPaymentMethod" value="Cash" checked="checked">
												Cash
											</label> <label class="radio-inline"> <input type="radio"
												name="cashCPaymentMethod" value="MobileWallet">Mobile
												Payment
											</label>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-4 col-md-offset-4" id="customer_added"
											style="color: red; font-size: 12px; font-weight: bold;">
										</div>
										<div class="col-sm-offset-4 col-md-3"></div>
										<div class="col-md-2">
											<input type="button" value="Add Cash Customer"
												id="cashCustomerId" onclick="insertCashCustomers();"
												class="btn btn-block btn-info">
										</div>
									</div>

								</form>
							</div>
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
	<script src="js/cashCustomers.js"></script>
	<script src="js/validation.js"></script>

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
	<!-- ================== END PAGE LEVEL JS ================== -->

	<script>
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
					// 				console.log('cities ... ' + json.data);
					$.each(json.data, function(e) {
						var option = document.createElement('option');
						$(option).val(json.data[e].city_id);
						$(option).text(json.data[e].city_name);
						$('[name="CashCCity"]').append(option);
					});
					$('[name="CashCCity"]').selectpicker('refresh');
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
					// 				console.log('getFOList ... ' + json.data);
					$.each(json.data, function(e) {
						var option = document.createElement('option');
						$(option).val(json.data[e].foid);
						$(option).text(json.data[e].foName);
						$('[name="CashCFoId"]').append(option);
					});

					$('[name="CashCFoId"]').selectpicker('refresh');
				}
			});
		}

		function selectNizamDost() {
			var doid =
	<%=bean.getUser_district()%>
		$.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getVLEs',
					districtid : doid
				},
				success : function(json) {
					$.each(json.data, function(e) {
						var option = document.createElement('option')
						$(option).val(json.data[e].salesmanId)
						$(option).text(
								json.data[e].name + ', ' + json.data[e].foname)
						$('[name="cashCNdId"]').append(option)
					})
					$('[name="cashCNdId"]').selectpicker('refresh')
				}
			})
		}
	</script>

	<script>
		$(document).ready(function() {
			App.init();
			getTehsilND();
			getFOList();
			selectNizamDost();

		});
	</script>

</body>


</html>