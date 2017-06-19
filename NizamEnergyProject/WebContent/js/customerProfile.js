function getCustomerProfile(customerId, customerCnic) {
	$
			.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getCustomerProfile',
					customerId : customerId,
					customerCnic : customerCnic
				},
				success : function(json) {

					if (customerId != null && customerCnic != null) {

						// now get customer name and phone number for cc
						// verification
						$('#customer_loan_source').text(json.data[0].donner);
						$('#customer_loan_amount')
								.text(
										json.data[0].barrowed_amount == 'N/A' ? '0 PKR'
												: formatNumber(json.data[0].barrowed_amount));

						$('#guarantor_customer_loan_source').text(
								json.data[0].donner);
						$('#guarantor_customer_loan_amount')
								.text(
										json.data[0].barrowed_amount == 'N/A' ? '0 PKR'
												: formatNumber(json.data[0].barrowed_amount));
						console.log('//////// ---- '
								+ json.data[0].barrowed_amount);

						$('#customer_name_for_cc').text(
								json.data[0].customer_name);
						$('#customer_name_for_nd_q1').text(
								json.data[0].customer_name);
						$('#customer_name_for_nd_q5').text(
								json.data[0].customer_name);
						$('#customer_name_for_guarantor_q1').text(
								json.data[0].customer_name);
						$('#customer_name_for_customer_verification_q1').text(
								json.data[0].customer_name);
						$('#customer_house')
								.text(
										json.data[0].customer_residence == ''
												|| json.data[0].customer_residence == 'null' ? 'N/A'
												: json.data[0].customer_residence == 'other' ? json.data[0].customer_other_residence
														: json.data[0].customer_residence);

						$('#customer_live_since').text(
								json.data[0].customer_residence_period);
						$('#guarantor_customer_house')
								.text(
										json.data[0].customer_residence == ''
												|| json.data[0].customer_residence == 'null' ? 'N/A'
												: json.data[0].customer_residence == 'other' ? json.data[0].customer_other_residence
														: json.data[0].customer_residence);

						$('#guarantor_customer_live_since').text(
								json.data[0].customer_residence_period);

						$('#guarantor_customer_source_of_income').text(
								json.data[0].incomeSource);
						$('#guarantor_customer_largest_income_amount').text(
								formatNumber(json.data[0].largestAmount));

						$('#customer_source_of_income').text(
								json.data[0].incomeSource);
						$('#customer_largest_income_amount').text(
								formatNumber(json.data[0].largestAmount));

						$('#customer_phone_for_cc')
								.text(
										json.data[0].customer_phone == '92'
												|| json.data[0].customer_phone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].customer_phone));

						// Customer Table
						$('#customer_name').text(json.data[0].customer_name);
						$('#customerName').text(json.data[0].customer_name);
						$('#customer_name_for_accept_request').text(
								json.data[0].customer_name);

						$('#customer_cnic').text(json.data[0].customer_cnic);
						$('#date_of_birth')
								.text(
										json.data[0].dateOfBirth == '0000-00-00'
												|| json.data[0].dateOfBirth == '' ? 'N/A'
												: json.data[0].dateOfBirth);
						$('#customer_address').text(
								(json.data[0].customer_address) == '' ? 'N/A'
										: json.data[0].customer_address);
						$('#city_name').text(json.data[0].city_name);
						$('#tehsil_district').text(
								json.data[0].city_name + ', '
										+ json.data[0].district_name);
						$('#customer_phone')
								.text(
										json.data[0].customer_phone == '92'
												|| json.data[0].customer_phone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].customer_phone));
						$('#customer_phone2')
								.text(
										json.data[0].customer_phone2 == '92'
												|| json.data[0].customer_phone2 == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].customer_phone2));
						$('#father_name').text(
								(json.data[0].father_name) == '' ? 'N/A'
										: json.data[0].father_name);
						$('#gender').text(json.data[0].gender);
						$('#relation_status').text(
								(json.data[0].relation_status) == '' ? 'N/A'
										: json.data[0].relation_status);
						$('#created_date').text(json.data[0].created_on);
						$('#salary_or_pension').text(
								formatNumber(json.data[0].salaryPension));

						$('#business_income').text(
								formatNumber(json.data[0].businessIncome));
						$('#family_contribution')
								.text(
										(json.data[0].family_contribution) == '' ? 'N/A'
												: json.data[0].family_contribution);
						$('#education')
								.text(
										(json.data[0].educations) == 'undefined' ? 'N/A'
												: json.data[0].educations);

						$('#customerCaste').text(
								(json.data[0].customerCaste) == '' ? 'N/A'
										: json.data[0].customerCaste);
						$('#customer_union_council')
								.text(
										(json.data[0].customer_union_council) == '' ? 'N/A'
												: json.data[0].customer_union_council);
						$('#customer_disibility')
								.text(
										(json.data[0].customer_disibility) == '' ? 'N/A'
												: json.data[0].customer_disibility);
						$('#customer_training_skill').text(
								json.data[0].customer_training_skills);
						$('#customer_head_faimly').text(
								json.data[0].customer_head_faimly);
						$('#customer_dependants_members').text(
								json.data[0].customer_dependants_members);
						$('#customer_total_household_members').text(
								json.data[0].customer_total_household_members);
						$('#customer_families_type')
								.text(
										json.data[0].customer_families_type == 'single family' ? 'Single family'
												: 'Joint family ('
														+ json.data[0].customer_families_household
														+ ')');
						$('#customer_roomoccupied_household').text(
								json.data[0].customer_roomoccupied_household);
						$('#customer_residence')
								.text(
										json.data[0].customer_residence == ''
												|| json.data[0].customer_residence == 'null' ? 'N/A'
												: json.data[0].customer_residence == 'other' ? json.data[0].customer_other_residence
														: json.data[0].customer_residence);

						$('#customer_residence_period').text(
								json.data[0].customer_residence_period);
						$('#province').text(json.data[0].province);
						$('#requested_date').text(json.data[0].requesteddate);
						$('#customer_children').text(
								json.data[0].customer_children);
						$('#customer_education_amount')
								.text(
										formatNumber(json.data[0].customer_education_amount));
						$('#customer_total_adult_members').text(
								json.data[0].customer_total_adult_members);
						$('#customer_medical_recuring')
								.text(
										json.data[0].customer_medical_recuring
												+ ' ('
												+ formatNumber(json.data[0].customer_medical_recuring_amount)
												+ ')');
						$('#customer_sys_installed_place').text(
								json.data[0].customer_sys_installed_place);
						$('#customer_installment_address').text(
								json.data[0].customer_installment_address);
						$('#customer_income_comtributers')
								.text(
										json.data[0].familyContributors
												+ ' ('
												+ formatNumber(json.data[0].familyContribution)
												+ ')');

						// business_info Table
						$('#business_name').text(json.data[0].business_names);
						$('#businees_type').text(json.data[0].businees_types);
						$('#period').text(json.data[0].periods);

						// insert_customer_expense Table
						$('#monthly_expenses')
								.text(
										formatNumber(json.data[0].additionalMonthlyExpenses));
						$('#customer_electricity_type')
								.text(
										json.data[0].customerElectricityType == 'N/A' ? 'N/A'
												: json.data[0].customerElectricityType == 0 ? 'Off-Grid'
														: 'On-Grid');
						$('#customer_sourceof_electricity')
								.text(
										json.data[0].customerSourceOfElectricity == 'N/A' ? 'N/A'
												: json.data[0].customerSourceOfElectricity);
						$('#customer_electricity_amount')
								.text(
										json.data[0].customerElectricityAmount == '0' ? 'N/A'
												: formatNumber(json.data[0].customerElectricityAmount));
						$('#customer_mobile_type')
								.text(
										json.data[0].customerMobileType == 'N/A' ? 'N/A'
												: json.data[0].customerMobileType);
						$('#customer_mobilebills_amount')
								.text(
										json.data[0].customerMobileBillsAmount == '0' ? 'N/A'
												: formatNumber(json.data[0].customerMobileBillsAmount));

						$('#networks_in_area').text(
								(json.data[0].network) == '' ? 'N/A'
										: json.data[0].network);

						$('#customer_transport_mode')
								.text(
										(json.data[0].customerTransportMode == 'N/A' ? 'N/A'
												: json.data[0].customerTransportMode)
												+ ' ('
												+ (json.data[0].customerTransportAmount == 'null' ? 'N/A'
														: formatNumber(json.data[0].customerTransportAmount))
												+ ')');
						$('#customer_karyana')
								.text(
										(json.data[0].customerKaryana == 'N/A' ? 'N/A'
												: json.data[0].customerKaryana)
												+ ' ('
												+ (json.data[0].customerKaryanaAmount == 'null' ? 'N/A'
														: formatNumber(json.data[0].customerKaryanaAmount))
												+ ')');
						$('#customer_estimated_amount')
								.text(
										formatNumber(json.data[0].customer_estimated_amount));

						// employment_details Table
						$('#organisation_name').text(
								(json.data[0].organisation_names));
						$('#job_position').text((json.data[0].job_positions));
						$('#job_period').text((json.data[0].job_periods));

						// customer_livestock_assets Table
						$('#assets_amount').text(
								formatNumber(json.data[0].assets_amount));

						$('#customer_land')
								.text(
										((json.data[0].customerLand) == '0' ? 'N/A'
												: json.data[0].customerLand)
												+ ' ('
												+ formatNumber(json.data[0].customerLandAmount)
												+ ')');

						$('#customer_bbuffalo')
								.text(
										((json.data[0].customerBuffalo) == '0' ? 'N/A'
												: json.data[0].customerBuffalo)
												+ ' ('
												+ formatNumber(json.data[0].customerBuffaloAmount)
												+ ')');
						$('#customer_cow')
								.text(
										((json.data[0].customerCow) == '0' ? 'N/A'
												: json.data[0].customerCow)
												+ ' ('
												+ formatNumber(json.data[0].customerCowAmount)
												+ ')');
						$('#customer_calf')
								.text(
										((json.data[0].customerCalf) == '0' ? 'N/A'
												: json.data[0].customerCalf)
												+ ' ('
												+ formatNumber(json.data[0].customerCalfAmount)
												+ ')');
						$('#customer_goat_sheep')
								.text(
										((json.data[0].customerGoatSheep) == '0' ? 'N/A'
												: json.data[0].customerGoatSheep)
												+ ' ('
												+ formatNumber(json.data[0].customerGoatSheepAmount)
												+ ')');
						$('#customer_bike')
								.text(
										((json.data[0].customerBike) == '0' ? 'N/A'
												: json.data[0].customerBike)
												+ ' ('
												+ formatNumber(json.data[0].customerBikeAmount)
												+ ')');
						$('#customer_car')
								.text(
										((json.data[0].customerCar) == '0' ? 'N/A'
												: json.data[0].customerCar)
												+ ' ('
												+ formatNumber(json.data[0].customerCarAmount)
												+ ')');
						$('#customer_tractors')
								.text(
										((json.data[0].customerTractors) == '0' ? 'N/A'
												: json.data[0].customerTractors)
												+ ' ('
												+ formatNumber(json.data[0].customerTractorsAmount)
												+ ')');
						$('#customer_tv')
								.text(
										((json.data[0].customerTV) == '0' ? 'N/A'
												: json.data[0].customerTV)
												+ ' ('
												+ formatNumber(json.data[0].customerTvAmount)
												+ ')');
						$('#customer_fridge')
								.text(
										((json.data[0].customerFridge) == '0' ? 'N/A'
												: json.data[0].customerFridge)
												+ ' ('
												+ formatNumber(json.data[0].customerFridgeAmount)
												+ ')');
						// salesman Table
						$('#salesman_id').text(json.data[0].salesman_id);
						$('#salesman_name').text(json.data[0].salesman_name);
						$('#salesman_cnic').text(json.data[0].salesman_cnic);
						$('#salesman_primary_phone')
								.text(
										getFormattedPhoneNumber(json.data[0].salesman_primary_phone));
						$('#salesman_secondary_phone')
								.text(
										getFormattedPhoneNumber(json.data[0].salesman_secondary_phone));

						// field_officer Table
						$('#fo_id').text(json.data[0].fo_id);
						$('#fo_name').text(json.data[0].fo_name);
						$('#fo_priamary_phone')
								.text(
										getFormattedPhoneNumber(json.data[0].fo_priamary_phone));
						$('#fo_secondary_phone')
								.text(
										getFormattedPhoneNumber(json.data[0].fo_secondary_phone));

						// monthly_and_yearly_income Table
						$('#net_monthly_income').text(
								formatNumber(json.data[0].netIncomeAmount));
						$('#customer_total_income').text(
								formatNumber(json.data[0].customerTotalIncome));
						$('#customer_total_irregularly_income')
								.text(
										formatNumber(json.data[0].customerTotalIrregularlyIncome));

						$('#farming')
								.text(
										json.data[0].agricultureProfitFrequencyy == '' ? 'N/A'
												: json.data[0].agricultureProfitFrequencyy
														+ ' ('
														+ formatNumber(json.data[0].farmingg)
														+ ')');
						$('#customerLivestockIncome')
								.text(
										formatNumber(json.data[0].customerLivestockIncomee));
						$('#customerLivestockType')
								.text(
										json.data[0].customerLivestockTypee == '' ? 'N/A'
												: json.data[0].customerLivestockTypee);
						$('#customerLivestockOthertype').text(
								json.data[0].customerLivestockOthertype);

						$('#customerRemittancee')
								.text(
										json.data[0].customerRemittancesFrequencyy
												+ ' ('
												+ formatNumber(json.data[0].customerRemittancess)
												+ ')');
						$('#customerRemittancesRelation')
								.text(
										json.data[0].customerRemittancesRelationn == '' ? 'N/A'
												: json.data[0].customerRemittancesRelationn);
						$('#customer_remittances_profession')
								.text(
										json.data[0].customerRemittancesProfession == '' ? 'N/A'
												: json.data[0].customerRemittancesProfession);
						$('#customer_rental_profit_income')
								.text(
										json.data[0].customerRentalFrequency == '' ? 'N/A'
												: json.data[0].customerRentalFrequency
														+ ' ('
														+ (formatNumber(json.data[0].customerRentalProfitIncome))
														+ ')');

						$('#customer_rental_from').text(
								json.data[0].customerRentalFrom == '' ? 'N/A'
										: json.data[0].customerRentalFrom);
						$('#customer_labour_type').text(
								json.data[0].customerLabourType == '' ? 'N/A'
										: json.data[0].customerLabourType);
						$('#customer_labour_amount')
								.text(
										formatNumber(json.data[0].customerLabourAmount));

						$('#customer_labour_in_month').text(
								json.data[0].customerLabourDaysInMonth);
						$('#customer_other_labour_type')
								.text(
										(json.data[0].customerOtherLabourType) == '' ? 'N/A'
												: json.data[0].customerOtherLabourType);

					}
				}
			});
}

