<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log-in</title>

	<%@ include file="include.jsp" %>
	<script type="text/javascript" src="js/login.js"></script>
	<link rel="stylesheet" href="css/login.css">
	
	
	
</head>
<body>

	<div id="container">
		<div id="divLogo">
			<a href="home">
				<img id="logoLogin" src="img/logo2.png">
			</a>
		</div>
		
		<c:if test="${loginError != null}">
		<div class="alert alert-danger alert-dismissible fade show">
	    	<div>
	    		<i class="fa fa-exclamation-triangle"></i>
	    		<strong>Attenzione!</strong>
	    		<p>Le credenziali che hai inserito non sono corrette<p>
			</div>
	    </div>
	    </c:if>

		<c:if test="${adminNotAuthenticated != null}">
			<div class="row">
				<a class="adminLogin col text-center" href="login?admin=false">
					Non sono un amministratore
				</a>
			</div>
		</c:if>
	    
		<form method="post" <c:if test="${adminNotAuthenticated == null}"> action="login" </c:if> <c:if test="${adminNotAuthenticated != null && store == null}"> action="login?admin=true" </c:if>
				<c:if test="${adminNotAuthenticated != null && store != null}"> action="login?store=true" </c:if> class="needs-validation" novalidate>
		  <div class="form-group">
		 	 <c:if test="${adminNotAuthenticated == null}">
		    	<label for="uname">Inserisci il tuo Username</label>
		    	<a class="adminLogin" href="login?admin=true">
			  		Sei un amministratore?
			  	</a>
			</c:if>
			<c:if test="${adminNotAuthenticated != null}">
				<label for="uname">Inserisci il tuo ID</label>
			  <c:if test="${store == null}">
				<a class="adminLogin" href="login?store=true">
			  		Gestisci uno store fisico?
			  	</a>
			  </c:if>
			</c:if>
			<c:if test="${adminNotAuthenticated == null}">
		    	<input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
		    	<div class="valid-feedback">Username valido.</div>
		    </c:if>
		    <c:if test="${adminNotAuthenticated != null}">
		    	<input type="text" class="form-control" id="id" placeholder="ID" name="id" required>
		    	<div class="valid-feedback">ID valido.</div>
		    </c:if>
		    
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>
		  
		  <div class="form-group">
		    <label class="insert" for="pwd">Inserisci la tua Password</label>
		   	<c:if test="${adminNotAuthenticated == null}">
		   		<a class="forgottenPassword" href="forgottenPassword">
		  			Password dimenticata?
		  		</a>
		  	</c:if>
		    <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
		    <div class="valid-feedback">Password valida.</div>
		    <div class="invalid-feedback">Per favore, riempi questo campo</div>
		  </div>

		  <c:if test="${adminNotAuthenticated == null}">
			  <div class="registration">
			  	Non hai ancora un account? 
			  	<a href="registration">
			  		Registrati
			  	</a>
			  </div>
		  </c:if>
		  <div class="col text-center">
		  	<input type="submit" class="btn btn-primary" value="Accedi" />
		  </div>
		  <c:if test="${adminNotAuthenticated == null}">
		  	<div class="g-signin2" data-width="230" data-height="45" data-longtitle="true" data-onsuccess="onSignIn"></div>
		  </c:if>
		  
		  
		</form>
		
	</div>

</body>
</html>