<%@page import="it.unisa.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.Customer, 
    it.unisa.model.ShippingAddress, 
    it.unisa.model.PaymentMethod,
    it.unisa.model.Product, 
    java.util.Collection, 
    java.util.Iterator"%>
    
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
	Collection<?> orders = (Collection<?>) session.getAttribute("orders");
	if (orders == null)
	{
		response.sendRedirect(request.getContextPath() + "/LoadCustomerDataServlet");
		return;
	}
	Collection<?> orderProducts = (Collection<?>) session.getAttribute("orderProducts");
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
					<form action="javascript:void(0)" method="POST">
						<div id="nameSurnameContainer">
							<div class="personalData">
								<label for="name">Nome:</label>
								<input type="text" id="updateCustomerNameInput" name="name" value="<%= customer.getName()%>">
							</div>
							<div class="personalData">
								<label for="surname">Cognome:</label>
								<input type="text" id="updateCustomerSurnameInput" name="surname" value="<%= customer.getSurname()%>">
							</div>
						</div>
						<div class="personalData">
							<label for="phone">Telefono:</label>
							<%
								String phone = "";
								if (customer.getPhone() != null)
									phone += customer.getPhone();
							%>
							<input type="tel" id="updateCustomerPhoneInput" name="phone" value="<%= phone%>">
						</div>
						<div class="personalData">
							<label for="email">E-mail:</label>
							<input type="text" id="updateCustomerEmailInput" name="email" value="<%= customer.getEmail()%>">
						</div>
						<div class="personalData">
							<label for="username">Username:</label>
							<input type="text" id="updateCustomerUsernameInput" name="username" value="<%= customer.getUsername()%>">
						</div>
						<div class="personalData">
							<label for="password">Password:</label>
							<input type="password" id="updateCustomerPasswordInput" name="password" value="<%= customer.getPassword()%>">
						</div>
						<button type="submit" id="uploadCustomerDataButton">Salva cambiamenti</button>
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
								<a  id="deleteShippingAddressAnchor" href="<%= request.getContextPath()%>/DeleteCustomerDataServlet?shippingAddressId=<%= shippingAddress.getId()%>">
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
						<form action="<%= request.getContextPath()%>/AddCustomerDataServlet?shippingAddress=true" method="POST">
							<div class="shippingAddressInput">
								<label for="street">Via:</label>
								<input type="text" name="street" placeholder="Via" required>
							</div>
							<div class="shippingAddressInput">
								<label for="streetNumber">Civico:</label>
								<input type="number" name="streetNumber" placeholder="Civico" required>
							</div>
							<div class="shippingAddressInput">
								<label for="city">Paese:</label>
								<input type="text" name="city" placeholder="Paese" required>
							</div>
							<div class="shippingAddressInput">
								<label for="province">Provincia:</label>
								<input type="text" name="province" placeholder="Provincia" required>
							</div>
							<div class="shippingAddressInput">
								<label for="zip">CAP:</label>
								<input type="text" name="zip" placeholder="CAP" required>
							</div>
							<button>Salva</button>
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
								<input type="text" name="owner" placeholder="Titolare" required>
							</div>
							<div class="paymentMethodInput">
								<label for="pan">Pan:</label>
								<input type="text" name="pan" placeholder="Pan" required>
							</div>
							<div class="paymentMethodInput">
								<label for="cvv">Cvv:</label>
								<input type="text" name="cvv" placeholder="Cvv" required>
							</div>
							<div class="paymentMethodInput">
								<label for="expirationDate">Data di scadenza:</label>
								<input type="text" name="expirationDate" placeholder="Data di scadenza" required>
							</div>
							<button>Salva</button>
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
									<p><%= order.getTotalAmount()%></p>
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
							</div>
							<div class="orderBoxBody">
								<%
									Iterator<?> it2 = orderProducts.iterator();
									while(it2.hasNext())
									{
										Collection<?> products = (Collection<?>) it2.next();
										Iterator<?> it3 = products.iterator();
										while(it3.hasNext())
										{
											Product product = (Product) it3.next();
								%>
									<div class="productContainer">
										<div class="productImageContainer">
											<!-- <img src="<%= request.getContextPath()%>/GetPictureServlet?productCode=<%= product.getCode()%>"> -->
											<img src="C:/Users/IVAN/Desktop/iphone.jpeg">
										</div>
										<div class="productNameContainer">
											<p>
												<%= product.getCategory()%>
												<%= product.getBrand()%>
												<%= product.getModel()%>
											</p>
											<div class="productPriceContainer">
												<p><%= product.getPrice()%></p>										
											</div>
										</div>
									</div>
								<%
										}
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
		
		<%@ include file="../footer.jsp" %>
		
	</body>

</html>