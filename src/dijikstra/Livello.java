package dijikstra;

import java.util.LinkedList;

public class Livello {
	
	private LinkedList<Stanza> stanze;
	private int stanzaAttuale = 0;
	
	public Livello (LinkedList<Stanza> stanze) {
		this.setStanze(stanze);
	}

	public LinkedList<Stanza> getStanze() {
		return stanze;
	}

	public void setStanze(LinkedList<Stanza> stanze) {
		this.stanze = stanze;
	}
	
	public boolean tornaStanzaPrecedente() {
		if (stanzaAttuale > 0) {
			stanzaAttuale -= 1;
			System.out.println("Stanza precedente!");
			return true;
		}
		else System.out.println("Non c'è nessuna stanza prima di questa!");
		return false;
	}
	public boolean vaAvantiStanza() {
		if (stanzaAttuale < stanze.size() - 1) {
			stanzaAttuale += 1;
			System.out.println("Stanza successiva!");
			return true;
		}
		else System.out.println("Questa è l'ultima stanza del livello!");
		return false;
	}
	
	public Stanza getStanzaAttuale () {
		return stanze.get(stanzaAttuale);
	}
}
