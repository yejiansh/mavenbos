package cn.itcast.bos.domain.bc;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 定区
 * 
 * @author seawind
 * 
 */
@Entity
@Table(name = "bc_decidedzone")
public class DecidedZone {
	@Id
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	@GeneratedValue(generator = "assignedGenerator")
	private String id; // 定区编号
	private String name; // 定区名称

	// 一对一 关联取派员
	@OneToOne
	private Staff staff; // 负责定区取派员

	// 一对多 关联分区
	@OneToMany(mappedBy = "decidedZone")
	private Set<Subarea> subareas = new HashSet<Subarea>(); // 包含的分区

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

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Set<Subarea> getSubareas() {
		return subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}
