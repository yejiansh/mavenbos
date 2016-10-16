package cn.itcast.bos.service.bc;

import cn.itcast.bos.domain.bc.DecidedZone;
import cn.itcast.bos.page.Pageable;

/**
 * 定区管理 业务接口
 * 
 * @author seawind
 * 
 */
public interface DecidedZoneService extends Pageable<DecidedZone> {

	/**
	 * 添加定区
	 * 
	 * @param model
	 * @param subareaid
	 */
	public void saveDecidedZone(DecidedZone decidedZone, String[] subareaids);

}
