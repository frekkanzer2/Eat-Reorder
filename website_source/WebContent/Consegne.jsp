<%@page import="model.bean.Ordine_Bean"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean user = null;%>

<% user = (AccountUtenteRegistrato_Bean)session.getAttribute("utente");
if(user==null || !user.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino)){
	response.sendRedirect("Homepage.jsp");
}

List<Ordine_Bean> ordini = (List<Ordine_Bean>)request.getAttribute("ordini");
%>

<!DOCTYPE html>
<html>

<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Consegne</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
    <!--External container-->
    <div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
        <div class="registration-title">Bentornato, </div> <!--REFACTORIZZARE CON JSP INSERENDO IL NOME DEL FATTORINO-->
		<div class="registration-description">E&grave; ora di fare consegne!</div>
        <img class="std-img center-block" src="assets/img/LogomarcoIS%20PNG.png">

        <!--HERE STARTS THE CARD OF THE ORDER-->

        <!--CARD OF THE ORDER-->
        <%for(Ordine_Bean ordine : ordini){%>
        <a href = "#"> <!--THIS A TAG WILL REDIRECT YOU TO THE ORDER DETAILS-->
        <div class="bg-yellow custom-border-red border-rounded-small padding-medium card-spacing-fix" style="overflow: auto;">
            <div class="row">
                <!--First column contains ID-->
                <div class="col-sm-6">
                    <p id="order-id" class="title-in-card pvt-fix-spacing">
                        Order <%=ordine.getCodiceID()%> <!--Sample ID, use scriptlet also here!-->
                    </p>
                </div>
                <!--Second column that contains notes for status of the order-->
                <div class="col-sm-6">
                    <p id="str-do-not-edit" class="description-in-card-text">
                        Stato dell'ordine <!--DO NOT CHANGE WITH SCRIPTLET-->
                    </p>
                    <p id="order-status" class="description-in-card-text">
                       <%=ordine.getStato()%> <!--Sample STATUS, use scriptlet also here!-->
                    </p>
                </div>
            </div>
        </div>
        </a>
        <%} %>
        <!--END OF ORDER'S CARD-->

    </div>

</body>

</html>