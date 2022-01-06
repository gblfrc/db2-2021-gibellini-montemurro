package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Subscription;


@Stateless
public class SubService {
	
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	//this method persists the subscription
	public void persistSubscription (Subscription sub) {
		em.persist(sub);
	}
	
	//this method refreshes the subscription
	public void refreshSubscription (Subscription sub) {
		em.refresh(sub);
	}
}
