package dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Application;

@Transactional
@Repository
public class ApplicationDao extends BaseDaoImpl<Application> {

	@Override
	protected Class<Application> getEntityClass() {
		
		return Application.class;
	}

}
