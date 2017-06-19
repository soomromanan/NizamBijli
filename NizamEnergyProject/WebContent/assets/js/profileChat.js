$(function() {
	sender = $('#chatBoxProfile').data('sender');
	receiver = $('#chatBoxProfile').data('receiver');
	senderName = $('#chatBoxProfile').data('sendername');
	receiverName = $('#chatBoxProfile').data('receivername');
	customer = $('#chatBoxProfile').data('customerid');
	loadChatBox();
	$('#send-message').click(function(e) {sendAndUpdate()})	
})
function sendAndUpdate(){
	messageText = $('input[name="message"]').val();
	if (messageText !== "") {
		$.ajax({
			url : 'SaDoChatServlet',
//			url : '/NizamEnergyProject/SaDoChatServlet',
			method : 'post',
			data : {
				action : "sendMessage",
				message : messageText,
				sender : sender,
				receiver : receiver,
				customer : customer
			},
			success : function(data) {
				chat = JSON.parse(data)
				li = document.createElement('li');
				$(li).addClass('left')
				name = senderName;
				$(li).append('<span class="date-time">'
						+ chat.timeStamp + '</span>'
						+ '<a href="javascript:;" class="name">'
						+ chat.senderName + '</a>' + ' <div class="message">'
						+ chat.chatText + '</div>');
				$('#chatBoxProfile').append(li)
				$('input[name="message"]').val("")
				scrolldown();
			}
		})
	} else {
//		alert("Empty")
	}
}

function loadChatBox() {
//	alert(sender+""+receiver+""+customer)
	
	if($('#chatBoxProfile').html() !== undefined){
		$.ajax({
			url : 'SaDoChatServlet',
//			url : '/NizamEnergyProject/SaDoChatServlet',
			method : 'post',
			data : {
				action : "getChatList",
				sender : receiver,
				receiver : sender,
				customer : customer
			},
			// dataType : 'json',
			success : function(data) {

				data = JSON.parse(data).chatList;

				$.each(data, function(key, value) {

					// $.each(value, function(key, value) {
					li = document.createElement('li');
					name = ""
					if (value.sender == sender) {
						$(li).addClass('left');
						name = senderName
					} else {
						$(li).addClass('right');
						name = receiverName
					}
					$(li).append(
							'<span class="date-time">' + value.timeStamp
									+ '</span>'
									+ '<a href="javascript:;" class="name">'
									+ value.senderName + '</a>' + ' <div class="message">'
									+ value.chatText + '</div>')
					$('#chatBoxProfile').append(li)
					scrolldown();
				})
			}
		})
	}
	
}
function scrolldown() {
	$('[data-scrollbar=true]').slimScroll({
		scrollTo : $('#chatBoxProfile').height()
	})

}