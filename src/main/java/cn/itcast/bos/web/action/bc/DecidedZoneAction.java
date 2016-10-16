package cn.itcast.bos.web.action.bc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.service.bc.DecidedZoneService;
import cn.itcast.bos.web.action.base.BaseAction;
import cn.itcast.crm.domain.Customer;
import cn.itcast.crm.service.CustomerService;

import com.opensymphony.xwork2.ActionContext;

/**
 * 定区管理
 * 
 * @author seawind
 * 
 */
public class DecidedZoneAction extends BaseAction<DecidedZone> {

	// 注入Service
	@Resource(name = "decidedZoneService")
	private DecidedZoneService decidedZoneService;

	// 业务方法 --- 添加定区
	public String save() {
		// 将数据传递 业务层，添加定区
		decidedZoneService.saveDecidedZone(getModel(), subareaid);

		return "saveSUCCESS";
	}

	// 接收分区id 属性驱动
	private String[] subareaid;

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	// 业务方法 ---- 定区列表分页查询
	public String pageQuery() {
		// 关联查询条件
		DetachedCriteria detachedCriteria = pagination.getDetachedCriteria();

		// 通过定区编码
		if (StringUtils.isNotBlank(getModel().getId())) {
			// 根据定区编码查询 （单表）
			detachedCriteria.add(Restrictions.eq("id", getModel().getId()));
		}
		if (getModel().getStaff() != null && StringUtils.isNotBlank(getModel().getStaff().getStation())) {
			// 根据取派员 单位查询
			DetachedCriteria staffCriteria = detachedCriteria.createCriteria("staff"); // staff是DecidedZone 属性
			staffCriteria.add(Restrictions.like("station", "%" + getModel().getStaff().getStation() + "%"));
		}
		if (StringUtils.isNotBlank(hasassociation)) {
			// 关联分区查询
			if (hasassociation.equals("1")) {
				// 关联分区的定区
				detachedCriteria.add(Restrictions.isNotEmpty("subareas"));
			} else {
				// 未关联分区的定区
				detachedCriteria.add(Restrictions.isEmpty("subareas"));
			}
		}

		// 调用业务层
		decidedZoneService.pageQuery(pagination);
		// 压栈
		ActionContext.getContext().getValueStack().push(pagination);
		return "pageQuerySUCCESS";
	}

	// 属性驱动 接收 是否关联分区
	private String hasassociation;

	public void setHasassociation(String hasassociation) {
		this.hasassociation = hasassociation;
	}

	// 注入Service
	@Resource(name = "customerService")
	private CustomerService customerService;

	// 业务方法 --- 查询未关联客户
	public String findnoassociations() {
		List<Customer> customers = customerService.findnoassociationCustomers();
		ActionContext.getContext().getValueStack().push(customers);
		return "findnoassociationsSUCCESS";
	}

	// 业务方法 --- 查询已经关联定区的客户
	public String findhasassociations() {
		List<Customer> customers = customerService.findhasassociationCustomers(getModel().getId());
		ActionContext.getContext().getValueStack().push(customers);
		return "findhasassociationsSUCCESS";
	}

	// 业务方法 --- 进行关联
	public String assigncustomerstodecidedzone() {
		// 调用customerService 进行关联
		customerService.assignCustomersToDecidedZone(customerIds, getModel().getId());
		return "assigncustomerstodecidedzoneSUCCESS";
	}

	// 属性驱动
	private String[] customerIds;

	public void setCustomerIds(String[] customerIds) {
		this.customerIds = customerIds;
	}

}
