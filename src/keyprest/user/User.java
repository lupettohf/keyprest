package keyprest.user;

public class User {
	
	private int ID;
	private String Username;
	private String Hashed_Password;
	private String MailAddress;
	private String RealName;
	private String BillingAddress;
	private int Points;
	private boolean IsAdmin;
	
	public User(int ID, String Username, String Hashed_Password, String MailAddress, String RealName, String BillingAddress, int Points, boolean IsAdmin)
	{
		this.ID = ID;
		this.Username = Username;
		this.Hashed_Password = Hashed_Password;
		this.MailAddress = MailAddress;
		this.RealName = RealName;
		this.BillingAddress = BillingAddress;
		this.Points = Points;
		this.IsAdmin = IsAdmin;
	}
	
	public int getID() 
	{
		return this.ID;
	}
	
	public String getUsername()
	{
		return this.Username;
	}
	
	public void setUsername(String Username)
	{
		this.Username = Username;
	}
	
	public String getHashedPassword()
	{
		return this.Hashed_Password;
	}
	
	public void setHashedPassword(String Hashed_Password)
	{
		this.Hashed_Password = Hashed_Password;
	}
	
	public String getMailAddress()
	{
		return this.MailAddress;
	}
	
	public void setMailAddress(String MailAddress)
	{
		this.MailAddress = MailAddress;
	}
	
	public String getRealName()
	{
		return this.RealName;
	}
	
	public void setRealName(String RealName)
	{
		this.RealName = RealName;
	}
	
	public String getBillingAddress()
	{
		return this.BillingAddress;
	}
	
	public void setBillingAddress(String BillingAddress)
	{
		this.BillingAddress = BillingAddress;
	}
	
	public int getPoints()
	{
		return this.Points;
	}
	
	public void setPoints(int Points)
	{
		this.Points = Points;
	}
	
	public boolean IsAdmin()
	{
		return this.IsAdmin;
	}
}
