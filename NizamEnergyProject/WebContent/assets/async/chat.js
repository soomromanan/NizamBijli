var chatBox = 
'<div class="col-md-4 chat_box" style="position : fixed; bottom : -10px; right : 25px; z-index : 1000;">'
+'<!-- begin panel -->'
	+'<div class="panel panel-primary" data-sortable-id="index-2">'
		+'<div class="panel-heading">'
			+'<div class="panel-heading-btn"><a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a></div>'
			+'<p class="panel-title"></p>'
		+'</div>'
		+'<div class="panel-body bg-silver">'
			+'<div data-scrollbar="true" data-height="225px">'
				+'<ul class="chats" id="chatBox">'
//					+'<li class="left">'
//						+'<span class="date-time">yesterday 11:23pm</span>'
//						+'<a href="javascript:;" class="name">Sowse Bawdy</a>'
//						+'<a href="javascript:;" class="image"><img alt="" src="assets/img/user-12.jpg" /></a>'
//						+'<div class="message">'
//							+'Lorem ipsum dolor sit amet, consectetuer adipiscing elit volutpat. Praesent mattis interdum arcu eu feugiat.'
//						+'</div>'
//					+'</li>'
//					+'<li class="right">'
//						+'<span class="date-time">08:12am</span>'
//						+'<a href="#" class="name"><span class="label label-primary">ADMIN</span> Me</a>'
//						+'<a href="javascript:;" class="image"><img alt="" src="assets/img/user-13.jpg" /></a>'
//						+'<div class="message">'
//							+'Nullam posuere, nisl a varius rhoncus, risus tellus hendrerit neque.'
//						+'</div>'
//					+'</li>'
//					+'<li class="right">'
//						+'<span class="date-time">08:12am</span>'
//						+'<a href="#" class="name"><span class="label label-primary">ADMIN</span> Me</a>'
//						+'<a href="javascript:;" class="image"><img alt="" src="assets/img/user-13.jpg" /></a>'
//						+'<div class="message">'
//							+'Nullam posuere, nisl a varius rhoncus, risus tellus hendrerit neque.'
//						+'</div>'
//					+'</li>'
//					+'<li class="left">'
//						+'<span class="date-time">09:20am</span>'
//						+'<a href="#" class="name">Neck Jolly</a>'
//						+'<a href="javascript:;" class="image"><img alt="" src="assets/img/user-10.jpg" /></a>'
//						+'<div class="message">'
//							+'Euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.'
//						+'</div>'
//					+'</li>'
				+'</ul>'
			+'</div>'
		+'</div>'
		+'<div class="panel-footer">'
        	+'<form name="send_message_form" data-id="message-form">'
                +'<div class="input-group">'
                    +'<input type="text" class="form-control input-sm" name="message" placeholder="Enter your message here.">'
                    +'<span class="input-group-btn">'
                    	+'<button class="btn btn-primary btn-sm" type="button" id="send_message_btn">Send</button>'
                    +'</span>'
                +'</div>'
            +'</form>'
        +'</div>'
    +'</div>'
    +'<!-- end panel -->'
+'</div>'
+'<!-- end col-4 -->';
var sender;
var receiver;
var receiverInterval;

$(function(){
	sender = $('#header[data-userid]').data('userid')	
})

function openChat(receiverName, receiverId){
//	remove all chab boxes
	$('.chat_box').remove()
	
//	add chat box
	$('body').after(chatBox)
	
//	add scrollbar to chabbox
	$('[data-scrollbar]').slimScroll({
		height : '250px'
	});
	receiver = receiverId;
	loadChat()
	
	$('.chat_box .panel-title').text(receiverName)
	
	$('#send_message_btn').click(function(){send()})
	
//	register close button to remove panel chat box
	$('[data-click]').click(function(){
		
		$('.chat_box').remove()
		clearInterval(receiverInterval)
		
	})
	
	
//	receiverInterval = setInterval(receive, 5000);
}


function loadChat(){
	
		$.ajax({

		url : 'notificationChat',
		method : "post",
		data : {
			action : 'getChat',
			sender : sender,
			receiver : receiver
		},
		success : function(data) {

			chatList = JSON.parse(data).chatList;

			$.each(chatList, function(e) {

				// $.each(value, function(key, value) {
				li = document.createElement('li');
				name = ""
				if (chatList[e].sender == sender) {
					$(li).addClass('right');

				} else {
					$(li).addClass('left');

				}
				$(li).append(
						'<span class="date-time">' + chatList[e].timestamp
								+ '</span>'
								+ '<p href="javascript:;" class="name f-s-13">'
								+ chatList[e].senderName + '</p>'
								+ ' <div class="message">'
								+ chatList[e].chat_text + '</div>')
//				$(li).addClass('bg-silver')
				$('#chatBox').append(li)
				$('[data-scrollbar]').slimScroll({
					scrollTo : $('#chatBox').height()
				})
			})
		}

	})
}


function send() {
	var messageGet = $('input[name="message"]').val();
	if (messageGet !== "") {

		$.ajax({

			url : 'notificationChat',
			method : "post",
			data : {
				action : "sendMessageToDo",
				message : messageGet,
				sender : sender,
				receiver : receiver

			},

			success : function(data) {

				chat = JSON.parse(data);
				console.log("message")
				console.log(chat)
				li = document.createElement('li');
				$(li).addClass('right')
				name = chat.senderName;
				$(li).append(
						'<span class="date-time">' + chat.timestamp + '</span>'
								+ '<a href="javascript:;" class="name f-s-13">'
								+ chat.senderName + '</a>'
								+ ' <div class="message">' + chat.chat_text
								+ '</div>');

				$('#chatBox').append(li)
				$('input[name="message"]').val("")
				$('[data-scrollbar]').slimScroll({
					scrollTo : $('#chatBox').height()
				})

			}

		})

	}

}

function receive() {
	console.log("Receive Message For  "+sender+" "+receiver)
		$.ajax({

		url : 'notificationChat',
		method : "post",
		data : {
			action : "receiveUnseenReceiverChat",
			sender : receiver,
			receiver : sender
		},

		success : function(data) {
			chat = JSON.parse(data);
			console.log("message")
			console.log(chat)

			if (chat.length > 0) {
				
				$.each(chat, function(e){						
					li = document.createElement('li');
					$(li).addClass('left')
//					name = chat[e].senderName;
					$(li).append(
							'<span class="date-time">' + chat[e].chatDateTime+ '</span>'
									+ '<a href="javascript:;" class="name f-s-13">'
									+ chat[e].senderName + '</a>'
									+ ' <div class="message">' + chat[e].chatText
									+ '</div>');
					$('#chatBox').append(li)
				})
				$('[data-scrollbar]').slimScroll({
					scrollTo : $('#chatBox').height()
				})
			}

		}

	})

}