function getCustomerOtherPhoneNumbers(customerId) {
	$
			.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getCustomerOtherPhoneNumbers',
					customerId : customerId
				},
				success : function(json) {

					if (customerId != null && json != null) {
						$('#additionContact').empty();
						if (json.data.length > 0) {
							$
									.each(
											json.data,
											function(e) {
												var tds = '<tr>'
														+ '<td>'
														+ json.data[e].other_name
														+ '</td>'
														+ '<td>'
														+ json.data[e].relation_with_other
														+ '</td>'
														+ '<td>'
														+ getFormattedPhoneNumber(json.data[e].other_phone_number)
														+ '</td>' + '</tr>';
												$("#additionContact").append(
														tds);
											});
						}
					}
				}
			});
}

function getOtherIncome(customerId) {
	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCustomerOtherIncome',
			customerId : customerId
		},
		success : function(json) {

			if (customerId != null && json != null) {
				$('#additionalIncome').empty();
				if (json.data.length > 0) {
					$.each(json.data, function(e) {
						var tds = '<tr>' + '<td>' + json.data[e].detail
								+ '</td>' + '<td>'
								+ formatNumber(json.data[e].amount) + '</td>'
								+ '</tr>';
						$("#additionalIncome").append(tds);
					});
				}
			}
		}
	});
}

