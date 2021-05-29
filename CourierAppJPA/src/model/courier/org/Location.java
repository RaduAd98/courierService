package model.courier.org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;

import java.util.List;

import javax.persistence.ManyToOne;
import static javax.persistence.CascadeType.ALL;

@Entity
public class Location {
	@Id
	private Integer postalCode;
	private String cityName;
	private String countyName;
	@ManyToOne(cascade = ALL)
	private Deposit deposit;
	
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Integer postalCode, String cityName, String countyName) {
		super();
		this.postalCode = postalCode;
		this.cityName = cityName;
		this.countyName = countyName;
	}
	
	public Location(Integer postalCode, String cityName, String countyName, List<Deposit> deposits) {
		super();
		this.postalCode = postalCode;
		this.cityName = cityName;
		this.countyName = countyName;
		this.deposit = getDepositForLocation(deposits);
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Deposit getDepositForLocation(List<Deposit> deposits) {
		Deposit deposit = new Deposit();
		if(deposits.isEmpty()) {
			System.out.println("The list of deposits provided is empty, so you can't allocate one deposit to this location!");
		} else {
			for(Deposit d: deposits) {
				if(d.getLocation() == this.countyName || d.getLocations_served().contains(this.cityName)) {
					deposit = d;
				}
			}
		}
		return deposit;
	}
	
	@Override
	public String toString() {
		return "Location [postalCode=" + postalCode + ", cityName=" + cityName + ", countyName=" + countyName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
		Location other = (Location) obj;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		return true;
	}
	
	
	
}
