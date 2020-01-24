public class Deck{
	
	private final String filePath = "StarCitizenDeck.txt";
	private ArrayList<Card> deck = new ArrayList<Card>();

	public Deck(String filePath){
		FileReader fr = null;

		try{
			fr = new FileReader(filePath);
			Scanner s = new Scanner(fr);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public void shuffle(){
		Collections.shuffle(this.deck);
	}
}