// Driver for Employee hierarchy

// Java core packages
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;


import java.util.GregorianCalendar;

// Java extension packages
import javax.swing.JOptionPane;

import java.util.Date;

public class Test {

    // test Employee hierarchy
    public static void main(String args[]) {
    	//Getting todays date
    	Calendar today = Calendar.getInstance();
    	
		double currMonth = today.get(Calendar.MONTH);
		double currDay = today.get(Calendar.DAY_OF_WEEK);
		double currYear = today.get(Calendar.YEAR);
        
		
		//Adding employees to array
        Employee[] employees= new Employee[]{
        		new Boss("John", "Smith", 800.0, 4, 6, 2002),

                new CommissionWorker("Sue", "Jones", 400.0, 3.0, 150, 2, 2, 1990),

                new PieceWorker("Bob", "Lewis", 2.5, 200, 3, 3, 2010),

                new HourlyWorker("Karen", "Price", 13.75, 40, 4, 7, 2013)
        };

        for(Employee employee:employees){
        	//calculating amount earned by each employee
        	double totalAmountEarned=employee.earnings();
        	
        	if((currYear - employee.getStartYear()) >= 5){
        		//adding 100 to amount earned if worked 5 or more years 
        		totalAmountEarned += 100.00;
        	}
        
        	String output = ("ID" + "     Employee  " + "    Earned: $ " +  "\n " + employee.getIdNumber() + "      " + employee.getFirstName() + " " + employee.getLastName()
        			+ "     " + totalAmountEarned );
        	//displaying employee id, number and amount earned to screen
        	JOptionPane.showMessageDialog(null, output,
                    "Demonstrating Polymorphism",
                    JOptionPane.INFORMATION_MESSAGE);


        	
        
        	
        } 
   
        
        
        System.exit(0);
    }
} // end class Test
