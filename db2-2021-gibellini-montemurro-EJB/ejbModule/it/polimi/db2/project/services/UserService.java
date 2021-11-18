package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Client;

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
	
	
	
	
}
