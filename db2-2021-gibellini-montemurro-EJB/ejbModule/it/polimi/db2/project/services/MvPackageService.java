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
	
	public List<Object[]> findAllPurchasesPerPackageAndValidityPeriod() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.findPurchasesPerPackageAndValidityPeriod",  Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
	
	public List<Object[]> findTotPurchase() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.findTotPurchase", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
	
	public List<Object[]> findTotRevWoOpt() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.revWoOpt", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
	
	public List<Object[]> findTotRevWOpt() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.revWOpt", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
	
	public List<Object[]> findAvgOpt() {
		TypedQuery<Object[]> query = em.createNamedQuery("MvPackage.avgOpt", Object[].class);
		List<Object[]> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
}