function getOtherAssets(customerId) {
	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCustomerOtherAssets',
			customerId : customerId
		},
		success : function(json) {

			if (customerId != null && json != null) {
				$('#additionalAssets').empty();
				if (json.data.length > 0) {
					$.each(json.data, function(e) {
						var tds = '<tr>' + '<td>' + json.data[e].type + '</td>'
								+ '<td>' + formatNumber(json.data[e].amount)
								+ '</td>' + '</tr>';
						$("#additionalAssets").append(tds);
					});
				}
			}
		}
	});
}

function getOtherMonthlyExpenses(customerId) {
	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCustomerMonthlyExpenses',
			customerId : customerId
		},
		success : function(json) {
			if (customerId != null && json != null) {
				$('#additionalMonthlyExpenses').empty();
				if (json.data.length > 0) {
					$.each(json.data, function(e) {
						var tds = '<tr>' + '<td>' + json.data[e].type + '</td>'
								+ '<td>' + formatNumber(json.data[e].amount)
								+ '</td>' + '</tr>';
						$("#additionalMonthlyExpenses").append(tds);
					});
				}
			}
		}
	});
}

function getCustomerLoanAndLiabilities(customerId) {
	$.ajax({
		url : 'CustomerFormController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : 'getCustomerLoanAndLiabilities',
			customerId : customerId
		},
		success : function(json) {
			if (customerId != null && json != null) {
				$('#loanAndLiabilities').empty();
				if (json.data.length > 0) {
					$.each(json.data, function(e) {
						var tds = '<tr>' + '<td>' + json.data[e].donner
								+ '</td>' + '<td>'
								+ formatNumber(json.data[e].barrowed_amount)
								+ '</td>' + '<td>'
								+ formatNumber(json.data[e].remaining_amount)
								+ '</td>' + '<td>'
								+ json.data[e].monthly_installment + '</td>'
								+ '<td>' + json.data[e].payment_planInMonth
								+ ' Month</td>' + '<td>'
								+ json.data[e].frequency_paymentInMonth
								+ ' Month</td>' + '</tr>';
						$("#loanAndLiabilities").append(tds);
					});

				}

			}
		}
	});
}

