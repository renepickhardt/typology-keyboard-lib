package de.typology.predict;

import java.util.List;

import de.typology.predict.model.Prediction;

/**
 * This interface is implemented by all classes that compute predictions.
 * 
 * @author till
 *
 */
public interface PredictionProvider {
	
	public void start();
	
	public void end();
	
	public List<Prediction> getPredictions(final ImmutablePredictionContextComposer composer);

}
