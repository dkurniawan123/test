package dice;

import java.util.Random;

public class Dice {

	private int size;
	
	public Dice(){
		this(6);
	}
	
	public Dice(int size){
		
		if (size <= 0){
			throw new InvalidDiceException();
		}
		
		this.size = size;
		
	}
	
	public int roll(){
		
		Random rng = new Random();
		return (rng.nextInt(size) + 1);
		
	}
	
	public int[] roll(int numberOfRoll){
		
		int[] rolls = new int[numberOfRoll];
		
		for (int i = 0; i < numberOfRoll; i++){
			rolls[i] = roll();
		}
		
		return rolls;
		
	}
	
	public String toString(){
		
		return String.format("D-%d", size);
	}
	
}
