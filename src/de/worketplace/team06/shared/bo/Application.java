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
     * Variable für das Speichern des Bewerbungstextes
     */
    private String applicationText;

    /**
     * ID der dazgehörigen Ausschreibung
     */
    private int callID;
    
    /**
     * ID der dazgehörigen OrganisationsEinheit
     */
    private int orgaUnitID;
    
    /**
     * ID der zugehörigen ratingID
     */
    private int ratingID;
    
    /**
     * Auslesen des Bewerbungstextes
     * @return applicationText
     */
    public String getText() {
        return applicationText;
    }

    /**
     *  Auslesen der ID der Ausschreibung
     *  @return callID
     */
	public int getCallID() {
		return callID;
	}
	
    /**
     *  Auslesen der ID der OrganisationsEinheit
     *  @return orgaUnitID
     */
	public int getOrgaUnitID() {
		return orgaUnitID;
	}
	
	/**
	 * Auslesen der ID der zugehörigen Rating Instanz
	 * @return ratingID;
	 */
	public int getRatingID() {
		return ratingID;
	}
    
    /**
     * Setzen des Bewerbungstextes
     * @param applicationText
     */
    public void setText(String applicationText) {
        this.applicationText = applicationText;
    }
    
    /**
     *  Setzen der ID der Ausschreibung
     *  @param callID
     */
	public void setCallID(int id) {
		this.callID = id;
	}

    /**
     *  Setzen der ID der OrganisationsEinheit
     *  @param orgaUnitID
     */
	public void setOrgaUnitID(int id) {
		this.orgaUnitID = id;
	}

	/**
	 * Setzen der ID der zugehörigen Rating Instanz
	 * @param ratingID
	 */
	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

}