package cn.itcast.bos.web.action.qp;

import javax.annotation.Resource;

import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.service.qp.NoticeBillService;
import cn.itcast.bos.utils.BOSContext;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;

import com.opensymphony.xwork2.ActionContext;

/**
 * 通知单 操作
 * 
 * @author seawind
 * 
 */
public class NoticeBillAction extends BaseAction<NoticeBill> {
	// 注入Service
	@Resource(name = "customerService")
	private CustomerService customerService;

	// 业务方法 --- 调用CRM的远程接口，获取客户信息 通过手机号
	public String findCustomerByPhone() {
		// 调用远程接口
		Customer customer = customerService.findByTelephone(getModel().getTelephone());
		// 传递到页面 ，转换json
		ActionContext.getContext().getValueStack().push(customer);

		return "findCustomerByPhoneSUCCESS";
	}

	// 注入Service
	@Resource(name = "noticeBillService")
	private NoticeBillService noticeBillService;

	// 业务方法 --- 新单 添加业务通知单
	public String save() {
		// 关联当前 业务受理员
		getModel().setUser(BOSContext.getCurrentUser());
		// 调用业务层 ，完成通知单保存
		noticeBillService.saveNoticeBill(getModel());
		return "saveSUCCESS";
	}

}
