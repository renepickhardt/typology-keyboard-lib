package de.typology.predict.api;

/**
 * A class to hold and update the state of the input.
 * It stores the word currently composed as well as it's predecessor words
 * 
 * @author till
 *
 */
public class PredictionContextComposer {
	
	protected PredictionContextComposer() {}
	
	public void clearCurrentWord() {}
	
	public void clearPredecessorWords() {}
	
	//we need to use int here because char cannot store all unicode characters
	public void appendChar(int character) {}
	
	public void deleteLastChar() {}
	
	public void finishCurrentWord() {}
	
	public void reopenPreviousWord() {}
	
	public void setCurrentWord(CharSequence word) {}
	
	public void setPredecessorWords(CharSequence[] predecessors) {}
	
	public void pushPredecessorWord(CharSequence predecessorWord) {}
	
	/**
	 * @param wordIndex The index the requested word.
	 * 0 means the current word
	 * i > 0 means the i-th predecessor of the current word
	 * @return The wordIndex-th predecessor of the current word
	 * or the current word
	 */
	public CharSequence getWord(int wordIndex) {
		return null;
	}

}
