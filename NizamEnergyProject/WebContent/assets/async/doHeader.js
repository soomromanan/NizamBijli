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
	getUnseenRequestForBookingDO();
	getUnseenRequest();
})

function getUnseenRequest() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getUnseenRequest"
		},
		success : function(data) {
			// console.log("UnseenRequest Success")
			// console.log(data)
			$('a #do_unseen_loan_request_count').empty()
			if (data.request > 0) {
				$('a #do_unseen_loan_request_count').text(data.request)
			} else {
				$('a #do_unseen_loan_request_count').empty()
			}
		}
	})
}

function getUnseenRequestForBookingDO() {
	$.ajax({
		url : 'DoDashboardController',
		method : 'POST',
		dataType : 'json',
		data : {
			action : "getUnseenBookings"
		},
		success : function(data) {
			console.log(data.bookings)
			$('a #do_unseen_bookings_count').empty()
			if (data.bookings > 0) {
				$('a #do_unseen_bookings_count').text(data.bookings)
			} else {
				$('a #do_unseen_bookings_count').empty()
			}
		}
	})
}