package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Organisation extends OrgaUnit {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Organisation() {
    	
    }

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

}