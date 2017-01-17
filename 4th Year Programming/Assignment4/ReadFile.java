//David Higgins
//11428382


import java.io.FileReader;
import java.io.LineNumberReader;


public class ReadFile { 
    
    public static void textFileReader(String f) throws Exception{
        
        FileReader fr = new FileReader (f);
        
        LineNumberReader lnr = new LineNumberReader(fr);
        System.out.println("Line Number " + " Info");
        while(fr!=null){//if there's is info in text file, continue
            
             int j=lnr.getLineNumber();//getting the line number 
             String i= lnr.readLine();//getting first line of data
             
             if(i==null){//if file has run out of text, break out of while loop
                 break;
             }               
             
             System.out.println( "     "+ (j+1) +"        " + i);//printing data to console
             }
        
        lnr.close();
    }

}
