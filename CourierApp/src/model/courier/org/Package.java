package model.courier.org;

public class Package {
	
	private Integer packageID;
	private TypeOfPackage type;
	private Double weight;
	private Sender sender;
	private Recipient recipient;
	
	public Package() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Package(Integer packageID, TypeOfPackage type, Double weight, Sender sender, Recipient recipient) {
		super();
		this.packageID = packageID;
		this.type = type;
		this.weight = weight;
		this.sender = sender;
		this.recipient = recipient;
	}

	public Integer getPackageID() {
		return packageID;
	}

	public void setPackageID(Integer packageID) {
		this.packageID = packageID;
	}

	public TypeOfPackage getType() {
		return type;
	}

	public void setType(TypeOfPackage type) {
		this.type = type;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	@Override
	public String toString() {
		return "Package [packageID=" + packageID + ", type=" + type + ", weight=" + weight + ", sender=" + sender
				+ ", recipient=" + recipient + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packageID == null) ? 0 : packageID.hashCode());
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
		Package other = (Package) obj;
		if (packageID == null) {
			if (other.packageID != null)
				return false;
		} else if (!packageID.equals(other.packageID))
			return false;
		return true;
	}
	
	

}
