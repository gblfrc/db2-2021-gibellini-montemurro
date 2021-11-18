package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Employee;

@Stateless
public class UserService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	//method to get a client given the username and the password
	//returns null if no user is found
	public Client getClient(String username, String password) {
		TypedQuery<Client> query = em.createNamedQuery("Client.clientFromCredentials", Client.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		Client result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return result;
	}
	
	//method to get an employee given the username and the password
	//returns null if no user is found
	public Employee getEmployee(String username, String password) {
		TypedQuery<Employee> query = em.createNamedQuery("Employee.employeeFromCredentials", Employee.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		Employee result;
		try {
			result = query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return result;
	}
	
	
}
