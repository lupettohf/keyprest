package keyprest.store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import keyprest.database.connectionManager;
import keyprest.user.User;
import keyprest.user.User_utils;
import keyprest.utils.Validators;

public class Cart_utils {
	
	public static ArrayList<CartItem> getCartItems(String session_key) 
	{
		String QUERY = "SELECT * FROM cart WHERE user_id = ?";
		
		int _id = 0;
		
		try {
			if(User_utils.getUser(session_key) == null) {
				return null;
			}else{
				_id = User_utils.getUser(session_key).getID();
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
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean addToCart(int product_id, String session_key) throws SQLException
	{
		String QUERY = "INSERT INTO cart" +
				"(product_id, user_id) VALUES (?, ?)";
		
		int _id = 0;
		
		if(Product_utils.productByID(product_id) == null  || User_utils.getUser(session_key) == null) {
			return false;
		}else{
			_id = User_utils.getUser(session_key).getID();
		}
		
		if(_id > 0)
		{
			PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);

			preparedStatement.setInt(1, product_id);
			preparedStatement.setInt(2, _id);

			if(preparedStatement.executeUpdate() == 1) {return true;}	
		}

		return false;
	}
	
	public static boolean deleteFromCart(String session_key, int cart_id) throws SQLException
	{
		String QUERY = "DELETE FROM cart WHERE user_id = ? AND id = ?";
		
		int _id = 0;
		
		if(User_utils.getUser(session_key) == null) {
			return false;
		}else{
			_id = User_utils.getUser(session_key).getID();
		}
		
		if(_id > 0)
		{
			PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);

			preparedStatement.setInt(1, _id);
			preparedStatement.setInt(2, cart_id);

			if(preparedStatement.executeUpdate() == 1) {return true;}	
		}

		return false;
	}

}
