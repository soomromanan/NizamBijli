function insertDo() {
	var name = $('[name = "firstname"]').val();
	var cnic = $('[name = "cnic"]').val();
	var dateOfBirth = $('[name = "dateOfBirth"]').val();
	var gender = $('[name = "gender"]:checked').val();
	var maritalStatus = $('[name = "do_marital_status"]:checked').val();
	var dateOfJoining = $('[name = "joiningDate"]').val();
	var primaryPhone = $('[name = "phone1"]').val();
	var secondaryPhone = $('[name = "phone2"]').val();
	var refNumber1 = $('[name = "refNumber1"]').val();
	var refNumber2 = $('[name = "refNumber2"]').val();
	var email = $('[name = "email"]').val();
	var address = $('[name = "address"]').val();
	var district = $('[name = "district"]').val();
	var education = $('[name = "education"]').val();
	var salary = $('[name = "salary"]').val();
	var userType = $('[name = "userType"]').val();
	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'addDistrictOfficer',
			name : name,
			cnic : cnic,
			dateOfBirth : dateOfBirth,
			gender : gender,
			marritalStatus : maritalStatus,
			joiningDate : dateOfJoining,
			primaryPhone : primaryPhone,
			secondaryPhone : secondaryPhone,
			refNumber1 : refNumber1,
			refNumber2 : refNumber2,
			email : email,
			address : address,
			district : district,
			education : education,
			salary : salary,
			userType : userType
		},
		success : function(json) {
			if (json.id != "0") {
				$('#do_added').text("Successfully Distric Officer Added")
				$('#submit').prop('disabled', true)
			} else {
				$('#do_added').text("<b>Distric Officer Not Added</b>")
				$('#submit').prop('disabled', true)
			}
		}
	});
}

function updateDo() {
	console.log('updateDo')
	var name = $('[name = "firstname"]').val();
	var cnic = $('[name = "cnic"]').val();
	var dateOfBirth = $('[name = "dateOfBirth"]').val();
	var gender = $('[name = "gender"]:checked').val();
	var maritalStatus = $('[name = "do_marital_status"]:checked').val();
	var dateOfJoining = $('[name = "joiningDate"]').val();
	var primaryPhone = $('[name = "phone1"]').val();
	console.log('primary>>>'+primaryPhone)
	var secondaryPhone = $('[name = "phone2"]').val();
	var refNumber1 = $('[name = "refNumber1"]').val();
	var refNumber2 = $('[name = "refNumber2"]').val();
	var email = $('[name = "email"]').val();
	var address = $('[name = "address"]').val();
	var district = $('[name = "district"]').val();
	var education = $('[name = "education"]').val();
	var salary = $('[name = "salary"]').val();
	var doid = $('[name = "doid"]').val();

	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'updateDistrictOfficer',
			name : name,
			cnic : cnic,
			dateOfBirth : dateOfBirth,
			gender : gender,
			marritalStatus : maritalStatus,
			joiningDate : dateOfJoining,
			primaryPhone : primaryPhone,
			secondaryPhone : secondaryPhone,
			refNumber1 : refNumber1,
			refNumber2 : refNumber2,
			email : email,
			address : address,
			district : district,
			education : education,
			salary : salary,
			doid : doid
		},
		success : function(data) {
			$('#do_added').text("Successfully Distric Officer Updated")
			$('#submit').prop('disabled', true)
		}
	});
}

function insertFo() {
	console.log('insertFo')
	var foName = $('[name = "sfirstname"]').val();
	var foCnic = $('[name = "sdoCnic"]').val();
	var foDateOfBirth = $('[name = "sdateOfBirth"]').val();
	var foGender = $('[name = "foGender"]:checked').val();
	var foMaritalStatus = $('[name = "fo_marital_status"]:checked').val();
	var foDateOfJoining = $('[name = "sjoiningDate"]').val();
	var foEducation = $('[name = "seducation"]').val();
	var foPrimaryPhone = $('[name = "foPrimaryPhone"]').val();
	var foSecondaryNumber = $('[name = "foSecondaryNumber"]').val();
	var foAccNumber = $('[name = "foAccountNo"]').val();
	var foEmail = $('[name = "foEmail"]').val();
	var foAddress = $('[name = "foAddress"]').val();
	var foCity = $('[name = "foCity"]').val();
	var foSalary = $('[name = "foSalary"]').val();
	var percellcomm = $('[name = "percellcomm"]').val();
	var doid = $('[name= "sdoId"]').val();

	console.error('#foSubmit ### 123');
	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'insertFieldOfficer',
			foName : foName,
			foCnic : foCnic,
			foDateOfBirth : foDateOfBirth,
			foGender : foGender,
			foMaritalStatus : foMaritalStatus,
			foDateOfJoining : foDateOfJoining,
			foEducation : foEducation,
			foPrimaryPhone : foPrimaryPhone,
			foSecondaryNumber : foSecondaryNumber,
			foAccNo : foAccNumber,
			foEmail : foEmail,
			foAddress : foAddress,
			foCity : foCity,
			foSalary : foSalary,
			percellcomm : percellcomm,
			doid : doid
		},
		success : function(json) {
			if (json.id != "0") {
				$('#fo_added').text("Successfully Field Officer Added")
				$('#foSubmit').prop('disabled', true)
			} else {
				$('#fo_added').text("<b>Field Officer Not Added</b>")
				$('#foSubmit').prop('disabled', true)
			}
		}
	});

}

