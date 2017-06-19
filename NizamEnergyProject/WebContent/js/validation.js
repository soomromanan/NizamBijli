var numberValidationVar = "1";

var name = "0";
var bdageget = "0";
var cnicget = "0";
var doPrimaryPhone = "0";
var addressget = "0";
var salaryget = "0";
var doEmail = "0";
var DoEducation = "0";
var joiningDate = "0";
var doDistrict = "0";

/* update do form */
var firstnameup = "1";
var dateOfBirthup = "1";
var doCnicup = "1";
var updateDoEducation = "1";
var joiningDateup = "1";
var dophone1up = "1";
var dophone2up = "1";
var doReferralNumber1up = "1";
var doReferralNumber2up = "1";
var doemailup = "1";
var addressup = "1";
var doDistrictup = "1";
var salaryup = "1";
/* end do form update */

/* insert fo form */
var foNameGet = "";
var foCityGet = "";
var foJoiningDateGet = "";
var foBDget = "";
var foCnicGet = "";
var foGetEducation = "";
var foPhoneGet = "";
var foSecondaryPhone = "";
var foEmail = "";
var foTehsil = "";
var foAddressGet = "";
var foSalaryGet = "";
var foPerSellCommission = "";
/* end insert fo form */

/* update fo form */
var foNameGetUp = "1";
var foCityGetUp = "1";
var foJoiningDateGetUp = "1";
var foBDgetUp = "1";
var foCnicGetUp = "1";
var foGetEducationUp = "1";
var foPhoneGetUp = "1";
var foSecondaryPhoneUp = "1";
var foEmailUp = "1";
var foTehsilUp = "1";
var foAddressGetUp = "1";
var foSalaryGetUp = "1";
var foPerSellCommissionUp = "1";
/* end update fo form */

/* salesman insert form starts */
var ndFullName = "";
var ndBDget = "";
var ndCnicGet = "";
var ndJoiningDateGet = "";
var ndPrimaryPhoneGet = "";
var ndSecondaryPhoneGet = "";
var ndEmailGet = "";
var ndAddressGet = "";
var ndFoGet = "";
var ndTehsilGet = "";
var ndSalaryGet = "";
var ndPerSellCommission = "";
/* end salesman insert form */

/* salesman update form starts */
var ndFullNameUpdate = "1";
var ndBDgetUpdate = "1";
var ndCnicUpdate = "1";
var ndJoiningDateUpdate = "1";
var ndPrimaryPhoneUpdate = "1";
var ndSecondaryPhoneUpdate = "1";
var ndEmailUpdate = "1";
var ndAddressUpdate = "1";
var ndFoUpdate = "1";
var ndTehsilUpdate = "1";
var ndSalaryUpdate = "1";
var ndPerSellCommissionUpdate = "1";
/* salesman update form starts */

var vlefirstname = "";
var vledateOfBirth = "";
var vledoCnic = "";
var vledophone1 = "";
var vleaddress = "";
/*
 * var vlesalary = ""; var vlebeforeTime = ""; var vleafterTime = "";
 */
var vleonTime = "";

/*
 * var fromfilter = "0"; var tofilter = "0";
 */

function cnicValidation(cnicfield, cnicMsg) {
	var cnic = document.getElementById(cnicfield).value;
	// var myRegExp = new RegExp(/\d[0-9+]{5}-\d[0-9+]{7}-\d[0-9+]{1}$/);
	var myRegExp = new RegExp(/^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$/);

	if (myRegExp.test(cnic)) {
		cnicget = "1";
		vledoCnic = "1";
		doCnicup = "1";
		ndCnicGet = "1";
		ndCnicUpdate = "1";
		document.getElementById(cnicMsg).innerHTML = '';
		document.getElementById(cnicfield).style.borderColor = "#ccd0d4";
	} else {

		cnicget = "0";
		vledoCnic = "0";
		doCnicup = "0";
		ndCnicGet = "0";
		ndCnicUpdate = "0";
		document.getElementById(cnicMsg).innerHTML = 'Invalid CNIC';
		document.getElementById(cnicfield).style.borderColor = "red";
	}

}

function cnicValidationforFo(cnicfield, cnicMsg) {
	// var idToTest = '12345-1234567-1',
	var cnic = document.getElementById(cnicfield).value;
	// var myRegExp = new RegExp(/\d[0-9+]{5}-\d[0-9+]{7}-\d[0-9+]{1}$/);
	var myRegExp = new RegExp(/^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$/);

	if (myRegExp.test(cnic)) {

		foCnicGet = "1";
		foCnicGetUp = "1";
		console.log('foCnicGet in if ... ' + myRegExp.test(cnic));
		document.getElementById(cnicMsg).innerHTML = '';
		document.getElementById(cnicfield).style.borderColor = "#ccd0d4";
	} else {
		foCnicGet = "0";
		foCnicGetUp = "0";
		console.log('foCnicGet in else ... ' + myRegExp.test(cnic));
		document.getElementById(cnicMsg).innerHTML = 'Invalid CNIC';
		document.getElementById(cnicfield).style.borderColor = "red";
	}

}

