<%@page import="it.unisa.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.Customer, 
    it.unisa.model.ShippingAddress, 
    it.unisa.model.PaymentMethod,
    it.unisa.model.Product,
    it.unisa.model.Composes,
    it.unisa.model.Picture,
    java.util.List,
    java.util.ArrayList,
    java.util.Collection,
    java.util.Collections,
    java.util.Iterator,
    java.text.DecimalFormat,
    javax.sql.DataSource,
    it.unisa.db.ComposesDAO,
    it.unisa.db.PictureDAO,
    java.sql.SQLException"%>
    
<%
	Customer customer = (Customer) session.getAttribute("customer");
	if (customer == null)
	{
		response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
		return;
	}
	
	// Carico indirizzi di spedizione, metodi di pagamento e ordini dell'utente
	Collection<?> shippingAddresses = (Collection<?>) session.getAttribute("shippingAddresses");
	if (shippingAddresses == null)
	{
		response.sendRedirect(request.getContextPath() + "/LoadCustomerDataServlet");
		return;
	}
	Collection<?> paymentMethods = (Collection<?>) session.getAttribute("paymentMethods");
	if (paymentMethods == null)
	{
		response.sendRedirect(request.getContextPath() + "/LoadCustomerDataServlet");
		return;
	}
	
	List<?> orders = (List<?>) session.getAttribute("orders");
	
	if (orders == null) {
	    response.sendRedirect(request.getContextPath() + "/LoadCustomerDataServlet");
	    return;
	} else {
	    Collections.reverse(orders);
	}
	Collection<?> orderProducts = (Collection<?>) session.getAttribute("orderProducts");
	
	String error = (String) request.getAttribute("error");
	if (error == null)
		error = "";
	
	String message = (String) request.getAttribute("message");
	if (message == null)
		message = "";
%>

