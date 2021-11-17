package it.polimi.db2.project.entities;

public abstract class User {

	private String username;
	private String password;
	private String email;

	// getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	// setters
	public void setUsername(String usrn) {
		username = usrn;
	}

	public void setPassword(String pwd) {
		password = pwd;
	}

	public void setEmail(String em) {
		email = em;
	}

}
