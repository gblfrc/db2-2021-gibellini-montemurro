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
	@NamedQuery(name="Order.findPurchasesPerPackage", query="SELECT o.subscription.package_,count (o) FROM Order o WHERE o.validity=true GROUP BY o.subscription.package_"),
	@NamedQuery(name="Order.findPurchasesPerPackageAndValidityPeriod", query="SELECT o.subscription.package_,o.subscription.validityperiod.months,count (o) FROM Order o WHERE o.validity=true GROUP BY o.subscription.package_,o.subscription.validityperiod.months"),
	@NamedQuery(name="Order.amountWithOptional",query="SELECT o.subscription.package_,sum(o.subscription.amount) FROM Order o WHERE o.validity=true GROUP BY o.subscription.package_"),
	@NamedQuery(name="Order.suspendedOrders",query="SELECT o FROM Order o WHERE o.validity=false"),
	@NamedQuery(name="Order.getBestSeller", query="SELECT opt.name FROM Order o INNER JOIN o.subscription.optionalProductsSub opt WHERE o.validity=true GROUP BY opt.name HAVING count(o) >= ALL(SELECT count(o1) FROM Order o1 INNER JOIN o1.subscription.optionalProductsSub opt1 WHERE o1.validity=true GROUP BY opt1.name) "),

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