function getCustomerFamilyGuarantor(customerId, gType) {
	$
			.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getCustomerGuarantors',
					customerId : customerId,
					gType : gType
				},
				success : function(json) {

					if (customerId != null && json.data.length != 0) {
						console.log('yeesss if||||');
						$('#f_guarantor_name').text(
								(json.data[0].gguarantorName) == '' ? 'N/A'
										: json.data[0].gguarantorName);
						$('#f_guarantor_cnic').text(
								(json.data[0].gguarantorCnic) == '' ? 'N/A'
										: json.data[0].gguarantorCnic);
						$('#f_guarantor_primary_phone')
								.text(
										json.data[0].gguarantorPhone == '92'
												|| json.data[0].gguarantorPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorPhone));
						$('#f_guarantor_secondary_phone')
								.text(
										json.data[0].gguarantorSecondryPhone == '92'
												|| json.data[0].gguarantorSecondryPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorSecondryPhone));
						$('#f_guarantor_relationship')
								.text(
										(json.data[0].gRelationToCustomer) == '' ? 'N/A'
												: json.data[0].gRelationToCustomer);
						$('#f_guarantor_monthly_income')
								.text(
										(json.data[0].gguarantorIncome) == '' ? 'N/A'
												: formatNumber(json.data[0].gguarantorIncome));
						$('#f_guarantor_profession')
								.text(
										(json.data[0].guaranterProfession) == '' ? 'N/A'
												: json.data[0].guaranterProfession);

						// get family guarantor name and primary phone for cc
						// verification form
						$('#f_guarantor_name_for_cc').text(
								(json.data[0].gguarantorName) == '' ? 'N/A'
										: json.data[0].gguarantorName);
						$('#f_guarantor_primary_phone_for_cc')
								.text(
										json.data[0].gguarantorPhone == '92'
												|| json.data[0].gguarantorPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorPhone));
					} else {
						console.log('yeesss else ||||');
						$('#f_guarantor_name').text('N/A');
						$('#f_guarantor_cnic').text('N/A');
						$('#f_guarantor_primary_phone').text('N/A');
						$('#f_guarantor_secondary_phone').text('N/A');
						$('#f_guarantor_relationship').text('N/A');
						$('#f_guarantor_monthly_income')
								.text(formatNumber('0'));
						$('#f_guarantor_profession').text('N/A');
					}
				}
			});
}

