/*
 * Questa servlet carica l'immagine di un prodotto
 * dal db e la restituisce all'interno della response
 */

package it.unisa.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.db.PictureDAO;
import it.unisa.model.Picture;

@WebServlet("/GetPictureServlet")
public class GetPictureServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String productCodeString = request.getParameter("productCode");
		if (productCodeString != null)
		{
			int productCode = Integer.parseInt(productCodeString);
			DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
			PictureDAO dao = new PictureDAO(ds);
			Picture picture = null;
			try
			{
				picture = dao.doRetrieveByKey(productCode);
			}
			catch (SQLException e)
			{
				System.out.println(e);
				response.sendRedirect(request.getContextPath());
				request.getSession().invalidate();
				return;
			}
			
			ServletOutputStream out = response.getOutputStream();
			byte[] bt = picture.getImage();
			out.write(bt);
			response.setContentType("image/jpeg");
			out.close();	
		}
		else
		{
			response.sendRedirect(request.getContextPath());
			request.getSession().invalidate();
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
