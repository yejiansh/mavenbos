package cn.itcast.bos.service.bc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.Pagination;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.SubareaService;

public class SubareaServiceImpl extends BaseService implements SubareaService {

	// 注入DAO
	@Resource(name = "subareaDAO")
	private GenericDAO<Subarea, String> subareaDAO;

	@Override
	public void saveSubarea(Subarea subarea) {
		subareaDAO.save(subarea);
	}

	@Override
	public void pageQuery(Pagination<Subarea> pagination) {
		super.pageQuery(pagination, subareaDAO);
	}

	@Override
	public List<Subarea> findByCondition(DetachedCriteria detachedCriteria) {
		return subareaDAO.findByDetachedCriteria(detachedCriteria);
	}

	@Override
	public List<Subarea> findAllNoAssociations() {
		// 查询条件
		return subareaDAO.findByNamedQuery("Subarea.findnoassociations");
	}
}
