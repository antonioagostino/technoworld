<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Techno World</title>
	
	<%@ include file="include.jsp" %>
<!-- 	<link rel="stylesheet" href="css/home.css"> -->
	<link rel="stylesheet" href="css/product.css">
	<script src="js/product.js"></script>
	
</head>
<body onload="checkProduct('${product.id}', 'product')">
	
	<%@ include file="menuBar.jsp" %>  
	<div class="container" id="mainContainer">

	    <div class="row">
	
	      <div class="col-lg-9">
	        <div class="card mt-4">
	         <div id="centerImage"> <img id="productImage" class="card-img-top" src="img/products/${product.imagePath}" alt="">  </div>
	          <div class="card-body">
	            <h3 id="name" class="card-title">${product.manufacturer} ${product.model}</h3>
	            <h4 id="price">${product.price}€</h4>
	            <p class="card-text">Descrizione:<br></p>
	            <p class="card-text" id="description">${product.specs}</p>
	  		    <span class="text-warning" id="stars">
					<c:forEach var="i" begin="1" end="${product.starsAvg}">
						&#9733;
					</c:forEach>
					<c:forEach var="i" begin="1" end="${5- product.starsAvg}">
						&#9734;
					</c:forEach>
				</span>
	          </div>
	        </div>
			
			
	        <div class="card card-outline-secondary my-4">
	          <div class="card-header">Recensioni</div>
	          <div class="card-body">
	          	<div id="reviewBox" class="col">
					<c:if test="${thereAreReviews != null}">
						<c:forEach var="review" items="${product.reviews}">
							<h6 id="reviewTitle">${review.title}</h6>
							<p id="reviewText">${review.body}</p>
							<small id="author" class="text-muted">Scritta da ${review.username}</small>
							<hr>
						</c:forEach>
					</c:if>
					<c:if test="${thereAreReviews == null}">
						<h4>Questo prodotto non ha recensioni</h4>
					</c:if>
		         </div>
				  <c:if test="${thereAreReviews != null}">
				  <span id="otherReviewsButton">
				  	<a href="#" onclick="loadOtherReviews(${product.id})">Altre recensioni...</a>
				  </span><hr>
				  </c:if>
				  <c:if test="${purchased == true}">
				  	<button class="btn btn-primary" id="writeReview">Scrivi una recensione</button>
				  </c:if>
		         
	          </div>
	        </div>
	      </div>
	     
	
	      <div class="col-lg-3">
	        <div class="card mt-4">
	          <div class="card-title">
	            <h3 id="summary"><strong>Riepilogo:</strong></h3>
	          </div>
	          <div class="card-text" id="summaryBody">
	            <h5 id="productInSummary">${product.manufacturer} ${product.model}</h5>
	            <div class="card-text" id="priceDiv">
	              <h4 id="price2"><strong>€${product.price}</strong></h4>
	              <small id="shipment">+ spedizione gratuita</small>
	            </div>
	
	
	          </div>
	          <div class="row" id="quantityDiv">
	            <h6 id="quantity">Quantità:&nbsp; </h6>
	          
	            <select id="productQuantity" class="quantity__select" aria-label="Seleziona Quantità:">
	              <option value="1">1</option>
	              <option value="2">2</option>
	              <option value="3">3</option>
	              <option value="4">4</option>
	              <option value="5">5</option>
	            </select>
	          </div>
	          
	          <c:if test="${administrator == null}">
	          	<button type="button" id="addToCart" onclick="checkProduct('${product.id}', 'cart')" class="btn">Aggiungi al carrello</button>
	          </c:if>
	          <c:if test="${administrator != null}">
	          <a href="modifyProduct?id=${product.id}">
	          	<button id="modifyProduct" class="btn">Modifica prodotto</button>
	          </a>
	          <form class="form-horizontal" method="post" action = "deleteProduct">
		         <button type="submit" id="deleteProduct" class="btn">Elimina prodotto</button>
		         <select name="productId" style = "display:none;">
 				 	<option>${product.id}</option>
 				 </select>
	          </form>
	          </c:if>
	          
	
	        </div>
	      </div> <!-- lg-3 -->
	    </div> <!-- row -->
        

      </div> <!-- mainContainer -->
      
      
	<div class="bg-modal">
		<div class="modal-content">
	  		<form action="postReview" method="post">
	    		<div id="title">
	    			<div class="close">+</div>
	      				<h3>Scrivi una recensione</h3>
	    			</div>
	      		<div class="form-group">
	        		<label for="usr">Titolo:</label>
	        		<input type="text" class="form-control" id="tit" name="title" required></input>
	    		</div>
	    		<div class="form-group">
	        		<label for="sel1">Valutazione: </label>
	        		<select class="form-control" id="stars" name="stars">
	          			<option>1</option>
	          			<option>2</option>
	          			<option>3</option>
	          			<option>4</option>
	          			<option>5</option>
	        		</select>
	      		</div>
	      		<div class="form-group" style="display: none;">
	      			<select style="display: none;" name="product">
	      				<option>${product.id}</option>
	      			</select>
	      		</div>
	    		<div class="form-group">
	        		<label for="comment">Commento:</label>
	        		<textarea class="form-control" rows="5" name="comment" id="comment"></textarea>
	    		</div>
	    		<button class="btn btn-primary" id="sendReview">Invia recensione</button>
	   		</form> 
	  	</div> 
	</div>
      
      <%@ include file="footer.jsp" %>
      
</body>
</html>