package de.typology.predict.model;

/**
 * Holds the information about a prediction: -the actual prediction -its score
 * 
 * TODO: does it make sence to
 * 
 * @author till
 * 
 */
public final class Prediction {

	// TODO: why using CharSequence and not string?
	// TODO: why is the variable called name?
	private final CharSequence name;

	public Prediction(CharSequence name, int score) {
		this.name = name;
	}

	// TODO: why is it called getText and not getName? why not
	// getPredictionString or getPredictionWord or just getWord
	public CharSequence getText() {
		return this.name;
	}

	// TODO: add java doc what is the range of score? can it be negative?
	public int getScore() {
		return 0;
	}

}
