// 30 minute session on Mouse move 
var min = 30;
var sec = 30;

var timer = setInterval(function() {
	if (sec <= 0) {
		if (min <= 0 && sec <= 0) {
			clearInterval(timer);
		}
		if (min > 0) {
			sec = 60;
		}
		min--;
	}
	sec--;
}, 1000);

$("body").mousemove(function() {

	if (min <= 0 && sec <= 0) {

		window.location = "UserLoginController?click=logout";
	} else {
		min = 30;
		sec = 30;
	}
});

$(function() {
	$('.dropdown>a>span').hide();
	$('.dropdown-header>span').hide()
	countUnseenMessages();
	getUnseenMessages();
	getUnseenRequest();
	getUnseenRequestForBooking();
	setInterval(updateHeaderNotification, 300000)
})

function updateHeaderNotification() {
	countUnseenMessagesFromAppliance();
	getUnseenMessagesFromAppliance()

}

// Begin Count Unseen Messages From Appliances
function countUnseenMessagesFromAppliance() {
	$.ajax({
		url : 'Alerts',
		method : 'POST',
		data : {
			action : 'countUnseenSAAlarms'
		},
		dataType : 'json',
		success : function(data) {
			if (data.countAlerts > 0) {
				$('#appliance_messages a span').text(data.countAlerts)
				$('#appliance_messages a span').show();
			} else {
				$('#appliance_messages a span').hide();
				$('#appliance_messages ul .dropdown-header span').hide()
			}
		}

	})
}

// End Count Unseen Messages from Appliances

var noMessageLi = '<li class="media"  id="no_message">'
		+ '<a href="javascript:;">' + '<div class="media-body">'
		+ '<h6 class="media-heading">No Alarms</h6>' + '</div>' + '</a>'
		+ '</li>'

// Begin Get Unseen Messages From Appliance
function getUnseenMessagesFromAppliance() {
	$
			.ajax({
				url : 'Alerts',
				method : 'POST',
				data : {
					action : 'getUnseenSAAlarms'
				},
				dataType : 'json',
				success : function(data) {
					$('#appliance_messages ul li.data').remove();
					if (data.length > 0) {
						if ($('#appliance_messages ul #no_message')) {
							$('#appliance_messages ul #no_message').hide()
						}
						$
								.each(
										data,
										function(e) {
											var li = '<li class="media data">'
													+ '<a href="ViewServlet?click=viewAppliance&id='
													+ data[e].applianceId
													+ '">'
													+ '<div class="media-left"><i class="fa fa-bell media-object bg-blue"></i></div>'
													+ '<div class="media-body">'
													+ '<h6 class="media-heading">'
													+ data[e].messageFrom
													+ '</h6>'
													+ '<p class="text-muted">'
													+ data[e].messageDate
													+ '</p>' + '</div>'
													+ '</a>' + '</li>'
											$(
													'#appliance_messages ul .dropdown-footer')
													.before(li);
										})

						if (data.length > 5) {
							$('#appliance_messages ul li.dropdown-footer')
									.show()
						}
						// $('#appliance_messages ul').append(footer);
					} else {
						$('#appliance_messages ul li.dropdown-footer').hide()
						$('#appliance_messages ul #no_message').show()
					}
				}

			})
}

// End Get Unseen Messages From Appliances

function countUnseenMessages() {
	$.ajax({
		url : 'CommentsController',
		method : 'POST',
		data : {
			click : 'countUnseenMessages'
		},
		dataType : 'json',
		success : function(data) {
			console.log("UnseenRequest Success" + data.countMessages)
			if (data.countMessages > 0) {
				$('#messages a span').text(data.countMessages)
				$('#messages a span').show();
			} else {
				$('#messages a span').hide();
				$('#messages ul .dropdown-header span').hide()
			}
		}

	})
}

var noMessageLi = '<li class="media"  id="no_loanbook_message">'
		+ '<a href="javascript:;">' + '<div class="media-body">'
		+ '<h6 class="media-heading">No Messages</h6>' + '</div>' + '</a>'
		+ '</li>'

function getUnseenMessages() {
	$
			.ajax({
				url : 'CommentsController',
				method : 'POST',
				data : {
					click : 'showUnseenMessages'
				},
				dataType : 'json',
				success : function(data) {
					$('#messages ul li.data').remove()

					if (data.length > 0) {
						if ($('#messages ul #no_loanbook_message')) {
							$('#messages ul #no_loanbook_message').hide()
						}
						$
								.each(
										data,
										function(e) {
											var li = '<li class="media data">'
													+ '<a href="PaymentLoanServlet?appliace_key='
													+ data[e].applianceId
													+ '">'
													+ '<div class="media-left"><i class="fa fa-envelope m-r-5"></i></div>'
													+ '<div class="media-body">'
													+ '<h6 class="media-heading">'
													+ data[e].messageFrom
													+ '</h6>'
													+ '<p class="text-muted">'
													+ data[e].messageDate
													+ '</p>' + '</div>'
													+ '</a>' + '</li>'
											$('#messages ul .dropdown-footer')
													.before(li);
										})

						if (data.length > 5) {
							$('#messages ul li.dropdown-footer').show()
						}
						// $('#appliance_messages ul').append(footer);
					} else {
						$('#messages ul li.dropdown-footer').hide()
						$('#messages ul #no_loanbook_message').show()
					}
				}

			})
}

function getUnseenRequest() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getUnseenRequest"
		},
		success : function(data) {
			$('a #unseen_loan_request_count').empty()
			if (data.request > 0) {
				$('a #unseen_loan_request_count').text(data.request)
			} else {
				$('a #unseen_loan_request_count').empty()
			}
		}
	})
}

function getUnseenRequestForBooking() {
	$.ajax({
		url : 'SuperAdminDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getUnseenRequestForBooking"
		},
		success : function(data) {
			$('a #unseen_booking_count').empty()
			if (data.request > 0) {
				$('a #unseen_booking_count').text(data.request)
			} else {
				$('a #unseen_booking_count').empty()
			}
		}
	})
}
