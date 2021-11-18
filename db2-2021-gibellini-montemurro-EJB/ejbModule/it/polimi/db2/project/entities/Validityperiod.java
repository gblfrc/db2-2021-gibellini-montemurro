package it.polimi.db2.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name="Validityperiod.findAll", query="SELECT v FROM Validityperiod v")
public class Validityperiod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int months;

	private int monthlyFee;

	//bi-directional many-to-one association to Subscription
	@OneToMany(mappedBy="validityperiod")
	private List<Subscription> subscriptions;

	public Validityperiod() {
	}

	public int getMonths() {
		return this.months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public int getMonthlyFee() {
		return this.monthlyFee;
	}

	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public List<Subscription> getSubscriptions() {
		return this.subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Subscription addSubscription(Subscription subscription) {
		getSubscriptions().add(subscription);
		subscription.setValidityperiod(this);

		return subscription;
	}

	public Subscription removeSubscription(Subscription subscription) {
		getSubscriptions().remove(subscription);
		subscription.setValidityperiod(null);

		return subscription;
	}

}