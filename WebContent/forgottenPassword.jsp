<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Password dimenticata</title>

	<%@ include file="include.jsp" %>
	
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/forgottenPassword.js"></script>
	<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
	
	<link rel="stylesheet" href="css/login.css">
	<link rel="stylesheet" href="css/forgottenPassword.css">
	
</head>
<body onload="disable()">

	<div id="container">
		<div id="divLogo">
			<a href="home">
				<img id="logoLogin" src="img/logo2.png">
			</a>
		</div>
		
		<form>
		  <div class="form-group">
			  <label for="uname">Inserisci il tuo Username</label>
			  <input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
			  <div id="wrongUsername" class="invalid-feedback d-block">Username inesistente</div>
			  
			  <div class="text-center">
				 <input id="buttonUsername" class="btn btn-primary" onclick="getSecurityQuestion()" value="Invia"/>
			  </div>
			  
			  <label for="uname">La tua Domanda di Sicurezza</label>
			  <input type="text" class="form-control" id="securityQuestion" placeholder="" name="securityQuestion" disabled>
			  	
			  <input type="text" class="form-control" id="securityAnswer" placeholder="Risposta" name="securityAnswer" required>
			  <div id="wrongAnswer" class="invalid-feedback d-block">Risposta di Sicurezza non corretta</div>
			  
			  <div class="text-center">
				 <input id="buttonPassword" class="btn btn-primary" onclick="getPassword()" value="Recupera Password"/>
			  </div>
			  
			  
		  </div>
		</form>
		
		<div class="text-center">
			<input id="buttonNewPassword" class="btn btn-primary" onclick="saveNewPassword()" value="Salva nuova Password"/>
			<div id="invalidNewPassword" class="invalid-feedback d-block">La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola, almeno una minuscola e almeno un numero</div>
		</div>
		
	</div> 
	
	<%@ include file="footer.jsp" %>

</body>
</html>