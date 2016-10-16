package cn.itcast.bos.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.bos.dao.GenericDAO;

@SuppressWarnings("all")
public class GenericDAOImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, PK> {

	private Class<T> entityClass;

	public GenericDAOImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public void save(T obj) {
		this.getHibernateTemplate().save(obj);
	}

	@Override
	public void update(T obj) {
		this.getHibernateTemplate().update(obj);
	}

	@Override
	public void delete(T obj) {
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public T findById(PK id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		return this.getHibernateTemplate().loadAll(entityClass);
	}

	@Override
	public List<T> findByNamedQuery(String queryName, Object... params) {
		return this.getHibernateTemplate().findByNamedQuery(queryName, params);
	}

	@Override
	public List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public long findTotalCount(DetachedCriteria detachedCriteria) {
		// 投影
		detachedCriteria.setProjection(Projections.rowCount());
		long totalCount = (Long) this.getHibernateTemplate().findByCriteria(detachedCriteria).get(0);
		// 清除投影
		detachedCriteria.setProjection(null);
		// 重置结果策略
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		return totalCount;
	}

	@Override
	public List<T> findPageData(DetachedCriteria detachedCriteria, int firstResult, int maxResults) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	}

	@Override
	public void saveOrUpdate(T obj) {
		this.getHibernateTemplate().merge(obj);
	}

}
