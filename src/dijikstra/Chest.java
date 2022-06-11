package dijikstra;

public class Chest implements Entita{
	
	private Item item;
	
	public Chest () {
		setItem(Equipment.ritornaItemCasuale());
	}
	
	public Item chestOpening() {
		return item;
	}
	
	public char charSimbolo() {
		return 'C';
	}

	public void setItem(Item item) {
		this.item = item;
	}
}
