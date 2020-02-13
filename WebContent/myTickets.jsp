<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>I miei ticket</title>
    <%@ include file="include.jsp" %>
    <link rel="stylesheet" href="css/mytickets.css">
</head>
<body>

<%@ include file="menuBar.jsp" %>

<br>
    <div class="container-fluid">
        <div class="row">
            <h2 class="col display-4 text-center" id="pageTitle"> I tuoi Ticket</h2>
        </div>
        <c:forEach var="ticket" items="${tickets}">
        <div class="row" id="ticket-body">
            <div class="col card">
                <div class="card-body row">
                    <div class="col-sm-8">
                        <h5><a href="ticket?id=${ticket.id}">Ticket #${ticket.id}</a>
                            <small class="text-muted"> - aperto il ${ticket.date} da ${ticket.user.username}</small>
                        </h5>
                    </div>
                    <div class="col-sm-4">
                        <p class="text-right">Aperto <i class="fa fa-bell"></i></p>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</body>
</html>
