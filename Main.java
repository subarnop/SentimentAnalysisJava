import java.io.*;
import java.util.*;

class Main{
	static class Keys{
		static String[] keys;
		public static void getKeys(){
			try{
				FileInputStream fis = new FileInputStream("positivekeywords.txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String line = null;
				line = br.readLine();
				keys = line.split(" ");
				br.close();		
			}
			catch(Exception E){
				System.out.println(E);
			}
		}
	}

	public static double[] generateHist(String S[]){
		try{
			double[] hist = new double[Keys.keys.length];
			int n=0;
			for(String t:S){
				boolean val = KeyGen.isSilly(t);
				if(!val){
					int i=0;
					for(String k:Keys.keys){
						if(t.equals(k)){
							hist[i] += 1;
							break;
						}
						i++;
					}
				}
			}
			for(int i=0;i<hist.length;i++){
				n += hist[i];
			}
			for(int i=0;i<hist.length;i++){
				if(hist[i]>0)
					hist[i]=hist[i]/n+1;
			}
            /*for(int i=0;i<hist.length;i++){
                System.out.print(hist[i] + " ");
            }*/
			return hist;
		}
		catch(Exception e){
			System.out.println(e);
			return null;
		}
	}
	
	public static void main(String[] args){

		int max=1000, count=0;
        int countp[] = {0,0};
		Keys.getKeys();

		String[] classes = {"Negetive", "Positive"};

		List<double[]> list = new ArrayList<>();
		double[] histogram = new double[1000];
		double[][] trainhist = new double[2][1000];
		try{
			FileInputStream fis = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			while ((line = br.readLine()) != null) {

				/* Input from file is dependent on format of source */
				String[] columndet = line.split("\t");
				columndet[0] = columndet[0].toLowerCase();
				int cl_type = (Integer.parseInt(columndet[1]));
				String delims = "[ -.,?!]+";
				//columndet[0]=columndet[0].replaceAll("not ","not_");
				String[] s = columndet[0].split(delims);               				
				histogram=generateHist(s);
                /*for(int i=0;i<1000;i++){
                    trainhist[cl_type][i]=(trainhist[cl_type][i]<histogram[i]?histogram[i]:trainhist[cl_type][i]);
                }*/
                for(int i=0;i<1000;i++){
                    trainhist[cl_type][i]+=histogram[i];
                }
                countp[cl_type]++;             
				if(count++>(90*max/100))
					break;					
			}
			
            for(int i=0;i<1000;i++){
                for(int j=0;j<2;j++){
                    //trainhist[j][i] = trainhist[j][i]/(count-1);
                    trainhist[j][i] = trainhist[j][i]/countp[j];
                }
            }
            
            //HistogramExample.main(trainhist[0],"Negetive Class");
            //HistogramExample.main(trainhist[1],"Positive Class");
            
			double ham_res=0, nbayes_res=0, euc_res=0, taxicab_res=0;
			double[][] test = new double[max-count][1000];
			count=0;
			int k=0;
			int[] exp_val = new int[max-count];
			while(( line = br.readLine())!=null){

				String[] columndet = line.split("\t");
				columndet[0] = columndet[0].toLowerCase();
				exp_val[k] = Integer.parseInt(columndet[1]);
				//columndet[0]=columndet[0].replaceAll("not ","not_");
				String delims = "[ -.,?!]+";
				String[] s = columndet[0].split(delims);
				test[k] = generateHist(s);
				k++;
			}
                        
			File file = new File("avg_results.txt");
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
				count=0;
				ham_res=0;
				euc_res=0;
				taxicab_res=0;
				nbayes_res=0;
				
				for(int i=0;i<k;i++){

					int idxmatch = knn.main(trainhist,test[i],"euclidian");
					if(exp_val[i]==idxmatch){					    
						euc_res++;
				    }

					idxmatch = knn.main(trainhist,test[i],"taxicab");
					if(exp_val[i]==idxmatch){
						taxicab_res++;
					}
					idxmatch = knn.main(trainhist,test[i],"hamming");
					if(exp_val[i]==idxmatch){
						ham_res++;
					}
					idxmatch = nbayes.main(trainhist,test[i]);
					if(exp_val[i]==idxmatch){
						nbayes_res++;
					}
					count++;
				}
				
				bw.write(" result for euclidian formula: " + (euc_res/count)*100 + "% \n");
				System.out.println(" result for euclidian formula: " + (euc_res/count)*100 + "% \n");
				bw.write(" result for taxicab   formula: " + (taxicab_res/count)*100 + "% \n");
				bw.write(" result for hamming   formula: " + (ham_res/count)*100 + "% \n\n");
				bw.write(" result for nbayes formula: " + (nbayes_res/count)*100 + "% \n");
				System.out.println(" result for nbayes formula: " + (nbayes_res/count)*100 + "% \n");

		
			br.close();
			bw.close();
		}
		catch(Exception E){
			System.out.println(E);
		}
	}
}
