package cn.itcast.bos.service.bc;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.Pageable;

/**
 * 分区管理
 * 
 * @author seawind
 * 
 */
public interface SubareaService extends Pageable<Subarea> {

	/**
	 * 添加分区
	 * 
	 * @param model
	 */
	public void saveSubarea(Subarea subarea);

	/**
	 * 根据条件查询
	 * 
	 * @param detachedCriteria
	 * @return
	 */
	public List<Subarea> findByCondition(DetachedCriteria detachedCriteria);

	/**
	 * 查询未关联定区的分区
	 * 
	 * @return
	 */
	public List<Subarea> findAllNoAssociations();

}
