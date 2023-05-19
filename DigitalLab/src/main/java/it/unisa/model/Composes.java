package it.unisa.model;

public class Composes 
{
	// Instance variables
	private Order order;
	private Product product;
	
	
	// Constructors
	
	public Composes()
	{
		order = null;
		product = null;
	}
	
	
	// Get methods
	
	public Order getOrder()
	{
		return order;
	}
	
	public Product getProduct()
	{
		return product;
	}
	
	
	// Set methods
	
	public void setOrder(Order newOrder)
	{
		order = newOrder;
	}
	
	public void setProduct(Product newProduct)
	{
		product = newProduct;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +order.toString()+
			   ", " +product.toString();
	}
}
