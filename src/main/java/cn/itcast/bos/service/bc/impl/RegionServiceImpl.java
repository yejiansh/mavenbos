package cn.itcast.bos.service.bc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.page.Pagination;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.bc.RegionService;

public class RegionServiceImpl extends BaseService implements RegionService {

	// 注入DAO
	@Resource(name = "regionDAO")
	private GenericDAO<Region, String> regionDAO;

	@Override
	@Transactional(readOnly = false)
	public void saveBatch(List<Region> regions) {
		for (Region region : regions) {
			regionDAO.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(Pagination<Region> pagination) {
		super.pageQuery(pagination, regionDAO);
	}

	@Override
	public List<Region> findAllRegions() {
		return regionDAO.findAll();
	}

	@Override
	public List<Region> findRegionsByCondition(String q) {
		// 省、 市、区 只有包含 q 返回
		return regionDAO.findByNamedQuery("Region.findRegionsByCondition", "%" + q + "%", "%" + q + "%", "%" + q + "%");
	}
}
