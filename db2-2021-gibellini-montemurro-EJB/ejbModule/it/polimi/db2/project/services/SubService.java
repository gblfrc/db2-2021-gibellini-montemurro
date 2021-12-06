package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Subscription;
import it.polimi.db2.project.entities.ValidityPeriod;


@Stateless
public class SubService {
	
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public ValidityPeriod getValidityPeriod(int months) {
		return em.find(ValidityPeriod.class, months);
	}
	
	public void persistSubscription (Subscription sub) {
		em.persist(sub);
	}
	
	public void refreshSubscription (Subscription sub) {
		em.refresh(sub);
	}
}
