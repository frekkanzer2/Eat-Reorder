<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Segnalazione</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container-form-floating">
		<div id="container-report"class="custom-border-red bg-yellow center-block">
			<div class="report-title" tyle="margin-bottom: 10px;">Hai avuto un problema con un ordine?</div>
			<div class="report-description"style="margin-bottom:14px;">Descrivi accuratamente il problema riscontrato.<br />Lo staff di Eat&amp;Reorder ti aiuter&aacute; con piacere!</div>
			<!--REPORT FORM-->
			<form id="report-form"class="form-horizontal center-block"action="">
				<!--Form group for id order input-->
				<div class="form-group">
					<div class="col-sm-12">
						<input type="text"class="form-control custom-border-red"id="id_order"placeholder="Inserisci l'ID dell'ordine"pattern="[0-9]+">
					</div>
				</div>
				<!--Form group for reason to ban (problemi con regular expression-->
				<div class="form-group">
					<div class="col-sm-12">
						<textarea type="text"class="form-control custom-border-red"id="reason"rows="5"placeholder="Descrivi il problema riscontrato"pattern="[a-zA-Z0-9\. ’àèìòù]{10,250}"></textarea>
					</div>
				</div>
				<!--Form group for confirm button-->
				<div class="form-group">
					<div class="col-sm-12">
						<button type="submit"class="standard-button center-block border-rounded-medium custom-border-red bg-red text-yellow"style="margin-top:20px;">Invia</button>
					</div>
				</div>
			</form>
			<div class="report-description">Non trovi l'ID dell'ordine?<br />Controlla la mail ricevuta al momento dell'ordine!</div>
		</div>
	</div>
	<!-- SCRIPT -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>