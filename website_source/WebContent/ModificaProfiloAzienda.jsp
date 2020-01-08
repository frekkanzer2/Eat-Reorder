<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@page import="model.bean.AccountAzienda_Bean"%>
<%@page import="java.time.DayOfWeek"%>

<%!AccountUtenteRegistrato_Bean utente = null;%>
<%!AccountAzienda_Bean azienda = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda))
		response.sendRedirect("Homepage.jsp");
	azienda = (AccountAzienda_Bean) utente;
%>
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
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--Form for registration-->
	<div class="container-form-floating">
		<form class="custom-border-red login-form-style" method="post"
			action="DoModificaProfiloAzienda" onsubmit="return checkModifica();">
			<div class="registration-title">Modifica il tuo profilo</div>
			<div class="registration-description">Saranno modificati solo i
				campi inseriti</div>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<p class="errorText" style="margin-top: 20px;">
				<%
					if (request.getAttribute("msg_error") != null) {
				%>
				<%=request.getAttribute("msg_error")%>
				<%
					}
				%>
			</p>
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
				<label class="std-label">Nome dell'azienda</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="nome" placeholder="Nome dell'attivit&agrave"
					pattern="[a-zA-Z 'àèìòù]{3,40}" value="<%=azienda.getNome()%>">
				<label class="std-label">Telefono</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="telefono" placeholder="Telefono"
					pattern="[0-9]{9,10}" value="<%=azienda.getTelefono()%>">
				<label class="std-label">Indirizzo</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="indirizzo" placeholder="Indirizzo"
					pattern="[a-zA-Z 'àèìòù]{3,80}" value="<%=azienda.getVia()%>">
				<label class="std-label">Numero civico</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="civico" placeholder="Numero civico"
					pattern="[0-9]{1,3}" value="<%=azienda.getNumeroCivico()%>">
				<label class="std-label">Citt&agrave</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="citta" placeholder="Citt&agrave"
					pattern="[a-zA-Z 'àèìòù]{4,45}" value="<%=azienda.getCitta()%>">
				<label class="std-label">Provincia</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="text" name="provincia" placeholder="Provincia"
					pattern="[a-zA-Z]{2}" value="<%=azienda.getProvincia()%>">
				<label class="std-label">Password</label>
				<input
					class="custom-border-red border-rounded-small form-control input-style-login"
					type="password" name="password" placeholder="Password"
					pattern="[a-zA-Z0-9]{7,20}" value="<%=azienda.getPassword()%>">
				<hr />
				<!--List of checkboxes-->
				<%Set<DayOfWeek> giorni = azienda.getGiorniDiApertura();%>
				<div class="custom-text-centered text-red"
					style="margin-top: -6px; margin-bottom: 6px;">Giorni
					lavorativi</div>
				<div class="row" style="width: 100%; margin: 0; padding: 0;">
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.MONDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day1" value = "MONDAY"checked><%}else {%>
						
						<input name="checkbox" class="form-check-input" type="checkbox"
							value = "MONDAY" id="checkbox-day1">
							<%} %>
						<label class="form-check-label"
							for="checkbox-day1">Luned&igrave</label><br>
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.TUESDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day2" value = "TUESDAY"checked><%}else {%>
						
						<input name="checkbox" class="form-check-input" type="checkbox"
							value = "TUESDAY" id="checkbox-day2"><%} %>
						<label class="form-check-label"
							for="checkbox-day2">Marted&igrave</label><br>
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.WEDNESDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day3" value = "WEDNESDAY" checked><%}else {%>
					
						<input name="checkbox" class="form-check-input" type="checkbox"
							value = "WEDNESDAY" id="checkbox-day3" >	<%} %>
					    <label class="form-check-label"
							for="checkbox-day3">Mercoled&igrave</label><br>
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.THURSDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day4" value = "THURSDAY"checked><%}else {%>
						
						<input name="checkbox" class="form-check-input" type="checkbox"
							value = "THURSDAY" id="checkbox-day4"><%} %>
							 <label class="form-check-label"
							for="checkbox-day4">Gioved&igrave</label>
					</div>
					<div class="col-sm-6 list-of-checkboxes">
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.FRIDAY)){%>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day5" value = "FRIDAY" checked><%}else {%>
						
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day5" value = "FRIDAY">	<%} %>
						<label class="form-check-label"
							for="checkbox-day5">Venerd&igrave</label><br>
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.SATURDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day6" value = "SATURDAY"checked><%}else {%>
						
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day6" value = "SATURDAY"><%} %>
							 <label
							class="form-check-label" for="checkbox-day6">Sabato</label><br>
						<!--New day-->
						<%if(giorni.contains(DayOfWeek.SUNDAY)){ %>
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day7" value = "SUNDAY" checked><%}else {%>
							
						<input name="checkbox" class="form-check-input" type="checkbox"
							id="checkbox-day7" value="SUNDAY"><%} %>
						<label class="form-check-label"
							for="checkbox-day7">Domenica</label>
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
							name="start-time" value="<%=azienda.getOrarioDiApertura().truncatedTo(ChronoUnit.MINUTES).toString()%>" required>
					</div>
					<div class="col-sm-6">
						<div class="custom-text-centered text-red"
							style="margin-top: -6px; margin-bottom: 6px;">Orario di
							chiusura</div>
						<input type="time" id="end-time"
							class="center-block custom-border-red border-rounded-medium"
							name="end-time" value="<%=azienda.getOrarioDiChiusura().truncatedTo(ChronoUnit.MINUTES).toString()%>" required>
					</div>
				</div>
				<hr />
				<!--Confirm button-->
				<button class="btn form-list-button bg-red border-rounded-small"
					type="submit">Modifica</button>
			</div>
		</form>
	</div>
	<!-- SCRIPT -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function checkModifica() {
			var nome = document.getElementById("nome");
			var telefono = document.getElementById("telefono");
			var indirizzo = document.getElementById("indirizzo");
			var civico = document.getElementById("civico");
			var citta = document.getElementById("citta");
			var provincia = document.getElementById("provincia");
			var password = document.getElementById("password");
			if (nome.value == "" || telefono.value == ""
					|| indirizzo.value == "" || civico.value == ""
					|| citta == "" || provincia == "" || password == "") {
				alert("ATTENZIONE! Errore nella validazione dei campi!")
				return false;
			}
			return true;
		}
	</script>
</body>
</html>