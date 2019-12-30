<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
		<!--Bar that contains buttons or other things-->
		<div class="collapse navbar-collapse" id="navcol-1">
			<ul class="nav navbar-nav mr-auto"></ul>
			<!--Span that contains buttons-->
			<span class="float-right navbar-text actions"> <!--Buttons-->
				<a class="btn btn-light header-button" role="button"
				href="Login.jsp">Accedi</a> <a class="btn btn-light header-button"
				role="button" href="#">Registrati</a>
			</span>
		</div>
	</div>
</nav>