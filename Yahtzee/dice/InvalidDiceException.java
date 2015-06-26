package dice;

public class InvalidDiceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDiceException(){
		super();
	}
	
	public InvalidDiceException(String str){
		super(str);
	}

	public InvalidDiceException(Throwable cause){
		super(cause);
	}

	public InvalidDiceException(String str, Throwable cause){
		super(str, cause);
	}
}
