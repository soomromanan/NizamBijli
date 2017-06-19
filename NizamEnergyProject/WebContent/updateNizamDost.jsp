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
	var request;
	var requestgsm;
	var requestproduct;
	var requestcnic;

	function sendInfo() {
		var v = document.vinform1.customerMobile.value;
		var url = "table.jsp?val=" + v;
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

	function sendInfoCustomerGsm() {
		var v = document.vinform1.gsm.value;
		var url = "tableCustomerGsm.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfoCustomerGsm;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}

	function getInfoCustomerGsm() {
		if (request.readyState == 4) {
			var val = request.responseText;
			if (val >= 1) {

				$('#messagephoneGsm').show();

			} else {
				$('#messagephoneGsm').hide();
			}
		}
	}

	function append_payment_type() {
		var v = document.vinform1.customerpayment_type.value;
		if (v == "1") {
			$(function() {
				$("#downPaymentCash").fadeOut();
				$("#weeklyPaymentCash").fadeOut();
				$("#schemeCash").fadeOut();

				$('#click').attr('id', 'cashAdd');
				$('#cashAdd').attr('id', 'cashAdd');
			});
		} else if (v == "2") {
			$(function() {
				$("#downPaymentCash").fadeIn();
				$("#weeklyPaymentCash").fadeIn();
				$("#schemeCash").fadeIn();

				$('#cashAdd').attr('id', 'click');
				$('#click').attr('id', 'click');
			});
		}

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

	function append_weekly() {
		var v = document.vinform1.appname.value;
		if (v == "50 W") {
			$(function() {
				$('#downPayment').val("8000");
				$('#customerWeeklyPayment').val("1000");
			});
		} else if (v == "80 W") {
			$(function() {
				$('#downPayment').val("10000");
				$('#customerWeeklyPayment').val("1500");
			});
		} else if (v == "100 W") {
			$(function() {
				$('#downPayment').val("12000");
				$('#customerWeeklyPayment').val("2000");
			});
		}

	}

	function appendexee_weekly() {

		var exev = document.existing_customer.exappname2.value;

		if (exev == "50 W") {
			$(function() {

				$('#exe2downPayment').val("8000");
				$('#exe2customerWeeklyPayment').val("1000");
				// 			  $("#execustomer_btn").show();

			});
		} else if (exev == "80 W") {
			$(function() {

				$('#exe2downPayment').val("10000");
				$('#exe2customerWeeklyPayment').val("1500");
			});
		} else if (exev == "100 W") {
			$(function() {
				$('#exe2downPayment').val("12000");
				$('#exe2customerWeeklyPayment').val("2000");
			});
		} else {
			$(function() {

			});
		}

	}

	function appendexe_weekly() {
		// 		alert('clled appendexe_weekly');
		var exev = document.existingcustomer_form.exappname.value;
		// 		alert('get value from '+v);
		if (exev == "50 W") {
			$(function() {

				$('#exedownPayment').val("8000");
				$('#execustomerWeeklyPayment').val("1000");
				$("#execustomerid").show();

			});
		} else if (exev == "80 W") {
			$(function() {

				$('#exedownPayment').val("10000");
				$('#execustomerWeeklyPayment').val("1500");
				$("#execustomerid").show();
			});
		} else if (exev == "100 W") {
			$(function() {

				$('#exedownPayment').val("12000");
				$('#execustomerWeeklyPayment').val("2000");
				$("#execustomerid").show();
			});
		} else {
			$(function() {

				$("#execustomerid").hide();
			});
		}

	}

	function append_salesman() {
		var salesman = document.vinform1.gsalesman3.value;

		$('#salesmandetail').val(salesman);

	}

	function getInfo() {

		if (request.readyState == 4) {

			var val = request.responseText;
			// 			alert(val);
			if (val >= 1) {

				$('#messagephone').show();
				// 				$('#messagephone').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagephone').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function sendInfogsm() {

		var v = document.vinform1.customerappgsm.value;

		var url = "gsm_validation.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestgsm = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestgsm = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestgsm.onreadystatechange = getInfogsm;
			requestgsm.open("GET", url, true);
			requestgsm.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}
	function getInfogsm() {

		if (requestgsm.readyState == 4) {

			var val = requestgsm.responseText;

			if (val >= 1) {
				$('#messagegsm').show();
				// 				$('#messagegsm').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagegsm').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function sendInfoproductid() {

		var v = document.vinform1.customerappproId.value;

		var url = "product_id.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestproduct = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestproduct = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestproduct.onreadystatechange = getInfoproductid;
			requestproduct.open("GET", url, true);
			requestproduct.send();
		} catch (e) {
			alert("Unable to connect to server");
		}

	}
	function getInfoproductid() {

		if (requestproduct.readyState == 4) {

			var val = requestproduct.responseText;

			if (val >= 1) {
				$('#messageproduct').show();
				// 				$('#messageproduct').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messageproduct').hide();
				$('#click').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	var request;

	function checkphone() {

		var v = document.salesman.salesphone.value;
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

				// 				$('#messagephonevalid').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
			} else {
				$('#messagephonevalid').hide();
			}

			// document.getElementById('amit').innerHTML = val;
		}
	}

	function cniccheck() {

		var v = document.salesman.salescnic.value;
		var url = "cniccheck.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			request = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			request = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			request.onreadystatechange = getInfocnic;
			request.open("GET", url, true);
			request.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnic() {

		if (request.readyState == 4) {

			var val = request.responseText;

			if (val >= 1) {
				$('#messagenic').show();
				// 				$('#messagenic').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
			} else {
				$('#messagenic').hide();
			}

			// document.getElementById('amit').innerHTML = val;
		}
	}

	function emailphone_tech() {

		var v = document.tech_form.phone.value;
		var url = "phone_check_tech.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfophone_tech;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfophone_tech() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#tech_phone').show();
				// 				$('#tech_phone').delay(5000).fadeOut('slow');

				$('#click_tech').prop('disabled', true);

			} else {
				$('#tech_phone').hide();
				$('#click_tech').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function chechcnic_tech() {

		var v = document.tech_form.cnic.value;
		var url = "cnic_chec_tech.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestphone = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestphone = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestphone.onreadystatechange = getInfocnic_tech;
			requestphone.open("GET", url, true);
			requestphone.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfocnic_tech() {

		if (requestphone.readyState == 4) {

			var val = requestphone.responseText;

			if (val >= 1) {
				$('#tech_cnic').show();
				// 				$('#tech_cnic').delay(5000).fadeOut('slow');

				$('#click_tech').prop('disabled', true);

			} else {
				$('#tech_cnic').hide();
				$('#click_tech').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	function check_customer_cnic() {

		var v = document.vinform1.customercnic.value;

		var url = "check_customer_cnic.jsp?val=" + v;
		if (window.XMLHttpRequest) {
			requestcnic = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			requestcnic = new ActiveXObject("Microsoft.XMLHTTP");
		}
		try {
			requestcnic.onreadystatechange = getInfo_customer12;
			requestcnic.open("GET", url, true);
			requestcnic.send();
		} catch (e) {
			alert("Unable to connect to server");
		}
	}
	function getInfo_customer12() {

		if (requestcnic.readyState == 4) {

			var val = requestcnic.responseText;

			if (val >= 1) {

				$('#messagecnic').show();
				// 				$('#messagecnic').delay(5000).fadeOut('slow');
				$('#click').attr('disabled', 'disabled');
				$('#click').prop('disabled', true);

			} else {
				$('#messagecnic').hide();
				$('#click').prop('disabled', false);
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
			// 			alert(val);
			if (val >= 1) {
				$('#messagephonevalid').show();

				//                 $('#messagephonevalid').delay(5000).fadeOut('slow');
				$('#click_Salesman').attr('disabled', 'disabled');
			} else {
				$('#messagephonevalid').hide();
				$('#click_Salesman').prop('disabled', false);
			}
			// document.getElementById('amit').innerHTML = val;
		}
	}

	/* webshims.setOptions('forms-ext', {
		replaceUI : 'auto',
		types : 'number'
	});
	webshims.polyfill('forms forms-ext'); */
</script>
<script>
	//danish
	function loan(a) {
		//alert('sdfkdf'+a);
		if (a == 'ON') {
			document.getElementById("loans0").style.display = 'none';
			document.getElementById("loans1").style.display = 'block';
		} else if (a == 'OFF') {
			document.getElementById("loans0").style.display = 'block';
			document.getElementById("loans1").style.display = 'none';
		}
	}

	function bank(a) {
		//alert('sdfkdf'+a);
		if (a == 'ON') {
			document.getElementById("bank0").style.display = 'none';
			document.getElementById("bank1").style.display = 'block';
		} else if (a == 'OFF') {
			document.getElementById("bank0").style.display = 'block';
			document.getElementById("bank1").style.display = 'none';
		}
	}
</Script>

<script>
	function getFoResults(val) {
		if (val.checked && val.value == "Car") {
			$('#slicence').show();
			$('#slabel').show();
		} else if (val.checked && val.value == "Bike") {
			$('#slicence').show();
			$('#slabel').show();
		} else if (val.checked && val.value == "None") {
			$('#slicence').hide();
			$('#slabel').hide();
		}
	};

	function getResults(val) {
		if (val.checked && val.value == "Car") {
			$('#licence').show();
			$('#label').show();
		} else if (val.checked && val.value == "Bike") {
			$('#licence').show();
			$('#label').show();
		} else if (val.checked && val.value == "None") {
			$('#licence').hide();
			$('#label').hide();
		}
	};

	function getEducation(val) {
		//     	    elem.checked && elem.value == "Show" ? $('#licence').show() : $('#label').show();
		if (val.checked && val.value == "Educated") {
			$('#college').show();
			$('#degree').show();
			$('#dateOfStart').show();
			$('#dateOfEnd').show();
			$('#percentage').show();
			$('#a').show();
			$('#b').show();
			$('#c').show();
			$('#d').show();
			$('#e').show();

		} else {
			$('#college').hide();
			$('#degree').hide();
			$('#dateOfStart').hide();
			$('#dateOfEnd').hide();
			$('#percentage').hide();
			$('#a').hide();
			$('#b').hide();
			$('#c').hide();
			$('#d').hide();
			$('#e').hide();
		}
	};

	function getFoEducation(val) {
		//     	    elem.checked && elem.value == "Show" ? $('#licence').show() : $('#label').show();
		if (val.checked && val.value == "true") {
			$('#sCollege').show();
			$('#sDegree').show();
			$('#sDateOfStart').show();
			$('#sDateOfEnd').show();
			$('#sPercentage').show();
			$('#sa').show();
			$('#sb').show();
			$('#sc').show();
			$('#sd').show();
			$('#se').show();

		} else {
			$('#sCollege').hide();
			$('#sDegree').hide();
			$('#sDateOfStart').hide();
			$('#sDateOfEnd').hide();
			$('#sPercentage').hide();
			$('#sa').hide();
			$('#sb').hide();
			$('#sc').hide();
			$('#sd').hide();
			$('#se').hide();
		}
	};

	function getMarritalStatus(val) {
		if (val.value == "Married") {
			$('#wifephone').show();
			$('#wifename').show();
			$('#wifeCnic').show();
			$('#wifedateOfBirth').show();
			$('#w').show();
			$('#x').show();
			$('#y').show();
			$('#z').show();
		} else {
			$('#wifephone').hide();
			$('#wifename').hide();
			$('#wifeCnic').hide();
			$('#wifedateOfBirth').hide();
			$('#w').hide();
			$('#x').hide();
			$('#y').hide();
			$('#z').hide();
		}
	};
</script>

<script>
	$(document).ready(function() {
		/*  $('#licence').hide();
		$('#label').hide(); */
		$('#college').hide();
		$('#degree').hide();
		$('#dateOfStart').hide();
		$('#dateOfEnd').hide();
		$('#percentage').hide();
		$('#a').hide();
		$('#b').hide();
		$('#c').hide();
		$('#d').hide();
		$('#e').hide();
		$('#wifephone').hide();
		$('#wifename').hide();
		$('#wifeCnic').hide();
		$('#wifedateOfBirth').hide();
		$('#w').hide();
		$('#x').hide();
		$('#y').hide();
		$('#z').hide();
		$('#doCnic').hide();
		$('#cnicLabel').hide();

		$('#sCollege').hide();
		$('#sDegree').hide();
		$('#sDateOfStart').hide();
		$('#sDateOfEnd').hide();
		$('#sPercentage').hide();
		$('#sa').hide();
		$('#sb').hide();
		$('#sc').hide();
		$('#sd').hide();
		$('#se').hide();

		$('#sdoCnic').hide();
		$('#cnicFoLabel').hide();

		$('#slicence').hide();
		$('#slabel').hide();

		$('#licence').hide();
		$('#label').hide();

	});
</script>

<script>
	$(document).ready(function() {

		$("#butn").click(function() {
			/* Single line Reset function executes on click of Reset Button */
			$("#salesman")[0].reset();
		});
	});

	function getFOList() {

		console.log('getFOList() called.. ');

		$.ajax({
			url : 'AddUsersServlet',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'getAllFODistrictWise'
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

	function getTehsilND(doid) {

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
				console.log('cities ... ' + json.data);
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

	function getVle(vle_id) {

		$.ajax({
			url : 'UserFormsDataController',
			dataType : 'json',
			method : 'POST',
			data : {
				action : 'salesmanInfo',
				id : vle_id
			},
			success : function(data) {
				var name = data.NDDetail.salesman_name;
				var joiningDate = data.NDDetail.salesman_joindate;
				var dateOfBirth = data.NDDetail.date_of_birth;
				var Cnic = data.NDDetail.salesman_cnic;
				var gender = data.NDDetail.gender;
				var marritalStatus = data.NDDetail.marrital_status;
				var primaryPhone = data.NDDetail.salesman_primary_phone;
				var secondaryPhone = data.NDDetail.salesmanSecondaryPhone;
				var address = data.NDDetail.salesman_address;
				var educated = data.NDDetail.education;
				var salary = data.NDDetail.salesman_basic_sallery;
				var ndEmail = data.NDDetail.salesman_email;
				var foid = data.NDDetail.fo_id;
				var tehsil = data.NDDetail.city_id;
				var NDAccNum = data.NDDetail.vle_acount_no;
				var perSale = data.NDDetail.per_sale;
				var district = data.NDDetail.salesman_district;

				$('#ndFullName').val(name);
				$('#joiningDate').val(joiningDate);
				$('#ndDateOfBirth').val(dateOfBirth);
				$('#ndSalary').val(salary);
				$('#ndCnic').val(Cnic);
				$('#doCnic').show();
				$('#cnicLabel').show();

				$('#ndEducation').selectpicker();
				$('#ndEducation').selectpicker('val', educated);

				$('#ndCity').selectpicker();
				$('#ndCity').selectpicker('val', tehsil);

				$('#foList').selectpicker();
				$('#foList').selectpicker('val', foid);

				RadionButtonSelectedValueSet('ndGender', gender);
				RadionButtonSelectedValueSet('nd_marital_status',
						marritalStatus);

				if (primaryPhone != 'null') {
					primaryPhone = primaryPhone.substring(2);
					primaryPhone = [ primaryPhone.slice(0, 3), '-',
							primaryPhone.slice(3) ].join('');
					$('#ndPrimaryPhone').val(primaryPhone);
				}

				if (secondaryPhone != '' || secondaryPhone != "") {
					console.log('secondaryPhone ' + secondaryPhone + 'l');
					secondaryPhone = secondaryPhone.substring(2);
					secondaryPhone = [ secondaryPhone.slice(0, 3), '-',
							secondaryPhone.slice(3) ].join('');
					$('#ndSecondaryPhone').val(secondaryPhone);
				}
				$('#ndAccNumber').val(NDAccNum);
				$('#ndAddress').val(address);
				$('#ndEmail').val(ndEmail);
				$('#ndPerSellCommission').val(perSale);

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
		class="fade page-without-sidebar page-header-fixed">
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
		<!-- end #header -->



		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<!-- <ol class="breadcrumb pull-right">
				<li><a href="DistrictOfficerDashboard">Home</a></li>
				<li class="active">District Officer Form</li>

			</ol> -->
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<!-- 			<h1 class="page-header">District Officer Form</h1> -->
			<!-- end page-header -->

			<!-- begin row -->
			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse" style="margin-top: 3%;">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Nizam Dost Form</h4>
						</div>
						<div class="panel-body">

							<div id="wizard">
								<!-- begin wizard step-2 -->
								<h3>Update Nizam Dost</h3>
								<div>
									<fieldset>
										<!-- begin row -->
										<div class="row">
											<!-- begin col-6 -->
											<form method="post" class="form-horizontal" data-Nd_form
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
																	data-table="salesman"
																	data-column="salesman_primary_phone" data-type="phone"
																	name="ndPrimaryPhone" maxlength="11"
																	required="required"
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
																onchange="addressValidation('ndAddress','addressResult')">
															<span style="color: red; font-size: 12px;"
																id="addressResult"></span>
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
															onchange="numericValidation('ndSalary','ssalaryResult','Invalid Number')"
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
																onchange="numericValidationAT('ndPerSellCommission','percellcommTimeResult','Invalid Number')">
															<span style="color: red; font-size: 12px;"
																id="percellcommTimeResult"></span>
														</div>
													</div>
												</div>
												<%-- <input type="hidden" name="district"
												value="<%=bean.getUser_district()%>"> <input
												type="hidden" name="doId" value="<%=bean.getUserId()%>"> --%>
												<input id="ndid" name="ndid" type="hidden" value="">
												<script>
													document
															.getElementById("ndid").value = (getUrlParameter('vleId'));
												</script>
												<div class="form-group">
													<div class="col-md-4 col-md-offset-4" id="nd_added"
														style="color: red; font-size: 12px; font-weight: bold;">
													</div>
													<div class="col-sm-offset-4 col-md-3"></div>
													<div class="col-md-2">
														<input type="button" name="update_nd" id="ndSubmit"
															onclick="updateNd();" value="Update Salesman"
															class="btn btn-block btn-info ">
													</div>
												</div>


											</form>
											<!-- end col-6 -->
										</div>
										<!-- end row -->
									</fieldset>

								</div>
								<!-- end wizard step-2 -->
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

		<%
			}
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
		$(document)
				.ready(
						function() {
							App.init();
							FormWizard.init();
							FormPlugins.init();

							$(
									'#ndFullName, #ndDateOfBirth, #ndCnic, #joiningDate, #ndPrimaryPhone, #ndAddress, #ndSalary, #ndPerSellCommission, #ndSecondaryPhone')
									.change(salesmanFormUpdate);
						});
	</script>
	<script>
		function getUrlParameter(sParam) {
			var sPageURL = decodeURIComponent(window.location.search
					.substring(1)), sURLVariables = sPageURL.split('&'), sParameterName, i;

			for (i = 0; i < sURLVariables.length; i++) {
				sParameterName = sURLVariables[i].split('=');

				if (sParameterName[0] === sParam) {
					return sParameterName[1] === undefined ? true
							: sParameterName[1];
				}
			}
		};

		window.onload = getVle(getUrlParameter('vleId'));
	</script>


</body>
</html>