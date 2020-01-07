<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente=(AccountUtenteRegistrato_Bean)session.getAttribute("utente");
	if(utente==null) response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <!--Viewport for toggle-->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--Title-->
        <title>Home</title>
        <!--Preset imports-->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <!--Custom imports-->
        <link rel="stylesheet" href="css/eat-reorder-style.css">
        <%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
        <%@page import="model.bean.AccountModeratore_Bean"%>
		<%@page import="model.bean.AccountFattorino_Bean"%>
		<%@page import="model.bean.AccountAzienda_Bean"%>
		<%@page import="model.bean.AccountCliente_Bean"%>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <!--Display info-->
        <%!AccountCliente_Bean utenteCliente = null;%>
        <%!AccountAzienda_Bean utenteAzienda = null;%>
        <%!AccountFattorino_Bean utenteFattorino = null;%>
        <%!AccountModeratore_Bean utenteModeratore = null;%>
	    <div class="container-form-floating">
	    	<!-- CASE CLIENTE -->
	    	<%
	    		if(utente != null){
	    			
					if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) {
						utenteCliente = (AccountCliente_Bean) utente;
			%>
			<div class="registration-title">Benvenuto <%= utenteCliente.getNome() %></div>
			<%
					}
			%>
			<!-- CASE AZIENDA -->
	    	<%
	    			if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda)) {
						utenteAzienda = (AccountAzienda_Bean) utente;
			%>
			<div class="registration-title">Benvenuto <%= utenteAzienda.getNome() %></div>
			<%
					}
			%>
			<!-- CASE FATTORINO -->
	    	<%
	    			if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino)) {
						utenteFattorino = (AccountFattorino_Bean) utente;
			%>
			<div class="registration-title">Benvenuto <%= utenteFattorino.getNome() %></div>
			<%
					}
			%>
			<!-- CASE MODERATORE -->
	    	<%
	    			if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Moderatore)) {
						utenteModeratore = (AccountModeratore_Bean) utente;
			%>
			<div class="registration-title">Benvenuto Moderatore</div>
			<%
					}
	    		}
			%>
			<!--Image on the form-->
			<img id="image-home" class="img-fluid d-block mx-auto" src="assets/img/LogomarcoIS%20PNG.png">
			<!-- DISPLAYING DATAS -->
			<!-- CASE CLIENTE -->
	    	<%
				if (utenteCliente != null) {
			%>
			<div class="registration-description">Nome: <%= utenteCliente.getNome() %></div>
			<div class="registration-description">Cognome: <%= utenteCliente.getCognome() %></div>
			<div class="registration-description">Email: <%= utenteCliente.getEmail() %></div>
			<%
				}
			%>
			<!-- CASE AZIENDA -->
	    	<%
	    		if (utenteAzienda != null) {
			%>
			<div class="registration-description">Nome: <%= utenteAzienda.getNome() %></div>
			<div class="registration-description">Telefono: <%= utenteAzienda.getTelefono() %></div>
			<div class="registration-description">Indirizzo: <%= utenteAzienda.getVia() %>, <%= utenteAzienda.getNumeroCivico() %></div>
			<div class="registration-description">Citt&agrave: <%= utenteAzienda.getCitta() %></div>
			<div class="registration-description">Provincia: <%= utenteAzienda.getProvincia() %></div>
			<div class="registration-description">Partita IVA: <%= utenteAzienda.getPartitaIva() %></div>
			<div class="registration-description">Email: <%= utenteAzienda.getEmail() %></div>
			<%
				}
			%>
			<!-- CASE FATTORINO -->
	    	<%
	    		if (utenteFattorino != null) {
			%>
			<div class="registration-description">Nome: <%= utenteFattorino.getNome() %></div>
			<div class="registration-description">Cognome: <%= utenteFattorino.getCognome() %></div>
			<div class="registration-description">Telefono: <%= utenteFattorino.getTelefono() %></div>
			<div class="registration-description">Citt&agrave: <%= utenteFattorino.getCittaConsegna() %></div>
			<div class="registration-description">Provincia: <%= utenteFattorino.getProvinciaConsegna() %></div>
			<%
				}
			%>
			<!-- CASE MODERATORE -->
	    	<%
	    		if (utenteModeratore != null) {
			%>
			<div class="registration-description">Email: <%= utenteModeratore.getEmail() %></div>
			<%
				}
			%>
	    </div>
	    <!-- Script -->
	    <script src="assets/js/jquery.min.js"></script>
	    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
        