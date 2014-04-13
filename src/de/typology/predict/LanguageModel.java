package de.typology.predict.api;

import de.typology.predict.api.model.Language;

/**
 * A language model.
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
public interface LanguageModel {
	
	public void startSession();
	public void endSession();
	
	/**
	 * @return The language supported by this language model
	 */
	public Language getSupportedLanguage();
	
	/**
	 * Querries the language for the given ngram and counts
	 * the number of occurrences of the ngram and lower order
	 * ngrams and skip-grams
	 * 
	 * @param ngram The ngram to querry for
	 * @return the number of occurences of the ngram and it's lower
	 * order and skip-grams
	 */
	public int getNgramCount(final String[] ngram);
	
	//more methods to get continuation counts etc. go here

}
