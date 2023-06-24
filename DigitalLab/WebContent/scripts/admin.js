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
		
		let url = '/DigitalLab/UpdatePersonalDataServlet';
		let data = 
		{
			"adminName": adminName,
			"adminSurname": adminSurname,
			"adminUsername": adminUsername,
			"adminPassword": adminPassword
		};
		
		$.post(url, data, function(data)
		{
			let entireName = data.adminName +" "+ data.adminSurname;
			$("#leftAdminData").html(entireName);
			
			$("#adminNameInput").val(data.adminName);
			$("#adminSurnameInput").val(data.adminSurname);
			$("#adminUsernameInput").val(data.adminUsername);
			$("#adminPasswordInput").val(data.adminPassword);
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
		let url = '/DigitalLab/AdminProductControlServlet?action=select';
		$.get(url, {"code": productCode}, function(data)
		{
			$("#updateProductCodeInput").val(data.code);
			$("#updateProductBrandInput").val(data.brand);
			$("#updateProductModelInput").val(data.model);
			$("#updateProductCategoryInput").val(data.category);
			$("#updateProductPriceInput").val(data.price);
			$("#updateProductQuantityInput").val(data.quantity);
			$("#updateProductDescriptionInput").val(data.description);
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
		
		let url = '/DigitalLab/AdminProductControlServlet?action=update';
		let data =
		{
			"code": productCode,
			"brand": productBrand,
			"model": productModel,
			"category": productCategory,
			"price": productPrice,
			"quantity": productQuantity,
			"description": productDescription
		};
		
		$.get(url, data, function(data){
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
	$("#deleteProductButton").click(deleteProduct);
	
	function deleteProduct()
	{
		let productCode = $("#deleteProductCodeInput").val();
		let url = '/DigitalLab/AdminProductControlServlet?action=delete';
		$.get(url, {"code": productCode});
		$("#deleteProductForm")[0].reset();
	}
});