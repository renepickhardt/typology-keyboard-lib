package com.android.inputmethod.api;

import com.android.inputmethod.api.model.LMQuerryResult;

/**
 * A provider for the language model.
 * The actual provider can be anything:
 * -a trie
 * -a database
 * -a hashmap
 * -a fst
 * -...
 * 
 * @author till
 *
 */
public interface PredictionDataProvider {
	
	public void startSession();
	public void endSession();
	
	/**
	 * Querries the language for the given ngram and counts
	 * the number of occurrences of the ngram and lower order
	 * ngrams and skip-grams
	 * 
	 * @param ngram The ngram to querry for
	 * @return the number of occurences of the ngram and it's lower
	 * order and skip-grams
	 */
	public LMQuerryResult getNgramCount(final String[] ngram);

}
