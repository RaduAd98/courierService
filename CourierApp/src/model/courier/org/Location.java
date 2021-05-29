package model.courier.org;

public class Location {
	private Integer postalCode;
	private String cityName;
	private String countyName;
	
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
