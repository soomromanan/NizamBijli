var customerId
var eligibilityId
var alert = '<div class="alert fade in m-b-15 ">'
		+ '<span class="close" data-dismiss="alert">&times;</span>'
		+ '<strong class="inline"></strong>' + '<p class="text-justify"></p>'
		+ '</div>'
$(function() {
	customerId = $('[data-customerid]').data('customerid');
	applianceId = $('[data-id]').data('id');
	var customerincomesourceid
	if (customerId) {
		getCustomerFormWizard()
	}
	$('#add_appliance')
			.click(
					function() {
						if ($('[data-applianceids]').data('applianceids') != "true") {
							$("#add_appliance").prop("disabled", true);
							var data = new Object();
							vleId: $('[name="customer_vle_guarantor"]').val()
							data.appliance = []
							var isAppliance = $(
									'[name="customer_appliance_name"]')
									.toArray();
							$.each(isAppliance, function(e) {
								data.appliance[e] = isAppliance[e].value
							})
							data.saleType = []
							var isSaleType = $('[name="sale_type"]').toArray();
							$.each(isSaleType, function(e) {
								data.saleType[e] = isSaleType[e].value
							})
							data.schemes = []
							var isScheme = $(
									'[name="customer_appliance_scheme"]')
									.toArray()
							var isScheme2 = $(
									'[name="customer_appliance_scheme_other"]')
									.toArray()
							$.each(isScheme,
											function(e) {
												if (parseInt(isScheme[e].value) == 1) {
													data.schemes[e] = parseInt(isScheme2[e].value)
													console.log('IF '
															+ data.schemes[e]);
												} else if (!isNaN(parseInt(isScheme[e].value))) {
													data.schemes[e] = parseInt(isScheme[e].value)
													console.log('else '
															+ data.schemes[e]);
												}
											})

							data.advance = []
							var isAdvance = $('[name="customer_advance_payment"]').toArray();
							$.each(isAdvance, function(e) {
								data.advance[e] = parseInt(isAdvance[e].value)
							})

							data.monthly = []
							var isMonthly = $(
									'[name="customer_monthly_payment"]')
									.toArray();
							$.each(isMonthly, function(e) {
								data.monthly[e] = parseInt(isMonthly[e].value)
							})

							data.total = []
							var isTotal = $('[name="customer_total_payment"]')
									.toArray();
							$.each(isTotal, function(e) {
								data.total[e] = parseInt(isTotal[e].value)
							})

							if ($('[name="customer_appliance_name"]').val != null) {
								if (customerId != "" && eligibilityId != "") {
									$
											.ajax({
												url : 'CustomerFormController',
												method : 'POST',
												dataType : 'json',
												data : {
													action : 'insertAppliance',
													data : data,
													customerId : customerId,
													vleId : $('[name="customer_vle_guarantor"]').val()
												},
												success : function(json) {
													$('#appliance_added_status').append(alert)
													$(alert).alert()
													if (json.status == "ok"
															&& json.data != "0") {
														$(
																'#appliance_added_status .alert')
																.addClass(
																		'alert-success')
														$(
																'#appliance_added_status .alert strong')
																.text(
																		"Successfully Cutomer Added")
														$(
																'#appliance_added_status .alert p')
																.text(
																		"New Appliance Id is : "
																				+ json.data)
														$('[data-applianceids]')
																.data(
																		'applianceids',
																		json.data)
														$('#add_appliance')
																.prop(
																		'disabled',
																		true)
														
													} else {
														
														$(
																'#appliance_added_status .alert')
																.addClass(
																		'alert-danger')
														$(
																'#appliance_added_status .alert strong')
																.text("Error")
														$("#add_appliance")
																.prop(
																		"disabled",
																		false)
														if (json.message == undefined) {
															$(
																	'#appliance_added_status .alert p')
																	.text("");
														}
														$(
																'#appliance_added_status .alert p')
																.text(
																		"Please add appliance(s)")
														
													}
												}
											})
								} else {
									// console.log("Insert Customer and E")
								}
							}
						}
					})

	$('#customer_income_source_detail').on('submit', function(event) {
		insertCustomerIncomeSourceDetail()
		event.preventDefault();
	})

	$('#customer_business_detail').on('submit', function(event) {
		insertCustomerBusinessDetail()
		event.preventDefault();
	})

	$('#customer_employement_detail').on('submit', function(event) {
		insertCustomerEmployeeDetail()
		event.preventDefault()
	})

	$('#wizard').bwizard({
						nextBtnText : 'Save and Next',
						validating : function(e, ui) {
							if (ui.index > ui.nextIndex) {
								return true
							} else {

								switch (ui.index) {
								case 0:
									if ($('[data-customerid]').data('customerid') == null || 
										$('[data-customerid]').data('customerid') == "0") {
										console.log("insert Customer Info")
										return insertCustomerInfo();
									} else {
										console.log("Already Saved")
										console.log("Customer goes to Update")
										return insertCustomerInfo();
									}
									break;
								case 1:
									$('#customer_income_source_detail').submit()
									if (!$('#customer_business_detail :input').prop('disabled')) {
										$('#customer_business_detail').submit()
									}
									if (!$(
											'#customer_employement_detail :input').prop('disabled')) {
										$('#customer_employement_detail').submit()
									}

									break;
								case 2:
									insertCustomerAssetsIncomeSource();
									insertCustomerExpense();
									insertMonthlyExpense();
									insertAssets();
									insertLoan();
									break;
								case 3:
									insertFamilyGuarantorDetail()
									insertOutsideGuarantorDetail()
									break;
								case 4:
									break;
								}
							}
						}
					})

})

