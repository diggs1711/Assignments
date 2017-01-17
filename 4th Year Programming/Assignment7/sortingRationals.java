import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class sortingRationals {

    public static void main(String args[]) throws Exception{
        //creating 10 instances of rational class
        
        Rational rat1 = new Rational();
        Rational rat2 = new Rational();
        Rational rat3 = new Rational();
        Rational rat4 = new Rational();
        Rational rat5 = new Rational();
        Rational rat6 = new Rational();
        Rational rat7 = new Rational();
        Rational rat8 = new Rational();
        Rational rat9 = new Rational();
        Rational rat10 = new Rational();
        
        //creating an arraylist of rational objects
        ArrayList<Rational> ratNums = new ArrayList<Rational>();
        
        //setting the instance variables of each rational object 
        rat1.setP("1");
        rat1.setQ("3");
        
        rat2.setP("4");
        rat2.setQ("5");
        
        rat3.setP("5");
        rat3.setQ("6");
        
        rat4.setP("4");
        rat4.setQ("13");
        
        rat5.setP("3");
        rat5.setQ("6");
        
        rat6.setP("1");
        rat6.setQ("7");
        
        rat7.setP("10");
        rat7.setQ("8");
        
        rat8.setP("14");
        rat8.setQ("9");
        
        rat9.setP("111");
        rat9.setQ("10");
        
        rat10.setP("1");
        rat10.setQ("110");
        
        //adding the instances of the rational class to the arraylist
        ratNums.add(rat1);
        ratNums.add(rat2);
        ratNums.add(rat3);
        ratNums.add(rat4);
        ratNums.add(rat5);
        ratNums.add(rat6);
        ratNums.add(rat7);
        ratNums.add(rat8);
        ratNums.add(rat9);
        ratNums.add(rat10);
        
        //shuffling the objects in the arraylist into new random position
        Collections.shuffle(ratNums);
        
        System.out.println("Unsorted Array");
        
      //looping through the shuffled array and printing out each rational object   
        for(Rational i: ratNums){                
            System.out.println(i.getP() + "/" + i.getQ());       
        }
        System.out.println();
        
      //sorting the arraylist using the insertionSort class to preform the sort function
        Collections.sort(ratNums, new insertionSort());
        
        System.out.println("Sorted Array");
        
      //looping through the sorted array and printing out each rational object 
        for(Rational i: ratNums){              
            System.out.println(i.getP() + "/" + i.getQ());
        }
        System.out.println();
        
        //prompting user to inout a fraction 
        System.out.println("Enter fraction you wish to search for :");
        Scanner sc = new Scanner(System.in);
        
        String s = sc.nextLine();//capturing inputed fraction as a string
        
        //splitting the string into two seperate strings, corresponding to the numerator
        //and denominator of the fraction,and adding them to new string array
        String[] parts = s.split("/");
        
        //creating new instance of the rational class, which the inputed fraction will be
        //assigned to.
        Rational fractionToLookFor = new Rational();
        
        fractionToLookFor.setP(parts[0]);
        fractionToLookFor.setQ(parts[1]);
        
        //using the built-in binarySearch to see if the inputed fraction is contained within
        //the arraylist
        int index = Collections.binarySearch(ratNums, fractionToLookFor ,new insertionSort());
        
        //if fraction is not found, a result of minus one is given
        //therefore if less zero, fraction is not in the arraylist
        if(index < 0){
            System.out.println("Fraction not found");
        }else{//otherwise the fraction is present 
            System.out.println("Fraction found at postion :" + (index+1) ); 
        }
        
    }
    
 
    private static ArrayList<Rational> insertionSort(ArrayList<Rational> ratNums)                                             
    {
        int index;     // general index for keeping track of a position in array
        int toSort;  // stores the index of an out-of-place element when sorting.
        int last = ratNums.size() - 1;//length of array
        
        // Work forward through the list, starting at 2nd element, 
        // and sort each element relative to the ones before it.
        for (toSort = 1; toSort <= last; toSort++)
        {
            
            Rational temp = ratNums.get(toSort);
       
            // Go back through the list to see how far back (if at all)
            // this element should be moved.
            boolean moveMade = false;
            index = toSort - 1;
           
            while ((index >= 0) && (temp.compareTo(ratNums.get(index)) < 0))
            {
                // Shuffle elements over to the right, put firstUnsorted before them 
            
                ratNums.set(index +1 , ratNums.get(index));
                index--;

                moveMade = true;
            }
            if (moveMade) {   
                ratNums.set(index +1 , temp);
            }
        }
        return ratNums; 
    } 

    
}
