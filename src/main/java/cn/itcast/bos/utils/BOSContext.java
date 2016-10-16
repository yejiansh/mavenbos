package cn.itcast.bos.utils;

import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.user.User;

/**
 * 框架系统 上下文
 * 
 * @author seawind
 * 
 */
public class BOSContext {
	/**
	 * 返回当前登陆用户
	 * 
	 * @return
	 */
	public static User getCurrentUser() {
		return (User) ServletActionContext.getRequest().getSession().getAttribute("user");
	}
}
