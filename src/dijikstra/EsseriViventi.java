package dijikstra;

public abstract class EsseriViventi implements Entita{
	private int atk = 5, def = 5;
	private String nome;
	private int vita;
	private Item itemInMano;
	
	public EsseriViventi() { 
	}
	public abstract String toString();
	
	public int getVita() {
		return vita;
	}
	public void setVita(int vita) {
		this.vita = vita;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public Item getItemInMano() {
		return itemInMano;
	}
	public void setItemInMano(Item itemInMano) {
		this.itemInMano = itemInMano;
	}

}
