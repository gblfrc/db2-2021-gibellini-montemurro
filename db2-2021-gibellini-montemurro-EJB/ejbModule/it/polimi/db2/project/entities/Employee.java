package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "employee")
@NamedQuery(name="Employee.employeeFromCredentials", query="SELECT e FROM Employee e WHERE e.username = ?1 and e.password = ?2")
public @Data class Employee implements Serializable, User {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Username")
	private String username;
	
	private String password;
	private String email;

}
