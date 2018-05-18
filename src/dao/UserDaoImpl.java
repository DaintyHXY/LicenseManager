package dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

//	public UserDaoImpl(SessionFactory sessionFactory, Class<User> entityClass) {
//		super(sessionFactory, entityClass);
//		// TODO Auto-generated constructor stub
//	}

	@Override
	protected Class<User> getEntityClass(){
		return User.class;
	}
	
	
	
	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	

}
