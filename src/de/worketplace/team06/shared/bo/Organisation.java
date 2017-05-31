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
     * Variable für den Namen der Organisation
     */
    private String name;
    
    /**
     * Variable für die Straße der Organisation
     */
    private String street;
    
    /**
     * Variable für die Postleitzahl der Organisation
     */
    private int zipcode;
    
    /**
     * Variable für die Stadt der Organisation
     */
    private String city;

	/**
     * Default constructor
     */
    public Organisation() {
    	
    }
    
    /**
     * Auslesen des Namens der Organisations 
     */
    public String getName() {
		return name;
	}
    
    /**
     * Auslesen der Straße der Organisation
     * @return street
     */
    public String getStreet() {
        return this.street;
    }
    
    /**
     * Auslesen der Postleitzahl der Organisation
     * @return zipcode
     */
    public int getZipcode() {
        return this.zipcode;
    }

    /**
     * Auslesen der Stadt der Organisation
     * @return
     */
    public String getCity() {
        return this.city;
    }
    
    /**
     * Setzen des Namens der Organisations
     * @param name
     */
    public void setName(String name) {
		this.name = name;
	}
    
    /**
     * Setzen der Straße der Organisation
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Setzen der Postleitzahl der Organisation
     * @param zipcode
     */
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Setzen der Stadt der Organisation
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

}