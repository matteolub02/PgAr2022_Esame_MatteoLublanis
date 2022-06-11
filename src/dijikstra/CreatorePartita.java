package dijikstra;

import java.util.LinkedList;
import java.util.Random;

public class CreatorePartita {
	private final static int SIZE_DEFAULT = 15;
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
	
	public static Partita storia() {
		Equipment.generaItem();
		LinkedList<Stanza> stanze = new LinkedList<Stanza>(); 
		Random rand = new Random();
		int numeroStanze = rand.nextInt(5, 8);
		for (int i = 0; i < numeroStanze - 1; i++) {
			Stanza stanza = creaStanzaNormale();
			stanze.add(stanza);
		}
		Stanza ultimaStanza = creaStanzaConKibo();
		stanze.add(ultimaStanza);
		LinkedList<Livello> mappa = new LinkedList<Livello> ();
		mappa.add(new Livello(stanze));
		return new Partita(mappa);
	}
	
	
	public static Stanza creaStanzaNormale() {
		Entita[][] mappa = new Entita[SIZE_DEFAULT][SIZE_DEFAULT];
		for (int i = 0; i < SIZE_DEFAULT; i++) {
			for (int j = 0; j < SIZE_DEFAULT; j++) mappa[i][j] = new Vuoto();
		}
		mappa[14][7] = new PassaggioStanzaPrecedente();
		mappa[0][7] = new PassaggioStanzaSuccessiva();
		Random rand = new Random();
		Giocatore giocatore = new Giocatore();
		mappa[14][8] = giocatore;
		boolean finito = false;

		int numeroMostri = rand.nextInt(1, 5);

		for (int i = 0; i < numeroMostri; i++) {
			Mostro mostro = new Mostro(Equipment.ritornaArmaCasuale());
			finito = false;
			do {
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
				if (checkEmpty(mappa, riga, colonna)) {
					mappa[riga][colonna] = mostro;
					finito = true;
				}
			} while (!finito);
		}

		int numeroChest = rand.nextInt(1, 4);
		for (int i = 1; i < numeroChest; i++) {
			Chest chest = new Chest();
			finito = false;
			do {
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
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
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
				if (checkEmpty(mappa, riga, colonna)) {
					mappa[riga][colonna] = muro;
					finito = true;
				}
			} while (!finito);
		}

		return new Stanza(mappa, giocatore);
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
	
	public static Stanza creaStanzaConKibo() {
		Entita[][] mappa = new Entita[SIZE_DEFAULT][SIZE_DEFAULT];
		for (int i = 0; i < SIZE_DEFAULT; i++) {
			for (int j = 0; j < SIZE_DEFAULT; j++) mappa[i][j] = new Vuoto();
		}
		mappa[14][7] = new PassaggioStanzaPrecedente();
		
		Random rand = new Random();
		Giocatore giocatore = new Giocatore();
		mappa[14][8] = giocatore;
		Entita kibo = new Kibo();
		boolean finito = false;
		do {
			int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
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
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
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
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
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
				int riga = rand.nextInt(0, SIZE_DEFAULT), colonna = rand.nextInt(0, SIZE_DEFAULT);
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
