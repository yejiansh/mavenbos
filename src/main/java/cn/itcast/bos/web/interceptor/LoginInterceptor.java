package cn.itcast.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器
 * 
 * @author seawind
 * 
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 判断用户是否登陆，如果没有登陆，跳转login，如果登陆允许访问
		if (ServletActionContext.getRequest().getSession().getAttribute("user") == null) {
			// 没有登陆
			ActionSupport action = (ActionSupport) invocation.getAction();
			action.addActionError(action.getText("nologin"));
			return "login";
		} else {
			// 已经登陆
			return invocation.invoke();
		}
	}

}
