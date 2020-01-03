<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Segnalazione</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/footer.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="contact-clean" style="margin-top: 5px">
		<form class="custom-border-red login-form-style" method="post"
			action="SegnalazioneServlet"
			style="background-color: rgb(242, 206, 132);">
			<h2 class="text-left" style="margin-top: -19px; margin-bottom: 13px;">Eat
				&amp; Reorder</h2>
			<p class="text-left "
				style="font-size: 12px; margin-top: -5px; margin-bottom: -20px;">
				Hai avuto un problema con un ordine? Non c'è problema,ti aiuta lo
				staff Eat&amp;Reorder!<br>Contattaci descrivendo accuratamente
				il problema nel form sottostante<br> <br> <br>
			</p>
			<div class="form-group">
				<input class="border rounded form-control" type="text"
					name="NumeroOrdine" placeholder="Numero Ordine">
			</div>
			<div class="form-group">
				<textarea class="border rounded form-control" name="Descrizione"
					placeholder="Descrivi qui il problema" rows="6"></textarea>
			</div>
			<button class="btn form-list-button bg-red border-rounded-small"
				type="button">Invia segnalazione</button>
			<p style="font-size: 11px;">Non sai dove trovare il numero
				d'ordine? Controlla l'email ricevuta al completamento dell'ordine.</p>
		</form>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>