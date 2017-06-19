function verifyND() {
	var ndVerify = document.getElementById('nd_verification');
	var eligId = ndVerify.getAttribute('data-eligibilityId');

	var ndq1 = $('[name = "ndQ1"]').val();
	var ndq2 = $('[name = "ndQ2"]').val();
	var ndq3 = $('[name = "ndQ3"]').val();
	var ndq4 = $('[name = "ndQ4"]').val();
	var ndq5 = $('[name = "ndQ5"]').val();
	var ndverifiedstatus = $('[name = "btn_cc_verified_nd"]').val();

	if (ndq1.valueOf() != '' && ndq2.valueOf() != '' && ndq3.valueOf() != ''
			&& ndq4.valueOf() != '' && ndq5.valueOf() != '') {
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			datType : 'json',
			data : {
				action : 'ccVerify',
				eligId : eligId,
				ndQ1 : ndq1,
				ndQ2 : ndq2,
				ndQ3 : ndq3,
				ndQ4 : ndq4,
				ndQ5 : ndq5,
				ndVerifiedStatus : ndverifiedstatus
			},
			success : function(data) {
				console.log('action success');
				$('#ndQ1').prop('disabled', true);
				$('#ndQ2').prop('disabled', true);
				$('#ndQ3').prop('disabled', true);
				$('#ndQ4').prop('disabled', true);
				$('#ndQ5').prop('disabled', true);

				$('#btn_cc_verified_nd').prop('disabled', true);
				$("#ndMessage").css("display", "none");
			}
		});
	} else {
		$("#ndMessage").css("display", "block");
	}
}

function verifyFG() {
	var fgVerify = document.getElementById('fg_verification');
	var eligId = fgVerify.getAttribute('data-eligibilityId');

	var fgq1;
	var fgq2;
	var fgq3;

	if ($('[name = "fgQ1"]').val() == '2') {
		fgq1 = $('[name = "fgQ1_"]').val();
	} else {
		fgq1 = $('[name = "fgQ1"]').val();
	}
	if ($('[name = "fgQ2"]').val() == '2') {
		fgq2 = $('[name = "fgQ2_"]').val();
	} else {
		fgq2 = $('[name = "fgQ2"]').val();
	}
	if ($('[name = "fgQ3"]').val() == '2') {
		fgq3 = $('[name = "fgQ3_"]').val();
	} else {
		fgq3 = $('[name = "fgQ3"]').val();
	}

	var fgq4 = $('[name = "fgQ4"]').val();
	var fgq5 = $('[name = "fgQ5"]').val();
	var fgVerifiedStatus = $('[name = "btn_cc_verified_fg"]').val();

	if (fgq1.valueOf() != '' && fgq2.valueOf() != '' && fgq3.valueOf() != ''
			&& fgq4.valueOf() != '' && fgq5.valueOf() != '') {
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			datType : 'json',
			data : {
				action : 'ccVerify',
				eligId : eligId,
				fgQ1 : fgq1,
				fgQ2 : fgq2,
				fgQ3 : fgq3,
				fgQ4 : fgq4,
				fgQ5 : fgq5,
				fgVerifiedStatus : fgVerifiedStatus
			},
			success : function(data) {
				console.log('action success');

				if ($('[name = "fgQ1"]').val() == '2') {
					$('#fgQ1_').prop('disabled', true);
					$("#cncl").css("display", "none");
				} else {
					$('#fgQ1').prop('disabled', true);
				}
				if ($('[name = "fgQ2"]').val() == '2') {
					$('#fgQ2_').prop('disabled', true);
					$("#fgQ2_cncl").css("display", "none");
				} else {
					$('#fgQ2').prop('disabled', true);
				}
				if ($('[name = "fgQ3"]').val() == '2') {
					$('#fgQ3_').prop('disabled', true);
					$("#fgQ3_cncl").css("display", "none");
				} else {
					$('#fgQ3').prop('disabled', true);
				}

				$('#fgQ4').prop('disabled', true);
				$('#fgQ5').prop('disabled', true);

				$('#btn_cc_verified_fg').prop('disabled', true);
				$("#fgMessage").css("display", "none");
			}
		});
	} else {
		$("#fgMessage").css("display", "block");
	}

}

