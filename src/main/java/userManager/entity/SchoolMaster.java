package userManager.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "SCHOOLMASTER")
public class SchoolMaster implements Serializable {

	private static final long serialVersionUID = 2008853282348896271L;

	private SchoolMasterId schoolMasterId;

	@Id
	public SchoolMasterId getSchoolMasterId() {
		return schoolMasterId;
	}

	public void setSchoolMasterId(SchoolMasterId schoolMasterId) {
		this.schoolMasterId = schoolMasterId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SchoolMaster)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		SchoolMaster other = (SchoolMaster) obj;

		return (this.schoolMasterId.equals(other.schoolMasterId));
	}

	@Override
	public int hashCode() {
		return this.schoolMasterId.hashCode();
	}

}
