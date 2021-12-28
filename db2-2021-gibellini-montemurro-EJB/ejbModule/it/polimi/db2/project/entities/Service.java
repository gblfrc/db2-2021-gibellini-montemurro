package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "service")
@NamedQuery(name="Service.findAllServices", query="SELECT s FROM Service s")
public @Data class Service implements Serializable {

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
	
}
