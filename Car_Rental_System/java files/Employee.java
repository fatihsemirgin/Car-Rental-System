public class Employee {
	private int employee_id;
	private String name;
	private String surname;
	private String gender;
	private String birthdate;
	private int off_id;
	private boolean emp_available;
	private int rent_count_emp;

	public Employee(String name, String surname, String gender, String birthdate, int off_id, int employee_id) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.employee_id = employee_id;
		this.off_id = off_id;
		emp_available = true;
		rent_count_emp = 0;
	}

	public String EmployeeAvailable() {
		if (emp_available)
			return "available";
		else
			return "not available";
	}

	public int getRent_count_emp() {
		return rent_count_emp;
	}

	public void setRent_count_emp(int rent_count_emp) {
		this.rent_count_emp = rent_count_emp;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public void setOff_id(int off_id) {
		this.off_id = off_id;
	}

	public int getOff_id() {
		return off_id;
	}

	public String getSurname() {
		return surname;
	}

	public String getGender() {
		return gender;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public String getName() {
		return name;
	}

	public boolean isEmp_available() {
		return emp_available;
	}

	public void setEmp_available(boolean emp_available) {
		this.emp_available = emp_available;
	}

}
