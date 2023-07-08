package it.unisa.model;

public class Contains 
{
	// Instance variables
	private Product product;
	private ShoppingCart shoppingCart;
	private int quantity;
	
	
	// Constructors
	
	public Contains()
	{
		product = null;
		shoppingCart = null;
		quantity = -1;
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
	
	public int getQuantity()
	{
		return quantity;
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
	
	public void setQuantity(int newQuantity)
	{
		quantity = newQuantity;
	}
	
	public void incrementQuantity()
	{
		quantity++;
	}
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +product.toString()+
			   ", " +shoppingCart.toString()+
			   ", " +quantity;
	}
}
