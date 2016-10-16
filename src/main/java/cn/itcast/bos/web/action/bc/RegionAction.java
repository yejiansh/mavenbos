package cn.itcast.bos.web.action.bc;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import cn.itcast.bos.domain.bc.Region;
import cn.itcast.bos.service.bc.RegionService;
import cn.itcast.bos.utils.PinYin4jUtils;
import cn.itcast.bos.web.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

/**
 * 区域管理
 * 
 * @author seawind
 * 
 */
public class RegionAction extends BaseAction<Region> {

	private Logger log = Logger.getLogger(this.getClass());

	private File file;

	public void setFile(File file) {
		this.file = file;
	}

	// 注入Service
	@Resource(name = "regionService")
	private RegionService regionService;

	// 业务方法 --- 批量导入
	public String importXls() throws Exception {
		log.info("执行区域信息批量导入...." + file);
		// 使用POI API解析Excel , 将每一行数据 ，封装Region对象中
		List<Region> regions = new ArrayList<Region>();
		// 1、创建 HSSFWorkbook
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(file));
		// 2、读取指定sheet
		Sheet sheet = hssfWorkbook.getSheetAt(0); // 读取第一个sheet
		// 3、 读取sheet的每一行
		for (Row row : sheet) { // row 每一行
			// 4、 读取每一个格子信息
			// 跳过第一行
			if (row.getRowNum() == 0 || row.getCell(0).getStringCellValue().equals("")) {
				continue;
			}
			Region region = new Region();
			region.setId(row.getCell(0).getStringCellValue());
			region.setProvince(row.getCell(1).getStringCellValue());
			region.setCity(row.getCell(2).getStringCellValue());
			region.setDistrict(row.getCell(3).getStringCellValue());
			region.setPostcode(row.getCell(4).getStringCellValue());

			// 生成简码和城市编码
			String province = region.getProvince().substring(0, region.getProvince().length() - 1);
			String city = region.getCity().substring(0, region.getCity().length() - 1);
			String district = region.getDistrict().substring(0, region.getDistrict().length() - 1);

			// 简码
			String[] headers = PinYin4jUtils.getHeadByString(province + city + district);
			String shortcode = "";
			for (String header : headers) {
				shortcode += header;
			}
			region.setShortcode(shortcode);

			// 城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setCitycode(citycode);
			regions.add(region);
		}

		try {
			// 调用业务层，完成数据保存
			regionService.saveBatch(regions);
			ServletActionContext.getResponse().getWriter().print("success");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			ServletActionContext.getResponse().getWriter().print("failure");
		}

		return NONE;
	}

	// 业务方法 --- 分页查询
	public String pageQuery() {
		// 调用业务层
		regionService.pageQuery(pagination);

		// 压入值栈返回
		ActionContext.getContext().getValueStack().push(pagination);
		return "pageQuerySUCCESS";
	}

	// 业务方法 --- 区域json列表
	public String ajaxlist() {
		List<Region> regions = null;
		if (StringUtils.isNotBlank(q)) {
			// 查询条件存在
			regions = regionService.findRegionsByCondition(q);
		} else {
			// 调用业务层，查询所有数据
			regions = regionService.findAllRegions();
		}
		// 将列表转换为 json返回
		ActionContext.getContext().getValueStack().push(regions);

		return "ajaxlistSUCCESS";
	}

	private String q;

	public void setQ(String q) {
		this.q = q;
	}

}
