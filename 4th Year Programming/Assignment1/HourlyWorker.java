
public final class HourlyWorker extends Employee {

	private double wage;
	private double hours;

	public HourlyWorker(String first, String last, double wagePerHour, double hoursWorked ,int day,int month,int year) {
		
		super(first, last,day, month,year);
		setWage(wagePerHour);
		setHours(hoursWorked);
		
		// TODO Auto-generated constructor stub
	}
	
	public void setWage(double wagePerHour){
		wage= (wagePerHour > 0 ? wagePerHour : 0);
	}
	
	public void setHours(double hoursWorked){
		hours= (hoursWorked > 0 && hoursWorked < 168 ? hoursWorked : 0);
	}

	@Override
	public double earnings() {
		return wage * hours;
	}
	
	public String toString(){
		return "Hourly Worker : " + super.toString();
	}

}
