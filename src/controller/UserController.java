package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.User;
import service.UserService;

import java.util.logging.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;


@Controller
@SessionAttributes("user")
public class UserController  {	
	
	@Autowired
	private UserService userservice;
	
	private static final Log logger = LogFactory.getLog(UserController.class);
	
	@RequestMapping("/")
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
	public String loginAndSign(User user,String action,Model model )throws Exception{
		if(action.equals("login")){
			logger.info("login called");
			logger.info(user.getName());
			logger.info(user.getPwd());
			ModelAndView mv = new ModelAndView();
			
			User searchUser = userservice.findByUnique(user, "name", user.getName());

			if(searchUser.getPwd().equals(user.getPwd())){
				logger.info("login success");
				
				//model.addFlashAttribute("msg", searchUser.getName());
				model.addAttribute("user", searchUser);
				
				
				return "redirect:/toHome";
			}
		
			
			return "register";
			
		}
		else if(action.equals("sign")){
			logger.info("sign called");
			return "register";
		}
		return null;
	}

//	private ModelAndView login(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@RequestMapping(value="/register")
	public ModelAndView register(User user,String action,Model model) throws Exception{
		
		logger.info("do register");
		logger.info(user.getName());
		logger.info(user.getPwd());
		User a = new User();
		a.setName(user.getName());
		a.setPwd(user.getPwd());
		userservice.save(a);
		model.addAttribute("user", a);
		
		return new ModelAndView("main");
	}
	
	@RequestMapping(value="/main")
	public String toSuccess(){
		return "main";
	}

	@RequestMapping("/toHome")
	public String toHome(@ModelAttribute("msg") String msg, Model model,HttpSession session){
		logger.info("msg:"+msg);
		logger.info(session.getAttribute("user"));
		//model.addAttribute("msg",msg);
		return "main";
	}
}
