<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%! AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null) response.sendRedirect("Homepage.jsp");
	else if(utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda)) response.sendRedirect("HomepageAzienda.jsp");
	else if(utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) response.sendRedirect("HomepageCliente.jsp");
	else if(utente.getTipo().equals(AccountUtenteRegistrato_Bean.Moderatore)) response.sendRedirect("HomepageModeratore.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Home</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@ page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<!--BODY HERE-->
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--DIV that contains the image and the searchbar-->
	<div>
		<!--IMAGE ON THE HOME-->
		<img id="image-home" class="img-fluid d-block mx-auto" src="assets/img/LogomarcoIS%20PNG.png">
		<!--Button to see the orders commited by users-->
		<div id="container-display-orders" class="center-block">
			<a href="DoVisualizzaConsegne" class="btn form-list-button bg-red border-rounded-small">Visualizza consegne</a>
		</div>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<%@include file="Footer.html"%>
</body>
</html>