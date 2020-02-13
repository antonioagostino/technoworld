function writeTexts(name, surname, date, username, email, question, answer, sameUsername, sameEmail, invalidPassword, invalidEmail) {
	
	$("#name").val(name);
	$("#surname").val(surname);
	$("#date").val(date);
	$("#username").val(username);
	$("#email").val(email);
	$("#question").val(question);
	$("#answer").val(answer);
		
	if(sameUsername == "true") {
  	  
	  $("#username").css('color', '#d70000');
  	  $("#username").css('border', '1px solid #d70000');
  	  $("#username").focusout(function() {
  		  $("#username").css('box-shadow', '0 0 0');
  		
  	  });
  	  $("#username").focus(function() {
  		  $("#username").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
  		  $("#username").css('color', 'black');
  	  });
  	
    }
    if(sameEmail == "true") {
  	  $("#email").css('color','#d70000');
  	  $("#email").css('border', '1px solid #d70000');
  	  $("#email").focusout(function() {
  		  $("#email").css('box-shadow', '0 0 0');
  	  });
  	  $("#email").focus(function() {
  		  $("#email").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
  		  $("#email").css('color', 'black');
  	  });
    }
    
    if(invalidPassword == "true") {
	  $("#password").css('color','#d70000');
	  $("#password").css('border', '1px solid #d70000');
	  $("#password").focusout(function() {
		  $("#password").css('box-shadow', '0 0 0');
	  });
	  $("#password").focus(function() {
		  $("#password").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
		  $("#password").css('color', 'black');
	  });
    }
    
    if(invalidEmail == "true") {
    	  $("#email").css('color','#d70000');
    	  $("#email").css('border', '1px solid #d70000');
    	  $("#email").focusout(function() {
    		  $("#email").css('box-shadow', '0 0 0');
    	  });
    	  $("#email").focus(function() {
    		  $("#email").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
    		  $("#email").css('color', 'black');
    	  });
      }
}




