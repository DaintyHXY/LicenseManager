package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="record")
public class Record {
	
	@Id
	@Column(name="id",unique=true,nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordId;
	
	@Column
	private Date startTime;
	
//	@Column
//	private Integer useTime;
	
	@ManyToOne(targetEntity=License.class)
	@JoinColumn(name="license_id")
	private License license;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	
	public Record(){}
	public Record(License license, User user){
		this.license = license;
		this.user = user;
	}
	
	public Integer getRecordId(){
		return recordId;
	}
	
	public void setRecordId(int recordId){
		this.recordId = recordId;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public License getLicense(){
		return license;
	}
	public void setLicense(License license){
		this.license = license;
	}
	
	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	

}
