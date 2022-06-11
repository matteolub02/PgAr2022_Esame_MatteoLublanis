package dijikstra;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {
	
	private final static String DIJIKSTRA = "DIJIKSTRA";
	
	
   public static String permutazioniDijikstra() { 
	     char[] result = DIJIKSTRA.toCharArray();
	   
	     int n = result.length;
	     while (n > 0) {
	         int v = ThreadLocalRandom.current().nextInt(n);
	         char temp = result[v];
	         result[v] = result[n - 1];
	         result[n - 1] = temp;
	         n--;
	     }
	     return new String(result);
   	}
   
   public static int danno(int attacco, int difesa, int potenza) {
	   Random rand = new Random();
	   double modificatore = 1;
	   if (rand.nextDouble(1, 101) < 7.5) modificatore = 1.5;
	   return (int)Math.floor((((2*potenza*attacco)/(25*difesa)) + 2)*modificatore);
   }
	
}
