package cn.itcast.bos.domain.bc;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 区域
 * 
 * @author seawind
 * 
 */
@Entity
@Table(name = "bc_region")
@NamedQueries(value = { @NamedQuery(name = "Region.findRegionsByCondition", query = "from Region where province like ? or city like ? or district like ?"), @NamedQuery(name = "Region.findByProvinceCityDistrict", query = "from Region where province=? and city=? and district= ?") })
public class Region {
	@Id
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	@GeneratedValue(generator = "assignedGenerator")
	private String id; // 区域编号 （可以指定）
	private String province; // 省份信息
	private String city; // 城市信息
	private String district; // 区域信息
	private String postcode; // 邮编
	private String shortcode; // 简码 上海市闵行区 --- shmh
	private String citycode; // 城市编码 上海市 --- shanghai

	// 一对多 关联分区
	@OneToMany(mappedBy = "region")
	private Set<Subarea> subareas = new HashSet<Subarea>();

	// @JSON(serialize = false)
	// public Set<Subarea> getSubareas() {
	// return subareas;
	// }

	@Transient
	// @Transient 不会在表中生成列字段
	public String getName() {
		return province + "," + city + "," + district;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Set<Subarea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}
