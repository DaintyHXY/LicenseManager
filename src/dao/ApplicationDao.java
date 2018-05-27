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
		System.out.println("�û����ȼ��� "+userPriority+"----����ʱ�䣺 "+applyTime+"-----���ȼ�: "+priority);
        return priority;
	}

}
