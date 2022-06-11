package dijikstra;

public class MiniBoss extends Mostro{
	public MiniBoss() {
		super();
		setAtk(7);
		setVita(30);
	}
	
	public String toString() {
		return ("-------\n"
				+ "Miniboss: " + getNome() + 
				"\nVita: " + getVita() + " - ATK: " + getAtk() + " - DEF: " + getDef() 
				+ "\nOggetto in mano: " + getItemInMano().getStringaDescrittiva() + "\n-------");
	} 
	
	
}
