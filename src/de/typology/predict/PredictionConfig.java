package de.typology.predict;

import java.util.List;

import de.typology.predict.model.Language;


/**
 * A class for configuring the prediction computation
 * 
 * @author till
 *
 */
public final class PredictionConfig {
	
	public static final int MAX_NGRAM_LENGTH = 5;
	public static final int MAX_WORD_LENGTH = 48;
	
	public static final int PREDICTION_TIMEOUT = 200;
	
	private PredictionConfig() {}
	
	/**
	 * Adds a language to the list of languages used for the predictions
	 * 
	 * @param lang The added language
	 */
	public static void enableLanguage(final Language lang) {}
	
	/**
	 * Removed a language from the list of languages used for predictions
	 * 
	 * @param lang The removed language
	 */
	public static void disableLanguage(final Language lang) {}
	
	public static List<Language> getEnabledLanguages() {
		return null;
	}
	
	//more configurations go here like timeouts, blocking offensive words,
	//...

}
