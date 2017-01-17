//David Higgins
//11428382

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;


public class SerialsingArray implements Serializable {
    //creating array of students
    public static StudentData[] studentInfo= new StudentData[100];
    //class variables
    static String cc;//
    static String name;
    static int sid=0;
    
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        
        
        
        for(int i=0;i<2;i++){
            
            //loop that asks user to input student data
            name = JOptionPane.showInputDialog(null, "Please enter student's name : ");
            cc = JOptionPane.showInputDialog( null, "Please enter course  code : ");
            sid= Integer.parseInt(JOptionPane.showInputDialog( null, "Please enter student ID : "));
            
            //data is stored in variable student and a new instance of the studentData class
            //is created
            StudentData student=new StudentData(name,cc,sid);
            
            //object stored in array
            studentInfo[i]=student;
            }
        
        //writing array to a new text document
        FileOutputStream out = new FileOutputStream("studentdata.txt");
        ObjectOutputStream s = new ObjectOutputStream(out);
        s.writeObject(studentInfo);      
        s.flush();
        s.close();
        
        //reading data from text file
        FileInputStream in = new FileInputStream("studentdata.txt");
        ObjectInputStream s1 = new ObjectInputStream(in);
        //adding data to new array studData 
        StudentData[] studData = (StudentData[])s1.readObject();
        s1.close();
        
        //looping through array and printing out info associated with object
        for(Object i:studData){
            if(i!=null){
            i.toString();}//using modified toString() method
            else{break;}
        }
    
    }
    
    
    

}