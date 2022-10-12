public class Contract {
	private int office_id;
	private int contract_id;
	private int employee_id;
	private int customer_id;
	private int car_id;
	private Date start_date;
	private Date end_date;
	private boolean available;

	public Contract(int employee_id, int customer_id, int car_id, Date start_date, Date end_date, int contract_id,
			int office_id, boolean available) {
		this.employee_id = employee_id;
		this.customer_id = customer_id;
		this.car_id = car_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.contract_id = contract_id;
		this.office_id = office_id;
		this.available = available;
	}

	public void display() {
		System.out.println(
				this.contract_id + ".Contract: " + "Employee" + this.employee_id + ";Customer" + this.customer_id
						+ ";Car" + this.car_id + ";" + this.start_date.display() + ";" + this.end_date.display());
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getOffice_id() {
		return office_id;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}

	public int getContract_id() {
		return contract_id;
	}

	public void setContract_id(int contract_id) {
		this.contract_id = contract_id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
}