function mobileNumberValidation(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {
		doPrimaryPhone = "1";
		dophone1up = "1";

		foPhoneGet = "1";
		foPhoneGetUp = "1";

		ndPrimaryPhoneGet = "1";
		ndPrimaryPhoneUpdate = "1";

		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#submit').prop("disabled", false);
		$('#updateSalesman').prop("disabled", false);
		$('#foSubmit').prop("disabled", false);
	} else {

		doPrimaryPhone = "0";
		dophone1up = "0";

		foPhoneGet = "0";
		foPhoneGetUp = "0";

		ndPrimaryPhoneGet = "0";
		ndPrimaryPhoneUpdate = "0";

		$('#submit').prop("disabled", true);
		$('#foSubmit').prop("disabled", true);
		$('#updateSalesman').prop("disabled", true);
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
	}

}

function numberValidation(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	
	if (mobileRegExp.test(mobileNumber)) {

		numberValidationVar = "1";

		dophone2up = "1";
		doReferralNumber1up = "1";
		doReferralNumber2up = "1";

		foSecondaryPhone = "1";
		foSecondaryPhoneUp = "1";

		ndSecondaryPhoneGet = "1";
		ndSecondaryPhoneUpdate = "1";

		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";

		$('#submit').removeClass('disabled');
		$('#submit').attr("data-toggle", "modal");

		$('#updateSalesman').removeClass('disabled');
		$('#updateSalesman').attr("data-toggle", "modal");

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').attr("data-toggle", "modal");

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

	} else if (mobileNumber == '') {

		numberValidationVar = "1";

		$('#submit').removeClass('disabled');
		$('#submit').attr("data-toggle", "modal");

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').attr("data-toggle", "modal");

		$('#updateSalesman').removeClass('disabled');
		$('#updateSalesman').attr("data-toggle", "modal");

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";

		console.error('Else IF >> Iam Null')
	} else {

		dophone2up = "0";
		doReferralNumber1up = "0";
		doReferralNumber2up = "0";

		foSecondaryPhone = "0";
		foSecondaryPhoneUp = "0";

		ndSecondaryPhoneGet = "0";
		ndSecondaryPhoneUpdate = "0";

		numberValidationVar = "0";

		$('#submit').addClass('disabled');
		$('#submit').removeAttr('data-toggle');

		$('#foSubmit').addClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

		$('#ndSubmit').addClass('disabled');
		$('#ndSubmit').attr("data-toggle", "modal");

		$('#updateSalesman').addClass('disabled');
		$('#updateSalesman').attr("data-toggle", "modal");

		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
		console.error('elseeeeee  thae..')
	}

}

function mobileNumberValidation2(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {

		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#submit').prop("disabled", false);
		$('#updateSalesman').prop("disabled", false);
		$('#foSubmit').prop("disabled", false);
	} else {

		$('#submit').prop("disabled", true);
		$('#foSubmit').prop("disabled", true);
		$('#updateSalesman').prop("disabled", true);
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
	}

}

function mobileNumberValidationforFo(mobilefield, msg) {
	var mobileNumber = document.getElementById(mobilefield).value;
	mobileNumber = mobileNumber.replace("-", "");
	var mobileRegExp = new RegExp(/^(\D*([3])(\D*)([0-9])(\d{8})\D*)$/);
	if (mobileRegExp.test(mobileNumber)) {
		foPhoneGet = "1";
		foSecondaryPhone = "1";
		foPhoneGetUp = "1";
		foSecondaryPhoneUp = "1";
		document.getElementById(msg).innerHTML = '';
		document.getElementById(mobilefield).style.borderColor = "#ccd0d4";
		$('#updateSalesman').prop("disabled", false);
		$('#foSubmit').prop("disabled", false);

	} else {
		foSecondaryPhone = "0";
		foPhoneGet = "0";
		foPhoneGetUp = "0";
		foSecondaryPhoneUp = "0";
		document.getElementById(msg).innerHTML = 'Invalid mobile number';
		document.getElementById(mobilefield).style.borderColor = "red";
		$('#foSubmit').prop("disabled", true);
		$('#updateSalesman').prop("disabled", true);
	}

}

function numericValidation(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		salaryget = "1";
		foSalaryGet = "1";
		foSalaryGetUp = "1";
		ndSalaryGet = "1";
		salaryup = "1";
		ndSalaryUpdate = "1";

		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		salaryup = "0";
		salaryget = "0";
		foSalaryGet = "0";
		foSalaryGetUp = "0";
		ndSalaryGet = "0";
		ndSalaryUpdate = "0";
		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}

function numericValidationBT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {

		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {

		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}

function numericValidationAT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		console.log('IF number.length' + number.length)
		foPerSellCommission = "1";
		foPerSellCommissionUp = "1";

		ndPerSellCommission = "1";
		ndPerSellCommissionUpdate = "1";
		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";

		// $('#ndSubmit').prop("disabled", false);
		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').attr("data-toggle", "modal");

		$('#foSubmit').prop("disabled", false);
		$('#foSubmit').prop("disabled", false);

		salesmanFormUpdate();
		SalesmanformSubmit();
		FOformSubmit();
		FOformUpdate();
	} else if (number.length == 0) {
		console.log('ELsE number.length' + number.length)
		foPerSellCommission = "0";
		foPerSellCommissionUp = "0";

		ndPerSellCommission = "0";
		ndPerSellCommissionUpdate = "0";

		document.getElementById(msg).innerHTML = errorMsg
		document.getElementById(numericField).style.borderColor = "red";

		SalesmanformSubmit();
		FOformSubmit();
		FOformUpdate();
	}

}