function insertCustomerInfo() {
	console.log('insertCustomerInfo, log for id'+customerId)
	var bool = true;
	$.each($('#select_customer_city, #select_customer_vle'), function() {
		if ($(this).val() === "") {
			console.log('select_customer_city')
			$(this).selectpicker('setStyle', 'btn-danger', 'add')
			$(this).selectpicker('setStyle', 'btn-primary', 'remove')
			bool = false;
		} else {
			$(this).selectpicker('setStyle', 'btn-danger', 'remove')
			$(this).selectpicker('setStyle', 'btn-primary', 'add')
		}
	})

	$.each($('#add_info input[type="date"]:required'), function(e) {
		if ($(this).val() === "") {
			console.log('add_info')
			$(this).parents('.has-feedback').addClass('has-error')
			$(this).parents('.has-feedback').removeClass('has-success')
			bool = false;
		} else {
			$(this).parents('.has-feedback').addClass('has-success')
			$(this).parents('.has-feedback').removeClass('has-error')
		}
	})
	$.each($('#customer_info_form input[type="text"]:required'), function(e) {
		if ($(this).val() === "") {
			console.log('customer_info_form')
			$(this).parents('.has-feedback').addClass('has-error')
			$(this).parents('.has-feedback').removeClass('has-success')
			bool = false;
		} else {
			$(this).parents('.has-feedback').addClass('has-success')
			$(this).parents('.has-feedback').removeClass('has-error')
		}
	})
	$.each($('#customer_info_form input.phone:required'), function(e) {
		if ($(this).val() === "") {
			console.log('customer_info_form')
			$(this).parents('.has-feedback').addClass('has-error')
			$(this).parents('.has-feedback').removeClass('has-success')
			bool = false;
		} else {
			$(this).parents('.has-feedback').addClass('has-success')
			$(this).parents('.has-feedback').removeClass('has-error')
		}
	})
	if (!bool) {
		return false;
	} else {
		var city = $('#select_customer_city').val()
		var province = $('[name="customer_provinces"]:checked').val();
		var address = $('[name="customer_address"]').val()
		var customer_union_council = $('[name="customer_union_council"]').val()
		var vle = $('[name="customer_vle_guarantor"]').val()
		var requestedDate = $('[name="customer_requested_date"]').val()
		var education_medical_amount = $(
				'[name="customer_education_medical_amount"]').val();
		customer_sys_installed_place = $(
				'[name="customer_sys_installed_place"]:checked').val();
		customer_installment_address = $(
				'[name="customer_installment_address"]').val()

		var fullname = $('[name="customer_fullname"]').val()
		var fatherName = $('[name="customer_father_name"]').val()
		var gender = $('[name="customer_gender"]:checked').val()
		var date = new Date(dateOfBirth)
		dateOfBirth = date.getFullYear() + "-" + (date.getMonth() + 1) + "-"
				+ date.getDate()
		var cnic = $('[name="customer_cnic"]').val()
		var dateOfBirth = $('[name="customer_date_of_birth"]').val()
		var customerCaste = $('[name="customer_caste"]').val()
		var primaryPhone = $('[name="customer_primary_phone"]').val()
		var secondaryPhone = $('[name="customer_secondary_phone"]').val()
		var maritalStatus = $('[name="customer_marital_status"]:checked').val()
		// var email = $('[name="customer_email"]').val()
		var education = $('[name="customer_education"]:checked').val()
		// var family = $('[name="customer_family_size"]').val()

		var customer_disibility = $('[name="customer_disibility"]:checked')
				.val()
		var customer_training_skill = $(
				'[name="customer_training_skill"]:checked').val()
		var customer_course_name = $('[name="customer_course_name"]').val()
		var customer_head_faimly = $('[name="customer_head_faimly"]:checked')
				.val()
		var customer_total_household_members = $(
				'[name="customer_total_household_members"]:checked').val()
		var customer_dependants_members = $(
				'[name="customer_dependants_members"]:checked').val()
		var customer_children = $('[name="number_of_children"]:checked').val()
		// var customer_education_amount =
		// $('[name="customer_education_amount"]').val()
		var customer_education_amount = isNaN(parseInt($(
				'[name="customer_education_amount"]').val())) ? 0 : parseInt($(
				'[name="customer_education_amount"]').val())
		var customer_total_adult_members = $(
				'[name="customer_total_adult_members"]:checked').val()
		var customer_medical_recuring = $(
				'[name="customer_medical_recuring"]:checked').val()
		// var customer_medical_recuring_amount =
		// $('[name="customer_medical_recuring_amount"]').val()
		var customer_medical_recuring_amount = isNaN(parseInt($(
				'[name="customer_medical_recuring_amount"]').val())) ? 0
				: parseInt($('[name="customer_medical_recuring_amount"]').val())
		var customer_families_type = $(
				'[name = "customer_families_type"]:checked').val()
		var customer_families_household = $(
				'[name="customer_families_household"]:checked').val()
		var customer_roomoccupied_household = $(
				'[name="customer_roomoccupied_household"]:checked').val()
		var customer_residence = $('[name="customer_residence"]:checked').val()
		var customer_other_residence = $('[name="customer_other_residence"]')
				.val()
		var customer_residence_period = $(
				'[name="customer_residence_period"]:checked').val()

		var data = new Object();
		data.who_is_other = []
		var isOther = $('[name="who_is_other"]').toArray()
		$.each(isOther, function(e) {
			data.who_is_other[e] = (isOther[e].value)
		})

		data.relation_with_customer = []
		var isRelation = $('[name="relation_with_customer"]').toArray()
		$.each(isRelation, function(e) {
			data.relation_with_customer[e] = (isRelation[e].value)
		})

		data.customer_other_phone = []
		var isNumber = $('[name="customer_other_phone"]').toArray()
		$.each(isNumber, function(e) {
			data.customer_other_phone[e] = (isNumber[e].value)
		})

		data.general_field_in_text_foramte_type = []
		var isTextFieldType = $('[name="general_field_in_text_foramte_type"]')
				.toArray()
		$.each(isTextFieldType,
						function(e) {
							data.general_field_in_text_foramte_type[e] = isTextFieldType[e].value
						})

		data.general_field_in_text_foramte = []
		var isTextField = $('[name="general_field_in_text_foramte"]').toArray()
		$.each(isTextField, function(e) {
			data.general_field_in_text_foramte[e] = isTextField[e].value
		})

		data.general_field_in_digit_foramte_type = []
		var isDigitFieldType = $('[name="general_field_in_digit_foramte_type"]')
				.toArray()
		$.each(isDigitFieldType,
						function(e) {
							data.general_field_in_digit_foramte_type[e] = isDigitFieldType[e].value
						})
						
		data.general_field_in_digit_foramte = []
		var isDigitField = $('[name="general_field_in_digit_foramte"]').toArray()
				$.each(isDigitField,
						function(e) {
							data.general_field_in_digit_foramte[e] = parseInt(isDigitField[e].value)
						})
				
				$.ajax({
					url : 'CustomerFormController',
					method : 'post',
					dataType : 'json',
					async : false,
					data : {
						data : data,
						applianceId : applianceId == null ? 0 : applianceId,
						customerId : customerId == null ? 0 : customerId, 
						user_form_status : customerId == null ? 0 : 1,
						action : "insertCustomerInfo",
						city : city,
						province : province,
						address : address,
						customer_union_council : customer_union_council,
						requesteddate : requestedDate,
						education_medical_amount : education_medical_amount,
						vle : vle,
						customer_sys_installed_place : customer_sys_installed_place,
						customer_installment_address : customer_installment_address,
						fullname : fullname,
						fatherName : fatherName,
						gender : gender,
						dateOfBirth : dateOfBirth,
						customerCaste : customerCaste,
						cnic : cnic,
						primaryPhone : primaryPhone,
						secondaryPhone : secondaryPhone,
						maritalStatus : maritalStatus,
						education : education,
						customer_disibility : customer_disibility,
						customer_training_skill : customer_training_skill,
						customer_course_name : customer_course_name,
						customer_head_faimly : customer_head_faimly,
						customer_total_household_members : customer_total_household_members,
						customer_dependants_members : customer_dependants_members,
						customer_children : customer_children,
						customer_education_amount : customer_education_amount,
						customer_total_adult_members : customer_total_adult_members,
						customer_medical_recuring : customer_medical_recuring,
						customer_medical_recuring_amount : customer_medical_recuring_amount,
						customer_families_type : customer_families_type,
						customer_families_household : customer_families_household,
						customer_roomoccupied_household : customer_roomoccupied_household,
						customer_residence : customer_residence,
						customer_other_residence : customer_other_residence,
						customer_residence_period : customer_residence_period
					// ,general_field_in_text_foramte :
					// general_field_in_text_foramte
					// ,general_field_in_digit_foramte :
					// general_field_in_digit_foramte

					},
					success : function(json) {
						showAlert('#wizard_1_status', json,
								"Customer Added, ID")
						console.log('Customer info data ' + json.data)
						if (json.status == "ok" && parseInt(json.data) > 0) {
							customerId = json.data
							$('[data-customerid]')
									.data('customerid', json.data)
							$('[data-eligibilityid]').data('eligibilityid',
									json.eligibilityId)
							return true;
						} else {
							return false;
						}

					}
				})
	}

}

