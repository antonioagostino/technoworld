
$(document).ready(function(){
	  
	 $("#findAStore").click(function() {
		window.location.href = "map";
	 });
	 
	 $(".fa-2x").hover(function(){
		    $(this).css("color", "#feac01");
		    }, function(){
		    $(this).css("color", "white");
	 });
	 
	 
	
	 
});


function registerToNewsletter() {
	 $(".fa-angle-right").remove();
	 
	 $('#reg').html("<span class=\"spinner-border spinner-border-sm\"></span>");
	 
	 var email = $("#email").val();
	 
	 if(validateEmail(email)){
		 $.post("newsletter",{ 	email : email }, function(result){
		   	    if(result == 1){
		   	    	$(".spinner-border").remove();
			 		$("#reg").append("<i class=\"fas fa-angle-right\">");
		   	    }
		   	 else {
			    	$(".spinner-border").remove();
			 		$("#reg").append("<i class=\"fas fa-angle-right\">");	
			 }
		   	    
		 });
	 }
	 else {
	    	$(".spinner-border").remove();
	 		$("#reg").append("<i class=\"fas fa-angle-right\">");	
	 }
	 
	 
}

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}