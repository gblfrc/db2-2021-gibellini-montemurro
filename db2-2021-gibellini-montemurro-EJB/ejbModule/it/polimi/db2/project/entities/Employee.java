package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@NamedQuery(name="Employee.employeeFromCredentials", query="SELECT e FROM Employee e WHERE e.username = ?1 and e.password = ?2")
public class Employee implements Serializable, User {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Username")
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