function insertCustomerExpense() {
	console.log('insertCustomerExpenseIncomeSource');
	// if ($('[data-customer-monthly-multiple_expense]').data(
	// 'customer-monthly-multiple_expense') != "true") {
	var data = new Object();
	data.customerId = customerId

	customer_total_expense_income = isNaN(parseInt($(
			'[name="customer_total_expense_income"]').val())) ? 0 : parseInt($(
			'[name="customer_total_expense_income"]').val())

	customer_Connectivity = $('[name="customer_Connectivity"]:checked').val()
	customer_sourceof_electricity = $(
			'[name="customer_sourceof_electricity"]:checked').val()
	customer_primary_sourse_amount = isNaN(parseInt($(
			'[name="customer_primary_sourse_amount"]').val())) ? 0
			: parseInt($('[name="customer_primary_sourse_amount"]').val())
	customer_mobile_type = $('[name="customer_mobile_type"]:checked').val()
	customer_mobilebills_amount = isNaN(parseInt($(
			'[name="customer_mobilebills_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_mobilebills_amount"]').val())
	mobilink_network = isNaN(parseInt($('[name="mobilink_network"]:checked')
			.val())) ? 0 : parseInt($('[name="mobilink_network"]:checked')
			.val())
	ufone_network = isNaN(parseInt($('[name="ufone_network"]:checked').val())) ? 0
			: parseInt($('[name="ufone_network"]:checked').val())
	zong_network = isNaN(parseInt($('[name="zong_network"]:checked').val())) ? 0
			: parseInt($('[name="zong_network"]:checked').val())
	warid_network = isNaN(parseInt($('[name="warid_network"]:checked').val())) ? 0
			: parseInt($('[name="warid_network"]:checked').val())
	telenor_network = isNaN(parseInt($('[name="telenor_network"]:checked')
			.val())) ? 0
			: parseInt($('[name="telenor_network"]:checked').val())
	customer_transport_mode = $('[name="customer_transport_mode"]:checked')
			.val()
	customer_transport_amount = isNaN(parseInt($(
			'[name="customer_transport_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_transport_amount"]').val())
	customer_karyana = $('[name="customer_karyana"]:checked').val()
	customer_karyana_amount = isNaN(parseInt($(
			'[name="customer_karyana_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_karyana_amount"]').val())

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'JSON',
		data : {
			action : 'insertCustomerExpense',
			customerId : customerId,
			customer_total_expense_income : customer_total_expense_income,
			customer_Connectivity : customer_Connectivity,
			customer_sourceof_electricity : customer_sourceof_electricity,
			customer_primary_sourse_amount : customer_primary_sourse_amount,
			customer_mobile_type : customer_mobile_type,
			customer_mobilebills_amount : customer_mobilebills_amount,
			mobilink_network : mobilink_network,
			ufone_network : ufone_network,
			zong_network : zong_network,
			warid_network : warid_network,
			telenor_network : telenor_network,
			customer_transport_mode : customer_transport_mode,
			customer_transport_amount : customer_transport_amount,
			customer_karyana : customer_karyana,
			customer_karyana_amount : customer_karyana_amount,
		},
		success : function(json) {
			showAlert('#wizard_3_status', json, "Updated : ")
			if (json.status == "ok") {
				$('[data-customer-monthly-multiple_expense]').data(
						'customer-monthly-multiple_expense', "true")
			} else if (json.status == "error") {

			}
		}
	})
	// }
}

function insertCustomerAssetsIncomeSource() {
	// if($('[data-customer-live-assets]').data('customer-live-assets') !=
	// "true"){
	var data = new Object();

	customerTotalAssetsIncome = isNaN(parseInt($(
			'[name="customer_total_assets_income"]').val())) ? 0 : parseInt($(
			'[name="customer_total_assets_income"]').val())
	customer_land = isNaN($('[name="customer_land"]:checked').val()) ? "0" : $(
			'[name="customer_land"]:checked').val()
	customer_bbuffalo = isNaN($('[name="customer_bbuffalo"]:checked').val()) ? "0"
			: $('[name="customer_bbuffalo"]:checked').val()
	customer_cow = isNaN($('[name="customer_cow"]:checked').val()) ? "0" : $(
			'[name="customer_cow"]:checked').val()
	customer_calf = isNaN($('[name="customer_calf"]:checked').val()) ? "0" : $(
			'[name="customer_calf"]:checked').val()
	customer_goat_sheep = isNaN($('[name="customer_goat_sheep"]:checked').val()) ? "0"
			: $('[name="customer_goat_sheep"]:checked').val()
	customer_bike = isNaN($('[name="customer_bike"]:checked').val()) ? "0" : $(
			'[name="customer_bike"]:checked').val()
	customer_car = isNaN($('[name="customer_car"]:checked').val()) ? "0" : $(
			'[name="customer_car"]:checked').val()
	customer_tractors = isNaN($('[name="customer_tractors"]:checked').val()) ? "0"
			: $('[name="customer_tractors"]:checked').val()
	customer_tv = isNaN($('[name="customer_tv"]:checked').val()) ? 0 : $(
			'[name="customer_tv"]:checked').val()
	customer_fridge = isNaN($('[name="customer_fridge"]:checked').val()) ? 0
			: $('[name="customer_fridge"]:checked').val()

	customer_land_amount = isNaN(parseInt($('[name="customer_land_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_land_amount"]').val())
	customer_buffalo_amount = isNaN(parseInt($(
			'[name="customer_buffalo_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_buffalo_amount"]').val())
	customer_cow_amount = isNaN(parseInt($('[name="customer_cow_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_cow_amount"]').val())
	customer_calf_amount = isNaN(parseInt($('[name="customer_calf_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_calf_amount"]').val())
	customer_goat_Sheep_amount = isNaN(parseInt($(
			'[name="customer_goat_Sheep_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_goat_Sheep_amount"]').val())
	customer_bike_amount = isNaN(parseInt($('[name="customer_bike_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_bike_amount"]').val())
	customer_car_amount = isNaN(parseInt($('[name="customer_car_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_car_amount"]').val())
	customer_tractors_amount = isNaN(parseInt($(
			'[name="customer_tractors_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_tractors_amount"]').val())
	customer_tv_amount = isNaN(parseInt($('[name="customer_tv_amount"]').val())) ? 0
			: parseInt($('[name="customer_tv_amount"]').val())
	customer_fridge_amount = isNaN(parseInt($('[name="customer_fridge_amount"]')
			.val())) ? 0 : parseInt($('[name="customer_fridge_amount"]').val())

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'JSON',
		data : {
			action : 'insertCustomerLivestockAssets',
			customerId : customerId,
			customerTotalAssetsIncome : customerTotalAssetsIncome,
			customer_land : customer_land,
			customer_bbuffalo : customer_bbuffalo,
			customer_cow : customer_cow,
			customer_calf : customer_calf,
			customer_goat_sheep : customer_goat_sheep,
			customer_bike : customer_bike,
			customer_car : customer_car,
			customer_tractors : customer_tractors,
			customer_tv : customer_tv,
			customer_fridge : customer_fridge,
			customer_land_amount : customer_land_amount,
			customer_buffalo_amount : customer_buffalo_amount,
			customer_cow_amount : customer_cow_amount,
			customer_calf_amount : customer_calf_amount,
			customer_goat_Sheep_amount : customer_goat_Sheep_amount,
			customer_bike_amount : customer_bike_amount,
			customer_car_amount : customer_car_amount,
			customer_tractors_amount : customer_tractors_amount,
			customer_tv_amount : customer_tv_amount,
			customer_fridge_amount : customer_fridge_amount
		},
		success : function(json) {
			console.log(json.data)
			showAlert('#wizard_3_status', json, "Updated : ")
			if (json.status == "ok") {
				$('[data-customer-live-assets]').data('customer-live-assets',
						"true")
			} else if (json.status == "error") {

			}
		}
	})
	// }
}

function insertCustomerIncomeSourceDetail() {
	// if($('[data-customerincomesource]').data('customerincomesource') !=
	// "true"){
	var data = new Object();
	data.customerId = customerId
	data.salaryOrPension = isNaN(parseInt($(
			'[name="customer_salary_or_pension"]').val())) ? 0 : parseInt($(
			'[name="customer_salary_or_pension"]').val())
	data.businessIncome = isNaN(parseInt($('[name="customer_business_income"]')
			.val())) ? 0 : parseInt($('[name="customer_business_income"]')
			.val())
	data.farmingIncome = isNaN(parseInt($('[name="customer_farming_income"]')
			.val())) ? 0
			: parseInt($('[name="customer_farming_income"]').val())
	data.customer_income_comtributers = $(
			'[name="customer_income_comtributers"]:checked').val()

	data.customer_total_income_name = isNaN(parseInt($(
			'[name="customer_total_income_name"]').val())) ? 0 : parseInt($(
			'[name="customer_total_income_name"]').val())
	data.customer_total_irregularly_income_name = isNaN(parseInt($(
			'[name="customer_total_irregularly_income_name"]').val())) ? 0
			: parseInt($('[name="customer_total_irregularly_income_name"]')
					.val())
	data.agricultureProfitFrequency = $(
			'[name="agriculture_profit_frequency"]:checked').val()

	data.customerLivestockIncome = isNaN(parseInt($(
			'[name="customer_livestock_income"]').val())) ? 0 : parseInt($(
			'[name="customer_livestock_income"]').val())
	data.customerLivestockType = $('[name="customer_livestock_type"]:checked')
			.val()
	data.customerLivestockOthertype = $('[name="customer_livestock_othertype"]').val();
	data.customerRemittances = isNaN(parseInt($('[name="customer_remittances"]')
			.val())) ? 0 : parseInt($('[name="customer_remittances"]').val())
	data.customerRemittancesFrequency = $(
			'[name="customer_remittances_frequency"]:checked').val()
	data.customerRemittancesRelation = $(
			'[name="customer_remittances_relationship"]').val()
	data.customer_remittances_profession = $(
			'[name="customer_remittances_profession"]').val()

	data.customer_contributers_amount = isNaN(parseInt($(
			'[name="customer_contributers_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_contributers_amount"]').val())
	data.customer_rental_profit_income = isNaN(parseInt($(
			'[name="customer_rental_profit_income"]').val())) ? 0 : parseInt($(
			'[name="customer_rental_profit_income"]').val())
	data.customer_rental_frequency = $(
			'[name="customer_rental_frequency"]:checked').val()
	data.customer_rental_from = $('[name="customer_rental_from"]').val()
	data.customer_labour_amount = isNaN(parseInt($(
			'[name="customer_labour_amount"]').val())) ? 0 : parseInt($(
			'[name="customer_labour_amount"]').val())
	data.customer_labour_type = $('[name="customer_labour_type"]:checked')
			.val()
	data.customer_labour_in_month = isNaN(parseInt($(
			'[name="customer_labour_in_month"]').val())) ? 0 : parseInt($(
			'[name="customer_labour_in_month"]').val())
	data.customer_other_labour_type = $('[name="customer_other_labour_type"]')
			.val()

	data.otherIncomeSourcesType = []
	var isTypes = $('[name="customer_other_income_source_type"]').toArray();
	$.each(isTypes, function(e) {
		data.otherIncomeSourcesType[e] = isTypes[e].value
	})

	data.otherIncomeSourcesAmount = []
	var isAmount = $('[name="customer_other_income_source_amount"]').toArray()
	$.each(isAmount, function(e) {
		data.otherIncomeSourcesAmount[e] = parseInt(isAmount[e].value)
	})

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'insertIncomeSource',
			data : data
		},
		success : function(json) {
			showAlert('#wizard_2_status', json, "Updated : ")
			if (json.status == "ok") {
				$('[data-customerincomesource]').data('customerincomesource',
						"true")
			} else if (json.status == "error") {

			}
		}
	})
	// }
}

function insertCustomerBusinessDetail() {
	// if($('[data-customer-businessid]').data('customer-businessid') == ""
	// || $('[data-customer-businessid]').data('customer-businessid') == "0"){

	var data = new Object();
	data.title = $('[name="customer_business_title"]').val();
	data.type = $('[name="customer_business_type"]').val();
	data.timePeriod = $('[name="customer_business_time_period"]').val();

	if ($('[data-customerid]').data('customerid') != null) {
		console.log("Insert Customer Business Details")

		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			async : false,
			data : {
				action : 'insertCustomerBusinessInfo',
				customerId : customerId,
				data : data
			},
			success : function(json) {
				console.log(json)
				showAlert('#wizard_2_status_bd', json,
						"Business Detail Added , ID")
				if (json.status == "ok") {
					$('[data-customer-businessid]').data('customer-businessid',
							json.data)
					console.log("Saved customer businessid id "
							+ $('[data-customer-businessid]').data(
									'customer-businessid'))
				} else {
					// return false;
				}
			}
		})
		// }
	}

}

function insertCustomerEmployeeDetail() {
	// if($('[data-customer-employeeid]').data('customer-employeeid') == ""
	// || $('[data-customer-employeeid]').data('customer-employeeid') == "0"){

	var companyTitle = $('[name="customer_employement_company_name"]').val();
	var designation = $('[name="customer_employement_designation"]').val();
	var jobPeriod = $('[name="customer_employement_job_period"]').val();
	if ($('[data-customerid]').data('customerid') != null) {
		console.log("Insert Customer Employee Details")
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			async : false,
			data : {
				action : 'insertCustomerEmployeeInfo',
				customerId : customerId,
				companyTitle : companyTitle,
				designation : designation,
				jobPeriod : jobPeriod,
			},
			success : function(json) {
				console.log(json)
				showAlert('#wizard_2_status_ed', json,
						"Employment Detail Added, ID")
				if (json.status == "ok") {
					$('[data-customer-employeeid]').data('customer-employeeid',
							json.data)
					console.log("Saved customer employeeid id : "
							+ $('[data-customer-employeeid]').data(
									'customer-employeeid'))
				} else {
					// return false
				}
			}
		})
		// }
	}
}

function insertMonthlyExpense() {
	// if($('[data-customer-monthly-expense]').data("customer-monthly-expense")
	// != "true"){
	var data = new Object();
	var types = $('[name="customer_expenses_type"]').toArray()
	data.monthlyExpenseType = []
	$.each(types, function(e) {
		data.monthlyExpenseType[e] = types[e].value
	})

	var amounts = $('[name="customer_expenses_amount"]').toArray()
	data.monthlyExpenseAmount = []
	$.each(amounts, function(e) {
		data.monthlyExpenseAmount[e] = amounts[e].value
	})
	console.log(data)

	if (data.monthlyExpenseType.length > 0) {
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'insertMonthlyExpense',
				customerId : customerId,
				data : data
			},
			success : function(json) {
				console.log(json)
				showAlert('#wizard_3_status_me', json,
						"Monthly Expense Added, ID")
				if (json.status == "ok") {
					$('[data-customer-monthly-expense]').data(
							"customer-monthly-expense", "true")
				} else if (json.status == "error") {

				}
			}
		})
	}
}

