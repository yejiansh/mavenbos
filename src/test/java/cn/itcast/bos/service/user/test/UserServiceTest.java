package cn.itcast.bos.service.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testLogin() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");

		System.out.println(userService.login(user));
	}

	@Test
	public void testSaveUser() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");

		userService.saveUser(user);
	}

}
