public class Card{

	private String name;
	private int[] stats;
	private Player owner;

	public Card(String name, int[] stats){
		this.name = name;
		this.stats = stats;
		this.owner = null;
	}

	public void setOwner(Player newOwner){
		this.owner = newOwner;
	}

	public Player getOwner(){
		return this.owner;
	}

	public String getName(){
		return this.name;
	}

	public int getStat(int i){
		return stats[i];
	}

	public String toString(){

	}


}