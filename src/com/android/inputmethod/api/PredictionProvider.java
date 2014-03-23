package com.android.inputmethod.api;

/**
 * The class for computing the predictions.
 * 
 * @author till
 *
 */
public class PredictionProvider {
	
	/**
	 * Sets the correction provider to use for correcting the current word
	 * 
	 * @param corProv the CorrectionProvider to use
	 */
	public void setCorrectionProvider(CorrectionProvider corProv) {}
	
	/**
	 * Computes predictions for given the current word and its
	 * predecessors
	 * 
	 * @param currentWord The word that is currently typed
	 * @param previousWords The predecessor words of the current word
	 * @param correctCurrentWord Whether the the current word should be
	 * corrected
	 * @param callback the callback to call when the computation is done
	 * @return The id of this prediction querry
	 */
	public long getPredictions(String currentWord, String[] previousWords,
			boolean correctCurrentWord, OnPredictionsComputedCallback callback) {
		return 0;
	}

}
