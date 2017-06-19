
//linking table row
$(function(e) {
	redirect();
	
});
function redirect(){
	$("tr.link").on('click', function(event) {
		console.log('clicked');
		link = $(this).data('link');
		if (link != undefined) {
			window.location=link;			
		}
	});
	$("tr.link-not-last-cell>td:not(:last-child)").on('click', function(event) {
		link = $(this).parent().data('link');
		if (link != undefined) {
			window.location=link;			
		}
	});
	$("tr.link-not-first-child>td:not(:first-child)").on('click', function(event) {
		link = $(this).parent().data('link');
		if (link != undefined) {
			window.location=link;			
		}
	});
	
	
	$("tr.click-appliance-row").on('click', function(event) {
		console.log('clicked');
		link = $(this).data('link');
		if (link != undefined) {
			var data = link.split(',');
			
			getLoanBook(data[0], data[1]);
			
		}
	});
}