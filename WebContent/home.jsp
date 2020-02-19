<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Home</title>
	
	<%@ include file="include.jsp" %>
	<link rel="stylesheet" href="css/home.css">
	
</head>
<body>
	<%@ include file="menuBar.jsp" %>
	<div id="carouselBox" class="row">
		<div class="col">
			<div id="demo" class="carousel slide" data-ride="carousel">
		  		<!-- Indicators -->
			  <ul class="carousel-indicators">
			    <li data-target="#demo" data-slide-to="0" class="active"></li>
			    <li data-target="#demo" data-slide-to="1"></li>
			    <li data-target="#demo" data-slide-to="2"></li>
			  </ul>
			
			  <!-- The slideshow -->
			  <div class="carousel-inner">
			    <div id="carouselPhone" class="carousel-item">
			      <img class="carousel-img" src="img/carousel/home/1.jpg" alt="cellulari">
			      
			      
			    </div>
			    <div id="carouselLaptop" class="carousel-item active">
			      <img id="carouselImg2" class="carousel-img" src="img/carousel/home/2.jpg" alt="laptop">
			      
			    </div>
			    
			    <div id="carouselHardware" class="carousel-item">
			      <img class="carousel-img" src="img/carousel/home/3.jpg" alt="cpu">
			    </div>
			  </div>
			
			  <!-- Left and right controls -->
			  <a class="carousel-control-prev" href="#demo" data-slide="prev">
			    <span class="carousel-control-prev-icon"></span>
			  </a>
			  <a class="carousel-control-next" href="#demo" data-slide="next">
			    <span class="carousel-control-next-icon"></span>
			  </a>
			</div>
		</div>
	</div>
	
	
	
	 <div class="jumbotron text-center">
		<h1>TechnoWorld</h1>
		<h3>Entra in un mondo di pura tecnologia</h3>
	</div>
	
	
	<div class="row">
		<div class="col-sm-12">
		<div class="smartphoneCategory">
			<h1>Smartphone</h1>
    		<p><a id="smartphoneLink" href="search?category=2">Acquista ></a></p>
 	    	<div class="imageSmartphone"></div>
		</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="laptopCategory">
		   		<h1>Laptop</h1>
		   		<p><a id="laptopLink" href="search?category=1">Acquista ></a></p>
			    <div class="imageLaptop"></div>
			</div>
	    </div>
	</div>
	
	<div class="row">
		<div class="col-sm-6">
			<div class="hardwareCategory">
		    	<div class="imageHardware"></div>
		    	<h1>PC-Hardware</h1>
		    	<p><a id="hardwareLink" href="search?category=3">Acquista ></a></p>
    		</div>
    	</div>
	   	
	   	<div class="col-sm-6">
			<div class="accessoryCategory">
		   		<div class="imageAccessory"></div>
		   		<h1>Accessori</h1>
		   		<p><a id="accessoryLink" href="search?category=4">Acquista ></a></p>
	   		</div>
		</div>
	</div>
		
	<%@ include file="footer.jsp" %>

</body>
</html>