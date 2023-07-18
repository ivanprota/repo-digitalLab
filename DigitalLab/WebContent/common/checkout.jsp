<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="it.unisa.model.ShippingAddress" %>
<%@ page import="it.unisa.model.PaymentMethod" %>
<%@ page import="it.unisa.model.Product" %>

<%@ page import="it.unisa.db.PictureDAO" %>
<%@ page import="it.unisa.model.Picture" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="javax.sql.DataSource" %>

<% 
	Collection<?> shippingAddresses = (Collection<?>) request.getAttribute("shippingAddresses");
	Collection<?> paymentMethods = (Collection<?>) request.getAttribute("paymentMethods");
	
	// Lista prodotti del carrello
	DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
	@SuppressWarnings("unchecked")
	Collection<Product> cartItems = (Collection<Product>) session.getAttribute("cartItems");
	@SuppressWarnings("unchecked")
	Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("mapProductsQuantity");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Checkout DigitalLab.it</title>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/checkout.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
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

	<%@ include file="../header.jsp" %>
	
	<div id="checkoutBodyContainer">
		<h1>Riepilogo ordine</h1>
		<hr/>
		
		<div class="checkoutBodyFields">
			<h2>Indirizzo di spedizione</h2>
			<p class="change"><a href="">Modifica</a></p>
			<!-- Inserire indirizzo di spedizione principale dell'utente -->
			<div class="ListContainer">
			<%
				if(shippingAddresses != null) {
					Iterator<?> it = shippingAddresses.iterator();
					while(it.hasNext()) {
						ShippingAddress shippingAddress = (ShippingAddress) it.next();
						%>
				        <label class="choice">
				          <input type="radio" name="shippingAddress" value="<%=shippingAddress%>">
				          <%=shippingAddress%>
				        </label>
						<%
					}
				} else {}
			%>
			</div>
		</div>

		<hr/>

		<div class="checkoutBodyFields">
			<h2>Metodo di pagamento</h2>
			<p class="change"><a href="">Modifica</a></p>
			<!-- Inserire metodo di pagamento principale dell'utente -->
			<div class="ListContainer">
			<%
				if(paymentMethods != null) {
					Iterator<?> it = paymentMethods.iterator();
					while(it.hasNext()) {
						PaymentMethod paymentMethod = (PaymentMethod) it.next();
						%>
				        <label class="choice">
				          <input type="radio" name="paymentMethod" value="<%=paymentMethod%>">
				          <%=paymentMethod%>
				        </label>
						<%
					}
				} else {}
			%>
			</div>
		</div>
		
		<hr/>
		
		<div class="checkoutBodyFields">
			<h2>Articoli</h2>
			<p class="change"><a href="<%=request.getContextPath()%>/common/cart.jsp">Modifica</a></p>
			<!-- Inserire prodotti del carrello -->
			<div id="productsContainer" class="ListContainer">
				<%double total = 0.00;
				for (Product product : cartItems) {
					int quantity = map.get(product.getCode());
				    total += product.getPrice() * quantity;
	               	PictureDAO pictureDAO = new PictureDAO(dataSource);
	                Picture picture = pictureDAO.doRetrieveAllByKey(product.getCode()).iterator().next();%>
	                
	                <div class="cartItem">
		               	<div class="cartItemImage">
		                   	<img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>" alt="PRODUCT IMAGE">
		                </div>
		               	<div class="cartItemDetails">
		                	<%DecimalFormat decimalFormat = new DecimalFormat("#0.00");
							String formattedPrice = decimalFormat.format(product.getPrice());%>
		                    <h3><%=product.getBrand() + " " + product.getModel() + " "%><strong><%=formattedPrice%> &euro;</strong></h3>
		                    <p> Quantità: <%=quantity%></p>
		               	</div>
					</div><%
				}
				DecimalFormat decimalFormat = new DecimalFormat("#0.00");
				String formattedPrice = decimalFormat.format(total);%>
			</div>
			<p id="total">Totale: <%=formattedPrice%> &euro;</p>
		</div>
		
		<hr/>
		
		<!-- Cofnerma l'ordine -->
		<div id="completeOrderButton">
			<input type="button" id="confirmOrderButton" value="Conferma l'ordine">
		</div>
		
		<span>
			Confermando il tuo ordine accetti le nostre Condizioni generali di uso e vendita.<br/>
			Prendi visione della nostra Informativa sulla privacy.<br/><br/>
			<strong>Tutti i prezzi sono comprensivi di IVA.</strong>
		</span>
		
	</div>
	
	<script>
		$(document).ready(function() {
			
			let PaymentMethodSelected;
			let ShippingAddressSelected;
			
		    $(document).on("click", "#confirmOrderButton", function() {
		    	
		    	
		    	
		        var isPaymentMethodSelected = false;
		        $("input[name='paymentMethod']").each(function() {
		            if ($(this).is(":checked")) {
		                isPaymentMethodSelected = true;
		                PaymentMethodSelected = $(this).val();
		                return false;
		            }
		        });
	
		        var isShippingAddressSelected = false;
		        $("input[name='shippingAddress']").each(function() {
		            if ($(this).is(":checked")) {
		                isShippingAddressSelected = true;
		             	ShippingAddressSelected = $(this).val();
		                return false;
		            }
		        });
	
		        if (isPaymentMethodSelected && isShippingAddressSelected) {
		        	
		        	var completeOrderButton = $('<input type="button" id="confirmButton" value="Conferma"> <input type="button" id="cancelButton" value="Annulla">');
		            $("#completeOrderButton").html(completeOrderButton);
					
		            // Dividere la stringa ad ogni virgola:
		            let fields1 = ShippingAddressSelected.split(", ");
		            var id = fields1[0];
		            /*var provincia = fields1[1];
		            var via = fields1[2];
		            var citta = fields1[3];
		            var cap = fields1[4];
		            var civico = fields1[5];
		            var username = fields1[6];
		            var email = fields1[7];
		            var password = fields1[8];
		            var nome = fields1[9];
		            var cognome = fields1[10];
		            var telefono = fields1[11];*/
		            let fields2 = PaymentMethodSelected.split(", ");
		            var pan = fields2[0];
		            /*var owner = fields2[1];
		            var cvv = fields2[2];
		            var expirationDate = fields2[3];
		            var username = fields2[4];
		            var email = fields2[5];
		            var password = fields2[6];
		            var nome = fields2[7];
		            var cognome = fields2[8];
		            var telefono = fields2[9];*/
		            
		            $("#confirmButton").click(function() {
		            	
						//	Invio dati e invocazione servlet
						let data = {
							"ShippingAddress": id,
							"PaymentMethod": pan
						};
		            
		            	let url = "/DigitalLab/SaveOrder";
						
						$.get(url, data);
						
						
		                $(this).hide();
		                $("#checkoutBodyContainer").html('<div class="centered-content"><h2>L\'ordine è stato ricevuto!</h2><p>Riceverai i tuoi articoli nel più breve tempo possibile. Grazie di aver scelto DigitalLab.it.</p></div>');
		            });
	
		            $("#cancelButton").click(function() {
		                $("#completeOrderButton").html('<input type="button" id="confirmOrderButton" value="Conferma l\'ordine">');
		            });
		        }
		        
		        else {
		            var errorMessage = "";
		            if (!isPaymentMethodSelected) {
		                errorMessage += "Devi selezionare un metodo di pagamento. ";
		            }
		            if (!isShippingAddressSelected) {
		                errorMessage += "Devi selezionare un indirizzo di spedizione. ";
		            }
		            alert(errorMessage.trim());
		            return;
		        }
		    });
		    
		});
	</script>
	
	<%@ include file="../footer.jsp" %>

</body>
</html>