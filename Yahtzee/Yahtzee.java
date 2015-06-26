import dice.*;

public class Yahtzee {

	private static final int N_DICE = 5;
	private static final int DICE_SIZE = 20;
	
	public static void main(String[] args){
		
		Dice dice = new Dice(DICE_SIZE);
		int counter = 0;
		
		while(true){
			
			int[] rolls = dice.roll(N_DICE);
			
			print(rolls);
			
			if (isYahtzee(rolls)){
				
				System.out.println("Yahtzee!");
				System.out.println(String.format("Number of roll: %d", counter));
				
				break;
			}
			
			counter++;
			
		}
		
	}
	
	private static void print(int[] rolls){
		
		StringBuilder sb = new StringBuilder();
		
		for (int roll: rolls){
			sb.append(Integer.toString(roll));
			sb.append(" ");
		}
		
		System.out.println(sb);
		
	}
	
	private static boolean isYahtzee(int[] rolls){
		
		int firstRoll = rolls[0];
		
		for (int roll: rolls){
			if (roll != firstRoll){
				return false;
			}
		}
		
		return true;
		
	}
	
}
