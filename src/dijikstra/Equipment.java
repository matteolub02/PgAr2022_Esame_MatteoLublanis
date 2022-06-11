package dijikstra;

import java.util.ArrayList;
import java.util.Random;

public class Equipment { //Contiene tutti gli item disponibili del gioco
	
	private static ArrayList<Item> equipment = new ArrayList<>();
	
	public static void generaItem() {
		Random rand = new Random();
		for (int i = 0; i < 40; i++) {
			Item spada = new Arma("Spada", rand.nextInt(35, 56));
			equipment.add(spada);
		}
		for (int i = 0; i < 25; i++) {
			Item pozione = new Pozione("Pozione curativa");
			equipment.add(pozione);
		}
		for (int i = 0; i < 35; i++) {
			Item scudo = new Scudo("Scudo protettivo");
			equipment.add(scudo);
		}
	}
	
	public static Item ritornaItemCasuale() {
		Random rand = new Random();
		return equipment.get(rand.nextInt(0, equipment.size()));
	}
	
	public static Arma ritornaArmaCasuale() {
		for ( ; ; ) {
			Random rand = new Random();
			int i = rand.nextInt(0, equipment.size());
			if (equipment.get(i).simboloItem() == 'A') return (Arma)equipment.get(i);
		}
	}
	
}
