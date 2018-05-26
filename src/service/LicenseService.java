package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.LicenseDao;
import entity.License;

@Transactional
@Service
public class LicenseService extends BaseServiceImpl<License> {

	@Autowired
	private LicenseDao licenseDao;
	
	@Override
	protected Class<License> getEntityClass() {
		
		return License.class;
	}

}
