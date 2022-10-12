
public class Date {
	private int day;
	private int month;
	private int year;

	Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String display() {
		return (this.day + "." + this.month + "." + this.year);
	}

	public void formatControl() {
		if (day > 30) {
			int monthChecker = day / 30;
			day %= 30;
			month += monthChecker;
		}
		if (month > 12) {
			int yearChecker = month / 12;
			month %= 12;
			year += yearChecker;
		}
	}
}
