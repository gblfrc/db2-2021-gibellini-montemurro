package it.polimi.db2.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.ValidityPeriod;


@Stateless
public class VPService {
	
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public List<ValidityPeriod> getAllValidityPeriod() {
		TypedQuery<ValidityPeriod> query = em.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class);
		return query.getResultList();
	}
	
}