package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Administrator;
import it.unisa.utils.Constants;

public class AdministratorDAO 
{
	private DataSource ds = null;
	
	public AdministratorDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Administrator admin) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.ADMINISTRATOR_TABLE_NAME+ " VALUES (?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, admin.getUsername());
			preparedStatement.setString(2, admin.getName());
			preparedStatement.setString(3, admin.getSurname());
			preparedStatement.setString(4, admin.getPassword());
			
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
		String deleteSQL = "DELETE FROM " +Constants.ADMINISTRATOR_TABLE_NAME+ " WHERE administrator_username = ?";
		
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
	
	public synchronized boolean doUpdate(String username, String columnName, String columnValue) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "";
		if (columnName.equals("administrator_username"))
			updateSQL += "UPDATE " +Constants.ADMINISTRATOR_TABLE_NAME+ " SET administrator_username = ? WHERE administrator_username = ?";
		else if (columnName.equals("administrator_name"))
			updateSQL += "UPDATE " +Constants.ADMINISTRATOR_TABLE_NAME+ " SET administrator_name = ? WHERE administrator_username = ?";
		else if (columnName.equals("administrator_surname"))
			updateSQL += "UPDATE " +Constants.ADMINISTRATOR_TABLE_NAME+ " SET administrator_surname = ? WHERE administrator_username = ?";
		else if (columnName.equals("administrator_password"))
			updateSQL += "UPDATE " +Constants.ADMINISTRATOR_TABLE_NAME+ " SET administrator_password = ? WHERE administrator_username = ?";
		
		int result = 0;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, columnValue);
			preparedStatement.setString(2, username);
			
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
		
		return (result!=0);
	}
	
	public synchronized Administrator doRetrieveByKey(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Administrator admin = new Administrator();
		String selectSQL = "SELECT * FROM " +Constants.ADMINISTRATOR_TABLE_NAME+ " WHERE administrator_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			admin.setUsername(rs.getString("administrator_username"));
			admin.setName(rs.getString("administrator_name"));
			admin.setSurname(rs.getString("administrator_surname"));
			admin.setPassword(rs.getString("administrator_password"));
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
		
		return admin;
	}
	
	public synchronized Collection<Administrator> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Administrator> admins = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.ADMINISTRATOR_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Administrator admin = new Administrator();
				admin.setUsername(rs.getString("administrator_username"));
				admin.setName(rs.getString("administrator_name"));
				admin.setSurname(rs.getString("administrator_surname"));
				admin.setPassword(rs.getString("administrator_password"));
				
				admins.add(admin);
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
		
		return admins;
	}
}
