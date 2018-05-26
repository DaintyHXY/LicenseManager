package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ApplicationDao;
import entity.Application;

@Transactional
@Service
public class ApplicationService extends BaseServiceImpl<Application> {
	
	@Autowired
	ApplicationDao applicationDao;

	@Override
	protected Class<Application> getEntityClass() {
		
		return Application.class;
	}

}