<!DOCTYPE html>
<html>

	<head>

		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<title>Area Utente</title>
		
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/personal-area.css">
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.7.0.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/customer.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/common.js"></script>

	</head>

	<body>
		
		<%@ include file="../header.jsp" %>
		
		<div id="personalAreaContainer">
		
			<!-- Inizio sezione di sinistra -->
			<div id="personalAreaLeftContainer">
				<div id="personalDataLeftContainer">
					<div id="personalDataLeftImageContainer">
						<img src="<%= request.getContextPath()%>/imgs/user.png">
					</div>
					<h1 id="leftCustomerData">
						<%= customer.getName()%> <%= customer.getSurname()%>
					</h1>
					<p id="leftCustomerEmail">
						<%= customer.getEmail()%>
					</p>
				</div>
				<div id="personalSectionContainer">
					<ul>
						<li>
							<a onmouseover="changeBackground(this)" onmouseout="resetBackground(this)" onclick="setProfileSectionOnScreen()">
								Profilo
							</a>
						</li>
						<li>
							<a onmouseover="changeBackground(this)" onmouseout="resetBackground(this)" onclick="setMyOrdersOnScreen()">
								I miei ordini
							</a>
						</li>
						<li>
							<a href="<%= request.getContextPath()%>/LogoutServlet" onmouseover="changeBackground(this)" onmouseout="resetBackground(this)">
								Esci
							</a>
						</li>
					</ul>
				</div>
			</div>
			<!-- Fine sezione di sinistra -->
			
			<!-- Inizio sezione di destra -->
			<div id="personalAreaRightContainer">
				<!-- Inizio dati personali -->
				<div id="personalDataRightContainer">
					<h1>Profilo</h1>
					<p id="feedbackParagraph"></p>
					<p id="errorMessage"><%= error%></p>
					<p id="confirmMessage"><%= message%></p>
					<form action="javascript:void(0)" method="POST">
						<div id="nameSurnameContainer">
							<div class="personalData">
								<label for="name">Nome:</label>
								<input type="text" id="updateCustomerNameInput" name="name" value="<%= customer.getName()%>" maxlength="20">
							</div>
							<div class="personalData">
								<label for="surname">Cognome:</label>
								<input type="text" id="updateCustomerSurnameInput" name="surname" value="<%= customer.getSurname()%>" maxlength="20">
							</div>
						</div>
						<div class="personalData">
							<label for="phone">Telefono:</label>
							<%
								String phone = "";
								if (customer.getPhone() != null)
									phone += customer.getPhone();
							%>
							<input type="tel" id="updateCustomerPhoneInput" name="phone" value="<%= phone%>" maxlength="10" oninput="checkPhone(this, 'errorMessagePhone')">
							<p id="errorMessagePhone"></p>
						</div>
						<div class="personalData">
							<label for="email">E-mail:</label>
							<input type="text" id="updateCustomerEmailInput" name="email" value="<%= customer.getEmail()%>" maxlength="40" oninput="checkEmail(this, 'errorMessageEmail', 'red')">
							<p id="errorMessageEmail"></p>
						</div>
						<div class="personalData">
							<label for="username">Username:</label>
							<input type="text" id="updateCustomerUsernameInput" name="username" value="<%= customer.getUsername()%>" maxlength="20" oninput="checkUsername(this, 'errorMessageUsername', 'red')">
							<p id="errorMessageUsername"></p>
						</div>
						<div class="personalData">
							<label for="password">Password:</label>
							<input type="password" id="updateCustomerPasswordInput" name="password" value="<%= customer.getPassword()%>" maxlength="20">
						</div>
						<div id="customerDataButtonContainer">
							<button type="submit" id="uploadCustomerDataButton">Salva cambiamenti</button>
						</div>
					</form>
				</div>
				<!-- Fine dati personali -->
				
				<!-- Inizio indirizzi di spedizione -->
				<div id="shippingAddressContainer">
					<h1>Indirizzi di Spedizione</h1>
					<div id="shippingAddressBoxContainer">
						<%
							if(shippingAddresses != null && shippingAddresses.size() != 0)
							{
								Iterator<?> it = shippingAddresses.iterator();
								while (it.hasNext())
								{
									ShippingAddress shippingAddress = (ShippingAddress) it.next();
						%>
						<div class="shippingAddressBox">
							<h1>Indirizzo:</h1>
							<p><%= customer.getName()%> <%= customer.getSurname()%>,</p>
							<p><%= shippingAddress.getStreet()%>, <%= shippingAddress.getStreetNumber()%>,</p>
							<p><%= shippingAddress.getCity()%>,</p>
							<p><%= shippingAddress.getProvince()%>,</p>
							<p><%= shippingAddress.getZip()%></p>
							<div class="shippingAddressBottomBox">
								<a id="deleteShippingAddressAnchor" href="<%= request.getContextPath()%>/DeleteCustomerDataServlet?shippingAddressId=<%= shippingAddress.getId()%>">
									Elimina
								</a>
							</div>
						</div>
						<%		}
							}
						%>
						<div id="shippingAddressAddBox">
							<a onclick="shippingAddressFormAppear()">
								Aggiungi
							</a>
						</div>
					</div>
					<!-- Inizio form nuovo indirizzo -->
					<div id="shippingAddressForm">
						<h1>Inserisci il nuovo indirizzo</h1>
						<form action="<%= request.getContextPath()%>/AddCustomerDataServlet" method="POST">
							<input type="text" name="shippingAddress" value="true" hidden = "hidden">
							<div class="shippingAddressInput">
								<label for="street">Via:</label>
								<input type="text" name="street" placeholder="Via" required maxlength="20">
							</div>
							<div class="shippingAddressInput">
								<label for="streetNumber">Civico:</label>
								<input type="number" name="streetNumber" placeholder="Civico" required>
							</div>
							<div class="shippingAddressInput">
								<label for="city">Paese:</label>
								<input type="text" name="city" placeholder="Paese" required maxlength="20">
							</div>
							<div class="shippingAddressInput">
								<label for="province">Provincia:</label>
								<input type="text" name="province" placeholder="Provincia" required maxlength="15">
							</div>
							<div class="shippingAddressInput">
								<label for="zip">CAP:</label>
								<input type="text" name="zip" placeholder="CAP" required maxlength="10">
							</div>
							<div id="shippingAddressAddButtonContainer">
								<button>Salva</button>
							</div>
						</form>
					</div>
				</div>
				<!-- Fine indirizzi di spedizione -->
				<!-- Inizio metodi di pagamento -->
				<div id="paymentMethodContainer">
					<h1>Metodi di pagamento</h1>
					<div id="paymentMethodBoxConatiner">
						<%
							if (paymentMethods != null && paymentMethods.size() != 0)
							{
								Iterator<?> it = paymentMethods.iterator();
								while (it.hasNext())
								{
									PaymentMethod paymentMethod = (PaymentMethod) it.next();
						%>
						<div class="paymentMethodBox">
							<h1>Metodo di Pagamento:</h1>
							<p><%= paymentMethod.getOwner()%></p>
							<p>Pan: <%= paymentMethod.getPan()%></p>
							<p>Scadenza: <%= paymentMethod.getExpirationDate()%></p>
							<div id="paymentMethodBottomBox">
								<a href="<%= request.getContextPath()%>/DeleteCustomerDataServlet?paymentMethodPan=<%= paymentMethod.getPan()%>">
									Elimina
								</a>
							</div>
						</div>
						<%
								}
							}
						%>
						<div id="paymentMethodAddBox">
							<a onclick="paymentMethodFormAppear()">
								Aggiungi
							</a>
						</div>
					</div>
					<!-- Inizio form nuovo metodo di pagamento -->
					<div id="paymentMethodForm">
						<h1>Inserisci il nuovo metodo di pagamento</h1>
						<form action="<%= request.getContextPath()%>/AddCustomerDataServlet?paymentMethod=true" method="POST">
							<div class="paymentMethodInput">
								<label for="owner">Titolare:</label>
								<input type="text" name="owner" placeholder="Titolare" required maxlength="20">
							</div>
							<div class="paymentMethodInput">
								<label for="pan">Pan:</label>
								<input type="text" name="pan" placeholder="Pan" required maxlength="16">
							</div>
							<div class="paymentMethodInput">
								<label for="cvv">Cvv:</label>
								<input type="text" name="cvv" placeholder="Cvv" required maxlength="3">
							</div>
							<div class="paymentMethodInput">
								<label for="expirationDate">Data di scadenza:</label>
								<input type="text" name="expirationDate" placeholder="Data di scadenza" required>
							</div>
							<div id="paymentMethodFormButtonContainer">
								<button>Salva</button>
							</div>
						</form>
					</div>
					<!-- Fine form nuovo metodo di pagamento -->
				</div>
				<!-- Fine metodi di pagamento -->
				
				<!-- Inizio ordini -->
				<div id="orderContainer">
					<h1>I miei ordini</h1>
					<div id="orderBoxContainer">
						<%
							if (orders != null && orders.size() != 0)
							{
								Iterator<?> it = orders.iterator();
								while(it.hasNext())
								{
									Order order = (Order) it.next();
									ShippingAddress orderShippingAddress = order.getShippingAddress();
									PaymentMethod orderPaymentMethod = order.getPaymentMethod();
								%>
									<div class="orderBox">
										<div class="orderBoxHeader">
											<div class="orderBoxHeaderPaymentDate">
												<h1>Ordine effettuato il:</h1>
												<p><%= order.getPaymentDate()%></p>
											</div>
											<div class="orderBoxHeaderTotalAmount">
												<h1>Totale:</h1>
												<% DecimalFormat decimalFormat = new DecimalFormat("#0.00");
												String formattedPrice = decimalFormat.format(order.getTotalAmount());%>
												<p><%= formattedPrice %> &euro;</p>
											</div>
											<div class="orderBoxHeaderStatus">
												<h1>Stato:</h1>
												<p><%= order.getStatus()%></p>
											</div>
											<div class="orderBoxHeaderShippingAddress">
												<h1>Invia a:</h1>
												<p>
													<%= orderShippingAddress.getCustomer().getName()%>
													<%= orderShippingAddress.getCustomer().getSurname()%>,
													<%= orderShippingAddress.getStreet()%>
													<%= orderShippingAddress.getStreetNumber()%>,
													<%= orderShippingAddress.getCity()%>,
													<%= orderShippingAddress.getProvince()%>,
													<%= orderShippingAddress.getZip()%>
												</p>
											</div>
											<div class="orderBoxHeaderPaymentMethod">
												<h1>Pagamento:</h1>
												<p><%= orderPaymentMethod.getPan()%></p>
											</div>
											<div class="orderBoxHeaderDetails">
												<a id="<%=order.getCode()%>" onclick="funzione('<%= order.getCode() %>')">Dettagli</a>
											</div>
										</div>
											<div id="orderBoxDetailsView<%= order.getCode() %>">
											<%
												DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
												Collection<Product> collection = null;
												ComposesDAO composesDAO = new ComposesDAO(ds);
												PictureDAO pictureDAO = new PictureDAO(ds);
												Composes composes = null;
												Picture picture = null;
												try
												{
													collection = composesDAO.doRetrieveProductByOrderCode(order.getCode());
												}
												catch (SQLException e)
												{
													System.out.println(e);
												}
												
												Iterator<Product> it2 = collection.iterator();
												while (it2.hasNext())
												{
													Product product = (Product) it2.next();
													try
													{
														composes = composesDAO.doRetrieveByKey(product.getCode(), order.getCode());
														picture = pictureDAO.doRetrieveByKey(product.getCode());
													}
													catch (SQLException e)
													{
														System.out.println(e);
													}
											%>
							                    <div class="cartItem">
								                	<div class="cartItemImage">
								                  		<img src="<%= request.getContextPath()%>/imgs/products/<%= picture.getImageFileName()%>">
								                    </div>
								                    <div class="cartItemDetails">
								                        <%
													    	DecimalFormat decimalFormat2 = new DecimalFormat("#0.00");
													    	String formattedPrice2 = decimalFormat.format(product.getPrice());
														%>
														
								                            <h3>
								                            	<a href="<%=request.getContextPath()%>/common/product.jsp?productCode=<%= product.getCode()%>">
								                            	<%=product.getBrand() + " " + product.getModel()%>
								                        		</a>
								                        	</h3>
								                            <p>Prezzo: <%= formattedPrice2 %> &euro; Quantità: <%= composes.getQuantity()%></p>
								                    </div>                       
			                    				</div>	
			                    				<%
													}
			                    				%>							
											</div>
									</div>
								<%
								}
							}
						%>
					</div>
				</div>
				<!-- Fine ordini -->
			</div>
			<!-- Fine sezione di destra -->
			
		</div>
		
		<script>
			  function funzione(orderCode) {
			    var orderBox = $('#' + orderCode).closest('.orderBox');
			    var orderDetailsView = orderBox.find('.cartItem');
			    
			    orderDetailsView.slideToggle();
			  }
		</script>
		
		<%@ include file="../footer.jsp" %>
		
	</body>

</html>