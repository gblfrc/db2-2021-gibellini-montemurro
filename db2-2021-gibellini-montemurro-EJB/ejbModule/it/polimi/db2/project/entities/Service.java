package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "service")
@NamedQuery(name="Service.findAllServices", query="SELECT s FROM Service s")
public class Service implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String type;
	private int includedGB;
	private int extraGBFee;
	private int includedMinutes;
	private int extraMinutesFee;
	private int includedSMS;
	private int extraSMSFee;
	
	//may be useless
	@ManyToMany(mappedBy="services", fetch=FetchType.LAZY)
	private List<ServicePackage> packages;

	//getters
	public int getId() {
		return id;
	}
	
	public String getType() {
		return type;
	}

	public int getIncludedGB() {
		return includedGB;
	}

	public int getExtraGBFee() {
		return extraGBFee;
	}

	public int getIncludedMinutes() {
		return includedMinutes;
	}

	public void setIncludedGB(int includedGB) {
		this.includedGB = includedGB;
	}

	public int getExtraMinutesFee() {
		return extraMinutesFee;
	}
	
	public int getIncludedSMS() {
		return includedSMS;
	}

	public int getExtraSMSFee() {
		return extraSMSFee;
	}
	
	//setters
	public void setType(String type) {
		this.type = type;
	}

	public void setExtraGBFee(int extraGBFee) {
		this.extraGBFee = extraGBFee;
	}

	public void setIncludedMinutes(int includedMinutes) {
		this.includedMinutes = includedMinutes;
	}

	public void setExtraMinutesFee(int extraMinutesFee) {
		this.extraMinutesFee = extraMinutesFee;
	}

	public void setIncludedSMS(int includedSMS) {
		this.includedSMS = includedSMS;
	}

	public void setExtraSMSFee(int extraSMSFee) {
		this.extraSMSFee = extraSMSFee;
	}
	
}
