package cn.itcast.bos.domain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_user")
// 使用 lombok 注解@Data 生成setter 和 getter
@NamedQueries(value = { @NamedQuery(name = "User.login", query = "from User where username=? and password=?") })
public class User {
	@Id
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid")
	// 使用hibernate 注解定义生成器
	@GeneratedValue(generator = "uuidGenerator")
	private String id;
	@Column(name = "username", length = 20)
	private String username;
	private String password;
	private Double salary;
	@Temporal(TemporalType.DATE)
	// 针对日期
	private Date birthday;
	private String gender;
	private String station;
	private String telephone;
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
