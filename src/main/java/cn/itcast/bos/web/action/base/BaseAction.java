package cn.itcast.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.bos.page.Pagination;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 表现层代码复用
 * 
 * @author seawind
 * 
 */
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	// 模型驱动 没有实现
	private T model;

	@Override
	public T getModel() {
		return model;
	}

	public BaseAction() {
		// 读取子类 命名中 泛型类型
		Type type = getClass().getGenericSuperclass();
		Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
		Class modelClass = (Class) trueType;
		try {
			model = (T) modelClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("获取实例化泛型信息失败...");
		}

		// 封装分页查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(modelClass);
		pagination.setDetachedCriteria(detachedCriteria);
	}

	// 属性驱动
	protected Pagination<T> pagination = new Pagination<T>();

	public void setPage(int page) {
		pagination.setPage(page);
	}

	public void setRows(int rows) {
		pagination.setPageSize(rows);
	}

	// 返回json 结果对象
	protected Object result;

	public Object getResult() {
		return result;
	}

}
