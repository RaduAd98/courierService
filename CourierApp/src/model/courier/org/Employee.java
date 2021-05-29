package model.courier.org;

public class Employee {
	private Integer employeeID;
	private String name;
	private Departaments departamentName; 
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer employeeID, String name, Departaments departamentName) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.departamentName = departamentName;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Departaments getDepartamentName() {
		return departamentName;
	}

	public void setDepartamentName(Departaments departamentName) {
		this.departamentName = departamentName;
	}

	public void calculateSalary(Departaments departament, Integer numberOfHoursWorked){
		Double salary = 0.00;
		Integer payPerHour = 0;
		if(departament == Departaments.Assurance_Deposits) {
			payPerHour = 15;
			salary = numberOfHoursWorked * payPerHour.doubleValue();
		} else if(departament == Departaments.Delivery) {
			payPerHour = 14;
			salary = numberOfHoursWorked * payPerHour.doubleValue();
		} else if(departament == Departaments.Financial) {
			payPerHour = 20;
			salary = numberOfHoursWorked * payPerHour.doubleValue();
		} else if(departament == Departaments.Other) {
			payPerHour = 25;
			salary = numberOfHoursWorked * payPerHour.doubleValue();
		} else {
			System.out.println("Cannot make operations on that!");
		}
		System.out.println("The Employee with id " + this.employeeID + " that has the name " + this.name + " is in departament " + this.getDepartamentName() + " and has the salary of " + salary + " RON.");
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
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
		Employee other = (Employee) obj;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		return true;
	}
	
	
}
