<%@page import="it.unisa.db.ReviewDAO"%>
<%@page import="it.unisa.model.Customer"%>
<%@page import="java.util.Iterator"%>
<%@page import="it.unisa.db.PictureDAO"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Review" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="it.unisa.model.Customer"%>
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
   	Picture picture1 = null;
   	Picture picture2 = null;
   	if (pictures.size() != 0)
   	{
   		picture1 = (Picture) it.next();
   		picture2 = (Picture) it.next();
   	}
   	else
   	{
   		picture1 = new Picture();
   		picture1.setImageFileName("");
   		
   		picture2 = new Picture();
   		picture2.setImageFileName("");
   	}
   	
   	Customer customer = (Customer) session.getAttribute("customer");
%>

<!DOCTYPE html>
<html lang="IT">
<head>
    <meta charset="ISO-8859-1">
    <title><%= product.getBrand() + " " + product.getModel()%></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/product.css">
    
    <script src="<%= request.getContextPath()%>/scripts/product.js"></script>
    <script src="<%= request.getContextPath()%>/scripts/common.js"></script>
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
							onmouseover="setImage('<%=fileName1%>')" alt="PRODUCT IMAGE">
					</a>
				</div>
				<div class="productImagesContainer">
				<%
					String fileName2 = request.getContextPath() + "/imgs/products/" +picture2.getImageFileName();
				%>
					<a onmouseover="borderItem2(this)" onmouseout="deleteBorderItem(this)">
						<img src="<%= request.getContextPath()%>/imgs/products/<%= picture2.getImageFileName()%>" 
							onmouseover="setImage('<%=fileName2%>')" alt="PRODUCT IMAGE">
					</a>
				</div>
			</div>
			<div id="productImageContainer">
				<img id="productImage" src="<%= request.getContextPath()%>/imgs/products/<%= picture1.getImageFileName()%>" alt="PRODUCT IMAGE">
			</div>
			<div id="productDetailsContainer">
				<div id="productHeaderContainer">
					<h1>
						<%= product.getBrand()%>
						<%= product.getModel()%>
					</h1>
					<div id="productHeaderParagraphContainer">
						<div id ="productRatingContainer">
							<%
								DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
								ReviewDAO reviewDAO = new ReviewDAO(ds);
								String reviewImageFileName = "";
								Collection<Review> reviews = null;
								try
								{
									reviews = reviewDAO.doRetrieveAllByKey(product.getCode());
								}
								catch (SQLException e)
								{
									System.out.println(e);
								}
								
								Iterator<Review> it2 = reviews.iterator();
								int stars = 0;
								while (it2.hasNext())
								{
									Review review = (Review) it2.next();
									stars += review.getAssessment();
								}
								
								if (reviews.size() == 0)
									stars = 0;
								else stars /= reviews.size();
								
								switch(stars)
								{
									case 0: reviewImageFileName += "0stars.png"; break;
									case 1: reviewImageFileName += "1star.png"; break;
									case 2: reviewImageFileName += "2stars.png"; break;
									case 3: reviewImageFileName += "3stars.png"; break;
									case 4: reviewImageFileName += "4stars.png"; break;
									case 5: reviewImageFileName += "5stars.png"; break;
								}
							%>
							<img src="<%= request.getContextPath()%>/imgs/<%= reviewImageFileName%>" alt="REVIEW IMAGE">
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
					<%
						if (product.getQuantity() == 0)
						{
					%>
							<p>Prodotto momentaneamente non disponibile</p>
					<%		
						}
						else if (customer == null)
						{
					%>
							<p>Autenticati per effettuare l'acquisto!</p>
					<%		
						}
						else
						{
					%>
					<input type="button" value="Aggiungi al carrello" onclick="addToCart('<%= product.getCode() %>', '<%=customer.getUsername()%>')">
					<p>Ricorda che il reso è gratuito!</p>
					<% 
						}
					%>
				</div>
			</div>
			<div id="productReviews">
				<h3>Recensioni dei clienti</h3>
					<%
						Iterator<Review> it3 = reviews.iterator();
						while (it3.hasNext())
						{
							Review review = (Review) it3.next();
					%>
						<p>
							<%= review.getDescription()%>
						</p>
					<%
						}
					%>
			</div>
		</div>
	</div>
	

	<script>
	  function addToCart(productCode, customerUsername) {
	    $.ajax({
	      url: "<%=request.getContextPath()%>/addItemToCart",
	      method: 'POST',
	      data: {
	    	  productCode: productCode,
	    	  customerUsername: customerUsername
	    	},
	      success: function(response) {
	        alert('Prodotto aggiunto al carrello con successo!');
	      },
	      error: function(xhr, status, error) {
	        alert('Si è verificato un errore durante l\'aggiunta del prodotto al carrello.');
	      }
	    });
	  }
	</script>
	
    <%@ include file="../footer.jsp" %>

</body>
</html>