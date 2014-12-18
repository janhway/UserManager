package userManager.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TeacherId implements Serializable {

	private static final long serialVersionUID = 7338973140234139520L;

	private User user;
	private ClassRoom classRoom;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "CLASSID", referencedColumnName = "ID")
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "USERID", referencedColumnName = "ID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TeacherId)) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		TeacherId other = (TeacherId) obj;

		return (this.user.getId() == other.user.getId() && 
				this.classRoom.getId() == other.classRoom.getId());
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + user.getId();
		result = result * 31 + classRoom.getId();
		return result;
	}

	
}
