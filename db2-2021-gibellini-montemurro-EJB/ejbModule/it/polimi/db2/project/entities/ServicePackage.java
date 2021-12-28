package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "package")
@NamedQuery(name="ServicePackage.findAll", query="SELECT sp FROM ServicePackage sp")
public @Data class ServicePackage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="servpack",
				joinColumns=@JoinColumn(name="Package"),
				inverseJoinColumns=@JoinColumn(name="Service"))
	private List<Service> services;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="optionalpack",
			   joinColumns=@JoinColumn(name="Package"),
	           inverseJoinColumns=@JoinColumn(name="Product"))
	private List<OptionalProduct> optionalProducts;
		
	public boolean hasAllProducts(List<String> productsToCheck) {
		List<String> allProducts = new LinkedList<>();
		for(OptionalProduct op : optionalProducts) {
			allProducts.add(op.getName());
		}
		if (allProducts.containsAll(productsToCheck)) return true;
		else return false;		
	}
	
}
