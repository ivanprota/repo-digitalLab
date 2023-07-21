$(document).ready(function()
{
	$("#configurationButton").click(addItemsToCartFunction);
	
	function addItemsToCartFunction()
	{
		let username = $("#inputCustomerUsername").val();
		let caseValue;
		
		$("input[name='radioCase']").each(function() {
			if ($(this).is(":checked")) {
		    	caseValue = $(this).val();
				return false;
			}
		});
		let cpuValue = $("#cpuSelect").val();
		let motherboardValue = $("#motherboardSelect").val();
		let ramValue = $("#ramSelect").val();
		let gpuValue = $("#gpuSelect").val();
		let storageValue = $("#storageSelect").val();
		let psuValue = $("#psuSelect").val();
		let coolingValue = $("#coolingSelect").val();
		let monitorValue = $("#monitorSelect").val();
		let osValue = $("#osSelect").val();
		let extraValue = $("#extraSelect").val();
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : caseValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : cpuValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : motherboardValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : ramValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : gpuValue, "customerUsername" : username}
		})

		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : storageValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : psuValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : coolingValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : monitorValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : osValue, "customerUsername" : username}
		})
		
		$.ajax({
			url: '/DigitalLab/addItemToCart',
			method: "POST",
			data: {"productCode" : extraValue, "customerUsername" : username}
		})
		
		alert("Prodotto aggiunto al carrello con successo!");
	}
});