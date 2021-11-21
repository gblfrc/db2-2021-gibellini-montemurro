package it.polimi.db2.project.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Client;
import it.polimi.db2.project.entities.Order;

@Stateless
public class OrderService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;

	// method to get the list of currently invalid orders given the username of a client
	// returns empty list if no order is found
	public List<Order> getInvalidOrdersByClient(Client client) {
		TypedQuery<Order> query = em.createNamedQuery("Order.findAllInvalidByUser", Order.class);
		query.setParameter(1, client);
		List<Order> result;
		try {
			result = query.getResultList();
		} catch (NoResultException e) {
			return result=new LinkedList<>();
		}
		return result;
	}
}
