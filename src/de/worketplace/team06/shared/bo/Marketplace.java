package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Marketplace extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Marketplace() {
    }

    /**
     * Variable für den Titel des Marktplatz
     */
    private String title;
    
    /**
     * Variable für die Beschreibung des Marktplatz
     */
    private String description;

    /**
     * Auslesen des Titel von dem Marktplatz
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Auslesen der Beschreibung des Marktplatzes
     * @return
     */
    public String getDescription() {
		return description;
	}

    /**
     * Setzen des Titel von dem Marktplatz
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Setzen der Beschreibung von dem Marktplatz
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}

}