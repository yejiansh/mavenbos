package cn.itcast.bos.service.bc.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.page.Pagination;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.DecidedZoneService;

public class DecidedZoneServiceImpl extends BaseService implements DecidedZoneService {

	// 注入DAO
	@Resource(name = "decidedZoneDAO")
	private GenericDAO<DecidedZone, String> decidedZoneDAO;
	@Resource(name = "subareaDAO")
	private GenericDAO<Subarea, String> subareaDAO;

	@Override
	@Transactional(readOnly = false)
	// OpenSessionInView 后，所有业务层，默认事务只读
	public void saveDecidedZone(DecidedZone decidedZone, String[] subareaids) {
		// 保存定区
		decidedZoneDAO.save(decidedZone);
		// 分区关联定区
		if (subareaids != null) {
			for (String subareaId : subareaids) {
				Subarea subarea = subareaDAO.findById(subareaId);
				subarea.setDecidedZone(decidedZone);
			}
		}
	}

	@Override
	public void pageQuery(Pagination<DecidedZone> pagination) {
		super.pageQuery(pagination, decidedZoneDAO);
	}

}
