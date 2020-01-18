<%@page import="model.bean.Prodotto_Bean"%>
<%@page import="model.bean.AccountAzienda_Bean"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%AccountAzienda_Bean azienda = (AccountAzienda_Bean)request.getAttribute("azienda");%>

<!DOCTYPE html>
<html>
    <head>
        <!--Viewport for toggle-->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--Title-->
        <title>Listino</title>
        <!--Preset imports-->
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
        <link rel="stylesheet" href="assets/css/styles.css">
        <!--Custom imports-->
        <link rel="stylesheet" href="css/eat-reorder-style.css">
    </head>
    <body>
    <jsp:include page="header.jsp"></jsp:include>
        <!--Here goes the header-->
        <!--Info container-->
	    <div class="container-form-floating">
            
            <!--PER COMPILARE, È NECESSARIO AVER SCELTO UN'AZIENDA DALLA SELEZIONE NELLA PAGINA Ricerca-->
            <!--UTILIZZARE SCRIPTLET-->
            
            <div class="registration-title"><%=azienda.getNome()%><!--INSERT COMPANY NAME HERE--></div>
            <div class="registration-description" style="margin-bottom: 12px;">Listino dei prodotti<!--DO NOT CHANGE HERE--></div>
            
            <!--HERE STARTS THE LIST OF ALL COMPANY PRODUCTS-->
            <!--PRODUCTS' CONTAINER-->
            <div class="product-card-container center-block custom-border-red border-rounded-small bg-yellow-alt">
    
                <!--APPEND HERE ALL PRODUCT CARDS-->
				<%for(Prodotto_Bean prod: azienda.getProdotti().values()){ %>
                <!-- HERE STARTS THE CARD OF THE PRODUCT -->
                <div class="bg-yellow custom-border-red border-rounded-small padding-medium card-spacing-fix">
                	<a href="DoVisualizzaProdotto?prod=<%=prod.getCodice()%>&azienda=<%=azienda.getEmail()%>"> <!--There is the A tag to make the div clickable-->
                        <div class="row">
                            <!--First column that contains img-->
                            <div class="col-sm-3">
                                <img id="product-image" class = "custom-border-red border-rounded-medium img-preview-in-card center-block" src = "<%=prod.getImmagine().toString()%>"><!--Use scriptlet to add the url of the image-->
                            </div>
                            <!--Second column that contains title and description-->
                            <div class="col-sm-9">
                                <p id="product-name" class="title-in-card">
                                    <%=prod.getNome()%><!--Sample title, use scriptlet also here!-->
                                </p>
                                <p id="product-price" class="description-in-card">
                                    <%=prod.getPrezzo()%>&euro; <!--Sample PRICE, use scriptlet also here!-->
                                </p>
                            </div>
                        </div>
              		  </a>
                </div>
                <%} %>
                <!--END OF PRODUCT'S CARD-->
    
            </div>
			
	    </div>
	    <!-- Script -->
	    <script src="assets/js/jquery.min.js"></script>
	    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
        