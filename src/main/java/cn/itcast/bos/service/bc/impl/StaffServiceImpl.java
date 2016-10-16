package cn.itcast.bos.service.bc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.Pagination;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.StaffService;

public class StaffServiceImpl extends BaseService implements StaffService {
	// 注入DAO
	@Resource(name = "staffDAO")
	private GenericDAO<Staff, String> staffDAO;

	@Override
	@Transactional(readOnly = false)
	public void saveStaff(Staff staff) {
		if (staff.getId().equals("")) {
			staff.setId(null);
		}
		staffDAO.saveOrUpdate(staff);
	}

	@Override
	@Transactional(readOnly = true)
	public void pageQuery(Pagination<Staff> pagination) {
		super.pageQuery(pagination, staffDAO);
	}

	@Override
	@Transactional(readOnly = false)
	public void delBatch(String[] ids) {
		for (String id : ids) {
			Staff staff = staffDAO.findById(id);
			staff.setDeltag("1");
		}
	}

	@Override
	public List<Staff> findAllInUseStaffs() {
		// 查询条件 deltag is null ;
		return staffDAO.findByNamedQuery("Staff.findAllInUseStaffs");
	}
}
