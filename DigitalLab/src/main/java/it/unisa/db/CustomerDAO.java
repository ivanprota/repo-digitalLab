package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Customer;
import it.unisa.utils.Constants;

public class CustomerDAO 
{
	private DataSource ds = null;
	
	public CustomerDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Customer customer) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.CUSTOMER_TABLE_NAME+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, customer.getUsername());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getPassword());
			preparedStatement.setString(4, customer.getName());
			preparedStatement.setString(5, customer.getSurname());
			preparedStatement.setString(6, customer.getPhone());
			
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
	
	public synchronized boolean doDelete(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.CUSTOMER_TABLE_NAME+ " WHERE customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);
			
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
	
	public synchronized Customer doRetrieveByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Customer customer = new Customer();
		String selectSQL = "SELECT * FROM " +Constants.CUSTOMER_TABLE_NAME+ " WHERE customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			customer.setUsername(rs.getString("customer_username"));
			customer.setEmail(rs.getString("customer_email"));
			customer.setPassword(rs.getString("customer_password"));
			customer.setName(rs.getString("customer_name"));
			customer.setSurname(rs.getString("customer_surname"));
			customer.setPhone(rs.getString("customer_phone"));
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
		
		return customer;
	}
	
	public synchronized Collection<Customer> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Customer> customers = new LinkedList<Customer>();
		String selectSQL = "SELECT * FROM " +Constants.CUSTOMER_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Customer customer = new Customer();
				customer.setUsername(rs.getString("customer_username"));
				customer.setEmail(rs.getString("customer_email"));
				customer.setPassword(rs.getString("customer_password"));
				customer.setName(rs.getString("customer_name"));
				customer.setSurname(rs.getString("customer_surname"));
				customer.setPhone(rs.getString("customer_phone"));
				customers.add(customer);
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
		
		return customers;
	}
}
