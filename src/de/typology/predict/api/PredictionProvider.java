package de.typology.predict.api;


/**
 * The class for computing the predictions.
 * 
 * @author till
 *
 */
public class PredictionProvider {
	
	/**
	 * Sets the correction provider used for correcting the current word
	 * 
	 * @param corProv the CorrectionProvider to use
	 */
	public PredictionProvider(CorrectionProvider corProv) {}
	
	/**
	 * @return The instance of the PredictionContextComposer used by
	 * this PredictionProvider
	 */
	public PredictionContextComposer getPredictionContextComposer() {
		return null;
	}
	
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
	
	/**
	 * Computes predictions using the state of the PredictionContextComposer
	 * 
	 * @param correctCurrentWord Whether the the current word should be
	 * corrected
	 * @param callback the callback to call when the computation is done
	 * @return The id of this prediction querry
	 */
	public long getPredictions(boolean correctCurrentWord,
			OnPredictionsComputedCallback callback) {
		return 0;
	}
	// TODO: do we have some example code (maybe some other android project how the usage of callbacks works. It seems to me like android is doing this really nicely)

}
