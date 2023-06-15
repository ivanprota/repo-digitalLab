package it.unisa.testing;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.AdministratorDAO;
import it.unisa.db.ComposesDAO;
import it.unisa.db.ContainsDAO;
import it.unisa.db.CustomerDAO;
import it.unisa.db.OrderDAO;
import it.unisa.db.PaymentMethodDAO;
import it.unisa.db.PictureDAO;
import it.unisa.db.ProductDAO;
import it.unisa.db.ReviewDAO;
import it.unisa.db.ShippingAddressDAO;
import it.unisa.db.ShoppingCartDAO;
import it.unisa.model.Administrator;
import it.unisa.model.Composes;
import it.unisa.model.Contains;
import it.unisa.model.Customer;
import it.unisa.model.Order;
import it.unisa.model.PaymentMethod;
import it.unisa.model.Picture;
import it.unisa.model.Product;
import it.unisa.model.Review;
import it.unisa.model.ShippingAddress;
import it.unisa.model.ShoppingCart;

/**
 * Servlet implementation class TestingServlet
 */
@WebServlet("/TestingServlet")
public class TestingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

		// Testing sui metodi di AdministratorDAO
		Administrator admin = new Administrator();
		admin.setUsername("admin");
		admin.setName("Ivan");
		admin.setSurname("Prota");
		admin.setPassword("ciao");
		
		/*AdministratorDAO adminDAO = new AdministratorDAO(ds);
		try
		{
			/*adminDAO.doSave(admin);
			adminDAO.doDelete(admin.getUsername());*/
			/*System.out.println(adminDAO.doRetrieveByKey(admin.getUsername()));*/
			/*Collection<Administrator> admins = adminDAO.doRetrieveAll(null);
			Iterator<Administrator> it = admins.iterator();
			while (it.hasNext())
			{
				Administrator admin2 = it.next();
				System.out.println(admin2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		// Testing sui metodi di CustomerDAO
		Customer customer = new Customer();
		customer.setUsername("ivanprota");
		customer.setEmail("ivanprota99@gmail.com");
		customer.setPassword("ciao");
		customer.setName("Ivan");
		customer.setSurname("Prota");
		customer.setPhone("000-111");
		
		/*CustomerDAO customerDAO = new CustomerDAO(ds);
		try
		{
			customerDAO.doSave(customer);
			customerDAO.doDelete(customer.getUsername());
			System.out.println(customerDAO.doRetrieveByKey(customer.getUsername()));
			Collection<Customer> customers = customerDAO.doRetrieveAll(null);
			Iterator<Customer> it = customers.iterator();
			while (it.hasNext())
			{
				Customer customer2 = it.next();
				System.out.println(customer2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ShippingAddressDAO
		ShippingAddress address = new ShippingAddress();
		address.setId(1);
		address.setProvince("Napoli");
		address.setStreet("Via Santa Caterina");
		address.setCity("Gragnano");
		address.setZip("80054");
		address.setStreetNumber(15);
		address.setCustomer(customer);
		
		/*ShippingAddressDAO addressDAO = new ShippingAddressDAO(ds);
		try
		{
			//addressDAO.doSave(address);
			//addressDAO.doDelete(address.getId());
			//System.out.println(addressDAO.doRetrieveByKey(address.getId()));
			Collection<ShippingAddress> addresses = addressDAO.doRetrieveAll(null);
			Iterator<ShippingAddress> it = addresses.iterator();
			while (it.hasNext())
			{
				ShippingAddress address2 = it.next();
				System.out.println(address2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di PaymentMethodDAO
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPan("0000");
		paymentMethod.setOwner("Ivan Prota");
		paymentMethod.setCvv("000");
		paymentMethod.setExpirationDate(new Date(20000000));
		paymentMethod.setCustomer(customer);
		
		/*PaymentMethodDAO paymentDAO = new PaymentMethodDAO(ds);
		try
		{
			//paymentDAO.doSave(paymentMethod);
			//paymentDAO.doDelete(paymentMethod.getPan());
			//System.out.println(paymentDAO.doRetrieveByKey(paymentMethod.getPan()));
			Collection<PaymentMethod> methods = paymentDAO.doRetrieveAll(null);
			Iterator<PaymentMethod> it = methods.iterator();
			while (it.hasNext())
			{
				PaymentMethod paymentMethod2 = it.next();
				System.out.println(paymentMethod2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ShoppingCartDAO
		ShoppingCart cart = new ShoppingCart();
		cart.setCustomer(customer);
		cart.setSize(0);
		
		/*ShoppingCartDAO cartDAO = new ShoppingCartDAO(ds);
		try
		{
			//cartDAO.doSave(cart);
			//cartDAO.doDelete(cart.getCustomer().getUsername());
			//System.out.println(cartDAO.doRetrieveByKey(cart.getCustomer().getUsername()));
			Collection<ShoppingCart> carts = cartDAO.doRetrieveAll(null);
			Iterator<ShoppingCart> it = carts.iterator();
			while (it.hasNext())
			{
				ShoppingCart cart2 = it.next();
				System.out.println(cart2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di OrderDAO
		Order order = new Order();
		order.setCode(0);
		order.setStatus("Consegnato");
		order.setTotalAmount(1499.00);
		order.setPaymentDate(new Date(0));
		order.setCustomer(customer);
		order.setShippingAddress(address);
		order.setPaymentMethod(paymentMethod);
		
		/*OrderDAO orderDAO = new OrderDAO(ds);
		try
		{
			//orderDAO.doSave(order);
			//orderDAO.doDelete(order.getCode());
			//System.out.println(orderDAO.doRetrieveByKey(order.getCode()));
			Collection<Order> orders = orderDAO.doRetrieveAll(null);
			Iterator<Order> it = orders.iterator();
			while (it.hasNext())
			{
				Order order2 = it.next();
				System.out.println(order2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ProductDAO
		Product product = new Product();
		product.setCode(0);
		product.setQuantity(15);
		product.setDescription("iPhone 14 Pro Max 128GB Silver Black");
		product.setPrice(1499.00);
		product.setBrand("Apple");
		product.setModel("Pro Max");
		product.setCategory("Smartphone");
		
		/*ProductDAO productDAO = new ProductDAO(ds);
		try
		{
			//productDAO.doSave(product);
			//productDAO.doDelete(product.getCode());
			//System.out.println(productDAO.doRetrieveByKey(product.getCode()));
			Collection<Product> products = productDAO.doRetrieveAll(null);
			Iterator<Product> it = products.iterator();
			while (it.hasNext())
			{
				Product product2 = it.next();
				System.out.println(product2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di PictureDAO
		Picture picture = new Picture();
		picture.setProduct(product);
		String str = "ciao";
		byte[] strByte= str.getBytes();
		picture.setImage(strByte);
		
		/*PictureDAO pictureDAO = new PictureDAO(ds);
		try
		{
			//pictureDAO.doSave(picture);
			//pictureDAO.doDelete(picture.getProduct().getCode());
			//System.out.println(pictureDAO.doRetrieveByKey(picture.getProduct().getCode()));
			Collection<Picture> pictures = pictureDAO.doRetrieveAll(null);
			Iterator<Picture> it = pictures.iterator();
			while (it.hasNext())
			{
				Picture picture2 = it.next();
				System.out.println(picture2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ReviewDAO
		Review review = new Review();
		review.setProduct(product);
		review.setDescription("Ottimo prodotto");
		review.setAssessment(5);
		
		/*ReviewDAO reviewDAO = new ReviewDAO(ds);
		try
		{
			//reviewDAO.doSave(review);
			//reviewDAO.doDelete(review.getProduct().getCode());
			//System.out.println(reviewDAO.doRetrieveByKey(review.getProduct().getCode()));
			Collection<Review> reviews = reviewDAO.doRetrieveAll(null);
			Iterator<Review> it = reviews.iterator();
			while (it.hasNext())
			{
				Review review2 = it.next();
				System.out.println(review2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ContainsDAO
		Contains contains = new Contains();
		contains.setShoppingCart(cart);
		contains.setProduct(product);
		
		/*ContainsDAO containsDAO = new ContainsDAO(ds);
		try
		{
			//containsDAO.doSave(contains);
			//containsDAO.doDelete(contains.getShoppingCart().getCustomer().getUsername(), contains.getProduct().getCode());
			//System.out.println(containsDAO.doRetrieveByKey(contains.getShoppingCart().getCustomer().getUsername(), contains.getProduct().getCode()));
			Collection<Contains> containsCollection = containsDAO.doRetrieveAll(null);
			Iterator<Contains> it = containsCollection.iterator();
			while (it.hasNext())
			{
				Contains contains2 = it.next();
				System.out.println(contains2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
		
		
		// Testing sui metodi di ComposesDAO
		Composes composes = new Composes();
		composes.setProduct(product);
		composes.setOrder(order);
		
		/*ComposesDAO composesDAO = new ComposesDAO(ds);
		try
		{
			//composesDAO.doSave(composes);
			//composesDAO.doDelete(composes.getProduct().getCode(), composes.getOrder().getCode());
			//System.out.println(composesDAO.doRetrieveByKey(composes.getProduct().getCode(), composes.getOrder().getCode()));
			Collection<Composes> composesCollection = composesDAO.doRetrieveAll(null);
			Iterator<Composes> it = composesCollection.iterator();
			while (it.hasNext())
			{
				Composes composes2 = it.next();
				System.out.println(composes2);
			}
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
