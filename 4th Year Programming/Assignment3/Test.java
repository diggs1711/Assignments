import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class Test {
	private static StringTokenizer numbs1;
	private static StringTokenizer numbs2;//variable to store the tokenized string
	static int i=0;//count variable to determine if its the first or second fraction 
	
	public static void main (String[] args) throws Exception{
		//creating two instances of the rational class
		Rational rat = new Rational();
		Rational rat2 = new Rational();
        int count=0;
		//loop through the arguments to get the fractions
		numbs1 = new StringTokenizer(args[0], "//");
	    	//setting the string found as string1
	        rat.setP(numbs1.nextToken());
            rat.setQ(numbs1.nextToken());
	    	//dividing up the string into individual numbers
	    	
	    	    for(String nums:args){
	    	        numbs2=new StringTokenizer(nums, "//");
	    	        
                    if(count>0){
	    	        while(numbs2.hasMoreTokens()){
	    	            
	    	            rat2.setP(numbs2.nextToken());
	                    rat2.setQ(numbs2.nextToken());	
                        //when all numbers are set, apply various methods to the fractions
	    	            rat.add(rat2);
	    	            rat.subtract(rat2);
	    	            rat.divide(rat2);
	    	            rat.multiply(rat2);
	    	            System.out.println();
	    	            
	    	            }}
                    count++;
	    	        }
	    	
	    	
	    }
	

}

