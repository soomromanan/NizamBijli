function insertCashCustomers() {
	var CashCCity = $('[name = "CashCCity"]').val();
	var cfirstname = $('[name = "cfirstname"]').val();
	var cashCustomerCnic = $('[name = "cashCustomerCnic"]').val();
	var cashCPhone = $('[name = "cashCPhone"]').val();
	var cashCAddress = $('[name = "cashCAddress"]').val();
	var CashCFoId = $('[name = "CashCFoId"]').val();
	var cashCNdId = $('[name = "cashCNdId"]').val();
	var cashCAppliance = $('[name = "cashCAppliance"]').val();
	var cashCPrice = $('[name = "cashCPrice"]').val();
	var cashCConsumerNo = $('[name = "cashCConsumerNo"]').val();
	var cashCGsmNumber = $('[name = "cashCGsmNumber"]').val();
	var cashCImeiNumber = $('[name = "cashCImeiNumber"]').val();
	var cashCPaymentMethod = $('[name = "cashCPaymentMethod"]:checked').val();

	if (CashCCity != "null" && cfirstname != "" && cashCustomerCnic != ""
			&& cashCPhone != "" && cashCAddress != "" && CashCFoId != "null"
			&& cashCNdId != "" && cashCPrice != "" && cashCAppliance != ""
			&& cashCConsumerNo != "" && cashCGsmNumber != ""
			&& cashCImeiNumber != "" && cashCPaymentMethod != "") {

		$.ajax({
			url : 'AddCashCustomer',
			method : 'POST',
			datType : 'json',
			data : {
				action : 'addCashCustomers',
				CashCCity : CashCCity,
				cfirstname : cfirstname,
				cashCustomerCnic : cashCustomerCnic,
				cashCAddress : cashCAddress,
				CashCFoId : CashCFoId,
				cashCNdId : cashCNdId,
				cashCPhone : cashCPhone,
				cashCPrice : cashCPrice,
				cashCAppliance : cashCAppliance,
				cashCConsumerNo : cashCConsumerNo,
				cashCGsmNumber : cashCGsmNumber,
				cashCImeiNumber : cashCImeiNumber,
				cashCPaymentMethod : cashCPaymentMethod
			},
			success : function(json) {
				console.log(json)
				if (json.id != "0") {
					$('#customer_added').text("Successfully Cutomer Added")
					$('#cashCustomerId').prop('disabled', true)
				} else {
					$('#customer_added').text("<b>Cutomer Not Added</b>")
					$('#cashCustomerId').prop('disabled', true)
				}
			}
		});
	}

}