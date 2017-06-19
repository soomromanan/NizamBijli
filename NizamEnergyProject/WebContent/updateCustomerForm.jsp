<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<link rel="shortcut icon" href="assets/icons/favicon.png" />
<title>Nizam Energy</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700"
	rel="stylesheet">
<link
	href="assets/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="assets/plugins/bootstrap/css/bootstrap.min.css">
<link href="assets/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/animate.min.css" rel="stylesheet" />
<link href="assets/css/viewer.css" rel="stylesheet" />
<link href="assets/css/style-responsive.min.css" rel="stylesheet" />
<link href="assets/css/theme/default.css" rel="stylesheet" id="theme" />
<link href="assets/plugins/bootstrap-wizard/css/bwizard.min.css"
	rel="stylesheet" id="theme" />
<!-- ================== END BASE CSS STYLE ================== -->

<link href="assets/plugins/bootstrap-datepicker/css/datepicker.css"
	rel="stylesheet" />
<link href="assets/plugins/bootstrap-datepicker/css/datepicker3.css"
	rel="stylesheet" />

<link href="assets/plugins/bootstrap-select/bootstrap-select.min.css"
	rel="stylesheet" />

<link href="assets/plugins/parsley/src/parsley.css" rel="stylesheet" />
<link href="assets/css/style.min.css" rel="stylesheet" />
<link href="assets/css/mystyle.css" rel="stylesheet" />
<link
	href="assets/plugins/bootstrap-multiple-select/css/bootstrap-multiple-select.css"
	rel="stylesheet" />
<link href="assets/plugins/jquery-tag-it/css/jquery.tagit.css"
	rel="stylesheet" />

