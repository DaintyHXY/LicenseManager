package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="license")
public class License {
	
	@Id
	@Column(name="licenseId",unique=true,nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer licenseId;
	
	@Column
	private String licenseName;
	
	//按天数计算
	@Column
	private Integer useLimitTime;
	
	public License(){}
	
	public License(String licenseName, Integer useLimitTime){
		this.licenseName = licenseName;
		this.useLimitTime = useLimitTime;
	}
	
	public int getLicenseId(){
		return licenseId;
	}
	
	public void setLicenseId(Integer licenseId){
		this.licenseId = licenseId;
	}
	
	public String getLicenseName(){
		return licenseName;
	}
	
	public void setLicenseName(String licenseName){
		this.licenseName = licenseName;
	}
	
	public Integer getUseLimitTime(){
		return useLimitTime;
	}
	
	public void setUserLimitTime(Integer useLimitTime ){
		this.useLimitTime = useLimitTime;
	}
	

}
