import java.io.*;
import java.util.*;

public class Company {
	private Office[] offices;
	private Customer[] customers;
	private int office_count;
	private int customer_count;
	private int counter_employees2;
	private int count_cars2;
	public CarRequest[] requests;
	private CarRequest[] random_requests;
	private int random_r_count;
	public int request_count;
	private Contract[] contracts;
	private int contract_count;
	private Date currentDate = new Date(1, 1, 2021);
	private int total_compensation = 0;
	private int count_nextday = 0;

	public Company(int capacity_office, int capacity_customer, int capacity_request, int capacity_contract) {
		offices = new Office[capacity_office];
		office_count = 0;
		customers = new Customer[capacity_customer];
		customer_count = 0;
		counter_employees2 = 0;
		count_cars2 = 0;
		requests = new CarRequest[capacity_request];
		request_count = 0;
		contracts = new Contract[capacity_contract];
		contract_count = 0;
		random_requests = new CarRequest[capacity_request];
		random_r_count = 0;
	}

	public boolean dateKontrol(Date start, Date end) {
		if (start.getYear() == end.getYear() && start.getMonth() == end.getMonth()
				&& end.getDay() - start.getDay() > 4) {
			return false;
		} else if (start.getYear() == end.getYear() && start.getMonth() < end.getMonth()
				&& end.getMonth() - start.getMonth() == 1) {
			if (end.getDay() + 30 - start.getDay() > 4)
				return false;
		} else if (start.getYear() < end.getYear() && end.getYear() - start.getYear() == 1
				&& start.getMonth() - end.getMonth() == 11) {
			if (end.getDay() + 30 - start.getDay() > 4)
				return false;
		}
		if (start.getDay() != currentDate.getDay() && start.getMonth() != currentDate.getMonth()
				&& start.getYear() != currentDate.getYear()) {
			return false;
		} else if (start.getDay() != currentDate.getDay() && start.getMonth() == currentDate.getMonth()
				&& start.getYear() == currentDate.getYear()) {
			return false;
		}
		return true;
	}

	public void listContract() {
		for (int i = 0; i < contract_count; i++) {
			if (contracts[i].isAvailable() == true)
				System.out.println("  " + contracts[i].getContract_id() + ".Contract: Employee"
						+ contracts[i].getEmployee_id() + " Customer" + contracts[i].getCustomer_id() + " Car"
						+ contracts[i].getCar_id() + "   " + contracts[i].getStart_date().display() + "    "
						+ contracts[i].getEnd_date().display());
		}
	}

	// her þeyi belli araç isteði
	public void addCarRequest(int office_id, int customer_id, String brand, String model, String clas, Date start_date,
			Date end_date) {
		CarRequest request = new CarRequest(office_id, customer_id, brand, model, clas, start_date, end_date,
				request_count + 1);
		requests[request_count] = request;
		request_count++;

		boolean flag_date = dateKontrol(start_date, end_date);
		int employee_id = offices[office_id - 1].employeeKontrol();
		int car_id = offices[office_id - 1].carKontrol(brand, model, clas, office_id, count_cars2);

		if (employee_id != -1 && flag_date != false && car_id != -1) {
			Contract c = new Contract(employee_id, customer_id, car_id, start_date, end_date, contract_count + 1,
					office_id, true);
			offices[office_id - 1].rentCount(car_id, employee_id);
			customers[customer_id - 1].setCount(customers[customer_id - 1].getCount() + 1);
			contracts[contract_count] = c;
			contract_count++;
			c.display();
		} else {
			offices[office_id - 1].arrayCar(car_id);
			offices[office_id - 1].arrayEmployee(employee_id);
			request.isAvailable(car_id, employee_id, flag_date);
		}
	}