function insertLoan() {
	// if($('[data-customer-loan]').data('customer-loan') != null ||
	// $('[data-customer-loan]').data('customer-loan') != ""){
	var data = new Object()
	data.bankOrOrgName = []
	data.types = []

	var names = $('[name="customer_loan_entity_name"]').toArray();
	var i = 0;
	var j = [];
	var bool = true;
	$.each(names, function(e) {
		if (this.value != '') {
			data.bankOrOrgName[i] = this.value
			data.types[i] = $(this).data('type')
			j[i] = e;
			i++;
		}
	})

	data.totalAmount = []
	var amounts = $('[name="customer_loan_entity_amount"]').toArray()
	$.each(data.bankOrOrgName, function(e) {
		if (amounts[j[e]].value === "") {
			$(amounts[j[e]]).parents('.has-feedback').addClass('has-error')
			bool = false;
		}
		data.totalAmount[e] = amounts[j[e]].value
	})

	data.monthlyInstallment = []
	var installments = $('[name="customer_loan_entity_remaining_amount"]')
			.toArray()
	$.each(data.bankOrOrgName, function(e) {
		if (installments[j[e]].value === "") {
			$(installments[j[e]]).parents('.has-feedback')
					.addClass('has-error')
			bool = false;
		}
		data.monthlyInstallment[e] = installments[j[e]].value
	})

	data.remainingAmount = []
	var payments = $('[name="customer_loan_entity_monthly_payment"]').toArray()
	$.each(data.bankOrOrgName, function(e) {
		if (payments[j[e]].value === "") {
			$(payments[j[e]]).parents('.has-feedback').addClass('has-error')
			bool = false;
			}
		data.remainingAmount[e] = payments[j[e]].value
	})

	data.customer_payment_plan_in_month = []
	var payments = $('[name="customer_payment_plan_in_month"]').toArray()
	$.each(data.bankOrOrgName, function(e) {
		if (payments[j[e]] == undefined || payments[j[e]].value === "") {
			$(payments[j[e]]).parents('.has-feedback').addClass('has-error')
			// bool = false;
			data.customer_payment_plan_in_month[e] = 0
		} else {
			data.customer_payment_plan_in_month[e] = payments[j[e]].value
		}
	})

	data.customer_frequency_in_month = []
	var payments = $('[name="customer_frequency_in_month"]').toArray()
	$.each(data.bankOrOrgName, function(e) {
		if (payments[j[e]] == undefined || payments[j[e]].value === "") {
			$(payments[j[e]]).parents('.has-feedback').addClass('has-error')
			// bool = false;
			data.customer_frequency_in_month[e] = 0
		} else {
			data.customer_frequency_in_month[e] = payments[j[e]].value
		}
	})

	if (data.bankOrOrgName.length > 0 && bool) {
		console.log('run')
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			async : false,
			data : {
				action : 'insertLoan',
				customerId : customerId,
				data : data
			},
			success : function(json) {
				console.log('wizard_3_status_lal:  ' + json.data)
				showAlert('#wizard_3_status_lal', json,
						"Loan and Liabilities Added , " + json.data)
				if (json.status == "ok") {
					$('[data-customer-loan]').data('customer-loan', "true")
				} else {
					return false;
				}
			}
		})
	}
}

