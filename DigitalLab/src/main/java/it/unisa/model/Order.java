package it.unisa.model;

import java.sql.Date;

public class Order 
{
	// Instance variables
	private int code;
	private String status;
	private double totalAmount;
	private Date paymentDate;
	private Customer customer;
	private ShippingAddress shippingAddress;
	private PaymentMethod paymentMethod;
	
	
	// Constructors
	
	public Order()
	{
		code = -1;
		status = null;
		totalAmount = -1;
		paymentDate = null;
		customer = null;
		paymentMethod = null;
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
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	public ShippingAddress getShippingAddress()
	{
		return shippingAddress;
	}
	
	public PaymentMethod getPaymentMethod()
	{
		return paymentMethod;
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
	
	public void setCustomer(Customer newCustomer)
	{
		customer = newCustomer;
	}
	
	public void setShippingAddress(ShippingAddress newShippingAddress)
	{
		shippingAddress = newShippingAddress;
	}
	
	public void setPaymentMethod(PaymentMethod newPaymentMethod)
	{
		paymentMethod = newPaymentMethod;
	}
}
