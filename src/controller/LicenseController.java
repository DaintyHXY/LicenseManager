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
	
	//������п�����������֤��
	@RequestMapping("/checkAllLicense")
	@ResponseBody
	public List<Group> checkLicense(Model model){
		logger.info("checkLicense called");
		logger.info(groupService.findAll().size());
		
		return groupService.findAll();
	}
	
	//����Ѿ��ύ��������֤��Ϣ
	@RequestMapping("/checkApplyingLicense")
	@ResponseBody
	public List<Application> applyingLicense(Model model, HttpSession session){
		
		User user = (User) session.getAttribute("user");
		String userId = String.valueOf(user.getId());
		List<Application> list = applicationService.findByProperty("user_id", userId);
		logger.info("applyingLicense"+list.size());
		
		return list;
	}
	
	 //�鿴����ʹ�ü�¼
     @RequestMapping("/checkHistoryLicense")
     @ResponseBody
     public List<Record> recordLicense(Model model, HttpSession session){
    	 
    	 User user = (User) session.getAttribute("user");
    	 String userId = String.valueOf(user.getId());
    	 List<Record> list = recordService.findByProperty("user_id", userId);
    	 logger.info("recordLicense"+list.size());
    	 
    	 return list;
     }
     
     //�鿴�������Ϣ
     @RequestMapping("/checkApplyInfo")
     @ResponseBody
     public Application applyInfo(@RequestParam(value="applyId", required=true) Integer applyId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(applyId);
    	 
    	return applicationService.findByUnique("applicationId", applyId);
    	 
     }
		
     //�鿴���п����������ľ�����Ϣ
     @RequestMapping("/checkCanApplyInfo")
     @ResponseBody
     public Group canApplyInfo(@RequestParam(value="groupId", required=true) Integer groupId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(groupId);
    	 
    	return groupService.findByUnique("groupId",groupId);
    	 
     }
	
     //�鿴��¼�ľ�����Ϣ
     @RequestMapping("/checkHistoryApplyInfo")
     @ResponseBody
     public Record historyApplyInfo(@RequestParam(value="recordId", required=true) Integer recordId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(recordId);
    	 
    	return recordService.findByUnique("recordId",recordId);
    	 
     }
     
     //�����
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
             
             
             //�����û��������
             user.setApplicationNum(user.getApplicationNum()+1);
             
             //�����û����ȼ�
             int priority = userService.countPriority(user.getApplicationNum(), user.getApplicationFail());
             user.setUserPriority(priority);
             userService.update(user);
             
             //����������������ȼ�
             int order = applicationService.countPriority(user.getUserPriority(), application.getApplyTime());
             application.setOrderPriority(order);
             applicationService.save(application);
             
             map.put("msg", "success");
    	 }
    	 
    	 else map.put("msg", "error");
    	 
    	 
    	 
    	 return map;
     }
     
     //ȡ������
     @RequestMapping("/deleteApplyLicense")
     @ResponseBody
     public Map<String,Object> deleteApply(@RequestParam(value="applicationId",required=true) Integer applicationId){
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 
    	Application application = applicationService.findByUnique("applicationId", applicationId);
    	if(application==null)
    		map.put("msg", "ȡ��ʧ��!");
    	
    	else {
    		
    		//�������ȼ�,��ȥһ�δ���
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



