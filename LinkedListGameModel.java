package hangman;

public class LinkedListGameModel implements GameModel {
	private int state;
	private int count;
	private int numofcorrect;
	private String word;
	private LLCharacterNode guesses;
	//private LLCharacterNode correct;
	private LLCharacterNode guessesLast;
	//private LLCharacterNode correctLast;
	private LLCharacterNode Correct; 
	
	public LinkedListGameModel(String guessWord){
		word=guessWord;
		state=STARTING_STATE;
		Correct=new LLCharacterNode('_');
		for(int i=1;i<word.length();i++){
			Correct.insert('_');
			
		}
	    
		
		}


	public void addLL(char letter){
		LLCharacterNode newone=new LLCharacterNode(letter);
		LLCharacterNode curNode=guesses;
		if(curNode==null){
			guesses=newone;
			guessesLast=guesses;
		}
		else{
			guessesLast.setLink(newone);
			guessesLast=guessesLast.getLink();
		}
		
	}
	public void addCorrect(char letter){ //add the correct guess in order
		
		LLCharacterNode curNode=Correct;
		for(int a=0;a<word.length();a++){
			if(word.charAt(a)==letter){
				curNode.setInfo(letter);
			}	
					curNode=curNode.getLink();
				}
				
			
		
		
	}

	@Override
	public boolean isPriorGuess(char guess) {
		boolean prior=false;
		LLCharacterNode curNode=guesses;
		while(curNode!=null){
			if(curNode.getInfo()==guess){
				prior=true;
				curNode=curNode.getLink();
			}
			else
				curNode=curNode.getLink();// TODO Auto-generated method stub
		}
		return prior;
	}

	@Override
	public int numberOfGuesses() {
		// TODO Auto-generated method stub
		return count;
	}

	@Override
	public boolean isCorrectGuess(char guess) {
		boolean correct=false;
		for(int i=0;i<word.length();i++){
			if(guess==word.charAt(i))
				correct=true;// TODO Auto-generated method stub
		}
		
		return correct;
	}
	
	@Override
	public boolean doMove(char guess) {
		boolean move=false;
		if(!isPriorGuess(guess)){
			if(isCorrectGuess(guess)){
				move=true;
				count++;
				addLL(guess);
				addCorrect(guess);
				
				for(int i=0;i<word.length();i++){
					if(word.charAt(i)==guess)
						numofcorrect++;
				}
				}
				// TODO Auto-generated method stub
			
			else{
				count++;
				state++;
				addLL(guess);
			}
		}
		return move;
	}

	@Override
	public boolean inWinningState() {
		return numofcorrect==word.length();
	}

	@Override
	public boolean inLosingState() {
		boolean lose=false;
		if(state==NUMBER_OF_STATES)
			lose=true;// TODO Auto-generated method stub
		return lose;
	}
	
	public String toString() {
		String s="";
		for(LLCharacterNode curNode=Correct;curNode!=null;curNode=curNode.getLink()){
			s=s+curNode.getInfo()+" ";
		}
		return s.trim();
		
		
		
				}
				
				    
				    	
					    
					    	
					    
				  

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return state;
	}

	@Override
	public String previousGuessString() {
		LLCharacterNode curNode=guesses;
		String s="[";
		while(curNode!=null&&curNode.getLink()!=null){
			s=s+curNode.getInfo()+", ";
			curNode=curNode.getLink();
		}
		if(curNode.getLink()==null){
			s+=curNode.getInfo();
		}
		return s+"]";
	}

	@Override
	public String getWord() {
		// TODO Auto-generated method stub
		return word;
	}

}
