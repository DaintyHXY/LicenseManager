package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="id", unique=true,nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String pwd;
	
	//用户申请次数
	@Column
	private Integer applicationNum;
	
	//申请失败次数
	@Column
	private Integer applicationFail;
	
	//用户优先级
	@Column
	private Integer userPriority;
	
	public User(){}
	public User(String name,String pwd){
		this.name = name;
		this.pwd = pwd;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(Integer id){
		this.id  = id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPwd(){
		return pwd;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	
	public Integer getUserPriority(){
		return userPriority;
	}
	public void setUserPriority(int userPriority){
		this.userPriority = userPriority;
	}
	public Integer getApplicationNum() {
		return applicationNum;
	}
	public void setApplicationNum(Integer applicationNum) {
		this.applicationNum = applicationNum;
	}
	public Integer getApplicationFail() {
		return applicationFail;
	}
	public void setApplicationFail(Integer applicationFail) {
		this.applicationFail = applicationFail;
	}

	
}
