<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente != null)
		response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Benvenuto in Eat&amp;Reorder</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<body>
	<!--HEADER-->
	<nav
		class="navbar navbar-dark navbar-expand-md navigation-clean-button custom-border-red bg-yellow">
		<div class="container">
			<!--Logo-->
			<a id="er-logo" class="navbar-brand custom-text-centered"
				href="Homepage.jsp">Eat&amp;Reorder</a>
		</div>
	</nav>
	<!--Choose registration-->
	<div class="container-form-floating">
		<div id="container-choose-registration"
			class="custom-border-red bg-yellow center-block">
			<div class="registration-title" style="margin-bottom: 10px;">Come
				vuoi registrarti?</div>
			<form action="RegistrazioneCliente.jsp">
				<button type="submit"
					class="btn form-list-button bg-red border-rounded-small">Registrati
					come cliente</button>
			</form>
			<form action="RegistrazioneAzienda.jsp">
				<button type="submit"
					class="btn form-list-button bg-red border-rounded-small">Registrati
					come azienda</button>
			</form>
			<form action="RegistrazioneFattorino.jsp">
				<button type="submit"
					class="btn form-list-button bg-red border-rounded-small">Registrati
					come fattorino</button>
			</form>
		</div>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>