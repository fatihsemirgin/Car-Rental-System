import java.util.Random;

public class Office {

	private int office_id;
	private String phone;
	private String address;
	private String city;
	private Employee[] employees;
	private Car[] cars;
	private int income_sum = 0;
	private int expenses_office = 0;
	private int count_employee;
	private int count_e; 	// office'teki çalýþan sayýsý
	private int count_c;	// office'teki araç sayýsý
	private static int compensation = 0;
	private int income_car = 0;
	private int office_rent = 100;
	private int employee_salary = 0;
	private int employee_bonus = 0;
	private int car_maintenance = 0;
	private int car_maintenance2 = 0;
	private String clas;

	public Office(String phone, String address, String city, int office_id, int capacity_car) {

		this.phone = phone;
		this.address = address;
		this.city = city;
		this.office_id = office_id;
		employees = new Employee[3];
		cars = new Car[capacity_car];
		count_c = 0;
		count_e = 0;
		count_employee = 0;

	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public int getCompensation() {
		return compensation;
	}

	public int getIncome_sum() {
		return income_sum;
	}

	public void setIncome_sum(int income_sum) {
		this.income_sum = income_sum;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public int getOffice_id() {
		return office_id;
	}

	public int getCount_e() {
		return count_employee;
	}

	public void setCount_e(int count_employee) {
		this.count_employee = count_employee;
	}

	public void setPhone(String num) {
		phone = num;
	}

	public void setAddress(String address_) {
		address = address_;
	}

	public void setCity(String city_) {
		city = city_;
	}

	public void setOffice_id(int id) {
		office_id = id;
	}

	public int getExpenses_office() {
		return expenses_office;
	}

	public void setExpenses_office(int expenses_office) {
		this.expenses_office = expenses_office;
	}

	public void addEmployee(String name, String surname, String gender, String birthdate, int off_id,
			int counter_employees2) {
		count_e++;
		System.out.println(count_e);
		count_employee = counter_employees2;
		employees[count_employee - 1] = new Employee(name, surname, gender, birthdate, off_id, counter_employees2);
	}

	public void listEmployees(int id_office) {
		for (int i = 0; i < count_employee; i++) {
			if (employees[i] != null && employees[i].getName() != ("deleted")) {
				if (employees[i].getOff_id() == id_office)
					System.out.println("  " + employees[i].getEmployee_id() + ".Employee " + employees[i].getName()
							+ " " + employees[i].getSurname() + " " + employees[i].getGender() + " "
							+ employees[i].getBirthdate() + " " + employees[i].getOff_id() + " "
							+ employees[i].EmployeeAvailable() + " " + employees[i].getRent_count_emp());
			}
		}
	}

	public void deleteEmployee(int id_office, int id_employee) {
		for (int i = 0; i < count_employee; i++) {
			if (employees[i] != null) {
				if (employees[i].getEmployee_id() == id_employee && employees[i].getOff_id() == id_office) {
					System.out.println("  " + employees[i].getName() + " " + employees[i].getSurname() + " DELETED");
					employees[i].setName("deleted");
					compensation++;
					count_e--;
					System.out.println(count_e);
				}
			}
		}
	}

	public void addCar(String brand, String model, String clas, int km, int office_id, int count_cars2) {

		count_c = count_cars2;

		cars[count_cars2 - 1] = new Car(brand, model, clas, km, office_id, count_cars2);
	}

	public void listCar(int id_office, int count_cars2) {
		for (int i = 0; i < count_cars2; i++) {
			if (cars[i] != null) {
				if (cars[i].getOffice_id() == id_office) {
					System.out.println("  " + cars[i].getCar_id() + ".Car " + cars[i].getBrand() + " "
							+ cars[i].getModel() + " " + cars[i].getClas() + " " + cars[i].getKm() + " "
							+ cars[i].getOffice_id() + " -" + cars[i].CarAvailable() + " " + cars[i].getRent_count());
				}
			}

		}
	}

	public void statistic(Contract[] c2, int count_c2, Customer[] c3, int count_c3, int office_id) {
		int max = 0;
		int max2 = 0;
		int profit_car;
		Car car = null;
		clas = null;
		for (int i = 0; i < count_c; i++) {
			if (cars[i] != null) {
				if (cars[i].getRent_count() > max) {
					max = cars[i].getRent_count();
				}
				profit_car = (cars[i].getRent_count() * cars[i].getProfit());
				if (profit_car > max2) {
					max2 = cars[i].getRent_count();
					car = cars[i];
					clas = cars[i].getClas();
				}
			}
		}
		System.out.print("The most rented car: ");
		for (int j = 0; j < count_c; j++) {
			if (cars[j] != null) {
				if (max == cars[j].getRent_count()) {
					System.out.print(
							"Car" + cars[j].getCar_id() + ";" + cars[j].getBrand() + ";" + cars[j].getModel() + "   ");
				}
			}
		}
		System.out.println();
		System.out.print("The most rented car class: ");
		for (int j = 0; j < count_c; j++) {
			if (cars[j] != null) {
				if (max == cars[j].getRent_count()) {
					System.out.print(cars[j].getClas() + "   ");
				}
			}
		}
		System.out.println();
		if (car != null) {
			System.out.println("The car with the highest profit: Car" + car.getCar_id() + ";" + car.getBrand() + ";"
					+ car.getModel());
		}

		System.out.println("The car class with the highest profit: " + clas);

		int date_fark;
		double sum_date = 0;
		int count_contract = 0;
		for (int i = 0; i < count_c2; i++) {
			if (c2[i].getOffice_id() == office_id) {
				date_fark = c2[i].getEnd_date().getDay() - c2[i].getStart_date().getDay() + 1;
				sum_date += date_fark;
				count_contract++;
			}
		}
		sum_date = (sum_date / count_contract);
		System.out.println("The average number of days the cars are rented: " + sum_date + " days.");

		System.out.print("The customer who rented most: ");
		int max_customer = 0;

		for (int k = 0; k < count_c2; k++) {
			if (c2[k].getOffice_id() == office_id) {
				for (int m = 0; m < count_c3; m++) {
					if (c2[k].getCustomer_id() == c3[m].getCustomer_id()) {
						if (c3[m].getCount() > max_customer) {
							max_customer = c3[m].getCount();
						}
					}
				}
			}
		}
		for (int l = 0; l < count_c2; l++) {
			if (c2[l].getOffice_id() == office_id) {
				for (int m = 0; m < count_c3; m++) {
					if (c2[l].getCustomer_id() == c3[m].getCustomer_id()) {
						if (c3[m].getCount() == max_customer) {
							System.out.print("Customer" + c3[m].getCustomer_id() + ";" + c3[m].getName() + ";"
									+ c3[m].getSurname() + "   ");
						}
					}
				}
			}
		}
		System.out.println();

		int max_rent_employee = 0;
		for (int a = 0; a < count_employee; a++) {
			if (employees[a] != null) {
				if (employees[a].getRent_count_emp() > max_rent_employee) {
					max_rent_employee = employees[a].getRent_count_emp();
				}
			}
		}
		System.out.print("The employee who rented most: ");
		for (int a = 0; a < count_employee; a++) {
			if (employees[a] != null) {
				if (employees[a].getRent_count_emp() == max_rent_employee) {
					System.out.print("Employee" + employees[a].getEmployee_id() + ";" + employees[a].getName() + ";"
							+ employees[a].getSurname() + "  ");
				}
			}
		}

		int profit_car2 = 0;
		int profit_emp = 0;
		int contract_length;
		int x = 0;
		int bonus = 0;
		int temp = 0;
		int maxxx = 0;
		double summ = 0;
		for (int j = 0; j < count_c2; j++) {

			for (int a = 0; a < count_c; a++) {
				if (cars[a] != null) {
					if (c2[j].getCar_id() == cars[a].getCar_id()) {
						profit_car2 = cars[a].getProfit();
						x = j;
						break;
					}
				}

			}
			contract_length = c2[x].getEnd_date().getDay() - c2[x].getStart_date().getDay() + 1;
			profit_emp = contract_length * (profit_car2 - 30); //
			for (int k = 0; k < count_employee; k++) {
				if (employees[k] != null) {
					if (c2[j].getEmployee_id() == employees[k].getEmployee_id()) {
						temp = k;
						for (int m = 0; m < count_c; m++) {
							if (cars[m] != null) {
								if (c2[j].getCar_id() == cars[m].getCar_id()) {
									if (cars[m].getClas().equalsIgnoreCase("Luxury")) {
										bonus = 15;
									} else if (cars[m].getClas().equalsIgnoreCase("Sports")) {
										bonus = 10;
									} else if (cars[m].getClas().equalsIgnoreCase("Economy")) {
										bonus = 5;
									}
								}
							}

						}
						profit_emp -= bonus;
						summ += profit_emp;
						if (maxxx < profit_emp) {
							maxxx = profit_emp;
						}

					}
				}

			}
		}
		if (employees[temp] != null) {
			System.out.println("\nThe most profitable employee: Employee" + employees[temp].getEmployee_id() + ";"
					+ employees[temp].getName() + ";" + employees[temp].getSurname() + "  " + maxxx + " cp.");
		}
		summ /= count_e;
		System.out.println("Average income levels of the employees for the office: " + summ + " cp.");
	}

	public void profit(Contract[] c1, int countu) {
		income_car = 0;
		office_rent = 100;
		employee_salary = 0;
		employee_bonus = 0;
		car_maintenance = 0;
		car_maintenance2 = 0;
		income_sum = 0;
		expenses_office = 0;
		for (int i = 0; i < count_c; i++) {
			if (cars[i] != null) {

				if (cars[i].getClas().equalsIgnoreCase("Luxury")) {
					if (cars[i].getCarAvailabe() != true) {
						income_car = 300;
						cars[i].setProfit(income_car);
						income_sum += income_car;
						car_maintenance2 = 120;
						cars[i].setCar_maintenance2(car_maintenance2);
						System.out.println("  Car" + cars[i].getCar_id() + " : " + income_car);
					} else {
						car_maintenance2 = 120;
						cars[i].setCar_maintenance2(car_maintenance2);
						cars[i].setProfit(300);
					}

				}
				if (cars[i].getClas().equalsIgnoreCase("Economy")) {
					if (cars[i].getCarAvailabe() != true) {
						income_car = 100;
						cars[i].setProfit(income_car);
						income_sum += income_car;
						car_maintenance2 = 20;
						cars[i].setCar_maintenance2(car_maintenance2);
						System.out.println("  Car" + cars[i].getCar_id() + " : " + income_car);
					} else {
						car_maintenance2 = 20;
						cars[i].setCar_maintenance2(car_maintenance2);
						cars[i].setProfit(100);
					}
				}
				if (cars[i].getClas().equalsIgnoreCase("Sports")) {
					if (cars[i].getCarAvailabe() != true) {
						income_car = 200;
						cars[i].setProfit(income_car);
						income_sum += income_car;
						car_maintenance2 = 70;
						cars[i].setCar_maintenance2(car_maintenance2);
						System.out.println("  Car" + cars[i].getCar_id() + " : " + income_car);
					} else {
						car_maintenance2 = 70;
						cars[i].setCar_maintenance2(car_maintenance2);
						cars[i].setProfit(200);
					}

				}
			}

		}
		for (int i = 0; i < count_employee; i++) {
			if (employees[i] != null) {
				employee_salary += 30;
				for (int c = 0; c < countu; c++) {
					if (c1[c].getEmployee_id() == employees[i].getEmployee_id()) {
						if (cars[c1[c].getCar_id() - 1].getClas().equalsIgnoreCase("Luxury")) {
							employee_bonus += 15;

						} else if (cars[c1[c].getCar_id() - 1].getClas().equalsIgnoreCase("Sports")) {
							employee_bonus += 10;
						} else if (cars[c1[c].getCar_id() - 1].getClas().equalsIgnoreCase("Economy")) {
							employee_bonus += 5;
						}

					}
				}

			}

		}
		System.out.println("  Office rent : " + office_rent);
		System.out.println("  Employee salaries : " + employee_salary);
		System.out.println("  Employee performance bonuses: " + employee_bonus);

		Random r = new Random();

		int[] km = { 100, 200, 300 };

		for (int i = 0; i < count_c; i++) {
			if (cars[i] != null) {
				int index = r.nextInt(3);
				cars[i].setKm(km[index]);
			}

		}
		for (int j = 0; j < count_c; j++) {
			if (cars[j] != null) {
				if (cars[j].getCarAvailabe() != true) {
					if (cars[j].getClas().equalsIgnoreCase("Luxury")) {
						car_maintenance = (cars[j].getKm() / 100) * 15;
						cars[j].setCar_maintenance(car_maintenance);
					} else if (cars[j].getClas().equalsIgnoreCase("Sports")) {
						car_maintenance = (cars[j].getKm() / 100) * 10;
						cars[j].setCar_maintenance(car_maintenance);
					} else if (cars[j].getClas().equalsIgnoreCase("Economy")) {
						car_maintenance = (cars[j].getKm() / 100) * 5;
						cars[j].setCar_maintenance(car_maintenance);
					}
					System.out.println("  Car" + cars[j].getCar_id() + " maintenance:" + "\t"
							+ cars[j].getCar_maintenance2() + " + " + cars[j].getCar_maintenance() + " = "
							+ (cars[j].getCar_maintenance2() + cars[j].getCar_maintenance()) + "\t(" + cars[j].getKm()
							+ "km)");
				} else {
					cars[j].setCar_maintenance(0);
					System.out.println("  Car" + cars[j].getCar_id() + " maintenance:" + "\t"
							+ cars[j].getCar_maintenance2() + " + " + cars[j].getCar_maintenance() + " = "
							+ (cars[j].getCar_maintenance2() + cars[j].getCar_maintenance()));
				}

			}

		}
		expenses_office = office_rent + employee_salary + employee_bonus + car_maintenance + car_maintenance2;

	}

	public int employeeKontrol() {
		int[] id = new int[count_employee];

		for (int i = 0; i < count_employee; i++) {
			id[i] = -2;
		}
		int count = 0;
		int r = -1;

		for (int i = 0; i < count_employee; i++) {
			if (employees[i] != null) {
				if (employees[i].isEmp_available() == true) {
					id[count] = employees[i].getEmployee_id();
					count++;
				}
			}
		}
		r = (int) (count * Math.random());
		if (r >= 0 && id[r] != -2) {
			if (employees[id[r] - 1] != null) {
				employees[id[r] - 1].setEmp_available(false);
				return employees[id[r] - 1].getEmployee_id();
			}
		}
		return -1;
	}

	public void arrayCar(int car_id) {
		for (int i = 0; i < count_c; i++) {
			if (cars[i] != null) {
				if (car_id == cars[i].getCar_id())
					cars[i].setCarAvailabe(true);
			}
		}
	}

	public void arrayEmployee(int employee_id) {
		for (int i = 0; i < count_employee; i++) {
			if (employees[i] != null) {
				if (employee_id == employees[i].getEmployee_id())
					employees[i].setEmp_available(true);
			}
		}
	}

	public int carKontrol(String b, String m, String c, int office_id, int count_cars2) {

		int[] id = new int[count_cars2];
		int count = 0;
		int r = -1;

		for (int i = 0; i < count_cars2; i++) {
			id[i] = -2;
		}

		if (m.equalsIgnoreCase("*") && c.equalsIgnoreCase("*")) { // brand ý verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (b.equalsIgnoreCase(cars[i].getBrand()) && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}

		}

		else if (b.equalsIgnoreCase("*") && c.equalsIgnoreCase("*")) { // modeli verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (m.equalsIgnoreCase(cars[i].getModel()) && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}

		}

		else if (b.equalsIgnoreCase("*") && m.equalsIgnoreCase("*")) { // classý verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (c.equalsIgnoreCase(cars[i].getClas()) && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());

			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}

		}

		else if (b.equalsIgnoreCase("*")) { // modeli ve clasý verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (m.equalsIgnoreCase(cars[i].getModel()) && c.equalsIgnoreCase(cars[i].getClas())
							&& cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

					}
					count++;
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}

		}

