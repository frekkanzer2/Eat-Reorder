<%@page import="model.bean.AccountFattorino_Bean"%>
<%@page import="model.bean.Ordine_Bean"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	AccountUtenteRegistrato_Bean user = null;
	AccountFattorino_Bean fattorino = null;
	List<Ordine_Bean> ordini = null;
	Boolean canContinue = true;
	user = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (user == null || !user.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino)) {
		response.sendRedirect("Homepage.jsp");
		canContinue = false;
	}
	if (canContinue){
		fattorino = (AccountFattorino_Bean) user;
		ordini = (List<Ordine_Bean>) request.getAttribute("ordini");
		if (ordini == null) {
			response.sendRedirect("Homepage.jsp");
			canContinue = false;
		}
	}
%>

<!DOCTYPE html>
<html>

<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Consegne</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<%
		if (fattorino != null && canContinue) {
	%>
	<!--External container-->
	<div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
		<div class="registration-title">Bentornato <%=fattorino.getNome()%>,</div>
		<!--REFACTORIZZARE CON JSP INSERENDO IL NOME DEL FATTORINO-->
		<div class="registration-description">&Egrave; ora di fare consegne!</div>
		<img class="std-img center-block"
			src="assets/img/LogomarcoIS%20PNG.png">

		<!--HERE STARTS THE CARD OF THE ORDER-->

		<!--CARD OF THE ORDER-->
		<%
			for (Ordine_Bean ordine : ordini) {
		%>

		<div id="flip" class="bg-yellow custom-border-red border-rounded-small padding-medium card-spacing-fix" onclick="openPanel(<%=ordine.getCodiceID()%>)">
			<div class="row">
				<!--First column contains ID-->
				<div class="col-sm-6">
					<p id="order-id" class="title-in-card pvt-fix-spacing">
						Order
						<%=ordine.getCodiceID()%>
						<!--Sample ID, use scriptlet also here!-->
					</p>
				</div>
				<!--Second column that contains notes for status of the order-->
				<div class="col-sm-6">
					<p id="str-do-not-edit" class="description-in-card-text">
						Stato dell'ordine
						<!--DO NOT CHANGE WITH SCRIPTLET-->
					</p>
					<p id="order-status" class="description-in-card-text">
						<%=ordine.getStato()%>
						<!--Sample STATUS, use scriptlet also here!-->
					</p>
				</div>
			</div>
		</div>

		<div id="panel-<%=ordine.getCodiceID()%>"
			class="partial-container-form-floating center-block"
			style="width: 80%; display: none">
			<div class="registration-title">
				Consegna n.
				<%=ordine.getCodiceID()%>
				<!--ADD ORDER ID HERE WITH SCRIPTLET-->
			</div>

			<!--Edit everything with scriptlets-->
			<div class="registration-description">
				<span style="color: grey;"> Fattorino: </span>
				<%=fattorino.getNome()%>
				<!--ADD COURIER NAME HERE WITH SCRIPTLET-->
				<%=fattorino.getCognome()%>
				<!--ADD COURIER SURNAME HERE WITH SCRIPTLET-->
			</div>
			<div class="registration-description">
				<span style="color: grey;"> Cliente: </span>
				<%=ordine.getCliente().getNome()%>
				<%=ordine.getCliente().getCognome()%>
			</div>
			<div class="registration-description">
				<span style="color: grey;"> Cellulare Cliente: </span>
				<%=ordine.getTelefono()%>
				<!--ADD CUSTOMER NAME HERE WITH SCRIPTLET-->

				<!--DO NOT TOUCH THE COMMA!!!-->

				<!--ADD CUSTOMER PHONE HERE WITH SCRIPTLET-->
			</div>
			<div class="registration-description">
				<span style="color: grey;"> Azienda: </span>
				<%=ordine.getAzienda().getNome()%>
				<!--ADD COMPANY NAME HERE WITH SCRIPTLET-->
				
				<!--DO NOT TOUCH THE COMMA!!!-->
				<%=ordine.getAzienda().getTelefono()%>
				<!--ADD CUSTOMER PHONE HERE WITH SCRIPTLET-->
			</div>
			<hr />
			<div class="registration-description" style="text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;">
				<b>Ritirare al seguente indirizzo</b>
			</div>
			<div class="registration-description">
				<%=ordine.getAzienda().getCitta()%>, (<%=ordine.getAzienda().getProvincia()%>)
				<!--ADD ORDER SENDER CITY HERE WITH SCRIPTLET-->
				<br />
				<%=ordine.getAzienda().getVia()%>,
				<%=ordine.getAzienda().getNumeroCivico()%>
				<!--ADD ORDER SENDER ADDRESS HERE WITH SCRIPTLET-->
			</div>
			<div class="registration-description" style="text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;">
				<b>Consegnare al seguente indirizzo</b>
			</div>
			<div class="registration-description">
				<%=ordine.getIndirizzoConsegna()%>
			</div>
			<hr />
			<div class="registration-description" style="text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;">
				<b>Note del cliente</b>
			</div>
			<div class="registration-description">
				<!--IF NOTES IS EMPTY, DISPLAY "Non ci sono note"-->
				<%=ordine.getNote()%>
				<!--ADD ORDER NOTES HERE WITH SCRIPTLET-->
			</div>
			<hr />
			<div class="registration-description" style="text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;">
				<b>Stato dell'ordine</b>
			</div>
			<div class="registration-description" style="color:orange; font-size: 30px; text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;">
				<%=ordine.getStato()%>
				<!--ADD ORDER STATUS HERE WITH SCRIPTLET-->
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="col-sm-5">
						<div class="registration-description" style="font-size: 22px; text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;"">
						<b>Conferma ritiro</b>
					</div>
					<%
						if (ordine.getStato().equals(Ordine_Bean.IN_PREPARAZIONE)) {
					%>
					<button onclick="location.href='DoSetRitirato?id=<%=ordine.getCodiceID()%>'"
						class="standard-button-restyle center-block ">Ritirato</button>
					<!--BUTTON FOR CONFIRM-->
					<%}else{ %>
					<button onclick="location.href='DoSetRitirato?id=<%=ordine.getCodiceID()%>'"
						class="standard-button-restyle center-block btn-dark" disabled>Ritirato</button>
					<!--BUTTON FOR CONFIRM-->
					
					<%} %>
				</div>
				<div class="col-sm-5">
					
					<div class="registration-description" style="font-size: 22px; text-shadow: 1px 0px 1px #2e2b2b, 0px 1px 1px #2e2b2b, 2px 1px 1px #2e2b2b, 1px 2px 1px #2e2b2b, 3px 2px 1px #2e2b2b, 2px 3px 1px #2e2b2b, 4px 3px 1px #2e2b2b, 3px 4px 1px #2e2b2b, 5px 4px 1px #2e2b2b, 4px 5px 1px #2e2b2b, 6px 5px 1px #2e2b2b, 5px 6px 1px #2e2b2b, 7px 6px 1px #2e2b2b;"">
						<b>Conferma consegna</b>
					</div>
					<%if(ordine.getStato().equals(Ordine_Bean.RITIRATO)) {%>
					<button onclick="location.href='DoSetConsegnato?id=<%=ordine.getCodiceID()%>'"
						class="standard-button-restyle center-block">Consegnato</button>
					<!--BUTTON FOR CONFIRM-->
					<%}else{ %>
					<button onclick="location.href='DoSetConsegnato?id=<%=ordine.getCodiceID()%>'"
						class="standard-button-restyle center-block btn-dark" disabled>Consegnato</button>
					<!--BUTTON FOR CONFIRM-->
					
					<%} %>
				</div>

			</div>
		</div>
		<%
			}
		%>
		<!--END OF ORDER'S CARD-->

	</div>
	<%
		}
	%>
	<script src="assets/js/jquery.min.js"></script>
	<script type="text/javascript">
		function openPanel(id){
				$("#panel-"+id).slideToggle("slow");
		}
	</script>
	<%@include file="Footer.html"%>
</body>

</html>