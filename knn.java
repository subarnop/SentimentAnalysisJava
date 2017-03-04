import java.io.*;
import java.util.*;

public class knn{
    
    public static double taxicab(double x[], double[] y){
        
        double dist=0, diff;
        for(int i=0;i<x.length;i++){
            diff = Math.abs(x[i]-y[i])*(1000-i);
        }
        
        return dist;
    }   
     
    public static double euclidian(double x[], double[] y){
        
        double dist=0, diff;
        for(int i=0;i<x.length;i++){
            diff = (x[i]-y[i]);
            dist += diff*diff*(1000-i);
        }
        
        dist = Math.sqrt(dist);
        
        return dist;
    }
    
    public static double hamming(double x[], double[] y){
        
        double dist=0, diff;
        for(int i=0;i<x.length;i++){
            if((x[i]!=0 && y[i]!=0) || (x[i]==0 && y[i]==0))
                diff = 0;
            else
                diff = 1000-i;
            dist += diff;
        }
        return dist;
    }
    
    public static int main(double[][] d,double[] test,String S){
        
        double d0=0, d1=0;    

        if(S.equals("euclidian")){
            d0 = euclidian(d[0],test);
            d1 = euclidian(d[1],test);
        }
        else if(S.equals("taxicab")){
            d0 = taxicab(d[0],test);
            d1 = taxicab(d[1],test);
        }
        else if(S.equals("hamming")){
            d0 = hamming(d[0],test);
            d1 = hamming(d[1],test);
        }
        if(d0<d1)
            return 0;
        else
            return 1;
    }
}
