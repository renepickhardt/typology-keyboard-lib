package de.typology.predict;

import de.typology.predict.PredictionConfig.PredictionConfigChangeListener;

/**
 * The class for managing the prediction computation.
 * 
 * @author till
 * 
 */
public final class Predict implements PredictionConfigChangeListener {

	/**
	 * Sets the correction provider used for correcting the current word
	 * 
	 * @param corProv
	 *            the CorrectionProvider to use
	 */
	public Predict() {
	}

	/**
	 * @return The instance of the PredictionContextComposer used by this
	 *         PredictionProvider
	 */
	public PredictionContextComposer getPredictionContextComposer() {
		return null;
	}

	/**
	 * Loads the sources for prediction computation asynchronously.
	 */
	public void loadPredictionSources() {
	}

	public void closePredictionSources() {
	}

	// this method probably makes sense but with respect to our usecase
	// the PredictionContextComposer should be used
	// re-add this later
	// /**
	// * Computes predictions given the current word and its
	// * predecessors
	// *
	// * @param currentWord The word that is currently typed
	// * @param previousWords The predecessor words of the current word
	// * @param correctCurrentWord Whether the the current word should be
	// * corrected
	// * @param callback the callback to call when the computation is done
	// * @return The id of this prediction querry
	// */
	// public long getPredictions(CharSequence currentWord, CharSequence[]
	// previousWords,
	// boolean correctCurrentWord, OnPredictionsComputedCallback callback) {
	// Prediction[] predictions = {new Prediction(currentWord, 0.0)};
	// callback.onPredictionsComputed(Arrays.asList(predictions), 0);
	// return 0;
	// }

	// TODO: how will this affect our implementatiobn?
	public enum PredictionMode {
		/**
		 * Predictions with a different prefix than the given
		 * prefix are allowed.
		 */
		CORRECT_CURRENT_WORD,
		/**
		 * Predictions with a different prefix than the given
		 * prefix are not allowed.
		 */
		DO_NOT_CORRECT_CURRENT_WORD
		// batch input,...
	}

	/**
	 * Computes predictions using the current state of the
	 * PredictionContextComposer
	 * 
	 * @param mode
	 *            How the predictions are computed
	 * @param callback
	 *            The callback to call when the computation is done
	 * @return The id of this prediction querry
	 */
	public long getPredictions(final PredictionMode mode,
			final OnPredictionsComputedCallback callback) {
		return 0;
	}

	//we are not learning yet, readd this later
//	/**
//	 * Reports that the current word (the word the cursor is currently in) was
//	 * commited to the editor
//	 */
//	// We use this to give feedback to a learning prediction source
//	// TODO: should this be here or in the PredictionContextComposer? If so,
//	// we would need a listener for this event
//	// RENE: the question is where is the onselct event fired? (the question
//	// really is how much android is in the interfaces?)
//	public void reportCurrentWordCommited() {
//	}

	@Override
	public void onPredictionConfigChanged() {
		// reload PredictionProviders here
	}
	
	// multiple things need to be done asynchronously:
	// -getting suggestions
	// -loading (and closing?) the PredictionProviders
	// -feeding back information to the PredictionProviders

//    private static final class PredictionHandler implements Handler.Callback {
//        private Handler mHandler;
//
//        @Override
//        public boolean handleMessage(Message message) {
//            return false;
//        }
//    }

}