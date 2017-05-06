package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Application extends BusinessObject {


	/**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Application() {
    	// TODO implement here
    }

    /**
     * Variable für das Speichern des Bewerbungstextes.
     */
    private String applicationText;

    /**
     *  Bewerbungstext holen.
     */
    public String getApplicationText() {
        return applicationText;
    }

    /**
     * Bewerbungstext setzen.
     */
    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }

}