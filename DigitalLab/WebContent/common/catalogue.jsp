<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>

<% 	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
   		response.sendRedirect(request.getContextPath()+"/Catalogue");
      	return;
 	}%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo</title>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/catalogue.css">
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
	
	<!-- FILTRI CATALOGO -->
	<div id="catalogueBody">
		<!-- FILTRI Prezzo, brand e recensioni -->
			<div id="bodyContainer">
				<div id="catalogueFilters">
				<!-- PREZZO: -->
				<div id="cataloguePriceFilter">
					<h1>Prezzo dispositivo</h1>
					<ul>
						<li><a href="">Fino a 200 EURO</a></li>
						<li><a href="">200 a 400 EURO</a></li>
						<li><a href="">400 a 600 EURO</a></li>
						<li><a href="">600 a 800 EURO</a></li>
						<li><a href="">oltre 800 EURO</a></li>
					</ul>
					<!-- Min e Max EURO -->
					<div id="cataloguePriceFilterInputContainer">
						<div class="cataloguePriceFilterInput">
							<input type="number" step="0.01" placeholder="Min">
						</div>
						<div class="cataloguePriceFilterInput">
							<input type="number" step="0.01" placeholder="Max">
						</div>
						<div id="cataloguePriceFilterButton">
							<button>Cerca</button>
						</div>
					</div>
				</div>
				<!-- BRAND: -->
				<div id="catalogueBrandFilter">
					<h1>Brands</h1>
					<div class="catalogueBrandFilterContainer">
						<input id="AMDsBrand" type="checkbox" name="brands">
						<label for="AMDsBrand">AMD</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="IntelsBrand" type="checkbox" name="brands">
						<label for="IntelsBrand">Intel</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="MSIsBrand" type="checkbox" name="brands">
						<label for="MSIsBrand">MSI</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="MicrosoftsBrand" type="checkbox" name="brands">
						<label for="MicrosoftsBrand">Microsoft</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="SamsungsBrand" type="checkbox" name="brands">
						<label for="SamsungsBrand">Samsung</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="PhilipssBrand" type="checkbox" name="brands">
						<label for="PhilipssBrand">Philips</label>
					</div>
					<div class="catalogueBrandFilterContainer">
						<input id="GigabytesBrand" type="checkbox" name="brands">
						<label for="GigabytesBrand">Gigabyte</label>
					</div>
				</div>
				<!-- RECENSIONI: -->
				<div id="catalogueReviewFilter">
					<h1>Recensioni</h1>
					<div class="reviewStarContainer">
						<a href=""><img src="<%=request.getContextPath()%>/imgs/5stars.png"></a>
					</div>
					<div class="reviewStarContainer">
						<a href=""><img src="<%=request.getContextPath()%>/imgs/4stars.png">e più</a>
					</div>
					<div class="reviewStarContainer">
						<a href=""><img src="<%=request.getContextPath()%>/imgs/3stars.png">e più</a>
					</div>
					<div class="reviewStarContainer">
						<a href=""><img src="<%=request.getContextPath()%>/imgs/2stars.png">e più</a>
					</div>
					<div class="reviewStarContainer">
						<a href=""><img src="<%=request.getContextPath()%>/imgs/1star.png">e più</a>
					</div>
				</div>
			</div>
		</div>
		
	    <div class="product-container">
			<% Iterator<?> it = products.iterator();
			while(it.hasNext()) {
			    Product product = (Product) it.next();%>
			<div class="product">
			    <div class="product-image">
			        <a href="<%=request.getContextPath()%>/common/product.jsp?productCode=<%= product.getCode() %>">
			            <img src="GetPictureServlet?productCode=<%= product.getCode() %>">
			        </a>
			    </div>
			    <div class="product-name">
			        <%= product.getBrand() + " " + product.getModel() %>
			    </div>
			    <div class="product-price">
			        <%= product.getPrice() + " $" %>
			    </div>
			</div>
			<% } %>
	    </div>
    </div>
    
		<%@ include file="../footer.jsp"%>
</body>
</html>