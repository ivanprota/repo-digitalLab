function setQuantity(productCode)
{
	$.ajaxSetup({timeout: 10000});
	
	let quantity = $("#inputQuantityProduct").val();
	
	$.ajax({
		url: '/DigitalLab/Cart',
		method: 'GET',
		data: {
			"action": "updateQuantity",
			"productCode": productCode,
			"quantity": quantity
		},
		success: function(data) {
			$("#totalParagraph").html(" Totale:"+ data.formattedPrice +"&euro;");
		}
	});
}