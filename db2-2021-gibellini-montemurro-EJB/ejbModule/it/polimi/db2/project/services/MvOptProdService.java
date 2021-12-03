package it.polimi.db2.project.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.db2.project.entities.MvOptProd;
import it.polimi.db2.project.entities.MvPackage;

@Stateless
public class MvOptProdService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public List<MvOptProd> findBestSeller() {
		TypedQuery<MvOptProd> query = em.createNamedQuery("MvOptProd.findBestSeller", MvOptProd.class);
		List<MvOptProd> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=new LinkedList<>();
		}
		return result;
	}
}

