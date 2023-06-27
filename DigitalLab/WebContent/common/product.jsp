<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.model.Picture" %>

<%
   String productCode = request.getParameter("productCode");
   Product product = null;

   if (productCode != null) {
       int code = Integer.parseInt(productCode);
       DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
       ProductDAO productDAO = new ProductDAO(ds);
       try {
           product = productDAO.doRetrieveByKey(code);
       } catch (SQLException error) {
           error.printStackTrace();
       }
   }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title><%= product.getBrand() + " " + product.getModel()%></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/product.css">
    <!-- [JQUERY] Comparsa del menu quando lo la dimensione della pagina è abbastanza piccola -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
			$(document).ready(function() {
				$("#phoneButton").click(function() {
					$("#phoneNavContainer").slideToggle();
				})
			})
	</script>
	
	<!-- Script per aggiungere prodotti al carrello -->
	<script>
	  $(document).ready(function() {
	    $("#addToCartButton").click(function() {
	      var productCode = $(this).data("product-code");
	      addToCart(productCode);
	    });
	  });
	  function addToCart(productCode) {
	    $.ajax({
	      url: "<%= request.getContextPath()%>/AddItemToCart",
	      method: "POST",
	      data: { productCode: productCode },
	      success: function(response) {
	        alert("Prodotto aggiunto al carrello!");
	      },
	      error: function(xhr, status, error) {
	        console.error("Errore durante l'aggiunta al carrello:", error);
	      }
	    });
	  }
</script>
	
	
</head>
<body>

    <%@ include file="../header.jsp" %>
    
    <div id="productBody">
    <% if (product != null) { %>
    	<!-- DESCRIZIONE -->
		<div id="productInfo">
				<h1><%= product.getBrand() + " " + product.getModel()%></h1>
			  	<h2><%= product.getPrice() + " $"%></h2>
			 	<p class="corpo">Descrizione: <%= product.getDescription() %></p>
			  	<p id="codice">Codice: <%= product.getCode() %></p>
			  	<p class="corpo">Quantità: <%= product.getQuantity() %></p>
			  	<div id="categoria">
				  	<p class="corpo">Categoria: </p>
				  	<a id="category" href=""><%= product.getCategory() %></a>
			  	</div>
			  	<button id="addToCartButton" data-product-code="<%= product.getCode() %>">Aggiungi al carrello</button>

		</div>
		<!-- IMMAGINI -->
	    <div id="productImages">
	            <img src="<%= request.getContextPath()%>/GetPictureServlet?productCode=<%= product.getCode() %>">
	    </div>
	    <!-- RECENSIONI -->
	    <div id="productReviews">
		  <h3>Recensioni dei clienti</h3>
		  <!-- Inserire codice per visualizzare le recensioni -->
		</div>
	    
	    <% } %>
	</div>
	
    <%@ include file="../footer.jsp" %>

</body>
</html>