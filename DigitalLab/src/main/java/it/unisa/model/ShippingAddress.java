package it.unisa.model;

public class ShippingAddress 
{
	// Instance variables
	private int id;
	private String province;
	private String street;
	private String city;
	private String zip;
	private Customer customer;
	
	
	// Constructors
	
	public ShippingAddress()
	{
		customer = null;
		id = -1;
		province = null;
		street = null;
		city = null;
		zip = null;
	}
	
	
	// Get methods
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getProvince()
	{
		return province;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getZip()
	{
		return zip;
	}
	
	
	// Set methods
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	public void setId(int newId)
	{
		id = newId;
	}
	
	public void setProvince(String newProvince)
	{
		province = newProvince;
	}
	
	public void setStreet(String newStreet)
	{
		street = newStreet;
	}
	
	public void setCity(String newCity)
	{
		city = newCity;
	}
	
	public void setZip(String newZip)
	{
		zip = newZip;
	}
}
