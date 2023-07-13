<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>

<html>

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
						<img src="imgs/amd.jpg">
					</div>
					<div class="slide">
						<img src="imgs/intel.png">
					</div>
					<div class="slide">
						<img src="imgs/msi.jpg">
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
					<a href="">
						<img src="imgs/intel-i5.jpg">
						Intel Core i5-12400F
					</a>
				</div>
				<div class="bestSelling">
					<a href="">
						<img src="imgs/case.jpg">
						EMPIRE GAMING - Case
					</a>
				</div>
				<div class="bestSelling">
					<a href="">
						<img src="imgs/gpu.jpg">
						Gigabyte GeForce RTX 3060
					</a>
				</div>
				<div class="bestSelling">
					<a href="">
						<img src="imgs/windows11home.jpg">
						Windows 11 HOME
					</a>
				</div>
				<div class="bestSelling">
					<a href="">
						<img src="imgs/monitor.jpg">
						MSI Optix G24C24
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
					<a href="">
						<img src="imgs/pcgaming.png">
						PC Gaming
					</a>
				</div>
				<div class="mostPopular">
					<a href="">
						<img src="imgs/workstation.png">
						Workstation
					</a>
				</div>
				<div class="mostPopular">
					<a href="">
						<img src="imgs/pcstreaming.jpg">
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
					<img src="imgs/configpc2.jpg">
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