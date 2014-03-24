package de.typology.predict.api;

/**
 * A class for configuring the prediction computation
 * 
 * @author till
 *
 */
public class Predict {
	
	public enum Language {
		ENGLISH,
		GERMAN
		//...
	}
	
	public static final int NGRAM_LENGTH = 5;
	
	//the class is not publicly instantiable
	private Predict() {}

	/**
	 * Configures the language to use for the predictions
	 * 
	 * @param lang The language to use for the predictions
	 */
	public static void setLanguage(Language lang) {}
	
	/**
	 * 
	 * @param lang The language
	 * @return whether or not predictions in this language are
	 * supported
	 */
	public static boolean isLanguageAvailable(Language lang) {
		return false;
	}
	
	//more configurations go here like timeouts, blocking offensive words,
	//...
}
