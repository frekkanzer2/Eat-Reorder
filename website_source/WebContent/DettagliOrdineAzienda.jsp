<%@page import="model.ProdottoQuantita"%>
<%@page import="model.bean.Prodotto_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Ordine_Bean"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	Boolean canContinue = true;
	Ordine_Bean order = null;
	AccountUtenteRegistrato_Bean user = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (user == null || !user.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda)) {
		canContinue = false;
		response.sendRedirect("Homepage.jsp");
	} else {
		order = (Ordine_Bean) request.getAttribute("ordineSelezionato");
	}
	if (order == null && canContinue) {
		canContinue = false;
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
<title>Dettagli ordine</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<% 
		if (order != null && canContinue) {
	%>
    <!--External container-->
    <div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
        <div id="order-id" class="registration-title">Ordine <%=order.getCodiceID() %> <!--INSERIRE QUI NUMERO ORDINE--> </div>
        <div id="order-totalprice" class="registration-description">Prezzo dell'ordine: <%=order.getPrezzoTotal() %><!--INSERIRE QUI IL PREZZO TOTALE DELL'ORDINE--></div>
        <div id="order-status" class="registration-description">Stato dell'ordine: <%=order.getStato() %> <!--INSERIRE QUI LO STATO DELL'ORDINE--></div>
        <img class="std-img center-block" src="assets/img/LogomarcoIS%20PNG.png">

		<%!ArrayList<ProdottoQuantita> listOfQtProducts = null; %>
		<%
			listOfQtProducts = new ArrayList<>(order.getProdottiOrdinati());
			for (ProdottoQuantita qprod: listOfQtProducts) {
		%>

        <!--HERE STARTS THE CARD OF THE PRODUCT-->

        <!--CARD OF THE PRODUCT-->
        <div class="bg-yellow custom-border-red border-rounded-small padding-medium card-spacing-fix">
            <div class="row">
                <!--First column that contains img and price-->
                <div class="col-sm-2">
                    <img id="product-image" class = "custom-border-red border-rounded-medium img-preview-in-card center-block" src = "<%=qprod.getProdotto().getImmagine() %>"><!--Use scriptlet to add the url of the image-->
                    <p id="product-price" class="price-in-card">
                        <%=qprod.getProdotto().getPrezzo() %> &euro; <!--Sample price, use scriptlet also here!-->
                    </p>
                </div>
                <!--Second column that contains title and description-->
                <div class="col-sm-7">
                    <p id="product-name" class="title-in-card">
                        <%=qprod.getProdotto().getNome() %><!--Sample title, use scriptlet also here!-->
                    </p>
                    <p id="product-description" class="description-in-card">
                       	<%=qprod.getProdotto().getDescrizione() %> <!--Sample description, use scriptlet also here!-->
                    </p>
                </div>
                <!--Third column that contains quantity-->
                <div id="buttons-column-edits" class="col-sm-3">
                    <p id="order-status" class="description-in-card-text">
                        Qt. <%=qprod.getQta() %> <!--Sample QUANTITY, use scriptlet also here!-->
                    </p>
                </div>
            </div>
        </div>
        <!--END OF PRODUCT'S CARD-->
        <%
			}
        %>

    </div>
    
    <% 
		}
	%>
<%@include file="Footer.html"%>
</body>

</html>