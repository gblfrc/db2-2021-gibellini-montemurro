package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "mv_optprod")
@NamedQuery(name="MvOptProd.findBestSeller", query="SELECT mv FROM MvOptProd mv WHERE mv.tot_revenue= (SELECT max(mv.tot_revenue) FROM MvOptProd mv)")
public class MvOptProd implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	String id_optprod;
	
	int tot_revenue;
	
	//getters 
	public String getId_optprod() {
		return id_optprod;
	}
	
	public int getTot_revenue() {
		return tot_revenue;
	}
	
	//setters
	public void setId_optprod(String id_optprod) {
		this.id_optprod=id_optprod;
	}
	
	public void setTot_revenue(int tot_revenue) {
		this.tot_revenue=tot_revenue;
	}
}
