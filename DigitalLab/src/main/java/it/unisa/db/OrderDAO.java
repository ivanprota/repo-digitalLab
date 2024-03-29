package it.unisa.db;

import java.sql.Connection;
import java.sql.Date;
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
	
	public synchronized int doRetrieveLastOrderCode() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int productCode = -1;
		String selectSQL = "SELECT customer_order_code FROM " +Constants.ORDER_TABLE_NAME+ 
				" ORDER BY customer_order_code DESC LIMIT 1";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			productCode = rs.getInt("customer_order_code");
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
		
		return productCode;
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
	
	public synchronized Collection<Order> doRetrieveByCustomerUsername(String username) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.ORDER_TABLE_NAME+ " WHERE customer_order_customer_username = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
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
	
	public synchronized Collection<Order> doRetrieveByDateRange(Date startDate, Date endDate) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Order> orders = new LinkedList<>();
		String selectSQL = "SELECT * FROM " +Constants.ORDER_TABLE_NAME+ " WHERE (customer_order_payment_date BETWEEN ? AND ?)";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, startDate);
			preparedStatement.setDate(2, endDate);
			
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
	
	public synchronized Collection<Order> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<Order> orders = new LinkedList<>();
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
