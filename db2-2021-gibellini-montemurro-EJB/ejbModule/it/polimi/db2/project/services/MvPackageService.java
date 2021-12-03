package it.polimi.db2.project.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.db2.project.entities.MvPackage;

@Stateless
public class MvPackageService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public List<MvPackage> findAllPurchasesPerPackageAndValidityPeriod() {
		TypedQuery<MvPackage> query = em.createNamedQuery("MvPackage.findPurchasesPerPackageAndValidityPeriod", MvPackage.class);
		List<MvPackage> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=new LinkedList<>();
		}
		return result;
	}
	
	public List<Object[]> findAllPurchasesPerPackage() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.findDataPurchasePerPackage", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
}
