package it.unisa.model;

public class ShoppingCart 
{
	// Variabili di istanza
	private Customer customer;
	private int size;
	
	
	// Costruttori
	
	public ShoppingCart()
	{
		customer = null;
		size = -1;
	}
	
	
	// Metodi di accesso
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public int getSize()
	{
		return size;
	}
	
	
	// Metodi di modifica
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	public void setSize(int newSize)
	{
		size = newSize;
	}
}
