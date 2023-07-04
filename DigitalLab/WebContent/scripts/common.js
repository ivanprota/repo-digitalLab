/* Inserisce il bordo al dato item */
function borderItem(element)
{
	let item = element.firstElementChild;
	item.style.borderColor = "white";
}

/* Elimina il bordo al dato item */
function deleteBorderItem(element)
{
	let item = element.firstElementChild;
	item.style.borderColor = "";
}

/* Inserisce il bordo al dato item */
function borderItem2(element)
{
	let item = element.firstElementChild;
	item.style.borderColor = "black";
}






/* Utilizzato all'interno delle pagine utente e amministratore.
   Cambia il colore di background con uno più scuro */
function changeBackground(element)
{
	let parent = element.parentElement;
	parent.style.backgroundColor = "#d9d9d9";
}

/* Utilizzato all'interno delle pagine utente e amministratore.
   Reimposta il colore cambiato dalla funzione changeBackground */
function resetBackground(element)
{
	let parent = element.parentElement;
	parent.style.backgroundColor = "";
}




/* Utilizzato all'interno della pagina utente.
   Viene chiamata nel momento in cui un utente clicca sul tasto "Aggiungi"
   nella sezione degli indirizzi di spedizione.
   Fa comparire a schermo la form per l'inerimento dei dati */
function shippingAddressFormAppear()
{
	let form = document.getElementById("shippingAddressForm");
	form.style.display = "block";
}

/* Utilizzato all'interno della pagina utente.
   Viene chiamata nel momento in cui un utente clicca sul tasto "Aggiungi"
   nella sezione dei metodi di pagamento.
   Fa comparire a schermo la form per l'inerimento dei dati */
function paymentMethodFormAppear()
{
	let form = document.getElementById("paymentMethodForm");
	form.style.display = "block";
}




/* Utilizzato all'interno della pagina utente.
   Viene chiamata nel momento in cui un utente clicca il tasto
   corrispondente sulla sinistra della pagina.
   Fa comparire a schermo la sezione cliccata */
function setProfileSectionOnScreen()
{
	let profile = document.getElementById("personalDataRightContainer");
	let shippingAddress = document.getElementById("shippingAddressContainer");
	let paymentMethod = document.getElementById("paymentMethodContainer");
	let myOrders = document.getElementById("orderContainer");
	
	myOrders.style.display = "none";
	profile.style.display = "block";
	shippingAddress.style.display = "block";
	paymentMethod.style.display = "block";
}

/* Utilizzato all'interno della pagina utente.
   Viene chiamata nel momento in cui un utente clicca il tasto
   corrispondente sulla sinistra della pagina.
   Fa comparire a schermo la sezione cliccata */
function setMyOrdersOnScreen()
{
	let profile = document.getElementById("personalDataRightContainer");
	let shippingAddress = document.getElementById("shippingAddressContainer");
	let paymentMethod = document.getElementById("paymentMethodContainer");
	let myOrders = document.getElementById("orderContainer");
	
	myOrders.style.display = "block";
	profile.style.display = "none";
	shippingAddress.style.display = "none";
	paymentMethod.style.display = "none";
}

/* Utilizzato all'interno della pagina amministratore.
   Viene chiamata nel momento in cui un utente clicca il tasto
   corrispondente sulla sinistra della pagina.
   Fa comparire a schermo la sezione cliccata */
function setAdminProfileOnScreen()
{
	let profile = document.getElementById("personalDataRightContainer");
	let product = document.getElementById("opOnProductContainer");
	let order = document.getElementById("orderContainer");
	
	profile.style.display = "block";
	product.style.display = "none";
	order.style.display = "none";
}

/* Utilizzato all'interno della pagina amministratore.
   Viene chiamata nel momento in cui un utente clicca il tasto
   corrispondente sulla sinistra della pagina.
   Fa comparire a schermo la sezione cliccata */
function setAdminOpSectionOnScreen()
{
	let profile = document.getElementById("personalDataRightContainer");
	let product = document.getElementById("opOnProductContainer");
	let order = document.getElementById("orderContainer");
	
	profile.style.display = "none";
	product.style.display = "block";
	order.style.display = "none";
}

/* Utilizzato all'interno della pagina amministratore.
   Viene chiamata nel momento in cui un utente clicca il tasto
   corrispondente sulla sinistra della pagina.
   Fa comparire a schermo la sezione cliccata */
function setOrderViewOnScreen() 
{
	let profile = document.getElementById("personalDataRightContainer");
	let product = document.getElementById("opOnProductContainer");
	let order = document.getElementById("orderContainer");
	
	profile.style.display = "none";
	product.style.display = "none";
	order.style.display = "block";
}



/* Inserisce le immagini all'interno di un div di preview */
function imagePreview()
{
	let imgUpload = document.getElementById("uploadButton");
	let imgPreview = document.getElementById("uploadImagePreview");
	let totalFiles;
	let img;
	
	imgUpload.addEventListener("change", previewImage, false);
	
	function previewImage(event)
	{	
		totalFiles = imgUpload.files.length;
		
		for (let i=0; i<totalFiles; i++)
		{
			img = document.createElement("img");
			img.src = URL.createObjectURL(event.target.files[i]);
			img.style.width = "20%";
			img.style.marginRight = "10px";
			imgPreview.appendChild(img);
		}	
	}
}