function getCustomerOutsideGuarantor(customerId, gType) {
	$
			.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getCustomerGuarantors',
					customerId : customerId,
					gType : gType
				},
				success : function(json) {

					if (customerId != null && json.data.length != 0) {
						$('#o_guarantor_name').text(
								(json.data[0].gguarantorName) == '' ? 'N/A'
										: json.data[0].gguarantorName);
						$('#o_guarantor_cnic').text(
								(json.data[0].gguarantorCnic) == '' ? 'N/A'
										: json.data[0].gguarantorCnic);
						$('#o_guarantor_primary_phone')
								.text(
										json.data[0].gguarantorPhone == '92'
												|| json.data[0].gguarantorPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorPhone));
						$('#o_guarantor_secondary_phone')
								.text(
										json.data[0].gguarantorSecondryPhone == '92'
												|| json.data[0].gguarantorSecondryPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorSecondryPhone));
						$('#o_guarantor_relationship')
								.text(
										(json.data[0].gRelationToCustomer) == '' ? 'N/A'
												: json.data[0].gRelationToCustomer);
						$('#o_guarantor_monthly_income')
								.text(
										(json.data[0].gguarantorIncome) == '' ? 'N/A'
												: formatNumber(json.data[0].gguarantorIncome));
						$('#o_guarantor_profession')
								.text(
										(json.data[0].guaranterProfession) == '' ? 'N/A'
												: json.data[0].guaranterProfession);

						// now get outside guarantor name and primary phone for
						// cc verification form
						$('#o_guarantor_name_for_cc').text(
								(json.data[0].gguarantorName) == '' ? 'N/A'
										: json.data[0].gguarantorName);
						$('#o_guarantor_primary_phone_for_cc')
								.text(
										json.data[0].gguarantorPhone == '92'
												|| json.data[0].gguarantorPhone == '' ? 'N/A'
												: getFormattedPhoneNumber(json.data[0].gguarantorPhone));
					} else {
						$('#o_guarantor_name').text('N/A');
						$('#o_guarantor_cnic').text('N/A');
						$('#o_guarantor_primary_phone').text('N/A');
						$('#o_guarantor_secondary_phone').text('N/A');
						$('#o_guarantor_relationship').text('N/A');
						$('#o_guarantor_monthly_income')
								.text(formatNumber('0'));
						$('#o_guarantor_profession').text('N/A');

					}
				}
			});
}

function formatNumber(number) {
	number = parseInt(number);

	number = number.toFixed(2) + '';
	x = number.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? ' PKR ' : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function getFormattedPhoneNumber(phone) {
	if (phone != null) {
		formatted = '(+' + phone.substr(0, 2) + ') ' + phone.substr(2, 3) + '-'
				+ phone.substr(5, 7);
	}
	return formatted;
}