package entity;

import java.util.Date;
import java.util.Collections;

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
	
	//�������ʹ��ʱ��
	@Column
	private Integer wantTime;
	
	//�����ύ����
	@Column
	private Integer applyTime;
	
	//��ʼʹ�õ�ʱ��
	@Column
	private Date startTime;
	
	//�ύ�����ʱ��
	@Column
	private Date beginApplyTime;
	
	//�Ƿ�ɹ�����
	@Column
	private boolean isSuccessApply = false;
	
	//�Ƿ�ֹͣ����
	@Column
	private boolean isStopApply = false;
	
	//�������ȼ�
	private Integer orderPriority=0;
	
	//������֤ID
	@ManyToOne(targetEntity=Group.class)
	@JoinColumn(name="group_id")
	private Group group;
	
	//����û�ID
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Application(){
		
	}
	public Application(Integer wantTime,Date startTime, Group group, User user){
		this.wantTime = wantTime;
		this.startTime = startTime;
		this.group = group;
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

	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public User getUser(){
		return user;
	}
	public void setUser(User user){
		this.user = user;
	}
	
	public Integer getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(Integer orderPriority) {
		this.orderPriority = orderPriority;
	}
	
	
}