	public void addCarRequestRandom(int office_id, String clas) {
		CarRequest request = new CarRequest(office_id, clas, random_r_count + 1);
		random_requests[random_r_count] = request;
		random_r_count++;

		int customer_id = (int) (customer_count * Math.random() + 1);

		int RandomNumberDate = (int) (4 * Math.random());
		Date end = new Date(currentDate.getDay() + RandomNumberDate, currentDate.getMonth(), currentDate.getYear());
		end.formatControl();

		int employee_id = 0;
		int car_id = 0;
		int RandomNumberOffice = 0;

		if (office_id == -1) { // -1 geldiðinde rastgele ofis belirlenmesi için office count kadar bir
								// aralýktan bir sayý belirlenir
			RandomNumberOffice = (int) (office_count * Math.random());
			if (offices[RandomNumberOffice] != null) {
				car_id = offices[RandomNumberOffice].CarKontrolRandom(office_id, clas, count_cars2);
				employee_id = offices[RandomNumberOffice].employeeKontrol();
			} else
				System.out.println("Office not found!");
			if (employee_id != -1 && car_id != -1) {
				Contract c = new Contract(employee_id, customer_id, car_id, currentDate, end, contract_count + 1,
						office_id, true);

				if (offices[RandomNumberOffice] != null && customers[customer_id - 1] != null) {
					offices[RandomNumberOffice].rentCount(car_id, employee_id);
					customers[customer_id - 1].setCount(customers[customer_id - 1].getCount() + 1);
					contracts[contract_count] = c;
					contract_count++;
					c.display();
				}

			} else {
				offices[RandomNumberOffice].arrayCar(car_id);
				offices[RandomNumberOffice].arrayEmployee(employee_id);
				request.isAvailable(car_id, employee_id, true);
			}
		}

		else {
			car_id = offices[office_id - 1].CarKontrolRandom(office_id, clas, count_cars2);
			employee_id = offices[office_id - 1].employeeKontrol();
			if (employee_id != -1 && car_id != -1) {
				Contract c = new Contract(employee_id, customer_id, car_id, currentDate, end, contract_count + 1,
						office_id, true);
				offices[office_id].rentCount(car_id, employee_id);
				customers[customer_id - 1].setCount(customers[customer_id - 1].getCount() + 1);
				contracts[contract_count] = c;
				contract_count++;
				c.display();
			} else {
				offices[office_id - 1].arrayCar(car_id);
				offices[office_id - 1].arrayEmployee(employee_id);
				request.isAvailable(car_id, employee_id, true);
			}
		}
	}

	public void listCarRequest() {
		for (int i = 0; i < request_count; i++) {

			if (random_requests[i] != null) {
				System.out.println("  " + random_requests[i].getCar_request_id() + ".Car Request :  "
						+ random_requests[i].getOffice_id() + " " + random_requests[i].getClas());
			} else {
				System.out.println("  " + requests[i].getCar_request_id() + ".Car Request :  "
						+ requests[i].getOffice_id() + " " + requests[i].getCutomer_id() + " " + requests[i].getBrand()
						+ " " + requests[i].getModel() + " " + requests[i].getClas() + " "
						+ requests[i].getStart_date().display() + " " + requests[i].getEnd_date().display());
			}
		}
	}

	public void addCustomer(String name, String surname, int customer_id) {
		Customer customer = new Customer(name, surname, customer_id);
		customers[customer_count] = customer;
		customer_count++;
	}

	public void listCustomer() {
		for (int i = 0; i < customer_count; i++) {
			System.out.println("  " + customers[i].getCustomer_id() + ".Customer " + customers[i].getName() + " "
					+ customers[i].getSurname() + " " + customers[i].getCount());
		}
	}

	public void addOffice(String phone, String address, String city) {
		Office office = new Office(phone, address, city, office_count + 1, 100); // çalýþan ve araba sayýsýný tutar
		offices[office_count] = office;
		office_count++;
	}

	public void listOffices() {
		for (int i = 0; i < office_count; i++) {
			if (offices[i].getOffice_id() != -1) // silinen officelerde hata almamak için kontrol
				System.out.println("  " + offices[i].getOffice_id() + ".Office " + offices[i].getPhone() + " "
						+ offices[i].getAddress() + " " + offices[i].getCity());
		}
	}

	public void deleteOffice(int id_office) {
		for (int i = 0; i < office_count; i++) {
			if (offices[i].getOffice_id() == id_office) {
				System.out.println("  " + offices[i].getOffice_id() + ".Office DELETED");
				offices[i].setOffice_id(-1);
				for (int k = 0; k < counter_employees2; k++) {
					offices[i].deleteEmployee(id_office, k + 1);
				}
			}
		}
	}

