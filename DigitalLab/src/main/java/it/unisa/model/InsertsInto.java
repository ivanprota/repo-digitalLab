package it.unisa.model;

public class InsertsInto 
{
	// Variabili di istanza
	private Product product;
	private ShoppingCart shoppingCart;
	
	
	// Costruttori
	
	public InsertsInto()
	{
		product = null;
		shoppingCart = null;
	}
	
	
	// Metodi di accesso
	
	public Product getProduct()
	{
		return product;
	}
	
	public ShoppingCart getShoppingCart()
	{
		return shoppingCart;
	}
	
	
	// Metodi di modifica
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	public void setShoppingCart(ShoppingCart newShoppingCart)
	{
		shoppingCart = newShoppingCart;
	}
}