function updateFo() {
	console.log('updateFo')
	var foName = $('[name = "sfirstname"]').val();
	var foCnic = $('[name = "sdoCnic"]').val();
	var foDateOfBirth = $('[name = "sdateOfBirth"]').val();
	var foGender = $('[name = "foGender"]:checked').val();
	var foMaritalStatus = $('[name = "fo_marital_status"]:checked').val();
	var foDateOfJoining = $('[name = "sjoiningDate"]').val();
	var foEducation = $('[name = "seducation"]').val();
	var foPrimaryPhone = $('[name = "foPrimaryPhone"]').val();
	var foSecondaryNumber = $('[name = "foSecondaryNumber"]').val();
	var foAccNumber = $('[name = "foAccountNo"]').val();
	var foEmail = $('[name = "foEmail"]').val();
	var foAddress = $('[name = "foAddress"]').val();
	var foSalary = $('[name = "foSalary"]').val();
	var percellcomm = $('[name = "percellcomm"]').val();
	var foid = $('[name= "foid"]').val();

	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'updateFieldOfficer',
			foName : foName,
			foCnic : foCnic,
			foDateOfBirth : foDateOfBirth,
			foGender : foGender,
			foMaritalStatus : foMaritalStatus,
			foDateOfJoining : foDateOfJoining,
			foEducation : foEducation,
			foPrimaryPhone : foPrimaryPhone,
			foSecondaryNumber : foSecondaryNumber,
			foAccNo : foAccNumber,
			foEmail : foEmail,
			foAddress : foAddress,
			foSalary : foSalary,
			percellcomm : percellcomm,
			foid : foid
		},
		success : function(data) {
			$('#fo_added').text("Successfully Field Officer Updated")
			$('#foSubmit').prop('disabled', true)
		}
	});

}

function insertNd() {
	console.log('insertNd')
	var ndName = $('[name = "ndFullName"]').val();
	var ndDateOfBirth = $('[name = "ndDateOfBirth"]').val();
	var ndCnic = $('[name = "ndCnic"]').val();
	var ndGender = $('[name = "ndGender"]:checked').val();
	var ndMaritalStatus = $('[name = "nd_marital_status"]:checked').val();
	var ndDateOfJoining = $('[name = "joiningDate"]').val();
	var ndEducation = $('[name = "ndEducation"]').val();
	var ndPrimaryPhone = $('[name = "ndPrimaryPhone"]').val();
	var ndSecondaryPhone = $('[name = "ndSecondaryPhone"]').val();
	var ndAccNumber = $('[name = "ndAccNumber"]').val();
	var ndEmail = $('[name = "ndEmail"]').val();
	var ndAddress = $('[name = "ndAddress"]').val();
	var foList = $('[name = "foList"]').val();
	var ndCity = $('[name = "ndCity"]').val();
	var nddistrict = $('[name = "sdistrict"]').val();
	var ndSalary = $('[name = "ndSalary"]').val();

	var ndPerSellCommission = $('[name = "ndPerSellCommission"]').val();
	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'insertSalesman',
			ndName : ndName,
			ndDateOfBirth : ndDateOfBirth,
			ndCnic : ndCnic,
			ndGender : ndGender,
			ndMaritalStatus : ndMaritalStatus,
			ndDateOfJoining : ndDateOfJoining,
			ndEducation : ndEducation,
			ndPrimaryPhone : ndPrimaryPhone,
			ndSecondaryPhone : ndSecondaryPhone,
			ndAccNumber : ndAccNumber,
			ndEmail : ndEmail,
			ndAddress : ndAddress,
			foList : foList,
			ndCity : ndCity,
			ndSalary : ndSalary,
			ndPerSellCommission : ndPerSellCommission,
			ndDistrict : nddistrict
		},
		success : function(json) {
			if (json.id != "0") {
				$('#nd_added').text("Successfully NizamDost Added")
				$('#ndSubmit').prop('disabled', true)
			} else {
				$('#nd_added').text("NizamDost Not Added")
				$('#ndSubmit').prop('disabled', true)
			}
		}
	});

}

function updateNd() {
	var ndid = $('[name = "ndid"]').val();
	var ndName = $('[name = "ndFullName"]').val();
	var ndDateOfBirth = $('[name = "ndDateOfBirth"]').val();
	var ndCnic = $('[name = "ndCnic"]').val();
	var ndGender = $('[name = "ndGender"]:checked').val();
	var ndMaritalStatus = $('[name = "nd_marital_status"]:checked').val();
	var ndDateOfJoining = $('[name = "joiningDate"]').val();
	var ndEducation = $('[name = "ndEducation"]').val();
	var ndPrimaryPhone = $('[name = "ndPrimaryPhone"]').val();
	var ndSecondaryPhone = $('[name = "ndSecondaryPhone"]').val();
	var ndAccNumber = $('[name = "ndAccNumber"]').val();
	var ndEmail = $('[name = "ndEmail"]').val();
	var ndAddress = $('[name = "ndAddress"]').val();
	var ndSalary = $('[name = "ndSalary"]').val();

	var ndPerSellCommission = $('[name = "ndPerSellCommission"]').val();
	$.ajax({
		url : 'UserFormController',
		method : 'POST',
		datType : 'json',
		data : {
			action : 'updateSalesman',
			ndId : ndid,
			ndName : ndName,
			ndDateOfBirth : ndDateOfBirth,
			ndCnic : ndCnic,
			ndGender : ndGender,
			ndMaritalStatus : ndMaritalStatus,
			ndDateOfJoining : ndDateOfJoining,
			ndEducation : ndEducation,
			ndPrimaryPhone : ndPrimaryPhone,
			ndSecondaryPhone : ndSecondaryPhone,
			ndAccNumber : ndAccNumber,
			ndEmail : ndEmail,
			ndAddress : ndAddress,

			ndSalary : ndSalary,
			ndPerSellCommission : ndPerSellCommission
		},
		success : function(data) {
			$('#nd_added').text("Successfully NizamDost Updated")
			$('#ndSubmit').prop('disabled', true)
		}
	});

}