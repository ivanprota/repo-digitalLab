/*
 * Questa servlet effettua delle operazioni
 * sui prodotti in base al valore passato
 * all'interno della query string
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import it.unisa.db.PictureDAO;
import it.unisa.db.ProductDAO;
import it.unisa.model.Product;

@WebServlet("/AdminProductControlServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
					maxFileSize = 1024 * 1024 * 10, // 10MB
					maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AdminProductControlServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String action = (String) request.getParameter("action");
		
		if (action.equals("add"))
		{
			int code = Integer.parseInt(request.getParameter("code"));
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			String category = request.getParameter("category");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String description = request.getParameter("description");
				
			Product product = new Product();
			product.setCode(code);
			product.setBrand(brand);
			product.setModel(model);
			product.setCategory(category);
			product.setPrice(price);
			product.setQuantity(quantity);
			product.setDescription(description);
				
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProductDAO productDAO = new ProductDAO(ds);
				
			try
			{
				productDAO.doSave(product);		
			}
			catch(SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
				
			PictureDAO pictureDAO = new PictureDAO(ds);
			for (Part part : request.getParts())
			{
				String fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.equals(""))
				{
					try
					{
						pictureDAO.doUpdatePhoto(code, part.getInputStream());
					}
					catch(SQLException e)
					{
						System.out.println(e);
						response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
						return;
					}
				}
			}
				
			response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
			return;
		}
		else if (action.equals("select"))
		{
			int code = Integer.parseInt(request.getParameter("code"));
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProductDAO dao = new ProductDAO(ds);
			Product product;
			try
			{
				product = dao.doRetrieveByKey(code);
			}
			catch(SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
			
			request.setAttribute("product", product);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin-area.jsp");
			dispatcher.forward(request, response);
		}
		else if (action.equals("update"))
		{
			String codeString = request.getParameter("code");
			if (codeString == null || codeString.equals(""))
			{
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
			
			int code = Integer.parseInt(codeString);
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProductDAO dao = new ProductDAO(ds);
			Product product;
			
			try
			{
				product = dao.doRetrieveByKey(code);
			}
			catch (SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
			
			String brand = request.getParameter("brand");
			String model = request.getParameter("model");
			String category = request.getParameter("category");
			String description = request.getParameter("description");
			
			String priceString = request.getParameter("price");
			if (priceString == null || priceString.equals(""))
			{
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
			double price = Double.parseDouble(priceString);
			
			String quantityString = request.getParameter("quantity");
			if (quantityString == null || quantityString.equals(""))
			{
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;
			}
			int quantity = Integer.parseInt(quantityString);
			
			try
			{
				if (!(product.getBrand().equals(brand)))
				{
					dao.doUpdate(code, "product_brand", brand);
				}
				if (!(product.getModel().equals(model)))
				{
					dao.doUpdate(code, "product_model", model);
				}
				if (!(product.getCategory().equals(category)))
				{
					dao.doUpdate(code, "product_category", category);
				}
				if (!(product.getDescription().equals(description)))
				{
					dao.doUpdate(code, "product_description", description);
				}
				if (product.getPrice() != price)
				{
					dao.doUpdate(code, "product_price", priceString);
				}
				if (product.getQuantity() != quantity)
				{
					dao.doUpdate(code, "product_quantity", quantityString);
				}
			}
			catch (SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;				
			}
			
			response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
			return;	
				
		}
		else if (action.equals("delete"))
		{
			int code = Integer.parseInt(request.getParameter("code"));
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			ProductDAO dao = new ProductDAO(ds);
			try
			{
				dao.doUpdate(code, "product_quantity", "0");
			}
			catch(SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath() + "/admin/admin-area.jsp");
				return;			
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/admin-area.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
