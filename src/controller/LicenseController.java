package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import entity.Application;
import entity.Group;
import entity.License;
import entity.Record;
import entity.User;
import service.ApplicationService;
import service.GroupService;
import service.LicenseService;
import service.RecordService;
import service.UserService;

@Controller
@SessionAttributes("user")
@RequestMapping("/license")
public class LicenseController {
	
	@Autowired
	private LicenseService licenseService;
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private RecordService recordService;
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	
	private static final Log logger = LogFactory.getLog(License.class);
	
	//获得所有可以申请的许可证组
	@RequestMapping("/checkAllLicense")
	@ResponseBody
	public List<Group> checkLicense(Model model){
		logger.info("checkLicense called");
		logger.info(groupService.findAll().size());
		
		return groupService.findAll();
	}
	
	//获得已经提交申请的许可证信息
	@RequestMapping("/checkApplyingLicense")
	@ResponseBody
	public List<Application> applyingLicense(Model model, HttpSession session){
		
		User user = (User) session.getAttribute("user");
		String userId = String.valueOf(user.getId());
		List<Application> list = applicationService.findByProperty("user_id", userId);
		logger.info("applyingLicense"+list.size());
		
		return list;
	}
	
	 //查看所有使用记录
     @RequestMapping("/checkHistoryLicense")
     @ResponseBody
     public List<Record> recordLicense(Model model, HttpSession session){
    	 
    	 User user = (User) session.getAttribute("user");
    	 String userId = String.valueOf(user.getId());
    	 List<Record> list = recordService.findByProperty("user_id", userId);
    	 logger.info("recordLicense"+list.size());
    	 
    	 return list;
     }
     
     //查看申请表信息
     @RequestMapping("/checkApplyInfo")
     @ResponseBody
     public Application applyInfo(@RequestParam(value="applyId", required=true) Integer applyId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(applyId);
    	 
    	return applicationService.findByUnique("applicationId", applyId);
    	 
     }
		
     //查看所有可以申请的组的具体信息
     @RequestMapping("/checkCanApplyInfo")
     @ResponseBody
     public Group canApplyInfo(@RequestParam(value="groupId", required=true) Integer groupId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(groupId);
    	 
    	return groupService.findByUnique("groupId",groupId);
    	 
     }
	
     //查看记录的具体信息
     @RequestMapping("/checkHistoryApplyInfo")
     @ResponseBody
     public Record historyApplyInfo(@RequestParam(value="recordId", required=true) Integer recordId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(recordId);
    	 
    	return recordService.findByUnique("recordId",recordId);
    	 
     }
     
     //申请表
     @RequestMapping("/applyLicense")
     @ResponseBody
     public Map<String,Object> applyLicense(Integer group_id,Integer user_id,Integer wantTime,Date startTime,  Model model){
    	 
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 logger.info(user_id);
    	 logger.info(group_id);
    	 Application application = new Application();
    	 application.setWantTime(wantTime);
    	 application.setStartTime(startTime);
    	 
    	 
    	 Group group = groupService.findByUnique("groupId", group_id);
    	 User user = userService.findByUnique("id", user_id);
    	 if(group!=null&user!=null){
    		 application.setGroup(group);
             application.setUser(user);
             
             
             //更新用户申请次数
             user.setApplicationNum(user.getApplicationNum()+1);
             
             //更新用户优先级
             int priority = userService.countPriority(user.getApplicationNum(), user.getApplicationFail());
             user.setUserPriority(priority);
             userService.update(user);
             
             //更新申请表排序优先级
             int order = applicationService.countPriority(user.getUserPriority(), application.getApplyTime());
             application.setOrderPriority(order);
             applicationService.save(application);
             
             map.put("msg", "success");
    	 }
    	 
    	 else map.put("msg", "error");
    	 
    	 
    	 
    	 return map;
     }
     
     //取消申请
     @RequestMapping("/deleteApplyLicense")
     @ResponseBody
     public Map<String,Object> deleteApply(@RequestParam(value="applicationId",required=true) Integer applicationId){
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 
    	Application application = applicationService.findByUnique("applicationId", applicationId);
    	if(application==null)
    		map.put("msg", "取消失败!");
    	
    	else {
    		
    		//更新优先级,减去一次次数
    		int num = application.getUser().getApplicationNum();
    		num = num-1;
    		application.getUser().setApplicationNum(num);
    		userService.update(application.getUser());
    		
    		
    		applicationService.delete(application);
    		
    		map.put("msg", "success");
    	}
    	 
    	 return map;
     }
     
     
		
	}



