package it.polimi.db2.project.entities;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Entity
@NamedQuery(name="ValidityPeriod.findAll", query="SELECT v FROM ValidityPeriod v")
public @Data class ValidityPeriod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int months;

	private int monthlyFee;

}