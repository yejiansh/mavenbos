package cn.itcast.bos.web.action.qp;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.itcast.bos.domain.qp.WorkOrderManage;
import cn.itcast.bos.service.qp.WorkOrderManageService;
import cn.itcast.bos.web.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

/**
 * 工作单 操作
 * 
 * @author seawind
 * 
 */
public class WorkOrderManageAction extends BaseAction<WorkOrderManage> {
	// 注入Service
	@Resource(name = "workOrderManageService")
	private WorkOrderManageService workOrderManageService;

	// 业务方法 --- 保存工作单信息
	public String save() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 调用业务层， 保存model
			workOrderManageService.save(getModel());
			// 保存信息
			result.put("result", "success");
			result.put("msg", "工作单保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "failure");
			result.put("msg", "工作单保存失败 ！ 发生异常：" + e.getClass().getSimpleName() + ", 原因：" + e.getMessage());
		}
		ActionContext.getContext().getValueStack().push(result);
		return "saveSUCCESS";
	}

	// 业务方法 --- 分页查询
	public String pageQuery() {
		// 调用业务层分页
		workOrderManageService.pageQuery(pagination);

		// 压入值栈
		result = pagination;

		return "json";
	}
}
