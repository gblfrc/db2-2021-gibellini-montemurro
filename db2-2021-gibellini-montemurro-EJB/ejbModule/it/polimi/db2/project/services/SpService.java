package it.polimi.db2.project.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Employee;
import it.polimi.db2.project.entities.ServicePackage;

@Stateless
public class SpService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	public List<ServicePackage> getAllPackages(){
		TypedQuery<ServicePackage> query = em.createNamedQuery("ServicePackage.findAll", ServicePackage.class);
		List<ServicePackage> result = query.getResultList();
		return result;
	}
}
