package dijikstra;

public class Item { //SPADA, SCUDO, POZIONE
	private String stringaDescrittiva;
	
	public Item(String stringaDescrittiva) {
		this.stringaDescrittiva = stringaDescrittiva;
	}
	
	public String getStringaDescrittiva() {
		return stringaDescrittiva;
	}

	public void setStringaDescrittiva(String stringaDescrittiva) {
		this.stringaDescrittiva = stringaDescrittiva;
	}
	
	public char simboloItem () {
		return 'I';
	}
}
