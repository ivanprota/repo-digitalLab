package it.unisa.model;

public class Customer 
{
	// Variabili di istanza
	private String username;
	private String email;
	private String password;
	private String name;
	private String surname;
	private String phone;
	
	
	// Costruttori
	
	public Customer()
	{
		username = null;
		email = null;
		password = null;
		name = null;
		surname = null;
		phone = null;
	}
	
	
	// Metodi di accesso
	
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
	
	
	// Metodi di modifica
	
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
}
