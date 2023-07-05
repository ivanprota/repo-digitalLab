<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
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
    
    <!-- Barra avanzamento e pulsante -->
    <div id="progressBar">
        <span style="width: 0;"></span>
        <div class="step">Scegli il processore</div>
    	<button id="progressButton" onclick="nextStep()">Avanti</button>
    </div>
    
    <!-- Scelta elementi -->
    
    <!-- Fine scelta elementi -->
    
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
            "Scegli la tastiera",
            "Scegli il mouse",
            "Scegli il sistema operativo"
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
                
                if (currentStep === steps.length - 1) {
                    progressButton.textContent = "Aggiungi al carrello";
                    progressButton.onclick = addToCart;
                    progressBar.classList.add("complete");
                }
            }
        }

        function addToCart() {
            alert("Prodotto aggiunto al carrello!");
        }
    </script>
</body>
</html>
