import javafx.scene.image.*;

public class Card implements Comparable<Card>{
	
	
	
	private static final String IMAGE_FOLDER_DIR = "image";
	private static final String IMAGE_FORMAT = ".png";
	private static final String BACK_IMAGE_DIR = ("image/back_image.png");
	
	private Image cardImage;
	private Image backImage;
	
	private SuitEnum suit;
	private RankEnum rank;
	
	
	public Card(){
		
	}		
	
	public Card(SuitEnum suit, RankEnum rank){
		this.suit = suit;
		this.rank = rank;
	
		String location = generateImageLocation();
		
		try {
			cardImage = new Image(location);
			
		} catch (Exception ex) {
			System.out.println(String.format("cannot load image from: (%s)", location));		
			cardImage = null;
		}
		
		try {
			backImage = new Image(BACK_IMAGE_DIR);
		} catch (Exception ex){
			System.out.println(String.format("cannot load image from: (%s)", BACK_IMAGE_DIR));
			backImage = null;
		}
	}
		
	public SuitEnum getSuit() {
		return suit;
	}



	public RankEnum getRank() {
		return rank;
	}



	public Image getCardImage(){
		return cardImage;
	}
	
	private String generateImageLocation(){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(IMAGE_FOLDER_DIR);
		sb.append("/");
		
		sb.append(suit.toString());
		sb.append("_");
		sb.append(rank.toString());
		
		sb.append(IMAGE_FORMAT);
		
		
		
		return sb.toString().toLowerCase();
		
	}
	
	@Override
	public String toString(){
		return (suit + " " + rank);
	}
		
	public int compareTo(Card card) {
		
		if (this.rank.compareTo(card.rank) > 0){
			return 1;
		} else if (this.rank.compareTo(card.rank) < 0){
			return -1;
		} else {
			
			if(this.suit.compareTo(card.suit) > 0){
				return 1;
			} else if (this.suit.compareTo(card.suit) < 0){
				return -1;
			} else {
				return 0;
			}
			
		}
		
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}

	public Image getBackImage() {
		return backImage;
	}
	
	
	/*
	 @Override
	public int compareTo(Object o){
						
		if (o instanceof Card){
			Card card = (Card) o;
			return this.compareTo(card);
		} else {
			throw new IllegalArgumentException("error");
		}
		
		
		
	}
	
	change compareTo(card) to private
	 */

}
