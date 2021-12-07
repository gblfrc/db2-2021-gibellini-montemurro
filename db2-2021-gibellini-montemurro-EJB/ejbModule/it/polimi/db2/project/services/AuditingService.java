package it.polimi.db2.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class AuditingService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public List<Object[]> findAlerts() {
		TypedQuery<Object[]> query = em.createNamedQuery("AuditingTable.findAlerts", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
}
