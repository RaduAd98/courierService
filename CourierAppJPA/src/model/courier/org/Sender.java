package model.courier.org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;

@Entity
public class Sender {
	@Id
	private Integer senderID;
	private String name;
	private String location;
	@Enumerated(STRING)
	private TypeOfCustomer type;

	public Sender() {
		super();
	}

	public Sender(Integer senderID, String name, String location, TypeOfCustomer type) {
		super();
		this.senderID = senderID;
		this.name = name;
		this.location = location;
		this.type = type;
	}

	public Integer getSenderID() {
		return senderID;
	}

	public void setSenderID(Integer senderID) {
		this.senderID = senderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public TypeOfCustomer getType() {
		return type;
	}

	public void setType(TypeOfCustomer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Sender [senderID=" + senderID + ", name=" + name + ", location=" + location + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((senderID == null) ? 0 : senderID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sender other = (Sender) obj;
		if (senderID == null) {
			if (other.senderID != null)
				return false;
		} else if (!senderID.equals(other.senderID))
			return false;
		return true;
	}
	
	
	
	
	
}
