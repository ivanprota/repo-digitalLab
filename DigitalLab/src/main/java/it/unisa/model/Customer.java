package it.unisa.model;

public class Customer 
{
	// Instance variables
	private String username;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String phone;
	
	
	// Constructors
	
	public Customer()
	{
		username = null;
		email = null;
		password = null;
		name = null;
		surname = null;
		phone = null;
	}
	
	
	// Get methods
	
	public String getUsername()
	{
		return username;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getPhone()
	{
		return phone;
	}
	
	
	// Set methods
	
	public void setUsername(String newUsername)
	{
		username = newUsername;
	}
	
	public void setEmail(String newEmail)
	{
		email = newEmail;
	}
	
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setSurname(String newSurname)
	{
		surname = newSurname;
	}
	
	public void setPhone(String newPhone)
	{
		phone = newPhone;
	}
	
	
	// Overriding from Object Class
	
	@Override
	public String toString()
	{
		return "" +username+
			   ", " +email+
			   ", " +password+
			   ", " +name+
			   ", " +surname+
			   ", " +phone;
	}
}
