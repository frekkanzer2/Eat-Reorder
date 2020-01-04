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
<title>Modifica Profilo</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>
<jsp:include page="header.jsp"></jsp:include>
<!--Form for registration-->
<div class="container-form-floating">
	<form class="custom-border-red login-form-style" method="post">
		<div class="registration-title">Modifica il tuo profilo</div>
		<div class="registration-description">Saranno modificati solo i
			campi inseriti</div>
		<!--Image on the form-->
		<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
		<!--Form group that contains fields and confirm button-->
		<div class="form-group">
			<input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="nome" name="nome" placeholder="Nome dell'attività"
				pattern="[a-zA-Z0-9 ‘àèìòù]{3,20}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="telefono" name="telefono" placeholder="Telefono"
				pattern="[0-9]{9,10}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="indirizzo" name="indirizzo" placeholder="Indirizzo"
				pattern="[a-zA-Z ‘àèìòù]{3,27}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="civico" name="civico" placeholder="Numero civico"
				pattern="[0-9]{1,3}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="citta" name="citta" placeholder="Città"
				pattern="[a-zA-Z ‘àèìòù]{4,15}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="provincia" name="provincia" placeholder="Provincia"
				pattern="[a-zA-Z]{2}"> <input
				class="custom-border-red border-rounded-small form-control input-style-login"
				type="password" name="password" placeholder="Password"
				pattern="[a-zA-Z0-9]{7,20}">
			<hr />
			<!--List of checkboxes-->
			<div class="custom-text-centered text-red"
				style="margin-top: -6px; margin-bottom: 6px;">Giorni
				lavorativi</div>
			<div class="row" style="width: 100%; margin: 0; padding: 0;">
				<div class="col-sm-6 list-of-checkboxes">
					<!--New day-->
					<input class="form-check-input" type="checkbox" id="checkbox-day1">
					<label class="form-check-label" for="checkbox-day1"> Lunedì
					</label> <br>
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
					<label class="form-check-label" for="checkbox-day6"> Sabato
					</label> <br>
					<!--New day-->
					<input class="form-check-input" type="checkbox" id="checkbox-day7">
					<label class="form-check-label" for="checkbox-day7">
						Domenica </label>
				</div>
			</div>
			<hr />

			<!--List of hours-->
			<div class="row" style="width: 100%; margin: 0; padding: 0;">
				<div class="col-sm-6">
					<div class="custom-text-centered text-red"
						style="margin-top: -6px; margin-bottom: 6px;">Orario di
						apertura</div>
					<input type="time" id="start-time"
						class="center-block custom-border-red border-rounded-medium"
						name="start-time" required>
				</div>
				<div class="col-sm-6">
					<div class="custom-text-centered text-red"
						style="margin-top: -6px; margin-bottom: 6px;">Orario di
						chiusura</div>
					<input type="time" id="end-time"
						class="center-block custom-border-red border-rounded-medium"
						name="end-time" required>
				</div>
			</div>
			<hr />

			<!--Confirm button-->
			<button class="btn form-list-button bg-red border-rounded-small"
				type="submit">Modifica</button>
		</div>
	</form>
</div>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>