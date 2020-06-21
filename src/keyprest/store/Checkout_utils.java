package keyprest.store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import keyprest.database.connectionManager;

public class Checkout_utils {

	public static boolean processCart(ArrayList<CartItem> cart)
	{
		if(cart == null) {return false;}
		
		for(CartItem item: cart)
		{
			System.out.println("Item Id:" + item.getItemID() + " User ID: " + item.getUserID());
			if(!assignProduct(item.getItemID(), item.getUserID()))
			{
				return false;
			}
		}
		return true;	
	}
	
	private static boolean assignProduct(int product_id, int user_id)
	{
		String QUERY = "INSERT INTO orders (user, product_id, final_price, key_id) VALUES (?, ?, ?, ?)"; 
		
		float _finalprice = 0;
		int _discount = 0;
		int _keyid = 0;
		
		if(product_id <= 0 || user_id <= 0) {return false;} 
		Product _p;
		try {
			_p = ProductUtils.productByID(product_id);
		
			if(_p != null)
			{
				_finalprice = _p.getPrice();
				_discount = _p.getDiscount();
			
				if(_discount > 0)
				{
					_finalprice = _finalprice - (_finalprice * _discount / 100);
				}
			}else { return false;}
			System.out.println("Product is " + _p.getName() + " price " + _p.getPrice());
			PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);

			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, product_id);
			preparedStatement.setFloat(3, _finalprice);
		
			_keyid = retriveKey(product_id);
			
			System.out.println("Key id is " + _keyid);
			
			if(_keyid == 0) { return false; } 

			preparedStatement.setInt(4, _keyid);
			
			
			if(preparedStatement.executeUpdate() == 1) {return true;} else {return false;} 
		
		} catch (SQLException e) { System.out.println(e.toString()); return false;}
	}
	
	private static int retriveKey(int product_id) throws SQLException
	{
		String QUERY = "SELECT key_id, `key` FROM `keys` WHERE product_id = ? AND sold = 0 LIMIT 1;";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setInt(1, product_id);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next())
		{
			if(!rs.getString("key").isEmpty())
			{
					System.out.println("Key is " + rs.getString("key"));
					if(lockKey(rs.getInt("key_id")))
					{
						return rs.getInt("key_id");
					} else { return 0; }
			}
		}	
		return 0;
	}
	
	private static boolean lockKey(int key_id) throws SQLException
	{
		
		String QUERY = "UPDATE `keys` SET sold = '1' WHERE key_id = ?";
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		try {
			preparedStatement.setInt(1, key_id);
		
			if(preparedStatement.executeUpdate() > 0) { return true; } else { return false; }
		 
		} catch (SQLException e) {return false;}
	}
}
