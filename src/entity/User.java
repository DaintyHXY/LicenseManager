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
	
	public User(){}
	public User(String name,String pwd){
		this.name = name;
		this.pwd = pwd;
	}
	
	public int getId(){
		return id;
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

}
