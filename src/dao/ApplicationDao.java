package dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Application;

@Transactional
@Repository
public class ApplicationDao extends BaseDaoImpl<Application> {

	@Override
	protected Class<Application> getEntityClass() {
		
		return Application.class;
	}
	
    public int countPriority(int userPriority,int applyTime){
        
		int priority =  (int) (userPriority*0.6+applyTime*0.4);
		System.out.println("用户优先级： "+userPriority+"----申请时间： "+applyTime+"-----优先级: "+priority);
        return priority;
	}

}
