package de.typology.predict.model;

/**
 * This class holds the immutable context for a prediction.
 * The context is a sequence of words. The last word is the
 * prefix of the currently composed word.
 * 
 * @author till
 *
 */
public final class PredictionContext {
	
	public PredictionContext(String[] words) {
		
	}
	
	/**
	 * @return The number of words in the PredictionContext.
	 */
	public int getNumberOfWords() {
		return 0;
	}
	
	/**
	 * @param wordIndex The index of the word for which the size is returned.
	 * 				0 is the last word,
	 * 				values > 0 are the wordIndex-th predecessor.
	 * 				An index >= {@link #getNumberOfWords()} or < 0
	 * 				results in an {@link IndexOutOfBoundsException}
	 * @return The size (number of code points) of the word at the given index.
	 */
	public int getWordSize(final int wordIndex) {
		return 0;
	}
	
	/**
	 * @param wordIndex
	 *            The index of the requested word. 0 means the current word i > 0
	 *            means the i-th predecessor of the current word.
	 *            An index >= {@link #getNumberOfWords()} or < 0
	 * 				results in an {@link IndexOutOfBoundsException}.
	 * @return The wordIndex-th predecessor of the current word or the current
	 *         word
	 */
	public String getWordAt(final int wordIndex) {
		return null;
	}

	/**
	 * @param wordIndex
	 * 			The index of the word. 0 means the last word.
	 * 			An index >= {@link #getNumberOfWords()} or < 0
	 * 				results in an {@link IndexOutOfBoundsException}
	 * @param codePointIndex
	 *            The index of code point in the word. 0 is the the index of
	 *            the first character.
	 *            An index >= {@link #getWordSize(wordIndex)} or < 0
	 * 				results in an {@link IndexOutOfBoundsException}
	 * @return The code point at this index.
	 */
	// TODO: I do not understand the semantics of this function.
	public int getCodeAt(final int wordIndex, final int codePointIndex) {
		return 0;
	}

}
