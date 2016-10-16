package cn.itcast.bos.service.qp.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.domain.qp.NoticeBill;
import cn.itcast.bos.domain.qp.WorkBill;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.qp.NoticeBillService;
import cn.itcast.crm.service.CustomerService;

/**
 * 通知单 操作实现
 * 
 * @author seawind
 * 
 */
public class NoticeBillServiceImpl extends BaseService implements NoticeBillService {

	// 注入DAO
	@Resource(name = "noticeBillDAO")
	private GenericDAO<NoticeBill, String> noticeBillDAO;

	@Resource(name = "customerService")
	private CustomerService customerService;

	@Resource(name = "decidedZoneDAO")
	private GenericDAO<DecidedZone, String> decidedZoneDAO;

	@Resource(name = "workBillDAO")
	private GenericDAO<WorkBill, String> workBillDAO;

	@Resource(name = "regionDAO")
	private GenericDAO<Region, String> regionDAO;

	@Override
	@Transactional
	public void saveNoticeBill(NoticeBill noticeBill) {
		// 保存通知单信息
		noticeBillDAO.save(noticeBill);

		// 自动分单
		// 第一种 方式， 将收件地址，传递 CRM 比较 CRM客户地址，如果匹配， 获取客户对应定区编号
		String decidedZoneId = customerService.findDecidedZoneByAddress(noticeBill.getPickaddress());
		// 根据 定区id 查询定区
		if (StringUtils.isNotBlank(decidedZoneId)) {
			// 自动分单成功
			DecidedZone decidedZone = decidedZoneDAO.findById(decidedZoneId);
			// 保存通知单中
			noticeBill.setOrdertype("自动");
			noticeBill.setStaff(decidedZone.getStaff());
			// 创建工单对象
			WorkBill workBill = new WorkBill();
			workBill.setAttachbilltimes(0);
			workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
			workBill.setNoticeBill(noticeBill);
			workBill.setPickstate("新单");
			workBill.setRemark(noticeBill.getRemark());
			workBill.setStaff(decidedZone.getStaff());
			workBill.setType("新");
			workBillDAO.save(workBill);
			return;
		}

		// 第二种 方式 ： 对取件地址解析
		String pickAddress = noticeBill.getPickaddress();
		// 对地址解析 省市区
		int provinceIndex = pickAddress.indexOf("省");
		int cityIndex = pickAddress.indexOf("市", provinceIndex);
		int districtIndex = pickAddress.indexOf("区", cityIndex);

		String province = null;
		String city = null;
		String district = null;
		// 截取省市区
		if (provinceIndex == -1) {
			// 直辖市
			city = pickAddress.substring(0, cityIndex + 1); // 上海市、 北京市
			province = city;
			district = pickAddress.substring(cityIndex + 1, districtIndex + 1);
		} else {
			// 非直辖市
			province = pickAddress.substring(0, provinceIndex + 1);
			city = pickAddress.substring(provinceIndex + 1, cityIndex + 1);
			district = pickAddress.substring(cityIndex + 1, districtIndex + 1);
		}

		// 根据省市区，查询区域
		List<Region> regions = regionDAO.findByNamedQuery("Region.findByProvinceCityDistrict", province, city, district);
		if (!regions.isEmpty()) {
			Region region = regions.get(0);
			Set<Subarea> subareas = region.getSubareas();
			// 匹配分区关键字
			for (Subarea subarea : subareas) {
				if (pickAddress.contains(subarea.getAddresskey())) {
					// 匹配分区 关键字
					// 找到分区 ，是否分配定区，有没有关联取派员
					if (subarea.getDecidedZone() != null) {
						// 自动分单成功
						DecidedZone decidedZone = subarea.getDecidedZone();
						// 保存通知单中
						noticeBill.setOrdertype("自动");
						noticeBill.setStaff(decidedZone.getStaff());
						// 创建工单对象
						WorkBill workBill = new WorkBill();
						workBill.setAttachbilltimes(0);
						workBill.setBuildtime(new Timestamp(System.currentTimeMillis()));
						workBill.setNoticeBill(noticeBill);
						workBill.setPickstate("新单");
						workBill.setRemark(noticeBill.getRemark());
						workBill.setStaff(decidedZone.getStaff());
						workBill.setType("新");
						workBillDAO.save(workBill);
						return;
					}
				}
			}
		}

		// 人工分单
		noticeBill.setOrdertype("人工");
	}
}
