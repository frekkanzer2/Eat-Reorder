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
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>
<!--Start of body-->
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

	<!--Form for registration-->
	<div class="container-form-floating">
		<form class="custom-border-red login-form-style"
			action="RegistrazioneClienteServlet" method="post"
			onsubmit="return checkRegistration();">
			<div class="registration-title">Benvenuto in Eat&amp;Reorder</div>
			<div class="registration-description">Ordina comodamente da
				casa</div>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="email" name="email" placeholder="Email" id="email"
					pattern="[a-zA-Z0-9][a-zA-Z0-9\.]*@([a-zA-Z]+)\.[a-zA-Z]+"><input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="password" maxlength="20" name="password"
					placeholder="Password" id="password" pattern="[a-zA-Z0-9]{7,20}">
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="nome" name="nome" placeholder="Nome" id="nome"
					pattern="[a-zA-Z ‘àèìòù]{3,20}"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="cognome" name="cognome" placeholder="Cognome" id="cognome"
					pattern="[a-zA-Z ‘àèìòù]{3,20}">
				<button class="btn form-list-button bg-red border-rounded-small"
					type="submit" id="submit">Registrati</button>
			</div>
		</form>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function checkRegistration() {
			var email = document.getElementById("email");
			var password = document.getElementById("password");
			var nome = document.getElementById("nome");
			var cognome = document.getElementById("cognome");
			if (email.value == "" || password.value == "" || nome.value == ""
					|| cognome.value == "") {
				alert("ATTENZIONE! Errore nella validazione dei campi!")
				return false;
			}
			return true;
		}
	</script>
</body>
</html>