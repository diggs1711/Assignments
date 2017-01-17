//David Higgins
//11428382

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;


public class CopyOfSerialsingArray implements Serializable {
    
    public static StudentData[] studentInfo= new StudentData[10];
    
    
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        String cc;
        String name=null;
        int sid=0;
        
        for(int i=0;i<2;i++){
            
            name = JOptionPane.showInputDialog(null, "Please enter student's name : ");
            cc = JOptionPane.showInputDialog( null, "Please enter course  code : ");
            sid= Integer.parseInt(JOptionPane.showInputDialog( null, "Please enter student ID : "));
            
            StudentData student=new StudentData(name,cc,sid);
            studentInfo[i]=student;
            }
        
        
        FileOutputStream out = new FileOutputStream("studentdata.txt");
        ObjectOutputStream s = new ObjectOutputStream(out);
        s.writeObject(studentInfo);      
        s.flush();
        
        
        FileInputStream in = new FileInputStream("studentdata.txt");
        ObjectInputStream s1 = new ObjectInputStream(in);
        StudentData[] studData = (StudentData[])s1.readObject();
   
        for(Object i:studData){
            if(i!=null){
            i.toString();}
            else{break;}
        }
    
    }
    
    

    private void writeObject(ObjectOutputStream s) throws IOException {
         s.defaultWriteObject();
     
         }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
         s.defaultReadObject();
   
         }

}