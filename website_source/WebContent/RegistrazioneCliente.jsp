<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente != null) response.sendRedirect("Homepage.jsp");
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
<!--Start of body-->
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--Form for registration-->
	<div class="container-form-floating">
		<form class="custom-border-red login-form-style"
			action="DoRegistrazioneCliente" method="post"
			onsubmit="return checkRegistration();">
			<div class="registration-title">Benvenuto in Eat&amp;Reorder</div>
			<div class="registration-description">Ordina comodamente da casa</div>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
					<span><label class="std-label">Email</label></span>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Es. mario.rossi@gmail.com</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login" type="text" name="email" placeholder="Email" id="email">
					<label class="std-label">Password</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50"> Almeno 7 caratteri alfanumerci</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login" type="password" maxlength="20" name="password" placeholder="Password" id="password">
					<label class="std-label">Nome</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50"> Almeno 4 lettere</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login" type="text" name="nome" placeholder="Nome" id="nome">
					<label class="std-label">Cognome</label>
					<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50"> Almeno 4 lettere</span></span>
					<input class="custom-border-red border-rounded-small form-control input-style-login" type="text" name="cognome" placeholder="Cognome" id="cognome">
					<p class="errorText" style="margin-top: 20px;">
					<%
            			if (request.getAttribute("msg_error") != null){
            		%>
					<%=request.getAttribute("msg_error") %>
					<%
            			}
            		%>
				</p>
				<button class="btn form-list-button bg-red border-rounded-small" type="submit" id="submit">Registrati</button>
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