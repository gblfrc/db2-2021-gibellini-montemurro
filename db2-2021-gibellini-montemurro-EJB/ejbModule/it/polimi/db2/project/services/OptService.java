package it.polimi.db2.project.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.OptionalProduct;


@Stateless
public class OptService {
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	/*
	 * This method retrieves all optional products
	 */
	public List<OptionalProduct> findAllOptProducts(){
		TypedQuery<OptionalProduct> query = em.createNamedQuery("OptionalProduct.findAllOptionalProducts", OptionalProduct.class);
		List<OptionalProduct> optionalProducts = query.getResultList();
		return optionalProducts;
	}
	
	/*
	 * This method adds in DB an optional product
	 */
	public void addOptionalProduct(String name,int monthlyFee) {
		OptionalProduct optionalProduct = new OptionalProduct();
		optionalProduct.setName(name);
		optionalProduct.setMonthlyFee(monthlyFee);
		em.persist(optionalProduct);
	}
	
	/*
	 * This method finds all the optional products selected
	 */
	public List<OptionalProduct> findProductsSelected(String[] optProdList){
		List<OptionalProduct> optionals=new ArrayList<OptionalProduct>();
		for(int i=0;i<optProdList.length;i++) {
			if(optProdList[i]!=null)optionals.add(em.find(OptionalProduct.class, optProdList[i]));
		}
		return optionals;
	}
}