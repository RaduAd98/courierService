package model.courier.org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import java.util.List;
import java.util.ListIterator;

import javax.persistence.Enumerated;
import static javax.persistence.EnumType.STRING;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Package {
	
	@Id
	private Integer packageID;
	@Enumerated(STRING)
	private TypeOfPackage type;
	private Double weight;
	@OneToOne(cascade = ALL)
	private Sender sender;
	@OneToOne(cascade = ALL)
	private Recipient recipient;
	@ManyToOne(cascade = ALL)
	private Deposit deposit;
	
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
	
	public Package(Integer packageID, TypeOfPackage type, Double weight, Sender sender, Recipient recipient, List<Deposit> deposits) {
		super();
		this.packageID = packageID;
		this.type = type;
		this.weight = weight;
		this.sender = sender;
		this.recipient = recipient;
		this.deposit = attachPackageToDeposit(deposits);
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
	
	public Deposit attachPackageToDeposit1(List<Deposit> deposits) {
		Deposit deposit = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty! You can't attach a delivery to something yet!");
		} else {
			for(Deposit d: deposits) {
				if(d.packages_stocked.contains(this)) {
					System.out.println("The package already exists in the list of packages of deposit " + d.getDepositID());
				} else {
					if(this.getRecipient().getLocation() == d.getLocation() || this.getRecipient().getLocation() == d.getLocations_served().iterator().next().getCityName()) {
						d.addPackagesToDeposit(this);
						deposit = d;
						System.out.println("The package was added with success to the deposit!");
					} 
			}
		  }
		}
		return deposit;
	}
	
	public Deposit attachPackageToDeposit(List<Deposit> deposits) {
		Deposit deposit = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty! You can't attach a delivery to something yet!");
		} else {
			for(ListIterator<Deposit> iterator = deposits.listIterator(); iterator.hasNext();) {
				Deposit d = iterator.next();
				if(d.packages_stocked.contains(this)) {
					System.out.println("The package already exists in the list of packages of deposit " + d.getDepositID());
				} else {
					if(this.getRecipient().getLocation() == d.getLocation()) {
						d.addPackagesToDeposit(this);
						deposit = d;
						System.out.println("The package was added with success to the deposit!");
					} 
			}
		  }
		}
		return deposit;
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
