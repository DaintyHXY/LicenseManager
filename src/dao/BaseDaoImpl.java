package dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


@Transactional
public abstract class BaseDaoImpl<T> implements BaseDao<T> {
    
	private static final Log logger = LogFactory.getLog(BaseDaoImpl.class); 
	private SessionFactory sessionFactory;
	private Class<T> cls;
	
	
	//获得Dao实体类，需要继承实现，return具体实现类
	abstract protected Class<T> getEntityClass();
	
//	//在service层直接使用BaseDao构造函数，定义对象类型class
//	public BaseDaoImpl(final SessionFactory sessionFactory,final Class<T> entityClass){
//		this.sessionFactory = sessionFactory;
//		this.cls = entityClass;
//	}
	
	//持久化对象
	//protected T get(PK pk){}
	
	
	
	//取得sessionFactory
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	//取得当前的Session
	@Transactional
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	//新增对象
	@Transactional
	@Override
	public void save(T entity) {
		if(entity == null)
			logger.error("entity = null");
		else {
			logger.info("save called");
			getSession().save(entity);
			logger.debug(entity);
		}
		
	}

	//保存新增或修改的对象
	@Override
	public void saveOrUpdate(T entity) {
		if(entity == null)
			logger.error("entity = null");
		else{
			getSession().saveOrUpdate(entity);
			logger.debug(entity);
		}
		
	}

	//update对象
	@Override
	public void update(T entity) {
		if(entity == null)
			logger.error("entity = null");
		else{
			getSession().update(entity);
			getSession().flush();
			logger.debug(entity);
		}
		
	}

	//merge对象
	@SuppressWarnings("unchecked")
	@Override
	public T merge(T entity) {
		if(entity == null){
			logger.error("entity == null");
			return null;
		}
		else{ 
			logger.debug(entity);
			return (T)getSession().merge(entity);
			}
	}

	
	//删除对象
	@Override
	public void delete(T entity) {
		if(entity == null)
			logger.error("entity == null");
		else{
			getSession().delete(entity);
			getSession().flush();
			logger.debug(entity);
		}
		
	}

	
	//按id删除对象
	@Override
	public void delete(Serializable id) {
		T entity = get(id);
		delete(entity);
		
	}

	//按id获取对象
	@Override
	public T get(Serializable id) {
		
		return (T) getSession().get(cls, id);
	}

	//获取全部对象
	@Override
	public List<T> findAll() {
		//return find();
		
		String hql = "from "+getEntityClass().getSimpleName();
		Query query = getSession().createQuery(hql);
		
		List<T> t = query.getResultList();
		if(t.isEmpty())
			return null;
		else {
			logger.info(t.get(0));
			return t;
			}
		
	}

	@SuppressWarnings("unchecked")
	private List<T> find(final Criterion... criterions) {
		
		return createCriteria(criterions).list();
	}

	
	
	@SuppressWarnings("unchecked")
	private Criteria createCriteria(Criterion... criterions) {
		
		@SuppressWarnings("deprecation")
		Criteria criteria = getSession().createCriteria(cls).setCacheable(true);
		for(Criterion c : criterions){
			criteria.add(c);
		}
		return criteria;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	//按属性条件查询
	public T findByUnique(String property,Object value){
		
		//String hql ="from "+entity.getClass().getSimpleName()+" where "+property+"=?";
		String hql ="from "+getEntityClass().getSimpleName()+" where "+property+"=?";
		logger.info(hql);
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);
		
		List list = query.getResultList();
		if(list.size()>0)
			return (T)list.get(0);
		else return null;
		
	} 
	
	//得到query对象
//	public Query getQuery(final T entity,String property){
//		return getSession().createQuery("from"+ entity +" where property=?");
//	}
    
	//按属性查找列表
	public List<T> findByProperty(String property,Object value){
		String hql ="from "+getEntityClass().getSimpleName()+" where "+property+"=?";
		logger.info(hql);
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);
		
		List<T> list = query.getResultList();
        
		return list;
	}
}
