package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.User;
import service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

import javax.servlet.http.HttpSession;

import java.io.IOException;


@Controller
@SessionAttributes({"user","msg"})
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
	//5.24更新：
	@RequestMapping(value="/toLogin")
	@ResponseBody
	public  Map<String,Object> login(@RequestBody User user ,Model model )throws Exception{
		     
			logger.info("login called");
			logger.info(user.getName());
			logger.info(user.getPwd());
			ModelAndView mv = new ModelAndView();
			
			Map<String,Object> map = new  HashMap<String,Object>();
			
			User searchUser = userservice.findByUnique("name", user.getName());
            //map.put("name", searchUser.getName());
			if(searchUser == null){
            	model.addAttribute("msg", "用户不存在");
            	map.put("msg", "用户不存在");
            	return map;}
			
            else if(searchUser.getPwd().equals(user.getPwd())){
            	
            	
				logger.info("login success");
				
				//model.addFlashAttribute("msg", searchUser.getName());
				model.addAttribute("user", searchUser);
				map.put("msg", searchUser.getName());
				return map;
			}
            else {
            	model.addAttribute("msg", "未知错误");
            	map.put("msg", "未知错误");
            	return map;
            	}
		
			
			
			
		
//		else if(action.equals("sign")){
//			logger.info("sign called");
//			return null;
//		}
//		
//		logger.info("未知错误");
//		result="sorry";
//		return null;
	}

//	private ModelAndView login(User user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@RequestMapping(value="/register")
	public String register(User user,String action,Model model) throws Exception{
		
		logger.info("do register");
		logger.info(user.getName());
		logger.info(user.getPwd());
		User a = new User();
		a.setName(user.getName());
		a.setPwd(user.getPwd());
		userservice.save(a);
		model.addAttribute("user", a);
		
		return "redirect:/main";
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
	
	@RequestMapping("/wrong")
	@ResponseBody
	public Model wrongSolve(Model model){
		
		logger.info("wrong called");
		
		return model;
		
	}
}
