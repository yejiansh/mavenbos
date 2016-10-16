package cn.itcast.bos.service.qp;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.page.Pageable;

/**
 * 工作单操作
 * 
 * @author seawind
 * 
 */
public interface WorkOrderManageService extends Pageable<WorkOrderManage> {

	// 保存工作单
	public void save(WorkOrderManage workOrderManage);

}
