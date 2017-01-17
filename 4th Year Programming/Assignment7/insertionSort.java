import java.util.Comparator;


public class insertionSort implements Comparator {
    
    @Override
    public int compare(Object o,Object r) {
        double epsilon=0.00000000001;
        float rat1 =  ((float)(((Rational) r).getP())/(((Rational) r).getQ()));
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
