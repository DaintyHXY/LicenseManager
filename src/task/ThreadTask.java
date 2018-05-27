package task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import entity.Application;
import entity.Group;
import entity.License;
import entity.Record;
import service.ApplicationService;
import service.GroupService;
import service.LicenseService;
import service.RecordService;
import service.UserService;

@Component
public class ThreadTask {
	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	GroupService groupService;
	
	@Autowired
	LicenseService licenseService;
	
	@Autowired
	UserService userService;
	
//	List<Application> waitingApply =applicationService.findByProperty("isStopApply", false) ;
//	List<Application> MaxPriority;
//	List<Application> MidPriority;
//	List<Application> MinPriority;
	
	
	
	private static final Log logger = LogFactory.getLog(ThreadTask.class);
	
	//设置已提交申请天数
	//已完成
	@Scheduled(cron="0/10 * * * * ?")//每10秒执行一次
	public void getApplyTime(){
		try{
			TimeUnit.SECONDS.sleep(20);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+"-----已提交申请的天数");
		List<Application> list = applicationService.findByProperty("isStopApply", false);
		for(int i=0;i<list.size();i++){
			if(!list.get(i).getIsStopApply()){
				
			    Date beginTime = list.get(i).getBeginApplyTime();
			    Date today = new Date();
			    long day = (today.getTime()-beginTime.getTime())/(24*60*60*1000);
                list.get(i).setApplyTime((int) day);
                
                System.out.println("用户ID： "+list.get(i).getUser().getId()+"-----用户优先级："+list.get(i).getUser().getUserPriority());
                
                int orderPriority = applicationService.countPriority(list.get(i).getUser().getUserPriority(), list.get(i).getApplyTime());
                System.out.println(orderPriority);
                list.get(i).setOrderPriority(orderPriority);
                applicationService.update(list.get(i));
                System.out.println(list.get(i).getApplicationId()+"天数"+day);
                }
		}
		
		
		
		
	}
	
	//搜寻申请表，更改申请表状态，分发许可证
	//由于group的加入还未完成
	//5.27Group添加后改善完成，用户优先级还没增加
	//5.27已添加用户优先级，6：4
	@Scheduled(cron="0/10 * * * * ?")
	public void changeApplyState(){
		
		try{
			TimeUnit.SECONDS.sleep(50);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		System.out.println(sdf.format(today)+"-------更改申请表状态");
		
		List<Application> a_list = new ArrayList<Application>();
		a_list = applicationService.findByProperty("isStopApply", false);
		
		List<License> l_list = licenseService.findByProperty("isInUse", false);
		
		//按照优先级为申请表排序
		Collections.sort(a_list, new Comparator<Application>(){

			@Override
			public int compare(Application a1, Application a2) {
				//按优先级降序排列，优先级相同按照id升序排列
				if(a1.getOrderPriority()<a2.getOrderPriority()){
					return 1;}
				if(a1.getOrderPriority()==a2.getOrderPriority()){
					if(a1.getApplicationId()>a2.getApplicationId())
						return 1;
					else return -1;
				}
				
				return -1;
			}
		});
		
		//System.out.println("排序后："+"-----第1："+a_list.get(0).getApplicationId()+"-------第二："+a_list.get(1).getApplicationId()+"------第三："+a_list.get(2).getApplicationId());
		
		//为每个申请表查找空闲许可证
		for(int i=0;i<a_list.size();i++){
		 	Application application = a_list.get(i);
		 	int groupId = application.getGroup().getGroupId();
		 	
		 	//若想申请的开始使用时间早于今日，则说明申请失败
		 	if(application.getStartTime().before(new Date())){
		 		
		 		//更新用户失败次数
		 		int fail = application.getUser().getApplicationFail();
		 		application.getUser().setApplicationFail(fail+1);
		 		//更新用户优先级
		 		int priority = userService.countPriority(application.getUser().getApplicationNum(), application.getUser().getApplicationFail());
		 		application.getUser().setUserPriority(priority);
		 		
		 		userService.update(application.getUser());
		 		
		 		
		 		//更新申请表信息
		 		application.setIsSuccessApply(false);
		 		application.setIsStopApply(true);
		 		int order = applicationService.countPriority(application.getUser().getUserPriority(), application.getApplyTime());
		 		
		 		application.setOrderPriority(order);
		 		applicationService.update(application);
		 		
		 		
		 		logger.info(application.getApplicationId()+"---申请表超时，申请失败");
		 	}
		 	
		 	//查询空闲许可证表
		 	else {
		 		for(int j=0;j<l_list.size();j++){
		 			
		 			if(groupId == l_list.get(j).getGroup().getGroupId()){
		 				
		 				//设置成功申请的申请表
		 				application.setIsSuccessApply(true);
		 				application.setIsStopApply(true);
		 				applicationService.update(application);
		 				//a_list.remove(application);
		 				
		 				//设置被使用的license并从列表中移除
		 				l_list.get(j).setIsInUse(true);
		 				licenseService.update(l_list.get(j));
		 				l_list.remove(l_list.get(j));
		 				logger.info(application.getApplicationId()+"---申请成功");
		 				
		 				
		 				//新建记录表
		 				Record record = new Record();
		 				record.setLicense(l_list.get(j));
		 				record.setStartTime(application.getStartTime());
		 				record.setUseTime(application.getWantTime());
		 				record.setUser(application.getUser());
		 				recordService.save(record);
		 				
		 			}
		 			else{
		 				logger.info(application.getApplicationId()+"---没有空闲许可证，请等待");
		 			}
		 		}
		 	}
		 		
		 	
		 	
		 	
		 	
		 	//if(!licenseList.isEmpty())
		 		
		 		
		 	
            
		 	
		 	
		}
		
		
		
		
	}
	
//	//当申请成功时创建一张record表记录
//	public void setRecord(){
//		
//	}
	
	//查询是否已经到期
	//已完成
	//@Scheduled(cron="0/5 * * * * ?")//每5秒执行一次
	public void checkToRecovery(){
		try{
			TimeUnit.SECONDS.sleep(20);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		System.out.println(sdf.format(today)+"-----查询是否已经到期");
		List<Record> list = recordService.findAll();
		Calendar ca = Calendar.getInstance();
		for(int i=0;i<list.size();i++){
			Record record = list.get(i);
			ca.setTime(record.getStartTime());
			ca.add(Calendar.DATE, record.getUseTime());
			Date endTime = ca.getTime();
			if(endTime.before(today)){
				record.setIsStopUse(true);
				recordService.update(record);
			    //logger.info(record.getRecordId()+" 停止了吗:"+record.getIsStopUse());	
			}
			else {
				record.setIsStopUse(false);
			    recordService.update(record);
			}
			logger.info(record.getRecordId()+" 停止了吗:"+record.getIsStopUse());	
			
	        
		}
		
	}
	
	
	
	

}
