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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--Title-->
    <title>Nuovo Prodotto</title>
    <!--Preset imports-->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/Login-Form-Clean.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
    <!--Custom imports-->
    <link rel="stylesheet" href="css/eat-reorder-style.css">
    <%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    <!--DIV that contains the image and the searchbar-->
	<div class="center-block">
		<!--Form for the research-->
		<form class="custom-border-red generic-form-style partial-container-form-floating" method="POST" action="DoInserimentoProdotto"onsubmit="return checkProdotto();">
            <div class="registration-title">Inserisci un nuovo piatto!</div>
            <!--IMAGE ON THE HOME-->
            <img id="image-home" class="img-fluid d-block mx-auto"src="assets/img/LogomarcoIS%20PNG.png">
            <%
		    	if (request.getAttribute("msg_error") != null){
    		%>
				<p class="errorText" style="margin-top: 20px;">
					<%=request.getAttribute("msg_error") %>
				</p>
			<% 
				} 
			%>
            <!--FORM INPUTS-->
            <label class="std-label">Nome del piatto</label>
			<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Almeno 4 caratteri</span></span>
			<input class="custom-border-red border-rounded-small form-control input-style-login"id="nome"type="text"name="nome"placeholder="Nome del piatto"pattern="[a-zA-Z 'àèìòù]{1,45}">
			<label class="std-label">Prezzo del piatto</label>
			<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Numero seguito dalla virgola e centesimi espressi in decimi</span></span>
			<input class="custom-border-red border-rounded-small form-control input-style-login"id="prezzo"type="text"name="costo"placeholder="Prezzo del piatto"pattern="[0-9]+,[0-9]{2}">
            <label class="std-label">URL Immagine</label>
			<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Consigliamo di inserire un'immagine quadrata</span></span>
            <input class="custom-border-red border-rounded-small form-control input-style-login"id="img"type="text"name="img_path"placeholder="URL dell'immagine da caricare" pattern="(?:([A-Za-z]+):)?(\\/{0,3})([0-9.\\-A-Za-z]+)(?::(\\d+))?(?:\\/([^?#]*))?(?:\\?([^#]*))?(?:#(.*))?">
<<<<<<< HEAD
            <label class="std-label">Descrizione</label>
			<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Almeno 10 caratteri</span></span>
            <textarea type="text"class="custom-border-red border-rounded-small form-control input-style-login"id="description"name="descrizione"rows="3"placeholder="Descrizione del piatto" pattern="[a-zA-Z0-9\\. 'àèìòù]{10,250}"></textarea>
=======
            <textarea class="custom-border-red border-rounded-small form-control input-style-login"id="description"name="descrizione"rows="3"placeholder="Descrizione del piatto"></textarea>
>>>>>>> 7fb5f5169fc6eda2b2bf096bebdf844f3a749bef
            <button class="btn form-list-button bg-red border-rounded-small"type="submit">Conferma</button>
		</form>
	</div>
	<!-- SCRIPT -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function checkProdotto() {
			var nome = document.getElementById("nome");
			var prezzo = document.getElementById("prezzo");
			var img = document.getElementById("img");
			var desc=document.getElementById()
			if (nome.value == ""||prezzo.value==""||img.value==""||desc.value == "") {
				alert("ATTENZIONE! Errore nella validazione dei campi!")
				return false;
			}
			return true;
		}
	</script>
</body>

</html>