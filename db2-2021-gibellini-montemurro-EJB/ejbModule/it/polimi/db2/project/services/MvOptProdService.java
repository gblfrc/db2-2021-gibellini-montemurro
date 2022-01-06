package it.polimi.db2.project.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.db2.project.entities.MvOptProd;

@Stateless
public class MvOptProdService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	//This method retrieves best optional products
	public List<MvOptProd> findBestSeller() {
		TypedQuery<MvOptProd> query = em.createNamedQuery("MvOptProd.findBestSeller", MvOptProd.class);
		List<MvOptProd> result;
		try {
			result =query.getResultList();
			//refresh to avoid cache issues
			for(MvOptProd mop : result) {
				em.refresh(mop);
			}
		} catch (NoResultException e) {
			return result=new LinkedList<>();
		}
		return result;
	}
}

