<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                <h5>
                                    <c:if test="${message.sender == true}">
                                        ${ticket.user.username} (${ticket.user.name} ${ticket.user.surname})
                                    </c:if>
                                    <c:if test="${message.sender == false}">
                                        ${ticket.admin.id}
                                    </c:if>
                                </h5>
                            </div>
                            <div class="col-sm-4">
                                <p class="text-right">scritto il ${message.date}</p>
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
    <c:if test="${differentadmin == null && wrongticket == null && permissions == null}">
        <div class="row">
            <form class="form-horizontal col" method="post" action="createticket">
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
    </c:if>
</div>
</body>
</html>
