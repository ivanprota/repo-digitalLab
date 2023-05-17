package it.unisa.model;

import java.util.Date;

public class Order 
{
	// Variabili di istanza
	private int code;
	private String status;
	private double totalAmount;
	private String paymentMethod;
	private Date paymentDate;
	private Customer customer;
	private ShippingAddress shippingAddress;
	
	
	// Costruttori
	
	public Order()
	{
		code = -1;
		status = null;
		totalAmount = -1;
		paymentDate = null;
		paymentMethod = null;
		customer = null;
	}
	
	
	// Metodi di accesso
	
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
	
	
	// Metodi di modifica
	
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
