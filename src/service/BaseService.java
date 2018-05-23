package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface BaseService<T> {
	
	//��������
	public void save(final T entiyt);
	
	//������޸Ķ���
	public void saveOrUpdate(final T entity);
	
	//update����
	public void update(final T entity);
	
	//merge����
	public T merge(final T entity);
	
	//ɾ������
	public void delete(final T entity);
	
	//��IDɾ������
	public void delete(final Serializable id);
	
	//��id��ȡ����
	public T get(final Serializable id);
	
	//��ȡȫ������
	public List<T> findAll();
	
	

}
