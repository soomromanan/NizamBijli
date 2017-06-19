$(function() {
	getDoRecovery();
	getDoOverduePayments();
	getDoDefaulters();
	getDoInstallations();
	getAnalyticsData();
	getDoSales();
	getDoPerformanceDetails();
	getDoTopFiveNDs();
	getDoLoanStatus();
})

function updateDashboardWidget() {
	getDoRecovery();
	getDoOverduePayments();
	getDoDefaulters();
	getDoInstallations();
	getAnalyticsData();
	getDoSales();
	getDoPerformanceDetails();
	getDoTopFiveNDs();
	getDoLoanStatus();
}

function formatNumber(number) {
	number = number.toFixed(2) + '';
	x = number.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1;
}

function getDoRecovery() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoRecovery"
		},
		success : function(data) {
			console.log(data)
			$('#recovery_widget .stats-number').text(
					formatNumber(parseInt(data.amount)) + " PKR")
			$('#recovery_widget .progress-bar').width(data.percentage + "%")
			$('#recovery_widget .stats-desc')
					.text(
							formatNumber(parseInt(data.percentage))
									+ "% Recovery Rate")
		}
	})
}

function getDoOverduePayments() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoOverduePayments"
		},
		success : function(data) {
			console.log(data)
			$('#overdue_widget .stats-number').text(data.total);
			$('#overdue_widget .stats-desc').text("Late Payments");
		}
	})
}

function getDoDefaulters() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoDefaulters"
		},
		success : function(data) {
			console.log(data)
			$('#defaulter_widget .stats-number').text(data.total)
			$('#defaulter_widget .stats-desc').text("Defaults")
		}
	})
}

function getDoInstallations() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoInstallations"
		},
		success : function(data) {
			$('#installed_widget .stats-number').text(data.total)
			$('#installed_widget .stats-desc').text(
					"Current Month Installations")
		}
	})
}

function getDoSales() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoSales"
		},
		success : function(data) {
			console.log(data)
			$('#totalApps').text(data.toatlApps)
			$('#currentMonthApps').text(data.currentMonthApps)
			$('#installed').text(data.installed + " %")
			$('#currentWeekApps').text(data.currentWeekApps)
		}
	})
}

function getDoLoanStatus() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoLoanStatus"
		},
		success : function(data) {
			console.log(data)
			$('#Owned').text(data.owned)
			$('#Maintain').text(data.maintained)
			$('#Defaulter').text(data.defaulter)
			$('#Late').text(data.late)
			$('#cash').text(data.cash)
		}
	})
}

function getAnalyticsData() {
	$
			.ajax({
				url : 'DoDashboardController',
				method : 'post',
				dataType : 'json',
				data : {
					action : "getTotalCustomersAndAppliances"
				},
				success : function(data) {
					$('#toVerifybyCCDO').text(data.countCCVerifyCustomerDo);
					$('#countPendingCustomers').text(
							data.countPendingCustomersDo)
					$('#countAcceptedCustomers').text(
							data.countAcceptedCustomersDo)
					$('#countVarifiedCustomers').text(
							data.countVarifiedCustomersDo)

					// Ready To Assign
					$('#ReadyToAssign')
							.text(
									data.countAllStatusAndApplianceStatusDo.readyToAssign)

					// Owned
					$('#AwaitingDownPayment')
							.text(
									data.countAllStatusAndApplianceStatusDo.awaitingForDownpayment)
					$('#RDP').text(data.countAllStatusAndApplianceStatusDo.rdp)

					// installed
					$('#Installed')
							.text(
									parseInt(data.countAllStatusAndApplianceStatusDo.installed)
											+ parseInt(data.countAllStatusAndApplianceStatusDo.installedNoSignal))
					$('#returned').text(
							data.countAllStatusAndApplianceStatusDo.returned)
				}
			})
}

function getDoPerformanceDetails() {
	var to = document.getElementById("to").value;
	var from = document.getElementById("from").value;
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoPerformanceDetails",
			to : to,
			from : from
		},
		success : function(data) {
			console.log(data)
			$('#fos').empty()
			$('#nds').empty()
			$('#active_nds').empty()
			$('#installation').empty()
			$('#total').empty()
			$('#fos').text(data.fos)
			$('#nds').text(data.nds)
			$('#active_nds').text(data.active_nds)
			$('#installation').text(data.installed)
			$('#total').text(data.total)
		}
	})
}

function getDoTopFiveNDs() {
	var to = document.getElementById("to_1").value;
	var from = document.getElementById("from_1").value;
	console.log("hdhdhdh" + to + " " + from);
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getDoTopFiveNDs",
			to : to,
			from : from
		},
		success : function(data) {
			console.log(data)
			$('#top_five_nds').empty();
			if (data.length > 0) {
				$.each(data, function(e) {
					var tds = '<tr>' + '<td>' + data[e].ndName + '</td>'
							+ '<td>' + data[e].installed + '</td>' + '<td>'
							+ data[e].total + '</td>' + '</tr>';
					$("#top_five_nds").append(tds);
				})
			}
		}
	})
}
