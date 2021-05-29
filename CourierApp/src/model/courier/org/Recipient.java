package model.courier.org;

public class Recipient {
	private Integer recipientID;
	private String name;
	private String location;
	private TypeOfCustomer type;
	
	public Recipient() {
		super();
	}

	public Recipient(Integer recipientID, String name, String location, TypeOfCustomer type) {
		super();
		this.recipientID = recipientID;
		this.name = name;
		this.location = location;
		this.type = type;
	}

	public Integer getRecipientID() {
		return recipientID;
	}

	public void setRecipientID(Integer recipientID) {
		this.recipientID = recipientID;
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
		return "Recipient [recipientID=" + recipientID + ", name=" + name + ", location=" + location + ", type=" + type
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipientID == null) ? 0 : recipientID.hashCode());
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
		Recipient other = (Recipient) obj;
		if (recipientID == null) {
			if (other.recipientID != null)
				return false;
		} else if (!recipientID.equals(other.recipientID))
			return false;
		return true;
	}
	
	

}
