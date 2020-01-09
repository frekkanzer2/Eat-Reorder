<%@page import="model.dao.GestoreOrdineDAOImpl"%>
<%@page import="model.bean.Ordine_Bean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@page import="model.bean.AccountAzienda_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!AccountAzienda_Bean azienda = null;%>
<%!ArrayList<Ordine_Bean> listOfOrders = null;%>
<%!GestoreOrdineDAOImpl orderManager = null;%>
<%!Integer counter = null; %>
<%
	AccountUtenteRegistrato_Bean user = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (user == null || !user.getTipo().equals(AccountUtenteRegistrato_Bean.Azienda))
		response.sendRedirect("Homepage.jsp");
	else {
		azienda = (AccountAzienda_Bean) user;
		orderManager = new GestoreOrdineDAOImpl();
		listOfOrders = new ArrayList<>(orderManager.dammiOrdiniPreparazione(azienda));
		counter = 0;
	}
%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="header.jsp"></jsp:include>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Ordini</title>
<!--Preset imports-->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
<link rel="stylesheet" href="assets/css/styles.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>

<body>
    <!--External container-->
    <div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
        <div class="registration-title">Ordini di </div> <!--REFACTORIZZARE CON JSP INSERENDO IL NOME DELL'AZIENDA-->
		<div class="registration-description">Visualizza le ordinazioni per la tua azienda</div>
        <img class="std-img center-block" src="assets/img/LogomarcoIS%20PNG.png">

		<%
		if (azienda != null) {
			if (listOfOrders.size() > 0) {
				for (Ordine_Bean order: listOfOrders) {
					if (order.getStato().equalsIgnoreCase(Ordine_Bean.IN_PREPARAZIONE)) {
						counter++; //counter conta quanti ordini sono in preparazione
		%>

        <!--HERE STARTS THE CARD OF THE ORDER-->

        <!--CARD OF THE ORDER-->
        <a href="./DoVaiVisualizzaOrdine?idOrdine=<%=order.getCodiceID()%>"> <!--THIS A TAG WILL REDIRECT YOU TO THE ORDER DETAILS-->
        <div class="bg-yellow custom-border-red border-rounded-small padding-medium" style="overflow: auto;">
            <div class="row">
                <!--First column contains ID and price-->
                <div class="col-sm-7">
                    <p id="order-id" class="title-in-card">
                        Order <%=order.getCodiceID() %> <!--Sample ID, use scriptlet also here!-->
                    </p>
                    <p id="order-totalprice" class="description-in-card">
                        <%=order.getPrezzoTotal() %> &euro; <!--Sample PRICE, use scriptlet also here!-->
                    </p>
                </div>
                <!--Second column that contains notes for status of the order-->
                <div class="col-sm-5">
                    <p id="str-do-not-edit" class="description-in-card-text">
                        Stato dell'ordine <!--DO NOT CHANGE WITH SCRIPTLET-->
                    </p>
                    <p id="order-status" class="description-in-card-text">
                        <%=order.getStato() %> <!--Sample STATUS, use scriptlet also here!-->
                    </p>
                </div>
            </div>
        </div>
        </a>
        <!--END OF ORDER'S CARD-->
        
        <%
					}
				}
				if (counter == 0){
					%>
						<div class="registration-description" style="margin-bottom: 0px;">Non ci sono ordini da preparare</div>
					<%
				}
			} else {
				%>
					<div class="registration-description" style="margin-bottom: 0px;">Non ci sono ordini da preparare</div>
				<%
			}
		}
        %>

    </div>

</body>

</html>