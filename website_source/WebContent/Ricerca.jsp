<%@page import="model.bean.AccountAzienda_Bean"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
	AccountUtenteRegistrato_Bean utente = null;
	List<AccountAzienda_Bean> aziende = null;
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	Boolean canContinue = true;
	if (utente != null){
		if (!utente.getTipo().equalsIgnoreCase(AccountUtenteRegistrato_Bean.Cliente)) {
			response.sendRedirect("Homepage.jsp");
			canContinue = false;
		}
	}
	if (canContinue) {
		aziende = (List<AccountAzienda_Bean>) request.getAttribute("aziende");
		if (aziende == null) {
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
<title>Ricerca</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

	<%
		if (canContinue) {
	%>

    <!--THERE ARE SOME CARDS ON THE DIV CONTAINER-->
    <!--FOR EACH COMPANY FOUND WITH THE SEARCH ENGINE, YOU HAVE TO CREATE A CARD WITH TAG "A" AND SOME CLASSES (LOOK EXAMPLE)-->

    <!--CONTAINER THAT CONTAINS ALL CARDS-->
    <div class = "partial-container-form-floating">
	<%
		if (aziende.size() > 0) {
			for (AccountAzienda_Bean az: aziende) {
	%>
        		<!--HERE STARTS THE CARD-->
        		<a class = "company-presentation-card center-block card-spacing-fix" href="DoVisualizzaListinoAzienda?id=<%=az.getEmail()%>">
		            <%=az.getNome()%> <!--CHANGE NAME WITH COMPANY NAME WITH SCRIPTLET-->
        		</a>
    <%
    		}
		} else {
			//No company found in the research
			
			%>
				<div class = "company-presentation-card center-block card-spacing-fix">
					Nessuna azienda trovata
        		</div>
			<%
			
		}
    %> <!--HERE ENDS THE CARD-->

    </div>
    <br>
    <br>
    <br>
	
	<%
		}
	%>
	
    <!-- Script -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <%@include file="Footer.html"%>
</body>
</html>