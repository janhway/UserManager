package userManager.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  
@Table(name="SCHOOL")
public class School implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String address;
	private String postalNum;
	private String phone;
	
	private List<ClassRoom> classList;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "SCHOOLNAME", unique = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "POSTALNUM")
	public String getPostalNum() {
		return postalNum;
	}

	public void setPostalNum(String postalNum) {
		this.postalNum = postalNum;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<ClassRoom> getClassList() {
		return classList;
	}

	public void setClassList(List<ClassRoom> classList) {
		this.classList = classList;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof School)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		School other = (School) obj;

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
