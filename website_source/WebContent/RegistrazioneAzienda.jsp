<%@page import="java.time.DayOfWeek"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente=null;%>
<%
	utente=(AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if(utente!=null) response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Benvenuto in Eat&amp;Reorder</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>
<!--Start of body-->
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--Form for registration-->
	<div class="container-form-floating">
		<form class="custom-border-red login-form-style" onsubmit="return checkRegistration();"method="post"action="DoRegistrazioneAzienda">
			<!--Invisible title-->
			<h2 class="sr-only">Login Form</h2>
			<div class="registration-title">Benvenuto in Eat&amp;Reorder</div>
			<div class="registration-description">Automatizza vendite e consegne</div>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<p class="errorText" style="margin-top: 20px;">
					<%
            			if (request.getAttribute("msg_error") != null){
            		%>
					<%=request.getAttribute("msg_error") %>
					<%
            			}
            		%>
				</p>
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
					<label class="std-label">Email</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Es. pizza.panini@gmail.com</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="email"id="email"placeholder="Email">
					<label class="std-label">Password</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Almeno 7 caratteri alfanumerici</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="password"name="password"id="password"placeholder="Password">
					<label class="std-label">Nome</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Almeno 4 caratteri</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="nome"id="nome"placeholder="Nome dell'attivit&agrave">
					<label class="std-label">Telefono</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> 10 Caratteri numerici</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="telefono"id="telefono"placeholder="Telefono">
					<label class="std-label">Indirizzo</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Es. Via Roma</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="indirizzo"id="indirizzo"placeholder="Indirizzo">
					<label class="std-label">Civico</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Massimo 3 numeri</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="number"name="civico"id="civico"placeholder="Numero civico">
					<label class="std-label">Citta</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Almeno 5 caratteri</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="citta"id="civico"placeholder="Citt&agrave">
					<label class="std-label">Provincia</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Esattamente 2 caratteri</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="provincia"id="civico"placeholder="Provincia">
					<label class="std-label">Partita Iva</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Esattamente 11 Caratteri numerici</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="iva"id="iva"placeholder="Partita IVA">
				<hr />
				<!--List of checkboxes-->
				<div class="custom-text-centered text-red"style="margin-top:-6px; margin-bottom:6px;">Giorni lavorativi</div>
				<div class="row"style="width:100%; margin;0;padding:0;">
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox"id="checkbox-day1" value="MONDAY">
						<label class="form-check-label"for="checkbox-day1">Luned&igrave;</label><br>
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox" id="checkbox-day2" value="TUESDAY">
						<label class="form-check-label"for="checkbox-day2">Marted&igrave;</label><br>
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox" id="checkbox-day3" value="WEDNESDAY">
						<label class="form-check-label"for="checkbox-day3">Mercoled&igrave;</label><br>
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox"id="checkbox-day4" value="THURSDAY">
						<label class="form-check-label"for="checkbox-day4">Gioved&igrave;</label>
					</div>
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox"id="checkbox-day5" value="FRIDAY">
						<label class="form-check-label"for="checkbox-day5">Venerd&igrave;</label><br>
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox"id="checkbox-day6" value="SATURDAY">
						<label class="form-check-label"for="checkbox-day6">Sabato</label><br>
						<!--New day-->
						<input name="checkbox" class="form-check-input"type="checkbox"id="checkbox-day7" value="SUNDAY">
						<label class="form-check-label"for="checkbox-day7">Domenica</label>
					</div>
				</div>
				<hr />
				<!--List of hours-->
				<div class="row" style="width:100%;margin:0;padding:0;">
					<div class="col-sm-6">
						<div class="custom-text-centered text-red"style="margin-top:-6px;margin-bottom:6px;">Orario di apertura</div>
						<input type="time"id="start-time"class="center-block custom-border-red border-rounded-medium" name="start-time" value="12:00" required>
					</div>
					<div class="col-sm-6">
						<div class="custom-text-centered text-red"style="margin-top:-6px;margin-bottom:6px;">Orario di chiusura</div>
						<input type="time"id="end-time"class="center-block custom-border-red border-rounded-medium" name="end-time" value="15:00" required>
					</div>
				</div>
				<hr />
				<!--Confirm button-->
				<button class="btn form-list-button bg-red border-rounded-small"type="submit">Registrati</button>
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
			var telefono=document.getElementById("telefono");
			var indirizzo=document.getElementById("indirizzo");
			var civico=document.getElementById("civico");
			var citta=document.getElementById("citta");
			var provincia=document.getElementById("provincia");
			var iva=document.getElementById("iva");
			if (email.value == "" || password.value == "" || nome.value == ""|| telefono.value == ""||indirizzo==""||civico==""||citta==""||provincia==""||iva=="") {
				alert("ATTENZIONE! Errore nella validazione dei campi!")
				return false;
			}
			return true;
		}
	</script>
	<%@include file="Footer.html"%>
</body>
</html>