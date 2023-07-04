<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>

<% 		
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
   		response.sendRedirect(request.getContextPath()+"/Catalogue");
      	return;
 	}
	
	Collection<?> pictures = (Collection<?>) request.getAttribute("pictures");
	if (pictures == null)
	{
		request.getRequestDispatcher("/GetPictureServlet").forward(request, response);
		return;
	}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogo</title>
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/catalogue.css">
    <!-- [JQUERY] Comparsa del menu quando lo la dimensione della pagina è abbastanza piccola -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script type="text/javascript" src="scripts/common.js"></script>
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
						<li><a href="<%= request.getContextPath()%>/Catalogue?filter=price&priceRange1=200-">Fino a 200 EURO</a></li>
						<li><a href="<%= request.getContextPath()%>/Catalogue?filter=price&priceRange1=200&priceRange2=400">200 a 400 EURO</a></li>
						<li><a href="<%= request.getContextPath()%>/Catalogue?filter=price&priceRange1=400&priceRange2=600">400 a 600 EURO</a></li>
						<li><a href="<%= request.getContextPath()%>/Catalogue?filter=price&priceRange1=600&priceRange2=800">600 a 800 EURO</a></li>
						<li><a href="<%= request.getContextPath()%>/Catalogue?filter=price&priceRange1=800+">oltre 800 EURO</a></li>
					</ul>
					<!-- Min e Max EURO -->
					<div id="cataloguePriceFilterInputContainer">
						<form action="<%= request.getContextPath()%>/Catalogue" method="GET">
							<input type="text" name="filter" value="price" hidden="hidden">
							<div class="cataloguePriceFilterInput">
								<input type="number" step="0.01" placeholder="Min" name="priceRange1">
							</div>
							<div class="cataloguePriceFilterInput">
								<input type="number" step="0.01" placeholder="Max" name="priceRange2">
							</div>
							<div id="cataloguePriceFilterButton">
								<button>Cerca</button>
							</div>
						</form>
					</div>
				</div>
				<!-- BRAND: -->
				<div id="catalogueBrandFilter">
					<h1>Brands</h1>
					<form action="<%= request.getContextPath()%>/Catalogue" method="GET">
						<input type="text" name="filter" value="brand" hidden="hidden">
						<div class="catalogueBrandFilterContainer">
							<input id="AMDsBrand" type="checkbox" name="brands" value="AMD">
							<label for="AMDsBrand">AMD</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="IntelsBrand" type="checkbox" name="brands" value="Intel">
							<label for="IntelsBrand">Intel</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="MSIsBrand" type="checkbox" name="brands" value="MSI">
							<label for="MSIsBrand">MSI</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="MicrosoftsBrand" type="checkbox" name="brands" value="Microsoft">
							<label for="MicrosoftsBrand">Microsoft</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="SamsungsBrand" type="checkbox" name="brands" value="Samsung">
							<label for="SamsungsBrand">Samsung</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="PhilipssBrand" type="checkbox" name="brands" value="Philips">
							<label for="PhilipssBrand">Philips</label>
						</div>
						<div class="catalogueBrandFilterContainer">
							<input id="GigabytesBrand" type="checkbox" name="brands" value="Gigabyte">
							<label for="GigabytesBrand">Gigabyte</label>
						</div>
						<div id="catalogueBrandFilterButtonContainer">
							<button>Cerca</button>
						</div>
					</form>
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
			    Product product = (Product) it.next();
			    Iterator<?> it2 = pictures.iterator();
			    Picture picture = null;
			    while (it2.hasNext())
			    {
			    	picture = (Picture) it2.next();
			    	if (picture.getProduct().getCode() == product.getCode())
			    		break;
			    }
			%>
			<div class="product">
			    <div class="product-image">
			        <a href="<%=request.getContextPath()%>/common/product.jsp?productCode=<%= product.getCode()%>">
			            <img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
			        </a>
			    </div>
			    <div class="product-name">
			        <%= product.getBrand() + " " + product.getModel() %>
			    </div>
			    <div class="product-price">
			        <%= product.getPrice() + " $" %>
			    </div>
			</div>
			<%
			} 
			%>
	    </div>
    </div>
    
		<%@ include file="../footer.jsp"%>
</body>
</html>