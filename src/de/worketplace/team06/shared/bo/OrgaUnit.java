package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public abstract class OrgaUnit extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public OrgaUnit() {
    }

    /**
     * Variable für den Namen.
     */
    private String name;

    /**
     * Variable für die Beschreibung.
     */
    private String description;

    /**
     * Variable für die Speicherung der ID des Partnerproifls.
     */
    private int partnerprofileID;

    /**
     * Variable für die Speicherung der GoogleID.
     */
    public String googleID;

    /**
     * Beschreibung holen.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Pa
     */
    public int getPartnerProfile() {
        return this.partnerprofileID;
    }

    /**
     * Google ID holen.
     */
    public String getGoogleID() {
        return this.googleID;
    }

	/**
	 *  Namen holen.
	 */
	public String getName() {
		return name;
	}

    /**
     * Beschreibung setzen.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * partnerProfileID setzen.
     */
    public void setPartnerProfile(int partnerProfileID) {
        this.partnerprofileID = partnerProfileID;
    }

    /**
     * @param googleID
     */
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

	/**
	 * Namen setzen.
	 */
	public void setName(String name) {
		this.name = name;
	}

}