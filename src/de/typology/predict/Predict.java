package de.typology.predict.api;

import de.typology.predict.api.model.Language;

/**
 * A class for configuring the prediction computation
 * 
 * @author till
 *
 */
public class Predict {
	
	public static final int MAX_NGRAM_LENGTH = 5;
	public static final int MAX_WORD_LENGTH = 48;
	
	/**
	 * Adds a language to the list of languages used for the predictions
	 * 
	 * @param lang The added language
	 */
	public static void enableLanguage(Language lang) {}
	
	/**
	 * Removed a language from the list of languages used for predictions
	 * 
	 * @param lang The removed language
	 */
	public static void disableLanguage(Language lang) {}
	
	//more configurations go here like timeouts, blocking offensive words,
	//...
	
	
	private static Predict instance;
	
	//the class is not publicly instantiable
	private Predict() {}
	
	public Predict getInstance() {
		if (instance == null) {
			instance = new Predict();
		}
		return instance;
	}
	
	/**
	 * Adds the language model provider that is used to get language models
	 * for the enabled languages
	 * @param provider The LanguageModelProvider
	 */
	public void addLMProvider(LanguageModelProvider provider) {}
	
	/**
	 * @param lang The language
	 * @return whether or not predictions in this language are
	 * supported, e.g. if a language model for this language exists
	 */
	public boolean isLanguageAvailable(Language lang) {
		return false;
	}
	
}
