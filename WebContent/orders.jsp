<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Orders</title>
	<%@ include file="include.jsp" %>
	<link rel="stylesheet" href="css/orders.css">	
	<link rel="stylesheet" href="css/wrunner-default-theme.css">
	<script src="js/product.js"></script>
</head>
<body>
	<%@ include file="menuBar.jsp" %>
	
	<div class="jumbotron" id="titleOrders">
	<div class="imageTitleOrders"><img src="img/package.png" width="100px"></div>
		<h1>I miei ordini</h1>
		<h5>Visualizza i dati dei tuoi ordini</h5>
	</div>

			<div class="container" >
				<c:if test="${emptyOrders == true}">
					<h1>Non ci sono ordini</h1>
				</c:if>
		
		
			<div id="accordion">
			
			
			<c:forEach var="purchase" items="${purchases}">
				<div class="card">
      				<div class="card-header">
        				<a class="card-link" data-toggle="collapse" href="#collapse${purchase.id}"> Ordine n: ${purchase.id}</a>
      				</div>
					<div id="collapse${purchase.id}" class="collapse" data-parent="#accordion">
	        			<div class="card-body">
	          				<h5>Riepilogo ordine:</h5>
	          				<div class="row">
	          					<div class="col-sm-6"> <h6>Data acquisto:</h6><p> ${purchase.date}</p> </div>
	          					<div class="col-sm-6">
	          						<form action="sendRecipt" method="post">
	          							<div class="form-group" style="display: none;">
							      			<select style="display: none;" name="purchase">
							      				<option>${purchase.id}</option>
							      			</select>
							      		</div>
							      		
							      		<button class="btn btn-primary btn-sm" id="recipt">Richiedi ricevuta</button>
							      		
	          						 	 
	          						</form>
	          					 
	          					</div>
	          				</div>
	          				
	          				<h6>Spedizione:</h6>
	          				<p>${purchase.shipment}</p>
	          				<h6>Prodotti acquistati:</h6>
	          				<c:forEach var="product1" items="${purchase.products}">
	          					<div class="row product" >
		          					<div class="col-sm-2"><img alt="image not found" src="img/products/${product1.product.imagePath}"></div>
		          					<div class="col-sm-5"><a href="product?id=${product1.product.id}"><h6 class="product">${product1.product.manufacturer} ${product1.product.model} </h6></a></div>
		          					<div class="col-sm-3"><button type="button" class="btn btn-primary btn-sm" id="writeReview" onclick="window.location.href = 'product?id=${product1.product.id}';">Scrivi una recensione</button></div>
		          					<div class="col-sm-2"><h6 id="price">Prezzo: ${product1.product.price} €</h6></div>
	          					
	          					</div>
	          				</c:forEach>
	          					<hr>
	          					<div class="row">
	          						<div class="col-sm-6">Codice transazione: ${purchase.payment.transaction}</div>
	          						<div class="col-sm-6"><h6 style="text-align: right;">Totale: ${purchase.payment.amount} €</h6></div>
	          					</div>
	          				</div>		
	          			</div>	
	          		
	          		</div>				
	          	</c:forEach> 	
			</div> <!-- accordion -->
			
			
		
			
			</div>
			
			
			
			
		    
			
	
	
</body>
</html>

