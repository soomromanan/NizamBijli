$(function(){
	
	
	
	$('input.name').on('blur',function(){
		var element = $(this)
		var string = $(element).val()
		var sentence = ""
		$.each(string.split(" "), function(i, ele){
			sentence += (ele.charAt(0).toUpperCase() + ele.substr(1)).trim()+" "
//			console.log(sentence)
		})
		$(element).val(sentence.trim())
	})
})