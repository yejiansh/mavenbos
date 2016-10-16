package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.page.Pageable;

/**
 * 区域管理 业务层
 * 
 * @author seawind
 * 
 */
public interface RegionService extends Pageable<Region> {

	/**
	 * 批量导入区域信息
	 * 
	 * @param regions
	 */
	public void saveBatch(List<Region> regions);

	/**
	 * 查询所有区域
	 * 
	 * @return
	 */
	public List<Region> findAllRegions();

	/**
	 * 根据条件查询
	 * 
	 * @param q
	 * @return
	 */
	public List<Region> findRegionsByCondition(String q);

}
