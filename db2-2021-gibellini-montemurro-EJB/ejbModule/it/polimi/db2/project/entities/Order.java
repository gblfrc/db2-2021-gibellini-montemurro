package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.*;

import lombok.Data;

import java.sql.Date;

@Entity
@Table(name="orders")
@NamedQueries({
	@NamedQuery(name="Order.findAllByUser", query="SELECT o FROM Order o WHERE o.subscription.user = ?1"),
	@NamedQuery(name="Order.findAllInvalidByUser", query="SELECT o FROM Order o WHERE o.subscription.user = ?1 and o.validity = false"),
	@NamedQuery(name="Order.findBySubscription", query="SELECT o FROM Order o WHERE o.subscription = :sub"),
	@NamedQuery(name="Order.suspendedOrders",query="SELECT o FROM Order o WHERE o.validity=false"),
	@NamedQuery(name="Order.findById", query="SELECT o FROM Order o WHERE o.id = :id")
})
public @Data class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private Date creationDate;
	private Time creationTime;
	private boolean validity;
	private int refusedPayments;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subscription")
	private Subscription subscription;
	
	private int amount;
	
	//necessary to have a getter for validity
	//lombok creates isValidity but thymeleaf requires getValidity
	public boolean getValidity() {
		return validity;
	}

}