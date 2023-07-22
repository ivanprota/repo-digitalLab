package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sql.DataSource;

import org.apache.catalina.ssi.SSIFlastmod;

import it.unisa.model.Product;
import it.unisa.model.Review;
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
		
		String insertSQL = "INSERT INTO " +Constants.PRODUCT_TABLE_NAME+ 
				" (product_quantity, product_description, product_price, product_brand, product_model, product_category)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getQuantity());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setString(4, product.getBrand());
			preparedStatement.setString(5, product.getModel());
			preparedStatement.setString(6, product.getCategory());
			
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
	
	public synchronized Collection<Product> doRetrieveByFilter(String priceRange1, String priceRange2) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "";
		
		boolean isMoreThanOrLessThan = false;
		
		if (priceRange2 == null)
		{
			if (priceRange1 != null)
			{
				selectSQL += "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_price >= ?";
				isMoreThanOrLessThan = true;
			}
		}
		else if (priceRange1 == null)
		{
			if (priceRange2 != null)
			{
				selectSQL += "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_price <= ?";
				isMoreThanOrLessThan = true;
			}
		}
		else if (priceRange1 != null && priceRange2 != null)
			selectSQL += "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_price BETWEEN ? AND ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			if (isMoreThanOrLessThan)
			{
				if (priceRange1 != null)
				{
					double price = Double.parseDouble(priceRange1);
					preparedStatement.setDouble(1, price);
				}
				else
				{
					double price = Double.parseDouble(priceRange2);
					preparedStatement.setDouble(1, price);
				}
			}
			else
			{
				double price1 = Double.parseDouble(priceRange1);
				double price2 = Double.parseDouble(priceRange2);
				preparedStatement.setDouble(1, price1);
				preparedStatement.setDouble(2, price2);
			}
			
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
	
	public synchronized Collection<Product> doRetrieveByFiler(String[] brands) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_brand = ?";
		for (int i=1; i<brands.length; i++)
			selectSQL += " OR product_brand = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			for (int i=0, pos=1, brandsIndex=0; i<selectSQL.length(); i++)
			{
				if (selectSQL.charAt(i) == '?')
				{
					preparedStatement.setString(pos, brands[brandsIndex]);
					pos++;
					brandsIndex++;
				}
			}
			
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
	
	public synchronized Collection<Product> doRetrieveByFilter(String category) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME+ " WHERE product_category = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, category);
			
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
	
	public synchronized Collection<Product> doRetrieveByFilter(int stars) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Product> products = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.PRODUCT_TABLE_NAME;

		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			Collection<Review> reviews = null;
			ReviewDAO reviewDAO = new ReviewDAO(ds);
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
				
				reviews = reviewDAO.doRetrieveAllByKey(product.getCode());
				Iterator<Review> it = reviews.iterator();
				int sum = 0;
				while(it.hasNext())
				{
					Review review = (Review) it.next();
					sum += review.getAssessment();
				}
				
				if (reviews.size() == 0)
				{
					if (0 >= stars)
						products.add(product);
				}
				else if ((sum / reviews.size()) >= stars)
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
	
	public synchronized Collection<Product> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;		
		
		Collection<Product> products = new LinkedList<>();
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
