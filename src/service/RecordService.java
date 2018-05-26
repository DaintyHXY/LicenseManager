package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.RecordDao;
import entity.Record;

@Transactional
@Service
public class RecordService extends BaseServiceImpl<Record> {
	
	@Autowired
	private RecordDao recordDao;

	@Override
	protected Class<Record> getEntityClass() {
		
		return Record.class;
	}

}
