package de.typology.predict.api.model;

/**
 * Holds the information about a prediction:
 * -the actual prediction
 * -its score
 * 
 * @author till
 *
 */
public final class Prediction {
	
	private final CharSequence name;
	
	public Prediction(CharSequence name, double score) {
		this.name = name;
	}
	
	public CharSequence getText() {
		return name;
	}

}
