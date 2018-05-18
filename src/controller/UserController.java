package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;

import java.util.logging.*;
import java.io.IOException;


@Controller
public class UserController  {	
	
	@Autowired
	private UserService userservice;
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@RequestMapping("/index")
	public String hello(){
		
		//ModelAndView mv =new ModelAndView();
		//mv.addObject("spring", "springmvc");
		//mv.setViewName("hello");
		
		logger.info("index called");
		
		return "index";
		
		
	}
	//登录判定
	//目前还没做的：1.与数据库连接，判定是否是用户 2.成功登录保持状态跳转
	@RequestMapping(value="/LoginAndSign")
	public ModelAndView loginAndSign(User user,String action)throws Exception{
		if(action.equals("login")){
			logger.info("login called");
			logger.info(user.getName());
			logger.info(user.getPwd());
			ModelAndView mv = new ModelAndView();
//			if(userservice.login(user) != null){
//			 mv.addObject(userservice.login(user));
//			 return new ModelAndView("success");}
			
			userservice.addUser(user);
			
			return new ModelAndView("register");
			
		}
		else if(action.equals("sign")){
			logger.info("sign called");
			return new ModelAndView("register");
		}
		return null;
	}

//	private ModelAndView login(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
