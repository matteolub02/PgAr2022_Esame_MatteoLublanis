package dijikstra;

import java.util.LinkedList;

/**
 * @author Matteo Lublanis
 * Partita, gestisce lotta, movimento
 * 
 *
 */
public class Partita {
	
	private static final int CASO_MORTE_CONTRO_MOSTRO = 2;
	private static final int ERRORE = -2;
	private static final int TROVATA_KIBO = 0;
	private static final int UCCISIONE_MOSTRO = 3;
	private static final int CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO = 4;

	private static final int CASO_DEFAULT = 1;

	private int livelloAttuale = 0;

	private LinkedList<Livello> mappa;
	
	public Partita(LinkedList<Livello> mappa) {
		this.mappa = mappa;
	}
	
	public Entita movimento (int verso) { //In base all'input controlla se si pu? spostare il giocatore
		int[] posizioneGiocatore = getLivelloAttuale().getStanzaAttuale().posizioneDiGiocatore();
		switch (verso) {
		case 1:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0]+1, posizioneGiocatore[1])) System.out.println("C'? un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0]+1, posizioneGiocatore[1]);
			break;
		case 2:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0]-1, posizioneGiocatore[1])) System.out.println("C'? un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0]-1, posizioneGiocatore[1]);
			break;
		case 3:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0], posizioneGiocatore[1]-1)) System.out.println("C'? un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0], posizioneGiocatore[1]-1);
			break;
		case 4:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0], posizioneGiocatore[1]+1)) System.out.println("C'? un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0], posizioneGiocatore[1]+1);
			break;
		}
		return new Vuoto();
	}
	
	public int checkEntita(Entita entita) { //Ritorna entit? da salvare come tmp per evitare che scompaia dopo il passaggio del player
		switch (entita.charSimbolo()) {
		case 'C': //CHEST
			Item item = ((Chest) entita).chestOpening();
			if (InputDati.yesOrNo("Hai trovato una chest! Vuoi aprirla?")) {
				System.out.println("Item trovato: " + item.getStringaDescrittiva());
				if (getGiocatore().aggiungiAInventario(item)) {
					System.out.println("Item aggiunto all'inventario!");
					return CASO_DEFAULT;
				}
				else {
					System.out.println("Inventario pieno!");
				}
			}
			else {
				return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
			}
			break;
		case 'M': //MOSTRO
			if (faseLotta((Mostro)entita)) {
				return UCCISIONE_MOSTRO;
			}
			else {
				return CASO_MORTE_CONTRO_MOSTRO;
			}
		case 'K': //KIBO
			return TROVATA_KIBO;
		case '-': //PASSAGGIO
			Passaggio passaggio = (Passaggio)entita;
			if (passaggio.portaAvanti()) {
				if (getLivelloAttuale().vaAvantiStanza()) {
					return CASO_DEFAULT;
				}
				else return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
			}
			else {
				if (getLivelloAttuale().tornaStanzaPrecedente()) {
					return CASO_DEFAULT;
				}
				else return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
			}
		case 't': //SCALE SCESA
			if (livelloAttuale > 0) {
					getLivelloAttuale().getStanzaAttuale().setPosizione(CreatorePartita.RIGA_SCALE, CreatorePartita.COLONNA_SCALE, new ScaleScesa());
					getLivelloAttuale().getStanzaAttuale().spostaGiocatore(CreatorePartita.RIGA_SCALE+1, CreatorePartita.COLONNA_SCALE+1);
					livelloAttuale -= 1;
					System.out.println("Sei sceso di livello!");
					return CASO_DEFAULT;
			}
			else {
				System.out.println("Non ci sono livelli sottostanti!");
				return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
			}
		case 'T': //SCALE SALITA
			if (livelloAttuale + 1 < CreatorePartita.LIVELLO_FINALE) {
					if (getGiocatore().isBattutoMiniBoss()) {
						getLivelloAttuale().getStanzaAttuale().setPosizione(CreatorePartita.RIGA_SCALE, CreatorePartita.COLONNA_SCALE, new ScaleSalita());
						getLivelloAttuale().getStanzaAttuale().spostaGiocatore(CreatorePartita.RIGA_SCALE+1, CreatorePartita.COLONNA_SCALE+1);
						livelloAttuale += 1;
						System.out.println("Sei salito di livello!");
						return CASO_DEFAULT;
					}
					else {
						System.out.println("Devi battere il miniboss prima!");
						return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
					}
			}
			else {
				System.out.println("Non ci sono altri livelli soprastanti!");
				return CASO_PLAYER_CHEST_NON_APERTA_O_PASSAGGIO;
			}
		}
		
			
		return ERRORE;
	}
	
	public Entita spostaGiocatore(int riga, int colonna) {
		return getLivelloAttuale().getStanzaAttuale().spostaGiocatore(riga, colonna);
	}
	
	public boolean muroCasellaSpostamentoOLimite (int riga, int colonna) {
		if (	riga < 0 ||
				colonna < 0 ||
				getLivelloAttuale().getStanzaAttuale().getStanza().length - 1 < (riga) ||
				getLivelloAttuale().getStanzaAttuale().getStanza().length - 1 < (colonna) ||
				getLivelloAttuale().getStanzaAttuale().entitaInCasellaDesiderata(riga,
				colonna) == '#') return true;
		return false;
	}
	
	public void stampaInventario () {
		mappa.get(livelloAttuale);
	}
	
	public Livello getLivelloAttuale() {
		return mappa.get(livelloAttuale);
	}

	public void setLivelloAttuale(int livelloAttuale) {
		this.livelloAttuale = livelloAttuale;
	}
	
	public boolean faseLotta(Mostro mostro) { //GESTISCE LOTTA
		int turno = 0; //0 giocatore, 1 mostro
		boolean finito = false;
		boolean isMiniboss = false;
		if (mostro.getVita() == 30) isMiniboss = true; 
		do {
			switch (turno) {
			case 0: //GIOCATORE
				System.out.println(getGiocatore().getNome() + " colpisce " + mostro.getNome()  + " con " + (getGiocatore().getItemInMano()).getStringaDescrittiva());
				mostro.riceviDanno(Utils.danno(getGiocatore().getAtk(), getGiocatore().getAtk(), getGiocatore().getPotenzaItemInMano()));
				if (mostro.getVita() <= 0) {
					getGiocatore().setBattutoMiniBoss(isMiniboss);
					return true;
				}
				turno = 1;
				break;
			case 1: //MOSTRO
				System.out.println(mostro.getNome()  + " colpisce " + getGiocatore().getNome()  + " con " + (mostro.getItemInMano()).getStringaDescrittiva());
				getGiocatore().riceviDanno(Utils.danno(mostro.getAtk(), mostro.getDef(), mostro.getArmaMostro().getPotenza()));
				if (getGiocatore().getVita() <= 0) {
					return false;
				}
				turno = 0;
				break;
			}
			System.out.println(getGiocatore().toString() + "\n" + mostro.toString());
		} while (!finito);
		
		return false;
	}
	
	public Giocatore getGiocatore() {
		return getLivelloAttuale().getStanzaAttuale().getGiocatore();
	}
	

}
