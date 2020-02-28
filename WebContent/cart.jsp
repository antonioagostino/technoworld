<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Il tuo Carrello</title>
	<%@ include file="include.jsp" %>
    <script src="https://www.paypal.com/sdk/js?client-id=AUuQsZ6W_kSfRMWY_JWNer6Ho-eU3XDcVAgce3CZYj8LhYJO4ZiL9AME6LMbWHPxGkbTVp3zHpoQudsR"></script>
    <script src="js/cart.js"></script>
	<script src="js/product.js"></script>
	<link rel="stylesheet" href="css/cart.css">
</head>

<body onload="<c:forEach var="cartProduct" items="${cartProducts}">
            addCardProduct(${cartProduct.id}, ${cartProduct.orderQuantity});
            setUserId(${user.id});
            setTotalPrice(${totalPrice});
        </c:forEach>">

	<%@ include file="menuBar.jsp" %>
	
	<div id="container" class="row">

		<div id="paymentWaitingBox" class="row">
			<img src="img/paywait.gif" class="img-fluid">
			<p class="lead">Attesa conferma pagamento...</p>
		</div>
		<div class="alert alert-success col" role="alert" id="successfullPurchase">
			Acquisto effettuato con successo! Riceverai un riepilogo dell'ordine sulla tua casella di posta elettronica.
		</div>

		<div class="alert alert-danger col" role="alert" id="failedPurchase">
			Si sono verificati dei problemi durante il pagamento, riprova.
		</div>

		<c:if test="${emptyCart == true}">
			<div id="emptyCart" class="text-center py-3">Il Carrello è vuoto</div>
		</c:if>
		<c:if test="${emptyCart != true}">
			<div id="products" class="col-sm-9">
				<div class="row">
					<div class="col-sm-7"></div>
					<div id="titleQuantity" class="col-sm-2">Quantità</div>
					<div id="titlePrice" class="col-sm-1">Prezzo</div>
				</div>
				<c:forEach var="cartProduct" items="${cartProducts}">
					<div id="rowProduct${cartProduct.id}" class="row rowProduct">
						<hr class="hr">
						
						<img class="col-sm-2" src="img/products/${cartProduct.imagePath}" alt="">
						<div class="col-sm-6">
							<div id="modelProduct">${cartProduct.manufacturer} ${cartProduct.model}</div>
							<div>${cartProduct.description}</div>
							<div id="trashIcon" onclick="removeCartProduct('${cartProduct.id}')"><i class="fa fa-trash"></i> Rimuovi</div>
						</div>
						
						<div class="col-sm-2">
							<select id="productQuantity${cartProduct.id}" onchange="updateFunctions('${cartProduct.id}')" class="quantity__select">
				              <option id="val1${cartProduct.id}" value="1">1</option>
				              <option id="val2${cartProduct.id}" value="2">2</option>
				              <option id="val3${cartProduct.id}" value="3">3</option>
				              <option id="val4${cartProduct.id}" value="4">4</option>
				              <option id="val5${cartProduct.id}" value="5">5</option>
				            </select>
				            <script>
				            	var idProduct = ${cartProduct.id};
				            	var orderQuantity = ${cartProduct.orderQuantity};
				            	if(orderQuantity == 1) 
				            		document.getElementById("val1"+idProduct).setAttribute("selected", "");
				            	
				            	if(orderQuantity == 2)
				            		document.getElementById("val2"+idProduct).setAttribute("selected", "");
				            	
				            	if(orderQuantity == 3)
				            		document.getElementById("val3"+idProduct).setAttribute("selected", "");
				            	
				            	if(orderQuantity == 4)
				            		document.getElementById("val4"+idProduct).setAttribute("selected", "");
				            	
				            	if(orderQuantity == 5)
				            		document.getElementById("val5"+idProduct).setAttribute("selected", "");
				            </script>
			            </div>
			            <div id="productPrice${cartProduct.id}" class="productPrice col-sm-2">
			            <script>
				            var divProductPrice = "productPrice"+${cartProduct.id};
				            var productPrice = ${cartProduct.pricePerQuantity}; 
							document.getElementById(divProductPrice).innerHTML = productPrice.toFixed(2) + " €";
			            </script>
			            </div>
					</div>
				</c:forEach>
			
		</div>
		<div id="goToPayment" class="col-sm-3">
			<div class="card" id="cardPayment">
				<div id="rowTotalPrice" class="row">
                    <div class="col-sm-7"><h5>Prezzo Totale:</h5></div>
					<div class="col-sm-5"><h5 id="totalPrice"></h5>
					<script>
						var totPrice = ${totalPrice};
						document.getElementById("totalPrice").innerHTML = totPrice.toFixed(2) + " €";
					</script>
					</div>
				</div>
				<c:if test="${user != null}">
                    <h5 id="paypalTitle">Paga con PayPal:</h5>
					<div id="paypal-button-container"></div>
				</c:if>
				<c:if test="${user == null}">
				<a id="anchorPayment" href="login">
					<button class="btn btn-primary"> Procedi all'ordine</button>
				</a>
				</c:if>
			</div>
			
			<br>

			<h3 id="shipmentTitle">Spedizione</h3>

			<div class="alert alert-danger" id="shipmentAlert" role="alert">
				Non tutti i campi sono stati compilati!
			</div>
			<div class="alert alert-danger" id="storeAlert" role="alert">
				Scegli il negozio fisico per il ritiro!
			</div>

			<div id="shipmentcontainer" class="row">
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<input type="radio" id="radio1" name="optradio" value="option1" checked>
						</div>
					</div>
					<input type="text" class="form-control" placeholder="Ritiro in negozio" disabled>
				</div>
				<div class="input-group">
					<div class="input-group-prepend">
						<div class="input-group-text">
							<input type="radio" id="radio2" name="optradio" value="option2">
						</div>
					</div>
					<input type="text" class="form-control" placeholder="Spedizione a domicilio" disabled>
				</div>
				<div id="shipment" style="display: none;" class="col card">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button class="input-group-text fa fa-drivers-license" disabled></button>
						</div>
						<input type="text" class="form-control" id="recipient" placeholder="Nome e cognome del referente" name="recipient" required>
						<div class="invalid-feedback">Riempi questo campo!</div>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button class="input-group-text fas fa-home" disabled></button>
						</div>
						<input type="text" class="form-control" id="street" placeholder="Indirizzo di casa e numero civico" name="street" required>
						<div class="invalid-feedback">Riempi questo campo!</div>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button class="input-group-text fas fa-map-marker" disabled></button>
						</div>
						<input type="text" class="form-control" id="cap" placeholder="CAP" name="cap" required>
						<div class="invalid-feedback">Riempi questo campo!</div>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button class="input-group-text fas fa-building" disabled></button>
						</div>
						<input type="text" class="form-control" id="city" placeholder="Città" name="city" required>
						<div class="invalid-feedback">Riempi questo campo!</div>
					</div>

					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<button class="input-group-text fas fa-globe" disabled></button>
						</div>
						<input type="text" class="form-control" id="province" placeholder="Provincia" name="province" required>
						<div class="invalid-feedback">Riempi questo campo!</div>
					</div>
				</div>
				<div class="btn-group dropleft" id="localStoreDropdown">
					<button class="btn btn-secondary dropdown-toggle" id="localStoreDropdownButton" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Seleziona il negozio fisico
					</button>
					<div class="dropdown-menu" aria-labelledby="localStoreDropdownButton">
						<c:forEach var="store" items="${stores}">
							<a class="dropdown-item" href="#" onclick="changeStoreId(${store.id}, '${store.street}')">${store.street}</a>
						</c:forEach>
					</div>
				</div>
				<div class="input-group mb-3">
					<button onclick="valueCheck()" type="button" class="btn btn-secondary" id="shipmentButton">Conferma dati di spedizione</button>
				</div>
			</div>
		</div>
	</c:if>
</div>
			
		</div>
		
		
	</div>
		
	
	<%@ include file="footer.jsp" %>
	
</body>
</html>