function numericValidationAC(numericField, msg, errorMsg) {
	console.error('numericValidationAC')
	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		console.error('IF numericValidationAC' + number + 'C')

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').removeAttr('data-toggle');

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').removeAttr('data-toggle');

		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else if (number == '') {
		console.error('else IF numericValidationAC' + number + 'C')

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').removeAttr('data-toggle');

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').removeAttr('data-toggle');

		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		console.error('Else numericValidationAC' + number + 'C')

		$('#foSubmit').addClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').removeAttr('data-toggle');

		document.getElementById(msg).innerHTML = errorMsg
		document.getElementById(numericField).style.borderColor = "red";
	}

}

function numericValidationOT(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	var numericRegExp = new RegExp(/^(0|[1-9][0-9]*)$/);
	if (number.length > 0) {
		foOnTimeGet = "1";
		vleonTime = "1";

		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		foOnTimeGet = "0";
		vleonTime = "0";

		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(numericField).style.borderColor = "red";
	}

}

function nameValidation(nameField, msg, errorMsg) {
	var mob = document.getElementById(nameField).value;
	console.log('mob  --- ' + mob);
	var patt4 = /^([^0-9]*)$/;
	if (mob.length != 0 && patt4.test(mob)) {
		name = "1";
		firstnameup = "1";

		foNameGet = "1";
		foNameGetUp = "1";

		ndFullName = "1";
		ndFullNameUpdate = "1";

		document.getElementById(msg).innerHTML = "";
		document.getElementById(nameField).style.borderColor = "#ccd0d4";
	} else {
		name = "0";
		firstnameup = "0";

		foNameGetUp = "0";
		foNameGet = "0";

		ndFullName = "0";
		ndFullNameUpdate = "0";

		document.getElementById(msg).innerHTML = errorMsg;
		document.getElementById(nameField).style.borderColor = "red";
	}
}

function addressValidation(addressField, msg) {
	var address = document.getElementById(addressField);
	var message = document.getElementById(msg);
	if (address.value == "" || address.value.length < 4) {
		addressget = "0";
		foAddressGet = "0";
		foAddressGetUp = "0";
		vleaddress = "0";
		addressup = "0";
		ndAddressGet = "0";
		ndAddressUpdate = "0"

		document.getElementById(addressField).style.borderColor = "red";
		message.innerHTML = "Invalid Address";
	} else {
		addressup = "1";
		addressget = "1";
		foAddressGet = "1";
		foAddressGetUp = "1";
		vleaddress = "1";
		ndAddressGet = "1";
		ndAddressUpdate = "1"

		message.innerHTML = "";
		document.getElementById(addressField).style.borderColor = "#ccd0d4";
	}

}

function emailValidation(emailField, msg) {
	var email = document.getElementById(emailField).value;
	var emailMsg = document.getElementById(msg);
	var patt4 = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	if (!patt4.test(email)) {
		document.getElementById(emailField).style.borderColor = "red";
		emailMsg.innerHTML = "Invalid email";
		doEmail = "0";
		doemailup = "0";

		foEmail = "0";
		foEmailUp = "0";

		console.log('foEmail if -- ' + foEmailUp + " email -- " + email);

		$('#foSubmit').addClass('disabled');
		$('#foSubmit').removeAttr('data-toggle');
	} else {
		console.log('foEmail else -- ' + foEmailUp + " email -- " + email);
		doEmail = "1";
		foEmail = "1";
		foEmailUp = "1";
		doemailup = "1";
		emailMsg.innerHTML = "";
		document.getElementById(emailField).style.borderColor = "#ccd0d4";

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

	}

}

function emailValidationUsers(emailField, msg) {
	var email = document.getElementById(emailField).value;
	var emailMsg = document.getElementById(msg);
	var patt4 = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;

	if (patt4.test(email)) {
		emailMsg.innerHTML = "";
		document.getElementById(emailField).style.borderColor = "#ccd0d4";

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').attr('data-toggle');

		$('#foSubmit').removeClass('disabled');
		$('#foSubmit').attr("data-toggle", "modal");

		$('#ndSubmit').removeClass('disabled');
		$('#ndSubmit').attr("data-toggle", "modal");

	} else {
		if (email == '' || email == "") {
			emailMsg.innerHTML = "";
			document.getElementById(emailField).style.borderColor = "#ccd0d4";

			$('#foSubmit').removeClass('disabled');
			$('#foSubmit').attr('data-toggle');

			$('#foSubmit').removeClass('disabled');
			$('#foSubmit').attr("data-toggle", "modal");

			$('#ndSubmit').removeClass('disabled');
			$('#ndSubmit').attr("data-toggle", "modal");

		} else {
			document.getElementById(emailField).style.borderColor = "red";
			emailMsg.innerHTML = "Invalid email";

			$('#foSubmit').addClass('disabled');
			$('#foSubmit').removeAttr('data-toggle');

			$('#foSubmit').addClass('disabled');
			$('#foSubmit').removeAttr('data-toggle');

			$('#ndSubmit').addClass('disabled');
			$('#ndSubmit').removeAttr('data-toggle');
		}
	}

}

function licenseValidation(licenseField, msg) {
	var license = document.getElementById(licenseField).value;
	var licensenum = document.getElementById(msg);
	var patt4 = /^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
	if (!patt4.test(license)) {
		document.getElementById(licenseField).style.borderColor = "red";
		licensenum.innerHTML = "Invalid email";

	} else {
		licensenum.innerHTML = "";
		document.getElementById(licenseField).style.borderColor = "#ccd0d4";

	}

}

