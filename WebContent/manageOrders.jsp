<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ordini ricevuti</title>
    <%@ include file="include.jsp" %>
    <link rel="stylesheet" href="css/orders.css">
    <link rel="stylesheet" href="css/wrunner-default-theme.css">
    <script src="js/manageOrders.js"></script>
</head>
<body>

<div class="row">
    <div class="col-sm-4"></div>
    <div class="col-sm-4 text-center">
        <img src="img/logo.png" class="img-fluid">
    </div>
    <div class="col-sm-4 text-right">
        <a href="login?logout=true">
            <button class="btn btn-primary" id="logout-button-storemode">
                Logout
            </button>
        </a>
    </div>
</div>

<div class="jumbotron" id="titleOrders">
    <div class="imageTitleOrders"><img src="img/package.png" width="100px"></div>
    <h1>Ordini ricevuti</h1>
    <h5>Visualizza i dati degli ordini ricevuti</h5>
</div>

<div class="container">
    <c:if test="${emptyOrders == true}">
        <h1 id="noOrders">Non hai ricevuto nessun ordine!</h1>
    </c:if>


    <div id="accordion">

        <c:if test="${emptyOrders == false}">
            <c:forEach var="purchase" items="${purchases}">
                <div class="card">
                    <div class="card-header">
                        <a class="card-link" data-toggle="collapse" href="#collapse${purchase.id}"> Ordine n: ${purchase.id}</a>
                    </div>
                    <div id="collapse${purchase.id}" class="collapse" data-parent="#accordion">
                        <div class="card-body">
                            <div class="row">
                                <h5 class="col-sm-9">Riepilogo ordine:</h5>
                                <c:if test="${purchase.status == 1}">
                                    <p class="col-sm-3 text-right" id="purchase-status"><strong>Stato: </strong><i class="fa fa-gears"></i> in elaborazione</p>
                                </c:if>
                                <c:if test="${purchase.status == 2}">
                                    <p class="col-sm-3 text-right" id="purchase-status"><strong>Stato: </strong><i class="fa fa-gift"></i> pronto per il ritiro</p>
                                </c:if>
                                <c:if test="${purchase.status == 3}">
                                    <p class="col-sm-3 text-right" id="purchase-status"><strong>Stato: </strong><i class="fa fa-truck"></i> consegnato</p>
                                </c:if>
                            </div>
                            <div class="row">
                                <div class="col-sm-6"> <h6>Data acquisto:</h6><p> ${purchase.date}</p> </div>
                                <div class="col-sm-6">
                                    <c:if test="${purchase.status == 1}">
                                        <button class="btn btn-primary btn-sm" id="recipt" onclick="updatePurchaseStatus(${purchase.id}, '2');">Notifica possibilità di ritiro</button>
                                    </c:if>
                                    <c:if test="${purchase.status == 2}">
                                        <button class="btn btn-primary btn-sm" id="recipt" onclick="updatePurchaseStatus(${purchase.id}, '3');">Notifica avvenuta consegna</button>
                                    </c:if>
                                </div>
                            </div>
                            <h6>Spedizione:</h6>
                            <p>${purchase.shipment}</p>
                            <h6>Prodotti acquistati:</h6>
                            <c:forEach var="product1" items="${purchase.products}">
                                <div class="row product" >
                                    <div class="col-sm-2"><img alt="image not found" src="img/products/${product1.product.imagePath}"></div>
                                    <div class="col-sm-5"><h6 class="product">${product1.product.manufacturer} ${product1.product.model} x ${product1.quantity}</h6></div>
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
        </c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
