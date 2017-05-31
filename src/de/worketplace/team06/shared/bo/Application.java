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
     * Auslesen des Bewerbungstextes
     * @return applicationText
     */
    public String getApplicationText() {
        return applicationText;
    }

    /**
     *  Auslesen der ID der Ausschreibung
     *  @return callID
     */
	public int getCallId() {
		return callID;
	}
	
    /**
     *  Auslesen der ID der OrganisationsEinheit
     *  @return orgaUnitID
     */
	public int getOrgaUnitId() {
		return orgaUnitID;
	}
    
    /**
     * Setzen des Bewerbungstextes
     * @param applicationText
     */
    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }
    
    /**
     *  Setzen der ID der Ausschreibung
     *  @param callID
     */
	public void setCallId(int id) {
		this.callID = id;
	}

    /**
     *  Setzen der ID der OrganisationsEinheit
     *  @param orgaUnitID
     */
	public void setOrgaUnitId(int id) {
		this.orgaUnitID = id;
	}

}