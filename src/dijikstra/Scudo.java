package dijikstra;

public class Scudo extends Item{
	
	private int difesa = 5;
	
	public Scudo(String stringaScudo) {
		super(stringaScudo);
	}

	public int getDifesa() {
		return difesa;
	}

	public void riceviDanno(int attaccoSubito) {
		difesa -= attaccoSubito;
	}
	
	@Override
	public char simboloItem() {
		return 'S';
	}
}
