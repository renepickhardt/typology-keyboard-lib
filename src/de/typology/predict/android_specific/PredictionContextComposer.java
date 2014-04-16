package de.typology.predict.android_specific;

import de.typology.predict.ImmutablePredictionContextComposer;
import de.typology.predict.Predict;
import de.typology.predict.PredictionConfig;
import de.typology.predict.model.PredictionContext;

/**
 * A class to hold and update the state of the input. It stores the word
 * currently composed as well as it's predecessor words
 * 
 * @author till
 * 
 */
public final class PredictionContextComposer implements
		ImmutablePredictionContextComposer {

	public static final int MAX_PREDECESSOR_WORD_AMOUNT = PredictionConfig.MAX_NGRAM_LENGTH;

	/**
	 * The class is only instanciable inside the package. If one wants to obtain
	 * an instance outside of it, he has to call
	 * {@link Predict#getPredictionContextComposer()}.
	 */
	protected PredictionContextComposer() {
	}

	/**
	 * Sets the keyboard the text is composed in TODO: what kind of class is
	 * keyboard? is this given by and android dependency? I thought the lib is
	 * standalone?
	 */
	// Can be used for corrections in conjunction with the input key's
	// coordinates
	// TODO: find an appropriate class as parameter type an add parameter
	// TODO: what about multiple keyboards, e.g. for letters and digits?
	public void setKeyboard(/* keyboard */) {
	}

	/**
	 * Deletes the word the cursor is currently in.
	 * 
	 * TODO: just in the context that is used for the lib or in the actuall
	 * keyboard?
	 */
	public void clearCurrentWord() {
	}

	/**
	 * Deletes the predecessors of the word the cursor is currently in.
	 * 
	 * TODO: just in the context that is used for the lib or in the actuall
	 * keyboard?
	 */
	public void deletePredecessorWords() {
	}

	/**
	 * Inserts a character before the cursor. This increments the cursor
	 * position by one. Adding a separator character finishes editing the
	 * current word.
	 * 
	 * @param character
	 *            The character to append.
	 * @param posX
	 *            The key press' x-coordinate. Ignored if < 0.
	 * @param posY
	 *            The key press' y-coordinate. Ignored if < 0.
	 * @param isSeparator
	 *            Whether the character is a word separator.
	 */
	// we need to use int here because char cannot store all unicode characters
	public void addChar(final int character, final int posX, final int posY,
			boolean isSeparator) {
	}

	/**
	 * Deletes the character in before the cursor. This decrements the cursor
	 * position by one.
	 * 
	 * @return True if more context should be added using
	 *         {@link #prependPredecessorWord(CharSequence)}, false otherwise.
	 */
	public boolean deleteBeforeCursor() {
		return false;
	}

	/**
	 * The current word is replaced by the previous one.
	 * 
	 * @return True if more context should be added using
	 *         {@link #prependPredecessorWord(CharSequence)}, false otherwise.
	 */
	public boolean resumePreviousWord() {
		return false;
	}

	/**
	 * The current word is replaced with word.
	 * 
	 * @param word
	 * @param isAutoCommited
	 *            Whether the replacement is an auto commit.
	 */
	// If the replacement is auto committed and we receive deletions afterwards
	// we might want to restore the previous value for this word since the auto
	// commit might be wrong
	// TODO: are auto- and non-auto-committed (= user-selected) the only options
	// here?
	// RENE: yes i think so. At least I can't think of anything else right now
	public void setCurrentWord(final CharSequence word, boolean isAutoCommited) {
	}

	/**
	 * The words before the current word are replaced by predecessors.
	 * 
	 * @param predecessors
	 */
	// TODO: reconsider this. Maybe we should only have a method to set
	// the i-th predecessor.
	// RENE: this could be a queue? when a new word comes in the last words
	// should be popped out?
	public void setPredecessorWords(final CharSequence[] predecessors) {
	}

	/**
	 * Adds a word in front of the predecessor words.
	 * 
	 * @param predecessorWord
	 *            The word that becomes the the first predecessor word.
	 */
	public void prependPredecessorWord(final CharSequence predecessorWord) {
	}

	/**
	 * @return The position of the cursor in the word it is in.
	 */
	public int getCursorPositionInWord() {
		return 0;
	}

	/**
	 * @return The position index of the word the cursor is in. 0 means the last
	 *         word, values > 0 mean the i-th predecessor of te last word.
	 */
	public int getCursorWord() {
		return 0;
	}

	/**
	 * Moves the cursor to the given position. If the given position does not
	 * exists the cursor is not moved.
	 * 
	 * @param wordIndex
	 *            The index of the word to move to. 0 is the current word. A
	 *            value > 0 is the wordIndex-th predecessor.
	 * @param charIndex
	 *            The index of the character in the wordIndex-th word to move
	 *            to.
	 */
	public void setCursorPosition(final int wordIndex, final int charIndex) {
	}

	/**
	 * Moves the cursor by the given offset. values < 0 move the cursor to the
	 * left, values > 0 to the right.
	 * 
	 * @param offset
	 *            The offset to move the cursor by.
	 */
	public void moveCursor(final int offset) {
	}

	@Override
	public int getCurrentWordSize() {
		return 0;
	}

	@Override
	public int getNumberOfPredecessorWords() {
		return 0;
	}

	@Override
	public int getCodeAt(int codePointIndex) {
		return 0;
	}

	@Override
	public CharSequence getWordAt(final int wordIndex) {
		return null;
	}

	/**
	 * Creates an instance of PredictionContext with the same state as the composer.
	 * It can be used to make asynchronous prediction lookups.
	 * 
	 * @return an instance of PredictionContext with the same state.
	 */
	public PredictionContext getLookupContext() {
		// only copy stuff before the cursor to reduce overhead and
		// to get rid of the cursor in the read-only copy
		return null;
	}

}
