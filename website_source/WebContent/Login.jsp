<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Login</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/fonts/ionicons.min.css">
<link rel="stylesheet" href="assets/css/footer.css">
</head>

<body>
	<div class="login-clean"
		style="background-color: rgb(223, 223, 223); filter: blur(0px) brightness(100%) grayscale(0%);">
		<form class="border rounded" method="post" action="LoginServlet"
			style="background-color: rgb(242, 206, 132); opacity: 1; filter: blur(0px);">
			<h2 class="sr-only">Login Form</h2>
			<a href="Homepage.jsp"> <img
				src="assets/img/LogomarcoIS%20PNG.png"
				style="width: 250px; margin-bottom: 12px; margin-right: 0px;">
			</a>
			<div class="form-group">
				<input class="border rounded border-warning form-control"
					type="email" name="email" placeholder="Email"
					style="background-color: rgb(255, 255, 255); color: rgb(0, 0, 0);">
			</div>
			<div class="form-group">
				<input class="border rounded border-warning form-control"
					type="password" name="password" placeholder="Password"
					style="background-color: rgb(255, 255, 255); color: rgb(0, 0, 0);">
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block border rounded"
					type="submit" style="background-color: rgb(163, 20, 14);">Accedi</button>
			</div>
		</form>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>
<jsp:include page="footer.jsp"></jsp:include>