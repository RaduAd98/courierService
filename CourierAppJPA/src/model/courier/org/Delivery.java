package model.courier.org;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import static javax.persistence.TemporalType.DATE;
import javax.persistence.ManyToOne;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.MapsId;
import javax.persistence.JoinTable;

@Entity
public class Delivery {
	
	@Id
	private Integer deliveryAWB;
	@ManyToOne(cascade = ALL)
	private Package pack;
	@Temporal(DATE)
	private Date dateOfDelivery;
	private Double value;
	@ManyToOne
	private Courier courier;
	@ManyToOne(cascade = ALL)
	private Book book;

	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Delivery(Integer deliveryAWB,Date dateOfDelivery, Package pack) {
		super();
		this.deliveryAWB = deliveryAWB;
		this.pack = pack;
		this.dateOfDelivery = dateOfDelivery;
	}
	
	public Delivery(Integer deliveryAWB,Date dateOfDelivery, Package pack, HashMap<Courier, Boolean> couriers, Book book) {
		super();
		this.deliveryAWB = deliveryAWB;
		this.pack = pack;
		this.dateOfDelivery = dateOfDelivery;
		this.courier = setDeliveryForCourier(couriers, this);
		this.value = getValue();
		this.book = book;
	}
	
	public Delivery(Integer deliveryAWB,Date dateOfDelivery, Package pack, Courier courier) {
		super();
		this.deliveryAWB = deliveryAWB;
		this.pack = pack;
		this.dateOfDelivery = dateOfDelivery;
		this.courier = courier;
	}

	public Integer getDeliveryAWB() {
		return deliveryAWB;
	}

	public void setDeliveryAWB(Integer deliveryAWB) {
		this.deliveryAWB = deliveryAWB;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}
	
	public Double getValue() {
		return valueOfDelivery();
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	public Courier getCourier(HashMap<Courier, Boolean> couriers, Delivery delivery) {
		return setDeliveryForCourier(couriers, this);
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Courier setDeliveryForCourier(HashMap<Courier, Boolean> couriers, Delivery delivery) {
//		couriers = new HashMap<Courier, Boolean>();
		String locationOfDelivery = pack.getRecipient().getLocation();
		Boolean foundCourier = false;
		Courier courierAvailable  = null;
		if(couriers.isEmpty() == true) {
			System.out.println("The List of couriers is empty! Please fill it and try again!");
		} else {
			if(foundCourier==false) {
			for(Courier c: couriers.keySet()) {
				if(c.getLocation() == locationOfDelivery) {
					for(Boolean b: couriers.values()) {
						if(b == true ) {
							c.addDeliveries(delivery);
							System.out.println("The courier: " + c.getName() + " will transport this delivery soon!");
							courierAvailable=c;
							foundCourier=true;
						}
						else {
							System.out.println("The courier: " + c.getName() +  " is not available for another delivery!");
						}
					}
				}	
			}
		   }
		  }
		return courierAvailable;
	}
	

	public Double valueOfDelivery(){
		Double pricePerKg;
		if(pack != null && pack.getWeight() !=null) {
			if(pack.getWeight() >= 1 && pack.getWeight() < 2.00) {
				pricePerKg = 8.00;
				value = pricePerKg * pack.getWeight();
			} else if(pack.getWeight() >=2.00 && pack.getWeight() < 5.00) {
				pricePerKg = 7.50;
				value = pricePerKg * pack.getWeight();
			} else if(pack.getWeight() >=5.00 && pack.getWeight() < 10.00) {
				pricePerKg = 7.00;
				value = pricePerKg * pack.getWeight();
			} else if(pack.getWeight() > 10.00) {
				pricePerKg = 6.50;
				value  = pricePerKg * pack.getWeight();
			} else {
				pricePerKg = 9.00;
				value = pricePerKg * pack.getWeight();
			}
			System.out.println("The price of the delivery for package with ID " + pack.getPackageID() + " is " + value + " RON");
		}
		else {
			System.out.println("You have provided an empty package, so you can't calculate the value of the delivery!");
		}
		
		return value;
	}
	
	public Deposit attachPackageToDeposit1(List<Deposit> deposits) {
		Deposit deposit = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty! You can't attach a delivery to something yet!");
		} else {
			for(Deposit d: deposits) {
				if(d.packages_stocked.contains(pack)) {
					System.out.println("The package already exists in the list of packages of deposit " + d.getDepositID());
				} else {
					if(this.getPack().getRecipient().getLocation() == d.getLocation() || this.getPack().getRecipient().getLocation() == d.getLocations_served().iterator().next().getCityName()) {
						d.addPackagesToDeposit(pack);
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
				if(d.packages_stocked.contains(pack)) {
					System.out.println("The package already exists in the list of packages of deposit " + d.getDepositID());
				} else {
					if(this.getPack().getRecipient().getLocation() == d.getLocation() || this.getPack().getRecipient().getLocation() == d.getLocations_served().iterator().next().getCityName()) {
						d.addPackagesToDeposit(pack);
						deposit = d;
						System.out.println("The package was added with success to the deposit!");
					} 
			}
		  }
		}
		return deposit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deliveryAWB == null) ? 0 : deliveryAWB.hashCode());
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
		Delivery other = (Delivery) obj;
		if (deliveryAWB == null) {
			if (other.deliveryAWB != null)
				return false;
		} else if (!deliveryAWB.equals(other.deliveryAWB))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Delivery [deliveryAWB=" + deliveryAWB + ", pack=" + pack + ", dateOfDelivery=" + dateOfDelivery
				+ ", value=" + value + "]";
	}
	
	
}
