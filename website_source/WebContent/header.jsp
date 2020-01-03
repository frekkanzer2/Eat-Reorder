<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<!--HEADER-->
<nav
	class="navbar navbar-dark navbar-expand-md navigation-clean-button custom-border-red bg-yellow">
	<div class="container">
		<!--Logo-->
		<a id="er-logo" class="navbar-brand" href="Homepage.jsp">Eat&amp;Reorder</a>
		<!--Toggle button for UtenteNonRegistrato-->
		<button id="toggle-cstyle" data-toggle="collapse"
			class="navbar-toggler" data-target="#navcol-1">
			<span class="sr-only"></span><span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navcol-1">
			<%
				utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
				if (utente == null) {
			%>
			<!--Bar that contains buttons or other things for Not User-->
			<ul class="nav navbar-nav mr-auto"></ul>
			<!--Span that contains buttons-->
			<span id="header-span-btns" class="navbar-text actions"> <!--Buttons-->
				<a class="btn header-button" role="button" href="Login.jsp">Accedi</a> <a
				class="btn header-button" role="button" href="Registrazione.jsp">Registrati</a>
			</span>
			<%
				} else if (utente.getTipo().equals("Cliente")) {
			%>
			<!--Bar that contains buttons or other things for Cliente-->
			<ul class="nav navbar-nav mr-auto"></ul>
			<!--Span that contains buttons-->
			<span id="header-span-btns" class="navbar-text actions"> <!--Buttons-->
				<a class="btn header-button" role="button" href="#">Profilo</a> <a
				class="btn header-button" role="button" href="#">Modifica
					Profilo</a> <a class="btn header-button" role="button" href="#">Carrello</a>
				<a class="btn header-button" role="button" href="LogoutServlet">Logout</a>
			</span>
			<%
				} else if (utente.getTipo().equals("Azienda")) {
			%>
			<ul class="nav navbar-nav mr-auto"></ul>
			<!--Span that contains buttons for Azienda-->
			<span id="header-span-btns" class="navbar-text actions"> <!--Buttons-->
				<a class="btn header-button-smaller" role="button" href="#">Profilo</a>
				<a class="btn header-button-smaller" role="button" href="#">Modifica
					Profilo</a> <a class="btn header-button-smaller" role="button" href="#">Listino</a>
				<a class="btn header-button-smaller" role="button" href="#">Inserisci
					Prodotto</a> <a class="btn header-button-smaller" role="button"
				href="LogoutServlet">Logout</a>
			</span>
			<%
				} else {
			%>
			<ul class="nav navbar-nav mr-auto"></ul>
			<!--Span that contains buttons for Fattorino-->
			<span id="header-span-btns" class="navbar-text actions"> <!--Buttons-->
				<a class="btn header-button" role="button" href="#">Profilo</a> <a
				class="btn header-button" role="button" href="#">Modifica
					Profilo</a> <a class="btn header-button" role="button"
				href="LogoutServlet">Logout</a>
			</span>
			<%
				}
			%>
		</div>
	</div>
</nav>