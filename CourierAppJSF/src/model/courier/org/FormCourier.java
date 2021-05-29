package model.courier.org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

@ManagedBean @SessionScoped
public class FormCourier {
	private List<Courier> couriers = new ArrayList<Courier>();
	private Courier courier;
	
	private TestCourierJPA jpa;
	
	public List<Courier> getCouriers(){
		return couriers;
	}
	
	public Courier getCourier() {
		return this.courier;
	}
	
	
	public Integer getCourierId() {
		return this.courier.getCourierID();
	}
	
	public void setCourierId(Integer courierID) {
		
		Integer idx = this.couriers.indexOf(new Courier(null, null, null, courierID, null));
		this.courier = this.couriers.get(idx);
	}
	
	private List<Package> packets = new ArrayList<Package>();
	private DataModel<Delivery> deliveriesDataModel;
	
	public DataModel<Delivery> getDeliveryDataModel(){
		deliveriesDataModel = new ListDataModel<Delivery>(this.courier.getDeliveriesToBeServed());
		return deliveriesDataModel;
	}
	
	public List<Package>getPackages(){
		return packets;
	}
	
	public Integer getPackageId() {
		return this.deliveriesDataModel.getRowData().getPack().getPackageID();
	}
	
	public void setPackageId(Integer packageId) {
		Integer idx = this.packets.indexOf(new Package(packageId, null, null, null, null));
		Package pack = this.packets.get(idx);
		this.deliveriesDataModel.getRowData().setPack(pack);
	}
	
	private EntityManager em;
	public FormCourier(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourierAppJPA");
		em = emf.createEntityManager();
		initialiseCouriersModel();
		initialisePackagesModel();
	}
	
	private void initialiseCouriersModel() {
		this.couriers = em.createQuery("SELECT c FROM Courier c", Courier.class).getResultList();
		if(this.couriers!=null && !this.couriers.isEmpty()) {
			Collections.sort(this.couriers, (c1,c2)->c1.getCourierID().compareTo(c2.getCourierID()));
			if(!this.couriers.contains(this.courier))
				this.courier = this.couriers.get(0);
		}
		if(this.courier == null)
			addCourier(null);
	}
	
	private void initialisePackagesModel(){
		this.packets = em.createQuery("SELECT p FROM Package p", Package.class).getResultList();
		if(this.packets!=null && !this.packets.isEmpty())
			Collections.sort(this.packets, (p1, p2)->p1.getPackageID().compareTo(p2.getPackageID()));
	}
	
	public void previousCourier(ActionEvent event) {
		System.out.println("Previous Action ");
		Integer idxCurrent = this.couriers.indexOf(this.courier);
		if(idxCurrent - 1 >= 0)
			this.courier = this.couriers.get(idxCurrent - 1);
	}
	
	public void nextCourier(ActionEvent event) {
		System.out.println("Next Action ");
		Integer idxCurrent = this.couriers.indexOf(this.courier);
		if(idxCurrent + 1 < this.couriers.size())
			this.courier = this.couriers.get(idxCurrent + 1);
	}
	
	public void addCourier(ActionEvent event) {
		Integer courierId = couriers.get(couriers.size() - 1).getCourierID();;
		Courier courierN = new Courier(courierId, "George Vasile", "Iasi");
		this.couriers.add(courierN);
		this.courier = courierN;
	}
	
	public void removeCourier(ActionEvent event) {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
	
		if(this.em.contains(this.courier)) {
			this.em.remove(this.courier);
			this.couriers.remove(this.courier);
		}
		
		if(this.couriers.size() > 0)
			this.courier = this.couriers.get(0);
		else
			this.courier = null;
		
		em.getTransaction().commit();
	}
	
	public void saveCourier(ActionEvent event) {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		try {
			em.merge(this.courier);
			em.flush();
			em.refresh(courier);
			em.getTransaction().commit();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			this.em.getTransaction().rollback();
		}
	}
	
	public void leave(ActionEvent event) {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		this.em.getTransaction().rollback();
		if(this.em.contains(this.courier))
			this.em.refresh(this.courier);
		else
			initialiseCouriersModel();
	}
	
	public void removePackage(ActionEvent event) {
		Delivery deliveryModel = this.deliveriesDataModel.getRowData();
		this.courier.getDeliveriesToBeServed().remove(deliveryModel);
	}
	
	public void addPackage(ActionEvent event) {
		Delivery newDelivery = new Delivery(null, new Date(), this.packets.get(0));
		this.courier.getDeliveriesToBeServed().add(newDelivery);
		newDelivery.setCourier(this.courier);
	}
}
