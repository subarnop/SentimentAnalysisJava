import java.io.*;

class CountNum{
    public static void main(String[] args){
        try{
            FileInputStream fis = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
            int count=0;
            
			while ((line = br.readLine()) != null) {
			    count++;
			}
			System.out.println("Total cases: " + count);
        }
        catch(Exception E){}
    }
}
