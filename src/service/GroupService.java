package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Group;
@Transactional
@Service
public class GroupService extends BaseServiceImpl<Group> {

	@Override
	protected Class<Group> getEntityClass() {
		
		return Group.class;
	}

}
