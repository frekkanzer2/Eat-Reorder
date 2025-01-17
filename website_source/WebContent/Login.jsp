<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente=(AccountUtenteRegistrato_Bean)session.getAttribute("utente");
	if (utente!=null) {
		response.sendRedirect("Homepage.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Login</title>
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
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<!--Form for login-->
	<div class="alternative-background container-form-floating">
		<form class="custom-border-red login-form-style"method="post"action="DoLogin">
			<!--Invisible title-->
			<h2 class="sr-only">Login Form</h2>
			<!--Image on the form-->
			<img id="image-login-form" src="assets/img/LogomarcoIS%20PNG.png">
			<!--Form group that contains fields and confirm button-->
			<div class="form-group">
				<span></span>
				<label class="std-label">Email</label>
				<input class="custom-border-red border-rounded-small form-control input-style-login"type="text"name="email"placeholder="Email">
				<label class="std-label">Password</label>
				<input class="custom-border-red border-rounded-small form-control input-style-login"type="password"name="password"placeholder="Password">
				<p class="errorText" style="margin-top: 20px;">
					<%
            			if (request.getAttribute("msg_error") != null){
            		%>
					<%=request.getAttribute("msg_error") %>
					<% 
						} 
					%>
				</p>
				<button class="btn form-list-button bg-red border-rounded-small"type="submit">Accedi</button>
			</div>
		</form>
	</div>
	<!-- Script -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<%@include file="Footer.html"%>
</body>
</html>