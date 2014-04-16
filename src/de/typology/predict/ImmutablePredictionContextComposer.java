package de.typology.predict;

/**
 * TODO: why is this interface given for PredictionContextComposer? What other
 * classes will implement this interface. Why is the abstraction needed?
 * 
 * @author till
 * 
 */
public interface ImmutablePredictionContextComposer {

	/**
	 * TODO: returns the size of the word which is supposed to be predicted or
	 * the size of the prefix currently entered?
	 * 
	 * @return
	 */
	public int getCurrentWordSize();

	/**
	 * TODO: what does this depend on? Where do I set the context? is this
	 * retrieved from the defined context? What happens I I type 100 words
	 * without "." ?
	 * 
	 * @return
	 */
	public int getNumberOfPredecessorWords();

	/**
	 * @param codePointIndex
	 *            The index of the requested code point. 0 is the the index of
	 *            the first character.
	 * @return The code point at this index.
	 */
	// TODO: I do not understand the semantics of this function.
	public int getCodeAt(final int codePointIndex);

	/**
	 * TODO: next sentence is not a sentence. Do you mean the index OF the
	 * requested word?
	 * 
	 * TODO: what about negative indexes? Will there be exceptions thrown or how
	 * will this be handled?
	 * 
	 * @param wordIndex
	 *            The index the requested word. 0 means the current word i > 0
	 *            means the i-th predecessor of the current word
	 * @return The wordIndex-th predecessor of the current word or the current
	 *         word
	 */
	public CharSequence getWordAt(final int wordIndex);

}
