package cn.itcast.bos.web.action.bc;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.service.bc.StaffService;
import cn.itcast.bos.web.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

/**
 * 取派员管理
 * 
 * @author seawind
 * 
 */
public class StaffAction extends BaseAction<Staff> {

	// 注入Service
	@Resource(name = "staffService")
	private StaffService staffService;

	// 业务方法 --- 保存取派员
	public String save() {
		staffService.saveStaff(getModel());
		return "saveSUCCESS";
	}

	// 业务方法 --- 分页查询
	public String pageQuery() {
		// 调用业务层
		staffService.pageQuery(pagination);

		// 查询后，将pagination 转换为json返回
		ActionContext.getContext().getValueStack().push(pagination);

		return "pageQuerySUCCESS";
	}

	// 业务方法 ---- 作废
	public String delBatch() {
		// 调用业务层 完成删除
		if (ids != null) {
			staffService.delBatch(ids.split(","));
		}
		return "delBatchSUCCESS";
	}

	private String ids;

	public void setIds(String ids) {
		this.ids = ids;
	}

	// 业务方法 --- 查询未作废取派员列表
	public String ajaxlist() {
		// 调用业务层，获取取派员列表
		List<Staff> staffs = staffService.findAllInUseStaffs();
		// 将结果压入值栈返回
		ActionContext.getContext().getValueStack().push(staffs);

		return "ajaxlistSUCCESS";
	}
}
