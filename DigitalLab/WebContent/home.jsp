<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html lang="IT">

	<head>

		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<title>DigitalLab.it</title>
		
		<link type="text/css" rel="stylesheet" href="css/slider.css">
		<link type="text/css" rel="stylesheet" href="css/best-selling.css">
		<link type="text/css" rel="stylesheet" href="css/most-popular.css">
		<link type="text/css" rel="stylesheet" href="css/configuration-side.css">

		<script type="text/javascript" src="scripts/common.js"></script>
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

		<!-- header -->
		<%@ include file="header.jsp"%>
		<!-- /header -->
		
		
		<!-- Inizio corpo -->
		
		<!-- Inizio slider -->
		<div id="sliderContainer">
			<div class="slider">
				<div class="slides">
					
					<input type="radio" name="radio-btn" id="radio1">
					<input type="radio" name="radio-btn" id="radio2">
					<input type="radio" name="radio-btn" id="radio3">
					
					<div class="slide first">
						<img src="imgs/amd.jpg" alt="AMD IMAGE">
					</div>
					<div class="slide">
						<img src="imgs/intel.png" alt="INTEL IMAGE">
					</div>
					<div class="slide">
						<img src="imgs/msi.jpg" alt="MSI IMAGE">
					</div>
					
					<div class="navigation-auto">
						<div class="auto-btn1"></div>
						<div class="auto-btn2"></div>
						<div class="auto-btn3"></div>
					</div>
					
				</div>
				
				<div class="navigation-manual">
					<label for="radio1" class="manual-btn"></label>
					<label for="radio2" class="manual-btn"></label>
					<label for="radio3" class="manual-btn"></label>
				</div>
				
			</div>
			
			<script>
				
				var counter = 1;
				setInterval(function() {
					document.getElementById("radio" + counter).checked = true;
					counter++;
					if (counter > 3)
						counter = 1;
				}, 5000);
			
			</script>
			
		</div>
		<!-- Fine slider -->
		
		<!-- Inizio prodotti più venduti -->
		<div id="bestSellingContainer">
		
			<h1>I Più Venduti</h1>
			<div id="itemsContainer">			
				<div class="bestSelling">
					<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=4">
						<img src="imgs/products/cpu1_f1.jpg" alt="CPU IMAGE">
						Intel Core i5-12400F
					</a>
				</div>
				<div class="bestSelling">
					<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=22">
						<img src="imgs/products/case1_f1.jpg" alt="CASE IMAGE">
						Mars MC-U3
					</a>
				</div>
				<div class="bestSelling">
					<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=7">
						<img src="imgs/products/gpu2_f1.jpg" alt="GPU IMAGE">
						Gigabyte GeForce RTX 3060
					</a>
				</div>
				<div class="bestSelling">
					<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=18">
						<img src="imgs/products/os1_f1.jpg" alt="OS IMAGE">
						Windows 11 HOME
					</a>
				</div>
				<div class="bestSelling">
					<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=16">
						<img src="imgs/products/monitor1_f1.jpg" alt="MONITOR IMAGE">
						Samsung S36C 
					</a>
				</div>											
			</div>
		
		</div>
		<!-- Fine prodotti più venduti -->
		
		<!-- Inizio categorie più popolari -->
		<div id="mostPopularContainer">
		
			<h1>I Più Popolari</h1>
			<div id="pcContainer">
				<div class="mostPopular">
					<a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=PC%20Gaming">
						<img src="imgs/pcgaming.png" alt="PC GAMING IMAGE">
						PC Gaming
					</a>
				</div>
				<div class="mostPopular">
					<a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=PC%20Workstation">
						<img src="imgs/workstation.png" alt="WORKSTATION IMAGE">
						Workstation
					</a>
				</div>
				<div class="mostPopular">
					<a href="<%= request.getContextPath()%>/Catalogue?filter=category&categoryName=PC%20Streaming">
						<img src="imgs/pcstreaming.jpg" alt="PC STREAMING IMAGE">
						PC Streaming
					</a>
				</div>
			</div>
		
		</div>
		<!-- Fine categorie più popolari -->
		
		<!-- Inizio link configurazione -->
		<div id="confSideContainer">
		
			<div id="buttonContainer">
				<h1>Configura ora il tuo PC</h1>
				<a href="<%= request.getContextPath()%>/common/configuration.jsp">
    				<button id="confButton">CONFIGURA ORA!</button>
				</a>
			</div>
			<div id="confSideImageContainer">
				<a href="<%= request.getContextPath()%>/common/configuration.jsp">
					<img src="imgs/configpc2.jpg" alt="CONFIGURATION PC IMAGE">
				</a>
			</div>
		
		</div>
		<!-- Fine link configurazione -->
		
		<!-- Fine corpo -->
		
		
		<!-- footer -->
		<%@ include file="footer.jsp"%>
		<!-- /footer -->

	</body>

</html>