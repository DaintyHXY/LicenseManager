package dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.User;

@Transactional
@Repository
public class UserDao extends BaseDaoImpl<User>{

	@Override
	protected Class<User> getEntityClass() {
		
		return User.class;
	}
	
    public int countPriority(int ApplicationNum,int ApplicationFail){
        
		int priority =  (int) (ApplicationNum*0.6+ApplicationFail*0.4);
        return priority;
	}

}
