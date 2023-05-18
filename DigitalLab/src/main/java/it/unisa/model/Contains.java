package it.unisa.model;

public class Contains 
{
	// Instance variables
	private Product product;
	private ShoppingCart shoppingCart;
	
	
	// Constructors
	
	public Contains()
	{
		product = null;
		shoppingCart = null;
	}
	
	
	// Get methods
	
	public Product getProduct()
	{
		return product;
	}
	
	public ShoppingCart getShoppingCart()
	{
		return shoppingCart;
	}
	
	
	// Set methods
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	public void setShoppingCart(ShoppingCart newShoppingCart)
	{
		shoppingCart = newShoppingCart;
	}
}
