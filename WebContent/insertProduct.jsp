<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inserimento prodotto</title>
	<%@ include file="include.jsp" %>
	
	<script src="js/insertProduct.js"></script>
	
	<link rel="stylesheet" href="css/insertProduct.css">
	
</head>
<body>
	
	<%@ include file="menuBar.jsp" %>
	
	<br>
	
	<div class = "row" id = "mainRow">
		
		
		<div class="card">
			<div class= "col-sm-12 card-body"  id="rightRowInsertProduct">
			
		<form id="modulo1" class="form-horizontal" method="post" action="insertproduct">
			<div id="titleInsertProduct">
				<h1>Aggiungi un prodotto al catalogo</h1>
				<c:if test="${exists != null }">
					<c:if test="${exists == true}">
						<div class="alert alert-danger" role="alert">Il prodotto esiste gia nel catalogo!</div>
					</c:if>
					<c:if test="${exists == false}">
						<p class="alert alert-success" role="alert">Il prodotto è stato inserito correttamente nel catalogo</p>
					</c:if>
				</c:if>
			</div>
				
			<div class="row" id="categoryChoice">
				  <label for="sel1" class = "col-sm-2">Categoria:</label>
				  <select class="form-control col-sm-9" name="category">
					<option>Smartphone</option>
					<option>Laptop</option>
					<option>PC-Hardware</option>
					<option>Accessori</option>
				  </select>
			</div>

			<br>

			<div class="row" id="imgChoice">
				<div class="col-sm-2">Immagine:</div>
				<div id="imageInsertProduct" class="col-sm-7 photoCameraInsertProduct"></div>
			</div>




			  <div class="form-group" onchange="loadPhoto('null');" id="insertImage">
				<input type="file" class="form-control-file" id="formControlFile" name="path" required>
			  </div>

			<div class="row" id="insertTitle">
				  <div class="col-sm-2">Modello:</div>
				  <input type="text" class="form-control col-sm-9" id="usr" name="model"required>
			</div>

			<div class="row" id="insertManufacturer">
				  <div class="col-sm-2">Produttore:</div>
				  <input type="text" class="form-control col-sm-9" id="usr" name="manufacturer"required>
			</div>



			<div class="row" id="commentText">
				  <div class="col-sm-2">Descrizione del prodotto:</div>
				   <textarea class="form-control col-sm-9" rows="5" id="comment" name="description"required></textarea>
			</div>

			<div class="row" id="specificsText">
				  <div class="col-sm-2">Specifiche del prodotto:</div>
				   <textarea class="form-control col-sm-9" rows="2" id="comment" name="specifics"required></textarea>
			</div>

			<div class="row" id = "priceInsertProduct">
				  <div class="col-sm-2">Prezzo (€):</div>
				  <input type="text" class="form-control col-sm-2" id="usr" name="price"required>
			</div>


			<div id="rowSubmitInsertProduct">
					<button class="btn btn-primary">Invia</button>
			</div>
		</form>
	  </div> <!-- chiude la colonna di destra -->
	</div> <!-- chiude la card della seconda colonna -->
		

		
			
			
	</div> <!-- chiude la riga che contiene le due colonne -->
			
		
	<%@ include file="footer.jsp" %>
	
	
	
</body>
</html>