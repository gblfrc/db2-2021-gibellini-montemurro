package it.polimi.db2.project.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "auditingtable")
@NamedQuery(name="AuditingTable.findAlerts", query="SELECT a FROM AuditingTable a")
public @Data class AuditingTable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
		
	private String username;
	private String email;
	private int amount;
	private Date rejectionDate;
	private Time rejectionTime;
		
}
