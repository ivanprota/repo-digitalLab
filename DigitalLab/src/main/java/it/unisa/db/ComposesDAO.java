package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Composes;
import it.unisa.model.Order;
import it.unisa.model.Product;
import it.unisa.utils.Constants;

public class ComposesDAO 
{
	private DataSource ds = null;
	
	public ComposesDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Composes composes) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.COMPOSES_TABLE_NAME+ " VALUES (?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, composes.getProduct().getCode());
			preparedStatement.setInt(2, composes.getOrder().getCode());
			
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
	
	public synchronized boolean doDelete(int productCode, int orderCode) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.COMPOSES_TABLE_NAME+ " WHERE composes_product_code = ? " +
																						"AND composes_order_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, productCode);
			preparedStatement.setInt(2, orderCode);
			
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
	
	public synchronized Composes doRetrieveByKey(int productCode, int orderCode) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Composes composes = new Composes();
		String selectSQL = "SELECT * FROM " +Constants.COMPOSES_TABLE_NAME+ " WHERE composes_product_code = ? " +
																						"AND composes_order_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, productCode);
			preparedStatement.setInt(2, orderCode);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			Product product;
			ProductDAO dao1 = new ProductDAO(ds);
			int pCode = rs.getInt("composes_product_code");
			product = dao1.doRetrieveByKey(pCode);
			composes.setProduct(product);
			Order order;
			OrderDAO dao2 = new OrderDAO(ds);
			int oCode = rs.getInt("composes_order_code");
			order = dao2.doRetrieveByKey(oCode);
			composes.setOrder(order);
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
		
		return composes;
	}
	
	public synchronized Collection<Composes> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Composes> composesCollection = new LinkedList<Composes>();
		String selectSQL = "SELECT * FROM " +Constants.COMPOSES_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Composes composes = new Composes();
				Product product;
				ProductDAO dao1 = new ProductDAO(ds);
				int pCode = rs.getInt("composes_product_code");
				product = dao1.doRetrieveByKey(pCode);
				composes.setProduct(product);
				Order customerOrder;
				OrderDAO dao2 = new OrderDAO(ds);
				int oCode = rs.getInt("composes_order_code");
				customerOrder = dao2.doRetrieveByKey(oCode);
				composes.setOrder(customerOrder);
				
				composesCollection.add(composes);
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
		
		return composesCollection;
	}
}
