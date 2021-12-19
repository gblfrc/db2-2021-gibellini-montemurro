package it.polimi.db2.project.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import it.polimi.db2.project.entities.Service;

@Stateless
public class ServService {
	
	@PersistenceContext(name = "project_pc")
	private EntityManager em;
	
	/*
	 * This method retrieves all services
	 */
	public List<Service> findAllServices(){
		TypedQuery<Service> query = em.createNamedQuery("Service.findAllServices", Service.class);
		List<Service> services = query.getResultList();
		return services;
	}
	
	/*
	 * This method retrieves all the selected services 
	 */
	public List<Service> findServicesSelected(String[] serviceList) throws Exception{
		List<Service> services=new ArrayList<Service>();
		Service serv;
		for(int i=0;i<serviceList.length;i++) {	
			if(serviceList[i]!=null) {
				serv=em.find(Service.class,Integer.parseInt(serviceList[i]));
				if(serv==null)throw new Exception();
				else services.add(serv);
			}
		}
		return services;
	}
	
	/*
	 * This method persists a service object 
	 */
	public void persistService(Service service) {
		em.persist(service);
	}
}
