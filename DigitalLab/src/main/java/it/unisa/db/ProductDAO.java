package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Product;
import it.unisa.utils.Constants;

public class ProductDAO 
{
	private DataSource ds = null;
	
	public ProductDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Product product) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.PRODUCT_TABLE_NAME+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getCode());
			preparedStatement.setInt(2, product.getQuantity());
			preparedStatement.setString(3, product.getDescription());
			preparedStatement.setDouble(4, product.getPrice());
			preparedStatement.setString(5, product.getBrand());
			preparedStatement.setString(6, product.getModel());
			preparedStatement.setString(7, product.getCategory());
			
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
	
	public synchronized boolean doDelete(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			
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
	
	public synchronized boolean doUpdate(int code, String columnName, String columnValue) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String updateSQL = "";
		
		try
		{
			connection = ds.getConnection();
			
			if (columnName.equals("product_quantity"))
			{
				int quantity = Integer.parseInt(columnValue);
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_quantity = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setInt(1, quantity);
			}
			else if (columnName.equals("product_description"))
			{
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_description = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, columnValue);
			}
			else if (columnName.equals("product_price"))
			{
				double price = Double.parseDouble(columnValue);
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_price = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setDouble(1, price);
			}
			else if (columnName.equals("product_brand"))
			{
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_brand = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, columnValue);
			}
			else if (columnName.equals("product_model"))
			{
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_model = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, columnValue);
			}
			else if (columnName.equals("product_category"))
			{
				updateSQL += "UPDATE " +Constants.PRODUCT_TABLE_NAME+ " SET product_category = ? WHERE product_code = ?";
				preparedStatement = connection.prepareStatement(updateSQL);
				preparedStatement.setString(1, columnValue);
			}
			else return false;
			
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
	
	public synchronized Product doRetrieveByKey(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Product product = new Product();
		String selectSQL = "SELECT * FROM " + Constants.PRODUCT_TABLE_NAME+ " WHERE product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			product.setCode(rs.getInt("product_code"));
			product.setQuantity(rs.getInt("product_quantity"));
			product.setDescription(rs.getString("product_description"));
			product.setPrice(rs.getDouble("product_price"));
			product.setBrand(rs.getString("product_brand"));
			product.setModel(rs.getString("product_model"));
			product.setCategory(rs.getString("product_category"));
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
		
		return product;
	}
	
	public synchronized Collection<Product> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		
		Collection<Product> products = new LinkedList<Product>();
		String selectSQL = "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Product product = new Product();
				product.setCode(rs.getInt("product_code"));
				product.setQuantity(rs.getInt("product_quantity"));
				product.setDescription(rs.getString("product_description"));
				product.setPrice(rs.getDouble("product_price"));
				product.setBrand(rs.getString("product_brand"));
				product.setModel(rs.getString("product_model"));
				product.setCategory(rs.getString("product_category"));	
				
				products.add(product);
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
		
		return products;
	}
}
