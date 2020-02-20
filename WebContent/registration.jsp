<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrazione</title>

	<%@ include file="include.jsp" %>  
	<script src="js/registration.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/fontawesome.min.js"></script>
	<link rel="stylesheet" href="css/login.css">
		
</head>
<body onload="writeTexts('${name}', '${surname}', '${date}', '${username}', '${email}', '${question}', '${answer}', '${sameUsername}', '${sameEmail}', '${invalidPassword}', '${invalidEmail}')" >
	
	<div id="container">
		<div id="divLogo">
			<a href="home">
				<img id="logoLogin" src="img/logo2.png">
			</a>
		</div>
		<form method="post" action="registration">
		
		  <div class="form-group">
		 	<label for="name">Inserisci il tuo Nome</label>
			<input type="text" class="form-control" id="name" placeholder="Nome" name="name" required>	    
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		 	<label for="surname">Inserisci il tuo Cognome</label>
			<input type="text" class="form-control" id="surname" placeholder="Cognome" name="surname" required>		    
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		
		  <div class="form-group">
			 <label>Seleziona la tua Data di Nascita</label>
			 <input type="date" name="date" id="date" class="form-control" required>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		 	<label for="uname">Inserisci il tuo Indirizzo e-mail</label>
			<input type="text" class="form-control" id="email" placeholder="email@esempio.it" name="email" required>
			<c:if test="${sameEmail == true}">
				<div class="invalid-feedback d-block">Indirizzo e-mail già in uso</div>
			</c:if>
			<c:if test="${invalidEmail == true}">
				<div class="invalid-feedback d-block">Indirizzo e-mail non valido</div>
			</c:if>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		 	<label for="uname">Inserisci il tuo Username</label>
			<input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
		   	<c:if test="${sameUsername == true}">
		   		<div class="invalid-feedback d-block">Username già in uso</div>
		   	</c:if>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		    <label class="insert" for="pwd">Inserisci la tua Password</label>
		    <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
		    <c:if test="${invalidPassword == true}">
		   		<div class="invalid-feedback d-block">La password deve contenere almeno 8 caratteri, almeno una lettera maiuscola, almeno una minuscola e almeno un numero</div>
		   	</c:if>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		 	<label for="uname">Inserisci la Domanda di Sicurezza</label>
			<input type="text" class="form-control" id="question" placeholder="Domanda" name="question" required>	    
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		 	<label for="uname">Inserisci la Risposta di Sicurezza</label>
			<input type="text" class="form-control" id="answer" placeholder="Risposta" name="answer" required>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="col text-center">
		  	<input type="submit" class="btn btn-primary" value="Registrati" />
		  </div>
		</form>
	</div>
</body>
</html>