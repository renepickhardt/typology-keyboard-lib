package de.typology.predict;


/**
 * The class for managing the prediction computation.
 * 
 * @author till
 *
 */
public final class Predict {
	
	/**
	 * Sets the correction provider used for correcting the current word
	 * 
	 * @param corProv the CorrectionProvider to use
	 */
	public Predict() {}
	
	/**
	 * @return The instance of the PredictionContextComposer used by
	 * this PredictionProvider
	 */
	public PredictionContextComposer getPredictionContextComposer() {
		return null;
	}
	
	/**
	 * Loads the sources for prediction computation asynchronously.
	 */
	public void opensPredictionSources() {}
	
	public void closePredictionSources() {}
	
	//this method probably makes sense but with respect to our usecase
	//the PredictionContextComposer should be used
	//re-add this later
//	/**
//	 * Computes predictions given the current word and its
//	 * predecessors
//	 * 
//	 * @param currentWord The word that is currently typed
//	 * @param previousWords The predecessor words of the current word
//	 * @param correctCurrentWord Whether the the current word should be
//	 * corrected
//	 * @param callback the callback to call when the computation is done
//	 * @return The id of this prediction querry
//	 */
//	public long getPredictions(CharSequence currentWord, CharSequence[] previousWords,
//			boolean correctCurrentWord, OnPredictionsComputedCallback callback) {
//		Prediction[] predictions = {new Prediction(currentWord, 0.0)};
//		callback.onPredictionsComputed(Arrays.asList(predictions), 0);
//		return 0;
//	}
	
	public enum PredictionMode {
		CORRECT_CURRENT_WORD,
		DO_NOT_CORRECT_CURRENT_WORD
		//batch input,...
	}
	
	/**
	 * Computes predictions using the current state of the PredictionContextComposer
	 * 
	 * @param mode How the predictions are computed
	 * @param callback The callback to call when the computation is done
	 * @return The id of this prediction querry
	 */
	public long getPredictions(final PredictionMode mode,
			final OnPredictionsComputedCallback callback) {
		return 0;
	}
}