	public void dongu(boolean bool) throws IOException {
		String s, s1, s2, s3, s4;
		int i1 = 0, i2 = 0;
		System.out.println("-----" + currentDate.display() + "-----");
		while (bool) {
			System.out.print(">>>");
			Scanner sc = new Scanner(System.in);
			String str = sc.next();
			String[] words = str.split(";");
			String file_name = "";
			int id = 0;

			if (words[0].equalsIgnoreCase("load")) { // dosya okumanýn yapýldýðý if
				file_name = words[1];
				BufferedReader inputStream = null;
				try {
					inputStream = new BufferedReader(new FileReader(file_name));
					String satir;
					while ((satir = inputStream.readLine()) != null) {

						String[] line = satir.split(";");
						if (words[0].equalsIgnoreCase("load")) {
							System.out.println(">" + satir);
						}
						// OFFICES
						if (line[0].equalsIgnoreCase("addOffice")) {
							addOffice(line[1], line[2], line[3]);
						} else if (line[0].equalsIgnoreCase("listOffice")) {
							listOffices();
						} else if (line[0].equalsIgnoreCase("deleteOffice")) {
							deleteOffice(Integer.parseInt(line[1]));
						}
						// EMPLOYEES
						if (line[0].equalsIgnoreCase("addEmployee")) {

							if (offices[Integer.parseInt(line[5]) - 1] == null) {// olmayan office çalýþan eklenemez
								System.out.println("Office not found.");
							} else {
								counter_employees2++;
								offices[Integer.parseInt(line[5]) - 1].addEmployee(line[1], line[2], line[3], line[4],
										Integer.parseInt(line[5]), counter_employees2);
							}

						} else if (line[0].equalsIgnoreCase("listEmployee")) {

							offices[Integer.parseInt(line[1]) - 1].listEmployees(Integer.parseInt(line[1]));
						} else if (line[0].equalsIgnoreCase("deleteEmployee")) {
							offices[Integer.parseInt(line[1]) - 1].deleteEmployee(Integer.parseInt(line[1]),
									Integer.parseInt(line[2]));
						}
						// CAR
						if (line[0].equalsIgnoreCase("addCar")) {
							if (offices[Integer.parseInt(line[5]) - 1] == null) { // olmayan office araba eklenemez
								System.out.println("Office not found.");
							} else {
								count_cars2++;
								offices[Integer.parseInt(line[5]) - 1].addCar(line[1], line[2], line[3],
										Integer.parseInt(line[4]), Integer.parseInt(line[5]), count_cars2);
							}
						} else if (line[0].equalsIgnoreCase("listCar")) {
							offices[Integer.parseInt(line[1]) - 1].listCar(Integer.parseInt(line[1]), count_cars2);
						}
						// CUSTOMER
						if (line[0].equalsIgnoreCase("addCustomer")) {
							addCustomer(line[1], line[2], customer_count + 1);
						} else if (line[0].equalsIgnoreCase("listCustomer")) {
							listCustomer();
						} else if (line[0].equalsIgnoreCase("listCustomer")) {
							listCustomer();
						}
						// REQUEST
						else if (line[0].equalsIgnoreCase("addCarRequest")) {// her þeyi belli request
							String line6 = line[6];
							String line7 = line[7];
							line6 = line6.replace(".", ";");
							line7 = line7.replace(".", ";");
							String[] arr = line6.split(";");
							String[] arr1 = line7.split(";");
							Date start = new Date(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]),
									Integer.parseInt(arr[2]));
							Date end = new Date(Integer.parseInt(arr1[0]), Integer.parseInt(arr1[1]),
									Integer.parseInt(arr1[2]));
							addCarRequest(Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3], line[4],
									line[5], start, end);
						} else if (line[0].equalsIgnoreCase("addCarRequestRandom")) {
							addCarRequestRandom(Integer.parseInt(line[1]), line[2]);
						} else if (line[0].equalsIgnoreCase("addCarRequestNRandom")) {

							int min = Integer.parseInt(line[1]);
							int max = Integer.parseInt(line[2]);
							Random r = new Random();
							int random = r.nextInt(max - min) + min;
							System.out.println("random gelen sayý" + random);
							for (int i = 0; i < random; i++) {
								addCarRequestRandom(-1, "*");
							}
						} else if (line[0].equalsIgnoreCase("listCarRequest")) {
							listCarRequest();
						} else if (line[0].equalsIgnoreCase("listContract")) {
							listContract();
						}
					}
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}
			// elle girilen kýsmýn else'i
			else {
				if (words[0].equalsIgnoreCase("addOffice")) {
					System.out.println("Enter office phone number :");
					s = sc.next();
					System.out.println("Enter office address :");
					s1 = sc.next();
					System.out.println("Enter office city :");
					s2 = sc.next();
					addOffice(s, s1, s2);
				} else if (words[0].equalsIgnoreCase("addEmployee")) {
					System.out.println("Enter employee name :");
					s = sc.next();
					System.out.println("Enter employee surname :");
					s1 = sc.next();
					System.out.println("Enter employee gender :");
					s2 = sc.next();
					System.out.println("Enter employee birthdate :");
					s3 = sc.next();
					System.out.println("Enter employee office id :");
					i1 = sc.nextInt();

					if (offices[i1 - 1] == null) { // olmayan office çalýþan eklenemez
						System.out.println("Office not found.");
					} else {
						counter_employees2++;
						offices[i1 - 1].addEmployee(s, s1, s2, s3, i1, counter_employees2);
					}
				} else if (words[0].equalsIgnoreCase("addCar")) {
					System.out.println("Enter car name :");
					s = sc.next();
					System.out.println("Enter car brand :");
					s1 = sc.next();
					System.out.println("Enter car class :");
					s2 = sc.next();
					System.out.println("Enter car km :");
					i1 = sc.nextInt();
					System.out.println("Enter car office id :");
					i2 = sc.nextInt();

					if (offices[i2 - 1] == null) { // olmayan office araba eklenemez
						System.out.println("Office not found.");
					} else {
						count_cars2++;
						offices[i2 - 1].addCar(s, s1, s2, i1, i2, count_cars2);
					}
				} else if (words[0].equalsIgnoreCase("addCustomer")) {
					System.out.println("Enter customer name :");
					s = sc.next();
					System.out.println("Enter customer surname :");
					s1 = sc.next();
					addCustomer(s, s1, customer_count + 1);
				}

				else if (words[0].equalsIgnoreCase("listEmployee")) {
					if (words.length > 1) { // office id si girilmiþ mi diye kontrol
						id = Integer.parseInt(words[1]);
					}
					for (int i = 0; i < office_count; i++) {
						if (id == offices[i].getOffice_id()) {
							offices[i].listEmployees(i + 1);
						} else if (id == 0)
							offices[i].listEmployees(i + 1);
					}
				} else if (words[0].equalsIgnoreCase("listOffice")) {
					listOffices();
				} else if (words[0].equalsIgnoreCase("listCar")) {
					if (words.length > 1) { // office id si girilmiþ mi diye kontrol
						id = Integer.parseInt(words[1]);
					}
					for (int i = 0; i < office_count; i++) {
						if (id == offices[i].getOffice_id()) {
							offices[i].listCar(i + 1, count_cars2);
						} else if (id == 0)
							offices[i].listCar(i + 1, count_cars2);
					}
				} else if (words[0].equalsIgnoreCase("listCustomer")) {
					listCustomer();
				} else if (words[0].equalsIgnoreCase("deleteOffice")) {
					System.out.println("Enter office id :");
					i1 = sc.nextInt();
					deleteOffice(i1);
				} else if (words[0].equalsIgnoreCase("deleteEmployee")) {

					System.out.println("Enter employee id :");
					i1 = sc.nextInt();
					System.out.println("Enter office id :");
					i2 = sc.nextInt();
					offices[i2 - 1].deleteEmployee(i2, i1);
				} else if (words[0].equalsIgnoreCase("addCarRequest")) {
					System.out.println("Enter office id :");
					i1 = sc.nextInt();
					System.out.println("Enter customer id :");
					i2 = sc.nextInt();
					System.out.println("Enter brand :");
					s = sc.next();
					System.out.println("Enter model :");
					s1 = sc.next();
					System.out.println("Enter class :");
					s2 = sc.next();
					System.out.println("Enter start date(XX.XX.XXX):");
					s3 = sc.next();
					s3 = s3.replace(".", ";");
					String[] arr = s3.split(";");
					System.out.println("Enter end date(XX.XX.XXX):");
					s4 = sc.next();
					s4 = s4.replace(".", ";");
					String[] arr1 = s4.split(";");

					Date start = new Date(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
					Date end = new Date(Integer.parseInt(arr1[0]), Integer.parseInt(arr1[1]),
							Integer.parseInt(arr1[2]));
					addCarRequest(i1, i2, s, s1, s2, start, end);
				}

				else if (words[0].equalsIgnoreCase("addCarRequestRandom")) {
					System.out.println("Enter office id :"); // random isterse -1 girer
					i1 = sc.nextInt();
					System.out.println("Enter class :");
					s2 = sc.next();
					addCarRequestRandom(i1, s2);
				}

				else if (words[0].equalsIgnoreCase("addCarRequestNRandom")) {
					System.out.println("Enter min number : ");
					int min = sc.nextInt();
					System.out.println("Enter max number : ");
					int max = sc.nextInt();

					Random r = new Random();
					int random = r.nextInt(max - min) + min;
					for (int i = 0; i < random; i++) {
						addCarRequestRandom(-1, "*");
					}
				}

				else if (words[0].equalsIgnoreCase("listcontract")) {
					listContract();
				} else if (words[0].equalsIgnoreCase("listCarRequest")) {
					listCarRequest();
				} else if (words[0].equalsIgnoreCase("Exit")) {
					System.out.println("<<<<	Simulation has ended	>>>>  We hope you come again :) see you... ");
					break;
				} else if (words[0].equalsIgnoreCase("nextday")) {
					if (office_count == 0) {
						System.out.println("Please add an office");
						continue;
					} else {
						count_nextday++;
						System.out.println("--- Office Profits ---");
						for (int i = 0; i < office_count; i++) {
							System.out.println("Office " + offices[i].getOffice_id());
							offices[i].profit(contracts, contract_count);

							System.out.println("Income : " + offices[i].getIncome_sum() + " cp");
							System.out.println("Expenses : " + offices[i].getExpenses_office() + " cp");
							System.out.println("Office" + offices[i].getOffice_id() + " profit : "
									+ (offices[i].getIncome_sum() - offices[i].getExpenses_office()) + " cp");
							System.out.println("*******************************");
							total_compensation = offices[i].getCompensation() * 200;

						}
						System.out.println("Total compensation paid by company: " + total_compensation);
						System.out.println("--- Office Statistics of the Last 10 Days ---");
						for (int i = 0; i < office_count; i++) {
							System.out.println("---Office" + offices[i].getOffice_id() + "---");
							offices[i].statistic(contracts, contract_count, customers, customer_count, (i + 1));
							System.out.println("\n");
						}
						if (count_nextday % 3 == 0) {
							System.out.println("--- System Recommendations ----");
							for (int a = 0; a < office_count; a++) {
								if (offices[a].getIncome_sum() - offices[a].getExpenses_office() < 0) {
									System.out.println("Office" + offices[a].getOffice_id() + ": Buy a new car ("
											+ offices[a].getClas() + ")");
								}
							}
						}
						currentDate.setDay(currentDate.getDay() + 1);
						currentDate.formatControl();

						/// Contract bitiminde araç boþa çýkýyor
						for (int j = 0; j < contract_count; j++) {
							if (contracts[j].getEnd_date().getDay() + 1 == currentDate.getDay()) {
								offices[contracts[j].getOffice_id() - 1].arrayCar(contracts[j].getCar_id());
								offices[contracts[j].getOffice_id() - 1].arrayEmployee(contracts[j].getEmployee_id());
								contracts[j].setAvailable(false);
							} else if (contracts[j].getEnd_date().getDay() == contracts[j].getStart_date().getDay()) {
								offices[contracts[j].getOffice_id() - 1].arrayCar(contracts[j].getCar_id());
								offices[contracts[j].getOffice_id() - 1].arrayEmployee(contracts[j].getEmployee_id());
								contracts[j].setAvailable(false);

							}
						}

						System.out.println(
								"------------------------------------------------------------------------------------------");
						System.out.println("--- Date: " + currentDate.display() + " ---");

					}

				}

			}

		}
	}

}
