
public class Car {
	private int car_id;
	private int office_id;
	private String brand;
	private String model;
	private String clas; // economy,model,class
	private int km;
	private boolean car_availabe;
	private int car_maintenance;
	private int car_maintenance2;
	private int rent_count_car;
	private int profit;

	public Car(String brand, String model, String clas, int km, int office_id, int car_id) {
		this.brand = brand;
		this.model = model;
		this.clas = clas;
		this.km = km;
		this.car_id = car_id;
		this.office_id = office_id;
		car_availabe = true;
		car_maintenance = 0;
		car_maintenance2 = 0;
		rent_count_car = 0;
		profit = 0;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public int getCar_maintenance2() {
		return car_maintenance2;
	}

	public void setCar_maintenance2(int car_maintenance2) {
		this.car_maintenance2 = car_maintenance2;
	}

	public int getRent_count() {
		return rent_count_car;
	}

	public void setRent_count(int rent_count) {
		this.rent_count_car = rent_count;
	}

	public int getCar_maintenance() {
		return car_maintenance;
	}

	public void setCar_maintenance(int car_maintenance) {
		this.car_maintenance = car_maintenance;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public int getOffice_id() {
		return office_id;
	}

	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}

	public String getClas() {
		return clas;
	}

	public int getKm() {
		return km;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public boolean getCarAvailabe() {
		return car_availabe;
	}

	public void setCarAvailabe(boolean car_availabe) {
		this.car_availabe = car_availabe;
	}

	public String CarAvailable() {
		if (car_availabe)
			return "available";
		else
			return "not available";
	}
}