function verifyOG() {
	var ogVerify = document.getElementById('og_verification');
	var eligId = ogVerify.getAttribute('data-eligibilityId');

	var ogq1;
	var ogq2;
	var ogq3;

	if ($('[name = "ogQ1"]').val() == '2') {
		ogq1 = $('[name = "ogQ1_"]').val();
	} else {
		ogq1 = $('[name = "ogQ1"]').val();
	}
	if ($('[name = "ogQ2"]').val() == '2') {
		ogq2 = $('[name = "ogQ2_"]').val();
	} else {
		ogq2 = $('[name = "ogQ2"]').val();
	}
	if ($('[name = "ogQ2"]').val() == '2') {
		ogq3 = $('[name = "ogQ3_"]').val();
	} else {
		ogq3 = $('[name = "ogQ2"]').val();
	}

	var ogq4 = $('[name = "ogQ4"]').val();
	var ogq5 = $('[name = "ogQ5"]').val();
	var ogVerifiedStatus = $('[name = "btn_cc_verified_og"]').val();

	if (ogq1.valueOf() != '' && ogq2.valueOf() != '' && ogq3.valueOf() != ''
			&& ogq4.valueOf() != '' && ogq5.valueOf() != '') {
		$.ajax({
			url : 'CustomerFormController',
			method : 'POST',
			datType : 'json',
			data : {
				action : 'ccVerify',
				eligId : eligId,
				ogQ1 : ogq1,
				ogQ2 : ogq2,
				ogQ3 : ogq3,
				ogQ4 : ogq4,
				ogQ5 : ogq5,
				ogVerifiedStatus : ogVerifiedStatus
			},
			success : function(data) {
				console.log('action success');

				if ($('[name = "ogQ1"]').val() == '2') {
					$('#ogQ1_').prop('disabled', true);
					$("#ogQ1_cncl").css("display", "none");
				} else {
					$('#ogQ1').prop('disabled', true);
				}
				if ($('[name = "ogQ2"]').val() == '2') {
					$('#ogQ2_').prop('disabled', true);
					$("#ogQ2_cncl").css("display", "none");
				} else {
					$('#ogQ2').prop('disabled', true);
				}
				if ($('[name = "ogQ3"]').val() == '2') {
					$('#ogQ3_').prop('disabled', true);
					$("#ogQ3_cncl").css("display", "none");
				} else {
					$('#ogQ3').prop('disabled', true);
				}

				$('#ogQ4').prop('disabled', true);
				$('#ogQ5').prop('disabled', true);

				$('#btn_cc_verified_og').prop('disabled', true);
				$("#ogMessage").css("display", "none");
			}
		});
	} else {
		$("#ogMessage").css("display", "block");
	}
}

