<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Assistenza</title>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/support.css">
    <!-- [JQUERY] Comparsa del menu quando lo la dimensione della pagina è abbastanza piccola -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
			$(document).ready(function() {
				$("#phoneButton").click(function() {
					$("#phoneNavContainer").slideToggle();
				})
			})
	</script>
    
</head>
<body>
	<%@ include file="../header.jsp"%>
	
	<div id="supportBody">
		<div id="supportBodyContainer">
			<h1>Richiesta di Assistenza</h1>
			<form action="SupportServlet" method="POST">
			    <label for="orderNumber">Numero Ordine:</label>
			    <input type="text" id="orderNumber" name="orderNumber" required><br><br>
			    
			    <label for="name">Nome:</label>
			    <input type="text" id="name" name="name" required><br><br>
			    
			    <label for="email">Email:</label>
			    <input type="email" id="email" name="email" required><br><br>
			    
			    <label for="message">Messaggio:</label>
			    <textarea id="message" name="message" rows="4" cols="50" required></textarea><br><br>
			    
			    <div id="phoneContainer"> 
			    	<div id="checkboxPhoneContainer">
				    	<input type="checkbox" id="contactByPhone" name="contactByPhone">
				    </div>
				    <div id="labelPhoneContainer">
				    	<label for="contactByPhone">Desidero essere contattato telefonicamente</label><br><br>
				    </div>
			    </div>
			    
			    <label for="phoneNumber">Numero di Telefono:</label>
			    <input type="text" id="phoneNumber" name="phoneNumber"><br><br>
			    
			    <input type="submit" value="Invia Richiesta">
			</form>
		</div>
	</div>
    
    
    <%@ include file="../footer.jsp"%>
</body>
</html>