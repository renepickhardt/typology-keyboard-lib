package de.typology.predict.api;

/**
 * A class to hold and update the state of the input.
 * It stores the word currently composed as well as it's predecessor words
 * 
 * @author till
 * TODO: do I understand it correctly that this class is used to communicate with the android keyboard and can be exchanged when for example making predictions in another environment?
 * TODO: ok I see it is also used internally while the async callback is executed
 * TODO: for all classes we need to discuss where and how android is a dependency. and we should try to decouple from android as much as possible
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
