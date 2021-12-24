package it.polimi.db2.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public @Data class Subscription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Package")
	private ServicePackage package_;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	private String user;

	// bi-directional many-to-one association to ValidityPeriod
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ValidityPeriod")
	private ValidityPeriod validityPeriod;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "optionalsub", joinColumns = @JoinColumn(name = "Subscription"), inverseJoinColumns = @JoinColumn(name = "Product"))
	private List<OptionalProduct> optionalProductsSub;

	public Date getDeactivationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getStartDate());
		calendar.add(Calendar.MONTH, getValidityPeriod().getMonths());
		return calendar.getTime();
	}

	public int getAmount() {
		int vp = getValidityPeriod().getMonths();
		int amount = vp * getValidityPeriod().getMonthlyFee();
		for (OptionalProduct op : getOptionalProductsSub()) {
			amount = amount + op.getMonthlyFee() * vp;
		}
		return amount;
	}
}