<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.unisa.model.Administrator,
    		it.unisa.model.Product,
    		it.unisa.model.Order,
    		java.util.Collection,
    		java.util.Iterator"%>
    
<%
	Administrator admin = (Administrator) session.getAttribute("admin");
	if (admin == null)
	{
		response.sendRedirect(request.getContextPath() + "/login-signup/login.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>

	<head>

		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/personal-area.css">
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/personal-admin-area.css">
		
		<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/common.js"></script>

		<title>Area Amministrativa</title>

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
					<h1>
						<%= admin.getName()%> <%= admin.getSurname()%>
					</h1>
					<p>
						Amministratore
					</p>
					<div id="personalSectionContainer">
					<ul>
						<li>
							<a onmouseover="changeBackground(this)" onmouseout="resetBackground(this)" onclick="setAdminProfileOnScreen()">
								Profilo
							</a>
						</li>
						<li>
							<a onmouseover="changeBackground(this)" onmouseout="resetBackground(this)" onclick="setAdminOpSectionOnScreen()">
								Prodotti
							</a>
						</li>
						<li>
							<a onmouseover="changeBackground(this)" onmouseout="resetBackground(this)" onclick="setOrderViewOnScreen()">
								Ordini
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
			</div>
			<!-- Fine sezione di sinistra -->
			
			<!-- Inizio sezione di destra -->
			<div id="personalAreaRightContainer">
				<!-- Inizio dati personali -->
				<div id="personalDataRightContainer">
					<h1>Profilo</h1>
					<form action="<%= request.getContextPath()%>/UpdatePersonalDataServlet" method="POST">
						<div class="personalData">
							<label for="name">Nome:</label>
							<input type="text" name="name" value="<%= admin.getName()%>">
						</div>
						<div class="personalData">
							<label for="surname">Cognome:</label>
							<input type="text" name="surname" value="<%= admin.getSurname()%>">
						</div>
						<div class="personalData">
							<label for="username">Username:</label>
							<input type="text" name="username" value="<%= admin.getUsername()%>">
						</div>
						<div class="personalData">
							<label for="password">Password:</label>
							<input type="password" name="password" value="<%= admin.getPassword()%>">
						</div>
						<button>Salva cambiamenti</button>						
					</form>
				</div>
				<!-- Fine dati personali -->
				
				<!-- Inizio operazione sui prodotti -->
				<div id="opOnProductContainer">
				<h1>Prodotti</h1>
					<div class="fieldsetOp">
						<fieldset>
							<legend>Aggiungi un prodotto:</legend>
							<form action="<%= request.getContextPath()%>/AdminProductControlServlet?action=add"  
							enctype="multipart/form-data" method="POST">
								<div class="productOpImageContainer">
									<label for="uploadButton">Seleziona Immagini</label>
									<input type="file" id="uploadButton" name="photos" multiple>
									<div id="uploadImagePreview"></div>
									<script>imagePreview()</script>
								</div>
								<div class="productOpData">
									<label for="code">Codice Prodotto:</label>
									<input type="number" name="code" placeholder="Codice">
								</div>
								<div class="productOpData">
									<label for="brand">Brand:</label>
									<input type="text" name="brand" placeholder="Brand">
								</div>
								<div class="productOpData">
									<label for="model">Modello:</label>
									<input type="text" name="model" placeholder="Modello">
								</div>
								<div class="productOpData">
									<label for="category">Categoria:</label>
									<select name="category">
										<option value="Tutte le categorie">Tutte le categorie</option>
										<option value="Case">Case</option>
										<option value="Schede Madri">Schede madri</option>
										<option value="CPU">CPU</option>
										<option value="GPU">GPU</option>
										<option value="RAM">RAM</option>
										<option value="Storage">Storage</option>
										<option value="Cooling">Cooling</option>
										<option value="PSU">PSU</option>
										<option value="Monitor">Monitor</option>
										<option value="Sistemi Operativi">Sistemi Operativi</option>
										<option value="Accessori">Accessori</option>
									</select>
								</div>
								<div class="productOpData">
									<label for="price">Prezzo:</label>
									<input type="number" name="price" placeholder="Prezzo">
								</div>
								<div class="productOpData">
									<label for="quantity">Quantità:</label>
									<input type="number" name="quantity" placeholder="Quantità">
								</div>
								<div class="productOpData">
									<label for="description">Descrizione:</label>
									<textarea name="description" rows="7" cols="36"></textarea>
								</div>
								<button>Aggiungi</button>
							</form>
						</fieldset>
					</div>
					<div class="fieldsetOp">
						<fieldset>
							<legend>Modifica un prodotto:</legend>
							<form id="loadForm" action="<%= request.getContextPath()%>/AdminProductControlServlet?action=select" method="POST">
								<div class="productOpData">
									<label for="code">Codice Prodotto:</label>
									<input type="number" name="code" placeholder="Codice">
								</div>
								<button id="loadProductDataButton">Carica informazioni sul prodotto</button>
							</form>
							<form action="<%= request.getContextPath()%>/AdminProductControlServlet?action=update" method="POST">
							<%
								Product product = (Product) request.getAttribute("product");
							%>
								<div class="productOpData">
								<% 
									int code;
									if (product != null)
										code = product.getCode();
									else code = -1;
								%>
									<label for="code">Codice Prodotto:</label>
									<input type="number" name="code" readonly
									<% 
										if(code == -1) 
										{
									%> 		value="" 
									<%  }
										else 
										{
									%>		value="<%= code%>" 
									<% 	}
									%>>
								</div>
								<div class="productOpData">
								<%
									String brand;
									if (product != null)
										brand = product.getBrand();
									else brand = "";
								%>
									<label for="brand">Brand:</label>
									<input type="text" name="brand" value="<%= brand%>">
								</div>
								<div class="productOpData">
								<%
									String model;
									if (product != null)
										model = product.getModel();
									else model = "";
								%>
									<label for="model">Modello:</label>
									<input type="text" name="model" value="<%= model%>">
								</div>
								<div class="productOpData">
								<%
									String category;
									if (product != null)
										category = product.getCategory();
									else category = null;
								%>
									<label for="category">Categoria:</label>
									<select id="categorySelect" name="category">
										<option value="Tutte le categorie">Tutte le categorie</option>
										<option value="Case">Case</option>
										<option value="Schede Madri">Schede madri</option>
										<option value="CPU">CPU</option>
										<option value="GPU">GPU</option>
										<option value="RAM">RAM</option>
										<option value="Storage">Storage</option>
										<option value="Cooling">Cooling</option>
										<option value="PSU">PSU</option>
										<option value="Monitor">Monitor</option>
										<option value="Sistemi Operativi">Sistemi Operativi</option>
										<option value="Accessori">Accessori</option>
									</select>
									<script>setSelectValue("<%=category%>")</script>
								</div>
								<div class="productOpData">
								<%
									double price;
									if (product != null)
										price = product.getPrice();
									else price = -1;
								%>
									<label for="price">Prezzo:</label>
									<input type="number" name="price"
									<% 
										if(price == -1) 
										{
									%> 		value="" 
									<%  }
										else 
										{
									%>		value="<%= price%>" 
									<% 	}
									%>>
								</div>
								<div class="productOpData">
								<%
									int quantity;
									if (product != null)
										quantity = product.getQuantity();
									else quantity = -1;
								%>
									<label for="quantity">Quantità:</label>
									<input type="number" name="quantity"
									<% 
										if(quantity == -1) 
										{
									%> 		value="" 
									<%  }
										else 
										{
									%>		value="<%= quantity%>" 
									<% 	}
									%>>
								</div>
								<div class="productOpData">
								<%
									String description;
									if (product != null)
										description = product.getDescription();
									else description = "";
								%>
									<label for="description">Descrizione:</label>
									<textarea name="description" rows="7" cols="36"><%= description%></textarea>
								</div>
								<button>Modifica</button>
							</form>
							<script>setAdminOpSectionOnScreen()</script>
						</fieldset>
					</div>
					<div class="fieldsetOp">
						<fieldset>
							<legend>Elimina un prodotto</legend>
							<form action="<%= request.getContextPath()%>/AdminProductControlServlet?action=delete" method="POST">
								<div class="productOpData">
									<label for="code">Codice Prodotto:</label>
									<input type="number" name="code" placeholder="Codice">
								</div>
								<button>Elimina</button>	
							</form>
						</fieldset>
					</div>
				</div>
			<!-- Fine operazioni sui prodotti -->
			
			<!-- Inizio visualizzazione ordini -->
			<div id="orderContainer">
				<div id="orderFormContainer">
					<h1>Visualizza ordini</h1>
					<p>
						Puoi visualizzare gli ordini effettuati per:
					</p>
						<ul>
							<li>Cliente</li>
							<li>Periodo</li>
							<li>Cliente e Periodo</li>
						</ul>
					<form action="<%= request.getContextPath()%>/LoadOrderServlet" method="POST">
						<div class="orderData">
							<label for="username">Username Cliente:</label>
							<input type="text" name="username" placeholder="Username cliente">
						</div>
						<div class="orderData">
							<label for="startDate">Dal:</label>
							<input type="date" name="startDate">
						</div>
						<div class="orderData">
							<label for="endDate">Al:</label>
							<input type="date" name="endDate">
						</div>
						<button>Cerca</button>
					</form>
				</div>
				<%
					Collection<?> orders = (Collection<?>) request.getAttribute("orders");
					if (orders != null)
					{
				%>
				<script>setOrderViewOnScreen()</script>
				<h1>Lista ordini:</h1>
				<div id="orderViewContainer">
				<%
						Iterator<?> it = orders.iterator();
						while (it.hasNext())
						{
							Order order = (Order) it.next();
				%>
					<div class="orderView">
						<div class="orderCode">
							<h1>Codice:</h1>
							<p><%= order.getCode()%></p>
						</div>
						<div class="orderStatus">
							<h1>Stato:</h1>
							<p><%= order.getStatus()%></p>
						</div>
						<div class="orderTotal">
							<h1>Totale:</h1>
							<p><%= order.getTotalAmount()%></p>
						</div>
						<div class="orderPaymentDate">
							<h1>Data di acquisto:</h1>
							<p><%= order.getPaymentDate()%></p>
						</div>
						<div class="orderDetails">
							<a href="">Dettagli</a>
						</div>
					</div>
				<%
						}
				%>
				</div>
				<% 
					}
				%>
			</div>
			<!-- Fine visualizzazioni ordini -->
			
			</div>
			<!-- Fine sezione di destra -->
		
		</div>
		
		<%@ include file="../footer.jsp" %>

	</body>

</html>