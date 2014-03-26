package de.typology.predict.api;

/**
 * A mutable language model that supports the insertion of ngrams.
 * Its purpose is to create a user dictionary.
 * 
 * @author till
 *
 */
public interface MutableLanguageModel extends LanguageModel {
	
	/**
	 * Adds the given ngram to the language model or increases its count
	 * if it already exists
	 * 
	 * @param ngram
	 */
	public void addNgram(CharSequence[] ngram);

}
