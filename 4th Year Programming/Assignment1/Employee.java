// Abstract base class Employee.
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
public abstract class Employee {

    private String firstName;
    private String lastName;
    private int startDay;
    private int startMonth;
    private int startYear;
    static int count;
    private int idNumber;
    
    Calendar calender = new GregorianCalendar();
    // constructor
    public Employee(String first, String last,int day,int month,int year) {
        firstName = first;
        lastName = last;
        calender.set(Calendar.YEAR, year);
        calender.set(Calendar.MONTH, month); // 11 = december
        calender.set(Calendar.DAY_OF_MONTH, day);
        
        /*startYear=calender.YEAR;
        startMonth=calender.MONTH; // 11 = december
        startDay=calender.DAY_OF_MONTH;*/
        
        count = count+1;
        idNumber=count;
    }

    // get first name
    public String getFirstName() {
        return firstName;
    }

    // get last name
    public String getLastName() {
        return lastName;
    }

    public String toString() {
    	int year       = calender.get(Calendar.YEAR);  // now 2010
    	int month      = calender.get(Calendar.MONTH); // now 0 (Jan = 0)
    	int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
        return firstName + ' ' + lastName + ' ' + dayOfMonth + '/' + month + '/' + year + ' ' + idNumber;
    }

    public abstract double earnings();


	public int getIdNumber() {
		return idNumber;
	}

	public int getStartDay() {
		return calender.get(Calendar.DAY_OF_MONTH);
	}

	public int getStartMonth() {
		return calender.get(Calendar.MONTH);
	}

	public int getStartYear() {
		return calender.get(Calendar.YEAR);
	}


}
