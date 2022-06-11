package dijikstra;

public abstract class Passaggio implements Entita { //Corridoi tra classi

	public char charSimbolo() {
		return '-';
	}
	
	public abstract boolean portaAvanti();

}
