//David Higgins
//11428382

import java.io.FileWriter;
import javax.swing.JOptionPane;


public class hospitalAddSys {
    
    public static void main (String args[]) throws Exception{
        //instance variables
        String firstName;
        String lastName;
        String patientID;
        String phoneNumber;
        
        //Prompting user to input relevant data
         firstName =JOptionPane.showInputDialog("Please enter first name:");
        
         lastName = JOptionPane.showInputDialog("Please enter surname:");
         
         patientID = JOptionPane.showInputDialog("Please enter patient ID:");
        
         phoneNumber = JOptionPane.showInputDialog("Please enter phone number:");
        
        
        FileWriter fw =new FileWriter("patientData.txt");//creating new text file
        //writing inputed data to file
        fw.write(firstName +"\n");
        fw.write(lastName+"\n");
        fw.write(patientID+"\n");
        fw.write(phoneNumber+"\n");
        
        fw.close();
        
        //sending text to method to read info from text file
        ReadFile.textFileReader("patientData.txt");
        
        
    }

}
