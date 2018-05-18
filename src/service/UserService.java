package service;

import org.springframework.stereotype.Service;

import entity.User;



public interface UserService {
	
	public void addUser(User user);
	
	public void userLogin(User user);
	
	public void userRegister(User user);
	
	public void userUpdate(User user);

}
