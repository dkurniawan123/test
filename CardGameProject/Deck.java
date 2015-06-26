import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class Deck {

	private static final String BACK_IMAGE_DIR = ("image/back_image.png");
	
	private Image backImage;

	
	private ArrayList<Card> deck = new ArrayList<>();
	
	public Deck(){
		
		for (SuitEnum suit: SuitEnum.values()){
			for (RankEnum rank : RankEnum.values()){
				
				deck.add(new Card(suit, rank));
				
			}
		}
		
		backImage = new Image(BACK_IMAGE_DIR);
		
	}
	
	//shuffle deck
	public void shuffle(){
		
		ArrayList<Card> tempOldDeck = new ArrayList<>();
		
		for (Card card: deck){
			tempOldDeck.add(card);
		}
		
		int[] randomPermutation = generateRandomPermutation(deck.size());
		
		for (int i = 0; i < deck.size(); i++){	
			deck.set(i, tempOldDeck.get(randomPermutation[i] - 1));
		}		
		
	}
	
	private int[] generateRandomPermutation(int high){
		return generateRandomPermutation(1, high);
	}
	
	private int[] generateRandomPermutation(int low, int high){
		
		ArrayList<Integer> unselectedNumber = new ArrayList<>();
		
		for (int i = low; i <= high; i++){
			unselectedNumber.add(i);
		}
		
		Random rng = new Random();
		int[] randomPermutation = new int[high - low + 1];
		
		for (int i = 0; i < randomPermutation.length; i++){
			int randomIndex = rng.nextInt(unselectedNumber.size());
			randomPermutation[i] = unselectedNumber.get(randomIndex);
			unselectedNumber.remove(randomIndex);
		}
		
		return randomPermutation;
	}
	
	//draw top most
	public Card draw() throws EmptyDeckException{
		
		if (deck.size() > 0){
			Card drawnCard = deck.get(deck.size() - 1);
			deck.remove(deck.size() - 1);
			return drawnCard;
		} else {
			throw new EmptyDeckException();
		}
		
	}
	
	//get deck size
	public int size(){
		return deck.size();
	}
	
	public Image getBackImage(){
		
		return backImage;
		
		
	}
	
}
