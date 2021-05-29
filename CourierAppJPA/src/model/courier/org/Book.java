package model.courier.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Basic;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import static javax.persistence.TemporalType.DATE;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.JoinColumn;

@Entity
public class Book {
	
	@Id
	private Integer bookID;
	@Temporal(DATE)
	private Date date;
	
	@OneToMany(cascade = ALL, mappedBy = "book")
	private List<Deposit> deposits = new ArrayList<Deposit>();
	
	@OneToMany(cascade = ALL, mappedBy = "book")
	private List<Delivery> deliveries = new ArrayList<Delivery>();
	
	public Book() {
		super();
	}

	public Book(Integer bookID, Date date) {
		super();
		this.bookID = bookID;
		this.date = date;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void addToTheListOfDeposits1(Deposit d) {
		if(deposits.isEmpty()) {
			deposits.add(d);
		} else {
			for(Deposit e: deposits) {
				if(e.getDepositID() == d.getDepositID()) {
					System.out.println("The deposit already exists in the list! Try again with another!");
				}
				else {
					deposits.add(d);
					System.out.println("The mentioned deposit was added to the list of deposits!");
				}
				
			}
		}
	}
	

	
	public void addToTheListOfDeposits(Deposit d) {
		if(deposits.isEmpty()) {
			deposits.add(d);
		} else {
			for(ListIterator<Deposit> iterator = deposits.listIterator(); iterator.hasNext();) {
				Deposit e = iterator.next();
				if(e.getDepositID() == d.getDepositID()) {
					System.out.println("The deposit already exists in the list! Try again with another!");
				}
				else {
					deposits.add(d);
					System.out.println("The mentioned deposit was added to the list of deposits!");
				}
				
			}
		}
	}
	
	public void checkDepositsInTheList() {
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits is empty! So you can't verify what deposits are in the list!");
		} else {
			for(Deposit d: deposits) {
				System.out.println("The deposit: " + d.getDepositID() + " is located on " + d.getLocation() + " and has a capacity of: " + d.getCapacity() + " kg's.");
			}
		}
	}
	
	public void addDeliveryToTheListOfDeliveries1(Delivery d) {
		if(deliveries.isEmpty()) {
			deliveries.add(d);
		} else {
			for(Delivery e: deliveries) {
				if(e.getDeliveryAWB() == d.getDeliveryAWB()) {
					System.out.println("The delivery exists already in the list of deliveries! Try again with another delivery!");
				}
				else {
					deliveries.add(d);
					break;
				}
			}
		}
	}
	
	public void addDeliveryToTheListOfDeliveries(Delivery d) {
		if(deliveries.isEmpty()) {
			deliveries.add(d);
		} else {
			for(ListIterator<Delivery> iterator = deliveries.listIterator(); iterator.hasNext();) {
				Delivery e = iterator.next();
				if(e.getDeliveryAWB() == d.getDeliveryAWB()) {
					System.out.println("The delivery exists already in the list of deliveries! Try again with another delivery!");
				}
				else {
					deliveries.add(d);
					break;
				}
			}
		}
	}
	
	public void checkDeliveriesInTheList() {
		if(deliveries.isEmpty()) {
			System.out.println("The list of deliveries is empty! So you can't verify what deliveries are in the list!");
		}
		else {
			for(Delivery d: deliveries) {
				System.out.println("The delivery with the AWB " + d.getDeliveryAWB() + " has a date of delivery of " + d.getDateOfDelivery() + " and the package to be delivered is " + d.getPack());
			}
		}
	}
	
	public Double checkTotalValueOfDeliveries(Collection<Delivery> deliveries) {
		Double value = 0.00;
		if(deliveries.isEmpty()) {
			System.out.println("The list of deliveries is empty. Check it again and try another time!");
		} else {
			for(Delivery d: deliveries) {
				value = value + d.getValue();
			}
		}
		return value;
	}
	
	public void checkIndividualValueOfDeliveries(LinkedList<Delivery> deliveries) {
		if(deliveries.isEmpty()) {
			System.out.println("The list of deliveries is empty, so you can't check the value of any of the individual delivery!");
		} else {
			ListIterator<Delivery> iterator = deliveries.listIterator();
			while(iterator.hasNext()) {
				iterator.next().getValue();
			}
		}
	}
	
	public Collection<Delivery> showDeliveriesSortedByAWB(){
		return this.deliveries.stream()
				.sorted((d1,d2) -> d1.getDeliveryAWB().compareTo(d1.getDeliveryAWB()))
				.collect(Collectors.toList());
	}
	
	public Collection<Deposit> showDepositsSortedByLocation(){
		return this.deposits.stream()
				.sorted((d1,d2) -> d1.getLocation().compareTo(d2.getLocation()))
				.collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", date=" + date + ", deposits=" + deposits + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
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
		Book other = (Book) obj;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		return true;
	}
	
	
		
}
