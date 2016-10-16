package cn.itcast.bos.web.action.bc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.bc.SubareaService;
import cn.itcast.bos.utils.FileUtils;
import cn.itcast.bos.web.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

/**
 * 分区管理
 * 
 * @author seawind
 * 
 */
public class SubareaAction extends BaseAction<Subarea> {

	// 注入Service
	@Resource(name = "subareaService")
	private SubareaService subareaService;

	// 业务方法 --- 添加分区
	public String save() {
		// model已经封装 分区信息，关联具有id的Region对象
		// 调用业务层，完成保存
		subareaService.saveSubarea(getModel());
		return "saveSUCCESS";
	}

	// 业务方法 --- 分页查询
	public String pageQuery() {
		// 调用业务层，进行分页查询
		// 封装查询条件对象
		DetachedCriteria detachedCriteria = pagination.getDetachedCriteria();
		if (StringUtils.isNotBlank(getModel().getAddresskey())) {
			// 根据关键字查询
			detachedCriteria.add(Restrictions.like("addresskey", "%" + getModel().getAddresskey() + "%"));
		}
		if (getModel().getDecidedZone() != null && StringUtils.isNotBlank(getModel().getDecidedZone().getId())) {
			// 根据定区编码查询
			detachedCriteria.add(Restrictions.eq("decidedZone", getModel().getDecidedZone()));
		}
		// 关联区域
		if (getModel().getRegion() != null) {
			// 表关联
			DetachedCriteria regionCriteria = detachedCriteria.createCriteria("region", DetachedCriteria.INNER_JOIN);
			if (StringUtils.isNotBlank(getModel().getRegion().getProvince())) {
				// 按照省份查询
				regionCriteria.add(Restrictions.like("province", "%" + getModel().getRegion().getProvince() + "%"));
			}
			if (StringUtils.isNotBlank(getModel().getRegion().getCity())) {
				// 按照城市查询
				regionCriteria.add(Restrictions.like("city", "%" + getModel().getRegion().getCity() + "%"));
			}
			if (StringUtils.isNotBlank(getModel().getRegion().getDistrict())) {
				// 按照区域查询
				regionCriteria.add(Restrictions.like("district", "%" + getModel().getRegion().getDistrict() + "%"));
			}
		}

		subareaService.pageQuery(pagination);
		// 压入值栈返回
		ActionContext.getContext().getValueStack().push(pagination);
		return "pageQuerySUCCESS";
	}

	// 业务方法 ---- 根据查询条件，导出所有
	public String export() throws IOException {
		// 1、 封装DetachedCriteria
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		if (StringUtils.isNotBlank(getModel().getAddresskey())) {
			// 根据关键字查询
			detachedCriteria.add(Restrictions.like("addresskey", "%" + getModel().getAddresskey() + "%"));
		}
		if (getModel().getDecidedZone() != null && StringUtils.isNotBlank(getModel().getDecidedZone().getId())) {
			// 根据定区编码查询
			detachedCriteria.add(Restrictions.eq("decidedZone", getModel().getDecidedZone()));
		}
		// 关联区域
		if (getModel().getRegion() != null) {
			// 表关联
			DetachedCriteria regionCriteria = detachedCriteria.createCriteria("region", DetachedCriteria.INNER_JOIN);
			if (StringUtils.isNotBlank(getModel().getRegion().getProvince())) {
				// 按照省份查询
				regionCriteria.add(Restrictions.like("province", "%" + getModel().getRegion().getProvince() + "%"));
			}
			if (StringUtils.isNotBlank(getModel().getRegion().getCity())) {
				// 按照城市查询
				regionCriteria.add(Restrictions.like("city", "%" + getModel().getRegion().getCity() + "%"));
			}
			if (StringUtils.isNotBlank(getModel().getRegion().getDistrict())) {
				// 按照区域查询
				regionCriteria.add(Restrictions.like("district", "%" + getModel().getRegion().getDistrict() + "%"));
			}
		}

		// 2、 调用业务层，查询当前条件所有数据
		List<Subarea> subareas = subareaService.findByCondition(detachedCriteria);

		// 3、 根据 查询结果，生成Excel文件，提供下载
		// 创建工作薄
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		// 创建sheet
		HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");
		// 一行行写数据
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("区域编码");
		headRow.createCell(2).setCellValue("关键字");
		headRow.createCell(3).setCellValue("起始号");
		headRow.createCell(4).setCellValue("结束号");
		headRow.createCell(5).setCellValue("单双号");
		headRow.createCell(6).setCellValue("位置信息");
		// 每个对象 写一行
		for (Subarea subarea : subareas) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			dataRow.createCell(3).setCellValue(subarea.getStartnum());
			dataRow.createCell(4).setCellValue(subarea.getEndnum());
			dataRow.createCell(5).setCellValue(subarea.getSingle());
			dataRow.createCell(6).setCellValue(subarea.getPosition());

		}

		// 4、文件下载
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		String filename = "分区数据查询结果.xls";
		filename = FileUtils.encodeDownloadFilename(filename, ServletActionContext.getRequest().getHeader("user-agent"));
		ServletActionContext.getResponse().setContentType(ServletActionContext.getServletContext().getMimeType(filename));
		ServletActionContext.getResponse().setHeader("Content-Disposition", "attachment;filename=" + filename);
		hssfWorkbook.write(ServletActionContext.getResponse().getOutputStream());

		return NONE;
	}

	// 业务方法 --- 查询未关联定区的分区
	public String findnoassociations() {
		// 调用业务层，查询未关联定区的分区
		List<Subarea> subareas = subareaService.findAllNoAssociations();

		// 压入值栈
		ActionContext.getContext().getValueStack().push(subareas);
		return "findnoassociationsSUCCESS";
	}
}
