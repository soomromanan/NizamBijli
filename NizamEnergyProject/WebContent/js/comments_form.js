$(function() {
	get_comments_data();
})

get_comments_data(){
	var text = $("[name = 'text']").val();
	console.log(text);
}