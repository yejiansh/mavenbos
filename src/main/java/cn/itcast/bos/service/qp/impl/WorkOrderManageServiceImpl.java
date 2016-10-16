package cn.itcast.bos.service.qp.impl;

import javax.annotation.Resource;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.Pagination;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.qp.WorkOrderManageService;

public class WorkOrderManageServiceImpl extends BaseService implements WorkOrderManageService {

	// 注入DAO
	@Resource(name = "workOrderManageDAO")
	private GenericDAO<WorkOrderManage, String> workOrderManageDAO;

	@Override
	public void save(WorkOrderManage workOrderManage) {
		workOrderManageDAO.saveOrUpdate(workOrderManage);
	}

	@Override
	public void pageQuery(Pagination<WorkOrderManage> pagination) {
		super.pageQuery(pagination, workOrderManageDAO);
	}

}
