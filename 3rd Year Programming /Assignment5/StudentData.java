//David Higgins
//11428382

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class StudentData implements Serializable{
    
    //creating instance variables
    private String name;
    private String courseCode;
    private int studentId;
    
    public StudentData(String n, String cc, int sId){
        //constructor
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
    
    //Overriding to toString to print out information associated with each student
    @Override
    public String toString() {
        
        System.out.println("\nName : " + name + "\nCourse code : " + courseCode + " \nID : " + studentId);
        
        return name;
        
    }
    
  
    

}
