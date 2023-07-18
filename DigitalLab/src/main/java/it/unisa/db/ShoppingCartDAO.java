package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Customer;
import it.unisa.model.ShoppingCart;
import it.unisa.utils.Constants;

public class ShoppingCartDAO 
{
	private DataSource ds =  null;
	
	public ShoppingCartDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(ShoppingCart cart) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.SHOPPING_CART_TABLE_NAME+ " VALUES (?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, cart.getCustomer().getUsername());
			preparedStatement.setInt(2, cart.getSize());
			
			preparedStatement.executeUpdate();
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
		String deleteSQL = "DELETE FROM " +Constants.SHOPPING_CART_TABLE_NAME+ " WHERE shopping_cart_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);
			
			result = preparedStatement.executeUpdate();
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
	
	public synchronized boolean doEmptyByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String updateSQL = "UPDATE " +Constants.SHOPPING_CART_TABLE_NAME+ 
				" SET shopping_cart_size = 0 WHERE shopping_cart_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, username);
			
			result = preparedStatement.executeUpdate();
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
	
	public synchronized boolean doSizeUpdateByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String updateSQL = "UPDATE " +Constants.SHOPPING_CART_TABLE_NAME+ 
				" SET shopping_cart_size = shopping_cart_size + 1 WHERE shopping_cart_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, username);
			
			result = preparedStatement.executeUpdate();
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
	
	public synchronized ShoppingCart doRetrieveByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ShoppingCart cart = new ShoppingCart();
		String selectSQL = "SELECT * FROM " +Constants.SHOPPING_CART_TABLE_NAME+ " WHERE shopping_cart_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			Customer customer;
			CustomerDAO dao = new CustomerDAO(ds);
			String customerUsername = rs.getString("shopping_cart_customer_username");
			customer = dao.doRetrieveByKey(customerUsername);
			cart.setCustomer(customer);
			cart.setSize(rs.getInt("shopping_cart_size"));
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
		
		return cart;
	}
	
	public synchronized Collection<ShoppingCart> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ShoppingCart> carts = new LinkedList<ShoppingCart>();
		String selectSQL = "SELECT * FROM " +Constants.SHOPPING_CART_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				ShoppingCart cart = new ShoppingCart();
				Customer customer;
				CustomerDAO dao = new CustomerDAO(ds);
				String customerUsername = rs.getString("shopping_cart_customer_username");
				customer = dao.doRetrieveByKey(customerUsername);
				cart.setCustomer(customer);
				cart.setSize(rs.getInt("shopping_cart_size"));
				
				carts.add(cart);
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
		
		return carts;
	}
}
