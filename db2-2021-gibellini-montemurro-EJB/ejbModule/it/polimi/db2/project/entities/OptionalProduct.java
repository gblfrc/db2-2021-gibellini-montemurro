package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "optionalproduct")
@NamedQuery(name="OptionalProduct.findAllOptionalProducts", query="SELECT opt FROM OptionalProduct opt")
public class OptionalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="name")
	private String name;
	
	private int monthlyFee;
	
	//getters
	public String getName() {
		return name;
	}
	
	public int getMonthlyFee(){
		return monthlyFee;
	}
	
	//setters
	public void setName(String name) {
		this.name=name;
	}
	
	public void setMonthlyFee(int monthlyFee) {
		this.monthlyFee=monthlyFee;
	}
}
