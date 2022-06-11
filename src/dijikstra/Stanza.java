package dijikstra;

public class Stanza {
	private Entita[][] stanza;
	private Giocatore giocatore;
	private Stanza prossimo;
	
	public Stanza (Entita[][] stanza, Giocatore giocatore) {
		this.setStanza(stanza);
		this.giocatore = giocatore;
	}
	
	public void stampaMatrice() {
		for (int i = 0; i < stanza.length; i ++) {
			for (int j = 0; j < stanza.length; j++) {
				if (stanza[i][j] != null) System.out.print(stanza[i][j].charSimbolo());
				else System.out.print("*");
			}
			System.out.println();
		}
	}
	
	public void setProssimo (Stanza prossimo) {
		this.prossimo = prossimo;
	}
	
	public Stanza getProssimo () {
		return prossimo;
	}

	public Entita[][] getStanza() {
		return stanza;
	}
	
	public char entitaInCasellaDesiderata(int i, int j) {
		return stanza[i][j].charSimbolo();
	}
	
	public int[] posizioneDiGiocatore() {
		for (int i = 0; i < stanza.length; i++) {
			for (int j = 0; j < stanza.length; j++) {
				if (stanza[i][j].equals(giocatore)) {
					int[] posizione = {i, j};
					return posizione;
				}
			}
		}
		int[] nontrovato = {0, 0};
		return nontrovato;
	}
	
	public Entita spostaGiocatore(int riga, int colonna) {
		int[] posizioneDaRendereVuota = posizioneDiGiocatore();
		Entita entitaPrecedente = stanza[riga][colonna];
		stanza[posizioneDaRendereVuota[0]][posizioneDaRendereVuota[1]] = new Vuoto();
		stanza[riga][colonna] = giocatore;
		return entitaPrecedente;
	}
	
	public void setPosizione (int riga, int colonna, Entita entita) {
		stanza[riga][colonna] = entita;
	}
	
	public void setStanza(Entita[][] stanza) {
		this.stanza = stanza;
	}
	
	public Giocatore getGiocatore() {
		return giocatore;
	}
	
}
