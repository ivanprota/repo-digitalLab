package it.unisa.model;

public class Administrator 
{
	// Instance variables
	private String username;
	private String name;
	private String surname;
	private String password;
	
	
	// Constructors
	
	public Administrator()
	{
		username = null;
		name = null;
		surname = null;
		password = null;
	}
	
	
	// Get methods
	
	public String getUsername()
	{
		return username;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	
	// Set methods
	
	public void setUsername(String newUsername)
	{
		username = newUsername;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void setSurname(String newSurname)
	{
		surname = newSurname;
	}
	
	public void setPassword(String newPassword)
	{
		password = newPassword;
	}
	
	
	// Overriding from Object class
	
	@Override
	public String toString()
	{
		return "" +username+
			   ", " +name+
			   ", " +surname+
			   ", " +password;
	}
}
