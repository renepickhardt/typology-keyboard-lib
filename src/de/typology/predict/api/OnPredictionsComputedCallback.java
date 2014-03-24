package de.typology.predict.api;

import java.util.List;

import de.typology.predict.api.model.Prediction;

public interface OnPredictionsComputedCallback {
	
	/**
	 * Gets called with the result of the computation of the predictions
	 * 
	 * @param predictions The list of computed predictions, ordered by
	 * relevance
	 * @param querryId The id of the prediction querry
	 */
	public void onPredictionsComputed(List<Prediction> predictions,
			long querryId);

}
