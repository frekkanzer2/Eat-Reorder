<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente.getTipo().equals("Azienda")) {
	} else if (utente == null)
		response.sendRedirect("Homepage.jsp");
	else if (utente.getTipo().equals("Cliente"))
		response.sendRedirect("HomepageCliente.jsp");
	else if (utente.getTipo().equals("Fattorino"))
		response.sendRedirect("HomepageFattorino.jsp");
	/*else	response.sendRedirect("HomepageModeratore.jsp");*/
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
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/footer.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<!--BODY HERE-->
<body>

	<!--DIV that contains the image and the searchbar-->
	<div>
		<!--IMAGE ON THE HOME-->
		<img id="image-home" class="img-fluid d-block mx-auto"
			src="assets/img/LogomarcoIS%20PNG.png">
		<!--Button to see the orders commited by users-->
		<div id="container-display-orders" class="center-block">
			<a href="#" class="btn form-list-button bg-red border-rounded-small">Visualizza
				gli ordini</a>
		</div>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>