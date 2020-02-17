(function() {
		  'use strict';
		  window.addEventListener('load', function() {
		    // Get the forms we want to add validation styles to
		    var forms = document.getElementsByClassName('needs-validation');
		    // Loop over them and prevent submission
		    var validation = Array.prototype.filter.call(forms, function(form) {
		      form.addEventListener('submit', function(event) {
		        if (form.checkValidity() === false) {
		          event.preventDefault();
		          event.stopPropagation();
		        }
		        form.classList.add('was-validated');
		      }, false);
		    });
		  }, false);
	})();


function onSignIn(googleUser) {
	   
    var profile = googleUser.getBasicProfile();
    var name =  profile.getGivenName();
    var surname  = profile.getFamilyName();
    var email = profile.getEmail();
    
    window.onbeforeunload = function(e){
    	  gapi.auth2.getAuthInstance().signOut();
    };
    
    
    
    
    $.post("googleLogin",{ 	email : email, name : name, surname : surname }, function(result){
    				    	    if(result == 1){
    				    	    	window.location.replace("home");	
    				    	    }
  				    	  });
    
    
}

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
  
function onLoad() {
	gapi.load('auth2', function() {
        gapi.auth2.init();
    });      
}






