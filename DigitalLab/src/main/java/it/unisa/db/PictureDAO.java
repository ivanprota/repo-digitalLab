package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Picture;
import it.unisa.model.Product;
import it.unisa.utils.Constants;

public class PictureDAO 
{
	private DataSource ds;
	
	public PictureDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Picture picture) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.PICTURE_TABLE_NAME+ " VALUES (?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, picture.getProduct().getCode());
			preparedStatement.setBytes(2, picture.getImage());
			
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
		String deleteSQL = "DELETE FROM " +Constants.PICTURE_TABLE_NAME+ " WHERE picture_product_code = ?";
		
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
	
	public synchronized Picture doRetrieveByKey(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Picture picture = new Picture();
		String selectSQL = "SELECT * FROM " +Constants.PICTURE_TABLE_NAME+ " WHERE picture_product_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			Product product;
			ProductDAO dao = new ProductDAO(ds);
			int productCode = rs.getInt("picture_product_code");
			product = dao.doRetrieveByKey(productCode);
			picture.setProduct(product);
			picture.setImage(rs.getBytes("picture_image"));
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
		
		return picture;
	}
	
	public synchronized Collection<Picture> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Picture> pictures = new LinkedList<Picture>();
		String selectSQL = "SELECT * FROM " +Constants.PICTURE_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Picture picture = new Picture();
				Product product;
				ProductDAO dao = new ProductDAO(ds);
				int productCode = rs.getInt("picture_product_code");
				product = dao.doRetrieveByKey(productCode);
				picture.setProduct(product);
				picture.setImage(rs.getBytes("picture_image"));	
				
				pictures.add(picture);
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
		
		return pictures;
	}
}
