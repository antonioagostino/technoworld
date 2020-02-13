<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chi Siamo</title>

	<%@ include file="include.jsp" %>
	<link rel="stylesheet" href="css/whoWeAre.css">
	
</head>
<body>

	<%@ include file="menuBar.jsp" %>
	
	<div id="container" class="card">
	  <div class="card-body">
	    <h2 class="card-title">Chi Siamo</h2>
	    <p class="card-text">Il gruppo che si è dedicato alla realizzazione di questo progetto è formato da:</p>
	    
		<ul>
		  <li>Alfredo Farò</li>
		  <li>Antonio Agostino</li>
		  <li>Erica Coppolillo</li>
		  <li>Lorenzo Guastalegname</li>
		</ul>
	    
		<div id="preLinks">Fieri e Onorati studenti dei docenti<br></div>
		<div id="links" class="row">
			<div id="ricca" class="col-sm-5"><a href="https://www.mat.unical.it/ricca/" class="card-link">Francesco Ricca</a></div>
			<div class="col-sm-2">e</div>
			<div id="cuteri" class="col-sm-5"><a href="https://www.facebook.com/bernardo.cuteri" class="card-link">Bernardo Cuteri</a></div>
		</div>
	  </div>
	</div>
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>