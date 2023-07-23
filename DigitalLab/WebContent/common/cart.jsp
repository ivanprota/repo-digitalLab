<%@page import="it.unisa.model.ShoppingCart"%>
<%@page import="it.unisa.db.PictureDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %>
<%@ page import="it.unisa.db.ContainsDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.model.Contains" %>
<%@ page import="it.unisa.model.ShoppingCart" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Customer" %>
<%@ page import="java.text.DecimalFormat" %>

<% 
	Customer customer = (Customer) session.getAttribute("customer");
	DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
%>
 

<!DOCTYPE html>
<html lang="IT">
<head>
    <meta charset="UTF-8">
    <title>Il mio carrello DigitalLab.it</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cart.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/scripts/common.js"></script>
    <script src="<%= request.getContextPath()%>/scripts/cart.js"></script>
</head>
	<body>
	
	<%@ include file="../header.jsp" %>
	
	<div id="cartBodyContainer">
	    <h1>Carrello</h1>
	    
	    <div id="cartItems">
		<%
			if (customer == null)
			{
		%>
			<p>Il tuo carrello è vuoto.</p>
		<%
			}
			else
			{
			    Collection<Contains> containsItems;
			    ContainsDAO containsDAO = new ContainsDAO(dataSource);
			    try {
			        containsItems = containsDAO.doRetrieveAllByKey(customer.getUsername());
			    } catch (SQLException e) {
			        e.printStackTrace();
			        containsItems = new ArrayList<>();
			    }
			    
			    Collection<Product> cartItems = new ArrayList<>();
			    ProductDAO productDAO = new ProductDAO(dataSource);
				
			    Map<Integer, Integer> map = new HashMap<>();
			    for (Contains contains : containsItems) {
			        int productCode = contains.getProduct().getCode();
			        Product product = productDAO.doRetrieveByKey(productCode);
			        cartItems.add(product);
			        
			        map.put(productCode, contains.getQuantity());
			    }
		
		            if (cartItems.isEmpty()) {
	        %>
	            <p>Il tuo carrello è vuoto.</p>
	        <% 
	            	} else {
	                	for (Product product : cartItems) {
	                		PictureDAO pictureDAO = new PictureDAO(dataSource);
	                		Picture picture = pictureDAO.doRetrieveAllByKey(product.getCode()).iterator().next();
	                		
	                		int quantity = map.get(product.getCode());
	        %>
	                    <div class="cartItem">
		                	<div class="cartItemImage">
		                    	<img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>" alt="PRODUCT IMAGE">
		                    </div>
		                    <div class="cartItemDetails">
		                        <%
							    	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
							    	String formattedPrice = decimalFormat.format(product.getPrice());
								%>
								
		                            <h3>
		                            	<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=<%= product.getCode()%>">
		                            	<%=product.getBrand() + " " + product.getModel()%>
		                        		</a>
		                        	</h3>
		                            <p>
		                            	Prezzo: <%= formattedPrice %> &euro; Quantità: <input type="number" min="1" id="inputQuantityProduct" value="<%=quantity%>" onchange="setQuantity(this, '<%=product.getCode()%>')" onkeypress="return /[1-9]/i.test(event.key)" >
		                            </p>
		                            <a href="<%= request.getContextPath()%>/Cart?action=delete&productCode=<%= product.getCode()%>">Elimina</a>
		                    </div>
	                        
	                    </div>
	        <%
	                	}
	                	
	                	// Da passare a checkout.jsp
	                	session.setAttribute("cartItems", cartItems);
	                	session.setAttribute("mapProductsQuantity", map);
	            	}
	        %>
	    </div>
	    	
	    <div id="cartSummary">
		    <% double total = 0.00;
		
		    for (Product product : cartItems) {
		        int quantity = map.get(product.getCode());
		        total += product.getPrice() * quantity;
		    } 
	    	DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	    	String formattedPrice = decimalFormat.format(total);
		    %>
	        <p id="totalParagraph">Totale: <%= formattedPrice %> &euro;</p>
	        
	        <div id="cartActions">
	        	<%if(!cartItems.isEmpty()) {%>
		            <a href="<%=request.getContextPath()%>/LoadCheckOutCustomerData"><button>Procedi al pagamento</button></a>
		            <a href="<%= request.getContextPath()%>/EmptyCart"><button>Svuota carrello</button></a>
	            <%}%>
	        </div>
	    </div>
	    <% 
			}
	    %>
	</div>
	
	<%@ include file="../footer.jsp" %>

</body>
</html>