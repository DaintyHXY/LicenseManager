package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;

@Service
public class UserServiceImpl implements UserService {

//	@Override
//	public User login(User user) {
//		// TODO Auto-generated method stub
//		//µÇÂ¼ÅÐ¶¨
//		if(user.getName().equals("aaa")){
//			if(user.getPwd().equals("bbb"))
//				return user;}
//		else return null;
//		return null;
//	}
	
	//×¢Èëdao
	@Autowired
	private UserDao userDao;
	

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
		userDao.addUser(user);
		
	}


	@Override
	public void userLogin(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void userRegister(User user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void userUpdate(User user) {
		// TODO Auto-generated method stub
		
	}

}
