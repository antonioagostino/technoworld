function disable() {
	document.getElementById("buttonPassword").disabled = true;
}

function getSecurityQuestion() {
	
	var username = $("#username").val();
	if(username != '') {
		
		$.ajax({
				type: "GET",
				url: "passwordRecovery",
				data: {usernameForQuestion: username},
				success: function(data){
					
					if(data == "invalid") {
						$("#wrongUsername").css('visibility', 'visible');
						
					  	 $("#username").css('border', '1px solid #d70000');
					  	 $("#username").focusout(function() {
					  		$("#username").css('color', '#d70000');
					  		  $("#username").css('box-shadow', '0 0 0');
					  	  });
					  	  $("#username").focus(function() {
					  		  $("#username").css('color', 'black');
					  		  $("#username").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
					  	  });
					}
					
					else {
						document.getElementById("buttonUsername").disabled = true;
						document.getElementById("buttonPassword").disabled = false;
						
						$("#wrongUsername").css('visibility', 'hidden');
						
						$("#username").css('color', 'black');
					  	$("#username").css('border', '');
						$("#securityQuestion").val(data);
						$("#securityQuestion").css("background-color", "white");
					}
				}
			}
		);
	}
}


function getPassword() {
		
	var username = $("#username").val();
	var answer = $("#securityAnswer").val();
	
	if(answer != '') {
			var label = document.createElement("label");
			label.setAttribute("id", "labelPassword");
			label.innerHTML = "Inserisci la Nuova Password";		
			
			var input = document.createElement("input");
			input.setAttribute("id", "inputPassword");
			input.setAttribute("type","text"); 
			input.setAttribute("class","form-control");
			input.setAttribute("placeholder", "Nuova Password");
			input.setAttribute("type", "password");
		
			$.ajax({
				type: "GET",
				url: "passwordRecovery",
				data: {usernameForPassword: username, answer: answer},
				success: function(data) {
					
					if(data == "invalid") {
						 $("#wrongAnswer").css('visibility', 'visible');
		
						 $("#securityAnswer").css('color', '#d70000');
					  	 $("#securityAnswer").css('border', '1px solid #d70000');
					  	 $("#securityAnswer").focusout(function() {
					  		  $("#securityAnswer").css('box-shadow', '0 0 0');
					  		  $("#securityAnswer").css('color', '#d70000');
					  		
					  	  });
					  	  $("#securityAnswer").focus(function() {
					  		  $("#securityAnswer").css('box-shadow', '0px 0px 0px 3px rgba(215, 0, 0, 0.2)');
					  		  $("#securityAnswer").css('color', 'black');
					  	  });
					}
					
					else {
						document.getElementById("buttonPassword").disabled = true;
						
						$("#wrongAnswer").css('visibility', 'hidden');
						$("#buttonNewPassword").css('visibility', 'visible');
						
					  	$(".form-group").append(label);
						$(".form-group").append(input);
						$("#securityAnswer").css('color', 'black');
					  	$("#securityAnswer").css('border', '');
						$("#inputPassword").css("background-color", "white");
					}
				}
			}
		);
	}
}

function saveNewPassword() {
	
	var username = $("#username").val();
	var newPassword = $("#inputPassword").val();
	
	$.ajax({
		type: "POST",
		url: "passwordRecovery",
		data: {username: username, newPassword: newPassword},
		success: function(data) {
			
			if(data == "invalidNewPassword") 
				$("#invalidNewPassword").css('visibility', 'visible');
			else {
				$("#invalidNewPassword").css('visibility', 'hidden');
				$("#inputPassword").val("");
				$("#buttonNewPassword").val("Password aggiornata");
				document.getElementById("buttonNewPassword").disabled = true;
			}
		}
	});
}