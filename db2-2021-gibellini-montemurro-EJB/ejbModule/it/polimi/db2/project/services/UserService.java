package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Client;

@Stateless
public class UserService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public Client getClient(String username, String password) {
		Query query = em.createNamedQuery("Client.checkCredentials", Client.class);
		query.setParameter(1, username);
		query.setParameter(2, password);
		Client result =(Client) query.getResultList().get(0);
		return result;
	}
	
	
}
