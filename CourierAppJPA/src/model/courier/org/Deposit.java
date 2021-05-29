package model.courier.org;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class Deposit {
	@Id
	private Integer depositID;
	private String location;
	private Double capacity;
	@OneToMany(mappedBy = "deposit", cascade = ALL)
	private List<Location> locations_served = new ArrayList<Location>();
	@OneToMany(mappedBy = "deposit", cascade = ALL)
	protected List<Package> packages_stocked = new ArrayList<Package>();
	@OneToMany(mappedBy = "deposit", cascade = ALL)
	private List<Courier> couriers_deposit = new ArrayList<Courier>();
	@ManyToOne(cascade = ALL )
	private Book book;
	
	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Deposit(Integer depositID, String location, Double capacity) {
		super();
		this.depositID = depositID;
		this.location = location;
		this.capacity = capacity;
	}

//	public Deposit(Integer depositID, String location, Double capacity, List<Location> locations_served) {
//		super();
//		this.depositID = depositID;
//		this.location = location;
//		this.capacity = capacity;
//		this.locations_served = locations_served;
//	}
	
	public Deposit(Integer depositID, String location, Double capacity, Book book) {
		super();
		this.depositID = depositID;
		this.location = location;
		this.capacity = capacity;
		this.book = book;
	}

	public Integer getDepositID() {
		return depositID;
	}

	public void setDepositID(Integer depositID) {
		this.depositID = depositID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public List<Location> getLocations_served() {
		return locations_served;
	}

	public void setLocations_served(List<Location> locations_served) {
		this.locations_served = locations_served;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void addToListOfLocationsServed1(Location l) {
		if(locations_served.isEmpty()) {
			locations_served.add(l);
		} else {
			for(Location lo: locations_served) {
				if(l.getPostalCode() == lo.getPostalCode()) {
					System.out.println("The location already exists in the list, so you can't add this location to the list! Try again with another location!");
				} else {
					locations_served.add(l);
					break;
				}
			}
		}
	}
	
	public void addToListOfLocationsServed(Location l) {
		if(locations_served.isEmpty()) {
			locations_served.add(l);
		} else {
			for(ListIterator<Location> iterator = locations_served.listIterator(); iterator.hasNext();) {
				Location lo = iterator.next();
				if(l.getPostalCode() == lo.getPostalCode()) {
					System.out.println("The location already exists in the list, so you can't add this location to the list! Try again with another location!");
				} else {
					locations_served.add(l);
					break;
				}
			}
		}
	}
	
	public void checkListOfLocationsServedByDeposit() {
		if(locations_served.isEmpty()) {
			System.out.println("The list of locations served by this deposit is empty! You cannot check the locations in this case! Try again !");
		} else {
			for(Location l: locations_served) {
				System.out.println("The deposit with ID " + this.getDepositID() + " has the location with postal code " + l.getPostalCode() + " located on " + l.getCityName() + " city");
			}
		}
	}
	
	public void addPackagesToDeposit1(Package pack) {
		if(packages_stocked.isEmpty()) {
			System.out.println("The lists of packages stocked in this deposit is empty, so you can freely add a new package");
			packages_stocked.add(pack);
		} else {
			for(Package p: packages_stocked) {
				if(p.getPackageID() == pack.getPackageID()) {
					System.out.println("The package already exists in the list! You can't add a package that already exists in the list!");
				} else {
					packages_stocked.add(pack);
					System.out.println("The package with id " + pack.getPackageID() + " was succesfully added to deposit with id " + this.getDepositID());
					break;
				}
			}
		}
	}
	
	public void addPackagesToDeposit(Package pack) {
		if(packages_stocked.isEmpty()) {
			System.out.println("The lists of packages stocked in this deposit is empty, so you can freely add a new package");
			packages_stocked.add(pack);
		} else {
			for(ListIterator<Package> iterator = packages_stocked.listIterator(); iterator.hasNext();) {
				Package p = iterator.next();
				if(p.getPackageID() == pack.getPackageID()) {
					System.out.println("The package already exists in the list! You can't add a package that already exists in the list!");
				} else {
					packages_stocked.add(pack);
					System.out.println("The package with id " + pack.getPackageID() + " was succesfully added to deposit with id " + this.getDepositID());
					break;
				}
			}
		}
	}
	
	public void checkPackagesFromDeposit() {
		if(packages_stocked.isEmpty()) {
			System.out.println("The lists of packages from deposit with id " + this.getDepositID() + " is empty, so you can't check the packages from it.");
		} else {
			for(Package p: packages_stocked) {
				System.out.println("The deposit with id " + this.getDepositID() + " has the package with id " + p.getPackageID());
			}
		}
	}
	
//	public void addCourierToDeposit1(Courier c) {
//		if(couriers_deposit.isEmpty()) {
//			couriers_deposit.add(c);
//		} else {
//			for(Courier co: couriers_deposit) {
//				if(co.getCourierID() == c.getCourierID()) {
//					System.out.println("The courier already exists in the list of couriers alocated to deposit " + depositID);
//				}
//				else {
//					couriers_deposit.add(c);
//					System.out.println("The courier with id " + c.getCourierID() + " was successfully added to deposit with id " + depositID);
//				}
//			}
//		}
//	}
	
	public void addCourierToDeposit(Courier c) {
		if(couriers_deposit.isEmpty()) {
			couriers_deposit.add(c);
		} else {
			for(ListIterator<Courier> iterator = couriers_deposit.listIterator(); iterator.hasNext();) {
				Courier courier = iterator.next();
				if(courier.getCourierID() == c.getCourierID()) {
					System.out.println("The courier already exists in the list of couriers alocated to deposit " + depositID);
				}
				else {
					iterator.add(c);
					System.out.println("The courier with id " + c.getCourierID() + " was successfully added to deposit with id " + depositID);
				}
			}
		}
	}
	
	public Book setBookToThisDelivery(List<Book> books) {
		Book book = new Book();
		if(books.isEmpty())
			System.out.println("The list of books is empty!");
		else {
			for(ListIterator<Book> iterator = books.listIterator(); iterator.hasNext();) {
				Book b = iterator.next();
				if(b.showDepositsSortedByLocation().contains(this)) {
					book = b;
				}
				else {
					System.out.println("-----");
				}
			}
		}
		return book;
	}
	
	@Override
	public String toString() {
		return "Deposit [depositID=" + depositID + ", location=" + location + ", capacity=" + capacity
				+ ", locations_served=" + locations_served + ", packages_stocked=" + packages_stocked + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depositID == null) ? 0 : depositID.hashCode());
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
		Deposit other = (Deposit) obj;
		if (depositID == null) {
			if (other.depositID != null)
				return false;
		} else if (!depositID.equals(other.depositID))
			return false;
		return true;
	}
	
	
	
}
