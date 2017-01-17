//David Higgins
//11428382

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class CopyOfStudentData implements Serializable{
    
    private String name;
    private String courseCode;
    private int studentId;
    
    public CopyOfStudentData(String n, String cc, int sId){
        
        this.name=n;
        this.courseCode=cc;
        this.studentId=sId;
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    @Override
    public String toString() {
        
        System.out.println("\nName : " + name + "\nCourse code : " + courseCode + " \nID : " + studentId);
        
        return name;
        
    }
    
    private void writeObject(ObjectOutputStream s) throws IOException {
        
        s.writeObject(name);
        s.writeObject(courseCode); 
        s.writeObject(studentId); 
     
         }
    
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        
        name = (String) s.readObject();
        courseCode = (String) s.readObject(); 
        studentId = (int) s.readObject();
    }
    

}
