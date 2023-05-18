package it.unisa.model;

import java.util.Date;

public class Order 
{
	// Instance variables
	private int code;
	private String status;
	private double totalAmount;
	private String paymentMethod;
	private Date paymentDate;
	private Customer customer;
	private ShippingAddress shippingAddress;
	
	
	// Constructors
	
	public Order()
	{
		code = -1;
		status = null;
		totalAmount = -1;
		paymentDate = null;
		paymentMethod = null;
		customer = null;
	}
	
	
	// Get methods
	
	public int getCode()
	{
		return code;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public double getTotalAmount()
	{
		return totalAmount;
	}
	
	public Date getPaymentDate()
	{
		return paymentDate;
	}
	
	public String getPaymentMethod()
	{
		return paymentMethod;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public ShippingAddress getShippingAddress()
	{
		return shippingAddress;
	}
	
	
	// Set methods
	
	public void setCode(int newCode)
	{
		code = newCode;
	}
	
	public void setStatus(String newStatus)
	{
		status = newStatus;
	}
	
	public void setTotalAmount(double newTotalAmount)
	{
		totalAmount = newTotalAmount;
	}
	
	public void setPaymentDate(Date newPaymentDate)
	{
		paymentDate = newPaymentDate;
	}
	
	public void setPaymentMethod(String newPaymentMethod)
	{
		paymentMethod = newPaymentMethod;
	}
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	public void setShippingAddress(ShippingAddress newShippingAddress)
	{
		shippingAddress = newShippingAddress;
	}
}
