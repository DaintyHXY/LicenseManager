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
	
	//�������ύ��������
	//�����
	@Scheduled(cron="0/10 * * * * ?")//ÿ10��ִ��һ��
	public void getApplyTime(){
		try{
			TimeUnit.SECONDS.sleep(20);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date())+"-----���ύ���������");
		List<Application> list = applicationService.findByProperty("isStopApply", false);
		for(int i=0;i<list.size();i++){
			if(!list.get(i).getIsStopApply()){
				
			    Date beginTime = list.get(i).getBeginApplyTime();
			    Date today = new Date();
			    long day = (today.getTime()-beginTime.getTime())/(24*60*60*1000);
                list.get(i).setApplyTime((int) day);
                
                System.out.println("�û�ID�� "+list.get(i).getUser().getId()+"-----�û����ȼ���"+list.get(i).getUser().getUserPriority());
                
                int orderPriority = applicationService.countPriority(list.get(i).getUser().getUserPriority(), list.get(i).getApplyTime());
                System.out.println(orderPriority);
                list.get(i).setOrderPriority(orderPriority);
                applicationService.update(list.get(i));
                System.out.println(list.get(i).getApplicationId()+"����"+day);
                }
		}
		
		
		
		
	}
	
	//��Ѱ��������������״̬���ַ����֤
	//����group�ļ��뻹δ���
	//5.27Group��Ӻ������ɣ��û����ȼ���û����
	//5.27������û����ȼ���6��4
	@Scheduled(cron="0/10 * * * * ?")
	public void changeApplyState(){
		
		try{
			TimeUnit.SECONDS.sleep(50);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		System.out.println(sdf.format(today)+"-------���������״̬");
		
		List<Application> a_list = new ArrayList<Application>();
		a_list = applicationService.findByProperty("isStopApply", false);
		
		List<License> l_list = licenseService.findByProperty("isInUse", false);
		
		//�������ȼ�Ϊ���������
		Collections.sort(a_list, new Comparator<Application>(){

			@Override
			public int compare(Application a1, Application a2) {
				//�����ȼ��������У����ȼ���ͬ����id��������
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
		
		//System.out.println("�����"+"-----��1��"+a_list.get(0).getApplicationId()+"-------�ڶ���"+a_list.get(1).getApplicationId()+"------������"+a_list.get(2).getApplicationId());
		
		//Ϊÿ���������ҿ������֤
		for(int i=0;i<a_list.size();i++){
		 	Application application = a_list.get(i);
		 	int groupId = application.getGroup().getGroupId();
		 	
		 	//��������Ŀ�ʼʹ��ʱ�����ڽ��գ���˵������ʧ��
		 	if(application.getStartTime().before(new Date())){
		 		
		 		//�����û�ʧ�ܴ���
		 		int fail = application.getUser().getApplicationFail();
		 		application.getUser().setApplicationFail(fail+1);
		 		//�����û����ȼ�
		 		int priority = userService.countPriority(application.getUser().getApplicationNum(), application.getUser().getApplicationFail());
		 		application.getUser().setUserPriority(priority);
		 		
		 		userService.update(application.getUser());
		 		
		 		
		 		//�����������Ϣ
		 		application.setIsSuccessApply(false);
		 		application.setIsStopApply(true);
		 		int order = applicationService.countPriority(application.getUser().getUserPriority(), application.getApplyTime());
		 		
		 		application.setOrderPriority(order);
		 		applicationService.update(application);
		 		
		 		
		 		logger.info(application.getApplicationId()+"---�����ʱ������ʧ��");
		 	}
		 	
		 	//��ѯ�������֤��
		 	else {
		 		for(int j=0;j<l_list.size();j++){
		 			
		 			if(groupId == l_list.get(j).getGroup().getGroupId()){
		 				
		 				//���óɹ�����������
		 				application.setIsSuccessApply(true);
		 				application.setIsStopApply(true);
		 				applicationService.update(application);
		 				//a_list.remove(application);
		 				
		 				//���ñ�ʹ�õ�license�����б����Ƴ�
		 				l_list.get(j).setIsInUse(true);
		 				licenseService.update(l_list.get(j));
		 				l_list.remove(l_list.get(j));
		 				logger.info(application.getApplicationId()+"---����ɹ�");
		 				
		 				
		 				//�½���¼��
		 				Record record = new Record();
		 				record.setLicense(l_list.get(j));
		 				record.setStartTime(application.getStartTime());
		 				record.setUseTime(application.getWantTime());
		 				record.setUser(application.getUser());
		 				recordService.save(record);
		 				
		 			}
		 			else{
		 				logger.info(application.getApplicationId()+"---û�п������֤����ȴ�");
		 			}
		 		}
		 	}
		 		
		 	
		 	
		 	
		 	
		 	//if(!licenseList.isEmpty())
		 		
		 		
		 	
            
		 	
		 	
		}
		
		
		
		
	}
	
//	//������ɹ�ʱ����һ��record���¼
//	public void setRecord(){
//		
//	}
	
	//��ѯ�Ƿ��Ѿ�����
	//�����
	//@Scheduled(cron="0/5 * * * * ?")//ÿ5��ִ��һ��
	public void checkToRecovery(){
		try{
			TimeUnit.SECONDS.sleep(20);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		System.out.println(sdf.format(today)+"-----��ѯ�Ƿ��Ѿ�����");
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
			    //logger.info(record.getRecordId()+" ֹͣ����:"+record.getIsStopUse());	
			}
			else {
				record.setIsStopUse(false);
			    recordService.update(record);
			}
			logger.info(record.getRecordId()+" ֹͣ����:"+record.getIsStopUse());	
			
	        
		}
		
	}
	
	
	
	

}
