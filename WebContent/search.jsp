<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>${category.name}</title>
        <%@ include file="include.jsp" %>
        
        <link rel="stylesheet" href="css/home.css">
        <link rel="stylesheet" href="css/search.css">

        <!-- link per lo slider a 2 cursori -->
        <link rel="stylesheet" href="css/wrunner-default-theme.css">
        <script src="js/search.js"></script>
        
</head>
<body>
	<%@ include file="menuBar.jsp" %>
	<p id="categoryId" style="display: none;">${category.id}</p>
		<div id="carouselBoxSearch" class="row">
			<div class="col">
				<div id="demo" class="carousel slide" data-ride="carousel">
		  			<!-- Indicators -->
					<ul class="carousel-indicators">
					  <li data-target="#demo" data-slide-to="0" class="active"></li>
					  <li data-target="#demo" data-slide-to="1"></li>
					  <li data-target="#demo" data-slide-to="2"></li>
					</ul>
	
					<div class="carousel-inner">
					 	<div id="carousel2" class="carousel-item active"><img id="img2" class="carousel-img" src="img/carousel/${fn:toLowerCase(category.name)}/2.jpg"></div>
						<div id="carousel1" class="carousel-item"><img class="carousel-img" id="img1" src="img/carousel/${fn:toLowerCase(category.name)}/1.jpg"></div> 
						<div id="carousel3" class="carousel-item"><img class="carousel-img" id="img3" src="img/carousel/${fn:toLowerCase(category.name)}/3.jpg"></div>
					</div>
				</div>
			</div>

 			 <!-- Left and right controls -->
			 <a class="carousel-control-prev" href="#demo" data-slide="prev"><span class="carousel-control-prev-icon"></span></a>
			 <a class="carousel-control-next" href="#demo" data-slide="next"><span class="carousel-control-next-icon"></span></a>
		</div> <!-- carousel Box -->




      <div class="jumbotron text-center" id="jumb">
          <h1>${category.name}</h1>
        <h3>in vendita</h3>
      </div>


    <div class="container-fluid">
    	<div class="row">
            <div class="col-sm-3">

				<div class="form-group">
					<h4><br>Ordina per:</h4>
						<select class="form-control" id="orderBy" onchange="updateProducts()">
							<option>Consigliati</option>
							<option>Prezzo crescente</option>  <!--  TODO implement ajax function orderBy -->
							<option>Prezzo decrescente</option>
						</select>
				</div>

                <h4><br></br>Filtra per:</h4>
				<div class="container">
                    <div id="accordion">
                        <div class="card">
                            <div class="card-header"><a class="card-link" data-toggle="collapse" href="#brandCollapse">Brand </a></div>
                            <div id="brandCollapse" class="collapse" data-parent="#accordion">
                                <div class="card-body">
                                    <c:forEach var="manufacturer" items="${manufacturers}">
                                        <a id="${manufcturer.name}" class="list-group-item d-flex justify-content-between align-items-center">${manufacturer.name}<span class="badge badge-primary badge-pill">${manufacturer.products}</span></a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>                 
                    </div>
                </div>


                <h4><br>Prezzo:</h4>
                <div class="my-js-slider"></div>
				<script src="js/wrunner-native.js"></script>
                <script>
                	
                    var setting = {
                        roots: document.querySelector('.my-js-slider'),
                        type: 'range',
                        step: 1,
                        //prezzo minimo e prezzo massimo dei prodotti presenti
                        limits : {     minLimit: 0,      maxLimit: 5000   },
                    }
                    var slider = wRunner(setting);
                </script>    
                
                <button class="btn btn-primary" id="apply">Applica</button>
            </div><!-- col -->

            <div class="col-sm-9">
                <div class="row" id="productBox">
                    <c:forEach var="product" items="${products}">
                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <div class="card-image"><a href="product?id=${product.id}"><img class="card-img-top img-thumbnail" src="img/products/${product.imagePath}" alt=""></a></div>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="product?id=${product.id}">${product.manufacturer} ${product.model}</a>
                                    </h4>
                                    <h5>${product.price}€</h5>
                                    <p class="card-text">${product.description}</p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted">
                                        <c:forEach var="i" begin="1" end="${product.starsAvg}">
                                            &#9733;
                                        </c:forEach>
                                        <c:forEach var="i" begin="1" end="${5- product.starsAvg}">
                                            &#9734;
                                        </c:forEach>
                                    </small>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div><!-- col -->
        </div><!-- row -->
    </div> <!--container-->

	
	<div id="paginationDiv">
		
	   <script> createPagination(${pages}, 1); </script>
    </div>

    
    <%@ include file="footer.jsp" %>
    
</body>
</html>