function insertElectricityExpenseDetail() {
	if ($('[data-customerelectricityusageid]').data(
			'customerelectricityusageid') == null
			|| $('[data-customerelectricityusageid]').data(
					'customerelectricityusageid') == "") {
		console.log("insert Electricity Expenses")
		var data = new Object();
		data.electricityUsage = []
		$.each($('[name="customer_electricity_usage"]').val(), function(e) {
			data.electricityUsage[e] = $('[name="customer_electricity_usage"]')
					.val()[e]
		})
		data.electricityExpenses = $('[name="customer_electricity_expenses"]')
				.val()
		data.majorExpenseDescription = $(
				'[name="customer_major_expenses_description"]').val()
		data.majorExpenseAmount = $('[name="customer_major_expenses_amount"]')
				.val()
		data.totalExpenseAmount = $('[name="customer_total_expenses_amount"]')
				.val()
		data.other = $('[name="customer_other_electricity_usage"]').val()
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			async : false,
			data : {
				action : 'insertElectricityExpenses',
				customerId : customerId,
				data : data
			},
			success : function(json) {
				console.log(json)
				showAlert('wizard_2', json)
				if (json.status == "ok") {
					$('[data-customerelectricityusageid]').data(
							'customerelectricityusageid', json.data)
				} else {
					// return false;
				}
			}

		})
		console.log(data)
	}

}

function insertAssets() {

	console.log('insertAssets '
			+ $('[data-customer-assets]').data('customer-assets'))
	// if($('[data-customer-assets]').data('customer-assets') == ""){
	var data = new Object();
	data.types = [];
	$.each($('[name="customer_assets_type"]'), function(e) {
		data.types[e] = $(this).val()
	})

	data.amounts = []
	$.each($('[name="customer_assets_amount"]'), function(e) {
		data.amounts[e] = $(this).val()
	})

	if (data.types.length > 0) {
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'insertAssets',
				customerId : customerId,
				data : data
			},
			success : function(json) {
				console.log(json)
				showAlert('#wizard_3_status_as', json, "Assets Added, ID")
				if (json.status == "ok") {
					$('[data-customer-assets]').data('customer-assets', "true")
				}
			}
		})
	} else {

	}
	// }
}

function insertFamilyGuarantorDetail() {
	// if($('[data-family-guarantorid]').data('family-guarantorid') == ""
	// || $('[data-family-guarantorid]').data('family-guarantorid') == "0"){
	console.log("insert family guarantor")
	var data = new Object();
	data.name = $('[name="family_guarantor_fullname"]').val();
	data.cnic = $('[name="family_guarantor_cnic"]').val();
	data.cellNumber = $('[name="family_guarantor_cell_number"]').val();
	data.secondaryCellNumber = $(
			'[name="family_guarantor_secondary_cell_number"]').val();

	data.relation = $('[name="family_guarantor_relation"]').val();
	data.incomeSource = $('[name="family_guarantor_income_source"]').val();
	data.guarantorProfession = $('[name="family_guarantor_profession"]').val();
	console.log($('[name="family_guarantor_profession"]').val())

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'insertGuarantor',
			customerId : customerId,
			guarantorType : 1,
			data : data
		},
		success : function(json) {
			console.log(json)
			showAlert('#wizard_4_status_cg', json,
					"Customer Guarantor Added, ID")
			if (json.status == "ok") {
				if (json.data != "0") {
					$('[data-family-guarantorid]').data('family-guarantorid',
							json.data)
				}
			}

		}
	})
	// }
}

function insertOutsideGuarantorDetail() {
	// if($('[data-outside-guarantorid]').data('outside-guarantorid') == ""){
	console.log("insert outside guarantor")
	var data = new Object();

	data.name = $('[name="outside_guarantor_fullname"]').val();
	data.cnic = $('[name="outside_guarantor_cnic"]').val();
	data.cellNumber = $('[name="outside_guarantor_cell_number"]').val();
	data.secondaryCellNumber = $(
			'[name="outside_guarantor_secondary_cell_number"]').val();

	data.relation = $('[name="outside_guarantor_relation"]').val();
	data.incomeSource = $('[name="outside_guarantor_income_source"]').val();
	data.guarantorProfession = $('[name="outside_guarantor_profession"]').val();

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'insertGuarantor',
			customerId : customerId,
			guarantorType : 2,
			data : data
		},
		success : function(json) {
			console.log(json)
			showAlert('#wizard_4_status_cg', json,
					"Customer Guarantor Added, ID")
			if (json.status == "ok") {
				if (json.data != "0") {
					$('[data-outside-guarantorid]').data('outside-guarantorid',
							json.data)
				}
			}
		}
	})
	// }
}

