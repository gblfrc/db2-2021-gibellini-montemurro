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
	
	public List<Service> findAllServices(){
		TypedQuery<Service> query = em.createNamedQuery("Service.findAllServices", Service.class);
		List<Service> services = query.getResultList();
		return services;
	}
	
	public List<Service> findServicesSelected(String[] serviceList){
		List<Service> services=new ArrayList<Service>();
		for(int i=0;i<serviceList.length;i++) {	
			if(serviceList[i]!=null) {
				System.out.println("SEMPRE QUI");
				int myNum=Integer.parseInt(serviceList[i]);
				services.add(em.find(Service.class,myNum));
			}
		}
		return services;
	}
}
