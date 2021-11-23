package it.polimi.db2.project.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(name="Subscription.findAllByUser", query="SELECT s FROM Subscription s WHERE s.user = ?1")
public class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int amount;

	@Column(name="Package")
	private int package_;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	//may be useless to use a relationship, may simply need username
	//may need to introduce this relationship to use it from client
	//(on the other direction)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User")
	private Client user;

	//bi-directional many-to-one association to Validityperiod
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ValidityPeriod")
	private ValidityPeriod validityperiod;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="optionalsub",
			   joinColumns=@JoinColumn(name="Subscription"),
	           inverseJoinColumns=@JoinColumn(name="Product"))
	private List<OptionalProduct> optionalProductsSub;

	
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

	public Client getUser() {
		return this.user;
	}

	public void setUser(Client user) {
		this.user = user;
	}

	public ValidityPeriod getValidityperiod() {
		return this.validityperiod;
	}

	public void setValidityperiod(ValidityPeriod validityperiod) {
		this.validityperiod = validityperiod;
	}
	
	public void setOptionalProductsSub(List<OptionalProduct> products) {
		this.optionalProductsSub = products;
	}
	
	public List<OptionalProduct> getOptionalProductsSub(){
		return optionalProductsSub;
	}

}