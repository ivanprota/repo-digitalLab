<%@ page import="java.util.Collection" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="it.unisa.db.PictureDAO" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="javax.sql.DataSource" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Configurazione DigitalLab.it</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/configuration.css">
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
    
     <div id="configurationBodyContainer">
     
	    <!-- Barra avanzamento e pulsante -->
	    <div id="progressBar">
	        <span style="width: 0;"></span>
	        <div class="step">Scegli il processore</div>
	    	<button id="progressButton" onclick="nextStep()">Avanti</button>
	    	<!-- DA FARE: INSERIRE PULSANTE PER POTER NON SCEGLIERE ALCUN ELEMENTO E ANDARE AVANTI CON GLI STEPS -->
	    </div>
	    
		<!-- Applichiamo i filtri -->
	   	<% DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	   	ProductDAO dao = new ProductDAO(ds);
	   	Collection<Product> products = null;
	   	String category = "CPU";
	    products = dao.doRetrieveByFilter(category);%>
	
		<div id="productContainer">
			<%
			PictureDAO dao2 = new PictureDAO(ds);
			Collection<Picture> pictures = new LinkedList<Picture>();
			for (int code = 1; code <= 22; code++) {
				pictures.add(dao2.doRetrieveByKey(code));
			}
			Iterator<?> it = products.iterator();
			while(it.hasNext()) {
				Product product = (Product) it.next();
				Iterator<?> it2 = pictures.iterator();
				Picture picture = null;
				while (it2.hasNext())
				{
					picture = (Picture) it2.next();
					if (picture.getProduct().getCode() == product.getCode())
						break;
				}%>
				
				<!-- Carichiamo i prodotti -->
				<div class="product">
				
					<!-- Carichiamo l'immagine -->
					<div class="product-image">
						<!-- Creiamo il pulsante per la scelta del prodotto -->
						<!-- DA FARE: per i prodotti come "Monitor" e "Accessori" bisogna poter scegliere più di una componente (opzionale) -->
						<input type="radio" name="<%=category%>" value="<%=product.getCode()%>">
						<label class="choice">
						    <a href="<%=request.getContextPath()%>/common/product.jsp?productCode=<%= product.getCode()%>">
						    	<img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
						    </a>
					 	</label>
					</div>
					
					<!-- Carichiamo il nome e il prezzo -->
					<div class="product-name">
						<%= product.getBrand() + " " + product.getModel() %>
					</div>
					<div class="product-price">
						<%
						DecimalFormat decimalFormat = new DecimalFormat("#0.00");
						String formattedPrice = decimalFormat.format(product.getPrice());
						%>
						<%= formattedPrice %> &euro;
					</div>
				</div>
				<!-- Fine caricamento prodotti -->
			<%}%> 
		</div>
	</div>
    
    <%@ include file="../footer.jsp" %>
    
    <script>
        var steps = [
            "Scegli il processore",
            "Scegli la scheda madre",
            "Scegli la memoria RAM",
            "Scegli la scheda grafica",
            "Scegli la memoria di archiviazione",
            "Scegli l'alimentatore",
            "Scegli il case",
            "Scegli il sistema di raffreddamento",
            "Scegli il monitor",
            "Scegli gli accessori",
            "Scegli il sistema operativo"
        ];
        
        var categories = [
        	"CPU",
        	"Schede%20Madri",
        	"RAM",
        	"GPU",
        	"Storage",
        	"PSU",
        	"Case",
        	"Cooling",
        	"Monitor",
        	"Accessori",
        	"Sistemi%20Operativi"
        ];

        var currentStep = 0;
        var progressBar = document.querySelector("#progressBar span");
        var stepText = document.querySelector("#progressBar .step");
        var progressButton = document.querySelector("#progressButton");

        function nextStep() {
            if (currentStep < steps.length - 1) {
                currentStep++;
                var progressWidth = (currentStep / (steps.length - 1)) * 100;
                progressBar.style.width = progressWidth + "%";
                stepText.textContent = steps[currentStep];
                
                // Cambiamo valore della categoria per procedere
                category = categories[currentStep];
                
                if (currentStep === steps.length - 1) {
                    progressButton.textContent = "Aggiungi al carrello";
                    progressButton.onclick = addToCart;
                    progressBar.classList.add("complete");
                }
            }
        }

        function addToCart() {
        	// DA FARE: CHIAMARE LA SERVLET /AddItemToCart INVIANDO IN INPUT TUTTI GLI OGGETTI SELEZIONATI (magari con un messaggio di testo "Riceverai il tuo computer assemblato")
            alert("Prodotto aggiunto al carrello!");
        }
    </script>
</body>
</html>