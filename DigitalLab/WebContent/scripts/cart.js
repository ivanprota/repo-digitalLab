function setQuantity(element, productCode)
{
	$.ajaxSetup({timeout: 10000});
	
	let quantity = element.value;
	
	$.ajax({
		url: '/DigitalLab/Cart',
		method: 'GET',
		data: {
			"action": "updateQuantity",
			"productCode": productCode,
			"quantity": quantity
		},
		success: function(data) {
			$("#totalParagraph").html("Totale: "+ data.formattedPrice +"&euro;");
		}
	});
}