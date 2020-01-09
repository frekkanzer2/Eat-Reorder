<%@page import="model.bean.Prodotto_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%Prodotto_Bean prodotto = (Prodotto_Bean)request.getAttribute("prodotto"); %>
<!DOCTYPE html>
<html>

<head>
    <!--Viewport for toggle-->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--Title-->
    <title>Prodotto</title>
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
	<div class="center-block">
		<!--DIV THAT CONTAINS PRODUCT DETAILS-->
		<div class="custom-border-red generic-form-style partial-container-form-floating">
            <!--EDIT THE TITLE OF THE PRODUCT WITH SCRIPTLET-->
            <div class="registration-title"><%=prodotto.getNome()%><!--Add PRODUCT TITLE here with scriptlet--></div>
            <!--PRODUCT IMAGE-->
            <!--Change with scriptlet image's src-->
            <img id="details-img" class="img-fluid d-block mx-auto custom-border-red border-rounded-large" src="<%=prodotto.getImmagine().toString()%>">
            <!--DETAILS (CHANGE ALSO HERE!!!)-->
            <div class="registration-description">
                <!--Add PRODUCT DESCRIPTION here with scriptlet-->
               <%=prodotto.getDescrizione() %>
            </div>
            <hr/>
            <div class="registration-description"><%=prodotto.getPrezzo()%>&euro;<!--Add PRODUCT PRICE here with scriptlet--></div>
            <!--EDIT A TAG TO REDIRECT TO BUY ITEM SERVLET-->
            <a class="btn form-list-button bg-red border-rounded-small text-white" href="#">Aggiungi al carrello</a>
        </div>
	</div>
	<!-- SCRIPT -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>