function insertVleGuarantorDetail() {
	if ($('[data-vleguarantorid]').data('vleguarantorid') == "") {
		var salesmanId = $('[name="customer_vle_guarantor"]').val()
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			dataType : 'json',
			data : {
				action : 'insertVleGuarantor',
				salesmanId : salesmanId,
				customerId : customerId
			},
			success : function(json) {
				console.log(json)
				if (json.status == "ok" && json.data != "0") {
					$('[data-vleguarantorid]')
							.data('vleguarantorid', json.data)
					eligibilityId = json.data
				}
			}
		})
	}
}

function insertAppliance() {
}

function getCustomerFormWizard() {

	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCustomerForm',
			customerId : customerId
		},
		success : function(json) {
			console.log(json)
			console.log("Fill Wizard : "+ json.customerForm.metaData.formWizard)
			console.log('VLE: ' + json.customerForm.metaData.vle +" "+json.customerForm.metaData.district)
			$('#select_customer_district').selectpicker('val',json.customerForm.metaData.district)
			$('#select_customer_district').selectpicker('render');
			$('#select_customer_vle').selectpicker('val',json.customerForm.metaData.vle)
			$('#select_customer_vle').selectpicker('render')
			
			switch (parseInt(json.customerForm.metaData.formWizard)) {
			case 5:
				fillWizardFive(json.customerForm.appliances)
			case 4:
				fillGuarantor(json.customerForm.guarantors)
			case 3:

				fillLiveStocks(json.customerForm.assetsMap)
				fillMonthlyExpense(json.customerForm.expenses)
				fillAssets(json.customerForm.assets)
				fillLoan(json.customerForm.loans)

				countTotalLiveAssetsAmount();
				countTotalAssetsAmount();
			case 2:
				fillWizardTwo(json.customerForm.incomeSource)
				fillOtherIncomeSource(json.customerForm.otherIncomeSources)
			case 1:
				fillWizardOne(json.customerForm.customerInfo)
				fillOtherNumbers(json.customerForm.otherNumbers)
				countTotalEducationMedicalAmount();
				break;
			}
		}
	})
}

function fillWizardFive(json) {
	var status = "";
	try {
		console.log("Filling Wizard Five")
		// $('[data-applianceids]').data('applianceids',"true")
		$('#label').show();
		$('#appliancesInAccount').show();

		for ( var i = 0; i <= json.length; i++) {
			if (json[i].appliance_status == "0") {
				status = "<span class='label label-danger'>Inactive</span>"
			} else {
				status = "<span class='label label-success'>Active</span>"
			}
			/*
			 * $('#appliancesInAccount').append("<br><div
			 * id='applianceName'"+i+"'><p>"+json[i].appliance_name+"</p><p>"+status+"</p></div>")
			 */

			$("#applianceInAccountTable").append(
					"<tr><td>" + json[i].appliance_name + "</td><td>" + status
							+ "</td></tr>")

		}
		// $('[name="appliance"]').trigger('keyup')
	} catch (e) {
		console.error(e)
	}
}

function fillWizardOne(json) {
	try {
		$('[name="customer_requested_date"]').datepicker('update',
				new Date(json.requestedDate))
		$('[name="customer_fullname"]').val(json.fullName)
		$('[name="customer_father_name"]').val(json.fatherName)
		$('[name="customer_gender"][value="' + json.gender + '"]').prop(
				'checked', true)
		$('[name="customer_date_of_birth"]').datepicker('update',
				new Date(json.date_of_birth))

		$('[name="customer_cnic"]').val(json.CNIC)
		if (json.primaryPhone != null)
			$('[name="customer_primary_phone"]').val(
					json.primaryPhone.substr(2))
		if (json.secondaryPhone != null)
			$('[name="customer_secondary_phone"]').val(
					json.secondaryPhone.substr(2))
			// $('[name="customer_email"]').val(json.email)
		$(
				'[name="customer_marital_status"][value="' + json.maritalStatus
						+ '"]').prop('checked', true)
		// $('[name="customer_education"]').selectpicker('val',json.educationId)
		$('[name="customer_education"][value="' + json.educationId + '"]')
				.prop('checked', true)
		$('#select_customer_city').selectpicker('val', json.cityId)
		$('#select_customer_city').selectpicker('render')
		$('[name="customer_address"]').val(json.address)
		// $('[name="family_size"]').val(json.familyMember);

		$('[name="customer_union_council"]').val(json.customer_union_council)
		$('[name="customer_requested_date"]').datepicker('update',
				new Date(json.requesteddate))
		$('[name="customer_provinces"][value="' + json.province + '"]').prop(
				'checked', true)
		$(
				'[name="customer_sys_installed_place"][value="'
						+ json.customer_sys_installed_place + '"]').prop(
				'checked', true)
		$('[name="customer_installment_address"]').val(
				json.customer_installment_address)
		$('[name="customer_caste"]').val(json.customerCaste)
		$(
				'[name="customer_disibility"][value="'
						+ json.customer_disibility + '"]')
				.prop('checked', true)
		$(
				'[name="customer_training_skill"][value="'
						+ json.customer_training_skill + '"]').prop('checked',
				true)
		$('[name="customer_course_name"]').val(json.customer_course_name)

		$(
				'[name="customer_head_faimly"][value="'
						+ json.customer_head_faimly + '"]').prop('checked',
				true)
		$(
				'[name="customer_total_household_members"][value="'
						+ json.customer_total_household_members + '"]').prop(
				'checked', true)
		$(
				'[name="customer_dependants_members"][value="'
						+ json.customer_dependants_members + '"]').prop(
				'checked', true)

		$('[name="number_of_children"][value="' + json.customer_children + '"]')
				.prop('checked', true)
		$('[name="customer_education_amount"]').val(
				json.customer_education_amount)
		$(
				'[name="customer_total_adult_members"][value="'
						+ json.customer_total_adult_members + '"]').prop(
				'checked', true)
		$(
				'[name="customer_medical_recuring"][value="'
						+ json.customer_medical_recuring + '"]').prop(
				'checked', true)
		$('[name="customer_medical_recuring_amount"]').val(
				json.customer_medical_recuring_amount)

		$(
				'[name="customer_families_type"][value="'
						+ json.customer_families_type + '"]').prop('checked',
				true)
		$(
				'[name="customer_roomoccupied_household"][value="'
						+ json.customer_roomoccupied_household + '"]').prop(
				'checked', true)
		$(
				'[name="customer_residence"][value="' + json.customer_residence
						+ '"]').prop('checked', true)
		$('[name="customer_other_residence"]').val(
				json.customer_other_residence)
		$(
				'[name="customer_residence_period"][value="'
						+ json.customer_residence_period + '"]').prop(
				'checked', true)

	} catch (e) {
		console.log(e)
	}
}

function fillOtherNumbers(json) {
	$('[data-customer-form]').data("customer-form", "true")
	for ( var i in json) {
		if (i >= 0) {
			$('#btn_add_other_number').trigger('click')
			$('#dynamic_customer_number .btn-danger').remove()
		}
		$('[name="who_is_other"]').eq(i).val(json[i].other_name)
		$('[name="relation_with_customer"]').eq(i).val(
				json[i].relation_with_other)
		$('[name="customer_other_phone"]').eq(i)
				.val(json[i].other_phone_number)
	}
}

