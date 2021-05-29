package model.courier.org;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestCourierJPA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourierAppJPA");
		EntityManager em = emf.createEntityManager();
		
		List<Recipient> recipients = new ArrayList<Recipient>();
		recipients.add(new Recipient(100, "Ion George", "Iasi", TypeOfCustomer.F));
		recipients.add(new Recipient(101, "Ionica Ion", "Botosani", TypeOfCustomer.F));
		recipients.add(new Recipient(102, "Georgeta Maria", "Bacau", TypeOfCustomer.F));
		recipients.add(new Recipient(103, "Daniel Giurescu", "Bacau", TypeOfCustomer.J));
		recipients.add(new Recipient(104, "Valentin Nistor", "Piatra Neamt", TypeOfCustomer.F));
		recipients.add(new Recipient(105, "Eugenia Niculescu", "Suceava", TypeOfCustomer.F));
		recipients.add(new Recipient(106, "Jenica Istrati", "Buhusi", TypeOfCustomer.F));
		recipients.add(new Recipient(107, "Luiza Pacuraru", "Bacau", TypeOfCustomer.F));
	
		//Add to local Database;
		em.getTransaction().begin();
		for(int k=0; k < recipients.size(); k++) {
			em.persist(recipients.get(k));
		}
		em.getTransaction().commit();
		
		//Verify if recipients was successfully added to local Database;
		List<Recipient> recipientsPersistence = em.createQuery("SELECT r FROM Recipient r").getResultList();
		System.out.println("The list of recipients that are added to the database is: ");
		for(Recipient r: recipientsPersistence) {
			System.out.println("Recipient with id: " + r.getRecipientID() + " name " + r.getName());
		}
		List<Book> books = new ArrayList<Book>();
		Date date1 = new Date(2020, 1, 10);
		Date date2 = new Date(2020, 1, 15);
		Date date3 = new Date(2020, 1, 20);
		Date date4 = new Date(2019, 1, 25);
		Date date5 = new Date(2019, 1, 30);
		Date date6 = new Date(2019, 2, 5);
		Date date7 = new Date(2019, 2, 10);
		Date date8 = new Date(2019, 2, 15);
		
		books.add(new Book(100, date1));
		books.add(new Book(101, date2));
		books.add(new Book(102, date3));
		books.add(new Book(103, date4));
		books.add(new Book(104, date5));
		books.add(new Book(105, date6));
		books.add(new Book(106, date7));
		books.add(new Book(107, date8));
		
		//Populate Book local Database
