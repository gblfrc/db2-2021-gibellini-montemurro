package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "mv_optprod")
@NamedQuery(name="MvOptProd.findBestSeller", query="SELECT mv FROM MvOptProd mv WHERE mv.tot_revenue= (SELECT max(mv.tot_revenue) FROM MvOptProd mv)")
public @Data class MvOptProd implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	String id_optprod;
	
	int tot_revenue;
	
}
