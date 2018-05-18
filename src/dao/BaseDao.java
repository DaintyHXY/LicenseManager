package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	
	//��������
	public void save(final T entity);
	
	//�����������޸Ķ���
	public void saveOrUpdate(final T entity);
	
	//���¶���update�û��޸ĸ�������ʹ��
	public void update(final T entity);
	
	//���¶���merge�������ݿ�����������ʹ��
	public T merge(final T entity);
	
	//ɾ������
	public void delete(final T entity);
	
	//��idɾ������
	public void delete(final Serializable id);
	
	//��id��ȡ����
	public T get(final Serializable id);
	
	//��ȡȫ������
	public List<T> findAll();
	
	//ͳ������
	public int countAll();

}
