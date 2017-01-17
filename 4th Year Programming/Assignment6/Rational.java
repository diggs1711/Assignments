

public class Rational implements Comparable<Object> {
	
	private int p;//numerator
	private int q;//denominator
	
	public int getQ() {
		return q;
	}
	
	public void setQ(String num) throws Exception {
		q = Integer.parseInt(num);//converting the inputed string to an int and setting it as q
		if(q==0){
			throw new Exception();//exception throw if q=0
		}
		 
	}
	public int getP() {
		return p;
	}
	public  void setP(String num) {
		p = Integer.parseInt(num);//converting string to int and setting it as p
	}
	
	//For below
    // p = numerator of the first instance created
	// q = denominator of the first instance created
	// r.getP() = numerator of the second instance created
	// r.getQ() = denominator of the second instance created
	
	
	public void add(Rational r){
		int numer = ((p*r.getQ())+(q*r.getP()));//calculating the numerator
		int denom = q * r.getQ();//calculating the denominator
		System.out.format("\n"+ p + "/"+ q + " + " + r.getP() + "/" + r.getQ() + " =  %d / %d", numer , denom);
	}
	
	public void subtract(Rational r){
		//System.out.println(rat.getP());
		int numer = ((p*r.getQ())-(q*r.getP()));//calculating the numerator
		int denom = q * r.getQ();//calculating the denominator
		System.out.format("\n"+ p + "/"+ q + " - " + r.getP() + "/" + r.getQ() + " =  %d / %d", numer , denom);
	}
	
	public void divide(Rational r){
		int numer = p * r.getQ();//calculating the numerator
		int denom = q * r.getP();//calculating the denominator
		System.out.format("\n"+ p + "/"+ q + " / " + r.getP() + "/" + r.getQ() + " =  %d / %d", numer , denom);
	}
	
	public void multiply(Rational r){
		int numer = p * r.getP();//calculating the numerator
		int denom = q * r.getQ();//calculating the denominator
		System.out.format("\n"+ p + "/"+ q + " * " + r.getP() + "/" + r.getQ() + " =  %d / %d", numer , denom);
	}
	 @Override
    public int compareTo(Object o) {
        double epsilon=0.00000000001;
        float rat1 =  ((float)(this.p)/(this.q));
        //System.out.println((float)rat1);
        float rat2 = ((float) ((Rational) o).getP())/( ((Rational) o).getQ());
        //System.out.println(rat2);
        if(Math.abs(rat1  - rat2) < epsilon){
            return 0;
        }
        if(rat1  > rat2){
            return 1;
        }
        if(rat1  < rat2){
            return -1;
        }
        
        
        
        return 0;
    }
	
	

}
