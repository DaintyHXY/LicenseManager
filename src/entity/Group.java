package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="l_roup")
public class Group {
	
	@Id
	@Column(name="groupId",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer groupId;
	
	@Column
	private String groupName;
	
	//组内许可证的数量
	@Column 
	private Integer numInGroup;
	
	//组内许可证简介
	@Column
	private String groupIntroduction;
	
	@Column
	private Integer useLimitTime;

	public Integer getUseLimitTime() {
		return useLimitTime;
	}

	public void setUseLimitTime(Integer useLimitTime) {
		this.useLimitTime = useLimitTime;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getNumInGroup() {
		return numInGroup;
	}

	public void setNumInGroup(Integer numInGroup) {
		this.numInGroup = numInGroup;
	}

	public String getGroupIntroduction() {
		return groupIntroduction;
	}

	public void setGroupIntroduction(String groupIntroduction) {
		this.groupIntroduction = groupIntroduction;
	}
}
