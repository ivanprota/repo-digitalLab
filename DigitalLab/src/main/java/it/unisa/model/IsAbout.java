package it.unisa.model;

public class IsAbout 
{
	// Variabili di istanza
	private Order order;
	private Product product;
	
	
	// Costruttori
	
	public IsAbout()
	{
		order = null;
		product = null;
	}
	
	
	// Metodi di accesso
	
	public Order gerOrder()
	{
		return order;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	
	// Metodi di modifica
	
	public void setOrder(Order newOrder)
	{
		order = newOrder;
	}
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
}
