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
     * Auslesen des Titels des Marktplatzes
     * @return title
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Auslesen der Beschreibung des Marktplatzes
     * @return description
     */
    public String getDescription() {
		return description;
	}

    /**
     * Setzen des Titel des Marktplatzes
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Setzen der Beschreibung des Marktplatzes
     * @param description
     */
	public void setDescription(String description) {
		this.description = description;
	}

}