<%@ page import="java.util.Collection" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="it.unisa.model.Product" %>
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
%>

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
	
	<%@ include file="../header.jsp"%>
	
	<div id="configurationBodyContainer">
        
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
	            	<input type="radio" value="<%=request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
	                 <img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
	            </label>
	            <p><%= product.getPrice()%></p>
            </div>
        	<%  
        		}
        	%>
        	</div>               
        </div>
        <div id="cpuSelection">
        	<h1>CPU</h1>
        	<%
        		Iterator<?> itCpu = cpuCollection.iterator();
        	%>
        </div>
    </div>
	
	<%@ include file="../footer.jsp"%>
	
</body>
</html>