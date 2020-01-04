<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null)
		response.sendRedirect("Homepage.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda))
		response.sendRedirect("HomepageAzienda.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino))
		response.sendRedirect("HomepageFattorino.jsp");
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
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>

<!--BODY HERE-->
<body>
	<!--DIV that contains the image and the searchbar-->
	<div class="center-block">
		<!--IMAGE ON THE HOME-->
		<img id="image-home" class="img-fluid d-block mx-auto"
			src="assets/img/LogomarcoIS%20PNG.png">
		<!--Form for the research-->
		<form id="ban-form" class="form-horizontal center-block" action="">
			<!--Form group for id order input-->
			<div class="form-group">
				<label class="control-label col-sm-5 text-red" for="id_order">ID
					Ordine</label>
				<div class="col-sm-12">
					<input type="text" class="form-control custom-border-yellow"
						id="id_order" placeholder="Inserisci l'ID dell'ordine">
				</div>
			</div>
			<!--Form group for reason to ban-->
			<div class="form-group">
				<label class="control-label col-sm-5 text-red" for="reason">Motivazione</label>
				<div class="col-sm-12">
					<textarea type="text" class="form-control custom-border-yellow"
						id="reason" rows="5"
						placeholder="Inserisci la motivazione del ban"></textarea>
				</div>
			</div>
			<!--Form group for confirm button-->
			<div class="form-group">
				<div class="col-sm-12">
					<button type="submit"
						class="standard-button center-block border-rounded-medium custom-border-yellow"
						style="margin-top: 20px;">Ban</button>
				</div>
			</div>
		</form>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>