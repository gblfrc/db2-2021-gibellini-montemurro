package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@IdClass(MvPackageId.class)
@Table(name = "mv_package")
@NamedQueries({
	@NamedQuery(name="MvPackage.findPurchasesPerPackageAndValidityPeriod", query="SELECT mv FROM MvPackage mv"),
	@NamedQuery(name="MvPackage.findDataPurchasePerPackage", query="SELECT mv.id_package, sum(mv.totPurchase), sum(mv.totRevenueWoOpt), sum(mv.totRevenueWOpt), sum(mv.totOptProd) FROM MvPackage mv GROUP BY mv.id_package")
})
public class MvPackage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	int id_package;
	@Id
	int months;
	int totPurchase;
	int totRevenueWoOpt;
	int totRevenueWOpt;
	int totOptProd;
	
	//getters
	public int getId_package() {
		return id_package;
	}
	
	public int getMonths() {
		return months;
	}
	
	public int getTotPurchase() {
		return totPurchase;
	}
	
	public int getTotRevenueWoOpt() {
		return totRevenueWoOpt;
	}
	
	public int getTotRevenueWOpt() {
		return totRevenueWOpt;
	}
	
	public int getTotOptProd() {
		return totOptProd;
	}
	
	//setters
	public void setId_package(int id_package) {
		this.id_package=id_package;
	}
	
	public void setMonths(int months) {
		this.months=months;
	}
	
	public void setTotPurchase(int totPurchase) {
		this.totPurchase=totPurchase;
	}
	
	public void setTotRevenueWoOpt(int totRevenueWoOpt) {
		this.totRevenueWoOpt=totRevenueWoOpt;
	}
	
	public void getTotRevenueWOpt(int totRevenueWOpt) {
		this.totRevenueWOpt=totRevenueWOpt;
	}
	
	public void setTotOptProd(int totOptProd) {
		this.totOptProd=totOptProd;
	}
}
