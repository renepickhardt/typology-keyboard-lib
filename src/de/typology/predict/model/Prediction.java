package de.typology.predict.model;

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
	
	public Prediction(CharSequence name, int score) {
		this.name = name;
	}
	
	public CharSequence getText() {
		return name;
	}
	
	public int getScore() {
		return 0;
	}

}
