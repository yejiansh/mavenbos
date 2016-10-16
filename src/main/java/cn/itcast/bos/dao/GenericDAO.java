package cn.itcast.bos.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 通用DAO 接口
 * 
 * @author seawind
 * 
 * @param <T>
 *            传入对象类型
 * @param <PK>
 *            主键类型
 */
public interface GenericDAO<T, PK extends Serializable> {

	// 保存
	public void save(T obj);

	// 修改
	public void update(T obj);

	// 删除
	public void delete(T obj);

	// 根据主键查询
	public T findById(PK id);

	// 查询所有
	public List<T> findAll();

	// 条件查询
	public List<T> findByNamedQuery(String queryName, Object... params);

	public List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria);

	// 用于分页查询
	public long findTotalCount(DetachedCriteria detachedCriteria);

	public List<T> findPageData(DetachedCriteria detachedCriteria, int firstResult, int maxResults);

	// 保存或更新
	public void saveOrUpdate(T obj);
}
