package keyprest.store;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.commons.codec.digest.DigestUtils;

import keyprest.utils.Globals;
import keyprest.database.connectionManager;

//@WebServlet(name = "Product_handler", urlPatterns = {"/products"})
public class Product_handler extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Product> products = new ArrayList<Product>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/skeletons/products.jsp");
		int start_id = 1;
		int end_id = 10;
		
		//if(!request.getParameter("startid").isEmpty()) { start_id = Integer.parseInt(request.getParameter("startid")); };
		//if(!request.getParameter("endid").isEmpty()){ end_id = Integer.parseInt(request.getParameter("endid")); };
		
		try {
			products = Product_utils.retriveProducts(start_id, end_id);
			
			session.setAttribute("products", products);
			req.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
