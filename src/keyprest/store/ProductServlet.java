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

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Product product;
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/product.jsp");
		String Product_ID = request.getParameter("id");
		int _product_ID = 1;
		
		try {
			if(!Product_ID.isEmpty())
			{
				_product_ID = Integer.parseInt(Product_ID);
			}
		} catch(NumberFormatException e) {}
		
		
		try {
			
			//TODO spostare try
			product = ProductUtils.productByID(_product_ID);
			session.setAttribute("product", product);
			req.include(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
