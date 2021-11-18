package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "package")
@NamedQuery(name="ServicePackage.findAll", query="SELECT sp FROM ServicePackage sp")
public class ServicePackage implements Serializable {

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
	
	
	//getters
	public int getId() {
		return id;
	}
	
	public String name() {
		return name;
	}
	
	public List<Service> getServices(){
		return services;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
