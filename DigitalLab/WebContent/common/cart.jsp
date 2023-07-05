<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ListIterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="it.unisa.db.ContainsDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="it.unisa.model.Contains" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="it.unisa.model.Product" %>

<% String username = (String) session.getAttribute("username"); %>
<% DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Il mio carrello DigitalLab.it</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/cart.css">
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

<div id="cartBodyContainer">
    <h1>Carrello</h1>
    
    <div id="cartItems">
	<%
	    Collection<Contains> containsItems;
	    ContainsDAO containsDAO = new ContainsDAO(dataSource);
	    try {
	        containsItems = containsDAO.doRetrieveAll(username);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        containsItems = new ArrayList<>();
	    }
	
	    Collection<Product> cartItems = new ArrayList<>();
	    ProductDAO productDAO = new ProductDAO(dataSource);
	
	    for (Contains contains : containsItems) {
	        int productCode = contains.getProduct().getCode();
	        Product product = productDAO.doRetrieveByKey(productCode);
	        cartItems.add(product);
	    }

            if (cartItems.isEmpty()) {
        %>
            <p>Il tuo carrello è vuoto.</p>
        <% 
            } else {
                for (Product product : cartItems) {
        %>
                    <div class="cartItem">
                        <div class="cartItemImage">
                            <img src="">			<!-- Immagine!!!!!!! -->
                        </div>
                        <div class="cartItemDetails">
                            <h3><%=product.getBrand() + " " + product.getModel()%></h3>
                            <p>Prezzo: <%= product.getPrice() %> €</p>
                            <!-- Altre informazioni sui prodotti -->
                            
                        </div>
                    </div>
        <%
                }
            }
        %>
    </div>
    
    <div id="cartSummary">
	    <% double total = 0.0;
	
	    for (Product product : cartItems) {
	        total += product.getPrice();
	    } %>
        <p>Totale: <%= total %> EURO</p>
        
        <div id="cartActions">
            <button>Procedi al pagamento</button>
            <button>Svuota carrello</button>
        </div>
    </div>
</div>

<%@ include file="../footer.jsp" %>

</body>
</html>