package it.polimi.db2.project.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import it.polimi.db2.project.entities.Order;
import it.polimi.db2.project.entities.Subscription;

@Stateless
public class OrderService {

	@PersistenceContext(name = "project_pc")
	private EntityManager em;

	// method to get the list of currently invalid orders given the username of a client
	// returns empty list if no order is found
	public List<Order> getInvalidOrdersByClient(String client) {
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

	// this method retrieves all suspended orders
	public List<Order> getSuspendedOrders(){
		TypedQuery<Order> query = em.createNamedQuery("Order.suspendedOrders", Order.class);
		List<Order> result;
		try {
			result =query.getResultList();
		} catch (NoResultException e) {
			return result=null;
		}
		return result;
	}
	
	// this method retrieves the order associated to the subscription
	public Order getOrderBySubscription(Subscription sub) {
		TypedQuery<Order> query = em.createNamedQuery("Order.findBySubscription", Order.class);
		query.setParameter("sub", sub);
		Order result = query.getSingleResult(); 
		return result;
	}
	
	// this method persists order
	public void persistOrder(Order order) {
		em.persist(order);
	}
	
	// this method refreshes order
	public void refreshOrder(Order order) {
		em.refresh(order);
	}
	
	// this method merges order
	public void mergeOrder(Order order) {
		em.merge(order);
	}
	
	// this method retrieves the order by its id
	public Order findOrderById(int id) {
		TypedQuery<Order> query = em.createNamedQuery("Order.findById", Order.class);
		query.setParameter("id", id);
		Order result = query.getSingleResult(); 
		return result;
	}
	
}
