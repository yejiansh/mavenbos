package cn.itcast.bos.page;


/**
 * 可以被分页
 * 
 * @author seawind
 * 
 */
public interface Pageable<T> {
	/**
	 * 分页查询
	 * 
	 * @param pagination
	 */
	public void pageQuery(Pagination<T> pagination);
}
