package it.polimi.db2.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.OptionalProduct;
import it.polimi.db2.project.entities.Service;
import it.polimi.db2.project.entities.ServicePackage;

@Stateless
public class SpService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	/*
	 * This method retrieves all the service packages
	 */
	public List<ServicePackage> getAllPackages(){
		TypedQuery<ServicePackage> query = em.createNamedQuery("ServicePackage.findAll", ServicePackage.class);
		List<ServicePackage> result = query.getResultList();
		return result;
	}
	
	/*
	 * This method adds a service package
	 */
	public void addServicePackage(String name,List<Service> services,List<OptionalProduct> optionalProducts) {
		ServicePackage servicePackage= new ServicePackage();
		servicePackage.setName(name);
		servicePackage.setServices(services);
		servicePackage.setOptionalProducts(optionalProducts);
		em.persist(servicePackage);
	}
}
