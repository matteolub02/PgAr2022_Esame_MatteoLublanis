package dijikstra;

public class Pozione extends Item{
	
	public Pozione(String stringaPozione) {
		super(stringaPozione);
	}
	
	@Override
	public char simboloItem() {
		return 'P';
	}
}
