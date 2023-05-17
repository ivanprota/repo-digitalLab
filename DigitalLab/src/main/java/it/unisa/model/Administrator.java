package it.unisa.model;

public class Administrator 
{
	// Variabili di istanza
	private String username;
	private String name;
	private String surname;
	private String password;
	
	
	// Costruttori
	
	public Administrator()
	{
		username = null;
		name = null;
		surname = null;
		password = null;
	}
	
	
	// Metodi di accesso
	
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
	
	
	// Metodi di modifica
	
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
}
