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

}
