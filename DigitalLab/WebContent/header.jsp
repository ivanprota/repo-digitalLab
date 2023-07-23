<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="IT">

	<head>
	
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link rel="icon" type="image/x-icon" href="<%=request.getContextPath()+"/imgs/favicon.ico"%>">
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/header.css">
		
		<title>Digital Lab</title>
		
		<!-- Effetto di scorrimento per i contatti -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="<%= request.getContextPath()%>/scripts/common.js"></script>
		<script>
			$(document).ready(function() {
				$("#phoneButton").click(function() {
					$("#phoneNavContainer").slideToggle();
				})
			})
		</script>
		
	</head>
	
	<body>

		<!-- Inizio header -->
		<header>
			
			<!-- Inizio logo -->
			<div id="logoContainer">
				
				<a href="<%= request.getContextPath()%>/home.jsp" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
					<img src="<%=request.getContextPath()+"/imgs/logo.png"%>" alt="HEADER LOGO">
				</a>
				
			</div>
			<!-- Fine logo -->				
			
			<!-- Inizio link di supporto -->
			<div class="helpContainer" id="confLinkContainer">
				<a href="<%=request.getContextPath()%>/common/catalogue.jsp">Catalogo!</a>
			</div>
			<!-- Fine link di supporto -->
			
			<!-- Inizio barra di ricerca -->
			<div id="searchContainer">
			
				<form action="<%= request.getContextPath()%>/Catalogue" method="GET">
				<input type="text" name="searchBar" value="true" hidden="hidden">
				<select name="category">
					<option value="Tutte le categorie">Tutte le categorie</option>
					<option value="Case">Case</option>
					<option value="Schede madri">Schede madri</option>
					<option value="CPU">CPU</option>
					<option value="GPU">GPU</option>
					<option value="RAM">RAM</option>
					<option value="Storage">Storage</option>
					<option value="Cooling">Cooling</option>
					<option value="PSU">PSU</option>
					<option value="Monitor">Monitor</option>
					<option value="Sistemi Operativi">Sistemi Operativi</option>
					<option value="Accessori">Accessori</option>
				</select>
				
				<input id="searchInput" name="search" type="search" placeholder="Ricerca DigitalLab.it" spellcheck="false">
				<button id="searchButton">Cerca</button>
				</form>
				
			</div>
			<!-- Fine barra di ricerca -->
			
			<!-- Inizio link di supporto -->
			<div class="helpContainer">
				<a href="<%=request.getContextPath()%>/common/support.jsp">Assistenza</a>
				<a href="#footer">Contatti</a>
			</div>
			<!-- Fine link di supporto -->
			
			<!-- Inizio area utente -->
			<div id="userArea">
			
				<div id="cartContainer">
					<a href="<%=request.getContextPath()%>/common/cart.jsp" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
						<img id="cartImage" src="<%=request.getContextPath()+"/imgs/cart.png"%>" alt="CART IMAGE">
					</a>
				</div>
				<div id="userContainer">
					<a href="<%= request.getContextPath()%>/UserRedirectServlet" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
						<img id="userImage" src="<%=request.getContextPath()+"/imgs/user.png"%>" alt="USER LOGO">
					</a>				
				</div>
			
			</div>
			<!-- Fine area utente -->		
			
		</header>
		<!-- Fine header -->

		<!-- Inizio menu di navigazione -->		
		<nav aria-label="Site menu">
		
			<div id="navContainer">				
				<ul>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Case">CASE</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Schede%20Madri">SCHEDE MADRI</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=CPU">CPU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=GPU">GPU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=RAM">RAM</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Storage">STORAGE</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Cooling">COOLING</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=PSU">PSU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Monitor">MONITOR</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Sistemi%20Operativi">SISTEMI OPERATIVI</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Accessori">ACCESSORI</a></li>
				</ul>
			</div>	
									
		</nav>
		<!-- Fine menu di navigazione -->
		
		<!-- Inizio menu di navigazione (phone view) -->
		<nav id="phoneNav" aria-label="Site-menu">
			
			<div id="phoneNavButton">
				<a id="phoneButton">MENU</a>
			</div>		
			<div id="phoneNavContainer">
				<ul>
					<li>
						<a href="<%= request.getContextPath()%>/UserRedirectServlet" >Il mio profilo</a>
						<a href="<%=request.getContextPath()%>/common/cart.jsp" >Carrello</a>
					</li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Case">CASE</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Schede%20Madri">SCHEDE MADRI</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=CPU">CPU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=GPU">GPU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=RAM">RAM</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Storage">STORAGE</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Cooling">COOLING</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=PSU">PSU</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Monitor">MONITOR</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Sistemi%20Operativi">SISTEMI OPERATIVI</a></li>
					<li><a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=Accessori">ACCESSORI</a></li>
				</ul>				
			</div>
			
		</nav>
		<!-- Fine menu di navigazione (phone view) -->

	</body>

</html>