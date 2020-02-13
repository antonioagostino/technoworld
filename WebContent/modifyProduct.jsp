<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modifica prodotto</title>
	<%@ include file="include.jsp" %>
	
	<script src="js/insertProduct.js"></script>
	
	<link rel="stylesheet" href="css/insertProduct.css">
	
</head>
<body onload="loadPhoto('${product.imagePath}')">
	
	<%@ include file="menuBar.jsp" %>
	
	<br>
	
	<div class = "row" id = "mainRow">
		
<!-- 		<div class="col-sm-1" id="leftRowInsertProduct"></div> -->
		
		<div class="card">
			<div class= "col-sm-12 card-body"  id="rightRowInsertProduct">
			
		<form id="modulo1" class="form-horizontal" method="post" action="modifyProduct">
		
<!-- 			<div class="modal fade" id="modalConfirm" tabindex="-1" role="dialog" aria-labelledby="examplepModalLabel" aria-hidden="true"> -->
<!-- 			  <div class="modal-dialog" role="document"> -->
<!-- 			    <div class="modal-content"> -->
<!-- 			      <div class="modal-header"> -->
<!-- 			        <h5 class="modal-title" id="exampleModalLabel">Informazioni operazione</h5> -->
<!-- 			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 			          <span aria-hidden="true">&times;</span> -->
<!-- 			        </button> -->
<!-- 			      </div> -->
<!-- 			      <div class="modal-body"> -->
<!-- 			        Operazione avvenuta con successo -->
<!-- 			      </div> -->
<!-- 			      <div class="modal-footer"> -->
<!-- 			        <button type="button" class="btn btn-primary" data-dismiss="modal">Chiudi</button> -->
<!-- 			      </div> -->
<!-- 			    </div> -->
<!-- 			  </div> -->
<!-- 			</div> -->
		
				<div id="titleInsertProduct">
					<h1>Modifica prodotto</h1>
					<c:if test="${notModified != null }">
						<c:if test="${notModified == true}">
							<div class="alert alert-danger" role="alert">Il prodotto esiste gia nel catalogo!</div>
						</c:if>
						<c:if test="${notModified == false}">
							<p class="alert alert-success" role="alert">Il prodotto è stato aggiornato correttamente nel catalogo</p>
						</c:if>
					</c:if>
				</div>
				
				<div class="row" id="categoryChoice">
					  <label for="sel1" class = "col-sm-2">Categoria:</label>
					  <select class="form-control col-sm-9" name="category" value="${product.category.name}" disabled>
					    <option>${product.category.name}</option>
					  </select>
				</div>
				
				<br>
				
				
			
				<div class="row" id="imgChoice">
					<div class="col-sm-2">Immagine:</div>
					<div id="imageInsertProduct" class="col-sm-7 photoCameraInsertProduct"></div>
				</div>

			
				
				
				  <div class="form-group" onchange="loadPhoto('null');" id="insertImage">
				    <input type="file" class="form-control-file" id="formControlFile" name="path" value="${product.imagePath}">
				  </div>
				
<%-- 				<c:if test="${completed == true}"> --%>
<!-- 						<script> -->
<!-- 							$("#modalConfirm").modal(); -->
<!-- 							 setTimeout(function(){ -->
<!-- 							  $('#modalConfirm').modal('hide') -->
<!-- 						 }, 2000);   -->
<!-- 						</script> -->
<%-- 				</c:if> --%>
				
				<div class="row" id="insertTitle">
					  <div class="col-sm-2">Modello:</div>
					  <input type="text" class="form-control col-sm-9" id="usr" name="model" value = "${product.model}"disabled>
				</div>
				
				<div class="row" id="insertManufacturer">
					  <div class="col-sm-2">Produttore:</div>
					  <input type="text" class="form-control col-sm-9" id="usr" name="manufacturer"  value = "${product.manufacturer}"disabled>
						<select name="productId" style = "display:none;">
	      				<option>${idProduct}</option>
	      			</select>					  
				</div>
				
			
				
				<div class="row" id="commentText">
					  <div class="col-sm-2">Descrizione del prodotto:</div>
					   <textarea class="form-control col-sm-9" rows="5" id="comment" name="description" required>${product.description}</textarea>
				</div>
				
				<div class="row" id="specificsText">
					  <div class="col-sm-2">Specifiche del prodotto:</div>
					   <textarea class="form-control col-sm-9" rows="2" id="comment" name="specifics" disabled>${product.specs}</textarea>
				</div>
				
				<div class="row" id = "priceInsertProduct">
					  <div class="col-sm-2">Prezzo (€):</div>
					  <input type="text" class="form-control col-sm-2" id="usr" name="price" value = "${product.price}"required>
				</div>
				
				
				<div id="rowSubmitInsertProduct">
						<button class="btn btn-primary">Invia</button>
				</div>
<%-- 				</c:if> --%>
			</form>
		  </div> <!-- chiude la colonna di destra -->
		</div> <!-- chiude la card della seconda colonna -->
		

		
			
			
	</div> <!-- chiude la riga che contiene le due colonne -->
			
		
	<%@ include file="footer.jsp" %>
	
	
	
</body>
</html>