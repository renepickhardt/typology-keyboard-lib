package de.typology.predict;

public interface ImmutablePredictionContextComposer {
	
	public int getCurrentWordSize();
	
	public int getNumberOfPredecessorWords();
	
	/**
	 * @param codePointIndex The index of the requested code point.
	 * 0 is the the index of the first character.
	 * @return The code point at this index.
	 */
	public int getCodeAt(final int codePointIndex);
	
	/**
	 * @param wordIndex The index the requested word.
	 * 0 means the current word
	 * i > 0 means the i-th predecessor of the current word
	 * @return The wordIndex-th predecessor of the current word
	 * or the current word
	 */
	public CharSequence getWordAt(final int wordIndex);

}
