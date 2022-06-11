package dijikstra;

import java.util.LinkedList;

public class Partita {
	
	private static final int CASO_MORTE_CONTRO_MOSTRO = 2;
	private static final int ERRORE = -2;
	private static final int TROVATA_KIBO = 0;
	private static final int UCCISIONE_MOSTRO = 3;
	private static final int CASO_PLAYER_CHEST_NON_APERTA = 4;

	private static final int CASO_DEFAULT = 1;

	private int livelloAttuale = 0;

	private LinkedList<Livello> mappa;
	
	public Partita(LinkedList<Livello> mappa) {
		this.mappa = mappa;
	}
	
	public Entita movimento (int verso) {
		int[] posizioneGiocatore = getLivelloAttuale().getStanzaAttuale().posizioneDiGiocatore();
		switch (verso) {
		case 1:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0]+1, posizioneGiocatore[1])) System.out.println("C'è un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0]+1, posizioneGiocatore[1]);
			break;
		case 2:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0]-1, posizioneGiocatore[1])) System.out.println("C'è un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0]-1, posizioneGiocatore[1]);
			break;
		case 3:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0], posizioneGiocatore[1]-1)) System.out.println("C'è un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0], posizioneGiocatore[1]-1);
			break;
		case 4:
			if (muroCasellaSpostamentoOLimite(posizioneGiocatore[0], posizioneGiocatore[1]+1)) System.out.println("C'è un muro in quella casella!");
			else return spostaGiocatore(posizioneGiocatore[0], posizioneGiocatore[1]+1);
			break;
		}
		return new Vuoto();
	}
	
	public int checkEntita(Entita entita) {
		switch (entita.charSimbolo()) {
		case 'C':
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
				return CASO_PLAYER_CHEST_NON_APERTA;
			}
			break;
		case 'M':
			if (faseLotta((Mostro)entita)) {
				return UCCISIONE_MOSTRO;
			}
			else {
				return CASO_MORTE_CONTRO_MOSTRO;
			}
		case 'K':
			return TROVATA_KIBO;
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
	
	public boolean faseLotta(Mostro mostro) {
		int turno = 0; //0 giocatore, 1 mostro
		boolean finito = false;

		do {
			switch (turno) {
			case 0:
				System.out.println(getGiocatore().getNome() + " colpisce " + mostro.getNome()  + " con " + (getGiocatore().getItemInMano()).getStringaDescrittiva());
				mostro.riceviDanno(Utils.danno(getGiocatore().getAtk(), getGiocatore().getAtk(), getGiocatore().getPotenzaItemInMano()));
				if (mostro.getVita() <= 0) {
					return true;
				}
				turno = 1;
				break;
			case 1:
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
