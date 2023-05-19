package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Customer;
import it.unisa.model.PaymentMethod;
import it.unisa.utils.Constants;

public class PaymentMethodDAO 
{
	private DataSource ds = null;
	
	public PaymentMethodDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(PaymentMethod paymentMethod) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.PAYMENT_METHOD_TABLE_NAME+ " VALUES (?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, paymentMethod.getPan());
			preparedStatement.setString(2, paymentMethod.getOwner());
			preparedStatement.setString(3, paymentMethod.getCvv());
			preparedStatement.setDate(4, paymentMethod.getExpirationDate());
			preparedStatement.setString(5, paymentMethod.getCustomer().getUsername());
			
			preparedStatement.executeUpdate();
			connection.commit();
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				if (connection != null)
					connection.close();
			}						
		}
	}
	
	public synchronized boolean doDelete(String pan) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.PAYMENT_METHOD_TABLE_NAME+ " WHERE payment_method_pan = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, pan);
			
			result = preparedStatement.executeUpdate();
			connection.commit();
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				if (connection != null)
					connection.close();
			}			
		}
		
		return (result != 0);
	}
	
	public synchronized PaymentMethod doRetrieveByKey(String pan) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		PaymentMethod paymentMethod = new PaymentMethod();
		String selectSQL = "SELECT * FROM " +Constants.PAYMENT_METHOD_TABLE_NAME+ " WHERE payment_method_pan = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, pan);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			paymentMethod.setPan(rs.getString("payment_method_pan"));
			paymentMethod.setOwner(rs.getString("payment_method_owner"));
			paymentMethod.setCvv(rs.getString("payment_method_cvv"));
			paymentMethod.setExpirationDate(rs.getDate("payment_method_expiration_date"));
			
			Customer customer;
			CustomerDAO dao = new CustomerDAO(ds);
			String customerUsername = rs.getString("payment_method_customer_username");
			customer = dao.doRetrieveByKey(customerUsername);
			
			paymentMethod.setCustomer(customer);
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				if (connection != null)
					connection.close();
			}			
		}
		
		return paymentMethod;
	}
	
	public synchronized Collection<PaymentMethod> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<PaymentMethod> paymentMethods = new LinkedList<PaymentMethod>();
		String selectSQL = "SELECT * FROM " +Constants.PAYMENT_METHOD_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				PaymentMethod paymentMethod = new PaymentMethod();
				paymentMethod.setPan(rs.getString("payment_method_pan"));
				paymentMethod.setOwner(rs.getString("payment_method_owner"));
				paymentMethod.setCvv(rs.getString("payment_method_cvv"));
				paymentMethod.setExpirationDate(rs.getDate("payment_method_expiration_date"));
				
				Customer customer;
				CustomerDAO dao = new CustomerDAO(ds);
				String customerUsername = rs.getString("payment_method_customer_username");
				customer = dao.doRetrieveByKey(customerUsername);
				
				paymentMethod.setCustomer(customer);
				
				paymentMethods.add(paymentMethod);
			}
		}
		finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}
			finally
			{
				if (connection != null)
					connection.close();
			}			
		}
		
		return paymentMethods;
	}
}
