package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "optionalproduct")
@NamedQuery(name="OptionalProduct.findAllOptionalProducts", query="SELECT opt FROM OptionalProduct opt")
public @Data class OptionalProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;
	
	private int monthlyFee;
	
}
