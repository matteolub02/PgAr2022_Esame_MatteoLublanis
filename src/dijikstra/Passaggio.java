package dijikstra;

public abstract class Passaggio implements Entita {

	public char charSimbolo() {
		return '-';
	}
	
	public abstract boolean portaAvanti();

}
