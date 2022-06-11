package dijikstra;

public class Arma extends Item{
	private int potenza;
	
	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}

	public Arma(String stringa, int potenza) {
		super(stringa);
		this.potenza = potenza;
	}

	public int getPotenza() {
		return potenza;
	}
	
	@Override
	public char simboloItem() {
		return 'A';
	}
}
