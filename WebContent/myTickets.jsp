<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>I miei ticket</title>
    <%@ include file="include.jsp" %>
    <link rel="stylesheet" href="css/mytickets.css">
</head>
<body>

<%@ include file="menuBar.jsp" %>
<div class="jumbotron text-center">
    <div class="imageTitleOrders"><img src="img/ticket.png" width="100px"></div>
    <c:if test="${administrator == null}">
        <h2 class="display-4">I tuoi ticket</h2>
        <h5>Visualizza tutti i ticket che hai aperto</h5>
    </c:if>
    <c:if test="${administrator != null}">
        <h2 class="display-4">Gestisci ticket</h2>
        <h5>Visualizza tutti i ticket ai quali hai risposto</h5>
    </c:if>
</div>
<br>
    <div class="container-fluid">
        <c:if test="${administrator != null}">
            <div class="row">
                <p class="col text-right"><a href="newtickets">Ticket ancora senza risposta</a></p>
            </div>
        </c:if>
        <c:if test="${user != null}">
            <div class="row">
                <p class="col text-right"><a href="createticket">Crea un nuovo ticket</a></p>
            </div>
        </c:if>
        <c:forEach var="ticket" items="${tickets}">
        <div class="row" id="ticket-body">
            <div class="col card">
                <div class="card-body row">
                    <div class="col-sm-8">
                        <h5><a href="ticket?id=${ticket.id}">Ticket #${ticket.id}</a>
                            <small class="text-muted"> - aperto il <fmt:formatDate pattern = "dd-MM-yyyy" value = "${ticket.date}" /> da ${ticket.user.username}</small>
                        </h5>
                    </div>
                    <div class="col-sm-4">
                        <c:if test="${ticket.status == 0}">
                            <p class="text-right">Aperto <i class="fa fa-check"></i></p>
                        </c:if>
                        <c:if test="${ticket.status == 1}">
                            <p class="text-right">Chiuso <i class="fa fa-close"></i></p>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
<%@ include file="footer.jsp" %>
</body>
</html>
