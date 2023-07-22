package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.ShippingAddress;
import it.unisa.model.ShoppingCart;
import it.unisa.model.Product;
import it.unisa.model.Composes;
import it.unisa.model.Customer;
import it.unisa.model.Order;
import it.unisa.model.PaymentMethod;
import it.unisa.db.ComposesDAO;
import it.unisa.db.ContainsDAO;
import it.unisa.db.OrderDAO;
import it.unisa.db.ShippingAddressDAO;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.db.PaymentMethodDAO;

import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

@WebServlet("/SaveOrder")
public class SaveOrder extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	DataSource dataSource = (DataSource) getServletContext().getAttribute("DataSource");
    	
    	HttpSession session = request.getSession();
    	@SuppressWarnings("unchecked")
		Collection<Product> cartItems = (Collection<Product>) session.getAttribute("cartItems");
    	@SuppressWarnings("unchecked")
		Map<Integer, Integer> map = (Map<Integer, Integer>) session.getAttribute("mapProductsQuantity");
    	
    	String shippingAddressIDString = request.getParameter("ShippingAddress");
    	int ShippingAddressID = -1;
    	if (shippingAddressIDString != null && !shippingAddressIDString.trim().equals(""))
    	{
    		ShippingAddressID = Integer.parseInt(shippingAddressIDString);
    	}
    	
    	String PaymentMethodPAN = request.getParameter("PaymentMethod");
    	
    	// Salvare nella lista degli ordini
        OrderDAO orderDAO = new OrderDAO(dataSource);
        Order order = new Order();
        
        ShippingAddressDAO shippingAddressDAO = new ShippingAddressDAO(dataSource);
        PaymentMethodDAO paymentMethodDAO = new PaymentMethodDAO(dataSource);
        ShippingAddress shippingAddress = new ShippingAddress();
        PaymentMethod paymentMethod = new PaymentMethod();
        
        try {
        	shippingAddress = shippingAddressDAO.doRetrieveByKey(ShippingAddressID);
        	paymentMethod = paymentMethodDAO.doRetrieveByKey(PaymentMethodPAN);
        } catch(SQLException e) {
        	System.err.println("Error");
        }
        
        Customer customer = (Customer) session.getAttribute("customer");

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        
        double total = 0.0;
        for(Product product : cartItems) {
        	total += product.getPrice() * map.get(product.getCode());
        }
        
        String array[] = {"In Transito", "Consegnato"};
        Random random = new Random();
        
        int randomInt = random.nextInt(2);
        order.setStatus(array[randomInt]);
        order.setTotalAmount(total);
        order.setPaymentDate(date);
        order.setCustomer(customer);
        order.setShippingAddress(shippingAddress);
        order.setPaymentMethod(paymentMethod);
        
        int code = -1;
        try {
			code = orderDAO.doRetrieveLastOrderCode();
			code++;
			order.setCode(code);
        } catch(SQLException e) {
			code = 0;
			order.setCode(code);
        }
        
        try {
			orderDAO.doSave(order);
		} catch (SQLException e) {
			System.err.println(e);
		}
        
        for(Product product : cartItems) {
        	Composes composes = new Composes();
        	composes.setProduct(product);
        	composes.setOrder(order);
        	composes.setQuantity(map.get(product.getCode()));
        	ComposesDAO composesDAO = new ComposesDAO(dataSource);
        	try {
        		composesDAO.doSave(composes);
    		} catch (SQLException e) {
    			System.err.println(e);
    		}
        }
        
        // Cancellare gli attributi!
        session.removeAttribute("ShippingAddress");
        session.removeAttribute("PaymentMethod");
        session.removeAttribute("orders");
        session.removeAttribute("orderProducts");
        session.removeAttribute("cartItems");
        session.removeAttribute("mapProductsQuantity");
        
        // Svuotare il carrello!
		ShoppingCartDAO dao = new ShoppingCartDAO(dataSource);
		ContainsDAO containsDAO = new ContainsDAO(dataSource);
		ShoppingCart shoppingCart = null;
		
		try
		{
			shoppingCart = dao.doRetrieveByKey(customer.getUsername());
			dao.doEmptyByKey(shoppingCart.getCustomer().getUsername());
			containsDAO.doDeleteAllByKey(shoppingCart.getCustomer().getUsername());
		}
		catch (SQLException e)
		{
			System.err.println(e);
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
    }
}