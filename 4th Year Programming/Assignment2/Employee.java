// Abstract base class Employee.
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
public abstract class Employee extends Exception{

    private  String firstName;
    private  String lastName;
    static int count;
    private int idNumber;
    
    static Calendar calender = new GregorianCalendar();
    // constructor
    public Employee(String first, String last,int day,int month,int year) throws Exception
    {
    	 calender.set(Calendar.YEAR, year);
         calender.set(Calendar.MONTH, month); // 11 = december
         calender.set(Calendar.DAY_OF_MONTH, day);
         Calendar today = Calendar.getInstance();
         firstName = first;
         lastName = last; 
        
       
    	    if(Employee.getStartYear()> today.get(Calendar.YEAR) || Employee.getStartYear() < 1990) {
    	    	JOptionPane.showMessageDialog(null,"Name : " + firstName + " " + lastName + "\n"
    	    			+ "Date Entered: " + Employee.getStartDay() + "/" +Employee.getStartMonth() + "/" + Employee.getStartYear() 
    	    			+ "\n The date you entered was invalid.\nIt must be before todays date and after the year 1990.",
                        "Incorrect date entered",
                        JOptionPane.ERROR_MESSAGE);
    	    	throw new IllegalArgumentException("Invalid Date entered"); }
        
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

	public static int getStartDay() {
		return calender.get(Calendar.DAY_OF_MONTH);
	}

	public static int getStartMonth() {
		return calender.get(Calendar.MONTH);
	}

	public static int getStartYear() {
		return calender.get(Calendar.YEAR);
	}


}

