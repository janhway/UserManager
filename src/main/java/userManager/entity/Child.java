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
@Table(name = "CHILD")
public class Child implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String birthday;
	private ClassRoom classRoom;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "BIRTHDAY")
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "CLASSID", referencedColumnName = "ID")
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Child)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		Child other = (Child) obj;

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
