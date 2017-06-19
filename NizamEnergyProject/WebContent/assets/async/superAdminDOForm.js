
$(function() {
	
	$('#submit').click(function() {

		if ($('[data-do_form]').data('do_form') != "true") {
			
			var submitDO = $('[name = "do_form_submit"]').val();
			var updateDO = $('[name = "do_form_update"]').val();
//			
			if (submitDO != null) {
				var name = $('[name = "firstname"]').val();
				var cnic = $('[name = "cnic"]').val();
				var dateOfBirth = $('[name = "dateOfBirth"]').val();
				var gender = $('[name = "gender"]').val();
				var maritalStatus = $('[name = "do_marital_status"]').val();
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
//				
//				console.log('submitDO ))))))))) ' + submitDO);
//				console.log('name - ' + name + ' cnic - ' + cnic + ' dateOfbirth - ' + dateOfBirth + ' gender - '
//						+ gender + ' maritalStatus - ' + maritalStatus + ' dateOfJoining - ' + dateOfJoining + ' primaryPhone - '
//						+ primaryPhone + ' secondaryPhone - ' + secondaryPhone + ' refNumber1 - '
//						+ refNumber1 + ' refNumber2 - ' + refNumber2 + ' email - ' + email + ' address - ' + address + ' district - '
//						+ district + ' education - ' + education + ' salary - ' + salary);
				console.log("in js");
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
						salary : salary
					},
					success : function(data) {
						console.log('action success');
						console.log("out js");
						$('#do_form_submit').prop('disabled',true);
					}
				});
			}
			
			
			if (updateDO != null) {
				var name = $('[name = "firstname"]').val();
				var cnic = $('[name = "cnic"]').val();
				var dateOfBirth = $('[name = "dateOfBirth"]').val();
				var gender = $('[name = "gender"]').val();
				var maritalStatus = $('[name = "do_marital_status"]').val();
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
				var doid = $('[name = "doid"]').val();
//				
//				console.log('submitDO ))))))))) ' + submitDO);
//				console.log('name - ' + name + ' cnic - ' + cnic + ' dateOfbirth - ' + dateOfBirth + ' gender - '
//						+ gender + ' maritalStatus - ' + maritalStatus + ' dateOfJoining - ' + dateOfJoining + ' primaryPhone - '
//						+ primaryPhone + ' secondaryPhone - ' + secondaryPhone + ' refNumber1 - '
//						+ refNumber1 + ' refNumber2 - ' + refNumber2 + ' email - ' + email + ' address - ' + address + ' district - '
//						+ district + ' education - ' + education + ' salary - ' + salary);
				console.log("in js");
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
						doid:doid
					},
					success : function(data) {
						console.log('action success');
						console.log("out js");
						$('#do_form_update').prop('disabled',true);
					}
				});
			}
			
			
			
		}
	});
	
	
	
	
	$('#foSubmit')
	   .click(
	     function() {

	      var foName = $('[name = "sfirstname"]').val();
	      var foCnic = $('[name = "scnic"]').val();
	      var foDateOfBirth = $('[name = "sdateOfBirth"]').val();
	      var foGender = $('[name = "foGender"]:checked').val();
	      var foMaritalStatus = $(
	        '[name = "fo_marital_status"]:checked').val();
	      var foDateOfJoining = $('[name = "sjoiningDate"]')
	        .val();
	      var foEducation = $('[name = "seducation"]').val();
	      var foPrimaryPhone = $('[name = "foPrimaryPhone"]')
	        .val();
	      var foSecondaryNumber = $(
	        '[name = "foSecondaryNumber"]').val();
	      var foAccNumber = $('[name = "foAccountNo"]').val();
	      var foEmail = $('[name = "foEmail"]').val();
	      var foAddress = $('[name = "foAddress"]').val();
	      var foCity = $('[name = "foCity"]').val();
	      var foSalary = $('[name = "foSalary"]').val();
	      var percellcomm = $('[name = "percellcomm"]').val();
	      var doid=$('[name= "sdoId"]').val();

	      console.log('name - ' + foName + ' cnic - ' + foCnic
	        + ' dateOfbirth - ' + foDateOfBirth
	        + ' gender - ' + foGender + ' maritalStatus - '
	        + foMaritalStatus + ' dateOfJoining - '
	        + foDateOfJoining + ' primaryPhone - '
	        + foPrimaryPhone + ' secondaryPhone - '
	        + foSecondaryNumber + ' acc no - '
	        + foAccNumber + ' email - ' + foEmail
	        + ' address - ' + foAddress + ' city - '
	        + foCity + ' education - ' + foEducation
	        + ' salary - ' + foSalary + ' commission - '
	        + percellcomm+' doid'+ doid );

	      if (foName != '' && foDateOfBirth != '' && foCnic != ''
	        && foDateOfJoining != ''
	        && foPrimaryPhone != '' && foAddress != ''
	        && foCity != null && foSalary != ''
	        && percellcomm != '') {

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
	        success : function(data) {
	         console.log('action success');
	        }
	       });

	      } else {
	       console.log('fill the fields...')
	      }
	     });
});