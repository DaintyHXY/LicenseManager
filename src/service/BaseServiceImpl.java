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
	
	//获得Service实体类，需要继承实现，return具体实现类
	abstract protected Class<T> getEntityClass();
	
	//新增对象
	@Transactional
	public void save(final T entity){
		logger.info(entity.equals(null));
		logger.info(baseDao.getClass());
		logger.info(baseDao);
		baseDao.save(entity);
	}
	
	//保存或修改对象
	public void saveOrUpdate(final T entity){
		baseDao.saveOrUpdate(entity);
	}
	
	//update对象
	public void update(final T entity){
		baseDao.update(entity);
	}
	
	//merge对象
	public T merge(final T entity){
		return baseDao.merge(entity);
	}
	
	//删除对象
	public void delete(final T entity){
		baseDao.delete(entity);
	}
	
	//按id删除对象
	public void delete(final Serializable id){
		baseDao.delete(id);
	}
	
	//按id获取对象
	public T get(final Serializable id){
		return baseDao.get(id);
	}
	
	//获取全部对象
	public List<T> findAll(){
		return baseDao.findAll();
	}
	
	//按属性查找对象
	public T findByUnique(T entity, String property,String value){
		return baseDao.findByUnique(entity, property, value);
	}
	
}