function setDateExpression(event, datefield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(datefield).value;
		if (mystring.split('').length == 2 || mystring.split('').length == 5) {
			mystring = mystring + "/";
		}
		document.getElementById(datefield).value = mystring;
	}
	return true;

}

function setNicDash(event, cnicfield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(cnicfield).value;
		if (mystring.split('').length == 5 || mystring.split('').length == 13) {
			mystring = mystring + "-";
		}
		document.getElementById(cnicfield).value = mystring;
	}
	return true;
}

function setMobileDash(event, cnicfield) {
	var keycode = event.which || event.keyCode;
	if (keycode != 8) {
		var mystring = document.getElementById(cnicfield).value;
		if (mystring.split('').length == 3) {
			mystring = mystring + "-";
		}
		document.getElementById(cnicfield).value = mystring;
	}
	return true;

}

function nameCharahterValidation(nameField) {
	var mystring = document.getElementById(nameField).value;
	var sp = mystring.split(' ');
	var f, r;
	var word = new Array();
	for ( var i = 0; i < sp.length; i++) {
		f = sp[i].substring(0, 1).toUpperCase();
		r = sp[i].substring(1);
		word[i] = f + r;
	}
	newstring = word.join(' ');
	document.getElementById(nameField).value = newstring;
	return true;
}

function emailCharahterValidation(nameField) {
	var mystring = document.getElementById(nameField).value;
	var sp = mystring.split(' ');
	var f, r;
	var word = new Array();
	for (i = 0; i < sp.length; i++) {
		f = sp[i].substring(0, 1).toLowerCase();
		r = sp[i].substring(1);
		word[i] = f + r;
	}
	newstring = word.join(' ');
	document.getElementById(nameField).value = newstring;
	return true;
}

function check_age(ageField, msg) {

	var currentdate = new Date();
	// var date = "Last Sync: " + currentdate.getDate() + "/"
	// + (currentdate.getMonth()+1) + "/"
	// + currentdate.getFullYear() ;

	// alert('current Date :'+currentdate.getFullYear());

	var dobb = document.getElementById("dob").value;
	var age = new Date(dobb);

	// alert('your Age: '+age.getFullYear());

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// $('#age').val("Your Current Age is "+currentDate+" One year");
	$('#age').val(currentDate);

	ageValidation(ageField, msg);

}

function pointsValidation(numericField, msg, errorMsg) {

	var number = document.getElementById(numericField).value;
	// var reg = "[-+]?[0-9]*[.]?[0-9]*";
	var numericRegExp = new RegExp('([-+]*\d+\.\d+|[-+]*\d+)');
	if (numericRegExp.test(number)) {
		document.getElementById(msg).innerHTML = '';
		document.getElementById(numericField).style.borderColor = "#ccd0d4";
	} else {
		document.getElementById(numericField).style.borderColor = "red";
		document.getElementById(msg).innerHTML = errorMsg;
	}

}

function checkAgeForCnic(val) {

	var currentdate = new Date();
	// var val = document.salesman.dateOfBirth.value;
	// alert(val);
	var age = new Date(val.value);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	if (currentDate >= 18) {
		$("#cnicLabel").show();
		$("#doCnic").show();
	}
}

function checkFoAgeForCnic(val) {

	var currentdate = new Date();
	// var val = document.salesman.dateOfBirth.value;
	// alert(val);
	var age = new Date(val.value);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	if (currentDate >= 18) {
		$("#cnicFoLabel").show();
		$("#sdoCnic").show();
	}
}

// abbas
function ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";

	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";

	}
}

