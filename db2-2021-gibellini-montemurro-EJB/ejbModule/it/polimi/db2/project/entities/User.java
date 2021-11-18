package it.polimi.db2.project.entities;

public interface User {

	// getters
	public String getUsername(); 

	public String getPassword();

	public String getEmail();

	// setters
	public void setUsername(String usrn);

	public void setPassword(String pwd);

	public void setEmail(String em);

}
