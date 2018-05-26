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
import entity.License;
import entity.Record;
import entity.User;
import service.ApplicationService;
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
	
	private static final Log logger = LogFactory.getLog(License.class);
	
	@RequestMapping("/checkAllLicense")
	@ResponseBody
	public List<License> checkLicense(Model model){
		logger.info("checkLicense called");
		logger.info(licenseService.findAll().size());
		
		return licenseService.findAll();
	}
	
	@RequestMapping("/checkApplyingLicense")
	@ResponseBody
	public List<Application> applyingLicense(Model model, HttpSession session){
		
		User user = (User) session.getAttribute("user");
		String userId = String.valueOf(user.getId());
		List<Application> list = applicationService.findByProperty("user_id", userId);
		logger.info("applyingLicense"+list.size());
		
		return list;
	}
		
     @RequestMapping("/checkHistoryLicense")
     @ResponseBody
     public List<Record> recordLicense(Model model, HttpSession session){
    	 
    	 User user = (User) session.getAttribute("user");
    	 String userId = String.valueOf(user.getId());
    	 List<Record> list = recordService.findByProperty("user_id", userId);
    	 logger.info("recordLicense"+list.size());
    	 
    	 return list;
     }
     
     @RequestMapping("/checkApplyInfo")
     @ResponseBody
     public Application applyInfo(@RequestParam(value="applyId", required=true) Integer applyId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(applyId);
    	 
    	return applicationService.findByUnique("applicationId", applyId);
    	 
     }
		
     
     @RequestMapping("/checkCanApplyInfo")
     @ResponseBody
     public License canApplyInfo(@RequestParam(value="licenseId", required=true) Integer licenseId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(licenseId);
    	 
    	return licenseService.findByUnique("licenseId",licenseId);
    	 
     }
		
     @RequestMapping("/checkHistoryApplyInfo")
     @ResponseBody
     public Record historyApplyInfo(@RequestParam(value="recordId", required=true) Integer recordId,Model model,HttpSession session){
    	 
    	 logger.info("checkApplyInfoCalled");
    	 logger.info(recordId);
    	 
    	return recordService.findByUnique("recordId",recordId);
    	 
     }
     
     @RequestMapping("/applyLicense")
     @ResponseBody
     public Map<String,Object> applyLicense(Integer license_id,Integer user_id,Integer wantTime,Date startTime,  Model model){
    	 
    	 Map<String,Object> map = new HashMap<String,Object>();
    	 logger.info(user_id);
    	 logger.info(license_id);
    	 Application application = new Application();
    	 application.setWantTime(wantTime);
    	 application.setStartTime(startTime);
    	 
    	 License license = licenseService.findByUnique("licenseId", license_id);
    	 User user = userService.findByUnique("id", user_id);
    	 if(license!=null&user!=null){
    		 application.setLicense(license);
             application.setUser(user);
             applicationService.save(application);
             map.put("msg", "success");
    	 }
    	 
    	 else map.put("msg", "error");
    	 
    	 
    	 
    	 return map;
     }
		
	}



