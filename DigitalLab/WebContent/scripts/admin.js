/** 
	Questa funzione utilizza AJAX per
	effetturare una chiamata asincrona
	alla servlet UpdatePersonalDataServlet
	e aggiornare i dati della pagina
*/
$(document).ready(function()
{	
	$.ajaxSetup({timeout: 10000});
	$("#personalUpdateButton").click(updatePersonalData);	
	
	function updatePersonalData()
	{
		let adminName = $("#adminNameInput").val();
		let adminSurname = $("#adminSurnameInput").val();
		let adminUsername = $("#adminUsernameInput").val();
		let adminPassword = $("#adminPasswordInput").val();
		
		$.ajax({
	      url: '/DigitalLab/UpdatePersonalDataServlet',
	      method: 'POST',
	      data: {
			"adminName": adminName,
			"adminSurname": adminSurname,
			"adminUsername": adminUsername,
			"adminPassword": adminPassword
	      },
	      success: function(data) {
			let entireName = data.adminName +" "+ data.adminSurname;
			$("#leftAdminData").html(entireName);
			
			$("#adminNameInput").val(data.adminName);
			$("#adminSurnameInput").val(data.adminSurname);
			$("#adminUsernameInput").val(data.adminUsername);
			$("#adminPasswordInput").val(data.adminPassword);
			
			$("#feedbackMessage").html("Cambiamenti salvati con successo");
	        $("#feedbackMessage").css({"color" : "green"});
	      },
	      error: function(xhr, status, error) {
	        $("#feedbackMessage").html("Impossibile salvare i cambiamenti");
	        $("#feedbackMessage").css({"color" : "red"});
	      }
	    });
	}
});

/** 
	Questa funzione utilizza AJAX per
	effetturare una chiamata asincrona
	alla servlet AdminProductControlServlet
	e aggiornare i dati della pagina
*/
$(document).ready(function()
{
	$.ajaxSetup({timeout: 10000});
	$("#loadProductDataButton").click(loadProductData);
	
	function loadProductData()
	{
		let productCode = $("#productCodeInput").val();
		
		$.ajax({
			url: '/DigitalLab/AdminProductControlServlet?action=select',
			method: 'GET',
			data: {"code": productCode},
			success: function(data)
			{
				$("#updateProductCodeInput").val(data.code);
				$("#updateProductBrandInput").val(data.brand);
				$("#updateProductModelInput").val(data.model);
				$("#updateProductCategoryInput").val(data.category);
				$("#updateProductPriceInput").val(data.price);
				$("#updateProductQuantityInput").val(data.quantity);
				$("#updateProductDescriptionInput").val(data.description);
			},
			error: function(xhr, status, error)
			{
				$("#updateMessage").html("Impossibile caricare le informazioni sul prodotto");
				$("#updateMessage").css({"color" : "red"});
			}
		})
	}
});

/** 
	Questa funzione utilizza AJAX per
	effetturare una chiamata asincrona
	alla servlet AdminProductControlServlet
	e aggiornare i dati della pagina
*/
$(document).ready(function()
{
	$.ajaxSetup({timeout: 10000});
	$("#updateProductDataButton").click(updateProductData);
	
	function updateProductData()
	{
		let productCode = $("#updateProductCodeInput").val();
		let productBrand = $("#updateProductBrandInput").val();
		let productModel = $("#updateProductModelInput").val();
		let productCategory = $("#updateProductCategoryInput").val();
		let productPrice = $("#updateProductPriceInput").val();
		let productQuantity = $("#updateProductQuantityInput").val();
		let productDescription = $("#updateProductDescriptionInput").val();

		$.ajax({
			url: '/DigitalLab/AdminProductControlServlet?action=update',
			method: 'GET',
			data: {
				"code": productCode,
				"brand": productBrand,
				"model": productModel,
				"category": productCategory,
				"price": productPrice,
				"quantity": productQuantity,
				"description": productDescription
			},
			success: function(data)
			{
				if (data.brand !== 'undefined')
					$("#updateProductBrandInput").val(data.brand);
				if (data.model !== 'undefined')
					$("#updateProductModelInput").val(data.model);
				if (data.category !== 'undefined')
					$("#updateProductCategoryInput").val(data.category);
				if (data.description !== 'undefined')
					$("#updateProductDescriptionInput").val(data.description);
				if (data.price !== 'undefined')
					$("#updateProductPriceInput").val(data.price);
				if (data.quantity !== 'undefined')
					$("#updateProductQuantityInput").val(data.quantity);
					
				$("#updateMessage").html("Prodotto modificato correttamente");
				$("#updateMessage").css({"color" : "green"});
			},
			error: function(xhr, status, error)
			{
				$("#updateMessage").html("Impossibile modificare il prodotto");
				$("#updateMessage").css({"color" : "red"});
			}
		})
	}
});

/** 
	Questa funzione utilizza AJAX per
	effetturare una chiamata asincrona
	alla servlet AdminProductControlServlet
	e aggiornare i dati della pagina
*/
$(document).ready(function()
{
	$.ajaxSetup({timeout: 10000});
	$("#deleteProductButton").click(deleteProduct);
	
	function deleteProduct()
	{
		let productCode = $("#deleteProductCodeInput").val();
		let url = '/DigitalLab/AdminProductControlServlet?action=delete';
		$.get(url, {"code": productCode});
		$("#deleteProductForm")[0].reset();
		
		$.ajax({
			url: '/DigitalLab/AdminProductControlServlet?action=delete',
			method: 'GET',
			data: {"code": productCode},
			success: function(data)
			{
				$("#deleteProductForm")[0].reset();
				$("#deleteMessage").html("Prodotto eliminato con successo");
				$("#deleteMessage").css({"color" : "red"});
			},
			error: function(xhr, status, error)
			{
				$("#deleteProductForm")[0].reset();
				$("#deleteMessage").html("Impossibile eliminare il prodotto");
				$("#deleteMessage").css({"color" : "red"});
			}
		})
	}
});