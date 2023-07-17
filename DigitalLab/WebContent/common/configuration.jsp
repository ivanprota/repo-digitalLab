<%@ page import="java.util.Collection" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="it.unisa.model.Product" %>
<%@ page import="it.unisa.model.Customer" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="it.unisa.db.ProductDAO" %>
<%@ page import="it.unisa.db.PictureDAO" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="javax.sql.DataSource" %>

<%
	

	Collection<?> caseCollection = (Collection<?>) request.getAttribute("case");
	Collection<?> cpuCollection = (Collection<?>) request.getAttribute("cpu");
	Collection<?> motherboardCollection = (Collection<?>) request.getAttribute("motherboard");
	Collection<?> ramCollection = (Collection<?>) request.getAttribute("ram");
	Collection<?> gpuCollection = (Collection<?>) request.getAttribute("gpu");
	Collection<?> storageCollection = (Collection<?>) request.getAttribute("storage");
	Collection<?> psuCollection = (Collection<?>) request.getAttribute("psu");
	Collection<?> coolingCollection = (Collection<?>) request.getAttribute("cooling");
	Collection<?> monitorCollection = (Collection<?>) request.getAttribute("monitor");
	Collection<?> osCollection = (Collection<?>) request.getAttribute("os");
	Collection<?> extraCollection = (Collection<?>) request.getAttribute("extra");
	
	Collection<?> casePictures = (Collection<?>) request.getAttribute("casePictures");
	
	if (caseCollection == null || casePictures == null)
	{
		response.sendRedirect(request.getContextPath() + "/Configuration");
		return;
	}
	
	Customer customer = (Customer) session.getAttribute("customer");
	String username = "";
	
	if(customer != null) {
		username = customer.getUsername();
	}
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Configurazione DigitalLab.it</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/configuration.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/scripts/configuration.js"></script>
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
	
	<%@ include file="../header.jsp"%>
	
	<div id="configurationBodyContainer">
        
        <input type="text" id="inputCustomerUsername" value="<%= username%>" hidden="hidden">
        
        <div id="caseSelectionContainer">
        	<h1>Case</h1>
        	<div id="caseSelection">
        	<%
        		Iterator<?> itCase = caseCollection.iterator();
        		while (itCase.hasNext())
        		{
	        		Product product = (Product) itCase.next();
	        		Picture picture = null;
	        		Iterator<?> itPicturesCase = casePictures.iterator();
	        		while (itPicturesCase.hasNext())
	        		{
	        			picture = (Picture) itPicturesCase.next();
	        			if(product.getCode() == picture.getProduct().getCode())
	        				break;
	        		}
        	%>
        	<div class="caseContainer">	
	            <label>
	            	<input id="radioCase" type="radio" name="radioCase" value="<%=product.getCode()%>" checked>
	                 <img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
	            </label>
	            <%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
                %>
                <p><%=formattedPrice%> &euro;</p>
            </div>
        	<%  
        		}
        	%>
        	</div>               
        </div>
        <div id="cpuSelection">
        	<h1>CPU</h1>
        	<div id="cpuContainer">
        		<select id="cpuSelect">
        	<%
        		Iterator<?> itCpu = cpuCollection.iterator();
        		while (itCpu.hasNext())
        		{
        			Product product = (Product) itCpu.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="motherboardSelection">
        	<h1>Scheda madre</h1>
        	<div id="motherboardContainer">
        		<select id="motherboardSelect">
        	<%
        		Iterator<?> itMotherboard = motherboardCollection.iterator();
        		while (itMotherboard.hasNext())
        		{
        			Product product = (Product) itMotherboard.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="ramSelection">
        	<h1>RAM</h1>
        	<div id="ramContainer">
        		<select id="ramSelect">
        	<%
        		Iterator<?> itRam = ramCollection.iterator();
        		while (itRam.hasNext())
        		{
        			Product product = (Product) itRam.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>        	
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="gpuSelection">
        	<h1>GPU</h1>
        	<div id="gpuContainer">
        		<select id="gpuSelect">
        	<%
        		Iterator<?> itGpu = gpuCollection.iterator();
        		while (itGpu.hasNext())
        		{
        			Product product = (Product) itGpu.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>        	
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="storageSelection">
        	<h1>Storage</h1>
        	<div id="storageContainer">
        		<select id="storageSelect">
        	<%
        		Iterator<?> itStorage = storageCollection.iterator();
        		while (itStorage.hasNext())
        		{
        			Product product = (Product) itStorage.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>        	
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="psuSelection">
        	<h1>PSU</h1>
        	<div id="psuContainer">
        		<select id="psuSelect">
        	<%
        		Iterator<?> itPsu = psuCollection.iterator();
        		while (itPsu.hasNext())
        		{
        			Product product = (Product) itPsu.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>        	
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="coolingSelection">
        	<h1>Cooling</h1>
        	<div id="coolingContainer">
        		<select id="coolingSelect">
        	<%
        		Iterator<?> itCooling = coolingCollection.iterator();
        		while (itCooling.hasNext())
        		{
        			Product product = (Product) itCooling.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>        	
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="monitorSelection">
        	<h1>Monitor</h1>
        	<div id="monitorContainer">
        		<select id="monitorSelect">
        	<%
        		Iterator<?> itMonitor = monitorCollection.iterator();
        		while (itMonitor.hasNext())
        		{
        			Product product = (Product) itMonitor.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="osSelection">
        	<h1>Sistema Operativo</h1>
        	<div id="osContainer">
        		<select id="osSelect">
        	<%
        		Iterator<?> itOs =	osCollection.iterator();
        		while (itOs.hasNext())
        		{
        			Product product = (Product) itOs.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        <div id="extraSelection">
        	<h1>Accessori</h1>
        	<div id="extraContainer">
        		<select id="extraSelect">
        	<%
        		Iterator<?> itExtra = extraCollection.iterator();
        		while (itExtra.hasNext())
        		{
        			Product product = (Product) itExtra.next();
        	%>
        	<%
		            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
	                String formattedPrice = decimalFormat.format(product.getPrice());
            %>
        		<option value="<%= product.getCode()%>"><%= product.getBrand()%> <%= product.getModel()%> (<%= formattedPrice%>&euro;)</option>
        	<%
        		}
        	%>
        		</select>
        	</div>
        </div>
        
        <%if(customer == null) {
        	%><p>Autenticati per effettuare l'acquisto</p><%
        } else { %>
            <input type="button" id="configurationButton" value="Aggiungi al carrello"><%
        } %>
    </div>
	
	<%@ include file="../footer.jsp"%>
	
</body>
</html>