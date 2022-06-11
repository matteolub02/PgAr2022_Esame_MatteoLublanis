package dijikstra;

import java.util.Random;

public class Mostro extends EsseriViventi{
	
	Arma armaMostro;
	
	public Arma getArmaMostro() {
		return armaMostro;
	}

	public Mostro() {
		super();
		setItemInMano(Equipment.ritornaArmaCasuale());
		armaMostro = Equipment.ritornaArmaCasuale();
		Random rand = new Random();
		setVita(rand.nextInt(15, 25));
		setNome(Utils.permutazioniDijikstra());
	}
	
	public String toString() {
		return ("-------\n"
				+ "Mostro: " + getNome() + 
				"\nVita: " + getVita() + " - ATK: " + getAtk() + " - DEF: " + getDef() 
				+ "\nOggetto in mano: " + getItemInMano().getStringaDescrittiva() + "\n-------");
	} 
	
	public char charSimbolo() {
		return 'M';
	}
	
	public void riceviDanno (int danno) {
		setVita(getVita() - danno);
	}
	
	public int getPotenzaItemInMano() {
		if (getItemInMano().simboloItem() == 'A') return ((Arma) getItemInMano()).getPotenza();
		else return 1;
	}
}
