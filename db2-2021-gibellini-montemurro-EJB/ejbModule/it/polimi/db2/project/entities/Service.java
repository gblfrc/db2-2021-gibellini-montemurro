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
	private Integer includedGB;
	private Double extraGBFee;
	private Integer includedMinutes;
	private Double extraMinutesFee;
	private Integer includedSMS;
	private Double extraSMSFee;
	
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

	public Integer getIncludedGB() {
		return includedGB;
	}

	public Double getExtraGBFee() {
		return extraGBFee;
	}

	public Integer getIncludedMinutes() {
		return includedMinutes;
	}

	public void setIncludedGB(Integer includedGB) {
		this.includedGB = includedGB;
	}

	public Double getExtraMinutesFee() {
		return extraMinutesFee;
	}
	
	public Integer getIncludedSMS() {
		return includedSMS;
	}

	public Double getExtraSMSFee() {
		return extraSMSFee;
	}
	
	//setters
	public void setType(String type) {
		this.type = type;
	}

	public void setExtraGBFee(Double extraGBFee) {
		this.extraGBFee = extraGBFee;
	}

	public void setIncludedMinutes(Integer includedMinutes) {
		this.includedMinutes = includedMinutes;
	}

	public void setExtraMinutesFee(Double extraMinutesFee) {
		this.extraMinutesFee = extraMinutesFee;
	}

	public void setIncludedSMS(Integer includedSMS) {
		this.includedSMS = includedSMS;
	}

	public void setExtraSMSFee(Double extraSMSFee) {
		this.extraSMSFee = extraSMSFee;
	}
	
}
