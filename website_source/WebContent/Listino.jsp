<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountAzienda_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente==null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda)) response.sendRedirect("Homepage.jsp");
%>
<!DOCTYPE html>
<html>

<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Listino</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@page import="model.bean.Prodotto_Bean"%>

</head>

<body>
    <!--External container-->
    <div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
        <div class="registration-title">Listino di </div> <!--REFACTORIZZARE CON JSP INSERENDO IL NOME DELL'AZIENDA-->
		<div class="registration-description">Gestisci comodamente i prodotti della tua azienda</div>
        <img class="std-img center-block" src="assets/img/LogomarcoIS%20PNG.png">

		<%!AccountAzienda_Bean azienda = null;%>
		<%
			azienda = (AccountAzienda_Bean) utente;
			
		%>
		<%!Collection<Prodotto_Bean> productList = azienda.getProdotti().values();%>

        <!--HERE STARTS THE CARD OF THE PRODUCT-->
		<%for(Prodotto_Bean prodotto: productList){ %>
        <!--CARD OF THE PRODUCT-->
        <div class="bg-yellow custom-border-red border-rounded-small padding-medium" style="overflow: auto;">
            <div id=""><!--Insert into ID the id of the product via scriptlet--></div>
            <div class="row">
                <!--First column that contains img and price-->
                <div class="col-sm-2">
                    <img id="product-image" class = "custom-border-red border-rounded-medium img-preview-in-card center-block" src = "<%=prodotto.getImmagine().toString()%>"><!--Use scriptlet to add the url of the image-->
                    <p id="product-price" class="price-in-card">
                        <%=prodotto.getPrezzo()%> <!--Sample price, use scriptlet also here!-->
                    </p>
                </div>
                <!--Second column that contains title and description-->
                <div class="col-sm-7">
                    <p id="product-name" class="title-in-card">
                        <%=prodotto.getNome()%><!--Sample title, use scriptlet also here!-->
                    </p>
                    <p id="product-description" class="description-in-card">
                       <%=prodotto.getDescrizione()%> <!--Sample description, use scriptlet also here!-->
                    </p>
                </div>
                <!--Third column that contains buttons for editing-->
                <div id="buttons-column-edits" class="col-sm-3">
                    <!--Here goes buttons to edit and remove-->
                    <!--Edit with scriptlet-->
                    <a href="./DoVaiModificaProdotto?idProdotto=<%=prodotto.getCodice()%>" class="standard-button-restyle center-block">Modifica</a>
                    <a href="./DoEliminaProdotto?idProdotto=<%=prodotto.getCodice()%>" class="standard-button-restyle center-block">Rimuovi</a>
                </div>
            </div>
        </div>
        <%} %>
        <!--END OF PRODUCT'S CARD-->

    </div>

</body>

</html>