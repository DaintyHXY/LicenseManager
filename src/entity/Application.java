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
@Table(name="application")
public class Application {
	
	@Id
	@Column(name="applicationId",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer applicationId;
	
	//想申请的使用时间
	@Column
	private Integer wantTime;
	
	//申请提交天数
	@Column
	private Integer applyTime;
	
	//开始使用的时间
	@Column
	private Date startTime;
	
	//提交申请的时间
	@Column
	private Date beginApplyTime;
	
	//是否成功申请
	@Column
	private boolean isSuccessApply = false;
	
	//是否停止申请
	@Column
	private boolean isStopApply = false;
	
	//外键许可证ID
	@ManyToOne(targetEntity=License.class)
	@JoinColumn(name="license_id")
	private License license;
	
	//外键用户ID
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Application(){
		
	}
	public Application(Integer wantTime,Date startTime, License license, User user){
		this.wantTime = wantTime;
		this.startTime = startTime;
		this.license = license;
		this.user = user;
	}
	
	public int getApplicationId(){
		return applicationId;
	}
	
	public void setApplicationId(int applicationId){
		this.applicationId = applicationId;
	}
	
	public int getWantTime(){
		return wantTime;
	}
	public void setWantTime(int wantTime){
		this.wantTime = wantTime;
	}
	
	public int getApplyTime(){
		return applyTime;
	}
	public void setApplyTime(int applyTime){
		this.applyTime = applyTime;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public Date getBeginApplyTime(){
		return beginApplyTime;
	}
	public void setBeginApplyTime(Date beginApplyTime){
		this.beginApplyTime = beginApplyTime;
	}
	
	public boolean getIsSuccessApply(){
		return isSuccessApply;
	}
	public void setIsSuccessApply(boolean isSuccessApply){
		this.isSuccessApply = isSuccessApply;
	}
	
	public boolean getIsStopApply(){
		return isStopApply;
	}
	public void setIsStopApply(boolean isStopApply){
		this.isStopApply = isStopApply;
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
