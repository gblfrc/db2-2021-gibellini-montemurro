package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "client")
@NamedQueries({
@NamedQuery(name="Client.clientFromCredentials", query="SELECT c FROM Client c WHERE c.username = ?1 and c.password = ?2"),
@NamedQuery(name="Client.getInsolventClients", query="SELECT c FROM Client c WHERE c.insolvent=true"),
@NamedQuery(name="Client.getAlerts", query="SELECT c FROM Order o INNER JOIN o.subscription.user c GROUP BY c.username HAVING sum(o.refusedPayments)>=3 "),
})
public class Client implements Serializable, User {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Username")
	private String username;
	
	private String password;
	private String email;
	private boolean insolvent;

	// getters
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}
	
	public boolean getInsolvent() {
		return insolvent;
	}

	// setters
	public void setUsername(String usrn) {
		username = usrn;
	}

	public void setPassword(String pwd) {
		password = pwd;
	}

	public void setEmail(String em) {
		email = em;
	}
	
	public void setInsolvent(boolean ins) {
		insolvent = ins;
	}
}
