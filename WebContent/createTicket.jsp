<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crea un nuovo ticket</title>
    <%@ include file="include.jsp" %>
    <link rel="stylesheet" href="css/insertTicket.css">

</head>
<body>

<%@ include file="menuBar.jsp" %>

<br>

<div class="row">
    <div class="card" id="ticket-body">
        <div class= "col-sm-12 card-body">
            <form class="form-horizontal" method="post" action="createticket">
                <div id="titleInsertTicket">
                    <h1>Crea un nuovo ticket</h1>
                    <c:if test="${status != null }">
                        <c:if test="${status == 1}">
                            <div class="alert alert-success" role="alert">Il ticket è stato creato, riceverai una risposta nel più breve tempo possibile</div>
                        </c:if>
                        <c:if test="${status == 2}">
                            <div class="alert alert-danger" role="alert">Problemi durante la creazione del ticket, riprova!</div>
                        </c:if>
                        <c:if test="${status == 3}">
                            <div class="alert alert-danger" role="alert">Il messaggio non può essere vuoto</div>
                        </c:if>
                    </c:if>
                </div>

                <div class="row" id="ticket-text">
                    <div class="col">
                        <div class="row">
                            <h5 id="ticket-text-title" class="col">Scrivi di seguito la tua richiesta:</h5>
                        </div>
                        <div class="row">
                            <textarea class="form-control col" rows="5" id="ticket-textarea" name="tickettext"required></textarea>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col" id="insertTicketButton">
                        <button class="btn btn-primary">Invia</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<%@ include file="footer.jsp" %>



</body>
</html>
