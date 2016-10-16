package cn.itcast.bos.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.GenericDAO;
import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.base.BaseService;
import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.utils.MD5Utils;

public class UserServiceImpl extends BaseService implements UserService {

	// 注入 DAO
	@Resource(name = "userDAO")
	private GenericDAO<User, String> userDAO;

	@Override
	@Transactional(readOnly = true)
	public User login(User user) {
		// 根据用户名和密码查询 ，多条件查询 --- 使用NamedQuery
		List<User> list = userDAO.findByNamedQuery("User.login", user.getUsername(), MD5Utils.md5(user.getPassword()));
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveUser(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDAO.save(user);
	}

	@Override
	@Transactional(readOnly = false)
	public void editPassword(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		userDAO.update(user);
	}
}
