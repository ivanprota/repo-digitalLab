package it.unisa.model;

public class ShippingAddress 
{
	// Instance variables
	private int id;
	private String province;
	private String street;
	private String city;
	private String zip;
	private int streetNumber;
	private Customer customer;
	
	
	// Constructors
	
	public ShippingAddress()
	{
		id = -1;
		province = null;
		street = null;
		city = null;
		zip = null;
		streetNumber = -1;
		customer = null;
	}
	
	
	// Get methods
	
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
	
	public int getStreetNumber()
	{
		return streetNumber;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	
	// Set methods
	
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
	
	public void setStreetNumber(int newStreetNumber)
	{
		streetNumber = newStreetNumber;
	}
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +id+
			   ", " +province+
			   ", " +street+
			   ", " +city+
			   ", " +zip+
			   ", " +streetNumber+
			   ", " +customer.toString();
	}
}
