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
	
	//TODO: why do we need startSession and endSession? This is not clear for me and not documented
	public void startSession();
	public void endSession();
	
	/**
	 * @return The language supported by this language model
	 */
	public Language getSupportedLanguage();

	//TODO: not sure if this needs to be public. I would assume the public API version should be something like public float getProbability(String Sequence); where the argument could also be replaced by an String array 
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

	//TODO: have a look at the current typology implementation. Here we also use string arrays but also patterns to show which kind of ngrams we want.

	
	//more methods to get continuation counts etc. go here
	//TODO: like the above method I am not sure if it has to be public (there could be valid use cases though)
}
