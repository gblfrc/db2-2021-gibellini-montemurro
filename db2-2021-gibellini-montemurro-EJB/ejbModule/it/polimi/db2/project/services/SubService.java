package it.polimi.db2.project.services;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.ValidityPeriod;


@Stateless
public class SubService {
	
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public ValidityPeriod getValidityPeriod(int months) {
		return em.find(ValidityPeriod.class, months);
	}
	
}
