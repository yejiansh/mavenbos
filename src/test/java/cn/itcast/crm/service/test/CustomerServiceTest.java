package cn.itcast.crm.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.crm.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerServiceTest {
	@Autowired
	private CustomerService customerService;

	@Test
	public void testFindnoassociations() {
		System.out.println(customerService.findnoassociationCustomers());
	}

	@Test
	public void testFindhasassociations() {
		System.out.println(customerService.findhasassociationCustomers("DQ001"));
	}

	@Test
	public void testassigncustomerstodecidedzone() {
		String[] customerIds = { "3" }; // 小丽，未被关联到定区
		String decidedzone_id = "DQ002"; // 定区编号
		customerService.assignCustomersToDecidedZone(customerIds, decidedzone_id);
	}
}
