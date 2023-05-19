package it.unisa.model;

import java.sql.Date;

public class PaymentMethod 
{
	// Instance variables
	private String pan;
	private String owner;
	private String cvv;
	private Date expirationDate;
	private Customer customer;

	
	// Constructors
	
	public PaymentMethod()
	{
		pan = null;
		owner = null;
		cvv = null;
		expirationDate = null;
		customer = null;
	}
	
	
	// Get methods
	
	public String getPan()
	{
		return pan;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public String getCvv()
	{
		return cvv;
	}
	
	public Date getExpirationDate()
	{
		return expirationDate;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	
	// Set methods
	
	public void setPan(String newPan)
	{
		pan = newPan;
	}
	
	public void setOwner(String newOwner)
	{
		owner = newOwner;
	}
	
	public void setCvv(String newCvv)
	{
		cvv = newCvv;
	}
	
	public void setExpirationDate(Date newExpirationDate)
	{
		expirationDate = newExpirationDate;
	}
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
}
