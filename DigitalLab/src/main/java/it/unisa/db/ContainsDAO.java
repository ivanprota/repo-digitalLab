package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Contains;
import it.unisa.model.Product;
import it.unisa.model.ShoppingCart;
import it.unisa.utils.Constants;

public class ContainsDAO 
{
	private DataSource ds = null;
	
	public ContainsDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Contains contains) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.CONTAINS_TABLE_NAME+ " VALUES (?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, contains.getShoppingCart().getCustomer().getUsername());
			preparedStatement.setInt(2, contains.getProduct().getCode());
			preparedStatement.setInt(3, contains.getQuantity());
			
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
	
	public synchronized boolean doDelete(String username, int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.CONTAINS_TABLE_NAME+ " WHERE contains_shopping_cart_customer_username = ? " +
																									"AND contains_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, code);
			
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
	
	public synchronized boolean doDeleteAllByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.CONTAINS_TABLE_NAME+ " WHERE contains_shopping_cart_customer_username = ?";
		
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
	
	public synchronized boolean doUpdateQuantity(String username, int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String updateSQL = "UPDATE " +Constants.CONTAINS_TABLE_NAME+ 
				" SET contains_product_quantity = contains_product_quantity + 1 WHERE "
				+ "contains_shopping_cart_customer_username = ? AND contains_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, code);
			
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
	
	public synchronized boolean doUpdateQuantity(String username, int code, int quantity) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String updateSQL = "UPDATE " +Constants.CONTAINS_TABLE_NAME+ 
				" SET contains_product_quantity = ? WHERE "
				+ "contains_shopping_cart_customer_username = ? AND contains_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, username);
			preparedStatement.setInt(3, code);
			
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
	
	
	public synchronized Contains doRetrieveByKey(String username, int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Contains contains = new Contains();
		String selectSQL = "SELECT * FROM " +Constants.CONTAINS_TABLE_NAME+ " WHERE contains_shopping_cart_customer_username = ? " +
																									"AND contains_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			ShoppingCart cart;
			ShoppingCartDAO dao1 = new ShoppingCartDAO(ds);
			String customerUsername = rs.getString("contains_shopping_cart_customer_username");
			cart = dao1.doRetrieveByKey(customerUsername);
			contains.setShoppingCart(cart);
			Product product;
			ProductDAO dao2 = new ProductDAO(ds);
			int productCode = rs.getInt("contains_product_code");
			product = dao2.doRetrieveByKey(productCode);
			contains.setQuantity(rs.getInt("contains_product_quantity"));
			contains.setProduct(product);
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
		
		return contains;
	}
	
	public synchronized Collection<Contains> doRetrieveAllByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Contains> containsCollection = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.CONTAINS_TABLE_NAME+ 
					" WHERE contains_shopping_cart_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Contains contains = new Contains();
				ShoppingCart cart;
				ShoppingCartDAO dao1 = new ShoppingCartDAO(ds);
				String customerUsername = rs.getString("contains_shopping_cart_customer_username");
				cart = dao1.doRetrieveByKey(customerUsername);
				contains.setShoppingCart(cart);
				Product product;
				ProductDAO dao2 = new ProductDAO(ds);
				int productCode = rs.getInt("contains_product_code");
				product = dao2.doRetrieveByKey(productCode);
				contains.setQuantity(rs.getInt("contains_product_quantity"));
				contains.setProduct(product);	
				
				containsCollection.add(contains);				
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
		
		return containsCollection;
	}
	
	public synchronized Collection<Contains> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Contains> containsCollection = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.CONTAINS_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Contains contains = new Contains();
				ShoppingCart cart;
				ShoppingCartDAO dao1 = new ShoppingCartDAO(ds);
				String customerUsername = rs.getString("contains_shopping_cart_customer_username");
				cart = dao1.doRetrieveByKey(customerUsername);
				contains.setShoppingCart(cart);
				Product product;
				ProductDAO dao2 = new ProductDAO(ds);
				int productCode = rs.getInt("contains_product_code");
				product = dao2.doRetrieveByKey(productCode);
				contains.setProduct(product);	
				contains.setQuantity(rs.getInt("contains_product_quantity"));
				
				containsCollection.add(contains);
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
		
		return containsCollection;
	}
}
