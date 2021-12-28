package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@IdClass(MvPackageId.class)
@Table(name = "mv_package")
@NamedQueries({
	@NamedQuery(name="MvPackage.findPurchasesPerPackageAndValidityPeriod", query="SELECT mv.packName, mv.months, mv.totPurchase FROM MvPackage mv"),
	@NamedQuery(name="MvPackage.findTotPurchase", query="SELECT mv.packName, sum(mv.totPurchase) FROM MvPackage mv GROUP BY mv.id_package"),
	@NamedQuery(name="MvPackage.revWoOpt", query="SELECT mv.packName, sum(mv.totRevenueWoOpt) FROM MvPackage mv GROUP BY mv.id_package"),
	@NamedQuery(name="MvPackage.revWOpt", query="SELECT mv.packName, sum(mv.totRevenueWOpt) FROM MvPackage mv GROUP BY mv.id_package"),
	@NamedQuery(name="MvPackage.avgOpt", query="SELECT mv.packName, sum(mv.totPurchase), sum(mv.totOptProd) FROM MvPackage mv GROUP BY mv.id_package")
})
public @Data class MvPackage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	int id_package;
	@Id
	int months;
	String packName;
	int totPurchase;
	int totRevenueWoOpt;
	int totRevenueWOpt;
	int totOptProd;
	
}
