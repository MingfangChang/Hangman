package hangman;

/**
 * The Array implementation of the GameModel interface.
 */
public class ArrayGameModel implements GameModel {
	/** The number of characters (lower/upper). */
	private static final int ALPHABET_COUNT = 26*2;
	private char[] guesses;
	private char[] allguesses;
	private String word;
	private String[] yeah;
	/** hung state */
	private int	state;
	private int lastIndex=-1;
	private int lastNum=-1;
	
	
	/**
	 * Creates a new ArrayGameModel object.
	 * 
	 *  guessWord the word to guess
	 */
	public ArrayGameModel(String guessWord) {
		word=guessWord;
		allguesses=new char[ALPHABET_COUNT];
		guesses=new char[word.length()+10];
		yeah=new String[word.length()];
		for(int f=0;f<word.length();f++){
			yeah[f]="_";// TODO (1)
		}
		state    = STARTING_STATE;
	}
		
	public boolean isPriorGuess(char guess) {
	    boolean prior=false;
		for(int i=0;i<guesses.length;i++){
			if(guesses[i]==guess)
				prior=true;
				// TODO (2)
		}
		return prior;
	}
	
	public int numberOfGuesses() {
		int count=0;
		for(int i=0;i<guesses.length;i++){
			if(guesses[i]!=0000)
				count++;// TODO (3)
		}
		
		return count;
	}
	
	public boolean isCorrectGuess(char guess) {
		boolean correct=false;
		for(int c=0;c<word.length();c++){
			if(word.charAt(c)==guess)
				correct=true;
		}
		// TODO (4)
		return correct;
	}
	
	public boolean doMove(char guess) {
		boolean move=false;
		if(isPriorGuess(guess)){
			lastNum++;
			allguesses[lastNum]=guess;
		}
		if(!isPriorGuess(guess)){
			if(isCorrectGuess(guess)){
				move=true;
				lastNum++;
				lastIndex++;
				allguesses[lastNum]=guess;
				guesses[lastIndex]=guess;
				for(int v=0;v<word.length();v++){
					if(word.charAt(v)==guess)
						yeah[v]=guess+"";
				}
			}
			else{
				move=false;
				state++;
				lastNum++;
				lastIndex++;
				allguesses[lastNum]=guess;
				guesses[lastIndex]=guess;
			}
		}
		return move;
	}

	public boolean inWinningState() {
		boolean win=true;
		
		for(int g=0;g<yeah.length;g++){
			if(yeah[g]=="_")
				win=false;// TODO (6)
		}
		
		return win;
	}

	public boolean inLosingState() {
		boolean lose=false;
		if(state==NUMBER_OF_STATES)
			lose=true;// TODO(7)
		return lose;
	}
	
	public String toString() {
		String s = "";
		for(int d=0;d<yeah.length-1;d++){
			s=s+yeah[d]+" ";
		}
		// TODO (8)
		
		return s+yeah[yeah.length-1];
	}

	public String previousGuessString() {
		String s="";
		for(int b=0;b<(numberOfGuesses()-1);b++){
			
			s=s+guesses[b]+","+" ";
		}
		// TODO (9)
		
		return "["+s+guesses[numberOfGuesses()-1]+"]";
	}
	
	public int getState() {
		return state;
	}

	public String getWord() {

		return word;// TODO (10)

	}
  
}
