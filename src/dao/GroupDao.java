package dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Group;

@Transactional
@Repository

public class GroupDao extends BaseDaoImpl<Group> {

	@Override
	protected Class<Group> getEntityClass() {
		// TODO Auto-generated method stub
		return Group.class;
	}
	
	

}