function ageValidationDO(ageField, msg, nicId, nicLabel) {
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	console.log(age + ' -- age -- ' + '------- ' + ageMsg)
	// alert(currentDate);
	// alert(nicLabel);
	if (currentDate >= 18) {
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// $("#CNIC_ID_SPAN").show();
		bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		dateOfBirthup = "1";

		ageMsg.innerHTML = "";
		document.getElementById('dateOfBirth').style.borderColor = "#ccd0d4";

	} else {
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// $("#CNIC_ID_SPAN").hide();
		bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0";
		vledateOfBirth = "0";
		dateOfBirthup = "0";

		document.getElementById('dateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

function checkDateOfBirthFo(checkdate) {

	var EnteredDate = $("#sdateOfBirth").val(); // For JQuery

	if (EnteredDate != "" || EnteredDate != '') {
		foBDget = "1";
		console.log(checkdate + ' if checkdate ');
	} else {
		foBDget = "0";
	}
}

function ageValidationNewFO(ageField, msg, nicId, nicLabel) {
	console.error('1')
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	if (currentDate >= 18) {
		console.error('2')
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// $("#CNIC_ID_SPAN").show();
		// bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		foBDgetUp = "1";

		ageMsg.innerHTML = "";
		document.getElementById('sdateOfBirth').style.borderColor = "#ccd0d4";

	} else {
		console.error('3')
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// $("#CNIC_ID_SPAN").hide();
		// bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0";
		vledateOfBirth = "0";
		foBDgetUp = "0"

		document.getElementById('sdateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

function ageValidationNewVLE(ageField, msg, nicId, nicLabel) {
	var currentdate = new Date();
	var age = new Date(ageField.value);
	currentDate = currentdate.getFullYear() - age.getFullYear();
	var ageMsg = document.getElementById(msg);
	console.log('------- ' + ageMsg)
	if (currentDate >= 18) {
		$("#" + nicId).show();
		$("#" + nicLabel).show();
		// bdageget = "1";
		ageValidationNew = "1";
		foBDget = "1";
		vledateOfBirth = "1";
		ndBDget = "1";
		ndBDgetUpdate = "1";
		ageMsg.innerHTML = "";
		document.getElementById('ndDateOfBirth').style.borderColor = "#ccd0d4";

	} else {
		$("#" + nicId).hide();
		$("#" + nicLabel).hide();
		// bdageget = "0";
		ageValidationNew = "0";
		foBDget = "0";
		vledateOfBirth = "0";
		ndBDget = "0";
		ndBDgetUpdate = "0";

		document.getElementById('ndDateOfBirth').style.borderColor = "red";
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

// G1

function check_g1age(g1ageField, msg) {
	var currentdate = new Date();
	var dob = document.getElementById("g1guarantorDob").value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	if (currentDate >= 18) {
		$("#G1_CNIC_ID").show();
		$("#G1_CNIC_ID_SPAN").show();
		$('[name="g1guarantorDob"]').next().text("");
	} else {
		$("#G1_CNIC_ID").hide();
		$("#G1_CNIC_ID_SPAN").hide();

		$('[name="g1guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}

function g1_check_age(ageField, msg) {

	var currentdate = new Date();
	var dobb = document.vinform1.dob.value;
	var age = new Date(dobb);

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	$('#age').val(currentDate);

	g1_ageValidation(ageField, msg);

}

function g1_ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";
	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

// G2

function check_g2age(g2ageField, msg) {
	var currentdate = new Date();
	var dob = document.getElementById("g2guarantorDob").value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	// var age = document.getElementById(ageField).value;
	// var ageMsg = document.getElementById(msg);

	if (currentDate >= 18) {
		$("#G2_CNIC_ID").show();
		$("#G2_CNIC_ID_SPAN").show();
		$('[name="g2guarantorDob"]').next().text("");
	} else {
		$("#G2_CNIC_ID").hide();
		$("#G2_CNIC_ID_SPAN").hide();
		$('[name="g2guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}

function g2_check_age(ageField, msg) {

	var currentdate = new Date();
	var dobb = document.vinform1.dob.value;
	var age = new Date(dobb);

	var currentDate = currentdate.getFullYear() - age.getFullYear();
	$('#age').val(currentDate);

	g2_ageValidation(ageField, msg);

}

function g2_ageValidation(ageField, msg) {
	var age = document.getElementById(ageField).value;
	var ageMsg = document.getElementById(msg);

	if (age >= 18) {
		$("#CNIC_ID").show();
		$("#CNIC_ID_SPAN").show();
		ageMsg.innerHTML = "";
	} else {
		$("#CNIC_ID").hide();
		$("#CNIC_ID_SPAN").hide();
		ageMsg.innerHTML = "Not applicable for CNIC";
	}
}

function check_child_ageD(child_dobField, divField) {

	var currentdate = new Date();
	// alert("Hello1");
	var dob = document.getElementById(child_dobField).value;
	// alert("Hello2");
	var age = new Date(dob);
	// alert("Hello3");
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(child_dobField);
	// alert(divField);
	if (currentDate >= 18) {
		$("#" + divField).show();
		// document.getElementById(divField).childNodes[1].innerHTML.show();
	}
}

function check_child_age(child_dobField, msg) {
	var currentdate = new Date();
	var dob = document.vinform1.child_dob.value;
	var age = new Date(dob);
	var currentDate = currentdate.getFullYear() - age.getFullYear();
	// alert(currentDate);

	if (currentDate >= 18) {
		$("#CHILD_CNIC_ID").show();
		$("#G1_CNIC_ID_SPAN").show();
		$('[name="g1guarantorDob"]').next().text("");
	} else {
		$("#CHILD_CNIC_ID").hide();
		$("#G1_CNIC_ID_SPAN").hide();
		$('[name="g1guarantorDob"]').next().text("'Not applicable for CNIC'");
		// alert("else"+currentDate);
	}

	// ageValidation(currentDate, msg);
}

function existingData(id, result, e) {
	// alert('called')

	var existingData = document.getElementById(id).value;
	var column = $(e).data('column');
	var table = $(e).data('table');
	var type = $(e).data('type');
	console.log(column)
	console.log(table)
	console.log(type)
	console.log(existingData)
	$
			.ajax({
				url : 'ValidationServlet',
				dataType : 'json',
				method : 'POST',
				async : false,
				data : {
					PhoneNumber : existingData,
					column : column,
					table : table,
					type : type
				},
				success : function(data) {
					console.log(data)

					if (data.imei == true) {
						$("#" + result).show();
						$("#" + result).text("imei number already exist");
					} else if (data.imei == false) {
						$("#" + result).hide();
					}

					if (data.cnic == true) {
						document.getElementById(result).innerHTML += 'Cnic already exist';
						cnicget = "0";
						foCnicGet = "0";
						ndCnicGet = "0";

						$('#submit').prop("disabled", true);
						$('#ndSubmit').prop("disabled", true);
						$('#foSubmit').prop("disabled", true);
						$('#updateSalesman').prop("disabled", true);
					} else if (data.cnic == false) {

						$('#submit').prop("disabled", false);
						$('#ndSubmit').prop("disabled", false);
						$('#foSubmit').prop("disabled", false);
						$('#updateSalesman').prop("disabled", false);
					}
					if (data.email == true) {
						document.getElementById(result).innerHTML += 'email already exist';
						doEmail = "0";
						foEmail = "0";
						ndEmailGet = "0";
						$('#submit').prop("disabled", true);
						$('#ndSubmit').prop("disabled", true);
						$('#foSubmit').prop("disabled", true);
						$('#updateSalesman').prop("disabled", true);

						console.log('existingData true--' + foEmail + '-');
					} else if (data.email == false) {
						console.log('existingData false--' + foEmail + '-');
						$('#submit').prop("disabled", false);
						$('#ndSubmit').prop("disabled", false);
						$('#foSubmit').prop("disabled", false);
						$('#updateSalesman').prop("disabled", false);
					}
					if (data.phone == true) {
						document.getElementById(result).innerHTML += 'phone number already exist';
						doPrimaryPhone = "0";
						foPhoneGet = "0";
						foSecondaryPhone = "0";
						ndPrimaryPhoneGet = "0";
						ndSecondaryPhoneGet = "0";
						$('#submit').prop("disabled", true);
						$('#ndSubmit').prop("disabled", true);
						$('#foSubmit').prop("disabled", true);
						$('#updateSalesman').prop("disabled", true);
					} else if (data.phone == false) {
						$('#submit').prop("disabled", false);
						$('#ndSubmit').prop("disabled", false);
						$('#foSubmit').prop("disabled", false);
						$('#updateSalesman').prop("disabled", false);
					}

					if (data.consumer == true) {
						document.getElementById(result).innerHTML += 'consumer number already exist';
						console.log('consumer number already exist')
						$('#cashCustomerId').prop("disabled", true);
					} else if (data.consumer == false) {
						console.log('consumer number already not exist')
						document.getElementById(result).innerHTML = "";
						$('#cashCustomerId').prop("disabled", false);
					}
				}
			})
}

function existingMobileNumber(phone, result, e) {

	var existNumber = document.getElementById(phone).value;
	// alert('0000: '+existNumber)
	console.log("++++++++++++++++++++++++++++++++");
	console.log(e);
	var column = $(e).data('column');
	var table = $(e).data('table');
	var type = $(e).data('type');
	$
			.ajax({
				url : 'ValidationServlet',
				dataType : 'json',
				method : 'POST',
				async : false,
				data : {
					PhoneNumber : existNumber,
					column : column,
					table : table
				},
				success : function(data) {
					// alert('44')
					for ( var i = 0; i < data.length; i++) {
						// alert("outer if "+data[i].phone);
						if (data[i].cnic == true) {
							document.getElementById(result).innerHTML += 'Cnic already exist';
							// document.getElementById('ndSubmit').disabled =
							// true;
						} else if (data[i].phone == true) {
							document.getElementById(result).innerHTML += 'Phone number already exist';
							// document.getElementById('ndSubmit').disabled =
							// true;

							$('#submit').prop("disabled", true);
							$('#ndSubmit').prop("disabled", true);
							$('#foSubmit').prop("disabled", true);
							$('#updateSalesman').prop("disabled", true);
							// vledophone1 = "0";

							console.log(data[i].phone);
							// alert(data[i].phone);

						} else if (data[i].phone == undefined
								|| data[i].phone == "undefined"
								|| data[i].phone == null
								|| data[i].phone == false) {

							// document.getElementById('ndSubmit').disabled =
							// true;

							// vledophone1 = "0";

							$('#submit').prop("disabled", false);
							$('#ndSubmit').prop("disabled", false);
							$('#foSubmit').prop("disabled", false);
							$('#updateSalesman').prop("disabled", false);
							// alert("exist number : "+data[i].phone);
							console.log(data[i].phone);

						} else if (data[i].email == true) {
							document.getElementById(result).innerHTML += 'Email id already exist'
							// document.getElementById('ndSubmit').disabled =
							// true;

						} else if (data[i].imei == true) {
							document.getElementById(result).innerHTML += 'this imei already exist'

						}
					}
				}
			});
	if ($('#imeiresult').length === $('#imeiresult:contains("Empty")').length) {

	} else {
		$('#saveApp').attr("disabled", "disabled");
	}
}

function selectFOEducation() {
	var selected = $('#seducation').val(); // For JQuery
	console.log(selected + 'selected');

	if (selected != null) {
		foGetEducation = "1";
	} else {
		foGetEducation = "0";
	}
}

function doFormSubmit() {

	console.log('name ' + name + ' bdageget ' + bdageget + ' doEmail '
			+ doEmail + ' DoEducation ' + DoEducation + ' cnicget ' + cnicget
			+ ' doPrimaryPhone ' + doPrimaryPhone + ' addressget ' + addressget
			+ ' salaryget ' + salaryget + ' district ' + doDistrict
			+ ' #joining ' + joiningDate)

	if (name == "1" && bdageget == "1" && doEmail == "1" && DoEducation == "1"
			&& cnicget == "1" && doPrimaryPhone == "1" && addressget == "1"
			&& salaryget == "1" && doDistrict == "1" && joiningDate == "1") {

		$('#submit').prop("disabled", false);
	}

	else {
		$('#submit').prop("disabled", true);
	}

}

function doFormUpdate() {

	console.log('firstnameup ' + firstnameup + ' dateOfBirthup '
			+ dateOfBirthup + ' doCnicup ' + doCnicup + ' updateDoEducation '
			+ updateDoEducation + ' joiningDateup ' + joiningDateup
			+ ' dophone1up ' + dophone1up + ' addressget ' + doemailup
			+ ' addressup ' + addressup + ' salaryup ' + salaryup)

	if (firstnameup == "1" && dateOfBirthup == "1" && doCnicup == "1"
			&& updateDoEducation == "1" && joiningDateup == "1"
			&& dophone1up == "1" && doemailup == "1" && addressup == "1"
			&& salaryup == "1") {

		$('#submit').prop("disabled", false);
	} else {
		$('#submit').prop("disabled", true);
	}
}

function FOformSubmit() {

	// console.log('foNameGet '+foNameGet+' foBDget '+foBDget+' foCnicGet
	// '+foCnicGet+' foPhoneGet '+foPhoneGet + ' foJoiningDateGet ' +
	// foJoiningDateGet +' foGetEducation '+foGetEducation+' foAddressGet '+
	// foAddressGet+' foSalaryGet '+foSalaryGet+
	// ' foTehsil '+foTehsil+' foPerSellCommission '+foPerSellCommission)

	if (foNameGet == "1" && foBDget == "1" && foCnicGet == "1"
			&& foPhoneGet == "1" && foAddressGet == "1" && foSalaryGet == "1"
			&& foTehsil == "1" && foPerSellCommission == "1"
			&& foJoiningDateGet == "1" && foGetEducation == "1") {

		$('#foSubmit').prop("disabled", false);

	} else {
		$('#foSubmit').prop("disabled", true);
	}
}

function FOformUpdate() {

	// console.log('foNameGetUp '+foNameGetUp+' foCityGetUp '+foCityGetUp+'
	// foJoiningDateGetUp '+foJoiningDateGetUp+' foTehsilUp '+foTehsilUp +
	// ' foCnicGetUp ' + foCnicGetUp +' foPhoneGetUp '+foPhoneGetUp+' foBDgetUp
	// '+ foBDgetUp+' foAddressGetUp '+foAddressGetUp+
	// ' foSalaryGetUp '+foSalaryGetUp+' foPerSellCommissionUp
	// '+foPerSellCommissionUp +' foGetEducationUp '+foGetEducationUp)

	if (foNameGetUp == "1" && foCityGetUp == "1" && foJoiningDateGetUp == "1"
			&& foTehsilUp == "1" && foCnicGetUp == "1" && foPhoneGetUp == "1"
			&& foBDgetUp == "1" && foAddressGetUp == "1"
			&& foSalaryGetUp == "1" && foPerSellCommissionUp == "1"
			&& foGetEducationUp == "1") {

		$('#foSubmit').prop("disabled", false);
	} else {
		$('#foSubmit').prop("disabled", true);
	}
}

function SalesmanformSubmit() {

	// console.log('ndFullName: ' + ndFullName + ' ndBDget: ' + ndBDget + '
	// ndCnicGet: ' + ndCnicGet +
	// ' ndJoiningDateGet: ' + ndJoiningDateGet + ' ndPrimaryPhoneGet: ' +
	// ndPrimaryPhoneGet +
	// ' ndAddressGet: ' + ndAddressGet + ' ndSalaryGet: ' + ndSalaryGet + '
	// numberValidationVar '+numberValidationVar+
	// ' ndPerSellCommission: ' + ndPerSellCommission+' ndFoGet '+ndFoGet+ '
	// ndTehsilGet '+ndTehsilGet);

	if (ndFullName == "1" && ndBDget == "1" && ndCnicGet == "1"
			&& numberValidationVar == "1" && ndJoiningDateGet == "1"
			&& ndPrimaryPhoneGet == "1" && ndAddressGet == "1"
			&& ndFoGet == "1" && ndSalaryGet == "1"
			&& ndPerSellCommission == "1" && ndTehsilGet == "1") {
		$('#ndSubmit').prop("disabled", false);

	} else {
		$('#ndSubmit').prop("disabled", true);
	}
}

function salesmanFormUpdate() {
	console.log('ndFullNameUpdate: ' + ndFullNameUpdate + ' ndBDgetUpdate: '
			+ ndBDgetUpdate + ' ndCnicUpdate: ' + ndCnicUpdate
			+ ' numberValidationVar ' + numberValidationVar
			+ ' ndJoiningDateUpdate: ' + ndJoiningDateUpdate
			+ ' ndPrimaryPhoneUpdate: ' + ndPrimaryPhoneUpdate
			+ ' ndAddressUpdate ' + ndAddressUpdate + ' ndSalaryUpdate: '
			+ ndSalaryUpdate + ' ndPerSellCommissionUpdate: '
			+ ndPerSellCommissionUpdate);

	if (ndFullNameUpdate == "1" && ndBDgetUpdate == "1" && ndCnicUpdate == "1"
			&& ndJoiningDateUpdate == "1" && numberValidationVar == "1"
			&& ndPrimaryPhoneUpdate == "1" && ndAddressUpdate == "1"
			&& ndSalaryUpdate == "1" && ndPerSellCommissionUpdate == "1") {
		$('#ndSubmit').prop("disabled", false);

	} else {
		$('#ndSubmit').prop("disabled", true);
	}
}

function PaymentFilterInit() {

	if (fromfilter == "1" && tofilter == "1") {
		$('#filterid').prop("disabled", false);
	} else {
		$('#filterid').prop("disabled", true);
	}
}

function checkDate(checkdate) {

	var EnteredDate = $("#sjoiningDate").val(); // For JQuery

	if (EnteredDate != "" || EnteredDate != '') {
		foJoiningDateGet = "1";
	} else {
		foJoiningDateGet = "0";
	}

}

function checkJoiningDateDO(checkdate, errorMsg) {

	var EnteredDate = new Date(checkdate.value); // For JQuery
	var joiningMsg = document.getElementById(errorMsg);

	if (EnteredDate != "Invalid Date" || EnteredDate != 'Invalid Date') {
		joiningDateup = "1";
		joiningDate = "1";
		joiningMsg.innerHTML = "";
		document.getElementById('joiningDate').style.borderColor = "#ccd0d4";
	} else {
		joiningDateup = "0";
		joiningDate = "0";

		document.getElementById('joiningDate').style.borderColor = "red";
		joiningMsg.innerHTML = "Invalid date";
	}
}

function checkDateOfBirthDo(checkdate) {

	var EnteredDate = $("#dateOfBirth").val(); // For JQuery

	if (EnteredDate != "" || EnteredDate != '') {
		dateOfBirthup = "1";
		console.log(checkdate + ' if checkdate ');
	} else {
		dateOfBirthup = "0";
	}
}

function checkJoiningDateFO(checkdate, errorMsg) {

	var EnteredDate = new Date(checkdate.value); // For JQuery
	var joiningMsg = document.getElementById(errorMsg);
	console.error('EnteredDate//' + EnteredDate + '//e')
	if (EnteredDate != "Invalid Date" || EnteredDate != 'Invalid Date') {
		foJoiningDateGetUp = "1";
		foJoiningDateGet = "1";
		joiningMsg.innerHTML = "";
		document.getElementById('sjoiningDate').style.borderColor = "#ccd0d4";
		// FOformUpdate()
	} else {
		foJoiningDateGetUp = "0";
		foJoiningDateGet = "0";

		document.getElementById('sjoiningDate').style.borderColor = "red";
		joiningMsg.innerHTML = "Invalid Date";
		// FOformUpdate()
	}
}

function checkJoiningDateND(checkdate, errorMsg) {

	var joiningMsg = document.getElementById(errorMsg);
	var EnteredDate = new Date(checkdate.value);

	console.log('//' + EnteredDate + '//')

	if (EnteredDate != "Invalid Date" || EnteredDate != 'Invalid Date') {
		ndJoiningDateGet = "1";
		ndJoiningDateUpdate = "1";

		console.error('1 EnteredDate' + EnteredDate + ' ndJoiningDateUpdate '
				+ ndJoiningDateUpdate)

		joiningMsg.innerHTML = "";
		document.getElementById('joiningDate').style.borderColor = "#ccd0d4";
	} else {
		ndJoiningDateGet = "0";
		ndJoiningDateUpdate = "0";

		console.error('2 EnteredDate' + EnteredDate + ' ndJoiningDateUpdate '
				+ ndJoiningDateUpdate)

		document.getElementById('joiningDate').style.borderColor = "red";
		joiningMsg.innerHTML = "Invalid Date";
	}
}

function checkTehsil(checkdate) {
	var selected = $('[name="foCity"]').val(); // For JQuery
	if (selected != "" || selected != '') {
		foTehsil = "1";
		console.log(selected + ' if tehsil,,, ');
	} else {
		foTehsil = "0";
		console.log(selected + 'else tehsil,,, ');
	}

}

function checkTehsilND(checkdate) {
	var selected = $('[name="ndCity"]').val(); // For JQuery
	if (selected != "" || selected != '') {
		ndTehsilGet = "1";
		console.log(selected + ' if tehsil,,, ');
	} else {
		ndTehsilGet = "0";
		console.log(selected + 'else tehsil,,, ');
	}

}

/*
 * function checkTehsilFOUpdate(checkdate) { var selected =
 * $('[name="foCity"]').val(); // For JQuery if (selected != "" || selected !=
 * '') { foTehsil = "1"; console.log(selected + ' if tehsil,,, '); } else {
 * foTehsil = "0"; console.log(selected + 'else tehsil,,, '); }
 *  }
 */

function selectFO(checkdate) {
	var selected = $('[name="foList"]').val(); // For JQuery

	if (selected != "" || selected != '') {
		ndFoGet = "1";
		console.log(selected + ' if fo,,, ');
	} else {
		ndFoGet = "0";
		console.log(selected + 'else fo,,, ');
	}

}

function selectDOEducation() {
	var selected = $('#education').val(); // For JQuery
	console.log(selected + 'selected')

	if (selected != null) {
		DoEducation = "1";
		updateDoEducation = "1";
		console.log(selected + ' if DoEducation ');
	} else {
		DoEducation = "0";
		updateDoEducation = "0";
		console.log(selected + 'else DoEducation ');
	}
}

function selectDODistrict(ss) {
	var selected = $('#district').val(); // For JQuery
	console.log(selected + 'selected');

	if (selected != null) {
		doDistrict = "1";
		doDistrictup = "1";
		console.log(selected + ' if district ');
	} else {
		doDistrictup = "0";
		doDistrict = "0";
		console.log(selected + 'else district ');
	}
}

function selectFieldOfficer() {
	var selected = $('#foList').val(); // For JQuery
	console.log(selected + 'selected');

	if (selected != null) {
		ndFoGet = "1";
		console.log(selected + ' if ndFoGet ');
	} else {
		ndFoGet = "0";
		console.log(selected + 'else ndFoGet ');
	}
}
