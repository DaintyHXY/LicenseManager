package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	//新增对象
	public void save(final T entity);
	
	//保存新增或修改对象
	public void saveOrUpdate(final T entity);
	
	//更新对象update用户修改个人资料使用
	public void update(final T entity);
	
	//更新对象merge更新数据库中任意数据使用
	public T merge(final T entity);
	
	//删除对象
	public void delete(final T entity);
	
	//按id删除对象
	public void delete(final Serializable id);
	
	//按id获取对象
	public T get(final Serializable id);
	
	//获取全部对象
	public List<T> findAll();
	
	//统计所有
	public int countAll();

}
