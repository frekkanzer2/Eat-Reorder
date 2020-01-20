<%@page import="model.ProdottoQuantita"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Carrello"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@page import="model.bean.AccountCliente_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%!AccountCliente_Bean cliente = null;%>
<%!Carrello cart = null; %>
<%!ArrayList<ProdottoQuantita> listOfQtProducts = null; %>
<%!Float totalPrice = 0F; %>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if (utente == null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) response.sendRedirect("Homepage.jsp");
	cliente = (AccountCliente_Bean) utente;
	cart = (Carrello) session.getAttribute("carrello");
	listOfQtProducts = new ArrayList<>(cart.getProdotti());
%>


<!DOCTYPE html>
<html>

<head>
<!--Viewport for toggle-->
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--Title-->
<title>Carrello</title>
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
    <!--External container-->
    <div class="product-card-container partial-container-form-floating center-block custom-border-red border-rounded-small bg-yellow-alt">
        <div class="registration-title">Carrello di <%=cliente.getNome() %></div>
		<div class="registration-description">Acquista comodamente da casa</div>
        <img class="std-img center-block" src="assets/img/LogomarcoIS%20PNG.png">
        
		<!-- IMPLEMENTATION OF THE TOTAL -->        
        
        <%
        	totalPrice = 0F;
        	if (listOfQtProducts.size() > 0) {
        		for(ProdottoQuantita pq: listOfQtProducts) {
        			totalPrice += pq.getProdotto().getPrezzo() * pq.getQta();
        		}
        			%>
        				<div class="report-description"style="margin-bottom:14px;"><b>Totale:</b> <%=totalPrice %></div>
        			<%        		
        	}
        %>
        
        <!-- IMPLEMENTATION OF THE TOTAL ENDED -->
        
        <!--GENERATE HERE ALL PRODUCTS IN THE CART-->

		<%
			if (listOfQtProducts.size() > 0) {
				for (ProdottoQuantita product: listOfQtProducts) {
		%>

	<%
    	if (request.getAttribute("msg_error") != null){
    %>
		<p class="errorText" style="margin-top: 20px;">
			<%=request.getAttribute("msg_error") %>
		</p>
	<% 
		} 
	%>
        <!--HERE STARTS THE CARD OF THE PRODUCT-->
        <!--CARD OF THE PRODUCT-->
        <div class="bg-yellow custom-border-red border-rounded-small padding-medium card-spacing-fix">
            <div class="row">
                <!--First column that contains title and price-->
                <div id="major-padding-column" class="col-sm-8">
                    <p id="product-name" class="cart-title-in-card">
                        <%=product.getProdotto().getNome()%><!--Sample title, use scriptlet also here! Product name goes here-->
                    </p>
                    <p id="product-price" class="cart-description-in-card">
                        <%=product.getProdotto().getPrezzo()%> &euro;<!--Sample price, use scriptlet also here! Product price goes here-->
                    </p>
                    <p id="product-qt" class="cart-description-in-card">
                        Qt. <%=product.getQta()%><!--Sample price, use scriptlet also here! Product price goes here-->
                    </p>
                </div>
                <!--Second column that contains buttons for editing-->
                <div id="cart-buttons-column-edits" class="col-sm-4">
                    <!--Here goes quantity and remove button-->
                    <span class = "center-block" style="width: 100%; margin-bottom: 4px;">
                        <!--FORM FOR THE EDITING OF THE QUANTITY-->
                        <!--There's a form to set the new product quantity in the cart-->
                        <form class="form-inline center-block control-form-qt" method="POST" action="DoModificaQuantita">
                        	<!-- These two following input field are not visible due to send some hidden datas -->
                        	<input name="productId" class="notDisplayed" value="<%=product.getProdotto().getCodice() %>">
                        	<input name="companyMail" class="notDisplayed" value="<%=product.getProdotto().getAzienda().getEmail() %>">
                        	<!-- Visible data here: -->
                        	<button class="cart-standard-button-restyle" type="submit" style="color: white;">Nuova qt.</button>
                            <input type="number" class="custom-border-red border-rounded-medium text-red" name="productQuantity" min="1" max="50" value="<%=product.getQta() %>" style = "width: 40px;">
                        </form>
                    </span>
                    <a href="DoRimuoviDalCarrello?id=<%=product.getProdotto().getCodice()%>" class="cart-standard-button-restyle center-block">Rimuovi</a> <!--BUTTON FOR DELETE-->
                </div>
            </div>
        </div>
        <!--END OF PRODUCT'S CARD-->
		<%
				}
				
		%>
			<a href="./Ordinazione.jsp" class="standard-button-restyle center-block">Crea l'ordine</a> <!--BUTTON FOR ORDER-->
		<%
				
			} else {
				
		%>
			<div class="registration-description" style="margin-bottom: 0px;">Il carrello è vuoto</div>
		<%		
				
			}
		
		%>

    </div>
<%@include file="Footer.html"%>
</body>

</html>