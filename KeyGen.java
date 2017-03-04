import java.io.*;
import java.util.Arrays;
import java.util.Comparator;


class Key{
	String key;
	int freq; /*Frequency of every key word in source text*/
}

/* Used to sort Key in decreasing order of frequency */
class CompareKey implements Comparator<Key>{
	public int compare(Key k1, Key k2){
		return k2.freq - k1.freq;
	}
}

class KeyGen{

	/* Method to check if token already exist in the array */
	static int exists(Key[] keys,String s){
		int i=0;
		for(Key k : keys){
			if(s.equals(k.key))
				return i;
			i++;
		}
		return -1;
	}

	public static boolean isSilly(String s){
		try{
			FileInputStream fis = new FileInputStream("negetivekeywords.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			line = br.readLine();
			line = line.toLowerCase();
			String[] nwords = line.split(" ");
			for(String n:nwords){
				if(s.equals(n)){
					return true;
				}
			}
			br.close();
		}
		catch(Exception E){
			System.out.println(E);
		}
		return false;
	}

	public static void main(String[] args){

		/* Intialize the Key array */
		Key[] keys = new Key[4000];
		for(int j=0;j<keys.length;j++){
			keys[j] = new Key();
		}

		try{
			FileInputStream fis = new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			int cl_type, i=0, j;
			String line = null;

			while ((line = br.readLine()) != null) {

				/* Input from file is dependent on format of source */
				String[] columndet = line.split("\t");
				columndet[0] = columndet[0].toLowerCase();
				//System.out.println("String: " + columndet[0]);
				cl_type=Integer.parseInt(columndet[1]);
				//System.out.println("Class Type:" + cl_type);
				String delims = "[ -.,?!]+";
				columndet[0]=columndet[0].replaceAll("not ","not_");
				String[] tokens = columndet[0].split(delims);

				/* token[] stores the array of Keywords */ 
				for(String s : tokens){
					if(s.length()>1 && !isSilly(s)){
						if(s.length()>1 && (i==0 || exists(keys,s)==-1) ){
							//System.out.println(s + s.length());
							keys[i].key=s;
							keys[i].freq += 1;
							i++;
						}
						else{
							keys[exists(keys,s)].freq += 1;
						}
					}
				}
			}

			/* sort token[] in decreasing order of frequency */
			Arrays.sort(keys,new CompareKey());

			/* write into a new file */
			File file = new File("positivekeywords.txt");
			file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			i=Integer.parseInt(args[1]);
			for(Key k : keys){
				if(k.freq>0 && i>0){
					bw.write(k.key+" ");
					i--;
				}
				else
					break;
			}

			br.close();
			bw.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