//		em.getTransaction().begin();
//		for(int i=0; i<books.size(); i++) {
//			em.persist(books.get(i));
//		}
//		em.getTransaction().commit();
		
		//Verify if books was added succesfully to local Database
		List<Book> booksPersistence = em.createQuery("SELECT b FROM Book b").getResultList();
		System.out.println("The list of books that are added to the database is: ");
		for(Book b: booksPersistence) {
			System.out.println("The id of book: " + b.getBookID() + " date " + b.getDate());
		}
		

		Date dateDelivery1 = new Date(2020, 1, 5);
		Date dateDelivery2 = new Date(2020, 1, 6);
		Date dateDelivery3 = new Date(2020, 1, 7);
		Date dateDelivery4 = new Date(2020, 2, 5);
		Date dateDelivery5 = new Date(2020, 2, 6);
		Date dateDelivery6 = new Date(2020, 2, 7);
		Date dateDelivery7 = new Date(2020, 2, 7);
		Date dateDelivery8 = new Date(2020, 3, 5);
		
		
		List<Sender> senders = new ArrayList<Sender>();
		senders.add(new Sender(100, "Ion Marin", "Iasi", TypeOfCustomer.F));
		senders.add(new Sender(101, "Georgeta Andreia", "Botosani", TypeOfCustomer.J));
		senders.add(new Sender(102, "Ionela Georgeta", "Bacau", TypeOfCustomer.F));
		senders.add(new Sender(103, "Marta Chitu", "Onesti", TypeOfCustomer.F));
		senders.add(new Sender(104, "Oana Enescu", "Suceava", TypeOfCustomer.J));
		senders.add(new Sender(105, "Cristian Petrescu", "Botosani", TypeOfCustomer.F));
		senders.add(new Sender(106, "Serban Savu", "Bacau", TypeOfCustomer.F));
		senders.add(new Sender(107, "Sorin Petran", "Iasi", TypeOfCustomer.F));
		
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits.add(new Deposit(100, "Iasi", 250.0, books.get(0)));
		deposits.add(new Deposit(101, "Botosani", 150.0, books.get(1)));
		deposits.add(new Deposit(102, "Bacau", 200.0, books.get(2)));
		deposits.add(new Deposit(103, "Suceava", 150.0, books.get(3)));
		deposits.add(new Deposit(104, "Piatra Neamt", 150.0, books.get(4)));
		deposits.add(new Deposit(105, "Buhusi", 150.0, books.get(5)));
		
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location(605200, "Comanesti", "Bacau", deposits));
		locations.add(new Location(200300, "Iasi", "Iasi", deposits));
		locations.add(new Location(300200, "Botosani", "Botosani", deposits));
		locations.add(new Location(650478, "Suceava", "Suceava", deposits));
		locations.add(new Location(300530, "Moinesti", "Bacau", deposits));
		locations.add(new Location(200100, "Onesti", "Bacau", deposits));

		
		deposits.get(0).addToListOfLocationsServed(locations.get(1));
		deposits.get(1).addToListOfLocationsServed(locations.get(2));
		deposits.get(2).addToListOfLocationsServed(locations.get(0));
		deposits.get(2).addToListOfLocationsServed(locations.get(5));
		deposits.get(2).addToListOfLocationsServed(locations.get(4));
		deposits.get(3).addToListOfLocationsServed(locations.get(3));
		
		List<Package> packages = new ArrayList<Package>();
		packages.add(new Package(100, TypeOfPackage.Small, 1.50, senders.get(0), recipients.get(0), deposits));
		packages.add(new Package(101, TypeOfPackage.Medium, 5.00, senders.get(1), recipients.get(1), deposits));
		packages.add(new Package(102, TypeOfPackage.Large, 10.00, senders.get(2), recipients.get(2), deposits));
		packages.add(new Package(103, TypeOfPackage.Small, 1.00, senders.get(3), recipients.get(3), deposits));
		packages.add(new Package(104, TypeOfPackage.Medium, 6.00, senders.get(4), recipients.get(4), deposits));
		packages.add(new Package(105, TypeOfPackage.Large, 12.00, senders.get(5), recipients.get(5), deposits));
		packages.add(new Package(106, TypeOfPackage.Small, 0.50, senders.get(6), recipients.get(6), deposits));
		packages.add(new Package(107, TypeOfPackage.Medium, 7.00, senders.get(7), recipients.get(7), deposits));
		
		List<Courier> couriersAsEmployee = new ArrayList<Courier>();
		couriersAsEmployee.add(new Courier(100, "Will Smith", Departaments.Delivery, 100, "Iasi", deposits));
		couriersAsEmployee.add(new Courier(101, "George Andrei", Departaments.Delivery, 101, "Botosani", deposits));
		couriersAsEmployee.add(new Courier(102, "Vasile Ion", Departaments.Delivery, 102, "Bacau", deposits));
		couriersAsEmployee.add(new Courier(103, "Shaithis Cosmescu", Departaments.Delivery, 103, "Suceava", deposits));
		couriersAsEmployee.add(new Courier(104, "Carol Stolojan", Departaments.Delivery, 104, "Bacau", deposits));
		couriersAsEmployee.add(new Courier(105, "Doru Poénaru", Departaments.Delivery, 105, "Iasi", deposits));
		couriersAsEmployee.add(new Courier(106, "Marku Randa", Departaments.Delivery, 106, "Iasi", deposits));
		couriersAsEmployee.add(new Courier(107, "Nicolae Florescu", Departaments.Delivery, 107, "Botosani", deposits));
		couriersAsEmployee.add(new Courier(108, "Petar Filotti", Departaments.Delivery, 108, "Suceava", deposits));
		couriersAsEmployee.add(new Courier(108, "Andrei Filotti", Departaments.Delivery, 109, "Piatra Neamt", deposits));
		couriersAsEmployee.add(new Courier(108, "George Onofrei", Departaments.Delivery, 110, "Buhusi", deposits));
		
		HashMap<Courier,Boolean> couriersForDeliveries = new HashMap<Courier, Boolean>();
		couriersForDeliveries.put(couriersAsEmployee.get(0), true);
		couriersForDeliveries.put(couriersAsEmployee.get(1), true);
		couriersForDeliveries.put(couriersAsEmployee.get(2), true);
		couriersForDeliveries.put(couriersAsEmployee.get(3), true);
		couriersForDeliveries.put(couriersAsEmployee.get(4), true);
		couriersForDeliveries.put(couriersAsEmployee.get(5), true);
		couriersForDeliveries.put(couriersAsEmployee.get(6), true);
		couriersForDeliveries.put(couriersAsEmployee.get(7), true);
		couriersForDeliveries.put(couriersAsEmployee.get(8), true);
		couriersForDeliveries.put(couriersAsEmployee.get(9), true);
		couriersForDeliveries.put(couriersAsEmployee.get(10), true);
		
		List<Delivery> deliveries = new ArrayList<Delivery>();
		deliveries.add(new Delivery(100, dateDelivery1, packages.get(0), couriersForDeliveries, books.get(0)));
		deliveries.add(new Delivery(101, dateDelivery2, packages.get(1), couriersForDeliveries, books.get(0)));
		deliveries.add(new Delivery(103, dateDelivery3, packages.get(2), couriersForDeliveries, books.get(1)));
		deliveries.add(new Delivery(104, dateDelivery4, packages.get(3), couriersForDeliveries, books.get(2)));
		deliveries.add(new Delivery(105, dateDelivery5, packages.get(4), couriersForDeliveries, books.get(3)));;
		
		
		em.getTransaction().begin();
		for(int j = 0 ; j < deliveries.size(); j++ ) {
			em.persist(deliveries.get(j));
		}
		em.getTransaction().commit();
		
		//Show just the deliveries that has the value greater than or equal to 10 RON
		System.out.println(" --------------------------- ");
		List<Delivery> deliveriesPersistence = em.createQuery("SELECT d FROM Delivery d").getResultList();
		deliveriesPersistence.stream()
							 .filter(d -> d.getValue().doubleValue() >= 10)
							 .map(d -> "Delivery AWB: " +  d.getDeliveryAWB() + ",value: " + d.getValue())
							 .forEach(d-> System.out.println(d));
		System.out.println(" --------------------------- ");
		//Cautare doar pe zona de Delivery