function verifyCustomer() {
	var custVerify = document.getElementById('customer_verify');
	var eligId = custVerify.getAttribute('data-eligibilityId');

	var csq1;
	var csq2;
	var csq4;

	if ($('[name = "cvQ1"]').val() == '1') {
		csq1 = $('[name = "cvQ1_"]').val();
	} else {
		csq1 = $('[name = "cvQ1"]').val();
	}
	if ($('[name = "cvQ2"]').val() == '1') {
		csq2 = $('[name = "cvQ2_"]').val();
	} else {
		csq2 = $('[name = "cvQ2"]').val();
	}

	var csq3 = $('[name = "cvQ3"]').val();

	if ($('[name = "cvQ4"]').val() == '1') {
		csq4 = $('[name = "cvQ4_"]').val();
	} else {
		csq4 = $('[name = "cvQ4"]').val();
	}

	var csq5 = $('[name = "cvQ5"]').val();
	var csq6 = $('[name = "cvQ6"]').val();

	var csVerifiedStatus = $('[name = "btn_cc_verified_customer"]').val();

	if (csq1.valueOf() != '' && csq2.valueOf() != '' && csq3.valueOf() != ''
			&& csq4.valueOf() != '' && csq5.valueOf() != ''
			&& csq6.valueOf() != '') {
		$
				.ajax({
					url : 'CustomerFormController',
					method : 'POST',
					datType : 'json',
					data : {
						action : 'ccVerify',
						eligId : eligId,
						csQ1 : csq1,
						csQ2 : csq2,
						csQ3 : csq3,
						csQ4 : csq4,
						csQ5 : csq5,
						csQ6 : csq6,
						csVerifiedStatus : csVerifiedStatus
					},
					success : function(data) {
						console.log('action success');

						if ($('[name = "cvQ1"]').val() == '1') {
							$('#cvQ1_').prop('disabled', true);
							$("#cvQ1_cncl").css("display", "none");
						} else {
							$('#cvQ1').prop('disabled', true);
						}
						if ($('[name = "cvQ2"]').val() == '1') {
							$('#cvQ2_').prop('disabled', true);
							$("#cvQ2_cncl").css("display", "none");
						} else {
							$('#cvQ2').prop('disabled', true);
						}
						$('#cvQ3').prop('disabled', true);
						if ($('[name = "cvQ4"]').val() == '1') {
							$('#cvQ4_').prop('disabled', true);
							$("#cvQ4_cncl").css("display", "none");
						} else {
							$('#cvQ4').prop('disabled', true);
						}

						$('#cvQ5').prop('disabled', true);
						$('#cvQ6').prop('disabled', true);

						$('[name = "btn_cc_verified_customer"]').prop(
								'disabled', true);
						$("#customerMessage").css("display", "none");
					}
				});
	} else {
		$("#customerMessage").css("display", "block");
	}
}
$(function() {
	$('#btn_cc_verified_all')
			.click(
					function() {

						if ($('[data-csverification]').data('csverification') != "true") {

							var csVerify = document
									.getElementById('customer_verify');

							var eligId = csVerify
									.getAttribute('data-eligibilityId');

							if (eligId != null) {

								var allVerifiedStatus = $(
										'[name = "btn_cc_verified_all"]').val();

								$.ajax({
									url : 'CustomerFormController',
									method : 'POST',
									datType : 'json',
									data : {
										action : 'ccVerify',
										eligId : eligId,
										allVerifiedStatus : allVerifiedStatus
									},
									success : function(json) {
										$('#btn_cc_verified_all').prop(
												'disabled', true);
									}
								});
							}
						}
					});
});

