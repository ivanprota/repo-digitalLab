package it.unisa.model;

public class ShoppingCart 
{
	// Instance variables
	private Customer customer;
	private int size;
	
	
	// Constructors
	
	public ShoppingCart()
	{
		customer = null;
		size = -1;
	}
	
	
	// Get methods
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	// Set methods
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	public void setSize(int newSize)
	{
		size = newSize;
	}
}
