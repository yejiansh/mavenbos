package cn.itcast.bos.domain.bc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 分区
 * 
 * @author seawind
 * 
 */
@Entity
@Table(name = "bc_subarea")
@NamedQueries(value = { @NamedQuery(name = "Subarea.findnoassociations", query = "from Subarea where decidedZone is null") })
public class Subarea {
	@Id
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	@GeneratedValue(generator = "assignedGenerator")
	private String id; // 编号
	private String addresskey; // 关键字
	private String startnum; // 起始号
	private String endnum; // 结束号
	private String single; // 是否区分单双号
	private String position; // 位置信息

	// 多对一 关联定区
	@ManyToOne
	@JoinColumn(name = "decidedzone_id")
	private DecidedZone decidedZone;

	// 多对一 关联区域
	@ManyToOne
	private Region region;

	// 为了返回json 含有subareaid 属性
	@Transient
	public String getSubareaid() {
		return id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddresskey() {
		return addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
		return startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public DecidedZone getDecidedZone() {
		return decidedZone;
	}

	public void setDecidedZone(DecidedZone decidedZone) {
		this.decidedZone = decidedZone;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}
