<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/footer.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
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
	<!-- FOOTER -->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>


</body>

</html>