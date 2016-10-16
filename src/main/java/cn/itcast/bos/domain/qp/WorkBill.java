package cn.itcast.bos.domain.qp;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.itcast.bos.domain.bc.Staff;

/**
 * WorkBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qp_workbill", catalog = "bos")
public class WorkBill implements java.io.Serializable {

	// Fields

	private String id; // 编号 uuid
	private NoticeBill noticeBill; // 关联通知单
	private Staff staff; // 取派员
	private String type; // 工单类型 ： 新、追、销
	private String pickstate; // 取件状态 ： 新单、已通知 、已确认、 已取件、已取消
	private Timestamp buildtime; // 工单产生时间
	private Integer attachbilltimes; // 追单次数
	private String remark; // 备注

	// Constructors

	/** default constructor */
	public WorkBill() {
	}

	/** minimal constructor */
	public WorkBill(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	/** full constructor */
	public WorkBill(NoticeBill noticeBill, Staff staff, String type, String pickstate, Timestamp buildtime, Integer attachbilltimes, String remark) {
		this.noticeBill = noticeBill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticebill_id")
	public NoticeBill getNoticeBill() {
		return this.noticeBill;
	}

	public void setNoticeBill(NoticeBill noticeBill) {
		this.noticeBill = noticeBill;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id")
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "pickstate", length = 20)
	public String getPickstate() {
		return this.pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	@Column(name = "buildtime", nullable = false, length = 0)
	public Timestamp getBuildtime() {
		return this.buildtime;
	}

	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	@Column(name = "attachbilltimes")
	public Integer getAttachbilltimes() {
		return this.attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}