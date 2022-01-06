package it.polimi.db2.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.db2.project.entities.AuditingTable;

@Stateless
public class AuditingService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	
	// This method retrieves all alerts in auditing table
	public List<AuditingTable> findAlerts() {
		TypedQuery<AuditingTable> query = em.createNamedQuery("AuditingTable.findAlerts", AuditingTable.class);
		List<AuditingTable> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
}
