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
	
	public Stanza getStanzaAttuale () {
		return stanze.get(stanzaAttuale);
	}
}
