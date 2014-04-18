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
	// TODO: why 48?
	public static final int MAX_WORD_LENGTH = 48;

	// in milliseconds i guess?
	public static final int PREDICTION_TIMEOUT = 200;

	public interface PredictionConfigChangeListener {

		// TODO: who is responsible for fireing this callback.
		public void onPredictionConfigChanged();

	}

	private PredictionConfig() {
	}

	/**
	 * Registers listener that are notified if configurations change
	 * 
	 * @param listener
	 * 
	 *            TODO: do we have our own event loop or is this logic being
	 *            handled by android?
	 */
	public static void registerConfigChangeListener(
			PredictionConfigChangeListener listener) {
	}

	/**
	 * Enables or disables predictions in the given language
	 * 
	 * @param lang
	 *            The language that is en- or disabled
	 * @param isEnabled
	 *            Whether the language is en- or disabled
	 */
	public static void setLanguageEnabled(final Language lang,
			final boolean isEnabled) {
	}

	public static List<Language> getEnabledLanguages() {
		return null;
	}

	/**
	 * Whether contacts should be used for predictions
	 * 
	 * @param isEnabled
	 */
	public static void setUseContactsEnabled(boolean isEnabled) {
	}

	// TODO: make them an issue in git this wil be forgotten otherwise.
	// more configurations go here like timeouts, blocking offensive words,
	// ...

}
