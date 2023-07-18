package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Product;
import it.unisa.model.Review;
import it.unisa.utils.Constants;

public class ReviewDAO 
{
	private DataSource ds;
	
	public ReviewDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Review review) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.REVIEW_TABLE_NAME+ " VALUES (?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, review.getProduct().getCode());
			preparedStatement.setString(2, review.getDescription());
			preparedStatement.setInt(3, review.getAssessment());
			
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
		String deleteSQL = "DELETE FROM " +Constants.REVIEW_TABLE_NAME+ " WHERE review_product_code = ?";
		
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
	
	public synchronized Review doRetrieveByKey(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Review review = new Review();
		String selectSQL = "SELECT * FROM " +Constants.REVIEW_TABLE_NAME+ " WHERE review_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			Product product;
			ProductDAO dao = new ProductDAO(ds);
			int productCode = rs.getInt("review_product_code");
			product = dao.doRetrieveByKey(productCode);
			review.setProduct(product);
			review.setDescription(rs.getString("review_description"));
			review.setAssessment(rs.getInt("review_assessment"));
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
		
		return review;
	}
	
	public synchronized Collection<Review> doRetrieveAllByKey(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Review> reviews = new LinkedList<Review>();
		String selectSQL = "SELECT * FROM " +Constants.REVIEW_TABLE_NAME+ " WHERE review_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				Review review = new Review();
				Product product;
				ProductDAO dao = new ProductDAO(ds);
				int productCode = rs.getInt("review_product_code");
				product = dao.doRetrieveByKey(productCode);
				review.setProduct(product);
				review.setDescription(rs.getString("review_description"));
				review.setAssessment(rs.getInt("review_assessment"));
				
				reviews.add(review);
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
		
		return reviews;
	}
	
	public synchronized Collection<Review> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Review> reviews = new LinkedList<Review>();
		String selectSQL = "SELECT * FROM " +Constants.REVIEW_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
 		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Review review = new Review();
				Product product;
				ProductDAO dao = new ProductDAO(ds);
				int productCode = rs.getInt("review_product_code");
				product = dao.doRetrieveByKey(productCode);
				review.setProduct(product);
				review.setDescription(rs.getString("review_description"));
				review.setAssessment(rs.getInt("review_assessment"));	
				
				reviews.add(review);
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
		
		return reviews;
	}
}
