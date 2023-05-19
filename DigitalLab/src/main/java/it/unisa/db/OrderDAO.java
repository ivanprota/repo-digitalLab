package it.unisa.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import it.unisa.model.Customer;
import it.unisa.model.Order;
import it.unisa.model.PaymentMethod;
import it.unisa.model.ShippingAddress;
import it.unisa.utils.Constants;

public class OrderDAO 
{
	private DataSource ds = null;
	
	public OrderDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public synchronized void doSave(Order order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " +Constants.ORDER_TABLE_NAME+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, order.getCode());
			preparedStatement.setString(2, order.getStatus());
			preparedStatement.setDouble(3, order.getTotalAmount());
			preparedStatement.setDate(4, order.getPaymentDate());
			preparedStatement.setString(5, order.getCustomer().getUsername());
			preparedStatement.setInt(6, order.getShippingAddress().getId());
			preparedStatement.setString(7, order.getPaymentMethod().getPan());
			
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
	
	public synchronized boolean doDelete(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result = 0;
		String deleteSQL = "DELETE FROM " +Constants.ORDER_TABLE_NAME+ " WHERE customer_order_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			
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
	
	public synchronized Order doRetrieveByKey(int code) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Order order = new Order();
		String selectSQL = "SELECT * FROM " +Constants.ORDER_TABLE_NAME+ " WHERE customer_order_code = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			order.setCode(rs.getInt("customer_order_code"));
			order.setStatus(rs.getString("customer_order_status"));
			order.setTotalAmount(rs.getDouble("customer_order_total_amount"));
			order.setPaymentDate(rs.getDate("customer_order_payment_date"));
			
			Customer customer;
			CustomerDAO dao1 = new CustomerDAO(ds);
			String customerUsername = rs.getString("customer_order_customer_username");
			customer = dao1.doRetrieveByKey(customerUsername);
			order.setCustomer(customer);
			
			ShippingAddress address;
			ShippingAddressDAO dao2 = new ShippingAddressDAO(ds);
			int addressId = rs.getInt("customer_order_shipping_address_id");
			address = dao2.doRetrieveByKey(addressId);
			order.setShippingAddress(address);
			
			PaymentMethod paymentMethod;
			PaymentMethodDAO dao3 = new PaymentMethodDAO(ds);
			String paymentMethodPan = rs.getString("customer_order_payment_method_pan");
			paymentMethod = dao3.doRetrieveByKey(paymentMethodPan);
			order.setPaymentMethod(paymentMethod);
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
		
		return order;
	}
	
	public synchronized Collection<Order> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Order> orders = new LinkedList<Order>();
		String selectSQL = "SELECT * FROM " +Constants.ORDER_TABLE_NAME;
		
		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " +order;
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				Order customerOrder = new Order();
				customerOrder.setCode(rs.getInt("customer_order_code"));
				customerOrder.setStatus(rs.getString("customer_order_status"));
				customerOrder.setTotalAmount(rs.getDouble("customer_order_total_amount"));
				customerOrder.setPaymentDate(rs.getDate("customer_order_payment_date"));
				
				Customer customer;
				CustomerDAO dao1 = new CustomerDAO(ds);
				String customerUsername = rs.getString("customer_order_customer_username");
				customer = dao1.doRetrieveByKey(customerUsername);
				customerOrder.setCustomer(customer);
				
				ShippingAddress address;
				ShippingAddressDAO dao2 = new ShippingAddressDAO(ds);
				int addressId = rs.getInt("customer_order_shipping_address_id");
				address = dao2.doRetrieveByKey(addressId);
				customerOrder.setShippingAddress(address);
				
				PaymentMethod paymentMethod;
				PaymentMethodDAO dao3 = new PaymentMethodDAO(ds);
				String paymentMethodPan = rs.getString("customer_order_payment_method_pan");
				paymentMethod = dao3.doRetrieveByKey(paymentMethodPan);
				customerOrder.setPaymentMethod(paymentMethod);
				
				orders.add(customerOrder);
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
		
		return orders;
	}
}
