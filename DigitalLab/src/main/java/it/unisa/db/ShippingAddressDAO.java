package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Customer;
import it.unisa.model.ShippingAddress;
import it.unisa.utils.Constants;

public class ShippingAddressDAO 
{
	private DataSource ds = null;
	
	public ShippingAddressDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(ShippingAddress shippingAddress) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.SHIPPING_ADDRESS_TABLE_NAME+
				"(shipping_address_province, shipping_address_street, shipping_address_city, shipping_address_zip, "
				+ "shipping_address_street_number, shipping_address_customer_username) VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, shippingAddress.getProvince());
			preparedStatement.setString(2, shippingAddress.getStreet());
			preparedStatement.setString(3, shippingAddress.getCity());
			preparedStatement.setString(4, shippingAddress.getZip());
			preparedStatement.setInt(5, shippingAddress.getStreetNumber());
			preparedStatement.setString(6, shippingAddress.getCustomer().getUsername());
			
			preparedStatement.executeUpdate();
			//connection.commit();
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
	
	public synchronized boolean doDelete(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.SHIPPING_ADDRESS_TABLE_NAME+ " WHERE shipping_address_id = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeUpdate();
			//connection.commit();
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
	
	public synchronized ShippingAddress doRetrieveByKey(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ShippingAddress shippingAddress = new ShippingAddress();
		String selectSQL = "SELECT * FROM " +Constants.SHIPPING_ADDRESS_TABLE_NAME+ " WHERE shipping_address_id = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			shippingAddress.setId(rs.getInt("shipping_address_id"));
			shippingAddress.setProvince(rs.getString("shipping_address_province"));
			shippingAddress.setStreet(rs.getString("shipping_address_street"));
			shippingAddress.setCity(rs.getString("shipping_address_city"));
			shippingAddress.setZip(rs.getString("shipping_address_zip"));
			shippingAddress.setStreetNumber(rs.getInt("shipping_address_street_number"));
			
			Customer customer;
			CustomerDAO dao = new CustomerDAO(ds);
			String customerUsername = rs.getString("shipping_address_customer_username");
			customer = dao.doRetrieveByKey(customerUsername);
			
			shippingAddress.setCustomer(customer);
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
		
		return shippingAddress;
	}
	
	public synchronized Collection<ShippingAddress> doRetrieveByCustomerUsername(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ShippingAddress> shippingAddresses = new LinkedList<ShippingAddress>();
		String selectSQL = "SELECT * FROM " +Constants.SHIPPING_ADDRESS_TABLE_NAME+ " WHERE shipping_address_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				ShippingAddress shippingAddress = new ShippingAddress();
				shippingAddress.setId(rs.getInt("shipping_address_id"));
				shippingAddress.setProvince(rs.getString("shipping_address_province"));
				shippingAddress.setStreet(rs.getString("shipping_address_street"));
				shippingAddress.setCity(rs.getString("shipping_address_city"));
				shippingAddress.setZip(rs.getString("shipping_address_zip"));
				shippingAddress.setStreetNumber(rs.getInt("shipping_address_street_number"));
				
				Customer customer;
				CustomerDAO dao = new CustomerDAO(ds);
				String customerUsername = rs.getString("shipping_address_customer_username");
				customer = dao.doRetrieveByKey(customerUsername);
				
				shippingAddress.setCustomer(customer);
				
				shippingAddresses.add(shippingAddress);
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
		
		return shippingAddresses;
	}
	
	public synchronized Collection<ShippingAddress> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ShippingAddress> shippingAddresses = new LinkedList<ShippingAddress>();
		String selectSQL = "SELECT * FROM " +Constants.SHIPPING_ADDRESS_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				ShippingAddress shippingAddress = new ShippingAddress();
				shippingAddress.setId(rs.getInt("shipping_address_id"));
				shippingAddress.setProvince(rs.getString("shipping_address_province"));
				shippingAddress.setStreet(rs.getString("shipping_address_street"));
				shippingAddress.setCity(rs.getString("shipping_address_city"));
				shippingAddress.setZip(rs.getString("shipping_address_zip"));
				shippingAddress.setStreetNumber(rs.getInt("shipping_address_street_number"));
				
				Customer customer;
				CustomerDAO dao = new CustomerDAO(ds);
				String customerUsername = rs.getString("shipping_address_customer_username");
				customer = dao.doRetrieveByKey(customerUsername);
				
				shippingAddress.setCustomer(customer);
				
				shippingAddresses.add(shippingAddress);
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
		
		return shippingAddresses;
	}
}
