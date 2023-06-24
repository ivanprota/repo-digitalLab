/** 
	Questa funzione utilizza AJAX per
	effetturare una chiamata asincrona
	alla servlet UpdatePersonalDataServlet
	e aggiornare i dati della pagina
*/
$(document).ready(function()
{
	$.ajaxSetup({timeot: 10000});
	$("#uploadCustomerDataButton").click(uploadCustomerData);
	
	function uploadCustomerData()
	{
		let customerName = $("#updateCustomerNameInput").val();
		let customerSurname = $("#updateCustomerSurnameInput").val();
		let customerPhone = $("#updateCustomerPhoneInput").val();
		let customerEmail = $("#updateCustomerEmailInput").val();
		let customerUsername = $("#updateCustomerUsernameInput").val();
		let customerPassword = $("#updateCustomerPasswordInput").val();
		
		let url = '/DigitalLab/UpdatePersonalDataServlet';
		let data =
		{
			"customerName" : customerName,
			"customerSurname": customerSurname,
			"customerPhone": customerPhone,
			"customerEmail": customerEmail,
			"customerUsername": customerUsername,
			"customerPassword": customerPassword
		};
		
		$.post(url, data, function(data)
		{
			let entireName = data.name+ " " +data.surname;
			$("#leftCustomerData").html(entireName);
			$("#leftCustomerEmail").html(data.email);
			
			$("#updateCustomerNameInput").val(data.name);
			$("#updateCustomerSurnameInput").val(data.surname);
			$("#updateCustomerPhoneInput").val(data.phone);
			$("#updateCustomerEmailInput").val(data.email);
			$("#updateCustomerUsernameInput").val(data.username);
			$("#updateCustomerPasswordInput").val(data.password);	
		});
	}
});