package it.polimi.db2.project.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "client")
@NamedQueries({
@NamedQuery(name="Client.clientFromCredentials", query="SELECT c FROM Client c WHERE c.username = ?1 and c.password = ?2"),
@NamedQuery(name="Client.getInsolventClients", query="SELECT c FROM Client c WHERE c.insolvent=true"),
})
public @Data class Client implements Serializable, User{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Username")
	private String username;
	
	private String password;
	private String email;
	private boolean insolvent;

}
