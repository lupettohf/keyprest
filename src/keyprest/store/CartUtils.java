package keyprest.store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import keyprest.database.connectionManager;
import keyprest.user.User;
import keyprest.user.UserUtils;
import keyprest.utils.Validators;

public class CartUtils {
	
	public static ArrayList<CartItem> getCartItems(String session_key) 
	{
		String QUERY = "SELECT * FROM cart WHERE user_id = ?";
		
		int _id = 0;
		
		try {
			if(UserUtils.getUser(session_key) == null) {
				return null;
			}else{
				_id = UserUtils.getUser(session_key).getID();
			}
		
			if(_id > 0) {
				PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
				preparedStatement.setInt(1, _id);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				ArrayList<CartItem> _cart = new ArrayList<CartItem>();
		
				while(rs.next())
				{
					_cart.add(
							new CartItem(
									rs.getInt("product_id"), 
									rs.getInt("user_id"),
									rs.getInt("id")
							));
				}
		
				return _cart;
			}
		
		} catch (SQLException e) {}
		
		return null;
	}
	
	public static float getCartSubtotal(List<CartItem> cart)
	{
		float _subtotal = 0;
		
		for(CartItem item: cart)
		{
			_subtotal = _subtotal + item.productDiscountPrice();
		}
		
		return _subtotal;
	}
	
	public static int getCartElements(String session_key)
	{
		String QUERY = "SELECT COUNT(*) AS cart FROM `cart` WHERE user_id = ?";
		
		int _id = 0; 
		
		try {
			if(UserUtils.getUser(session_key) == null) {
				return 0;
			}else{
				_id = UserUtils.getUser(session_key).getID();
			}
		
			if(_id > 0) {
				PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
				preparedStatement.setInt(1, _id);
		
				ResultSet rs = preparedStatement.executeQuery();
		
				ArrayList<CartItem> _cart = new ArrayList<CartItem>();
		
				while(rs.next())
				{
					return rs.getInt("incart");
				}
			}
		
		} catch (SQLException e) {}
		
		return 0;
	}
	
	public static boolean addToCart(int product_id, String session_key)
	{
		String QUERY = "INSERT INTO cart" +
				"(product_id, user_id) VALUES (?, ?)";
		
		int _id = 0;
		
		try {
			if(ProductUtils.productByID(product_id) == null  || UserUtils.getUser(session_key) == null) {
				return false;
			}else{
				_id = UserUtils.getUser(session_key).getID();
			}
		
			if(_id > 0)
			{
				PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);

				preparedStatement.setInt(1, product_id);
				preparedStatement.setInt(2, _id);

				if(preparedStatement.executeUpdate() == 1) {return true;}	
			} 
		} catch (SQLException e) {}
		
		return false;
	}
	
	public static boolean deleteFromCart(String session_key, int cart_id)
	{
		String QUERY = "DELETE FROM cart WHERE user_id = ? AND id = ?";
		
		int _id = 0;
		
		try {
			if(UserUtils.getUser(session_key) == null) {
				return false;
			}else{
				_id = UserUtils.getUser(session_key).getID();
			}
		
			if(_id > 0)
			{
				PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);

				preparedStatement.setInt(1, _id);
				preparedStatement.setInt(2, cart_id);

				if(preparedStatement.executeUpdate() == 1) {return true;}	
			}

			return false;
		
		} catch (SQLException e) {}
		
		return false;
	}

}
