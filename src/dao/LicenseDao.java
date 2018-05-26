package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.License;
import service.BaseServiceImpl;

@Transactional
@Repository
public class LicenseDao extends BaseDaoImpl<License> {

	@Override
	protected Class<License> getEntityClass() {
		
		return License.class;
	}


}
