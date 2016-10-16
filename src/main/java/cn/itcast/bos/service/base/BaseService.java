package cn.itcast.bos.service.base;

import java.io.Serializable;
import java.util.List;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.page.Pagination;

/**
 * 实现 业务层代码复用
 * 
 * @author seawind
 * 
 */
public abstract class BaseService {
	/**
	 * 通用 业务逻辑方法
	 * 
	 * @param pagination
	 * @param dao
	 */
	public <T, PK extends Serializable> void pageQuery(Pagination<T> pagination, GenericDAO<T, PK> dao) {
		// 调用DAO 查询 总记录数
		long total = dao.findTotalCount(pagination.getDetachedCriteria());
		pagination.setTotal(total);

		// 调用DAO 查询当前页数据
		int firstResult = (pagination.getPage() - 1) * pagination.getPageSize();
		int maxResults = pagination.getPageSize();
		List<T> rows = dao.findPageData(pagination.getDetachedCriteria(), firstResult, maxResults);
		pagination.setRows(rows);
	}
}
