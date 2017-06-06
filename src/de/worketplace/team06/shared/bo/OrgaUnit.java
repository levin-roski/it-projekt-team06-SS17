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
     * Variable für die googleID
     */
    private String googleID;  
    
    /**
     * Variable für die Beschreibung der OrganisationsEinheit
     */
    private String description;

    /**
     * Variable für die ID eines PartnerProfils
     */
    private int partnerprofileID;
    
    /**
     * Variable für den OrgaUnit Typ (Organisation, Team oder Person)
     */
    private String type;
    
    /**
     * Auslesen der GoogleID für die OrganisationsEinheit
     * @return googleID
     */
    public String getGoogleID() {
        return this.googleID;
    }
    
    /**
     * Auslesen der Beschreibung für die OrganisationsEinheit
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Auslesen der ID für das PartnerProfil der OrganisationsEinheit
     * @return partnerprofileID
     */
    public int getPartnerProfileID() {
        return this.partnerprofileID;
    }
    
    /**
     * Auslesen des OrgaUnit Typs (Organisation, Team oder Person)
    g * @return
     */
	public String getType() {
		return type;
	}

    /**
     * Setzen der GoogleID für die OrganisationsEinheit 
     * @param googleID
     */
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }
    
    /**
     * Setzen der Beschreibung der OrganisationsEinheit
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setzen der ID für das PartnerProfil der OrganisationsEinheit
     * @param partnerProfileID
     */
    public void setPartnerProfileID(int partnerProfileID) {
        this.partnerprofileID = partnerProfileID;
    }
	
	/**
	 * Setzen des Typs von OrgaUnit (Organisation, Team oder Person)
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

}