function fillLiveStocks(json) {
	try {

		$('[name="customer_land"][value="' + json.customer_land + '"]').prop(
				'checked', true)
		$('[name="customer_bbuffalo"][value="' + json.customer_bbuffalo + '"]')
				.prop('checked', true)
		$('[name="customer_cow"][value="' + json.customer_cow + '"]').prop(
				'checked', true)
		$('[name="customer_calf"][value="' + json.customer_calf + '"]').prop(
				'checked', true)
		$(
				'[name="customer_goat_sheep"][value="'
						+ json.customer_goat_sheep + '"]')
				.prop('checked', true)
		$('[name="customer_bike"][value="' + json.customer_bike + '"]').prop(
				'checked', true)
		$('[name="customer_car"][value="' + json.customer_car + '"]').prop(
				'checked', true)
		$('[name="customer_tractors"][value="' + json.customer_tractors + '"]')
				.prop('checked', true)
		$('[name="customer_tv"][value="' + json.customer_tv + '"]').prop(
				'checked', true)
		$('[name="customer_fridge"][value="' + json.customer_fridge + '"]')
				.prop('checked', true)

		$('[name="customer_land_amount"]').val(json.customer_land_amount)
		$('[name="customer_buffalo_amount"]').val(json.customer_buffalo_amount)
		$('[name="customer_cow_amount"]').val(json.customer_cow_amount)
		$('[name="customer_calf_amount"]').val(json.customer_calf_amount)
		$('[name="customer_goat_Sheep_amount"]').val(
				json.customer_goat_Sheep_amount)
		$('[name="customer_bike_amount"]').val(json.customer_bike_amount)
		$('[name="customer_car_amount"]').val(json.customer_car_amount)
		$('[name="customer_tractors_amount"]').val(
				json.customer_tractors_amount)
		$('[name="customer_tv_amount"]').val(json.customer_tv_amount)
		$('[name="customer_fridge_amount"]').val(json.customer_fridge_amount)

		$(
				'[name="customer_Connectivity"][value="'
						+ json.customer_electricity_type + '"]').prop(
				'checked', true)
		$(
				'[name="customer_sourceof_electricity"][value="'
						+ json.customer_sourceof_electricity + '"]').prop(
				'checked', true)
		$('[name="customer_primary_sourse_amount"]').val(
				json.customer_electricity_amount)

		$(
				'[name="customer_mobile_type"][value="'
						+ json.customer_mobile_type + '"]').prop('checked',
				true)
		$('[name="customer_mobilebills_amount"]').val(
				json.customer_mobilebills_amount)

		$('[name="mobilink_network"][value="' + json.mobilink_network + '"]')
				.prop('checked', true)
		$('[name="telenor_network"][value="' + json.telenor_network + '"]')
				.prop('checked', true)
		$('[name="ufone_network"][value="' + json.ufone_network + '"]').prop(
				'checked', true)
		$('[name="warid_network"][value="' + json.warid_network + '"]').prop(
				'checked', true)
		$('[name="zong_network"][value="' + json.zong_network + '"]').prop(
				'checked', true)

		$(
				'[name="customer_transport_mode"][value="'
						+ json.customer_transport_mode + '"]').prop('checked',
				true)
		$('[name="customer_transport_amount"]').val(
				json.customer_transport_amount)

		$('[name="customer_karyana"][value="' + json.customer_karyana + '"]')
				.prop('checked', true)
		$('[name="customer_karyana_amount"]').val(json.customer_karyana_amount)
	} catch (e) {
		console.log(e)

	}
}

function fillWizardTwo(json) {
	try {
		$('[data-customerincomesource]').data('customerincomesource', "true")
		$('[name="customer_salary_or_pension"]').val(json.salaryOrPension)
		$('[name="customer_salary_or_pension"]').trigger('keyup')

		$('[name="customer_business_income"]').val(json.businessIncome)
		$('[name="customer_business_income"]').trigger('keyup')

		$('[name="customer_farming_income"]').val(json.farmingIncome)
		$('[name="customer_farming_income"]').trigger('keyup')

		$(
				'[name="customer_livestock_type"][value="'
						+ json.customerLivestockType + '"]').prop('checked',
				true)

		$('[name="customer_livestock_othertype"]').val(
				json.customerLivestockOthertype)
		$('[name="customer_livestock_othertype"]').trigger('keyup')

		$('[name="customer_livestock_income"]').val(
				json.customerLivestockIncome)
		$('[name="customer_livestock_income"]').trigger('keyup')

		$('[name="customer_remittances"]').val(json.customerRemittances)
		$('[name="customer_remittances"]').trigger('keyup')
		$(
				'[name="customer_remittances_frequency"][value="'
						+ json.customerRemittancesFrequency + '"]').prop(
				'checked', true)

		$('[name="customer_remittances_relationship"]').val(
				json.customerRemittancesRelation)

		$('[name="customer_remittances_profession"]').val(
				json.customerRemittancesProfession)
		$('[name="customer_remittances_profession"]').trigger('keyup')

		$('[name="customer_family_contribution"]').val(json.familyContribution)
		// $('[name="customer_family_contribution"]').trigger('keyup')

		$(
				'[name="agriculture_profit_frequency"][value="'
						+ json.agricultureProfitFrequency + '"]').prop(
				'checked', true)

		$('[name="customer_remittances_where"]').val(
				json.customerRemittancesWhere)
		$('[name="customer_remittances_where"]').trigger('keyup')

		$('[name="customer_rental_profit_income"]').val(
				json.customerRentalProfitIncome)
		$('[name="customer_rental_profit_income"]').trigger('keyup')
		$(
				'[name="customer_rental_frequency"][value="'
						+ json.customerRentalFrequency + '"]').prop('checked',
				true)
		$('[name="customer_rental_from"]').val(json.customerRentalFrom)

		$(
				'[name="customer_income_comtributers"][value="'
						+ json.customerIncomeComtributers + '"]').prop(
				'checked', true)
		// $('[name="customer_income_comtributers"]').trigger('keyup')

		$('[name="customer_contributers_amount"]').val(json.familyContribution)
		$('[name="customer_contributers_amount"]').trigger('keyup')

		$(
				'[name="customer_labour_type"][value="'
						+ json.customerLabourType + '"]').prop('checked', true)

		$('[name="customer_labour_amount"]').val(json.customerLabourAmount)
		$('[name="customer_labour_amount"]').trigger('keyup')

		$('[name="customer_labour_in_month"]').val(json.customerLabourInMonth)
		$('[name="customer_labour_in_month"]').trigger('keyup')

		$('[name="customer_other_labour_type"]').val(
				json.customerOtherLabourType)
		$('[name="customer_other_labour_type"]').trigger('keyup')

		if (parseInt(json.salaryOrPension) > 0) {
			$('[data-customer-employeeid]').data('customer-employeeid',
					json.employeeId)
			$('[name="customer_employement_company_name"]').val(
					json.organisationName);
			$('[name="customer_employement_designation"]')
					.val(json.designation);
			$('[name="customer_employement_job_period"]').val(json.jobPeriod);
		}

		if (parseInt(json.businessIncome) > 0) {
			$('[data-customer-businessid]').data('customer-businessid',
					json.businessId)
			$('[name="customer_business_title"]').val(json.businessName);
			$('[name="customer_business_type"]').val(json.businessType);
			$('[name="customer_business_time_period"]')
					.val(json.businessPeriod);
		}
	} catch (e) {
		console.error(e)
	}
}

