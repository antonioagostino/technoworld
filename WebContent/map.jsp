<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mappa</title>
	
	<%@ include file="include.jsp" %>
	<script src="js/map.js"></script>
	<link rel="stylesheet" href="css/map.css">
</head>
<body onload="loadMarkers('${coords}')" >

	<%@ include file="menuBar.jsp" %>
	
	<div id="googleMap"></div>

	
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDoq-xsFhUtC3ucI3N5wH0KVVhVJZvNa68&callback=myMap"></script>
	
	
	<%@ include file="footer.jsp" %>
</body>
</html>