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
     * 
     */
    private String title;

    /**
     * "title" holen.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * "title" setzen.
     */
    public void setTitle(String title) {
        this.title = title;
    }

}