package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
@NamedQueries({
	@NamedQuery(name="Order.findAllByUser", query="SELECT o FROM Order o WHERE o.subscription.user = ?1"),
	@NamedQuery(name="Order.findAllInvalidByUser", query="SELECT o FROM Order o WHERE o.subscription.user = ?1 and o.validity = false"),
	@NamedQuery(name="Order.findBySubscription", query="SELECT o FROM Order o WHERE o.subscription = :sub"),
	@NamedQuery(name="Order.suspendedOrders",query="SELECT o.id, o.creationDate, o.creationTime FROM Order o WHERE o.validity=false"),
	@NamedQuery(name="Order.findById", query="SELECT o FROM Order o WHERE o.id = :id")
})
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	//@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	private Time creationTime;

	private boolean validity;
	private int refusedPayments;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="subscription")
	private Subscription subscription;
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getValidity() {
		return validity;
	}

	public void setValidity(boolean validity) {
		this.validity = validity;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Time getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public int getRefusedPayments() {
		return refusedPayments;
	}

	public void setRefusedPayments(int refusedPayments) {
		this.refusedPayments = refusedPayments;
	}
	
	public Subscription getSubscription() {
		return this.subscription;
	}

}