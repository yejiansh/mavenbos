package cn.itcast.bos.web.action.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.user.User;
import cn.itcast.bos.service.user.UserService;
import cn.itcast.bos.utils.BOSContext;
import cn.itcast.bos.web.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

/**
 * 用户管理 表现层
 * 
 * @author seawind
 * 
 */
public class UserAction extends BaseAction<User> {

	// 注入Service
	@Resource(name = "userService")
	private UserService userService;

	// 业务方法 --- 登陆
	public String login() {
		// 校验验证码是否正确
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		if (key == null || !key.equals(checkcode)) {
			// 验证码失败
			this.addActionError(this.getText("checkcodefail"));
			return "loginINPUT";
		}

		// 调用业务层
		User user = userService.login(getModel());
		if (user == null) {
			// 登陆失败
			this.addActionError(this.getText("loginfail"));
			return "loginINPUT";
		} else {
			// 登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("user", user);
			return "loginSUCCESS";
		}
	}

	// 属性驱动 接收验证码
	private String checkcode;

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	// 业务方法 --- 退出
	public String logout() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		// 返回登陆页面
		return "logoutSUCCESS";
	}

	// 业务方法 --- 修改密码
	public String editpassword() {
		// model中封装了新密码
		User user = BOSContext.getCurrentUser();

		// 将新密码 保存到 当前用户
		user.setPassword(getModel().getPassword());

		// 调用业务层
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			userService.editPassword(user);
			// 成功
			resultMap.put("result", "success");
			resultMap.put("msg", this.getText("editpasswordSUCCESSMSG"));
		} catch (Exception e) {
			e.printStackTrace();
			// 失败
			resultMap.put("result", "failure");
			resultMap.put("msg", this.getText("editpasswordFAILMSG"));
		}
		// 将resultMap 转换为json
		ActionContext.getContext().getValueStack().push(resultMap);

		return "editpassword";
	}

	// 业务方法，添加用户
	public String save() {
		return "saveSUCCESS";
	}
}