function fillOtherIncomeSource(json) {
	for ( var ob in json) {
		$('#btn_add_income_source').trigger('click')
		$('#other_income_source .btn-danger').remove()
		$('[name="customer_other_income_source_type"]').eq(ob).val(
				json[ob].type)
		$('[name="customer_other_income_source_amount"]').eq(ob).val(
				json[ob].amount)
		$('[name="customer_other_income_source_amount"]').trigger('keyup')
	}
}

function fillMonthlyExpense(json) {
	$('[data-customer-monthly-expense]').data("customer-monthly-expense",
			"true")
	for ( var i in json) {
		if (i >= 0) {
			$('#btn_add_expense').trigger('click')
			$('#add_expense .btn-danger').remove()
		}
		$('[name="customer_expenses_type"]').eq(i).val(json[i].type)
		$('[name="customer_expenses_amount"]').eq(i).val(json[i].amount)
	}
}

function fillAssets(json) {
	$('[data-customer-assets]').data('customer-assets', "true")

	for ( var i in json) {
		if (i >= 0) {
			$('#btn_add_assets').trigger('click')
			$('#add_assets .btn-danger').remove()
		}
		$('[name="customer_assets_type"]').eq(i).val(json[i].type)
		$('[name="customer_assets_amount"]').eq(i).val(json[i].amount)
	}
}

function fillLoan(json) {
	$('[data-customer-loan]').data('customer-loan', "true")
	for ( var i in json) {
		if (i > 0) {
			$('#btn_add_loan_and_liabilities').trigger('click')
		}
		switch (parseInt(json[i].type)) {
		case 0:
			$('#_loan_and_liability_id_ [name="customer_loan_entity_name"]')
					.eq(i).val(json[i].donnerName)
			$('#_loan_and_liability_id_ [name="customer_loan_entity_amount"]')
					.eq(i).val(json[i].barrowedAmount)
			$(
					'#_loan_and_liability_id_ [name="customer_loan_entity_remaining_amount"]')
					.eq(i).val(json[i].remainingAmount)
			$(
					'#_loan_and_liability_id_ #customer_loan_entity_monthly_payment_id')
					.eq(i).val(json[i].monthlyInstallment)

			$('#_loan_and_liability_id_ #select_payment_plan_in_month').val(
					json[i].payment_planInMonth).selectpicker('render');
			break;
		case 1:
			$('#_loan_and_liability_id_1 [name="customer_loan_entity_name"]')
					.val(json[i].donnerName)
			$('#_loan_and_liability_id_1 [name="customer_loan_entity_amount"]')
					.val(json[i].barrowedAmount)
			$(
					'#_loan_and_liability_id_1 [name="customer_loan_entity_remaining_amount"]')
					.val(json[i].remainingAmount)
			$(
					'#_loan_and_liability_id_1 [name="customer_loan_entity_monthly_payment"]')
					.val(json[i].monthlyInstallment)

			break;
		case 2:
			$('#_loan_and_liability_id_2 [name="customer_loan_entity_name"]')
					.val(json[i].donnerName)
			$('#_loan_and_liability_id_2 [name="customer_loan_entity_amount"]')
					.val(json[i].barrowedAmount)
			$(
					'#_loan_and_liability_id_2 [name="customer_loan_entity_remaining_amount"]')
					.val(json[i].remainingAmount)
			$(
					'#_loan_and_liability_id_2 [name="customer_loan_entity_monthly_payment"]')
					.val(json[i].monthlyInstallment)
			$('#select_payment_plan_in_month').selectpicker('val',
					json[i].payment_planInMonth)
			$('#select_frequency_in_month').selectpicker('val',
					json[i].frequency_paymentInMonth)

			break;
		case 3:
			$('#_loan_and_liability_id_3 [name="customer_loan_entity_name"]')
					.val(json[i].donnerName)
			$('#_loan_and_liability_id_3 [name="customer_loan_entity_amount"]')
					.val(json[i].barrowedAmount)
			$(
					'#_loan_and_liability_id_3 [name="customer_loan_entity_remaining_amount"]')
					.val(json[i].remainingAmount)
			$(
					'#_loan_and_liability_id_3 [name="customer_loan_entity_monthly_payment"]')
					.val(json[i].monthlyInstallment)
			$(
					'#_loan_and_liability_id_3 [name="customer_payment_plan_in_month"]')
					.val(json[i].payment_planInMonth)
			$('#_loan_and_liability_id_3 [name="customer_frequency_in_month"]')
					.val(json[i].frequency_paymentInMonth)

			break;
		}
	}
}

function fillGuarantor(json) {
	
	for ( var i in json) {
		switch (parseInt(json[i].type)) {
		case 2:
			$('[data-outside-guarantorid]').data('outside-guarantorid',
					json[i].id)
			$('[name="outside_guarantor_fullname"]').val(json[i].name);
			$('[name="outside_guarantor_cnic"]').val(json[i].CNIC);
			if (json[i].primaryPhone != null)
				$('[name="outside_guarantor_cell_number"]').val(
						json[i].primaryPhone.substr(2))
			if (json[i].secondaryPhone != null)
				$('[name="outside_guarantor_secondary_cell_number"]').val(
						json[i].secondaryPhone.substr(2))
			$('[name="outside_guarantor_relation"]').val(json[i].relation);
			$('[name="outside_guarantor_income_source"]').val(json[i].income);
			$('[name="outside_guarantor_profession"]').val(
					json[i].guarantorProfession);
			break;
		case 1:
			$('[data-family-guarantorid]').data('family-guarantorid',
					json[i].id)
			$('[name="family_guarantor_fullname"]').val(json[i].name);
			$('[name="family_guarantor_cnic"]').val(json[i].CNIC);
			if (json[i].primaryPhone != null)
				$('[name="family_guarantor_cell_number"]').val(
						json[i].primaryPhone.substr(2))
			if (json[i].secondaryPhone != null)
				$('[name="family_guarantor_secondary_cell_number"]').val(
						json[i].secondaryPhone.substr(2))
			$('[name="family_guarantor_relation"]').val(json[i].relation);
			$('[name="family_guarantor_income_source"]').val(json[i].income);
			$('[name="family_guarantor_profession"]').val(
					json[i].guarantorProfession);
			break;
		}
	}

}

function showAlert(div, json, message) {
	$(div + ' .alert').alert('close')
	$(div).append(alert)
	if (json.status == "ok" && parseInt(json.data) > 0) {
		$(div + ' .alert').addClass('alert-success')
		$(div + ' .alert strong').text("Success")
		$(div + ' .alert p').text(message + " : " + json.data)
	} else if (parseInt(json.data) == -1) {
		$(div + ' .alert').addClass('alert-warning')
		$(div + ' .alert strong').text("Not Saved")
		// $(div+' .alert p').text("")
	} else if (json.status == "error") {
		$(div + ' .alert').addClass('alert-danger')
		$(div + ' .alert strong').text("Error")
		if (json.message.length > 0) {
			$(div + ' .alert p').text(
					json.status + " : " + json.message[0].toString())
		} else {
			$(div + ' .alert p').text(json.status + " : " + json.message)
		}
	} else {
		$(div + ' .alert').addClass('alert-success')
		$(div + ' .alert strong').text("Success")
		$(div + ' .alert p').text("Updated")
	}
	$(alert).alert()
}

function getCnic(msg) {
	var customerCnic;
	if (document.getElementById('cnic') != null) {
		customerCnic = document.getElementById('cnic').value;
	} else {
		customerCnic = null;
	}
	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCnic',
			customerCnic : customerCnic
		},
		success : function(data) {
			console.log(data)
			if (data.status == "ok") {
				console.log(data)
				document.getElementById(msg).innerHTML += 'Cnic already exist'
			}
		}
	})

}