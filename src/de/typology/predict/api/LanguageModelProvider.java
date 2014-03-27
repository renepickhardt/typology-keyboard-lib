package de.typology.predict.api;

import de.typology.predict.api.model.Language;

/**
 * An interface to provide mutable and immutable language models
 * for languages
 * 
 * @author till
 *
 */
public interface LanguageModelProvider {
	
	/**
	 * @param lang
	 * @return Whether a model for this language exists
	 */
	public boolean hasLanguageModel(Language lang);
	
	//TODO: are the following two methods providing the same language modle one as mutable and one as the non mutable one? or will the objects behind provide two different LMs? 
	public LanguageModel getLanguageModel(Language lang);
	
	public MutableLanguageModel getMutableLanguageModel(Language lang);
	
	public MutableLanguageModel createMutableLanguageModel(Language lang);

}
