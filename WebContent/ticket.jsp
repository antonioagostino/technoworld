<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rispondi al ticket</title>
    <link rel="stylesheet" href="css/ticket.css">
    <%@ include file="include.jsp" %>
</head>
<body>

<%@ include file="menuBar.jsp" %>

<br>
<div class="container-fluid">
    <c:if test="${status != null }">
        <c:if test="${status == 1}">
            <div class="row">
                <div class="alert alert-success col text-center" role="alert">La risposta al ticket è stata creata</div>
            </div>
        </c:if>
        <c:if test="${status == 2}">
            <div class="row">
                <div class="alert alert-danger col text-center" role="alert">Problemi durante la creazione della risposta, riprova</div>
            </div>
        </c:if>
        <c:if test="${status == 3}">
            <div class="row">
                <div class="alert alert-danger col text-center" role="alert">Il messaggio non può essere vuoto</div>
            </div>
        </c:if>
    </c:if>
    <c:if test="${closed != null}">
        <div class="row">
            <div class="alert alert-success col text-center" role="alert">Il ticket è stato chiuso</div>
        </div>
    </c:if>
    <div class="row">
        <c:if test="${permissions != null}">
            <div class="alert alert-danger col text-center" role="alert">
                Non hai i permessi per accedere a questo contenuto
            </div>
        </c:if>
        <c:if test="${differentadmin != null}">
            <div class="alert alert-danger col text-center" role="alert">
                Un amministratore ha già preso in carico questa richiesta
            </div>
        </c:if>
        <c:if test="${wrongticket != null}">
            <div class="alert alert-danger col text-center" role="alert">
                Questo ticket è inesistente!
            </div>
        </c:if>
        <c:if test="${differentadmin == null && wrongticket == null && permissions == null}">
            <h2 class="col display-4 text-center" id="pageTitle"> Ticket #${ticket.id}</h2>
        </c:if>
    </div>
    <c:if test="${differentadmin == null && wrongticket == null && permissions == null}">
        <c:forEach var="message" items="${ticket.messages}">
            <div class="row" id="message-body">
                <div class="col card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-8">
                                <c:if test="${message.sender == true}">
                                    <h5>${ticket.user.username} (${ticket.user.name} ${ticket.user.surname})</h5>
                                </c:if>
                                <c:if test="${message.sender == false}">
                                    <h5 id="admin-title">
                                        Amministratore (${ticket.admin.id})
                                    </h5>
                                </c:if>
                            </div>
                            <div class="col-sm-4">
                                <p class="text-right">scritto il <fmt:formatDate pattern = "dd-MM-yyyy" value = "${message.date}" /></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <p>${message.message}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${differentadmin == null && wrongticket == null && permissions == null && closed == null && ticket.status == 0}">
        <div class="row">
            <form class="form-horizontal col" method="post" action="responseticket?id=${ticket.id}<c:if test="${ticket.admin.id != null}">&aid=${ticket.admin.id}</c:if>">
                <div class="row" id="ticket-text">
                    <div class="col">
                        <div class="row">
                            <h5 id="ticket-text-title" class="col">Rispondi al ticket:</h5>
                        </div>
                        <div class="row" id="ticket-textarea-father">
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
        <c:if test="${administrator != null}">
        <div class="row">
            <h3 class="col text-center">Oppure</h3>
        </div>
        <div class="row">
            <div class="col" id="deleteTicketContainer">
                <a href="closeticket?id=${ticket.id}"><button id="deleteTicketButton" class="btn btn-primary">Chiudi Ticket</button></a>
            </div>
        </div>
        </c:if>
    </c:if>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
