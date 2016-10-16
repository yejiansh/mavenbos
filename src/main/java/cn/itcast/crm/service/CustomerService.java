package cn.itcast.crm.service;

import java.util.List;

import cn.itcast.crm.domain.Customer;

/**
 * 客户相关操作
 * 
 * @author seawind
 * 
 */
public interface CustomerService {
	/**
	 * 查询所有未关联定区的客户
	 * 
	 * @return
	 */
	public List<Customer> findnoassociationCustomers();

	/**
	 * 根据定区 ，查询已经关联客户
	 * 
	 * @param decidedZoneId
	 * @return
	 */
	public List<Customer> findhasassociationCustomers(String decidedZoneId);

	/**
	 * 将选中客户，关联到指定定区上
	 * 
	 * @param customerIds
	 * @param decidedZoneId
	 */
	public void assignCustomersToDecidedZone(String[] customerIds, String decidedZoneId);

	/**
	 * 根据 手机号 查询客户信息
	 * 
	 * @param telephone
	 * @return
	 */
	public Customer findByTelephone(String telephone);

	/**
	 * 根据地址查询定区
	 * 
	 * @param address
	 * @return
	 */
	public String findDecidedZoneByAddress(String address);
}
