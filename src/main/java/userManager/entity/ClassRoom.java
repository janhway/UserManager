package userManager.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CLASS")
public class ClassRoom implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private School school;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 名字会变化 fix it later.
	@Column(name = "CLASSNAME", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "SCHOOLID", referencedColumnName = "ID")
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClassRoom)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		ClassRoom other = (ClassRoom) obj;

		return (this.id == other.id) && (this.name == other.name);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + id;
		result = result * 31 + name.hashCode();

		return result;
	}
}
