package cn.itcast.bos.domain.bc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 取派员
 * 
 * @author seawind
 * 
 */
@Entity
@Table(name = "bc_staff")
@NamedQueries(value = { @NamedQuery(name = "Staff.findAllInUseStaffs", query = "from Staff where deltag is null") })
public class Staff {
	@Id
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	@GeneratedValue(generator = "uuidGenerator")
	private String id; // 编号
	@Column(name = "name", length = 20)
	private String name; // 姓名
	private String telephone; // 手机
	@Column(name = "haspda", columnDefinition = "char(1)")
	// 列定义 columnDefinition 只需要写类型长度就可以了，不需要写列名
	private String haspda; // 是否有PDA 设备
	private String deltag; // 删除标记 ， 执行逻辑删除
	private String station; // 单位
	private String standard; // 收派标准

	// 一对一 关联定区
	@OneToOne(mappedBy = "staff")
	// staff 是定区中取派员 属性名 ，mappedBy相当于 inverse=true
	private DecidedZone decidedZone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
		return haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return deltag;
	}

	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public DecidedZone getDecidedZone() {
		return decidedZone;
	}

	public void setDecidedZone(DecidedZone decidedZone) {
		this.decidedZone = decidedZone;
	}

}
