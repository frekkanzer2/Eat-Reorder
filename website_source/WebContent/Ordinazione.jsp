<%@page import="java.time.LocalDate"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.LocalTime"%>
<%@page import="model.bean.AccountAzienda_Bean"%>
<%@page import="model.Carrello"%>
<%@page import="model.bean.AccountUtenteRegistrato_Bean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%!AccountUtenteRegistrato_Bean utente = null;%>
<%!Carrello cart = null;%>
<%!AccountAzienda_Bean company = null;%>
<%!LocalTime actualTime = null; %>
<%!DayOfWeek actualDay = null; %>
<%!DayOfWeek precDay = null; %>
<%!LocalDate hiddenDate = null; %>
<%
	utente = (AccountUtenteRegistrato_Bean) session.getAttribute("utente");
	if(utente==null || !utente.getTipo().equals(AccountUtenteRegistrato_Bean.Cliente)) response.sendRedirect("Homepage.jsp");
	else {
		cart = (Carrello) session.getAttribute("carrello");
		company = (AccountAzienda_Bean) cart.getCurrentAzienda();
		actualTime = LocalTime.now();
		hiddenDate = LocalDate.now();
		actualDay = hiddenDate.getDayOfWeek();
		precDay = actualDay.minus(1);
		System.out.println("actualTime: " + actualTime.toString());
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Ordinazione</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/Navigation-with-Button.css">
<link rel="stylesheet" href="assets/css/styles.css">
<!--Custom imports-->
<link rel="stylesheet" href="css/eat-reorder-style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container-form-floating">
		<div id="container-report"class="custom-border-red bg-yellow center-block border-rounded-large">
			<div class="report-title" style="margin-bottom: 10px;">Ci siamo quasi...</div>
			<div class="report-description"style="margin-bottom:14px;">Compila i campi per completare l'ordine!</div>
			<!--ORDER FORM-->
			<form class="form-horizontal center-block" action="DoOrdinazione" method="POST"onsubmit="return checkOrdine();">
                    <label class="std-label">Indirizzo di destinazione</label>
                    <span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Es. Roma, 26</span></span>
                    
                    <!--INPUT DELL'INDIRIZZO-->
<<<<<<< HEAD
					<input type="text"name="address"class="form-control custom-border-red"placeholder="Indirizzo">
					 	<label class="std-label">Telefono cliente</label>
						<span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Esattamente 10 numeri</span></span>
                    
	                    <!--INPUT del Telefono-->
						<input type="text"name="telefono"class="form-control custom-border-red"placeholder="Telefono">
	                    <label class="std-label">Note per l'azienda</label>
	                    <span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> E' possibile lasciarlo anche vuoto</span></span>
	                    <!--INPUT DELLE NOTE PER L'AZIENDA-->
	                    <textarea name="notes"class="form-control custom-border-red"rows="5"placeholder="Note per l'azienda"></textarea>
	                    <label class="std-label">Carta di credito</label>
	                    <span class="_0av1W9 ps6S3V BpPaLl ccsMLn PbG-b5 yuD_c0 _0qBygh _92Azvw"><svg class="dx-icon lny_1rAju" height="1em" width="1em" focusable="false" fill="currentColor" viewBox="0 0 24 24" aria-hidden="true"><path d="M12 2a10 10 0 1 0 10 10A10 10 0 0 0 12 2zm.8 15h-1.6v-7h1.6zm.1-8.2h-1.8V7h1.8z"></path></svg><span class="suggerimenti" class="VcCaWc O82Ha7 UnzkRv P6b3OO febL1w s9pfnb _0Pob50" style="text-align: right;"> Esattamente 16 numeri</span></span>
	                    <!--INPUT PER LA CARTA DI CREDITO-->
	                    <input type="text"name="credit-card"class="form-control custom-border-red"placeholder="XXXX-XXXX-XXXX-XXXX">
	                    <!--PULSANTE DI SUBMIT-->
=======
					<input type="text"id="indirizzo"name="address"class="form-control custom-border-red"placeholder="Indirizzo"pattern="[a-zA-Z 'אטלעש]{1,80}, [0-9]{1,3}">
					 <label class="std-label">Telefono cliente</label>
                    
                    <!--INPUT del Telefono-->
					<input type="text"id="telefono"name="telefono"class="form-control custom-border-red"placeholder="Telefono"pattern="[0-9]{9,10}">
                    <label class="std-label">Note per l'azienda</label>
                    <!--INPUT DELLE NOTE PER L'AZIENDA-->
                    <textarea name="notes"class="form-control custom-border-red"rows="5"placeholder="Note per l'azienda"></textarea>
                    <label class="std-label">Carta di credito</label>
                    <!--INPUT PER LA CARTA DI CREDITO-->
                    <input id="carta"type="text"name="credit-card"class="form-control custom-border-red"placeholder="XXXX-XXXX-XXXX-XXXX"pattern="[0-9]{16}">
                    <!--PULSANTE DI SUBMIT-->
>>>>>>> 7fb5f5169fc6eda2b2bf096bebdf844f3a749bef
                    
    <%
    	if (request.getAttribute("msg_error") != null){
    %>
		<p class="errorText" style="margin-top: 20px;">
			<%=request.getAttribute("msg_error") %>
		</p>
	<% 
		} 
	%>
                    
                    <%
                    	System.out.println("DEBUG > STARTING TESTS");
	                    System.out.println("DEBUG > Start Time: " + company.getOrarioDiApertura() + " | End Time: " + company.getOrarioDiChiusura());
                    	/*
                    		READ HERE
                    		BEFORE MIDNIGHT CHECK: ACTUALTIME IS GREATHER THAN START TIME AND IS GREATHER THAN END TIME
	                    		actualTime.isAfter(company.getOrarioDiApertura()) && actualTime.isAfter(company.getOrarioDiChiusura())
                    		AFTER MIDNIGHT CHECK: ACTUALTIME IS LOWER THAN START TIME AND IS LOWER THAN END TIME
	                    		actualTime.isBefore(company.getOrarioDiApertura()) && actualTime.isBefore(company.getOrarioDiChiusura())
                    	*/
                    	//Checking if endTime is smaller than startTime
                    	//Ex: sT 20.00 -> eT 8.00 (next day)
                    	if (company.getOrarioDiApertura().isAfter(company.getOrarioDiChiusura())){
                    		System.out.println("DEBUG > START TIME AFTER END TIME");
                    		if ((actualTime.isAfter(company.getOrarioDiApertura()) && actualTime.isAfter(company.getOrarioDiChiusura()))|| //Checking actualTime before midnight
                    				(actualTime.isBefore(company.getOrarioDiApertura()) && actualTime.isBefore(company.getOrarioDiChiusura()))){ //Checking actualTime after midnight
                    			//In that case, actualTime is before 00.00 OR after 00.00
                    			System.out.println("CHECK IS RIGHT");
                    			if (actualTime.isBefore(company.getOrarioDiApertura()) && actualTime.isBefore(company.getOrarioDiChiusura())){
                    				//AFTER MIDNIGHT CASE
                    				System.out.println("DEBUG > AFTER MIDNIGHT");
                    				if (company.getGiorniDiApertura().contains(precDay)) {
                        				%>
                            				<button type="submit"class="standard-button center-block border-rounded-medium custom-border-red bg-red text-yellow"style="margin-top:20px; margin-bottom: 10px;">Ordina!</button>
                    					<%
                        			}
                    			} else if (actualTime.isAfter(company.getOrarioDiApertura()) && actualTime.isAfter(company.getOrarioDiChiusura())) {
                    				//BEFORE MIDNIGHT CASE
                    				System.out.println("DEBUG > BEFORE MIDNIGHT");
                    				if (company.getGiorniDiApertura().contains(actualDay)) {
                        				%>
                            				<button type="submit"class="standard-button center-block border-rounded-medium custom-border-red bg-red text-yellow"style="margin-top:20px; margin-bottom: 10px;">Ordina!</button>
                    					<%
                        			}	
                    			}
                    		}
                    	} else if (company.getOrarioDiApertura().compareTo(company.getOrarioDiChiusura()) == 0){
                    		//company is always open in the day
                        	%>
                    			<button type="submit"class="standard-button center-block border-rounded-medium custom-border-red bg-red text-yellow"style="margin-top:20px; margin-bottom: 10px;">Ordina!</button>
            				<%
                    	} else if (actualTime.isAfter(company.getOrarioDiApertura()) &&
                    			actualTime.isBefore(company.getOrarioDiChiusura()) &&
                    			company.getGiorniDiApertura().contains(actualDay)) {
                    		//STANDARD CHECK
                    		%>
                    			<button type="submit"class="standard-button center-block border-rounded-medium custom-border-red bg-red text-yellow"style="margin-top:20px; margin-bottom: 10px;">Ordina!</button>
            				<%
                    	}
            		%>
            </form>
            <%
          		//Checking if endTime is smaller than startTime
        		//Ex: sT 20.00 -> eT 8.00 (next day)
        		if (company.getOrarioDiApertura().isAfter(company.getOrarioDiChiusura())){
        			if ((actualTime.isAfter(company.getOrarioDiApertura()) && actualTime.isAfter(company.getOrarioDiChiusura()))|| //Checking actualTime before midnight
        					(actualTime.isBefore(company.getOrarioDiApertura()) && actualTime.isBefore(company.getOrarioDiChiusura()))){ //Checking actualTime after midnight
	        			//In that case, actualTime is before 00.00 OR after 00.00
        				if (actualTime.isAfter(company.getOrarioDiApertura()) && actualTime.isAfter(company.getOrarioDiChiusura())){
        					//BEFORE MIDNIGHT CASE
        	    			if (company.getGiorniDiApertura().contains(actualDay)) {
                				%>
        							<div class="report-description">Controlla la tua mail!<br />Riceverai un resoconto dell'ordine via mail</div>
                   				<%
        	        		} else {
        	        			%>
                    				<div class="report-description" style="margin-top: 22px;">
                    					<b>
                   							Non puoi effettuare l'ordine<br />
                   							L'azienda, attualmente, &egrave; chiusa<br />
                   							Consulta orari e giorni di apertura
                   						</b>
                   					</div>
                   				<%
        	        		}
        	    		} else if (actualTime.isBefore(company.getOrarioDiApertura()) && actualTime.isBefore(company.getOrarioDiChiusura())){
        	    			//AFTER MIDNIGHT CASE
        	    			if (company.getGiorniDiApertura().contains(precDay)) {
                				%>
        							<div class="report-description">Controlla la tua mail!<br />Riceverai un resoconto dell'ordine via mail</div>
                   				<%
        	        		} else {
        	        			%>
                    				<div class="report-description" style="margin-top: 22px;">
                	    				<b>
	                   						Non puoi effettuare l'ordine<br />
    	               						L'azienda, attualmente, &egrave; chiusa<br />
        	           						Consulta orari e giorni di apertura
            	       					</b>
                   					</div>
                   				<%
        	        		}
        	    		}
	        		}
	        	} else if (company.getOrarioDiApertura().compareTo(company.getOrarioDiChiusura()) == 0){
    	    		//company is always open in the day
    	        	%>
        				<div class="report-description">Controlla la tua mail!<br />Riceverai un resoconto dell'ordine via mail</div>
                   	<%
    	    	} else if (actualTime.isAfter(company.getOrarioDiApertura()) &&
    	    			actualTime.isBefore(company.getOrarioDiChiusura()) &&
            			company.getGiorniDiApertura().contains(actualDay)) {
            		//STANDARD CHECK
            		%>
            			<div class="report-description">Controlla la tua mail!<br />Riceverai un resoconto dell'ordine via mail</div>
           			<%
            	} else {
            		%>
            			<div class="report-description" style="margin-top: 22px;">
            				<b>
           						Non puoi effettuare l'ordine<br />
           						L'azienda, attualmente, &egrave; chiusa<br />
           						Consulta orari e giorni di apertura
           					</b>
           				</div>
           			<%
            	}
            %>
		</div>
	</div>
	<script type="text/javascript">
		function checkOrdine() {
			var indirizzo = document.getElementById("indirizzo");
			var telefono = document.getElementById("telefono");
			var carta = document.getElementById("carta");
			if (indirizzo.value == "" || telefono.value == "" || carta.value == "") {
				alert("ATTENZIONE! Errore nella validazione dei campi!")
				return false;
			}
			return true;
		}
	</script>
</body>
</html>