function getCustomerVerificationData(eligibilityID) {
	$
			.ajax({
				url : 'CustomerFormController',
				method : 'POST',
				dataType : 'json',
				data : {
					action : 'getCustomerFormVerification',
					eligibID : eligibilityID
				},
				success : function(json) {

					if (eligibilityID != null) {
						var i = 0;
						if (json.data.length) {

							if (json.data[i].slVerifiedStatus == 1) {

								$('#ndQ1').selectpicker();
								$('#ndQ1').selectpicker('val',
										json.data[i].salesmanQ1);
								$('#ndQ1').prop('disabled', true);

								$('#ndQ2').selectpicker();
								$('#ndQ2').selectpicker('val',
										json.data[i].salesmanQ2);
								$('#ndQ2').prop('disabled', true);

								$('#ndQ3').selectpicker();
								$('#ndQ3').selectpicker('val',
										json.data[i].salesmanQ3);
								$('#ndQ3').prop('disabled', true);

								$('#ndQ4').selectpicker();
								$('#ndQ4').selectpicker('val',
										json.data[i].salesmanQ4);
								$('#ndQ4').prop('disabled', true);

								$('#ndQ5').selectpicker();
								$('#ndQ5').selectpicker('val',
										json.data[i].salesmanQ5);
								$('#ndQ5').prop('disabled', true);

								$('#btn_cc_verified_nd').prop('disabled', true);
							}

							if (json.data[i].fgVerifiedStatus == 1) {

								console.log('fg verified status: '
										+ json.data[i].customerVerifiedStatus);

								if (json.data[i].familyGuarantorQ1.valueOf() == '0'
										|| json.data[i].familyGuarantorQ1
												.valueOf() == '1') {
									$('#fgQ1').selectpicker();
									$('#fgQ1').selectpicker('val',
											json.data[i].familyGuarantorQ1);
									$('#fgQ1').prop('disabled', true);
								} else {
									$("#cncl").css("display", "none");
									$("#fgQ1").css("display", "none");
									$("#fgQ1_diff_info")
											.css("display", "block");
									$('#fgQ1_').val(
											json.data[i].familyGuarantorQ1);
									$('#fgQ1_').prop('disabled', true);
								}

								if (json.data[i].familyGuarantorQ2.valueOf() == '0'
										|| json.data[i].familyGuarantorQ2
												.valueOf() == '1') {
									$('#fgQ2').selectpicker();
									$('#fgQ2').selectpicker('val',
											json.data[i].familyGuarantorQ2);
									$('#fgQ2').prop('disabled', true);
								} else {
									$("#fgQ2_cncl").css("display", "none");
									$("#fgQ2").css("display", "none");
									$("#fgQ2_diff_info")
											.css("display", "block");
									$('#fgQ2_').val(
											json.data[i].familyGuarantorQ2);
									$('#fgQ2_').prop('disabled', true);
								}

								if (json.data[i].familyGuarantorQ3.valueOf() == '0'
										|| json.data[i].familyGuarantorQ3
												.valueOf() == '1') {
									$('#fgQ3').selectpicker();
									$('#fgQ3').selectpicker('val',
											json.data[i].familyGuarantorQ3);
									$('#fgQ3').prop('disabled', true);
								} else {
									$("#fgQ3_cncl").css("display", "none");
									$("#fgQ3").css("display", "none");
									$("#fgQ3_diff_info")
											.css("display", "block");
									$('#fgQ3_').val(
											json.data[i].familyGuarantorQ3);
									$('#fgQ3_').prop('disabled', true);
								}

								$('#fgQ4').selectpicker();
								$('#fgQ4').selectpicker('val',
										json.data[i].familyGuarantorQ4);
								$('#fgQ4').prop('disabled', true);

								$('#fgQ5').selectpicker();
								$('#fgQ5').selectpicker('val',
										json.data[i].familyGuarantorQ5);
								$('#fgQ5').prop('disabled', true);
								$('#btn_cc_verified_fg').prop('disabled', true);
							}
							if (json.data[i].ogVerifiedStatus == 1) {

								console.log('og verified status: '
										+ json.data[i].ogVerifiedStatus);

								if (json.data[i].outsideGuarantorQ1.valueOf() == '0'
										|| json.data[i].outsideGuarantorQ1
												.valueOf() == '1') {
									$('#ogQ1').selectpicker();
									$('#ogQ1').selectpicker('val',
											json.data[i].outsideGuarantorQ1);
									$('#ogQ1').prop('disabled', true);
								} else {
									$("#ogQ1_cncl").css("display", "none");
									$("#ogQ1").css("display", "none");
									$("#ogQ1_diff_info")
											.css("display", "block");
									$('#ogQ1_').val(
											json.data[i].outsideGuarantorQ1);
									$('#ogQ1_').prop('disabled', true);
								}

								if (json.data[i].outsideGuarantorQ2.valueOf() == '0'
										|| json.data[i].outsideGuarantorQ2
												.valueOf() == '1') {
									$('#ogQ2').selectpicker();
									$('#ogQ2').selectpicker('val',
											json.data[i].outsideGuarantorQ2);
									$('#ogQ2').prop('disabled', true);
									'<br><br><br>';
								} else {
									$("#ogQ2_cncl").css("display", "none");
									$("#ogQ2").css("display", "none");
									$("#ogQ2_diff_info")
											.css("display", "block");
									$('#ogQ2_').val(
											json.data[i].outsideGuarantorQ2);
									$('#ogQ2_').prop('disabled', true);
								}

								if (json.data[i].outsideGuarantorQ3.valueOf() == '0'
										|| json.data[i].outsideGuarantorQ3
												.valueOf() == '1') {
									$('#ogQ3').selectpicker();
									$('#ogQ3').selectpicker('val',
											json.data[i].outsideGuarantorQ3);
									$('#ogQ3').prop('disabled', true);
								} else {
									$("#ogQ3_cncl").css("display", "none");
									$("#ogQ3").css("display", "none");
									$("#ogQ3_diff_info")
											.css("display", "block");
									$('#ogQ3_').val(
											json.data[i].outsideGuarantorQ3);
									$('#ogQ3_').prop('disabled', true);
								}

								$('#ogQ4').selectpicker();
								$('#ogQ4').selectpicker('val',
										json.data[i].outsideGuarantorQ4);
								$('#ogQ4').prop('disabled', true);

								$('#ogQ5').selectpicker();
								$('#ogQ5').selectpicker('val',
										json.data[i].outsideGuarantorQ5);
								$('#ogQ5').prop('disabled', true);

								$('#btn_cc_verified_og').prop('disabled', true);
							}
							if (json.data[i].customerVerifiedStatus == 1) {

								console.log('cs verified status: '
										+ json.data[i].customerVerifiedStatus);

								if (json.data[i].customerQ1.valueOf() == '0') {
									$('#cvQ1').selectpicker();
									$('#cvQ1').selectpicker('val',
											json.data[i].customerQ1);
									$('#cvQ1').prop('disabled', true);
								} else {
									$("#cvQ1_cncl").css("display", "none");
									$("#cvQ1").css("display", "none");
									$("#cvQ1_diff_info")
											.css("display", "block");
									$('#cvQ1_').val(json.data[i].customerQ1);
									$('#cvQ1_').prop('disabled', true);
								}
								if (json.data[i].customerQ2.valueOf() == '0') {
									$('#cvQ2').selectpicker();
									$('#cvQ2').selectpicker('val',
											json.data[i].customerQ2);
									$('#cvQ2').prop('disabled', true);
								} else {
									$("#cvQ2_cncl").css("display", "none");
									$("#cvQ2").css("display", "none");
									$("#cvQ2_diff_info")
											.css("display", "block");
									$('#cvQ2_').val(json.data[i].customerQ2);
									$('#cvQ2_').prop('disabled', true);
								}
								$('#cvQ3').selectpicker();
								$('#cvQ3').selectpicker('val',
										json.data[i].customerQ3);
								$('#cvQ3').prop('disabled', true);

								if (json.data[i].customerQ4.valueOf() == '0') {
									$('#cvQ4').selectpicker();
									$('#cvQ4').selectpicker('val',
											json.data[i].customerQ4);
									$('#cvQ4').prop('disabled', true);
								} else {
									$("#cvQ4_cncl").css("display", "none");
									$("#cvQ4").css("display", "none");
									$("#cvQ4_diff_info")
											.css("display", "block");
									$('#cvQ4_').val(json.data[i].customerQ4);
									$('#cvQ4_').prop('disabled', true);
								}
								$('#cvQ5').selectpicker();
								$('#cvQ5').selectpicker('val',
										json.data[i].customerQ5);
								$('#cvQ5').prop('disabled', true);

								$('#cvQ6').selectpicker();
								$('#cvQ6').selectpicker('val',
										json.data[i].customerQ6);
								$('#cvQ6').prop('disabled', true);

								$('#btn_cc_verified_customer').prop('disabled',
										true);
							}
							if (json.data[i].verifiedAllStatus == 1) {
								$('#btn_cc_verified_all')
										.prop('disabled', true);
							}
						}
					}
				}
			});
}