//		em.getTransaction().begin();
//		Delivery dev1 = em.find(Delivery.class, 101);
//		if(dev1!=null) {
//			dev1.setDateOfDelivery(dateDelivery7);
//			em.persist(dev1);
//		}
		
		//Sorting deliveries list using awb
		List<Delivery> deliveriesList = em.createQuery("SELECT d FROM Delivery d").getResultList();
		List<Delivery > sortedDeliveryList = deliveriesList.stream().sorted((d1, d2) -> d1.getDeliveryAWB().compareTo(d2.getDeliveryAWB()))
		.collect(Collectors.toList());
		for(Delivery d: sortedDeliveryList) {
			System.out.println(d);
		}
		System.out.println(" --------------------------- ");
		//Counting the number of couriers from Bacau county
		List<Courier> couriersDeliveries = em.createQuery("SELECT c FROM Courier c ORDER BY c.courierID").getResultList();
		long noCouriers = couriersDeliveries.stream().filter(c -> c.getLocation().equals("Bacau")).count();
		System.out.println("The number of couriers that live in Bacau county, is: " + noCouriers);
		System.out.println(" --------------------------- ");
		//Find the recipient with id 100 and change his name to "George Amarinei"
		em.getTransaction().begin();
		Recipient recipient = em.find(Recipient.class, 100);
		if(recipient!=null) {
			recipient.setName("George Amarinei");
			em.persist(recipient);
		}
		em.getTransaction().commit();
		
		
		
	}

}
