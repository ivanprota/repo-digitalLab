<%@page import="java.util.Iterator"%>
<%@page import="it.unisa.db.PictureDAO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="it.unisa.servlet.AddItemToCart" %>
<%@ page import="java.text.DecimalFormat" %>

<%
   	String productCode = request.getParameter("productCode");
   	Product product = null;
	Collection<Picture> pictures = null;
   
   	if (productCode != null) {
		int code = Integer.parseInt(productCode);
	    DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	    ProductDAO productDAO = new ProductDAO(ds);
	    PictureDAO pictureDAO = new PictureDAO(ds);
	    try {
	    	product = productDAO.doRetrieveByKey(code);
	    	pictures = pictureDAO.doRetrieveAllByKey(code);
	    } catch (SQLException error) {
	        error.printStackTrace();
	    }
	}
   	
   	Iterator<Picture> it = pictures.iterator();
   	Picture picture1 = (Picture) it.next();
   	Picture picture2 = (Picture) it.next();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title><%= product.getBrand() + " " + product.getModel()%></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/product.css">
    
    <script src="<%= request.getContextPath()%>/scripts/product.js"></script>
    <script src="<%= request.getContextPath()%>/scripts/common.js"></script>
    <!-- [JQUERY] Comparsa del menu quando lo la dimensione della pagina � abbastanza piccola -->
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

    <%@ include file="../header.jsp" %>
    
	<div id="productBodyContainer">
		<div id="productImagesLeftContainer">
			<div id="productListImagesContainer">
				<div class="productImagesContainer">
				<%
					String fileName1 = request.getContextPath() + "/imgs/products/" +picture1.getImageFileName();
				%>
					<a onmouseover="borderItem2(this)" onmouseout="deleteBorderItem(this)">
						<img src="<%= request.getContextPath()%>/imgs/products/<%= picture1.getImageFileName()%>" 
							onmouseover="setImage('<%=fileName1%>')">
					</a>
				</div>
				<div class="productImagesContainer">
				<%
					String fileName2 = request.getContextPath() + "/imgs/products/" +picture2.getImageFileName();
				%>
					<a onmouseover="borderItem2(this)" onmouseout="deleteBorderItem(this)">
						<img src="<%= request.getContextPath()%>/imgs/products/<%= picture2.getImageFileName()%>" 
							onmouseover="setImage('<%=fileName2%>')">
					</a>
				</div>
			</div>
			<div id="productImageContainer">
				<img id="productImage" src="<%= request.getContextPath()%>/imgs/products/<%= picture1.getImageFileName()%>">
			</div>
			<div id="productDetailsContainer">
				<div id="productHeaderContainer">
					<h1>
						<%= product.getBrand()%>
						<%= product.getModel()%>
					</h1>
					<div id="productHeaderParagraphContainer">
						<div id ="productRatingContainer">
							<!-- Inserire valutazione -->
						</div>
						<p>
							<a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=<%= product.getCategory()%>">
								Categoria: <%= product.getCategory()%>
							</a>
						</p>
					</div>
				</div>
				<div id="productPriceContainer">
					<%
					    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
					    String formattedPrice = decimalFormat.format(product.getPrice());
					%>
					<p id="price"><%= formattedPrice %> &euro;</p>
					<p>Tutti i prezzi includono l'IVA.</p>
				</div>
				<div id="productDescriptionContainer">
					<h3>Descrizione</h3>
					<p><%= product.getDescription()%></p>
				</div>
				<div id="productPurchaseContainer">
					<input type="button" value="Aggiungi al carrello" onclick="addToCart('<%= product.getCode() %>')">
					<input type="button" value="Acquista">
					<p>Ricorda che il reso � gratuito!</p>
				</div>
			</div>
			<div id="productReviews">
				<h3>Recensioni dei clienti</h3>
				<!-- Inserire recensioni! -->
			</div>
		</div>
		<hr>
		<div id="similarProducts">
			<p>Prodotti simili</p>
			<!-- Inserire codice -->
		</div>
	</div>

	<script>
	  function addToCart(productCode) {
	    var customer = session.getAttribute("Customer");
	    alert(customer);
	    $.ajax({
	      url: <%=request.getContextPath()%>+"/AddItemToCart",
	      method: 'POST',
	      data: {
	    	  productCode: productCode,
	    	  customer: customer
	    	},
	      success: function(response) {
	        alert('Prodotto aggiunto al carrello con successo!');
	      },
	      error: function(xhr, status, error) {
	        alert('Si � verificato un errore durante l\'aggiunta del prodotto al carrello.');
	      }
	    });
	  }
	</script>
	
    <%@ include file="../footer.jsp" %>

</body>
</html>