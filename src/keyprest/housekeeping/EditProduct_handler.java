package keyprest.housekeeping;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.database.connectionManager;
import keyprest.store.Product;
import keyprest.store.Product_utils;
import keyprest.user.User_utils;

@WebServlet(name = "EditProduct_handler", urlPatterns = {"/editproduct"})
public class EditProduct_handler extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		if(session.getAttribute("username") == null)
		{
			req = request.getRequestDispatcher("login.jsp");
		} else if((boolean) session.getAttribute("housekeeper")) {
			req = request.getRequestDispatcher("/template/pages/edit_product.jsp");
		} else {
			req = request.getRequestDispatcher("user.jsp");
		}
		
		int Product_ID = Integer.parseInt(request.getParameter("product_id"));
		
		if(Product_ID <= 0) { Product_ID = 1; } 
		
		Product Current_Product;
		
		try {
			Current_Product = Product_utils.productByID(Product_ID);
			session.setAttribute("product", Current_Product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int Product_ID = Integer.parseInt(request.getParameter("product_id"));
		String Product_name = request.getParameter("product_name");
		String Product_description = request.getParameter("product_description");
		int Product_service = Integer.parseInt(request.getParameter("product_service"));
		float Product_price = Float.parseFloat(request.getParameter("product_price"));
		String Product_region = request.getParameter("product_region");
		int Product_discount = Integer.parseInt(request.getParameter("product_discount"));
		boolean Product_isDLC = Boolean.parseBoolean(request.getParameter("product_isdlc"));
	
	
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/pages/edit_product.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		//Verifica che l'utente sia loggato e sia admin.
		
		if(!(Product_ID <= 0)) { req.include(request, response); }
		try {
			if(SessionKey.isEmpty() || !(User_utils.isAdmin(SessionKey))) { 
				if(Product_utils.updateProduct(Product_ID, new Product(
						0, 
						Product_name, 
						Product_service,
						Product_description, 
						Product_price, 
						Product_discount, 
						Product_region, 
						Product_isDLC
					))) 
				{ session.setAttribute("success", "Prodotto modificato con successo"); } else { session.setAttribute("error", "Si é verificato un errore nella modifica del prodotto.");}		
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	
		req.include(request, response);
	}
}
