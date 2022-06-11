package dijikstra;

import java.util.concurrent.TimeUnit;

public class UIManager {
	
	private static final String[] MODALITA = {"Casual", "Storia"};
	private static final String[] SCELTE = {"Apri inventario", "Vai avanti", "Vai indietro", "Vai a sinistra", "Vai a destra"};
	private static final String[] SCELTEINVENTARIO = {"Usa oggetto"};
	private static final int AVANTI = 1, INDIETRO = 2, SINISTRA = 3, DESTRA = 4;
	private static final MyMenu menuIniziale = new MyMenu("", MODALITA, "Scegli la modalità di gioco:");
	
	public static void menuIniziale() throws InterruptedException {
		System.out.println("--- A D V E N T U R E  T I M E ---\nBenvenuto Astar!");
		//TimeUnit.SECONDS.sleep(1);
		System.out.println("Dijikstra ha rapito di nuovo la principessa Kibo! "
				+ "Spetta a te salvarla e a riportare la pace.");
		//TimeUnit.SECONDS.sleep(1);
		switch (menuIniziale.scegli()) {
		case 1: //Crea matrice singola e si gioca su quella
			Partita partita = CreatorePartita.casual();
			inizioPartita(partita);
			break;
		case 2: //Crea più stanze e più livelli
			Partita partita2 = CreatorePartita.storia();
			inizioPartita(partita2);
			break;
		case 0:
			System.out.print("Terminazione.");
			for (int i = 0; i < 2; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.print("."); 
			}
			return;
		}
	}
	
	public static void inizioPartita(Partita partita) {
		MyMenu menuScelte = new MyMenu("", SCELTE, "Scegli cosa fare:");
		boolean finito = false, rimessaChest = true;
		int risultatoSpostamento = 1;
		Entita entita = new Vuoto(), tmp = new Vuoto();
		int[] posizioneGiocatoreSuCesta = {0, 0};
		do {
			partita.getLivelloAttuale().getStanzaAttuale().stampaMatrice();
			System.out.println(partita.getLivelloAttuale().getStanzaAttuale().getGiocatore().toString());
			if (risultatoSpostamento == 4) {
				posizioneGiocatoreSuCesta = partita.getLivelloAttuale().getStanzaAttuale().posizioneDiGiocatore();
				tmp = entita;
			}
		switch (Character.toUpperCase(menuScelte.scegliSuMappa())) {
			case '1':
				if (partita.getLivelloAttuale().getStanzaAttuale().getGiocatore().stampaInventario()) {
					MyMenu menuScelteInventario = new MyMenu("", SCELTEINVENTARIO, "Scegli cosa fare:");
					switch (menuScelteInventario.scegli()) {
					case 1:
						int numeroItem = InputDati.leggiIntero("Inserisci numero item:", 1, 6) - 1;
						partita.getGiocatore().usaItem(numeroItem);
						break;
					case 0:
						break;
					}
				}
				break;
			case 'W':
				entita = partita.movimento(AVANTI);
				risultatoSpostamento = partita.checkEntita(entita);
				break;
			case 'S':
				entita = partita.movimento(INDIETRO);
				risultatoSpostamento = partita.checkEntita(entita);
				break;
			case 'A':
				entita = partita.movimento(SINISTRA);
				risultatoSpostamento = partita.checkEntita(entita);
				break;
			case 'D':
				entita = partita.movimento(DESTRA);
				risultatoSpostamento = partita.checkEntita(entita);
				break;
			case '0':
				finito = true;
				break;
			}
		
		if (!tmp.equals(entita) && !rimessaChest) {
			partita.getLivelloAttuale().getStanzaAttuale().setPosizione(posizioneGiocatoreSuCesta[0], posizioneGiocatoreSuCesta[1], tmp);
			tmp = new Vuoto();
			rimessaChest = true;
		}

		switch (risultatoSpostamento) {
		case 0:
			System.out.println("Hai salvato la principessa Kibo! Ottimo lavoro, " + partita.getGiocatore().getNome() + "!");
			finito = true;
			break;
		case 1:
			break;
		case 2:
			System.out.println("Sei morto contro un mostro...");
			finito = true;
			break;
		case 3:
			System.out.println("Hai ucciso il mostro!");
			break;
		case 4:
			rimessaChest = false;
			break;
		}
		
		
		} while (!finito);
		
	}
}
