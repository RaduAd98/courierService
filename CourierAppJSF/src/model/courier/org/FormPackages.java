package model.courier.org;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean @SessionScoped
public class FormPackages {
	private List<Package> packs = new ArrayList<Package>();
	private Package pack;
	
	public List<Package> getPackage() {
		return packs;
	}
	
	public List<Package> getPackages(){
		return packs;
	}
	
	public Package getPack() {
		return pack;
	}
	
	public void setPack(Package pack) {
		this.pack = pack;
	}
	
	public Integer getPackageId() {
		return this.pack.getPackageID();
	}
	
	public void setPackageId(Integer packageID) {
		Integer idx = this.packs.indexOf(new Package(packageID, null, null, null, null));
		this.pack = this.packs.get(idx);
	}
	
	private EntityManager em;
	public FormPackages() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourierAppJPA");
		em = emf.createEntityManager();
		initialise();
	}
	
	private void initialise() {
		this.packs = em.createQuery("SELECT p FROM Package p ORDER BY p.packageID", Package.class).getResultList();
		if(!packs.isEmpty()) {
			this.setPack(packs.get(0));
		}
	}
	
	public void previousPackage(ActionEvent event) {
		System.out.println("<<< PREVIOUS PACKAGE");
		Integer idxCurent = this.packs.indexOf(pack);
		if(idxCurent > 0)
			this.pack = this.packs.get(idxCurent - 1);
	}
	
	public void nextPackage(ActionEvent event) {
		System.out.println(">>> NEXT PACKAGE");
		Integer idxCurent = this.packs.indexOf(pack);
		if((idxCurent + 1) < this.packs.size())
			this.pack = this.packs.get(idxCurent + 1);
	}
	
	public void addPackage(ActionEvent event) {
		this.pack = new Package();
		Integer packID = packs.get(packs.size()-1).getPackageID();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String packageRecipient = ec.getRequestParameterMap().get("formPackageForm:package_recipient");
		String packageSender = ec.getRequestParameterMap().get("formPackageForm:package_sender");
		Double packageDouble = (Double) ec.getRequestMap().get("formPackageForm:package_weight");
		Recipient recipient = new Recipient();
		List<Integer> recId = new ArrayList<Integer>();
		for(Package p: packs) {
			recId.add(p.getRecipient().getRecipientID());
		}
		List<Integer> sendId = new ArrayList<Integer>();
		for(Package p: packs) {
			sendId.add(p.getSender().getSenderID());
		}
		recipient.setRecipientID(recId.get(recId.size()-1)+1);
		recipient.setName(packageRecipient);
		recipient.setLocation("Iasi");
		recipient.setType(TypeOfCustomer.F);
		Sender sender = new Sender();
		Integer senderID = sender.getSenderID();
		sender.setSenderID(sendId.get(sendId.size()-1)+1);
		sender.setName(packageSender);
		sender.setLocation("Botosani");
		sender.setType(TypeOfCustomer.F);
		this.pack.setPackageID(packID+1);
		this.pack.setRecipient(recipient);
		this.pack.setSender(sender);
		this.pack.setWeight(packageDouble);
		this.packs.add(this.pack);
	}
	
	public void removePackage(ActionEvent event) {
		this.packs.remove(this.pack);
		if(this.em.contains(this.pack)) {
			this.em.getTransaction().begin();
			this.em.remove(this.pack);
			this.em.getTransaction().commit();
		}
		
		if(!this.packs.isEmpty())
			this.pack = this.packs.get(0);
		else
			this.pack = null;
	}
	
	public void savePackage(ActionEvent event) {
		System.out.println("Save");
		try {
			this.em.getTransaction().begin();
			this.em.merge(this.pack);
			this.em.getTransaction().commit();
		} catch(Exception exc) {
			exc.getSuppressed();
		}
	}
	
	public void leaveTransactionPackage(ActionEvent event) {
		System.out.println("Leaving the Transaction!");
		em.clear();
		initialise();
	}

}
