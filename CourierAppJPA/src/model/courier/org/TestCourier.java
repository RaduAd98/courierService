package model.courier.org;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TestCourier {

	public static void main(String[] args) {
		Recipient recipient1 = new Recipient(100, "Ion Vasile", "Iasi", TypeOfCustomer.F);
		Sender sender1 = new Sender(100, "Georgeta Maria", "Botosani", TypeOfCustomer.F);
		Recipient recipient2 = new Recipient(101, "Mironica Ionela", "Botosani", TypeOfCustomer.F);
		Sender sender2 = new Sender(101, "Ionel Palade", "Bacau", TypeOfCustomer.F);
		Package package1 = new Package(100, TypeOfPackage.Small, 2.00, sender1, recipient1);
		Package package2 = new Package(101, TypeOfPackage.Medium, 5.00, sender2, recipient2);
		@SuppressWarnings("deprecation")
		Date date1 = new Date(2019, 8, 15);
		@SuppressWarnings("deprecation")
		Date date2 = new Date(2019, 9, 25);
		Delivery delivery1 = new Delivery(100, date1, package1);
		Courier courier1 = new Courier(100, "Will Smith", "Iasi");
		Courier courier2 = new Courier(101, "Vasile Ion","Botosani");
		Courier courier3 = new Courier(102, "Ionica Ion", "Bacau");
		HashMap<Courier, Boolean> couriers = new HashMap<Courier, Boolean>();
		couriers.put(courier1, true);
		couriers.put(courier2, false);
		couriers.put(courier3, true);
		delivery1.setDeliveryForCourier(couriers,delivery1);

		Date dateOfBookNo1 = new Date(2019, 10, 25);
		Book bookno1 = new Book(100, dateOfBookNo1);
		
		Deposit deposit1 = new Deposit(100, "Iasi", 250.0);
		bookno1.addToTheListOfDeposits(deposit1);
		System.out.println("-------------------------------------------------------------");
		bookno1.checkDepositsInTheList();
		System.out.println("-------------------------------------------------------------");
		bookno1.addDeliveryToTheListOfDeliveries(delivery1);
		bookno1.checkDeliveriesInTheList();
	
		delivery1.valueOfDelivery();
		
		Delivery delivery2 = new Delivery(101, date2, package2);
		System.out.println("-------------------------------------------------------------");
		Collection <Delivery> deliveries = new ArrayList<Delivery>();
		deliveries.add(delivery1);
		deliveries.add(delivery2);
		
		Double totalValueOfDeliveries = bookno1.checkTotalValueOfDeliveries(deliveries);
		System.out.println("The total value of the deliveries is: " + totalValueOfDeliveries + " RON");
		
		System.out.println("-------------------------------------------------------------");
		LinkedList<Delivery> deliveriesLinked = new LinkedList<Delivery>();
		deliveriesLinked.add(delivery1);
		deliveriesLinked.add(delivery2);
		bookno1.checkIndividualValueOfDeliveries(deliveriesLinked);
	
		Location location1 = new Location(605200, "Comanesti", "Bacau");
		Location location2 = new Location(300200, "Iasi", "Iasi");
		Location location3 = new Location(200100, "Bacau", "Bacau");
		
		deposit1.addToListOfLocationsServed(location1);
		deposit1.addToListOfLocationsServed(location2);
		deposit1.addToListOfLocationsServed(location3);
		System.out.println("-------------------------------------------------------------");
		deposit1.checkListOfLocationsServedByDeposit();
		
		List<Deposit> deposits = new ArrayList<Deposit>();
		deposits.add(deposit1);
		delivery1.attachPackageToDeposit(deposits);
		deposit1.checkPackagesFromDeposit();
		
		Employee employee1 = new Employee(100, "Will Smith", Departaments.Delivery);
		Courier courierAsEmployee1 = new Courier(100, "Will Smith", Departaments.Delivery, 100, "Iasi");
		courierAsEmployee1.calculateSalary(courier1.getDepartamentName(), 200);
//		courier1.calculateSalary(Departaments.Delivery, 200);
		System.out.println("-------------------------------------------------------------");
		courier3.allocateThisCourierToDeposit(deposits);
		courier1.allocateThisCourierToDeposit(deposits);
		courier2.allocateThisCourierToDeposit(deposits);
		
		bookno1.addDeliveryToTheListOfDeliveries(delivery2);
		
		bookno1.showDeliveriesSortedByAWB().stream().forEach(d -> System.out.println(d));;
		
		
	}

}