		else if (c.equalsIgnoreCase("*")) { // modeli ve brand verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (m.equalsIgnoreCase(cars[i].getModel()) && b.equalsIgnoreCase(cars[i].getBrand())
							&& cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

					}
					count++;
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		else if (m.equalsIgnoreCase("*")) { // brand ve clasý verilmiþ
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (c.equalsIgnoreCase(cars[i].getClas()) && b.equalsIgnoreCase(cars[i].getBrand())
							&& cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

					}
					count++;
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		return r - 1;
	}

	// random office clasý belli ya da yýldýz verilen istekler
	public int CarKontrolRandom(int office_id, String c, int count_cars2) {

		int[] id = new int[count_cars2];
		int count = 0;
		int r = -1;
		for (int i = 0; i < count_cars2; i++) {
			id[i] = -2;
		}

		if (c.equalsIgnoreCase("*")) {
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		else if (c.equalsIgnoreCase("economy")) {
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (cars[i].getClas().equalsIgnoreCase("economy") && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		else if (c.equalsIgnoreCase("sports")) {
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (cars[i].getClas().equalsIgnoreCase("sports") && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		else if (c.equalsIgnoreCase("luxury")) {
			for (int i = 0; i < count_cars2; i++) {
				if (cars[i] != null) {
					if (cars[i].getClas().equalsIgnoreCase("luxury") && cars[i].getCarAvailabe() == true) {
						id[count] = cars[i].getCar_id();

						count++;
					}
				}
			}
			int number = count;
			r = (int) (number * Math.random());
			if (r >= 0 && id[r] != -2) {
				cars[id[r] - 1].setCarAvailabe(false);
				return cars[id[r] - 1].getCar_id();
			}
		}

		return -1;
	}

	public void rentCount(int c_id, int e_id) {
		cars[c_id - 1].setRent_count(cars[c_id - 1].getRent_count() + 1);
		employees[e_id - 1].setRent_count_emp(employees[e_id - 1].getRent_count_emp() + 1);
	}

}
