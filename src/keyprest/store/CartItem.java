package keyprest.store;

import java.sql.SQLException;

public class CartItem {
	
	private int product_id;
	private int user_id;
	private int cart_id;
	
	public CartItem(int product_id, int user_id, int cart_id)
	{
		this.product_id = product_id;
		this.user_id = user_id;
		this.cart_id = cart_id;
	}
	
	public int getItemID()
	{
		return this.product_id;
	}
	
	public void setItemID(int product_id)
	{
		this.product_id = product_id;
	}
	
	public int getUserID()
	{
		return this.user_id;
	}

	public void setUserID(int user_id)
	{
		this.user_id = user_id;
	}
	
	public int getCartID()
	{
		return this.cart_id;
	}
	
	public String productName()
	{
		try {
			return ProductUtils.productByID(this.product_id).getName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//TODO Sti try vanno levati asap
	
	public String productDescription()
	{
		try {
			return ProductUtils.productByID(this.product_id).getDescription();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}

	public float productPrice()
	{
		try {
			return ProductUtils.productByID(this.product_id).getPrice();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public String productRegion()
	{
		try {
			return ProductUtils.productByID(this.product_id).getRegion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public float productDiscountPrice()
	{
		try {
			return ProductUtils.productByID(this.product_id).getDiscountPrice();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int productDiscountPercetage()
	{
		try {
			return ProductUtils.productByID(this.product_id).getDiscount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
