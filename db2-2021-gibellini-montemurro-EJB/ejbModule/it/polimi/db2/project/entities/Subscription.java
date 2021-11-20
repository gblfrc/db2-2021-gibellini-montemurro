package it.polimi.db2.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the subscription database table.
 * 
 */
@Entity
@NamedQuery(name="Subscription.findAll", query="SELECT s FROM Subscription s")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int amount;

	@Column(name="Package")
	private int package_;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	private String user;

	//bi-directional many-to-one association to Validityperiod
	@ManyToOne
	@JoinColumn(name="ValidityPeriod")
	private Validityperiod validityperiod;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="optionalsub",
			   joinColumns=@JoinColumn(name="Subscription"),
	           inverseJoinColumns=@JoinColumn(name="Product"))
	private List<OptionalProduct> optionalProductsSub;

	public Subscription() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPackage_() {
		return this.package_;
	}

	public void setPackage_(int package_) {
		this.package_ = package_;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Validityperiod getValidityperiod() {
		return this.validityperiod;
	}

	public void setValidityperiod(Validityperiod validityperiod) {
		this.validityperiod = validityperiod;
	}

}