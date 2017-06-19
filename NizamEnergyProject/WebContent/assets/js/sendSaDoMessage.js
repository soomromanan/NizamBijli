$(function() {
	var customerid
	var said
	var districtofficerid
	var doid
	
	$('a[href="#modal-alert-info"]').click(function(e){
		customerid = $(this).data('customerid')
		said = $(this).data('said')
		doid = $(this).data('doid')
		$('#customerText').val("")
	})
	
	$
	

	$('#sendMessage').click(function(e) {
		messageText = $('#customerText').val()
		if (messageText !== null) {
			$.ajax({
				url : 'SaDoChatServlet',
		//		url : '/NizamEnergyProject/SaDoChatServlet',
				method : 'post',
				data :{
					action : "sendMessage",
					customer : customerid,
					sender : said,
					receiver : doid,
					message : messageText
				}
			})
			
		}
	})
	$('#send-message').click(function(data) {
		messageText = $('#message').val()
		if (messageText !== null) {
			$.ajax({
				url : 'SaDoChatServlet',
			//	url:'/NizamEnergyProject/SaDoChatServlet',
				method : 'post',
				data : {
					action : "DoSaSendMessage",
					doid : districtofficerid,
					customerid : customerid,
					messageText : messageText
				},
				success : function(data){
											chat = JSON.parse(data)
											li = document.createElement('li');
											$(li).addClass('right')
											$(li).append('<span class="date-time">'
																	+ chat.timeStamp
																	+ '</span>'
																	+ '<a href="javascript:;" class="name">'
																	+ chat.senderName
																	+ '</a>'
																	+ ' <div class="message">'
																	+ chat.chatText
																	+ '</div>')
											$('#chatBox').append(li)
											$('#message').val("")
											scrolldown();
				}
			})
		}
	})
	
	
	$('a[href="#modal-alert-view"]').click(function(e) {
		customerid = $(this).data('customerid')
		districtofficerid = $(this).data('doid')
		$(this).text("Message")
		$('#chatBox').empty()
//		alert(customerid + " " + districtofficerid)
	})

	$('#modal-alert-view').on('shown.bs.modal', function(e) {
		$.ajax({
			url : 'SaDoChatServlet',
		//	url : '/NizamEnergyProject/SaDoChatServlet',
			method : 'post',
			data : {
				action : 'getDOChatList',
				senderid : districtofficerid,
				customerid : customerid
			},
			success : function(data) {
				$.each(JSON.parse(data), function(key, value) {
					li = document.createElement('li');
					if (districtofficerid === value.sender) {
						$(li).addClass('right')
					}else{
						$(li).addClass('left')
					}
					$(li).append(
							'<span class="date-time">' + value.timeStamp
									+ '</span>'
									+ '<a href="javascript:;" class="name">'
									+ value.senderName + '</a>' + ' <div class="message">'
									+ value.chatText + '</div>')
					$('#chatBox').append(li)
					scrolldown();
				})
			}
		})
	})
})
function scrolldown() {
	$('[data-scrollbar=true]').slimScroll({
		scrollTo : $('#chatBox').height()
	})

}