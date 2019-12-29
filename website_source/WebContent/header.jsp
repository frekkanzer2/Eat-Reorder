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
<title>Home</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/footer.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<!--BODY HERE-->
<body>
	<!--HEADER-->
	<nav
		class="navbar navbar-dark navbar-expand-md bg-warning border-danger border rounded-0 navigation-clean-button">
		<div class="container">
			<!--Logo-->
			<a id="er-logo" class="navbar-brand" href="Homepage.jsp">Eat &amp; Reorder</a>
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
					<a class="btn btn-light header-button" role="button" href="#">Accedi</a>
					<a class="btn btn-light header-button" role="button" href="#">Registrati</a>
				</span>
			</div>
		</div>
	</nav>