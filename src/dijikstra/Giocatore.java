package dijikstra;

import java.util.ArrayList;

public class Giocatore extends EsseriViventi{ //Rappresenta il player, nome di default Astar
	
	private final static int MAX_ITEM = 6;
	private ArrayList<Item> inventario = new ArrayList<Item>() ;
	private Item pugni = new Item("Pugni");
	private boolean battutoMiniBoss = false;
	
	public Giocatore() {
		super();
		setVita(20);
		setAtk(3000);
		setNome("Astar");
		setItemInMano(new Arma("Pugni", 3000));
	}
	
	public String toString() {
		return ("-------\n"
				+ getNome() + 
				"\nVita: " + getVita() + " - ATK: " + getAtk() + " - DEF: " + getDef() 
				+ "\nOggetto in mano: " + getItemInMano().getStringaDescrittiva() + "\n-------");
	}
	
	public boolean aggiungiAInventario (Item item) { //Aggiungi a inventario
		if (inventario.size() < MAX_ITEM) {
			if (inventario.size() == 0) {
				if (item.simboloItem() == 'A') { //se è una spada viene automaticamente sostituita ai pugni
					setItemInMano(item);
					inventario.add(item);
				}
				else inventario.add(item);
			}
			else inventario.add(item);
			return true;
		}
		else return false;
	}
	
	public void rompiScudo (Scudo scudo) { //Rompe scudo quando prende troppi danni
		inventario.remove(scudo);
		setItemInMano(pugni);
	}
	
	public void usaItem(int numItem) { //Usa item in base al char di controllo
		if (numItem > inventario.size() - 1) {
			System.out.println("Non hai così tanti item!");
			return;
		}
		else switch (inventario.get(numItem).simboloItem()) {
		case 'A':
			setItemInMano(inventario.get(numItem));
			return;
		case 'S':
			setItemInMano(inventario.get(numItem));
			return;
		case 'P':
			usaPozione();
			inventario.remove(numItem);
			return;
		}
	}
	public void usaPozione() { //Usa pozione
		if (getVita() + 10 < 20) setVita(getVita() + 10);
		else setVita(20);
	}
	
	public boolean stampaInventario() { //stampa item nell'inventario
		if (inventario.size() > 0) {
			for (Item e : inventario) System.out.print(e.getStringaDescrittiva() + " - ");
			System.out.println();
			return true;
		}
		else System.out.println("Hai solo i tuoi pugni con te!");
		return false;
	}
	
	public void riceviDanno(int danno) { //Diminuisce vita del giocatore in base all'item attivo
		if (getItemInMano().simboloItem() == 'S') {
			Scudo scudo = (Scudo)getItemInMano();
			scudo.riceviDanno(danno);
			if (scudo.getDifesa() < 0) {
				rompiScudo(scudo);
				System.out.println("Scudo rotto!");
				setVita(getVita() + scudo.getDifesa());
			}
			else System.out.println("Lo scudo ti ha difeso, resistenza attuale: " + scudo.getDifesa());
		}
		else setVita(getVita() - danno);
	}
	
	public ArrayList<Item> getInventario() { //Ritorna inventario
		return inventario;
	}
	
	public char charSimbolo() {
		return 'O';
	}
	
	public char tipoItemInMano() {
		return getItemInMano().simboloItem();
	}
	
	public int getPotenzaItemInMano() {
		if (getItemInMano().simboloItem() == 'A') return ((Arma) getItemInMano()).getPotenza();
		else return 1;
	}

	public boolean isBattutoMiniBoss() {
		return battutoMiniBoss;
	}

	public void setBattutoMiniBoss(boolean battutoMiniBoss) {
		this.battutoMiniBoss = battutoMiniBoss;
	}
}
