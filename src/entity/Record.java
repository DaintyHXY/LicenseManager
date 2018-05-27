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
	
	//使用开始时间
	@Column
	private Date startTime;
	
	//使用时长
	@Column
	private Integer useTime;
	
	//到期时间
	@Column
	private Date endTime;
	
	//是否停止使用
	@Column
	private boolean isStopUse = false;
	
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
	
	public Integer getUseTime(){
		return useTime;
	}
	public void setUseTime(int useTime){
		this.useTime = useTime;
	}
	
	public Date getEndTime(){
		return endTime;
	}
	public void setEndTime(Date endTime){
		this.endTime  = endTime;
	}
	
	public boolean getIsStopUse(){
		return isStopUse;
	}
	public void setIsStopUse(boolean isStopUse){
		this.isStopUse = isStopUse;
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
