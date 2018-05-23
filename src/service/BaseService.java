package service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface BaseService<T> {
	
	//新增对象
	public void save(final T entiyt);
	
	//保存或修改对象
	public void saveOrUpdate(final T entity);
	
	//update对象
	public void update(final T entity);
	
	//merge对象
	public T merge(final T entity);
	
	//删除对象
	public void delete(final T entity);
	
	//按ID删除对象
	public void delete(final Serializable id);
	
	//按id获取对象
	public T get(final Serializable id);
	
	//获取全部对象
	public List<T> findAll();
	
	

}
