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
	
	
	//���Daoʵ���࣬��Ҫ�̳�ʵ�֣�return����ʵ����
	abstract protected Class<T> getEntityClass();
	
//	//��service��ֱ��ʹ��BaseDao���캯���������������class
//	public BaseDaoImpl(final SessionFactory sessionFactory,final Class<T> entityClass){
//		this.sessionFactory = sessionFactory;
//		this.cls = entityClass;
//	}
	
	//�־û�����
	//protected T get(PK pk){}
	
	
	
	//ȡ��sessionFactory
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(final SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	//ȡ�õ�ǰ��Session
	@Transactional
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	//��������
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

	//�����������޸ĵĶ���
	@Override
	public void saveOrUpdate(T entity) {
		if(entity == null)
			logger.error("entity = null");
		else{
			getSession().saveOrUpdate(entity);
			logger.debug(entity);
		}
		
	}

	//update����
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

	//merge����
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

	
	//ɾ������
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

	
	//��idɾ������
	@Override
	public void delete(Serializable id) {
		T entity = get(id);
		delete(entity);
		
	}

	//��id��ȡ����
	@Override
	public T get(Serializable id) {
		
		return (T) getSession().get(cls, id);
	}

	//��ȡȫ������
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
	
	
	
	//������������ѯ
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
	
	//�õ�query����
//	public Query getQuery(final T entity,String property){
//		return getSession().createQuery("from"+ entity +" where property=?");
//	}
    
	//�����Բ����б�
	public List<T> findByProperty(String property,Object value){
		String hql ="from "+getEntityClass().getSimpleName()+" where "+property+"=?";
		logger.info(hql);
		Query query = getSession().createQuery(hql);
		query.setParameter(0, value);
		
		List<T> list = query.getResultList();
        
		return list;
	}
}
