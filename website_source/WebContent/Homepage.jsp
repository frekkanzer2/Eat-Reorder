<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"></jsp:include>
<div>
	<!--IMAGE ON THE HOME-->
	<img id="image-home" class="img-fluid d-block mx-auto"
		src="assets/img/LogomarcoIS%20PNG.png">
	<!--Form for the research-->
	<div class="d-flex justify-content-center" style="height: 100%;">
		<input id="search-field" class="form-control-sm d-inline"
			type="search" placeholder="Inserisci la città">
		<!--TO REFACTOR-->
		<button class="btn btn-light btn-sm border-secondary" type="submit"
			style="width: 82px; height: 30px; font-size: 15px; background-color: rgb(254, 182, 62);">Cerca</button>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
