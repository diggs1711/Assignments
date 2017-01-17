//David Higgins
//11428382

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;


public class appendTextFile {
    //instance variables
    static String textFile;
    static String stringToAdded;
    
    public static void main (String args[]) throws IOException{
        //asking user for the text file to be appended
        textFile = JOptionPane.showInputDialog(null, "Please enter text file you wish to view");
        
        //creating new instance of the randomaccessfile class
        RandomAccessFile raf = new RandomAccessFile(textFile + ".txt","rw");
        //getting length of text 
        long endOfText= raf.length();
        
        //moving pointer to end of file
        raf.seek(endOfText);
        
        //asking user for the string to be addded to end of file
        stringToAdded = JOptionPane.showInputDialog(null, "Please enter the string you wish to add");
        
        //writing string to end of file
        raf.writeBytes(stringToAdded);
        
        raf.close();
    }

}
