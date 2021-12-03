package it.polimi.db2.project.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "auditingtable")
@NamedQuery(name="AuditingTable.findAlerts", query="SELECT c.username, c.email, o.subscription.amount, a.rejectionDate, a.rejectionTime FROM AuditingTable a, Order o, Client c WHERE a.order=o.id AND o.subscription.user.username=c.username")
public class AuditingTable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="order")
	private int order;
		
	private Date rejectionDate;
	private Time rejectionTime;
	
	//getters
	public int getOrder() {
		return order;
	}
	
	public Date getRejectionDate(){
		return rejectionDate;
	}
	
	public Time getRejectionTime(){
		return rejectionTime;
	}
	
	//setters
	public void setRejectionDate(Date date){
		rejectionDate=date;
	}
	
	public void setRejectionTime(Time time){
		rejectionTime=time;
	}
	
	
}
