
/*$(document).ready(function() {
	$("#addToCartButton").click(function() {
		var productCode = $(this).data("product-code");
	    addToCart(productCode);
	 });
});
	  function addToCart(productCode) {
	    $.ajax({
	      url: "<%= request.getContextPath()%>/AddItemToCart",
	      method: "POST",
	      data: { productCode: productCode },
	      success: function(response) {
	        alert("Prodotto aggiunto al carrello!");
	      },
	      error: function(xhr, status, error) {
	        console.error("Errore durante l'aggiunta al carrello:", error);
	      }
	    });
	  }
*/
	  
function setImage(fileName)
{
	document.getElementById("productImage").src = fileName;
}