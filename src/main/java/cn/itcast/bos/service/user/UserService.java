package cn.itcast.bos.service.user;

import cn.itcast.bos.domain.user.User;

/**
 * 用户相关操作
 * 
 * @author seawind
 * 
 */
public interface UserService {
	// 登陆
	public User login(User user);

	// 保存用户
	public void saveUser(User user);

	// 修改密码
	public void editPassword(User user);
}
