package de.typology.predict.model;

/**
 * Holds the information about a prediction: -the actual prediction -its score
 * 
 * TODO: does it make sence to
 * 
 * @author till
 * 
 */
public final class Prediction implements CharSequence {

	// TODO: why using CharSequence and not string?
	// TODO: why is the variable called name?
	private final String name;

	public Prediction(String name, int score) {
		this.name = name;
	}

	// TODO: why is it called getText and not getName? why not
	// getPredictionString or getPredictionWord or just getWord
	public String getText() {
		return this.name;
	}

	// TODO: add java doc what is the range of score? can it be negative?
	public int getScore() {
		return 0;
	}

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int i) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int i, int i2) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
