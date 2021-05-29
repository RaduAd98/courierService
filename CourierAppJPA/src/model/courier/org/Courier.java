package model.courier.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Courier extends Employee {
	
	private Integer courierID;
	private String name;
	private String location;
	@OneToOne(cascade = ALL)
	private Deposit deposit;
	
	@OneToMany(mappedBy = "courier", cascade = ALL)
	private List<Delivery> deliveriesToBeServed = new ArrayList<Delivery>();

	public Courier() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Courier(Integer courierID, String name, String location) {
		super();
		this.courierID = courierID;
		this.name = name;
		this.location = location;
	}

	public Courier(Integer employeeID, String name, Departaments departamentName, Integer courierID,
			String location) {
		super(employeeID, name, departamentName);
		this.courierID = courierID;
		this.location = location;
		this.name = name;
	}
	
	public Courier(Integer employeeID, String name, Departaments departamentName, Integer courierID,
			String location, List<Deposit> deposits) {
		super(employeeID, name, departamentName);
		this.courierID = courierID;
		this.location = location;
		this.deposit = allocateThisCourierToDeposit(deposits);
		this.name = name;
	}
	

//	public Courier(Integer courierID, String name, String location, Deposit deposit) {
//		super();
//		this.courierID = courierID;
//		this.name = name;
//		this.location = location;
//		this.deposit = deposit;
//	}

//	public Courier(Integer courierID, String name, String location, Deposit deposit,
//			List<Delivery> deliveriesToBeServed) {
//		super();
//		this.courierID = courierID;
//		this.name = name;
//		this.location = location;
//		this.deposit = deposit;
//		this.deliveriesToBeServed = deliveriesToBeServed;
//	}

	public Integer getCourierID() {
		return courierID;
	}

	public void setCourierID(Integer courierID) {
		this.courierID = courierID;
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

//	public Deposit getDeposit() {
//		return deposit;
//	}
//
	public void setDeposit(List<Deposit> deposits) {
		this.deposit = allocateThisCourierToDeposit(deposits);
	}
	
	public Departaments getDepartmentName() {
		return Departaments.Delivery;
	}
	
	public Deposit getDeposit() {
		return deposit;
	}
	
	public List<Delivery> getDeliveriesToBeServed() {
		return deliveriesToBeServed;
	}

	public void setDeliveriesToBeServed(List<Delivery> deliveriesToBeServed) {
		this.deliveriesToBeServed = deliveriesToBeServed;
	}
	
	public void addDeliveries(Delivery d) {
		if(deliveriesToBeServed.contains(d)) {
			System.out.println("The courier has already one delivery with this characteristics! Try again with another delivery!");
		}
		else {
			deliveriesToBeServed.add(d);
			System.out.println("The delivery has been added to the list! The courier will deliver the packages to the destinations soon! ");
		}
	}
	
	public void showSalaryOfThisEmployee(Integer numberOfHoursWorked) {
		calculateSalary(getDepartamentName(), numberOfHoursWorked);
	}
	
	public Deposit allocateThisCourierToDeposit1(List<Deposit> deposits) {
		Deposit deposit1 = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty, so you can't add any courier to any of these! Try again with a list with deposits!");
		} else {
			for(Deposit d: deposits) {
				if(this.location== d.getLocation() || this.location == d.getLocations_served().iterator().next().getCountyName()) {
					d.addCourierToDeposit(this);
					deposit1 = d;
					System.out.println("The courier with id " + this.courierID + " was successfully added to the deposit with id " + d.getDepositID());
				} else {
					System.out.println("The deposit with id " + d.getDepositID() + " is not the deposit for courier with id " + this.courierID);
				}
			}
		}
		return deposit;
	}
	
	public Deposit allocateThisCourierToDeposit(List<Deposit> deposits) {
		Deposit deposit1 = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty, so you can't add any courier to any of these! Try again with a list with deposits!");
		} else {
			for(ListIterator<Deposit> iterator = deposits.listIterator(); iterator.hasNext();) {
				Deposit d = iterator.next();
				if(this.location== d.getLocation()) {
					d.addCourierToDeposit(this);
					deposit1 = d;
					System.out.println("The courier with id " + this.courierID + " was successfully added to the deposit with id " + d.getDepositID());
				} else {
					System.out.println("The deposit with id " + d.getDepositID() + " is not the deposit for courier with id " + this.courierID);
				}
			}
		}
		return deposit1;
	}
	
	public void getCourierName() {
		
		
	}

	@Override
	public String toString() {
		return "Courier [courierID=" + courierID + ", name=" + name + ", location=" + location 
				+ ", deliveriesToBeServed=" + deliveriesToBeServed + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courierID == null) ? 0 : courierID.hashCode());
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
		Courier other = (Courier) obj;
		if (courierID == null) {
			if (other.courierID != null)
				return false;
		} else if (!courierID.equals(other.courierID))
			return false;
		return true;
	}
	
	
	
}
