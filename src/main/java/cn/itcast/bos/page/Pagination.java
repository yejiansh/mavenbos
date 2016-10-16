package cn.itcast.bos.page;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.criterion.DetachedCriteria;

/**
 * 分页查询 数据Bean
 * 
 * @author seawind
 * 
 */
public class Pagination<T> {
	// 请求参数
	private int page;
	private int pageSize; // 对应请求rows
	// 查询条件
	private DetachedCriteria detachedCriteria;

	// 响应参数
	private long total;
	private List<T> rows;

	@JSON(serialize = false)
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JSON(serialize = false)
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@JSON(serialize = false)
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}

	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}