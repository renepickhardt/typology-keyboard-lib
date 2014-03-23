package com.android.inputmethod.api;

import java.util.List;

import com.android.inputmethod.api.model.Correction;

/**
 * A class providing corrections for typed words.
 * This should be generic in order to be usable in different situations.
 * E.g. for keyboards one would be interested in the actual touch points
 * whereas in other scenarios other data might be relevant.
 * 
 * @author till
 *
 */
public interface CorrectionProvider {
	
	/**
	 * Computes possible corrections for a word
	 * 
	 * @param word The word to correct
	 * @return A list of possible corrections for the given
	 * word ordered by relevance
	 */
	public List<Correction> computeCorrections(String word);

}
