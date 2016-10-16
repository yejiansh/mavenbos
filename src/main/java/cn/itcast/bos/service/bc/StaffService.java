package cn.itcast.bos.service.bc;

import java.util.List;

import cn.itcast.bos.domain.bc.Staff;
import cn.itcast.bos.page.Pageable;

/**
 * 取派员 操作
 * 
 * @author seawind
 * 
 */
public interface StaffService extends Pageable<Staff> {
	/**
	 * 保存取派员
	 * 
	 * @param staff
	 */
	public void saveStaff(Staff staff);

	/**
	 * 批量作废
	 * 
	 * @param split
	 */
	public void delBatch(String[] ids);

	/**
	 * 查询正在使用取派员
	 * 
	 * @return
	 */
	public List<Staff> findAllInUseStaffs();
}
