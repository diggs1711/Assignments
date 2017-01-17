

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
        // TODO Auto-generated method stub
        return 0;
    }
	
	
	

}
