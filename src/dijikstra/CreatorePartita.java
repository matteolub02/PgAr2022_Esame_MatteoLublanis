package dijikstra;

import java.util.LinkedList;
import java.util.Random;

public class CreatorePartita {
	private final static int SIZE_DEFAULT = 10;
	public static Partita casual() {
		Partita partita;
		Equipment.generaItem();
		Stanza stanza = creaStanzaConKiboCasuale(SIZE_DEFAULT, SIZE_DEFAULT);
		LinkedList<Stanza> stanze = new LinkedList<Stanza>(); 
		stanze.add(stanza);
		LinkedList<Livello> mappa = new LinkedList<Livello> ();
		mappa.add(new Livello(stanze));
		partita = new Partita(mappa);
		return partita;
	}
	
	public static Stanza creaStanzaConKiboCasuale(int size_righe, int size_colonne) {
		Entita[][] mappa = new Entita[size_righe][size_colonne];
		for (int i = 0; i < size_righe; i++) {
			for (int j = 0; j < size_colonne; j++) mappa[i][j] = new Vuoto();
		}
		Random rand = new Random();
		Giocatore giocatore = new Giocatore();
		mappa[rand.nextInt(0, size_righe)][rand.nextInt(0, size_colonne)] = giocatore;
		Entita kibo = new Kibo();
		boolean finito = false;
		do {
			int riga = rand.nextInt(0, size_righe), colonna = rand.nextInt(0, size_colonne);
			if (checkEmpty(mappa, riga, colonna)) {
				mappa[riga][colonna] = kibo;
				finito = true;
			}
		} while (!finito);
		
		int numeroMostri = rand.nextInt(1, 5);
		
		for (int i = 0; i < numeroMostri; i++) {
			Mostro mostro = new Mostro(Equipment.ritornaArmaCasuale());
			finito = false;
			do {
				int riga = rand.nextInt(0, size_righe), colonna = rand.nextInt(0, size_colonne);
				if (checkEmpty(mappa, riga, colonna)) {
					mappa[riga][colonna] = mostro;
					finito = true;
				}
			} while (!finito);
		}
		
		int numeroChest = rand.nextInt(1, 3);
		for (int i = 1; i < numeroChest; i++) {
			Chest chest = new Chest();
			finito = false;
			do {
				int riga = rand.nextInt(0, size_righe), colonna = rand.nextInt(0, size_colonne);
				if (checkEmpty(mappa, riga, colonna)) {
					mappa[riga][colonna] = chest;
					finito = true;
				}
			} while (!finito);
		}
		
		int numeroMuri = rand.nextInt(3, 10);
		for (int i = 0; i <numeroMuri; i++) {
			Muro muro = new Muro();
			finito = false;
			do {
				int riga = rand.nextInt(0, size_righe), colonna = rand.nextInt(0, size_colonne);
				if (checkEmpty(mappa, riga, colonna)) {
					mappa[riga][colonna] = muro;
					finito = true;
				}
			} while (!finito);
		}
		
		return new Stanza(mappa, giocatore);
	}
	
	public static boolean checkEmpty(Entita[][] mappa, int riga, int colonna) {
		return (mappa[riga][colonna] instanceof Vuoto);
	}
}
