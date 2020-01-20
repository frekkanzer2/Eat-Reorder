<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null) {
	} else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda))
		response.sendRedirect("HomepageAzienda.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino))
		response.sendRedirect("HomepageFattorino.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Moderatore))
		response.sendRedirect("HomepageModeratore.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente))
		response.sendRedirect("HomepageCliente.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Home</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div>
	<!--IMAGE ON THE HOME-->
	<img id="image-home" class="img-fluid d-block mx-auto" src="assets/img/LogomarcoIS%20PNG.png">
	
	<!--Form for the research-->
	<form action="DoRicercaAzienda" method="POST">
	<div class="d-flex justify-content-center" style="height: 100%;">
		<input id="search-field" name="ricerca" class="form-control-sm d-inline" type="search" placeholder="Inserisci la citt&agrave; dell'azienda che desideri ricercare">
		<button class="standard-button border-rounded-small custom-border-red" type="submit">Cerca</button>
	</div>
	</form>
	
		<%
    	if (request.getAttribute("msg_confirm") != null){
    %>
		<p class="confirmText" style="margin-top: 20px;">
			<%=request.getAttribute("msg_confirm") %>
		</p>
	<% 
		} 
	%>
	
	<%
    	if (request.getAttribute("msg_error") != null){
    %>
		<p class="errorText" style="margin-top: 20px;">
			<%=request.getAttribute("msg_error") %>
		</p>
	<% 
		} 
	%>
	
</div>
<!-- Script -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<%@include file="Footer.html"%>
</body>
</html>