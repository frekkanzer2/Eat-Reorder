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

	<!--DIV that contains the image and the searchbar-->
	<div>
		<!--IMAGE ON THE HOME-->
		<img id="image-home" class="img-fluid d-block mx-auto"
			src="assets/img/LogomarcoIS%20PNG.png">
		<!--Form for the research-->
		<div class="d-flex justify-content-center" style="height: 100%;">
			<input id="search-field" class="form-control-sm d-inline"
				type="search" placeholder="Inserisci la città">
			<button class="btn btn-sm border-secondary standard-button"
				type="submit">Cerca</button>
		</div>
	</div>

	<!--DIV that contains the link to the report of a company-->
	<div id="container-report-order" class="custom-text-right">
		<a href="#">Segnala un problema con un ordine</a>
	</div>

	<!--FOOTER-->
	<jsp:include page="footer.jsp"></jsp:include>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>