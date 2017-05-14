package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class OrgaUnit extends BusinessObject {

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
     * Variable für den Straßennamen.
     */
    private String street;

    /**
     * Variable für die Postleitzahl.
     */
    private int postalcode;

    /**
     * Variable für den Ort. 
     */
    private String city;
    
    /**
     * Straßennamen holen.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Postleitzahl holen.
     */
    public int getPostalcode() {
        return this.postalcode;
    }

    /**
     * Ort holen.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Straße setzen. 
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Postleitzahl setzen.
     */
    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * Ort setzen.
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Beschreibung holen.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Partnerprofile ID holen
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