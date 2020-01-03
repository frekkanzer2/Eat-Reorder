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
		<form class="custom-border-red login-form-style" method="post"
			action="RegistrazioneFattorinoServlet">
			<!--Invisible title-->
			<h2 class="sr-only">Login Form</h2>
			<div class="registration-title">Benvenuto in Eat&amp;Reorder</div>
			<div class="registration-description">Consegna piatti quando
				vuoi</div>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="email" name="email" placeholder="Email"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="password" name="password" placeholder="Password"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="nome" name="nome" placeholder="Nome"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="cognome" name="cognome" placeholder="Cognome"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="telefono" name="telefono" placeholder="Telefono"> <input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="citta" name="citta" placeholder="Città di consegna">
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="provincia" name="provincia"
					placeholder="Provincia di consegna">
				<hr />
				<!--List of checkboxes-->
				<div class="custom-text-centered text-red"
					style="margin-top: -6px; margin-bottom: 6px;">Giorni
					lavorativi</div>
				<div class="row" style="width: 100%; margin: 0; padding: 0;">
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day1">
						<label class="form-check-label" for="checkbox-day1">
							Lunedì </label> <br>
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day2">
						<label class="form-check-label" for="checkbox-day2">
							Martedì </label> <br>
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day3">
						<label class="form-check-label" for="checkbox-day3">
							Mercoledì </label> <br>
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day4">
						<label class="form-check-label" for="checkbox-day4">
							Giovedì </label>
					</div>
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day5">
						<label class="form-check-label" for="checkbox-day5">
							Venerdì </label> <br>
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day6">
						<label class="form-check-label" for="checkbox-day6">
							Sabato </label> <br>
						<!--New day-->
						<input class="form-check-input" type="checkbox" id="checkbox-day7">
						<label class="form-check-label" for="checkbox-day7">
							Domenica </label>
					</div>
				</div>
				<hr />

				<!--List of hours-->
				<div class="custom-text-centered text-red"
					style="margin-top: -6px; margin-bottom: 6px;">Orario
					lavorativo</div>
				<div class="row" style="width: 100%; margin: 0; padding: 0;">
					<div class="col-sm-6">
						<div class="custom-text-centered text-red"
							style="margin-top: -6px; margin-bottom: 6px;">Inizio
							consegne</div>
						<input type="time" id="start-time"
							class="center-block custom-border-red border-rounded-medium"
							name="start-time" required>
					</div>
					<div class="col-sm-6">
						<div class="custom-text-centered text-red"
							style="margin-top: -6px; margin-bottom: 6px;">Fine consegne</div>
						<input type="time" id="end-time"
							class="center-block custom-border-red border-rounded-medium"
							name="end-time" required>
					</div>
				</div>
				<hr />

				<!--Confirm button-->
				<button class="btn form-list-button bg-red border-rounded-small"
					type="submit">Registrati</button>
			</div>
		</form>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>