<style type="text/css">
input[type="number"]::-webkit-inner-spin-button,input[type="number"]::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
}
</style>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script>
	$(document)
			.ready(
					function() {

						$(document)
								.on(
										"keypress",
										'#name,#father,#general_t,#general_th,#general_o,#business_title,#company_title,'
												+ '#designation,#supervisor_name,#expense,#entity,#guarantor,#relation,#assets,#income_0,#income_1,#income_2,#income_3,'
												+ '#income_4,#income_5,#expense_0,#expense_1,#expense_2,#expense_3,#expense_4,#expense_5,#assets_0,#assets_1,#assets_2,'
												+ '#assets_3,#assets_4,#assets_5,#remittances_where,#remittances_driven,#customer_livestock_othertype_id,#remittances_relationship_id,'
												+ '#remittances_profession_id,#customer_rental_from_id,#customer_other_labour_type_id,#relation_with_customer,#who_is_other,#caste',
										function(e) {
											var regex = new RegExp(
													"^[a-zA-Z -]+$");
											var str = String
													.fromCharCode(!e.charCode ? e.which
															: e.charCode);
											console.log(str)
											if (regex.test(str)) {
												return true;
											} else {
												e.preventDefault();
												console
														.log('Please Enter Alphabate');
												return false;
											}
										})
					});

	function countTotalEducationMedicalAmount() {
		var customer_education_amount = 0;
		var customer_medical_recuring_amount = 0;
		var countAmount = 0;

		customer_education_amount = parseInt($(
				'[name="customer_education_amount"]').val())
		customer_medical_recuring_amount = parseInt($(
				'[name="customer_medical_recuring_amount"]').val())
		// 						console.log('customer_education_amount '+customer_education_amount)
		// 						console.log('customer_medical_recuring_amount '+customer_medical_recuring_amount)
		countAmount = parseFloat(customer_education_amount)
				+ parseFloat(customer_medical_recuring_amount);
		$('[name = "customer_education_medical_amount"]').val(countAmount)
		$('.customer_educationmedical_amount').text(
				formatNumber(countAmount) + " PKR");
		// 		console.log('countAmount: ' + countAmount)

	}
	function countTotalAssetsAmount() {

		var customer_primary_sourse_amount = 0
		var customer_mobilebills_amount = 0
		var customer_transport_amount = 0
		var customer_karyana_amount = 0

		customer_primary_sourse_amount = parseInt($(
				'[name="customer_primary_sourse_amount"]').val())
		customer_mobilebills_amount = parseInt($(
				'[name="customer_mobilebills_amount"]').val())
		customer_transport_amount = parseInt($(
				'[name="customer_transport_amount"]').val())
		customer_karyana_amount = parseInt($('[name="customer_karyana_amount"]')
				.val())

		totalAssetsAmount = parseFloat(customer_primary_sourse_amount)
				+ parseFloat(customer_mobilebills_amount)
				+ parseFloat(customer_transport_amount)
				+ parseFloat(customer_karyana_amount);

		$('[name = "customer_total_expense_income"]').val(totalAssetsAmount)
		$('.customer_total_expense_income').val(
				formatNumber(totalAssetsAmount) + " PKR");
	}

	function countTotalLiveAssetsAmount() {

		var customer_land_amount = 0;
		var customer_buffalo_amount = 0;
		var customer_cow_amount = 0;
		var customer_calf_amount = 0;
		var customer_goat_Sheep_amount = 0;
		var customer_bike_amount = 0;
		var customer_car_amount = 0;
		var customer_tractors_amount = 0;
		var customer_tv_amount = 0;
		var customer_fridge_amount = 0;

		var totalLiveStockAmount = 0;

		customer_land_amount = parseInt($('[name="customer_land_amount"]')
				.val())
		customer_buffalo_amount = parseInt($('[name="customer_buffalo_amount"]')
				.val())
		customer_cow_amount = parseInt($('[name="customer_cow_amount"]').val())
		customer_calf_amount = parseInt($('[name="customer_calf_amount"]')
				.val())
		customer_goat_Sheep_amount = parseInt($(
				'[name="customer_goat_Sheep_amount"]').val())
		customer_bike_amount = parseInt($('[name="customer_bike_amount"]')
				.val())
		customer_car_amount = parseInt($('[name="customer_car_amount"]').val())
		customer_tractors_amount = parseInt($(
				'[name="customer_tractors_amount"]').val())
		customer_tv_amount = parseInt($('[name="customer_tv_amount"]').val())
		customer_fridge_amount = parseInt($('[name="customer_fridge_amount"]')
				.val())

		totalLiveStockAmount = parseFloat(customer_buffalo_amount)
				+ parseFloat(customer_land_amount)
				+ parseFloat(customer_cow_amount)
				+ parseFloat(customer_calf_amount)
				+ parseFloat(customer_goat_Sheep_amount)
				+ parseFloat(customer_bike_amount)
				+ parseFloat(customer_car_amount)
				+ parseFloat(customer_tractors_amount)
				+ parseFloat(customer_tv_amount)
				+ parseFloat(customer_fridge_amount);

		$('[name="customer_total_assets_income"]').val(totalLiveStockAmount)
		$('.customer_total_assets_income').val(
				formatNumber(totalLiveStockAmount) + " PKR")
	}

	function formatNumber(number) {
		number = parseFloat(number).toFixed(2) + '';
		x = number.split('.');
		x1 = x[0];
		x2 = x.length > 1 ? '.' + x[1] : '';
		var rgx = /(\d+)(\d{3})/;
		while (rgx.test(x1)) {
			x1 = x1.replace(rgx, '$1' + ',' + '$2');
		}
		return x1;
	}

	$(document)
			.ready(
					function() {
						$("#customer_installment_address_id").attr("disabled",
								true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_sys_installed_place]:checked")
													.val() == "Home") {
												$(
														"#customer_installment_address_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_installment_address_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_course_name_id").attr("disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_training_skill]:checked")
													.val() != "Not Applicable") {
												$("#customer_course_name_id")
														.attr("disabled", false);
											} else {
												$("#customer_course_name_id")
														.attr("disabled", true);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_medical_recuring_amount_id").attr(
								"disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_medical_recuring]:checked")
													.val() == "Yes") {
												$(
														"#customer_medical_recuring_amount_id")
														.attr("disabled", false);
											} else {
												$(
														"#customer_medical_recuring_amount_id")
														.attr("disabled", true);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$('input[name=customer_families_household]').attr(
								"disabled", true);
						// 							$('#customer_families_household_id').prop("disabled", true)
						document
								.getElementById("customer_families_household_id").style.display = 'none';
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_families_type]:checked")
													.val() == "single family") {
												$(
														'input[name=customer_families_household]')
														.attr("disabled", true);
												// 							    	$('#customer_families_household_id').prop("disabled", true)
												document
														.getElementById("customer_families_household_id").style.display = 'none';
											} else {
												$(
														'input[name=customer_families_household]')
														.attr("disabled", false);
												document
														.getElementById("customer_families_household_id").style.display = 'block';
												// 							    	$('#customer_families_household_id').prop("disabled", false)
											}
										});
					});

	$(document).ready(function() {
		$("#customer_other_residence_id").attr("disabled", true);
		$(".radioBtn").click(function() {
			if ($("input[name=customer_residence]:checked").val() == "other") {
				$("#customer_other_residence_id").attr("disabled", false);
			} else {
				$("#customer_other_residence_id").attr("disabled", true);
			}
		});
	});

	$(document).ready(
			function() {

				var type = null;
				var agrGeneralAmount = 0
				var i = 0;
				var agrAmount = 0;
				$("input[name='agriculture_profit_frequency']").click(
						function() {
							type = this.value;
							console.log(i + ' Oncee If Called ' + type)
							if (i == 1) {
								console.log('direct:'
										+ ($('#customer_farming_income_id')
												.val()))
								agrAmount = ($('#customer_farming_income_id')
										.val())
								agrGeneralAmount = agrAmount;
								console.error(agrGeneralAmount);
							}
							if (type === "Once") {
								i = 1;
								var general = $(
										'#get_customer_total_income_yearly')
										.val()

								agrAmount = (agrGeneralAmount) * 1;
								$('#get_customer_total_income_yearly').val(
										agrAmount)
								$('input.customer_farming_income').val(
										agrAmount);
								$('#customer_total_yearly_income').text(
										formatNumber(agrAmount) + ' PKR')
								agrGeneralAmount = agrAmount;
							} else if (type == "Twice") {
								i++;
								var general = $(
										'#get_customer_total_income_yearly')
										.val()
								agrAmount = (agrGeneralAmount) * 2;
								$('#get_customer_total_income_yearly').val(
										agrAmount)
								$('input.customer_farming_income').val(
										agrAmount);
								$('#customer_total_yearly_income').text(
										formatNumber(agrAmount) + ' PKR')
								$("#customer_farming_income_id").attr(
										"disabled", true);
								// 								countTotalYearlyIncome();
							} else if (type == "Three_or_more") {
								i++;
								var general = $(
										'#get_customer_total_income_yearly')
										.val()
								agrAmount = (agrGeneralAmount) * 3;
								$('#get_customer_total_income_yearly').val(
										agrAmount)
								$('input.customer_farming_income').val(
										agrAmount);
								$('#customer_total_yearly_income').text(
										formatNumber(agrAmount) + ' PKR')
								$("#customer_farming_income_id").attr(
										"disabled", true);
								// 								countTotalYearlyIncome();
							} else if (type == "None") {
								console.log('None Called')
								i = 0
								agrGeneralAmount = 0
								agrAmount = 0;
								console.log(agrAmount)
								$('#get_customer_total_income_yearly').val(
										agrAmount)
								$('#customer_farming_income_id').val(agrAmount)
								$('#customer_total_yearly_income').text(
										formatNumber(agrAmount) + ' PKR')
								// 								countTotalYearlyIncome();
							}
							console.log('General Data ' + agrGeneralAmount)
							$('#customer_farming_income_id').val(agrAmount)
						});
			})

	$(document)
			.ready(
					function() {
						$("#customer_farming_income_id").attr("disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=agriculture_profit_frequency]:checked")
													.val() == "None") {
												console
														.log('function working 2')
												$("#customer_farming_income_id")
														.attr("disabled", true);
											} else {
												$("#customer_farming_income_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_livestock_income_id").attr("disabled",
								true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_livestock_type]:checked")
													.val() == "None") {
												$(
														"#customer_livestock_income_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_livestock_income_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_livestock_othertype_id").attr("disabled",
								true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_livestock_type]:checked")
													.val() == "other") {
												$(
														"#customer_livestock_othertype_id")
														.attr("disabled", false);
											} else {
												$(
														"#customer_livestock_othertype_id")
														.attr("disabled", true);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$(
								"#remittances_relationship_id,#customer_remittances_id,#customer_remittances_relationship,#remittances_profession_id")
								.attr("disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_remittances_frequency]:checked")
													.val() == "None") {
												$(
														"#remittances_relationship_id,#customer_remittances_id,#customer_remittances_relationship,#remittances_profession_id")
														.attr("disabled", true);
											} else {
												$(
														"#remittances_relationship_id,#customer_remittances_id,#customer_remittances_relationship,#remittances_profession_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_contributers_amount_id").attr("disabled",
								true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_income_comtributers]:checked")
													.val() == "0") {
												$(
														"#customer_contributers_amount_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_contributers_amount_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$(
								"#customer_rental_profit_income_id,#customer_rental_from_id")
								.attr("disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_rental_frequency]:checked")
													.val() == "None") {
												$(
														"#customer_rental_profit_income_id,#customer_rental_from_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_rental_profit_income_id,#customer_rental_from_id")
														.attr("disabled", false);
											}
										});
					});

	$(document)
			.ready(
					function() {
						$(
								"#customer_labour_id,#customer_labour_amount_id,#customer_labour_in_month_id")
								.attr("disabled", true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_labour_type]:checked")
													.val() == "None") {
												$(
														"#customer_labour_id,#customer_labour_amount_id,#customer_labour_in_month_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_labour_id,#customer_labour_amount_id,#customer_labour_in_month_id")
														.attr("disabled", false);
											}
										});
					});

	$(document).ready(
			function() {
				$("#customer_other_labour_type_id").attr("disabled", true);
				$(".radioBtn").click(
						function() {
							if ($("input[name=customer_labour_type]:checked")
									.val() == "Other") {
								$("#customer_other_labour_type_id").attr(
										"disabled", false);
							} else {
								$("#customer_other_labour_type_id").attr(
										"disabled", true);
							}
						});
			});

	$(document).ready(function() {
		totalAssets = 0;
		var type = null;
		var landAmount = 100000;
		var showTotalAssetAmont = 0;
		$("input[name='customer_land']").click(function() {
			type = this.value;
			console.log('type: ' + type)

			if (type == "5") {
				showTotalAssetAmont = (Number(landAmount * 5))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "10") {
				showTotalAssetAmont = (Number(landAmount * 10))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "20") {
				showTotalAssetAmont = (Number(landAmount * 20))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "30") {
				showTotalAssetAmont = (Number(landAmount * 30))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "50") {
				showTotalAssetAmont = (Number(landAmount * 50))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "60") {
				showTotalAssetAmont = (Number(landAmount * 60))
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			} else if (type == "0") {
				showTotalAssetAmont = 0;
				console.log('showTotalAssetAmont: ' + showTotalAssetAmont)
			}
			$('[name="customer_land_amount"]').val(showTotalAssetAmont)
			countTotalLiveAssetsAmount();
		});
	});

	$(document).ready(function() {

		var type = null;
		var i = 0;
		var totalAssets = 0;
		var buffaloAmount = 100000;
		$("input[name='customer_bbuffalo']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "2") {
				showTotalAssetAmont = (Number(buffaloAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(buffaloAmount * 5))
			} else if (type == "8") {
				showTotalAssetAmont = (Number(buffaloAmount * 8))
			} else if (type == "10") {
				showTotalAssetAmont = (Number(buffaloAmount * 10))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}
			$('[name="customer_buffalo_amount"]').val(showTotalAssetAmont)
			//									$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			//									$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {

		var type = null;
		var i = 0;
		var totalAssets = 0;
		var cowAmount = 70000;
		$("input[name='customer_cow']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "2") {
				showTotalAssetAmont = (Number(cowAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(cowAmount * 5))
			} else if (type == "8") {
				showTotalAssetAmont = (Number(cowAmount * 8))
			} else if (type == "10") {
				showTotalAssetAmont = (Number(cowAmount * 10))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}
			$('#customer_cow_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {

		var type = null;
		var i = 0;
		var totalAssets = 0;
		var calfAmount = 20000;
		$("input[name='customer_calf']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "2") {
				showTotalAssetAmont = (Number(calfAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(calfAmount * 5))
			} else if (type == "8") {
				showTotalAssetAmont = (Number(calfAmount * 8))
			} else if (type == "10") {
				showTotalAssetAmont = (Number(calfAmount * 10))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}
			$('#customer_calf_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {
		var type = null;
		var i = 0;
		var totalAssets = 0;
		var goatSheepAmount = 10000;
		$("input[name='customer_goat_sheep']").click(function() {
			type = this.value;
			console.log('type: ' + type)
			var showTotalAssetAmont = 0;
			if (type == "2") {
				showTotalAssetAmont = (Number(goatSheepAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(goatSheepAmount * 5))
			} else if (type == "8") {
				showTotalAssetAmont = (Number(goatSheepAmount * 8))
			} else if (type == "10") {
				showTotalAssetAmont = (Number(goatSheepAmount * 10))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}
			$('#customer_goat_Sheep_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {

		var type = null;
		var i = 0;
		var totalAssets = 0;
		var bikeAmount = 35000;
		$("input[name='customer_bike']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "1") {
				showTotalAssetAmont = (Number(bikeAmount * 1))
			} else if (type == "2") {
				showTotalAssetAmont = (Number(bikeAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(bikeAmount * 5))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}
			$('#customer_bike_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {
		var type = null;
		var i = 0;
		var totalAssets = 0;
		var carAmount = 400000;
		$("input[name='customer_car']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "1") {
				showTotalAssetAmont = (Number(carAmount * 1))
			} else if (type == "2") {
				showTotalAssetAmont = (Number(carAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(carAmount * 5))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}

			$('#customer_car_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {
		var type = null;
		var i = 0;
		var totalAssets = 0;
		var tractorsAmount = 400000;
		$("input[name='customer_tractors']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "1") {
				showTotalAssetAmont = (Number(tractorsAmount * 1))
			} else if (type == "2") {
				showTotalAssetAmont = (Number(tractorsAmount * 2))
			} else if (type == "5") {
				showTotalAssetAmont = (Number(tractorsAmount * 5))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}

			$('#customer_tractors_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {
		var type = null;
		var totalAssets = 0;
		var goatTvAmount = 10000;
		$("input[name='customer_tv']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;
			if (type == "1") {
				showTotalAssetAmont = (Number(goatTvAmount))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}

			$('#customer_tv_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document).ready(function() {
		var type = null;
		var totalAssets = 0;
		var goatTvAmount = 20000;
		$("input[name='customer_fridge']").click(function() {
			type = this.value;
			var showTotalAssetAmont = 0;

			if (type == "1") {
				showTotalAssetAmont = (Number(goatTvAmount))
			} else if (type == "0") {
				showTotalAssetAmont = 0;
			}

			$('#customer_fridge_amount_id').val(showTotalAssetAmont)
			// 														$('#get_customer_total_assets_income').val(showTotalAssetAmont)
			// 														$('#customer_total_assets_income').text(formatNumber(showTotalAssetAmont)+' PKR')
			countTotalLiveAssetsAmount();
		});
	})

	$(document)
			.ready(
					function() {
						$('input[name=customer_sourceof_electricity]').attr(
								"disabled", false);
						document
								.getElementById("customer_sourceof_electricity_id").style.display = 'block';
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_Connectivity]:checked")
													.val() == "0") {
												$(
														'input[name=customer_sourceof_electricity]')
														.attr("disabled", false);
												document
														.getElementById("customer_sourceof_electricity_id").style.display = 'block';
											} else {
												$(
														'input[name=customer_sourceof_electricity]')
														.attr("disabled", true);
												document
														.getElementById("customer_sourceof_electricity_id").style.display = 'none';
											}
										});
					});

	$(document)
			.ready(
					function() {
						$("#customer_transport_amount_id").attr("disabled",
								true);
						$(".radioBtn")
								.click(
										function() {
											if ($(
													"input[name=customer_transport_mode]:checked")
													.val() == "None") {
												$(
														"#customer_transport_amount_id")
														.attr("disabled", true);
											} else {
												$(
														"#customer_transport_amount_id")
														.attr("disabled", false);
											}
										});
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
	<!-- end #page-loader -->

	<!-- begin #page-container -->
	<div id="page-container"
		class="fade page-without-sidebar page-header-fixed">
		<!-- 	page-without-sidebar page-header-fixed -->
		<!-- begin #header -->
		<%@include file="/superAdminHeader.jsp"%>
		<!-- end #header -->


		<!-- begin #sidebar -->

		<!-- end #sidebar -->


		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->

			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Customer Form</h1>
			<!-- end page-header -->
			<!-- begin row -->

			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<!-- begin panel -->
					<div class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn"></div>
							<h4 class="panel-title">Customer Addition Form</h4>
						</div>
						<div class="panel-body f-s-13">
							<div id="wizard">
								<ol>
									<li>Personal Information</li>
									<li>Income Details</li>
									<li>Assets, Expenses & Liabilities</li>
									<li>Guarantor Information</li>
									<li>Rental Options</li>
								</ol>
								<!-- =============================Personal Information ==================== -->
								<!-- begin wizard step-1 -->
								<div class="wizard-step-1"
									data-customerid="<%=request.getParameter("customerid")%>"
									data-id="<%=request.getParameter("id")%>">
									<form class="form-horizontal parsley" data-eligibilityid
										id="add_info">
										<fieldset>
											<div class="form-group has-feedback" id="district">
												<label class="col-md-4 control-label"> District *</label>
												<div class="col-md-5">
													<select class="form-control selectpicker"
														id="select_customer_district" data-form=".district"
														data-live-search="true" data-size="5" name="district"
														data-parsley-trigger="blur" data-style="btn-white">
														<option value="" selected>Select Nizam Dost</option>
														<option class="divider2">
														<option>
													</select>
												</div>
											</div>
											<div class="form-group has-feedback" id="customer_city">
												<label class="control-label col-md-4">Select Tehsil,
													District *</label>
												<div class="col-md-5">
													<select class="form-control selectpicker"
														id="select_customer_city" name="customer_city"
														data-size="5" data-live-search="true"
														data-style="btn-white" data-parsley-trigger="blur"
														data-none-selected-text="Select Tehsil" required>
														<option value="" selected>Select Tehsil</option>
														<option class="divider2">
														<option>
													</select>
												</div>
											</div>
											<div class="form-group has-feedback"
												id="customer_vle_guarantor">
												<label class="col-md-4 control-label">Nizam Dost, FO
													*</label>
												<div class="col-md-5">
													<select class="form-control selectpicker"
														id="select_customer_vle"
														data-form=".customer_vle_guarantor"
														data-live-search="true" data-size="5"
														name="customer_vle_guarantor" data-parsley-trigger="blur"
														data-style="btn-white">
														<option value="" selected>Select Nizam Dost</option>
														<option class="divider2">
														<option>
													</select>
												</div>
											</div>

											<div class="form-group has-feedback" id="customer_provinces">
												<label class="col-md-4 control-label radio"
													data-name="customer_provinces">Provinces *</label>
												<div class="col-md-8">
													<label class="radio-inline"> <input type="radio"
														name="customer_provinces" id="customer_provinces"
														value="Punjab" checked="checked"> Punjab
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_provinces" id="customer_provinces"
														value="Sindh"> Sindh
													</label><label class="radio-inline"> <input type="radio"
														name="customer_provinces" id="customer_provinces"
														value="Balochistan"> Balochistan
													</label><label class="radio-inline"> <input type="radio"
														name="customer_provinces" id="customer_provinces"
														value="KPK"> KPK
													</label><label class="radio-inline"> <input type="radio"
														name="customer_provinces" id="customer_provinces"
														value="GB"> GB
													</label>
												</div>
											</div>

											<div class="form-group has-feedback" id="customer_address">
												<label class="col-md-4 control-label">
													Village/Town/Other Information </label>
												<div class="col-md-5">
													<input type="text" name="customer_address"
														class="form-control"
														placeholder="Village/Town/Other Information"
														data-parsley-trigger="blur" />
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_union_council">
												<label class="col-md-4 control-label">Union Council
												</label>
												<div class="col-md-5">
													<input type="text" class="form-control name"
														name="customer_union_council"
														id="customer_union_council_id" placeholder="Union Council"
														data-parsley-trigger="blur" />
												</div>
											</div>
											<!-- onclick="checkDate()" -->



											<div class="form-group has-feedback"
												id="customer_requested_date">
												<label class="col-md-4 control-label">Requested Date
													*</label>
												<div class="col-md-5">
													<input type="date" class="form-control"
														data-date-format='yyyy-mm-dd'
														name="customer_requested_date" id="requested_date"
														data-parsley-trigger="blur" required />
												</div>
											</div>


											<div class="form-group has-feedback"
												id="customer_sys_installed_place">
												<label class="control-label col-md-4 radio"
													data-name="customer_sys_installed_place">Where will
													the system be installed? </label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_sys_installed_place"
														value="Home" checked="checked"> Home
													</label> <label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_sys_installed_place"
														value="Business"> Business
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_sys_installed_place"
														value="Dera"> Dera
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_sys_installed_place"
														value="Other"> Other
													</label>

												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_installment_address">
												<label class="col-md-3 col-md-offset-1 control-label">In
													case of Business, Dera or Other location, Please specify
													the address (below) </label>
												<div class="col-md-5">
													<input type="text" name="customer_installment_address"
														id="customer_installment_address_id" class="form-control"
														placeholder="Customer Installment Address"
														required="required" />
												</div>
											</div>

										</fieldset>
									</form>
									<div class="row">
										<div class="col-md-5 col-md-offset-4" id="wizard_1_status">

										</div>
									</div>
									<form action="FormController" method="post"
										class="form-horizontal" id="customer_info_form"
										data-customer-form>
										<fieldset>



											<legend>Customer Info</legend>
											<div class="form-group has-feedback" id="customer_fullname">
												<label class="col-md-4 control-label">Full Name *</label>
												<div class="col-md-5">
													<input type="text" class="form-control name"
														name="customer_fullname" id="name" placeholder="Full Name"
														data-parsley-trigger="blur" required />
												</div>
											</div>
											<div class="form-group has-feedback"
												id="customer_father_name">
												<label class="col-md-4 control-label">Father
													Name/Husband Name *</label>
												<div class="col-md-5">
													<input type="text" class="form-control name"
														name="customer_father_name" id="father"
														placeholder="Father Name" data-parsley-trigger="blur"
														required />
												</div>
											</div>

											<div class="form-group has-feedback" id="customer_gender">
												<label class="col-md-4 control-label">Gender </label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														name="customer_gender" value="Male" checked="checked">
														Male
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_gender" value="Female"> Female
													</label>
												</div>
											</div>

											<div class="form-group has-feedback" id="customer_cnic">
												<label class="col-md-4 control-label">CNIC * </label>
												<div class="col-md-5">
													<input type="text" class="form-control mask cnic"
														name="customer_cnic" id="cnic" placeholder="CNIC"
														onchange="getCnic('cnicMsg')"
														data-parsley-trigger="blur" data-column="customer_cnic"
														data-table="customer" data-type="cnic"
														data-mask="99999-9999999-9" required /> <span
														style="color: red; font-size: 12px;" id="cnicMsg"></span>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_date_of_birth">
												<label class="col-md-4 control-label">Date Of Birth
												</label>
												<div class="col-md-5">
													<input type="date" class="form-control name"
														data-date-format='yyyy-mm-dd'
														name="customer_date_of_birth" id="dob"
														data-parsley-trigger="blur" required />
												</div>
											</div>

											<div class="form-group has-feedback" id="customer_caste">
												<label class="col-md-4 control-label">Caste </label>
												<div class="col-md-5">
													<input type="text" class="form-control name"
														name="customer_caste" id="caste" placeholder="Caste"
														data-parsley-trigger="blur" />
												</div>
											</div>
										</fieldset>
										<fieldset>
											<legend>Contact Details</legend>
											<div class="form-group has-feedback"
												id="customer_primary_phone">
												<label class="col-md-4 control-label">Phone Number
													(primary)*</label>
												<div class="col-md-5">
													<div class="input-group" data-parsley-trigger="change">
														<span class="input-group-addon">92</span> <input
															type="text" class="form-control mask"
															data-mask="9999999999" name="customer_primary_phone"
															placeholder="Phone Number (primary)" required="required" />
													</div>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_secondary_phone">
												<label class="col-md-4 control-label">Phone Number
													(secondary) </label>
												<div class="col-md-5">
													<div class="input-group" data-parsley-trigger="change">
														<span class="input-group-addon">92</span> <input
															type="text" class="form-control phone mask"
															data-mask="9999999999" name="customer_secondary_phone"
															placeholder="Phone Number (secondary)" />
													</div>
												</div>
											</div>
											<div id="dynamic_customer_number"></div>
											<div class="form-group has-feedback"
												id="other_customer_number">
												<div class="col-md-2 col-md-offset-7">
													<button class="btn btn-primary btn-block"
														id="btn_add_other_number" type="button">
														<span class="fa fa-plus"></span> Add Other Number
													</button>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_marital_status">
												<label class="col-md-4 control-label radio"
													data-name="customer_marital_status">Marital Status
												</label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														name="customer_marital_status" value="single"
														checked="checked"> Single
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_marital_status" value="married">
														Married
													</label><label class="radio-inline"> <input type="radio"
														name="customer_marital_status" value="Widows">
														Widow
													</label><label class="radio-inline"> <input type="radio"
														name="customer_marital_status" value="Divorced">
														Divorced
													</label>
												</div>
											</div>


											<div class="form-group has-feedback" id="customer_education">
												<label class="control-label col-md-4">Education </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="1" checked="checked">Never Attended School
													</label> <label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="2">(1-5) Primary
													</label><label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="3">(6-8) Middle
													</label><label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="4">(9-10) Secondary
													</label><label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="5">(11-12) Higher
													</label><label class="radio-inline" style="margin-left: 0px;">
														<input type="radio" id="customer_education"
														name="customer_education" value="6">(12-14)
														Diploma/BA/BSc
													</label><label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="7">(14-16) Graduate
													</label><label class="radio-inline"> <input type="radio"
														id="customer_education" name="customer_education"
														value="8">(16+) Post Graduate
													</label>

												</div>
											</div>
											<div class="form-group has-feedback" id="customer_disability">
												<label class="control-label col-md-4 radio"
													data-name="customer_disibility">Disability </label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														name="customer_disibility" value="No" checked="checked">
														No
													</label><label class="radio-inline"> <input type="radio"
														name="customer_disibility" value="Yes"> Yes
													</label>
												</div>
											</div>



											<div class="form-group has-feedback">
												<label class="control-label col-md-4 radio"
													data-name="customer_training_skill">Technical/Vocational
													Training </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_training_skill"
														id="customer_training_skill_" class="radioBtn"
														checked="checked" value="Not Applicable"> Not
														Applicable
													</label><label class="radio-inline"> <input type="radio"
														name="customer_training_skill" class="radioBtn"
														value="Local Apprentice"> Local Apprentice
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_training_skill" class="radioBtn"
														value="Short Course"> Shrot Course
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_training_skill" class="radioBtn"
														value="Diploma"> Diploma (Certificate)
													</label>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_course_name2">
												<label class="col-md-4 control-label">Other
													Technical/Vocational Training </label>
												<div class="col-md-5">
													<input type="text" name="customer_course_name"
														id="customer_course_name_id" class="form-control"
														placeholder="Other Technical/Vocational Training Name"
														data-parsley-trigger="blur" />
												</div>
											</div>

										</fieldset>

										<fieldset>
											<legend>Household</legend>

											<div class="row">
												<div class="col-md-6"></div>
												<div class="col-md-6  form-group">
													<label class="col-md-offset-1 col-md-5 control-label"
														style="padding-top: 1px;"><b>Educational/Medical
															Amount</b> </label>
													<div class="col-md-3">
														<h4 class="m-0 inline"
															id="customer_educationmedical_amount">
															<span class="customer_educationmedical_amount">0
																PKR</span>
														</h4>
														<input type="hidden" class="form-control"
															id="customer_education_medical_amount"
															name="customer_education_medical_amount">
													</div>
												</div>
											</div>
											<div class="form-group has-feedback"
												id="customer_head_faimly">
												<label class="control-label col-md-4 radio"
													data-name="customer_head_faimly">Head of Faimly </label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														name="customer_head_faimly" value="No" checked="checked">
														No
													</label><label class="radio-inline"> <input type="radio"
														name="customer_head_faimly" value="Yes"> Yes
													</label>
												</div>
											</div>


											<div class="form-group has-feedback"
												id="customer_total_household_members">
												<label class="control-label col-md-4 radio"
													data-name="customer_total_household_members">Total
													Household Members </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="0-2"
														checked="checked"> 0-2
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="3-4">
														3-4
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="5-6">
														5-6
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="7-8">
														7-8
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="9-10">
														9-10
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_total_household_members" value="11+">
														11+
													</label>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_dependants_members">
												<label class="control-label col-md-4 radio"
													data-name="customer_dependants_members">Household
													Members (Under 18) </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_dependants_members" value="0-2"
														checked="checked"> 0-2
													</label><label class="radio-inline"> <input type="radio"
														name="customer_dependants_members" value="3-4">
														3-4
													</label><label class="radio-inline"> <input type="radio"
														name="customer_dependants_members" value="5-6">
														5-6
													</label><label class="radio-inline"> <input type="radio"
														name="customer_dependants_members" value="7+"> 7+
													</label>
												</div>
											</div>
											<div class="form-group has-feedback" id="number_of_children">
												<label class="control-label col-md-4 radio"
													data-name="number_of_children">Number of Childrens
													going to School</label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="number_of_children" value="0" checked="checked">
														0
													</label><label class="radio-inline"> <input type="radio"
														name="number_of_children" value="1-2" checked="checked">
														1-2
													</label><label class="radio-inline"> <input type="radio"
														name="number_of_children" value="3-4"> 3-4
													</label><label class="radio-inline"> <input type="radio"
														name="number_of_children" value="5-6"> 5-6
													</label><label class="radio-inline"> <input type="radio"
														name="number_of_children" value="7+"> 7+
													</label>
												</div>
											</div>

											<div class="form-group has-feedback">
												<label class="col-md-offset-1 col-md-3 control-label">Education
													Expense Amount</label>
												<div class="col-md-5">
													<input
														class="form-control education-income-source mask currency name"
														name="customer_education_amount" data-mask="9999999"
														placeholder="Education (amount) " value="0"
														data-parsley-trigger="blur" required>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_total_household_members">
												<label class="control-label col-md-4 radio"
													data-name="customer_total_adult_members">Total
													Household Members (Adult & Above 60) </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="0-2"
														checked="checked"> 0-2
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="3-4">
														3-4
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="5-6">
														5-6
													</label><label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="7-8">
														7-8
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="9-10">
														9-10
													</label> <label class="radio-inline"> <input type="radio"
														name="customer_total_adult_members" value="11+">
														11+
													</label>
												</div>
											</div>


											<div class="form-group has-feedback"
												id="customer_medical_recuring">
												<label class="control-label col-md-4 radio"
													data-name="customer_medical_recuring">Any Medical
													Recuring Expense</label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_medical_recuring"
														value="No" id="customer_medical_recuring_false"
														checked="checked"> No
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_medical_recuring"
														value="Yes" id="customer_medical_recuring_true">
														Yes
													</label>

												</div>
											</div>

											<div class="form-group has-feedback">
												<label class="col-md-offset-1 col-md-3 control-label">Amount
													Medical Recuring Expense </label>
												<div class="col-md-5">
													<input type="text" name="customer_medical_recuring_amount"
														id="customer_medical_recuring_amount_id" value="0"
														class="form-control education-income-source mask currency"
														placeholder="Customer Recuring Expense (amount)" />
												</div>
											</div>

											<div class="form-group has-feedback">
												<label class="control-label col-md-4 radio"
													data-name="customer_families_type"><b>Family
														Type </b></label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_type"
														value="single family" checked="checked"> Single
														family
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_type"
														value="joint family"> Joint family
													</label>
												</div>
											</div>


											<div class="form-group has-feedback"
												id="customer_families_household_id">
												<label class="control-label col-md-4 radio"
													data-name="customer_families_household">Number of
													Families living in Household </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_household"
														value="2" checked="checked"> 2
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_household"
														value="3"> 3
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_household"
														value="4-5"> 4-5
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_household"
														value="6-7"> 6-7
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_families_household"
														value="8+"> 8+
													</label>
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_roomoccupied_household">
												<label class="control-label col-md-4 radio"
													data-name="customer_roomoccupied_household">Room
													Occupied By Household (Only bedroom & living room) </label>
												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_roomoccupied_household" value="0-2"
														checked="checked"> 0-2
													</label><label class="radio-inline"> <input type="radio"
														name="customer_roomoccupied_household" value="3-4">
														3-4
													</label><label class="radio-inline"> <input type="radio"
														name="customer_roomoccupied_household" value="5-6">
														5-6
													</label><label class="radio-inline"> <input type="radio"
														name="customer_roomoccupied_household" value="7+">
														7+
													</label>
												</div>
											</div>


											<div class="form-group has-feedback" id="customer_residence">
												<label class="control-label col-md-4 radio"
													data-name="customer_residence">Residence </label>
												<div class="col-md-5">
													<label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_residence" value="owned"
														id="customer_residence_"> Owned
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_residence"
														value="family owned" id="customer_residence_">
														Family owned
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_residence" value="rented"
														id="customer_residence_"> Rented
													</label><label class="radio-inline"> <input type="radio"
														class="radioBtn" name="customer_residence"
														id="customer_other_residence_" value="other">
														Other
													</label>

												</div>
											</div>

											<div class="form-group has-feedback " id="other_residence_"
												id="customer_other_residence">
												<label class="col-md-4 control-label">Other
													Residence </label>
												<div class="col-md-5">
													<input type="text" class="form-control name"
														name="customer_other_residence"
														id="customer_other_residence_id"
														placeholder="Insert Other Residence"
														data-parsley-trigger="blur" />
												</div>
											</div>

											<div class="form-group has-feedback"
												id="customer_residence_period">
												<label class="col-md-4 control-label">Residence
													Since (number of years)</label>

												<div class="col-md-6">
													<label class="radio-inline"> <input type="radio"
														name="customer_residence_period" value="0-5"
														checked="checked"> 0-5
													</label><label class="radio-inline"> <input type="radio"
														name="customer_residence_period" value="5-10">
														5-10
													</label><label class="radio-inline"> <input type="radio"
														name="customer_residence_period" value="10-20">
														10-20
													</label><label class="radio-inline"> <input type="radio"
														name="customer_residence_period" value="20-30">
														20-30
													</label><label class="radio-inline"> <input type="radio"
														name="customer_residence_period" value="30+"> 30+
													</label>
												</div>
											</div>
										</fieldset>

										<!-- <fieldset>
											<legend>Other Information </legend>
											
											<div id="dynamic_text_field_formate"></div>
													<div class="form-group has-feedback"
														id="other_text_field_formate">
														<div class="col-md-3 col-md-offset-6">
															<button class="btn btn-primary btn-block"
																id="btn_add_text_field_formate" type="button">
																<span class="fa fa-plus"></span> Add General Field in Text Formate 
															</button>
														</div>
													</div>
										
											<div id="dynamic_digit_field_formate"></div>
													<div class="form-group has-feedback"
														id="other_digit_field_formate">
														<div class="col-md-3 col-md-offset-6">
															<button class="btn btn-primary btn-block"
																id="btn_add_digit_field_formate" type="button">
																<span class="fa fa-plus"></span> Add General Field in Digit Formate 
															</button>
														</div>
													</div>
											
											<div class="col-md-3 col-md-offset-6">
												<input class="btn btn-primary pull-right" type="submit" value="Save">
											</div>
										</fieldset> -->
									</form>
								</div>
								<!-- end wizard step-1 -->
								<!-- =============================Personal Information END ==================== -->


								<!-- =============================Income Detail Starts ==================== -->
								<!-- begin wizard step-2 -->
								<div class="wizard-step-2">
									<div class="row">
										<div class="col-md-5 col-md-offset-4" id="wizard_2_status">

										</div>
									</div>
									<form class="form-horizontal well m-t-0"
										id="customer_income_source_detail" data-customerincomesource>
										<fieldset>
											<legend>Total Monthly Income</legend>


											<div class="row">
												<div class="col-md-6"></div>

												<div class="col-md-6">
													<div class="form-group">
														<label class="col-md-5 control-label"
															style="padding-top: 1px;"><b>Total Monthly
																Income</b> </label>
														<div class="col-md-3">

															<h4 class="m-0 inline" id="customer_total_income">
																0<span class=""> PKR</span>
															</h4>
															<input type="hidden" class="form-control"
																id="get_customer_total_income"
																name="customer_total_income_name">
														</div>
													</div>
												</div>
											</div>


											<script type="text/javascript">
												function countMonthlyAmount() {
												}

												function countIrregularlyAmount() {
												}
											</script>

											<div class="row">
												<div class="col-md-6"></div>

												<div class="col-md-6">
													<div class="form-group">
														<label class="col-md-5 control-label"
															style="padding-top: 1px;"><b>Total
																Irregularly Income</b> </label>
														<div class="col-md-3">

															<h4 class="m-0 inline" id="customer_total_yearly_income">
																0<span class=""> PKR</span>
															</h4>
															<input type="hidden" class="form-control"
																id="get_customer_total_income_yearly"
																name="customer_total_irregularly_income_name">
														</div>
													</div>
												</div>

											</div>


											<div class="row">
												<div class="col-md-6"></div>

												<div class="col-md-6">
													<div class="form-group">
														<label class="col-md-5 control-label"
															style="padding-top: 1px;"><b>Annualized
																Income</b> </label>
														<div class="col-md-3">

															<h4 class="m-0 inline" id="customer_net_monthly_income">
																0<span class=""> PKR</span>
															</h4>
															<input type="hidden" class="form-control"
																id="get_customer_net_monthly_income"
																name="customer_net_monthly_income_name">
														</div>
													</div>
												</div>

											</div>



											<div class="col-md-offset-1 col-md-6">
												<ul class="nav nav-pills">
													<li class="active"><a href="#nav-pills-tab-1"
														data-toggle="tab">Agriculture</a></li>
													<li><a href="#nav-pills-tab-2" data-toggle="tab">Live
															Stock</a></li>
													<li><a href="#nav-pills-tab-3" data-toggle="tab">Remittances</a></li>
													<li><a href="#nav-pills-tab-4" data-toggle="tab">Contributers/Rental</a></li>
													<li><a href="#nav-pills-tab-5" data-toggle="tab">Labour</a></li>
												</ul>
											</div>

											<div class="tab-content">
												<div class="tab-pane fade active in" id="nav-pills-tab-1">

													<div class="row">



														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="agriculture_profit_frequency">
																<label class="control-label col-md-4 radio"
																	data-name="agriculture_profit_frequency">Agri
																	Income Frequency</label>
																<div class="col-md-8">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="agriculture_profit_frequency"
																		id="agriculture_profit_frequency_id" value="None"
																		checked="checked"> None
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="agriculture_profit_frequency"
																		id="agriculture_profit_frequency_id" value="Once">
																		Once
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="agriculture_profit_frequency"
																		id="agriculture_profit_frequency_id" value="Twice">
																		Twice
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="agriculture_profit_frequency"
																		id="agriculture_profit_frequency_id"
																		value="Three_or_more"> Three or more
																	</label>

																</div>
															</div>
														</div>



														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_farming_income">
																<label class="col-md-4 col-md-offset-1 control-label">Agriculture
																	Income (Profit)</label>
																<div class="col-md-6">
																	<input type="text"
																		class="form-control yearly_income-source mask currency"
																		placeholder="Agriculture Income (amount)"
																		name="customer_farming_income"
																		id="customer_farming_income_id" data-mask="9999999"
																		required>
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="tab-pane fade " id="nav-pills-tab-2">
													<div class="row">



														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_livestock_type">
																<label class="control-label col-md-4 radio"
																	data-name="customer_livestock_type">Type</label>
																<div class="col-md-8">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_livestock_type" value="None"
																		checked="checked"> None
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_livestock_type" value="Rearing Animal">
																		Rearing Animal
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_livestock_type" value="Milking Animal">
																		Milking Animal
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_livestock_type" value="other">
																		other
																	</label>

																</div>
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_livestock_income">
																<label class="col-md-4 col-md-offset-1 control-label">Livestock
																	(Profit)</label>
																<div class="col-md-6">
																	<input
																		class="form-control yearly_income-source mask currency"
																		id="customer_livestock_income_id"
																		name="customer_livestock_income" value="0"
																		data-mask="9999999" required>
																</div>
															</div>
														</div>
													</div>



													<div class="row">
														<div class="col-md-6"></div>
														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_remittances_driven">
																<label class="col-md-4 col-md-offset-1 control-label">In
																	case of other livestock Except Rearing Animal and
																	Milking Animal, Please specify here </label>
																<div class="col-md-6">
																	<input type="text" name="customer_livestock_othertype"
																		id="customer_livestock_othertype_id"
																		class="form-control name"
																		placeholder="Type Other Livestock" required="required" />
																</div>

															</div>
														</div>
													</div>
												</div>

												<div class="tab-pane fade" id="nav-pills-tab-3">
													<div class="row">


														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_remittances_frequency">
																<label class="control-label col-md-4 radio"
																	class="radioBtn"
																	data-name="customer_remittances_frequency">Frequency</label>
																<div class="col-md-8">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_remittances_frequency" value="None"
																		checked="checked"> None
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_remittances_frequency" value="Monthly">
																		Monthly
																	</label>
																	<!-- <label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_remittances_frequency" value="Quartely"> 
																Quartely
															</label><label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_remittances_frequency" value="Half-Yearly"> 
																Half-Yearly
															</label><label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_remittances_frequency" value="Yearly"> 
																Yearly
															</label> -->
																	<label class="radio-inline" style="margin-left: 0;">
																		<input type="radio" class="radioBtn"
																		name="customer_remittances_frequency"
																		value="Irregularly"> Irregularly
																	</label>

																</div>
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_remittances">
																<label class="col-md-4 col-md-offset-1 control-label">Remittances</label>
																<div class="col-md-6">
																	<input class="form-control income-source mask currency"
																		id="customer_remittances_id"
																		name="customer_remittances" data-mask="9999999"
																		placeholder="amount">
																</div>
															</div>
														</div>

													</div>

													<div class="row">
														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_remittances_where">
																<label class="col-md-4 control-label">Relationship</label>
																<div class="col-md-6">
																	<input type="text"
																		name="customer_remittances_relationship"
																		id="remittances_relationship_id"
																		class="form-control name" placeholder="Relationship"
																		required="required" />
																</div>

															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_remittances_driven">
																<label class="col-md-4 col-md-offset-1 control-label">Profession</label>
																<div class="col-md-6">
																	<input type="text"
																		name="customer_remittances_profession"
																		id="remittances_profession_id"
																		class="form-control name"
																		placeholder="Type of Profession" required="required" />
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="tab-pane fade" id="nav-pills-tab-4">

													<div class="row">

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_income_comtributers">
																<label class="control-label col-md-4 radio"
																	data-name="customer_income_comtributers">Other
																	Income Contributers </label>
																<div class="col-md-5">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_income_comtributers" value="0"
																		checked="checked"> 0
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_income_comtributers" value="1">
																		1
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_income_comtributers" value="2">
																		2
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_income_comtributers" value="3">
																		3
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_income_comtributers" value="4+">
																		4+
																	</label>
																</div>
															</div>
														</div>


														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_contributers_amount">
																<label class="col-md-4 col-md-offset-1 control-label">Other
																	Total Contributers Amount</label>
																<div class="col-md-6">
																	<input
																		class="form-control yearly_income-source mask currency"
																		id="customer_contributers_amount_id"
																		name="customer_contributers_amount"
																		data-mask="9999999"
																		placeholder="Other Total Contributers Amount" required>
																</div>
															</div>
														</div>

													</div>

													<div class="row">

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_rental_frequency">
																<label class="control-label col-md-4 radio"
																	class="radioBtn" data-name="customer_rental_frequency">Rental
																	Frequency</label>
																<div class="col-md-8">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_rental_frequency" value="None"
																		checked="checked"> None
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_rental_frequency" value="Monthly">
																		Monthly
																	</label>
																	<!-- <label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_rental_frequency" value="Quartely"> 
																Quartely
															</label><label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_rental_frequency" value="Half-Yearly"> 
																Half-Yearly
															</label><label class="radio-inline"> <input type="radio" class="radioBtn"
																name="customer_rental_frequency" value="Yearly"> 
																Yearly
															</label> -->
																	<label class="radio-inline" style="margin-left: 0;">
																		<input type="radio" class="radioBtn"
																		name="customer_rental_frequency" value="Irregularly">
																		Irregularly
																	</label>

																</div>
															</div>


														</div>
														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_rental_profit_income">
																<label class="col-md-4 col-md-offset-1 control-label">Rental
																	Income/Profit on Investment </label>
																<div class="col-md-6">
																	<input class="form-control income-source mask currency"
																		id="customer_rental_profit_income_id"
																		name="customer_rental_profit_income"
																		data-mask="9999999"
																		placeholder="Rental Income/Profit on Investment (amount)">
																</div>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_rental_from">
																<label class="col-md-4 control-label">From</label>
																<div class="col-md-6">
																	<input type="text" name="customer_rental_from"
																		id="customer_rental_from_id" class="form-control name"
																		placeholder="Where" required="required" />
																</div>
															</div>
														</div>

														<div class="col-md-6"></div>
													</div>
												</div>

												<div class="tab-pane fade" id="nav-pills-tab-5">

													<div class="row">
														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_rental_profit_income">
																<label class="control-label col-md-4 radio"
																	style="padding-left: 0px;"
																	data-name="customer_labour_type">Type of Labour</label>
																<div class="col-md-8">
																	<label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_labour_type" value="None"
																		checked="checked"> None
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_labour_type" value="Labourer">
																		Labourer
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_labour_type" value="Electrician">
																		Electrician
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_labour_type" value="On Shop">
																		On shop
																	</label><label class="radio-inline"> <input
																		type="radio" class="radioBtn"
																		name="customer_labour_type" value="Carpenter">
																		Carpenter
																	</label><label class="radio-inline" style="margin-left: 0;">
																		<input type="radio" class="radioBtn"
																		name="customer_labour_type" value="Constructor">
																		Constructor
																	</label><label class="radio-inline" style="margin-left: 3px;">
																		<input type="radio" class="radioBtn"
																		name="customer_labour_type" value="Other">
																		Other
																	</label>
																</div>
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group has-feedback" id="customer_labour">
																<label class="col-md-4 col-md-offset-1 control-label">Daily
																	Amount </label>
																<div class="col-md-6">
																	<input class="form-control income-source mask currency"
																		id="customer_labour_amount_id"
																		name="customer_labour_amount" data-mask="9999999"
																		placeholder="Daily Amount">
																</div>
															</div>
														</div>
													</div>


													<div class="row">

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_other_labour_type">
																<label class="col-md-4 control-label">Other Type</label>
																<div class="col-md-6">
																	<input type="text" name="customer_other_labour_type"
																		id="customer_other_labour_type_id"
																		class="form-control name"
																		placeholder="Other Labour Type" required="required" />
																</div>
															</div>
														</div>

														<div class="col-md-6">
															<div class="form-group has-feedback"
																id="customer_labour_in_month">
																<label class="col-md-4 col-md-offset-1 control-label">No
																	of Labour Days in Month </label>
																<div class="col-md-6">
																	<input class="form-control labour-source mask currency"
																		id="customer_labour_in_month_id"
																		name="customer_labour_in_month" data-mask="9999999"
																		placeholder="No of Labour Days in Month">
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="col-md-6">
													<!-- <div class="form-group">
														<label class="col-md-3 control-label">Other Income</label>
													</div> -->
													<div id="other_income_source"></div>
													<div class="form-group has-feedback"
														id="customer_other_income">
														<div class="col-md-6 col-md-offset-4">
															<button class="btn btn-primary btn-block"
																id="btn_add_income_source" type="button">
																<span class="fa fa-plus"></span> Add Other Income Source
															</button>
														</div>
													</div>
												</div>
												<div class="col-md-6"></div>
											</div>

											<div class="row">

												<div class="col-md-6">
													<div class="form-group has-feedback"
														id="customer_business_income">
														<label class="col-md-4 control-label">Business
															Income (monthly)</label>
														<div class="col-md-6">
															<input class="form-control income-source mask currency"
																name="customer_business_income" data-mask="9999999"
																required>
														</div>
													</div>
												</div>

												<div class="col-md-6">
													<div class="form-group has-feedback"
														id="customer_salary_or_pension">
														<label class="col-md-4 col-md-offset-1  control-label">Salary/Pension</label>
														<div class="col-md-6">
															<input class="form-control income-source mask currency"
																name="customer_salary_or_pension" data-mask="9999999"
																required>
														</div>
													</div>
												</div>


											</div>
										</fieldset>
									</form>
									<div class="row m-0">
										<div class="col-md-6 p-t-10 p-r-5 p-l-0 p-b-10">
											<div class="row">
												<div class="col-md-12" id="wizard_2_status_bd"></div>
											</div>
											<form action="#" class="form-horizontal well"
												id="customer_business_detail" data-customer-businessid
												data-form="0">
												<fieldset>
													<legend>Business Detail</legend>
													<div class="row">
														<div class="col-md-12 status"></div>
													</div>
													<div class="form-group has-feedback"
														id="customer_business_title">
														<label class="col-md-4 control-label">Business/Shop
															Name </label>
														<div class="col-md-5">
															<input type="text" name="customer_business_title"
																id="business_title" class="form-control name"
																placeholder="Business Title" required="required" />
														</div>
													</div>

													<div class="form-group has-feedback"
														id="customer_business_type">
														<label class="col-md-4 control-label">Business
															Type </label>
														<div class="col-md-5">
															<input type="text" name="customer_business_type"
																class="form-control name" placeholder="Business Type"
																required="required" />
														</div>
													</div>

													<div class="form-group has-feedback"
														id="customer_business_time_period">
														<label class="col-md-4 control-label">Years in
															Business </label>
														<div class="col-md-5">
															<input type="text" name="customer_business_time_period"
																class="form-control mask" data-mask="999"
																placeholder="Years" data-parsley-type="number"
																required="required" />
														</div>
													</div>
													<!-- <div class="form-group has-feedback"
														id="customer_business_telephone">
														<label class="col-md-4 control-label">Telephone </label>
														<div class="col-md-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	type="text" name="customer_business_telephone"
																	class="form-control mask" data-mask="9999999999" />
															</div>
														</div>
													</div> -->
													<!-- <div class="form-group has-feedback"
														id="customer_business_address">
														<label class="col-md-4 control-label">Address </label>
														<div class="col-md-5">
															<input type="text" name="customer_business_address"
																class="form-control" placeholder="Address"
																required="required" />
														</div>
													</div> -->
													<!-- <div class="form-group has-feedback"
														id="customer_business_comment">
														<label class="col-md-4 control-label">Comments</label>
														<div class="col-md-5">
															<input type="text" name="customer_business_comment"
																class="form-control " placeholder="Comments"
																required="required" />
														</div>
													</div> -->
													<!-- <div class="col-md-3 col-md-offset-6">
														<input class="btn btn-primary pull-right" type="submit" value="Save">
													</div> -->
												</fieldset>
											</form>
										</div>
										<div class="col-md-6 p-t-10 p-r-0 p-l-5 p-b-10">
											<div class="row">
												<div class="col-md-12" id="wizard_2_status_ed"></div>
											</div>
											<form class="form-horizontal well" data-customer-employeeid
												id="customer_employement_detail" data-form="1">
												<legend>Employment Detail</legend>
												<fieldset>
													<div class="row">
														<div class="col-md-12 status"></div>
													</div>
													<div class="form-group has-feedback"
														id="customer_employement_company_title">
														<label class="col-md-4 control-label">Company/Org.
															Name </label>
														<div class="col-md-5">
															<input type="text"
																name="customer_employement_company_name"
																id="company_title" class="form-control name"
																placeholder="Company/Organization" required="required" />
														</div>
													</div>
													<div class="form-group has-feedback"
														id="customer_employement_designation">
														<label class="col-md-4 control-label">Designation
														</label>
														<div class="col-md-5">
															<input type="text"
																name="customer_employement_designation" id="designation"
																class="form-control name" placeholder="Designation"
																required="required" />
														</div>
													</div>
													<div class="form-group has-feedback"
														id="customer_employement_job_period">
														<label class="col-md-4 control-label">Length of
															Job in (Years) </label>
														<div class="col-md-5">
															<input type="text" name="customer_employement_job_period"
																class="form-control mask" data-mask="99"
																placeholder="Length of Job" required="required" />
														</div>
													</div>
													<!-- <div class="form-group has-feedback"
														id="customer_employement_office_phone">
														<label class="col-md-4 control-label">Office
															Telephone </label>
														<div class="col-md-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	type="text" name="customer_employement_office_phone"
																	class="form-control mask" data-mask="9999999999"
																	placeholder="Office Telephone" />
															</div>
														</div>
													</div> -->
													<!-- <div class="form-group has-feedback"
														id="customer_employement_supervisor_name">
														<label class="col-md-4 control-label">Supervisor
															Name </label>
														<div class="col-md-5">
															<input type="text"
																name="customer_employement_supervisor_name"
																id="supervisor_name" class="form-control"
																placeholder="Supervisor Name" required="required" />
														</div>
													</div> -->
													<!-- <div class="form-group has-feedback"
														id="customer_employement_address">
														<label class="col-md-4 control-label">Address </label>
														<div class="col-md-5">
															<input type="text" name="customer_employement_address"
																class="form-control" placeholder="Current Address"
																required="required" />
														</div>
													</div> -->
													<!-- <div class="col-md-3 col-md-offset-6"> -->
													<!-- <input class="btn btn-primary pull-right" type="submit" value="Save"> -->
													<!-- </div> -->
												</fieldset>
											</form>
										</div>
									</div>
								</div>
								<!-- end wizard step-2 -->
								<!-- =============================Income Detail End ==================== -->

								<!-- =============================General Information Starts ==================== -->
								<!-- begin wizard step-3 -->
								<div class="wizard-step-3">
									<div class="row m-t-10 m-b-10 well m-t-0">
										<div class="col-md-12">
											<form action="" class="form-horizontal "
												id="customer_monthly_assets_detail"
												data-customer-live-assets>
												<legend>Customer Assets</legend>
												<div class="row">
													<div class="col-md-4"></div>
													<div class="col-md-5">
														<div class="form-group">
															<label class="col-md-5 control-label"
																style="padding-top: 12px;"><b>Total Assets
																	of Customer </b> </label>
															<div class="col-md-7">
																<h4>
																	<input class="m-0 inline customer_total_assets_income"
																		style="border-color: transparent;"
																		id="get_input_customer_total_assets_income" readonly>
																</h4>
																<input type="hidden" class="form-control"
																	id="get_customer_total_assets_income"
																	name="customer_total_assets_income">
															</div>
														</div>
													</div>
												</div>


												<div class="row">

													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_land_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Land in acres (amount)</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_land_amount"
																	data-mask="9999999" id="customer_land_amount_id"
																	placeholder="Land (in acres) amount" required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group" id="customer_land">
															<label class="control-label col-md-3  radio"
																data-name="customer_land">Land (in acres)</label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="5">
																	1-5
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="10">
																	5-10
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="20">
																	10-20
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="30">
																	20-30
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="50">
																	30-50
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_land" value="60">
																	50+
																</label>

															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_buffalo_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Total Buffalo amount</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_buffalo_amount"
																	data-mask="9999999" id="customer_buffalo_amount_id"
																	placeholder="Total Buffalo (amount)" required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">

														<div class="form-group has-feedback"
															id="customer_bbuffalo">
															<label class="control-label col-md-3  radio"
																data-name="customer_bbuffalo">Buffalo </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bbuffalo" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bbuffalo" value="2">
																	1-2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bbuffalo" value="5">
																	3-5
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bbuffalo" value="8">
																	6-8
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bbuffalo" value="10">
																	9+
																</label>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_cow_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Total Cow amount</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_cow_amount"
																	data-mask="9999999" id="customer_cow_amount_id"
																	placeholder="Total Cow (amount)" required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback" id="customer_cow">
															<label class="control-label col-md-3  radio"
																data-name="customer_cow">Cow </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_cow" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_cow" value="2">
																	1-2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_cow" value="5">
																	3-5
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_cow" value="8">
																	6-8
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_cow" value="10">
																	9+
																</label>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_calf_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Total Calf amount</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_calf_amount"
																	data-mask="9999999" id="customer_calf_amount_id"
																	placeholder="Total Calf (amount)" required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback" id="customer_calf">
															<label class="control-label col-md-3  radio"
																data-name="customer_calf">Calf </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_calf" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_calf" value="2">
																	1-2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_calf" value="5">
																	3-5
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_calf" value="8">
																	6-8
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_calf" value="10">
																	9+
																</label>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_goat_Sheep_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Total Goat/ Sheep
																(amount) </label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	id="customer_goat_Sheep_amount_id"
																	name="customer_goat_Sheep_amount" data-mask="9999999"
																	type="hidden" placeholder="Total Goat Sheep (amount) "
																	required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">

														<div class="form-group has-feedback"
															id="customer_goat_sheep">
															<label class="control-label col-md-3  radio"
																data-name="customer_goat_sheep">Goat/Sheep </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	name="customer_goat_sheep" value="0" checked="checked">
																	0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_goat_sheep" value="2">
																	1-2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_goat_sheep" value="5">
																	3-5
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_goat_sheep" value="8">
																	6-8
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_goat_sheep" value="10">
																	9+
																</label>

															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_bike_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Bike </label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	id="customer_bike_amount_id"
																	name="customer_bike_amount" data-mask="9999999"
																	type="hidden" placeholder="Total Bike (amount) "
																	required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback" id="customer_bike">
															<label class="control-label col-md-3  radio"
																data-name="customer_bike">No of Motor Bikes </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bike" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bike" value="1">
																	1
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bike" value="2">
																	2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_bike" value="5">
																	3+
																</label>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_car_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Car </label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	id="customer_car_amount_id" name="customer_car_amount"
																	data-mask="9999999" type="hidden"
																	placeholder="Total Car (amount) " required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback" id="customer_car">
															<label class="control-label col-md-3  radio"
																data-name="customer_car">No. of Cars </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_car" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_car" value="1">
																	1
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_car" value="2">
																	2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_car" value="5">
																	3+
																</label>
															</div>
														</div>
													</div>

												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_tractors_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Tractors </label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	id="customer_tractors_amount_id"
																	name="customer_tractors_amount" data-mask="9999999"
																	type="hidden" placeholder="Total Tractors (amount) "
																	required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback"
															id="customer_vehicles">
															<label class="control-label col-md-3  radio"
																data-name="customer_vehicles">No. of Tractors </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tractors" value="0"
																	checked="checked"> 0
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tractors" value="1">
																	1
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tractors" value="2">
																	2
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tractors" value="5">
																	3+
																</label>
															</div>
														</div>
													</div>

												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_tv_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">TV Amount</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_tv_amount"
																	data-mask="9999999" id="customer_tv_amount_id"
																	placeholder="TV (amount) " required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">

														<div class="form-group has-feedback" id="customer_tv">
															<label class="control-label col-md-3  radio"
																data-name="customer_tv">TV </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tv" value="0"
																	checked="checked"> No
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_tv" value="1">
																	Yes
																</label>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-md-5">
														<div class="form-group has-feedback"
															id="customer_fridge_amount">
															<label class="col-md-offset-2 col-md-4 control-label"
																style="display: none;">Fridge Amount</label>
															<div class="col-md-6">
																<input class="form-control assets-source mask currency"
																	type="hidden" name="customer_fridge_amount"
																	data-mask="9999999" id="customer_fridge_amount_id"
																	placeholder="Fridge (amount) " required value="0">
															</div>
														</div>
													</div>


													<div class="col-md-10">
														<div class="form-group has-feedback" id="customer_fridge">
															<label class="control-label col-md-3  radio"
																data-name="customer_fridge">Fridge </label>
															<div class="col-md-9">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_fridge" value="0"
																	checked="checked"> No
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_fridge" value="1">
																	Yes
																</label>
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>

										<div class="col-md-12">
											<div class="row">
												<div class="col-md-7 col-md-offset-1"
													id="wizard_3_status_as"></div>
											</div>
											<form action="" class="form-horizontal parsley"
												id="customer_assets" data-customer-assets>


												<div id="add_assets"></div>
												<div class="row">
													<div class="col-md-2 col-md-offset-6">
														<button class="btn btn-success btn-block" type="button"
															id="btn_add_assets">
															<span class="fa fa-plus"></span> Add Assets
														</button>
													</div>
												</div>

											</form>
										</div>

									</div>

									<div class="row m-t-10 m-b-10 well m-t-0">
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-12" id="wizard_3_assets_status_me"></div>
											</div>
											<form action="" class="form-horizontal "
												id="customer_expense_source_detail"
												data-customer-monthly-multiple_expense>
												<legend>Customer Expense</legend>

												<div class="row">
													<div class="col-md-2"></div>
													<div class="col-md-6">
														<div class="form-group">
															<div class="col-md-9">
																<label class="col-md-7 control-label"
																	style="padding-top: 12px;"><b>Total Expense
																		of Customer</b> </label>
																<h4>
																	<input class="m-0 inline customer_total_expense_income"
																		style="border-color: transparent;"
																		id="customer_total_expense_income" readonly>
																</h4>
																<input type="hidden" class="form-control"
																	id="get_customer_total_expense_income"
																	name="customer_total_expense_income">
															</div>
														</div>
													</div>
												</div>


												<div class="row">
													<div class="col-md-2"></div>
													<div class="col-md-10">
														<div class="col-md-offset-1 col-md-8">
															<ul class="nav nav-pills">
																<li class="active"><a href="#nav-pills-tabex-1"
																	data-toggle="tab"> Electricity Expense Details</a></li>
																<li><a href="#nav-pills-tabex-2" data-toggle="tab">Mobile
																		Bills</a></li>
																<li><a href="#nav-pills-tabex-3" data-toggle="tab">Fuel
																		(Transport) </a></li>
																<li><a href="#nav-pills-tabex-4" data-toggle="tab">Karyana</a></li>
															</ul>
														</div>
													</div>
												</div>

												<div class="tab-content">
													<div class="tab-pane fade active in" id="nav-pills-tabex-1">

														<div class="form-group has-feedback"
															id="customer_Connectivity">
															<label class="control-label col-md-4 radio"
																data-name="customer_Connectivity"><b>Connectivity</b></label>
															<div class="col-md-5">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_Connectivity" value="0"
																	checked="checked"> Off-Grid
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_Connectivity" value="1">
																	On-Grid
																</label>

															</div>
														</div>


														<div class="form-group has-feedback"
															id="customer_sourceof_electricity_id">
															<label class="control-label col-md-4 radio"
																data-name="customer_sourceof_electricity">Primary
																Source of Electricity</label>
															<div class="col-md-6">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" id="customer_sourceof_electricity_id"
																	name="customer_sourceof_electricity" value="UPS"
																	checked="checked"> UPS
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" id="customer_sourceof_electricity_id"
																	name="customer_sourceof_electricity" value="generator">
																	Generator
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" id="customer_sourceof_electricity_id"
																	name="customer_sourceof_electricity"
																	value="battery torch"> Battery Torch
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" id="customer_sourceof_electricity_id"
																	name="customer_sourceof_electricity" value="solar">
																	Solar
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" id="customer_sourceof_electricity_id"
																	name="customer_sourceof_electricity" value="kerosene">
																	Kerosene
																</label>
															</div>
														</div>
														<div class="form-group has-feedback"
															id="customer_other_primary_sourse">
															<label class="col-md-4 control-label">Electricity
																Expense (amount)</label>
															<div class="col-md-4">
																<input class="form-control expense-source mask currency"
																	name="customer_primary_sourse_amount"
																	id="customer_primary_sourse_amount_id"
																	data-mask="99999999" value="0"
																	placeholder="Electricity Expense (amount)" required>
															</div>
														</div>
													</div>

													<div class="tab-pane fade" id="nav-pills-tabex-2">

														<!-- <legend>Mobile Bills </legend> -->
														<div class="form-group has-feedback"
															id="customer_mobile_type">
															<label class="control-label col-md-4 radio"
																data-name="number_of_children">Mobile Type</label>
															<div class="col-md-6">
																<label class="radio-inline"> <input type="radio"
																	name="customer_mobile_type" value="Smart"
																	checked="checked"> Smart (Android, Window)
																</label><label class="radio-inline"> <input type="radio"
																	name="customer_mobile_type"
																	value="Digital/Feature Phone"> Digital/Feature
																	Phone
																</label>
															</div>
														</div>

														<div class="form-group has-feedback"
															id="customer_mobilebills_amount">
															<label class="col-md-offset-1 col-md-3 control-label">Mobile
																Bills Amount</label>
															<div class="col-md-4">
																<input class="form-control expense-source mask currency"
																	name="customer_mobilebills_amount" data-mask="99999999"
																	placeholder="Mobile Usage (amount) " required value="0">
															</div>
														</div>

														<div class="form-group has-feedback"
															id="type_of_mobile_network_id">
															<label class="control-label col-md-4 radio"
																data-name="type_of_mobile_network ">What Type of
																Mobile Networks in your Areas</label>
															<div class="col-md-6">
																<label class="radio-inline"> <input
																	type="checkbox" name="mobilink_network" value="1">
																	Moblink
																</label><label class="radio-inline"> <input
																	type="checkbox" name="ufone_network" value="1">
																	UFone
																</label><label class="radio-inline"> <input
																	type="checkbox" name="zong_network" value="1">
																	Zong
																</label><label class="radio-inline"> <input
																	type="checkbox" name="warid_network" value="1">
																	Warid
																</label><label class="radio-inline"> <input
																	type="checkbox" name="telenor_network" value="1">
																	Telenor
																</label>
															</div>
														</div>
													</div>

													<div class="tab-pane fade " id="nav-pills-tabex-3">
														<!-- <legend>Fuel (Transport) </legend> -->
														<div class="form-group has-feedback"
															id="customer_transport_mode">
															<label class="col-md-offset-1 col-md-3 control-label">Customer
																Transport Mode </label>

															<div class="col-md-6">
																<label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_transport_mode"
																	value="None" checked="checked"> None
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_transport_mode"
																	value="Bike"> Bike
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_transport_mode"
																	value="Car"> Car
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_transport_mode"
																	value="Bus"> Bus
																</label><label class="radio-inline"> <input type="radio"
																	class="radioBtn" name="customer_transport_mode"
																	value="Train"> Train
																</label>
															</div>

														</div>


														<div class="form-group has-feedback"
															id="customer_transport_amount">
															<label class="col-md-offset-1 col-md-3 control-label">Customer
																Transport Amount</label>
															<div class="col-md-4">
																<input class="form-control expense-source mask currency"
																	name="customer_transport_amount"
																	id="customer_transport_amount_id" data-mask="99999999"
																	placeholder="Customer Transport amount" value="0"
																	required>
															</div>
														</div>
													</div>

													<div class="tab-pane fade " id="nav-pills-tabex-4">
														<!-- <legend>Karyana </legend> -->
														<div class="form-group has-feedback" id="customer_karyana">
															<label class="col-md-offset-1 col-md-3 control-label">Customer
																Karyana </label>

															<div class="col-md-6">
																<label class="radio-inline"> <input type="radio"
																	name="customer_karyana" value="Cash" checked="checked">
																	Cash
																</label><label class="radio-inline"> <input type="radio"
																	name="customer_karyana" value="Credit"> Credit
																</label>
															</div>
														</div>



														<div class="form-group has-feedback"
															id="customer_karyana_amount">
															<label class="col-md-offset-1 col-md-3 control-label">Customer
																Karyana Amount</label>
															<div class="col-md-4">
																<input class="form-control expense-source mask currency"
																	name="customer_karyana_amount" data-mask="99999999"
																	placeholder="Customer karyana amount" required
																	value="0">
															</div>
														</div>
													</div>
												</div>
											</form>
										</div>

										<div class="col-md-12">
											<div class="row">
												<div class="col-md-7 col-md-offset-1"
													id="wizard_3_status_me"></div>
											</div>
											<form action="" class="form-horizontal parsley"
												id="customer_monthly_expenses_detail"
												data-customer-monthly-expense>


												<div id="add_expense" class=""></div>
												<div class="row">
													<div class="col-md-2 col-md-offset-6">
														<button class="btn btn-success btn-block" type="button"
															id="btn_add_expense">
															<span class="fa fa-plus"></span> Add Expense
														</button>
													</div>
												</div>

											</form>
										</div>
									</div>


									<div class="row m-t-10 m-b-10">
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-4 col-md-offset-4"
													id="wizard_3_status_lal"></div>
											</div>
											<form action="" class="form-horizontal parsley well m-t-10"
												id="customer_loan" data-customer-loan>
												<fieldset>
													<legend>Loan and Liabilities</legend>
													<div class="row" style="margin-left: -170px">
														<div></div>
														<div>
															<label class="col-md-2 control-label">Bank Name</label>
														</div>
														<div>
															<label class="col-md-2 control-label"
																style="margin-left: 15px">Borrowed Amount</label>
														</div>
														<div>
															<label class="col-md-2 control-label"
																style="margin-left: -24px">Remaining Amount</label>
														</div>
														<div>
															<label class="col-md-2 control-label"
																style="margin-left: -16px;">Installments Amount</label>
														</div>
														<div>
															<label class="col-md-2 control-label"
																style="margin-left: -50px;">Duration of Loan</label>
														</div>
														<div>
															<label class="col-md-2 control-label"
																style="margin-left: 13px;">Frequency of Payments</label>
														</div>

													</div>
													<div class="row" id="_loan_and_liability_id_">

														<div class="has-feedback col-md-2 m-b-5">
															<input class="form-control" data-type="1" type="text"
																name="customer_loan_entity_name" id="entity"
																placeholder="Bank/NGO/Organization">
														</div>
														<div class="has-feedback col-md-2 m-b-5">
															<input class="form-control" data-type="1" type="text"
																name="customer_loan_entity_amount"
																placeholder="Borrowed Amount">
														</div>
														<div class="has-feedback col-md-2 m-b-5">
															<input class="form-control" data-type="1" type="text"
																name="customer_loan_entity_remaining_amount"
																placeholder="Remaining Amount">
														</div>
														<div class="has-feedback col-md-2 m-b-5">
															<input class="form-control" data-type="1" type="text"
																name="customer_loan_entity_monthly_payment"
																placeholder="Monthly Payment"
																id="customer_loan_entity_monthly_payment_id">
														</div>
														<!--  abbas -->
														<div class="has-feedback col-md-2  m-b-5">
															<select class="form-control selectpicker" data-type="1"
																id="select_payment_plan_in_month"
																data-live-search="true" data-size="6"
																name="customer_payment_plan_in_month"
																data-parsley-trigger="blur" data-style="btn-white"
																data-none-selected-text="Select Frequency" required>

																<option value="" selected>Select Frequency</option>
																<option class="divider2"></option>
															</select>
														</div>

														<div class="has-feedback col-md-2 m-b-5">
															<select class="form-control selectpicker" data-type="1"
																id="select_frequency_in_month" data-live-search="true"
																data-size="6" name="customer_frequency_in_month"
																data-parsley-trigger="blur" data-style="btn-white"
																data-none-selected-text="Select Frequency" required>

																<option value="" selected>Select Frequency</option>
																<option class="divider2"></option>

															</select>
														</div>
													</div>

													<div id="add_loan_and_liabilities"></div>
													<div class="row">
														<div class="col-md-2 col-md-offset-10">
															<button class="btn btn-success btn-block" type="button"
																id="btn_add_loan_and_liabilities" disabled>
																<span class="fa fa-plus"></span> Add Loan and
																Liabilities
															</button>
														</div>
													</div>
												</fieldset>
											</form>
										</div>
									</div>
								</div>
								<!-- end wizard step-3 -->
								<!-- =============================General Information End ==================== -->

								<!-- =============================Gaurontor Information Starts  ==================== -->
								<!-- begin wizard step-4 -->
								<div class="wizard-step-4" data-familyguarantorid
									data-outsideguarantorid data-vleguarantorid>
									<div class="row">
										<div class="col-md-4 col-md-offset-4" id="wizard_4_status_cg">

										</div>
									</div>
									<form class="form-horizontal" id="">
										<fieldset>

											<div class="row">
												<div class="col-md-6 " data-family-guarantorid>
													<legend>Faimly Guarantor</legend>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Guarantor
															Name</label>
														<div class="col-md-6  m-b-5">
															<input class="form-control" type="text"
																name="family_guarantor_fullname" id="guarantor"
																placeholder="Family Guarantor Name">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">CNIC</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control mask"
																data-mask="99999-9999999-9" type="text"
																name="family_guarantor_cnic"
																placeholder="Family Guarantor CNIC">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Primary
															Phone</label>
														<div class="col-md-6 m-b-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	class="form-control mask" data-mask="9999999999"
																	type="text" name="family_guarantor_cell_number"
																	placeholder="XXXXXXXXXX">
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Secondry
															Phone</label>
														<div class="col-md-6 m-b-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	class="form-control mask" data-mask="9999999999"
																	type="text"
																	name="family_guarantor_secondary_cell_number"
																	placeholder="XXXXXXXXXX">
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Relationship</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control" type="text"
																name="family_guarantor_relation" id="relation"
																placeholder="Relationship shouldn't be (Father, Mother, Brother)">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Monthly
															Income</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control mask" data-mask="9999999"
																type="text" name="family_guarantor_income_source"
																placeholder="Guarantor Monthly Income">
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Profession</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control" type="text"
																name="family_guarantor_profession" id="relation"
																placeholder="Guarantor Profession">
														</div>
													</div>


												</div>
												<div class="col-md-6" data-outside-guarantorid>
													<legend>OutSide Guarantor</legend>
													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Guarantor
															Name</label>

														<div class="col-md-6 m-b-5">
															<input class="form-control" type="text"
																name="outside_guarantor_fullname" id="guarantor"
																placeholder="Guarantor Name">
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">CNIC</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control mask"
																data-mask="99999-9999999-9" type="text"
																name="outside_guarantor_cnic"
																placeholder="Guarantor CNIC">
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Primary
															Phone</label>
														<div class="col-md-6 m-b-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	class="form-control mask" data-mask="9999999999"
																	type="text" name="outside_guarantor_cell_number"
																	placeholder="XXXXXXXXXX">
															</div>
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Secondry
															Phone</label>
														<div class="col-md-6 m-b-5">
															<div class="input-group">
																<span class="input-group-addon">92</span> <input
																	class="form-control mask" data-mask="9999999999"
																	type="text"
																	name="outside_guarantor_secondary_cell_number"
																	placeholder="XXXXXXXXXX">
															</div>
														</div>
													</div>


													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Relationship</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control" type="text"
																name="outside_guarantor_relation" id="relation"
																placeholder="Relationship to Customer">
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Monthly
															Income</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control mask" data-mask="9999999"
																type="text" name="outside_guarantor_income_source"
																placeholder="Guarantor Monthly Income">
														</div>
													</div>

													<div class="form-group">
														<label class="col-md-3 col-md-offset-1">Profession</label>
														<div class="col-md-6 m-b-5">
															<input class="form-control" type="text"
																name="outside_guarantor_profession" id="relation"
																placeholder="Guarantor Profession">
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
								<!-- =============================Gaurontor Information End  ==================== -->
								<!-- =============================Appliance Details Starts  ==================== -->
								<div class="wizard-step-5">
									<div class="row">
										<div class="col-md-4 col-md-offset-4"
											id="appliance_added_status"></div>
									</div>
									<form class="form-horizontal">
										<fieldset>
											<div id="appliancesInAccount" style="display: none">
												<legend>Appliances in account</legend>
												<div>
													<div class="">
														<table id="applianceInAccountTable"
															class="table equal table-bordered">
															<tr>
																<th>Appliance Name</th>
																<th>Status</th>

															</tr>
														</table>

														<div class="form-group has-feedback">
															<div class="col-md-2 m-b-5 col-md-offset-1">
																<select class="form-control" id="edit_appliance"
																	name="edit_appliance">
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

															<div class="col-md-2 m-b-5" style="display: show;">
																<input class="form-control mask income-source"
																	name="scheme_plan" id="scheme_plan"
																	placeholder="Scheme" data-mask="99"
																	onkeyup="getTotal()" disabled>
															</div>

															<div class="col-md-2 m-b-5">
																<input class="form-control mask income-source"
																	name="downpayment" id="downpayment"
																	placeholder="Advance Payment" data-mask="999999"
																	onkeyup="getTotal()" disabled>
															</div>
															<div class="col-md-2 m-b-5">
																<input class="form-control mask income-source"
																	name="monthly_payment" id="monthly_payment"
																	placeholder="Monthly Payment" data-mask="999999"
																	onkeyup="getTotal()" disabled>
															</div>
															<div class="col-md-2 m-b-5">
																<input class="form-control mask income-source"
																	name="total_payment" id="total_payment"
																	placeholder="Total Payment" data-mask="999999" disabled>
															</div>
														</div>
														<div class="col-md-4 col-md-offset-7"
															style="margin-right: 18px;">
															<button type="button" class="btn btn-primary pull-right"
																id="btn_update_appliance">Submit</button>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
									</form>
									<form class="form-horizontal" id="customer_appliance_request"
										data-applianceids>
										<fieldset>

											<div>
												<legend>Add new appliance</legend>
												<div id="other_scheme"></div>

												<div id="other_appliance"></div>

												<div class="form-group has-feedback"
													style="margin-left: -18px;" id="customer_other_appliance">

													<div class="col-md-offset-1 col-md-2">

														<button class="btn btn-primary btn-block"
															id="btn_add_appliance" type="button">
															<span class="fa fa-plus"></span> Add Appliance
														</button>
													</div>
												</div>

												<div class="col-md-4 col-md-offset-7"
													style="margin-right: 18px;">
													<button type="button" class="btn btn-primary pull-right"
														id="add_appliance">Submit</button>
												</div>
											</div>
										</fieldset>
									</form>
								</div>
								<!-- =============================Appliance Details End  ==================== -->
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
		<!-- begin theme-panel -->

		<!-- end theme-panel -->

		<!-- begin scroll to top btn -->
		<a href="javascript:;"
			class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade"
			data-click="scroll-top"> <i class="fa fa-angle-up"></i>
		</a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->

	<!-- ================== BEGIN BASE JS ================== -->

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	<script src="assets/plugins/jquery/jquery-1.9.1.min.js"></script>
	<script src="assets/plugins/jquery/jquery-migrate-1.1.0.min.js"></script>
	<script src="assets/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="assets/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/plugins/jquery-cookie/jquery.cookie.js"></script>
	<script src="assets/js/apps.min.js"></script>

	<script src="assets/plugins/bootstrap-wizard/js/bwizard.js"></script>

	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

	<script src="assets/plugins/bootstrap-select/bootstrap-select.min.js"></script>

	<script src="assets/plugins/parsley/dist/parsley.js"></script>

	<script src="assets/plugins/masked-input/masked-input.min.js"></script>
	<script
		src="assets/plugins/bootstrap-multiple-select/js/bootstrap-multiple-select.js"></script>


	<script src="assets/plugins/jquery-tag-it/js/tag-it.min.js"></script>
	<script type="text/javascript" src="js/customer_form_validation.js"></script>
	<!-- 	<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.5/numeral.min.js"></script> -->
	<script src="assets/plugins/inputmask/inputmask/inputmask.js"></script>
	<script src="assets/plugins/inputmask/inputmask/jquery.inputmask.js"></script>
	<!-- 	<script src="assets/plugins/inputmask/inputmask/inputmask.js"></script> -->
	<script>
		function cancelScheme() {
			$("#customer_appliance_scheme_new").css("display", "block");
			$("#customer_other_scheme").css("display", "none");

		}

		function dynCancelScheme(x) {
			$("#customer_appliance_scheme_new_" + x).css("display", "block");
			$("#customer_other_scheme_" + x).css("display", "none");

			document.getElementById('customer_advance_payment_' + x).value = '';
			document.getElementById('customer_monthly_payment_' + x).value = '';
			document.getElementById('customer_total_payment_' + x).value = '';

			$('#customer_advance_payment_' + x).prop('disabled', true);
			$('#customer_monthly_payment_' + x).prop('disabled', true);
		}

		function doAppln() {

			$('#customer_advance_payment').prop('disabled', false);
			$('#customer_monthly_payment').prop('disabled', false);
			$('#customer_total_payment').prop('disabled', false);

			////
			$('#customer_advance_payment').prop('readonly', false);
			$('#customer_monthly_payment').prop('readonly', false);
			$('#customer_total_payment').prop('readonly', false);

			document.getElementById('customer_advance_payment').value = '';
			document.getElementById('customer_monthly_payment').value = '';
			document.getElementById('customer_total_payment').value = '';
		}

		function doSScheme() {
			$('#customer_total_payment').prop('readonly', true);
			//  		$('#customer_advance_payment').prop('readonly',true);

		}

		function myScheme(x) {

			$('#customer_advance_payment_' + x).prop('readonly', false);
			$('#customer_monthly_payment_' + x).prop('readonly', false);
			$('#customer_total_payment_' + x).prop('readonly', false);

			////
			$('#customer_advance_payment_' + x).attr("disabled", false);
			$('#customer_monthly_payment_' + x).attr("disabled", false);
			$('#customer_total_payment_' + x).attr("disabled", false);

			document.getElementById('customer_advance_payment_' + x).value = '';
			document.getElementById('customer_monthly_payment_' + x).value = '';
			document.getElementById('customer_total_payment_' + x).value = '';
		}

		function changeappliance(a) {
			$('#scheme_' + a).attr("disabled", false);
		}

		function changefunction(a) {

			$('#customer_advance_payment_' + a).prop('readonly', true);
			$('#customer_monthly_payment_' + a).prop('readonly', true);
			$('#customer_total_payment_' + a).prop('readonly', true);

			if ($('#scheme_' + a + ' option:selected').val() == '1') {
				console.log("before other dynamic..");

				$("#customer_appliance_scheme_new_" + a).css("display", "none");
				$("#customer_other_scheme_" + a).css("display", "block");

				$('#customer_advance_payment_' + a).prop('readonly', false);
				$('#customer_monthly_payment_' + a).prop('readonly', false);
				$('#customer_total_payment_' + a).prop('readonly', false);

				document.getElementById('customer_advance_payment_' + a).value = '';
				document.getElementById('customer_monthly_payment_' + a).value = '';
				document.getElementById('customer_total_payment_' + a).value = '';
			}

		}

		function changeScheme(b) {

			var appliance = $('#appliance_name_2' + b + '').val();
			var scheme = $('#scheme_' + b + '').val();
			// 		console.log('appliance '+appliance)
			// 		console.log('sheme '+scheme)

			if (appliance == "60 W" && scheme == "12") {
				// 			console.log("called if")
				var price = (scheme * 2700) + 5500
				$("#customer_advance_payment_" + b).val('5500');
				$("#customer_monthly_payment_" + b).val('2700');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "7 W" && scheme == "12") {
				var price = (scheme * 2700) + 3000
				// 			console.log("called if")

				$("#customer_advance_payment_" + b).val('3000');
				$("#customer_monthly_payment_" + b).val('2700');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "60 W" && scheme == "18") {
				var price = (scheme * 2100) + 5500

				$("#customer_advance_payment_" + b).val('5500');
				$("#customer_monthly_payment_" + b).val('2100');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "7 W" && scheme == "18") {
				var price = (scheme * 2000) + 4000

				$("#customer_advance_payment_" + b).val('4000');
				$("#customer_monthly_payment_" + b).val('2000');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "60 W" && scheme == "24") {
				var price = (scheme * 1800) + 5500

				$("#customer_advance_payment_" + b).val('5500');
				$("#customer_monthly_payment_" + b).val('1800');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "7 W" && scheme == "24") {
				var price = (scheme * 1700) + 5500

				$("#customer_advance_payment_" + b).val('4500');
				$("#customer_monthly_payment_" + b).val('1700');
				$("#customer_total_payment_" + b).val(price);

			}

			else if (appliance == "60 W" && scheme == "36") {
				var price = (scheme * 1500) + 5500

				$("#customer_advance_payment_" + b).val('5500');
				$("#customer_monthly_payment_" + b).val('1500');
				$("#customer_total_payment_" + b).val(price);

			}

			else if (appliance == "7 W" && scheme == "36") {
				var price = (scheme * 1400) + 5000

				$("#customer_advance_payment_" + b).val('5000');
				$("#customer_monthly_payment_" + b).val('1400');
				$("#customer_total_payment_" + b).val(price);

			} else if (appliance == "B1 Basic1" && scheme == "16") {
				var price = (scheme * 2700) + 2700

				$("#customer_advance_payment_" + b).val('2700');
				$("#customer_monthly_payment_" + b).val('2700');
				$("#customer_total_payment_" + b).val(price);
			} else if (appliance == "B1 Basic2" && scheme == "16") {
				var price = (scheme * 2800) + 2800

				$("#customer_advance_payment_" + b).val('2800');
				$("#customer_monthly_payment_" + b).val('2800');
				$("#customer_total_payment_" + b).val(price);
			} else if (appliance == "B1 Roshni" && scheme == "16") {
				var price = (scheme * 2700) + 2700

				$("#customer_advance_payment_" + b).val('2700');
				$("#customer_monthly_payment_" + b).val('2700');
				$("#customer_total_payment_" + b).val(price);
			} else if (appliance == "B1 PowerP" && scheme == "16") {
				var price = (scheme * 3000) + 3000

				$("#customer_advance_payment_" + b).val('3000');
				$("#customer_monthly_payment_" + b).val('3000');
				$("#customer_total_payment_" + b).val(price);
			} else if (appliance == "B1 PowerC" && scheme == "16") {
				var price = (scheme * 3100) + 3100

				$("#customer_advance_payment_" + b).val('3100');
				$("#customer_monthly_payment_" + b).val('3100');
				$("#customer_total_payment_" + b).val(price);
			} else {
				var price = (scheme * 0) + 0

				$("#customer_advance_payment_" + b).val('0');
				$("#customer_monthly_payment_" + b).val('0');
				$("#customer_total_payment_" + b).val(price);
			}

		} //  end of function 

		function getTotal() {
			var scheme = $('#scheme_plan').val();
			var advance = $('#downpayment').val();
			var monthly = monthly = $('#monthly_payment').val();
			var total = (scheme * (monthly));
			total = parseInt(total, 10) + parseInt(advance, 10);
			$('#total_payment').val(total);
		}

		function doScheme(a) {

			// 	 		document.getElementById('customer_advance_payment_'+a).value='';
			// 	    	document.getElementById('customer_monthly_payment_'+a).value='';
			document.getElementById('customer_total_payment_' + a).value = '';

			$('#customer_total_payment_' + a).prop('readonly', true);
			// 	 		$('#customer_advance_payment_'+a).prop('readonly',true);

			var scheme = $('#oscheme_' + a).val();
			var advance = $('#customer_advance_payment_' + a).val();
			var monthly = monthly = $('#customer_monthly_payment_' + a).val();

			// not working correctly
			var total = (scheme * (monthly));

			total = parseInt(total, 10) + parseInt(advance, 10);

			console.log('scheme : ' + scheme);
			console.log('monthly : ' + monthly);
			console.log('advance : ' + advance);
			console.log('total : ' + total);

			$('#customer_total_payment_' + a).val(total);
		}

		var totalYearly = 0;
		function countTotalYearlyIncome() {
			var isInput = $('.yearly_income-source').toArray()
			totalYearly = 0;
			$.each(isInput, function(e) {

				if (!isNaN(parseInt(isInput[e].value))) {
					// 										console.log(parseInt(isInput[e].value))
					totalYearly += parseInt(isInput[e].value)
					console.log('totalYearly,,,,' + totalYearly)
				}
			})
			// 		console.clear()
			// 			console.log('totalYearly: ' + totalYearly)
			$('#customer_total_yearly_income').text(
					formatNumber(totalYearly) + ' PKR')

			$('#get_customer_total_income_yearly').val(totalYearly);
			countNetMonthlyIncome();
		}

		var totalMonthly = 0;
		function countTotalMonthlyIncome() {
			var isInput = $('.income-source').toArray()

			totalMonthly = 0;

			$.each(isInput, function(e) {
				var This_id = $(this).attr("id");

				if (!isNaN(parseInt(isInput[e].value))) {
					if (This_id == "customer_labour_amount_id") {
						var days = $('#customer_labour_in_month_id').val();
						// 						var newvar = (parseInt(isInput[e].value))*days;
						totalMonthly += (parseInt(isInput[e].value)) * days;
						// 						return false;
					} else {
						totalMonthly += parseInt(isInput[e].value)
					}
				}
			})
			// 		console.clear()
			// 					console.log(totalMonthly)
			$('#customer_total_income').text(
					formatNumber(totalMonthly) + ' PKR')

			$('#get_customer_total_income').val(totalMonthly);
			countNetMonthlyIncome();
		}

		function countNetMonthlyIncome() {
			// 			countTotalYearlyIncome();
			var netMonthlyIncome = (totalYearly / 12) + totalMonthly
			console.log('totalMonthly' + totalMonthly)
			console.log('totalYearly' + totalYearly)
			console.log('netMonthlyIncome::::' + netMonthlyIncome)
			$('#customer_net_monthly_income').text(
					formatNumber(netMonthlyIncome) + ' PKR')
			$('#get_customer_net_monthly_income').val(netMonthlyIncome)
		}

		function countTotalAssetsIncome() {
			var isInput = $('.assets-source').toArray()
			var total = 0;

			$.each(isInput, function(e) {

				if (!isNaN(parseInt(isInput[e].value))) {
					// 										console.log(parseInt(isInput[e].value))
					total += parseInt(isInput[e].value)
				}
			})
			// 		console.clear()
			// 					console.log(total)
			$('#customer_total_assets_income').text(
					formatNumber(total) + ' PKR')
			$('#get_customer_total_assets_income').val(total)
		}

		function countTotalExpenseIncome() {
			var isInput = $('.expense-source').toArray()
			var total = 0;

			$.each(isInput, function(e) {

				if (!isNaN(parseInt(isInput[e].value))) {
					// 										console.log(parseInt(isInput[e].value))
					total += parseInt(isInput[e].value)
				}
			})
			// 		console.clear()
			console.log("countTotalExpenseIncome== " + total)
			$('[name = "customer_total_expense_income"]').val(total)
			$('.customer_total_expense_income').val(
					formatNumber(total) + " PKR");
		}

		$(document)
				.ready(
						function() {

							$(
									'input[type="text"]:required,input[type="email"]:required')
									.parsley()

							$('.selectpicker').on(
									'change',
									function() {
										// 								console.log($(this).val())
										if ($(this).val() !== "") {
											// 									console.log("white")
											$(this).selectpicker('setStyle',
													'btn-danger', 'remove')
											$(this).selectpicker('setStyle',
													'btn-primary', 'add')
										} else {
											// 									console.log("red")
											$(this).selectpicker('setStyle',
													'btn-primary', 'remove')
											$(this).selectpicker('setStyle',
													'btn-danger', 'add')
										}
									})
							$('.selectpicker[multiple]').on(
									'change',
									function() {
										console.log($(this).val())
										console.log($(this).val() == null)
										if ($(this).val() != null) {
											// 									console.log("white")
											$(this).selectpicker('setStyle',
													'btn-danger', 'remove')
											$(this).selectpicker('setStyle',
													'btn-primary', 'add')
										} else {
											// 									console.log("red")
											$(this).selectpicker('setStyle',
													'btn-primary', 'remove')
											$(this).selectpicker('setStyle',
													'btn-danger', 'add')
										}
									})

							// 							$('.currency').on('keyup',function(e){
							// 								console.log(this.value)
							// 								this.value = numeral(this.value).format('0,0')
							// 							})

							// 			console.log(doid)
							// 			console.log(districtid)
							// 			console.log($('[data-customerid]').data('customerid'))
							// 			console.log($('[data-customerid]').data('customerid') == null)

							// 							$('#customer_cnic').hide()

							// 			$('.hide').each(function(){
							// 				$(this).toggleClass('hide')
							// 			})
							var today = new Date();
							today = new Date((today.getFullYear() - 18), today
									.getMonth(), today.getDate())
							$("input.date").datepicker({
								todayHighlight : !0,
								endDate : today,
								autoclose : !0,
								format : 'dd-MM-yyyy'
							})
							// customer_other_electricity_usage
							$('[name="customer_electricity_usage"]')
									.change(
											function() {
												// 				console.log($.inArray("Other",$(this).val()))
												if ($.inArray("Other", $(this)
														.val()) != -1) {
													$(
															'#customer_other_electricity_usage')
															.show()
												} else {
													$(
															'#customer_other_electricity_usage')
															.hide()
												}
											})

							// 			$("input.phone").mask('999-9999999', {placeholder:'0'})
							// 			$("input.currency").mask('0', {placeholder:'0'})
							// 			$("input.cnic").mask('99999-9999999-9', {placeholder:'0'})
							// 			$("input[data-length]").mask('9',{placeholder:'0'})
							$('form.parsley').parsley()

							$('.mask').each(function(e) {
								// 								console.log('maskData '+$(this).data('mask'))
								$(this).inputmask({
									mask : $(this).data('mask'),
									autoUnmask : true,
									placeholder : ''
								})
							})

							// 							$('.mask').each(function(e) {
							// 				 				console.log($(this).data('mask'))
							// 								$(this).mask('' + $(this).data('mask'), {
							// 									placeholder : ''
							// 								})
							// 							})

							$('.multiselect').multiselect({
								buttonClass : 'btn btn-white'
							})

							var changesE = $.Event('chages')
							var textFieldFormate = 0;
							$('#btn_add_text_field_formate')
									.click(
											function() {
												$('#dynamic_text_field_formate')
														.append(
																'<div class="row form-group has-feedback" id="other_text_field_formate_'+textFieldFormate+'">'

																		+ '<div class="col-md-2 m-b-4 col-md-offset-2">'
																		+ '<input class="form-control col-md-2 " name="general_field_in_text_foramte_type" type="text" id="text_field_general'+textFieldFormate+'" placeholder="Text Field Formate" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-5 m-b-4">'
																		+ '<input class="form-control" type="text" name="general_field_in_text_foramte" placeholder="General Field in Text Foramte">'
																		+ '</div>'
																		+ '<div class="col-md-1" >'
																		+ '<button class="btn btn-danger" data-dynamictextfieldformate="#other_text_field_formate_'+textFieldFormate+'" type="button"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												$('#dynamic_text_field_formate')
														.trigger(changesE)
												textFieldFormate++;

											})
							$('#dynamic_text_field_formate')
									.on(
											'click',
											'.btn-danger',
											function() {
												$(
														$(this)
																.data(
																		'dynamictextfieldformate'))
														.remove()
												$('#dynamic_text_field_formate')
														.trigger(changesE)
											})

							$('.multiselect').multiselect({
								buttonClass : 'btn btn-white'
							})

							var changesE = $.Event('chages')
							var digitFieldFormate = 0;
							$('#btn_add_digit_field_formate')
									.click(
											function() {
												$(
														'#dynamic_digit_field_formate')
														.append(
																'<div class="row form-group has-feedback" id="other_digit_field_formate_'+digitFieldFormate+'">'

																		+ '<div class="col-md-2 m-b-4 col-md-offset-2">'
																		+ '<input class="form-control col-md-2 " name="general_field_in_digit_foramte_type" type="text" id="text_field_general'+digitFieldFormate+'" placeholder="Digit Field Formate" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-5 m-b-4">'
																		+ '<input class="form-control" type="number" name="general_field_in_digit_foramte" placeholder="General Field in Digit Foramte">'
																		+ '</div>'
																		+ '<div class="col-md-1" >'
																		+ '<button class="btn btn-danger" data-dynamicdigitfieldformate="#other_digit_field_formate_'+digitFieldFormate+'" type="button"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												$(
														'#dynamic_digit_field_formate')
														.trigger(changesE)
												digitFieldFormate++;

											})
							$('#dynamic_digit_field_formate')
									.on(
											'click',
											'.btn-danger',
											function() {
												$(
														$(this)
																.data(
																		'dynamicdigitfieldformate'))
														.remove()
												$(
														'#dynamic_digit_field_formate')
														.trigger(changesE)
											})

							$('.multiselect').multiselect({
								buttonClass : 'btn btn-white'
							})

							var changesE = $.Event('changes')
							var otherNumberId = 0;
							$('#btn_add_other_number')
									.click(
											function() {
												$('#dynamic_customer_number')
														.append(
																'<div class="row form-group has-feedback" id="other_customer_num_'+otherNumberId+'">'

																		+ '<div>'
																		+ '<label class="col-md-2 control-label col-md-offset-2" >Other Number Details '
																		+ (otherNumberId + 1)
																		+ '</label>'
																		+ '</div>'
																		+ '<div class="col-md-3 m-b-5">'
																		+ '<input class="form-control" type="text" name="who_is_other" id="who_is_other" placeholder="Who is Other (Name)">'
																		+ '</div>'
																		+ '<div class="col-md-2 m-b-5">'
																		+ '<input class="form-control" type="text" name="relation_with_customer" id="relation_with_customer" placeholder="Relation With Customer">'
																		+ '</div>'
																		+ '<div class="col-md-7 m-b-5 col-md-offset-4">'
																		+ '<div class="col-md-9 input-group" style="padding-right: 4%">'
																		+ '<span class="input-group-addon">92</span>'
																		+ '<input type="text" class="form-control phone mask"'
																		+ 'data-mask="9999999999" name="customer_other_phone" id="customer_other_phone"'
																		+ 'placeholder="Other Phone Number (secondary)" required="required"/>'
																		+ '</div>'
																		+ '</div>'
																		+ '<div class="col-md-1" style="margin-left: -17%;">'
																		+ '<button class="btn btn-danger" data-dynamicnumberid="other_customer_num_'+otherNumberId+'" type="button"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												$('#dynamic_customer_number')
														.trigger(changesE)
												otherNumberId++;

											})
							$('#dynamic_customer_number')
									.on(
											'click',
											'.btn-danger',
											function() {
												console.log('dynamicnumberid')
												$(
														'#'
																+ $(this)
																		.data(
																				'dynamicnumberid'))
														.remove()
												$('#dynamic_customer_number')
														.trigger(changesE)
											})

							$('.multiselect').multiselect({
								buttonClass : 'btn btn-white'
							})

							var changesE = $.Event('changes')
							var otherIncomeSourceId = 0;
							$('#btn_add_income_source')
									.click(
											function() {
												$('#other_income_source')
														.append(
																'<div class="form-group has-feedback" id="customer_other_income_source_'+otherIncomeSourceId+'">'
																		+ '<div class="col-md-4 m-b-5">'
																		+ '		<input class="form-control" name="customer_other_income_source_type" type="text" id="income_'+otherIncomeSourceId+'" placeholder="Income Source Detail" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-6 m-b-5">'
																		//+ '<input class="form-control mask income-source" name="customer_other_income_source_amount" placeholder="Amount" data-mask="999999">'
																		+ '<input type="number" name="customer_other_income_source_amount" class="form-control yearly_income-source mask currency" data-mask="9999999" placeholder="Amount" data-parsley-type="number" required="required" />'
																		+ '</div>'
																		+ '<div class="col-md-1 m-b-5">'
																		+ '		<button class="btn btn-danger" data-otherisid="#customer_other_income_source_'+otherIncomeSourceId+'"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												$('#other_income_source')
														.trigger(changesE)
												otherIncomeSourceId++;
											})
							$('#other_income_source').on(
									'click',
									'.btn-danger',
									function() {
										$($(this).data('otherisid')).remove()
										$('#other_income_source').trigger(
												changesE)
									})

							$('.multiselect').multiselect({
								buttonClass : 'btn btn-white'
							})

							//************************************************** Add Appliance *************************************************//				

							var changesE = $.Event('changes');
							var otherApplianceId = 0;
							$('#btn_add_appliance')
									.click(
											function() {
												//abbas
												console.log("before");
												$('#other_appliance')
														.append(
																'<div class="form-group has-feedback" id="customer_other_appliance_'+otherApplianceId+'">'

																		+ '<div class="col-md-2 m-b-5">'
																		+ '<select class="form-control " '
																		+ 'name="customer_appliance_name" id="appliance_name_2'
																		+ otherApplianceId
																		+ '" data-style="btn-white"'
																		+ 'onchange="changeappliance('
																		+ otherApplianceId
																		+ ');changeScheme('
																		+ otherApplianceId
																		+ ')">'
																		+ '<option value="" >Nothing Selected</option>'
																		+ '<option value="7 W">7 W</option>'
																		+ '<option value="60 W">60 W</option>'
																		+ '<option value="B1 Basic1">B1 - Basic 1</option>'
																		+ '<option value="B1 Basic2">B1 - Basic 2</option>'
																		+ '<option value="B1 Roshni">B1 - Roshni</option>'
																		+ '<option value="B1 PowerPC">B1 - Power PC</option>'
																		+ '<option value="B1 PowerP">B1 - Power P</option>'
																		+ '<option value="B1 PowerC">B1 - Power C</option>'
																		+ '<option value="B1 Other">B1 - Other</option>'
																		+ '</select>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5">'
																		+ '<select class="form-control " '
																		+ 'name="sale_type" id="sale_type'
																		+ otherApplianceId
																		+ '" data-style="btn-white"'
																		+ 'onchange="changeappliance('
																		+ otherApplianceId
																		+ ');changeScheme('
																		+ otherApplianceId
																		+ ')">'
																		+ '<option value="" >Nothing Selected</option>'
																		+ '<option value="Regular">Regular Sales</option>'
																		+ '<option value="Booking">Booking Sales</option>'
																		+ '</select>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5 " id="customer_appliance_scheme_new_'+otherApplianceId+'" style="display: show;">'
																		+ '<select class="form-control "'
																		+ 'id="scheme_'
																		+ otherApplianceId
																		+ '" name="customer_appliance_scheme" data-style="btn-white" '
																		+ 'onchange="changefunction('
																		+ otherApplianceId
																		+ '); changeScheme('
																		+ otherApplianceId
																		+ ')">'
																		+ '<option value="" >Nothing Selected</option>'

																		+ '<option value="12">12</option>'
																		+ '<option value="16">16</option>'
																		+ '<option value="18">18</option>'
																		+ '<option value="24">24</option>'
																		+ '<option value="36">36</option>'
																		+ '<option value="1">Other</option>'
																		+ '</select>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5" style="display: none;" id="customer_other_scheme_'+otherApplianceId+'">'
																		+ '  <input class="form-control mask income-source" name="customer_appliance_scheme_other" id="oscheme_'
																		+ otherApplianceId
																		+ '" placeholder="Other Scheme" data-mask="9?99999" onkeyup="myScheme('
																		+ otherApplianceId
																		+ ')";>'

																		+ '<span class="btn fa fa-spinner"'
																		+ 'style="background-color: #00ACAC; color: white; font-weight: bold; width:50%; margin-left:103px; margin-top: 5px;"'
																		+ 'id = "cncl" onclick="dynCancelScheme('
																		+ otherApplianceId
																		+ ')" > Refresh</span>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5">'
																		+ '  <input class="form-control mask income-source" name="customer_advance_payment" id="customer_advance_payment_'
																		+ otherApplianceId
																		+ '" placeholder="Advance Payment" data-mask="999999" onkeyup="doScheme('
																		+ otherApplianceId
																		+ ')";>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5">'
																		+ '  <input class="form-control mask income-source" name="customer_monthly_payment" id="customer_monthly_payment_'
																		+ otherApplianceId
																		+ '" placeholder="Monthly Payment" data-mask="999999" onkeyup="doScheme('
																		+ otherApplianceId
																		+ ')";>'
																		+ '</div>'

																		+ '<div class="col-md-2 m-b-5">'
																		+ '  <input class="form-control mask income-source" name="customer_total_payment" id="customer_total_payment_'+otherApplianceId+'" placeholder="Total Payment" data-mask="999999" >'
																		+ '</div>'

																		+ '<div class="col-md-1 m-b-5">'
																		+ '  <button style="margin-left: 1243px; margin-top: -65px;	"'
																		+ 'class="btn btn-danger" data-addappliance="#customer_other_appliance_'+otherApplianceId+'"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>');

												$('#scheme_' + otherApplianceId)
														.attr("disabled", true);
												$(
														'#customer_advance_payment_'
																+ otherApplianceId)
														.attr("disabled", true);
												$(
														'#customer_monthly_payment_'
																+ otherApplianceId)
														.attr("disabled", true);
												$(
														'#customer_total_payment_'
																+ otherApplianceId)
														.attr("disabled", true);

												$('#other_appliance').trigger(
														changesE);
												otherApplianceId++;
											});
							$('#other_appliance')
									.on(
											'click',
											'.btn-danger',
											function() {
												$($(this).data('addappliance'))
														.remove();
												$('#other_appliance').trigger(
														changesE);
											});

							$('#customer_monthly_assets_detail').on('keyup',
									'.assets-source', function() {
										countTotalAssetsIncome()
									})

							$('#customer_income_source_detail').on(
									'keyup change', '.yearly_income-source',
									function() {
										countTotalYearlyIncome()
									})

							$('#customer_income_source_detail').on('keyup',
									'.income-source', function() {
										countTotalMonthlyIncome()
									})

							$('#customer_income_source_detail').on('keyup',
									'.labour-source', function() {
										countTotalMonthlyIncome()
									})

							$(
									'#customer_expense_source_detail,#customer_monthly_expenses_detail')
									.on('keyup', '.expense-source', function() {
										countTotalExpenseIncome()
										countTotalAssetsAmount();
									})

							$('#customer_info_form').on('keyup',
									'.education-income-source', function() {
										countTotalEducationMedicalAmount()
									})
							/* $('#other_income_source').on('changes',function(){
								console.log($('[name="customer_other_income_source_type"]').toArray().length)
							}) */

							$('input:checkbox').change(function() {
								var span = $(this).next('span');
								if ($(this).is(":checked")) {
									$(span).text($(span).data('true'))
								} else {
									$(span).text($(span).data('false'))
								}
								$($(this).data('toggle')).toggleClass('hide')
							})

							$('#customer_employement_detail :input').prop(
									'disabled', true)
							$('[name="customer_salary_or_pension"]')
									.keyup(
											function() {
												if (isNaN(parseInt(this.value))
														|| parseInt(this.value) <= 0) {
													$(
															'#customer_employement_detail :input')
															.prop('disabled',
																	true)
												} else {
													$(
															'#customer_employement_detail :input')
															.prop('disabled',
																	false)
												}
											})
							$('#customer_business_detail :input').prop(
									'disabled', true)
							$('[name="customer_business_income"]')
									.keyup(
											function() {
												if (isNaN(parseInt(this.value))
														|| parseInt(this.value) <= 0) {
													$(
															'#customer_business_detail :input')
															.prop('disabled',
																	true)
												} else {
													$(
															'#customer_business_detail :input')
															.prop('disabled',
																	false)
												}
											})

							var expenseId = 0;
							$('#btn_add_expense')
									.click(
											function() {
												$('#add_expense')
														.append(
																'<div class="row p-l-10 p-r-10" id="expense_id_'+expenseId+'" >'
																		+ '<div class="col-md-3 m-b-5 col-md-offset-1">'
																		+ '<input class="form-control" name="customer_expenses_type" type="text" id="expense_'+expenseId+'" placeholder="Expense type" required="required">'
																		//+ '<input class="form-control" name="customer_expenses_type" type="text" id="expense" placeholder="Expense type">'
																		+ '</div>'
																		+ '<div class="col-md-4 m-b-5">'
																		+ '<input type="number" class="form-control expense-source mask currency" name="customer_expenses_amount" placeholder="Amount" data-mask="999999999" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-1">'
																		+ '<button class="btn btn-danger" data-expense-id="expense_id_'+expenseId+'" type="button"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												$('#add_expense').trigger(
														changesE)
												expenseId++;

											})
							$('#add_expense').on(
									'click',
									'.btn-danger',
									function() {
										$('#' + $(this).data('expense-id'))
												.remove()
										$('#add_expense').trigger(changesE)
									})

							var assetsId = 0;
							$('#btn_add_assets')
									.click(
											function() {
												$('#add_assets')
														.append(
																'<div class="row p-r-10 p-l-10" id="asset_id_'+assetsId+'" >'
																		+ '<div class="col-md-3 col-md-offset-1 m-b-5">'
																		+ '<input class="form-control" name="customer_assets_type" type="text" id="assets_'+assetsId+'" placeholder="Asset type" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-4 m-b-5">'
																		+ '<input type="number" class="form-control mask" name="customer_assets_amount" placeholder="Amount" data-mask="999999999" required="required">'
																		+ '</div>'
																		+ '<div class="col-md-1 m-b-5">'
																		+ '<button class="btn btn-danger" data-asset-id="asset_id_'+assetsId+'" type="button"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												assetsId++;
												$('#add_assets').trigger(
														changesE)
											})

							$('#add_assets').on(
									'click',
									'.btn-danger',
									function() {
										$('#' + $(this).data('asset-id'))
												.remove()
										$('#add_assets').trigger(changesE)
									})
							/* $('#add_assets').on('changes',function(){
								console.log($('[name="customer_assets_type"]').toArray().length)
								console.log($('[name="customer_assets_amount"]').toArray().length)								
								if($('[name="customer_assets_type"]').toArray().length > 0){
									$('#add_customer_assets_btn').prop('disabled',false)
								}else{
									$('#add_customer_assets_btn').prop('disabled',true)
								}
							}) */

							/* $("select[name=customer_loan_entity_name]").on("change", function () {
								if ($(this).val() == " ") {
							        $("input[type='text'][name=customer_loan_entity_amount]").removeAttr("disabled", true);
								    $("input[type='text'][name=customer_loan_entity_remaining_amount]").removeAttr("disabled", true);
								    $("input[type='text'][name=customer_loan_entity_monthly_payment]").removeAttr("disabled", true);
								    $("input[type='text'][name=customer_payment_plan_in_month]").removeAttr("disabled", true);
								    $("input[type='text'][name=customer_frequency_in_month]").removeAttr("disabled", true);
								} else{
									$("input[type='text'][name=customer_loan_entity_amount]").removeAttr("disabled", false);
								    $("input[type='text'][name=customer_loan_entity_remaining_amount]").removeAttr("disabled", false);
								    $("input[type='text'][name=customer_loan_entity_monthly_payment]").removeAttr("disabled", false);
								    $("input[type='text'][name=customer_payment_plan_in_month]").removeAttr("disabled", false);
								    $("input[type='text'][name=customer_frequency_in_month]").removeAttr("disabled", false);
								}
							}).trigger('change'); */

							var lalId = 1;
							$('#btn_add_loan_and_liabilities')
									.click(
											function(e) {
												// 						        $('[name="customer_payment_plan"]').remove();
												// 						        $('[name="customer_frequency"]').remove();
												// 						        e.preventDefault();
												$('#add_loan_and_liabilities')
														.append(
																'<div class="row" id="_loan_and_liability_id_'+lalId+'">'
																		+ '<div class="col-md-2 m-b-5">'
																		+ '<input class="form-control" data-type="0" type="text" name="customer_loan_entity_name" placeholder="Other Entity Name '
																		+ (lalId + 1)
																		+ '">'
																		+ '</div>'
																		+ '<div class="col-md-2 m-b-5">'
																		+ '<input class="form-control" type="text" name="customer_loan_entity_amount" placeholder="Borrowed Amount">'
																		+ '</div>'
																		+ '<div class="col-md-2 m-b-5">'
																		+ '<input class="form-control" type="text" name="customer_loan_entity_remaining_amount" placeholder="Remaining Amount">'
																		+ '</div>'
																		+ '<div class="col-md-2 m-b-5">'
																		+ '<input class="form-control" type="text" name="customer_loan_entity_monthly_payment" placeholder="Monthly Payment">'
																		+ '</div>'
																		+ '<div class="has-feedback col-md-2 m-b-5" id="customer_payment_plan">'
																		/* abbas */
																		+ '<select class="form-control selectpicker" data-type="1" data-size="6"'
						                  + 'id="select_payment_plan_in_month"'
						                  + 'data-form=".customer_payment_plan"'
						                  + 'data-live-search="true" '
						                  + 'name="customer_payment_plan"'
						                  + 'data-parsley-trigger="blur" data-style="btn-white">'

																		+ '<option value="" selected>Select Frequency</option>'
																		+ '<option class="divider2"></option>'
																		+ '</select>'
																		+ '</div>'
																		+ '<div class="has-feedback col-md-2 m-b-5" id="customer_frequency" >'
																		+ '<select class="form-control selectpicker" data-type="1" data-size="6"'
						                  + 'id="select_frequency_in_month"'
						                  + 'data-form=".customer_frequency"'
						                  + 'data-live-search="true" '
						                  + 'name="customer_frequency"'
						                  + 'data-parsley-trigger="blur" data-style="btn-white">'

																		+ '<option value="" selected>Select Frequency</option>'
																		+ '<option class="divider2"></option>'
																		+ '</select>'
																		+ '</div>'
																		+ '<div class="col-md-1">'
																		+ '<button class="btn btn-danger" data-lalid="#_loan_and_liability_id_'+lalId+'" type="button" style="margin-left:1365px;margin-top:-67px;"><span class="fa fa-times"></span></button>'
																		+ '</div>'
																		+ '</div>')
												// 	    				                   $('.selectpicker').selectpicker('refresh');

												lalId++;
												$('#add_loan_and_liabilities')
														.trigger(changesE)

												$('#add_loan_and_liabilities')
														.on(
																'click',
																'.btn-danger',
																function() {
																	$(
																			$(
																					this)
																					.data(
																							'lalid'))
																			.remove()
																	$(
																			'#add_loan_and_liabilities')
																			.trigger(
																					changesE)
																})

												$
														.ajax({
															url : 'CustomerFormController',
															method : 'POST',
															dataType : 'json',
															data : {
																action : 'getSchemeFrequencies'
															},
															success : function(
																	json) {
																$
																		.each(
																				json.data,
																				function(
																						e) {
																					var option = document
																							.createElement('option')
																					$(
																							option)
																							.val(
																									json.data[e].scheme_frequency_id)
																					$(
																							option)
																							.text(
																									json.data[e].schemeFrequency)

																					$(
																							'[name="customer_payment_plan"], [name="customer_frequency"]')
																							.append(
																									option)
																					$(
																							'[name="customer_payment_plan"], [name="customer_frequency"]')
																							.selectpicker(
																									'refresh')
																				})
															}
														})

											})

							$
									.ajax({
										url : 'CustomerFormController',
										method : 'POST',
										dataType : 'json',
										data : {
											action : 'getSalaryRanges'
										},
										success : function(json) {
											$
													.each(
															json.data,
															function(e) {
																var option = document
																		.createElement('option')
																$(option)
																		.val(
																				json.data[e].salary_id)
																$(option)
																		.text(
																				json.data[e].salary_range)
																$(
																		'[name="customer_monthly_income"],[name="customer_family_monthly_income"]')
																		.append(
																				option)
																// 						$('[name="customer_monthly_income"],[name="customer_family_monthly_income"]').selectpicker('refresh')
															})
											$(
													'[name="customer_monthly_income"],[name="customer_family_monthly_income"]')
													.selectpicker('refresh')
										}
									})

							// 			Begin VLE's 
							$.ajax({
								url : 'CustomerFormController',
								method : 'POST',
								dataType : 'json',
								data : {
									action : 'getDistricts'
								},
								success : function(json) {
									$.each(json.data, function(e) {
										var option = document
												.createElement('option')
										console
												.log('hhh'
														+ json.data[e].userId)
										$(option).val(json.data[e].userId)
										$(option).text(json.data[e].name)
										$('[name="district"]').append(option)
									})
									$('[name="district"]').selectpicker(
											'refresh')
								}
							})
							$
									.ajax({
										url : 'CustomerFormController',
										method : 'POST',
										dataType : 'json',
										data : {
											action : 'getSchemeFrequencies' //,
										//userId : $('[data-userid]').data('userid')
										},
										success : function(json) {
											$
													.each(
															json.data,
															function(e) {

																var option = document
																		.createElement('option')
																$(option)
																		.val(
																				json.data[e].scheme_frequency_id)
																$(option)
																		.text(
																				json.data[e].schemeFrequency)

																$(
																		'[name="customer_payment_plan_in_month"], [name="customer_frequency_in_month"]')
																		.append(
																				option)
																$(
																		'[name="customer_payment_plan_in_month"], [name="customer_frequency_in_month"]')
																		.selectpicker(
																				'refresh')
															})
										}
									})

							function getVLEs() {
								$
										.ajax({
											url : 'CustomerFormController',
											method : 'POST',
											dataType : 'json',
											data : {
												action : 'getDOCities',
												districtid : $(
														'#select_customer_district')
														.val()
											},
											success : function(json) {
												$
														.each(
																json.data,
																function(e) {
																	var option = document
																			.createElement('option')
																	$(option)
																			.val(
																					json.data[e].city_id)
																	// 										$(option).text(json.data[e].district_name)
																	$(option)
																			.text(
																					json.data[e].city_name
																							+ ', '
																							+ json.data[e].district_name)
																	$(
																			'#select_customer_city')
																			.append(
																					option)
																})
												$('#select_customer_city')
														.selectpicker('refresh')
											}
										})

								$
										.ajax({
											url : 'CustomerFormController',
											method : 'POST',
											dataType : 'json',
											data : {
												action : 'getVLEs',
												districtid : $(
														"#select_customer_district")
														.val()
											},
											success : function(json) {
												$
														.each(
																json.data,
																function(e) {
																	var option = document
																			.createElement('option')
																	$(option)
																			.val(
																					json.data[e].salesmanId)
																	$(option)
																			.text(
																					json.data[e].name
																							+ ', '
																							+ json.data[e].foname)
																	$(
																			'[name="customer_vle_guarantor"]')
																			.append(
																					option)
																})
												$(
														'[name="customer_vle_guarantor"]')
														.selectpicker('refresh')
											}
										})
								getCustomerFormWizard();
							}

							function getCustomerFormWizard() {
								$
										.ajax({
											url : 'CustomerFormController',
											method : 'POST',
											dataType : 'json',
											data : {
												action : 'getCustomerForm',
												customerId : customerId
											},
											success : function(json) {
												console.log(json)
												console
														.log("Fill Wizard : "
																+ json.customerForm.metaData.formWizard)
												console
														.log('VLE: '
																+ json.customerForm.metaData.vle
																+ " "
																+ json.customerForm.metaData.district)
												$('#select_customer_city')
														.selectpicker(
																'val',
																json.customerForm.metaData.cityId)
												$('#select_customer_city')
														.selectpicker('render')
												$('#select_customer_vle')
														.selectpicker(
																'val',
																json.customerForm.metaData.vle)
												$('#select_customer_vle')
														.selectpicker('render')
											}
										})
							}

							$('#district').on('change', function() {
								getVLEs();
								console.log("called change")
							})

							App.init();
							// 	FormWizardValidation.init();
							$(".selectpicker").selectpicker()

							$('#appliance_name, #scheme').on('change',
									function() {
										change();
										console.log("called change")
									})

							function change() {
								var appliance = $('#appliance_name').val();
								var scheme = $('#scheme').val();
								console.log($('#appliance_name').val()
										+ $('#scheme').val());
								if (appliance == "60 W" && scheme == "12") {
									console.log("called if")
									var price = (scheme * 2700) + 5500

									$("#customer_advance_payment").val('5500');
									$("#customer_monthly_payment").val('2700');
									$("#customer_total_payment").val(price);

								} else if (appliance == "7 W" && scheme == "12") {
									var price = (scheme * 2700) + 3000
									console.log("called if")

									$("#customer_advance_payment").val('3000');
									$("#customer_monthly_payment").val('2700');
									$("#customer_total_payment").val(price);

								} else if (appliance == "60 W"
										&& scheme == "18") {
									var price = (scheme * 2100) + 5500

									$("#customer_advance_payment").val('5500');
									$("#customer_monthly_payment").val('2100');
									$("#customer_total_payment").val(price);

								} else if (appliance == "7 W" && scheme == "18") {
									var price = (scheme * 2000) + 4000

									$("#customer_advance_payment").val('4000');
									$("#customer_monthly_payment").val('2000');
									$("#customer_total_payment").val(price);

								} else if (appliance == "60 W"
										&& scheme == "24") {
									var price = (scheme * 1800) + 5500

									$("#customer_advance_payment").val('5500');
									$("#customer_monthly_payment").val('1800');
									$("#customer_total_payment").val(price);

								} else if (appliance == "7 W" && scheme == "24") {
									var price = (scheme * 1700) + 4500

									$("#customer_advance_payment").val('4500');
									$("#customer_monthly_payment").val('1700');
									$("#customer_total_payment").val(price);

								}

								else if (appliance == "60 W" && scheme == "36") {
									var price = (scheme * 1500) + 5500

									$("#customer_advance_payment").val('5500');
									$("#customer_monthly_payment").val('1500');
									$("#customer_total_payment").val(price);

								}

								else if (appliance == "7 W" && scheme == "36") {
									var price = (scheme * 1400) + 5000

									$("#customer_advance_payment").val('5000');
									$("#customer_monthly_payment").val('1400');
									$("#customer_total_payment").val(price);

								}

							} //  end of function change

							$(
									'#sheme_l, #customer_advance_payment, #customer_monthly_payment')
									.on('change', function() {
										get();
									})

							function get() {
								var scheme = $('#scheme_l').val();
								var advance = $('#customer_advance_payment')
										.val();
								var monthly = $('#customer_monthly_payment')
										.val();
								var total = (monthly * scheme);

								var price = parseInt(total, 10)
										+ parseInt(advance, 10);
								console.log('One s : '
										+ ((monthly * scheme) + advance));
								console.log('price : ' + price);

								$('#customer_total_payment').val(price);

							}

							$('#scheme').attr("disabled", true);
							$('#customer_advance_payment').attr("disabled",
									true);
							$('#customer_monthly_payment').attr("disabled",
									true);
							$('#customer_total_payment').attr("disabled", true);

							$('#appliance_name').on('change', function() {
								$('#scheme').attr("disabled", false);
							})

							$('#scheme')
									.on(
											'change',
											function() {

												$('#customer_advance_payment')
														.prop('readonly', true);
												$('#customer_monthly_payment')
														.prop('readonly', true);
												$('#customer_total_payment')
														.prop('readonly', true);

												console.log("before");
												if ($('#scheme option:selected')
														.val() == '1') {
													console.log("before other");
													$(
															"#customer_advance_payment")
															.val('');
													$(
															"#customer_monthly_payment")
															.val('');
													$("#customer_total_payment")
															.val('');
													$(
															"#customer_appliance_scheme_new")
															.css("display",
																	"none");
													$("#customer_other_scheme")
															.css("display",
																	"block");

													$(
															'#customer_advance_payment')
															.prop('readonly',
																	false);
													$(
															'#customer_monthly_payment')
															.prop('readonly',
																	false);
													$('#customer_total_payment')
															.prop('readonly',
																	false);
												}
											});
						});
	</script>
	<!-- <script src="WebContent/js/validation.js"></script> -->
	<script
		src="assets/plugins/bootstrap-wizard/js/customer_form_wizard.js"></script>
	<script>
		function checkDate() {
			var EnteredDate = document.getElementById("from").value; //for javascript

			var EnteredDate = $("#customer_requested_date").val(); // For JQuery

			var endYear = EnteredDate.substring(0, 4);
			var endMonth = EnteredDate.substring(5, 7);
			var endDate = EnteredDate.substring(8, 10);

			var myDate = new Date(endYear, endMonth - 1, endDate);

			var today = new Date();

			if (myDate > today) {
				$("#date_message").css("display", "block");

				$("#from").val("");
			}

			else {
				$("#date_message").css("display", "none");
			}
		}
		var appId =
	<%=request.getParameter("id")%>
		$(document).ready(
				function() {
					$('#edit_appliance').on('click', function() {
						$('input:text').removeAttr("disabled");
					});
					$('#btn_update_appliance').on(
							'click',
							function() {
								console.log('hello : '
										+ $("#scheme_plan").val())
								$.ajax({
									url : "CustomerFormController",
									method : "post",
									dataType : "json",
									data : {
										action : "updatePricePlan",
										scheme_plan : $("#scheme_plan").val(),
										downpayment : $("#downpayment").val(),
										monthly_payment : $("#monthly_payment")
												.val(),
										total_payment : $("#total_payment")
												.val(),
										appName : $("#edit_appliance").val(),
										appId : appId
									},
									success : function(data) {
										$('#btn_update_appliance').prop(
												"disabled", true)
										$("#scheme_plan")
												.prop("disabled", true)
										$("#downpayment")
												.prop("disabled", true)
										$("#monthly_payment").prop("disabled",
												true)
										$("#total_payment").prop("disabled",
												true)
										$("#btn_editt_appliance").prop(
												"disabled", true)
										$('#appliance_added_status').html(
												"<b>Successfully Scheme "
														+ data.ok + "</b>")
										// 						}
									}
								});
							});
				});
		//// Edit appliance IMEI and GSM ends
	</script>

	<script type="text/javascript">
		$(document).ready(function() {

		});
	</script>

</body>
</html>