public class CarRequest {

	private int office_id;
	private int cutomer_id;
	private String brand;
	private String model;
	private String clas;
	private Date start_date;
	private Date end_date;
	private int car_request_id;

	// her þeyi belli araç isteði
	public CarRequest(int office_id, int cutomer_id, String brand, String model, String clas, Date start_date,
			Date end_date, int car_request_id) {
		this.office_id = office_id;
		this.cutomer_id = cutomer_id;
		this.brand = brand;
		this.model = model;
		this.clas = clas;
		this.start_date = start_date;
		this.end_date = end_date;
		this.car_request_id = car_request_id;
	}

	//
	public CarRequest(int office_id, String clas, int car_request_id) {
		this.office_id = office_id;
		this.clas = clas;
		this.car_request_id = car_request_id;
	}

	public int getCar_request_id() {
		return car_request_id;
	}

	public void setCar_request_id(int car_request_id) {
		this.car_request_id = car_request_id;
	}

	public int getOffice_id() {
		return office_id;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}

	public int getCutomer_id() {
		return cutomer_id;
	}

	public void setCutomer_id(int cutomer_id) {
		this.cutomer_id = cutomer_id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public boolean isAvailable(int car, int empl, boolean date) {

		if (car == -1)
			System.out.println("Error:Car not available");

		if (empl == -1) {
			System.out.println("Error:No employee for the contract");
		}
		if (date == false)
			System.out.println("Error: Date is wrong");
		if (car != -1 && empl != -1 && date == true)
			return true;

		return false;
	}

}
