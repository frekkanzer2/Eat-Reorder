<%@page import="model.bean.AccountAzienda_Bean"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null) {
	} else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda))
		response.sendRedirect("HomepageAzienda.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Fattorino))
		response.sendRedirect("HomepageFattorino.jsp");
	else if (utente.getTipo().equals(AccountUtenteRegistrato_Bean.Moderatore))
		response.sendRedirect("HomepageModeratore.jsp");
		
	List<AccountAzienda_Bean> aziende = (List<AccountAzienda_Bean>) request.getAttribute("aziende");%>
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
<%%>

    <!--THERE ARE SOME CARDS ON THE DIV CONTAINER-->
    <!--FOR EACH COMPANY FOUND WITH THE SEARCH ENGINE, YOU HAVE TO CREATE A CARD WITH TAG "A" AND SOME CLASSES (LOOK EXAMPLE)-->

    <!--CONTAINER THAT CONTAINS ALL CARDS-->
    <div class = "partial-container-form-floating">
	<%for(AccountAzienda_Bean az: aziende){ %>
        <!--HERE STARTS THE CARD-->
        <a class = "company-presentation-card center-block card-spacing-fix" href="DoVisualizzaListinoAzienda?id=<%=az.getEmail()%>">
            <%=az.getNome()%> <!--CHANGE NAME WITH COMPANY NAME WITH SCRIPTLET-->
        </a>
       <%} %> <!--HERE ENDS THE CARD-->

    </div>
    <br>
    <br>
    <br>

    <!-- Script -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <%@include file="Footer.html"%>
</body>
</html>