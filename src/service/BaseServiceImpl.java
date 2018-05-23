package service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.BaseDao;
import dao.BaseDaoImpl;

@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private static final Log logger = LogFactory.getLog(BaseServiceImpl.class);
	
	@Autowired
	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao){
		this.baseDao = baseDao;
	}
	
	public BaseServiceImpl(){
		
	}
	
	//���Serviceʵ���࣬��Ҫ�̳�ʵ�֣�return����ʵ����
	abstract protected Class<T> getEntityClass();
	
	//��������
	@Transactional
	public void save(final T entity){
		logger.info(entity.equals(null));
		logger.info(baseDao.getClass());
		logger.info(baseDao);
		baseDao.save(entity);
	}
	
	//������޸Ķ���
	public void saveOrUpdate(final T entity){
		baseDao.saveOrUpdate(entity);
	}
	
	//update����
	public void update(final T entity){
		baseDao.update(entity);
	}
	
	//merge����
	public T merge(final T entity){
		return baseDao.merge(entity);
	}
	
	//ɾ������
	public void delete(final T entity){
		baseDao.delete(entity);
	}
	
	//��idɾ������
	public void delete(final Serializable id){
		baseDao.delete(id);
	}
	
	//��id��ȡ����
	public T get(final Serializable id){
		return baseDao.get(id);
	}
	
	//��ȡȫ������
	public List<T> findAll(){
		return baseDao.findAll();
	}
	
	//�����Բ��Ҷ���
	public T findByUnique(T entity, String property,String value){
		return baseDao.findByUnique(entity, property, value);
	}
	
}
