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
     *  Variable für das Speichern des Bewerbungstextes.
     */
    private String applicationText;

    /**
     *  ID der dazgehörigen Ausschreibung.
     */
    private int callId;
    
    /**
     *  ID der dazgehörigen OrganisationsEinheit.
     */
    private int orgaUnitId;
    
    /**
     *  Auslesen des Bewerbungstextes.
     */
    public String getApplicationText() {
        return applicationText;
    }

    /**
     *  Setzen des Bewerbungstextes.
     */
    public void setApplicationText(String applicationText) {
        this.applicationText = applicationText;
    }
    
    /**
     *  Auslesen der ID der Ausschreibung.
     */
	public int getCallId() {
		return callId;
	}

    /**
     *  Setzen der ID der Ausschreibung.
     */
	public void setCallId(int callId) {
		this.callId = callId;
	}

    /**
     *  Auslesen der ID der OrganisationsEinheit.
     */
	public int getOrgaUnitId() {
		return orgaUnitId;
	}

    /**
     *  Setzen der ID der OrganisationsEinheit.
     */
	public void setOrgaUnitId(int orgaUnitId) {
		this.orgaUnitId = orgaUnitId;
	}

}