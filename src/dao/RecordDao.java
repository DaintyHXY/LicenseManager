package dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entity.Record;

@Transactional
@Repository
public class RecordDao extends BaseDaoImpl<Record> {

	@Override
	protected Class<Record> getEntityClass() {
		
		return Record.class;
	}

}
