<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>

	<head>
	
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/header.css">
		
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
			
				<select>
					<option>Tutte le categorie</option>
					<option>Case</option>
					<option>Schede madri</option>
					<option>CPU</option>
					<option>GPU</option>
					<option>RAM</option>
					<option>Storage</option>
					<option>Cooling</option>
					<option>PSU</option>
					<option>Monitor</option>
					<option>Sistemi Operativi</option>
					<option>Accessori</option>
				</select>
				
				<input id="searchInput" type="search" placeholder="Ricerca DigitalLab.it" spellcheck="false">
				<button id="searchButton">Cerca</button>
				
			</div>
			<!-- Fine barra di ricerca -->
			
			<!-- Inizio link di supporto -->
			<div class="helpContainer">
				<a href="<%=request.getContextPath()%>/common/support.jsp">Assistenza</a>
				<a href="">Contatti</a>
			</div>
			<!-- Fine link di supporto -->
			
			<!-- Inizio area utente -->
			<div id="userArea">
			
				<div id="cartContainer">
					<a href="<%=request.getContextPath()%>/common/cart.jsp" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
						<img id="cartImage" src="<%=request.getContextPath()+"/imgs/cart.png"%>">
					</a>
				</div>
				<div id="userContainer">
					<a href="<%= request.getContextPath()%>/UserRedirectServlet" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
						<img id="userImage" src="<%=request.getContextPath()+"/imgs/user.png"%>">
					</a>				
				</div>
			
			</div>
			<!-- Fine area utente -->		
			
		</header>
		<!-- Fine header -->

		<!-- Inizio menu di navigazione -->		
		<nav>
		
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
		<nav id="phoneNav">
			
			<div id="phoneNavButton">
				<a id="phoneButton">MENU</a>
			</div>		
			<div id="phoneNavContainer">
				<ul>
					<li>
						<a href="">Il mio profilo</a>
						<a href="">Carrello</a>
					</li>
					<li><a href="">CASE</a></li>
					<li><a href="">SCHEDE MADRI</a></li>
					<li><a href="">CPU</a></li>
					<li><a href="">GPU</a></li>
					<li><a href="">RAM</a></li>
					<li><a href="">STORAGE</a></li>
					<li><a href="">COOLING</a></li>
					<li><a href="">PSU</a></li>
					<li><a href="">MONITOR</a></li>
					<li><a href="">SISTEMI OPERATIVI</a></li>
					<li><a href="">ACCESSORI</a></li>
				</ul>				
			</div>
			
		</nav>
		<!-- Fine menu di navigazione (phone view) -->

	</body>

</html>