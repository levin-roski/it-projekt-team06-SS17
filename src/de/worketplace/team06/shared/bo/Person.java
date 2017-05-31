package de.worketplace.team06.shared.bo;

import java.util.*;


public class Person extends OrgaUnit {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Person() {
    }
    
	/**
	 * Variable fpr den Vornamen der Person
	 */
    private String firstName;
    
    /**
     * Variable für den Nachnamen der Person
     */
    private String lastName;
    
    /**
     * Variable für die Straße der Person
     */
    private String street;
    
    /**
     * Variable für die Postleitzahl der Person
     */
    private int zipcode;
    
    /**
     * Variable für die Stadt der Person
     */
    private String city;
    
    /**
     * Auslesen des Vornamens der Person
     * @return firstName
     */
    public String getFirstName() {
		return firstName;
	}
    
    /**
     * Auslesen des Nachnamens der Person
     * @return lastName
     */
    public String getLastName() {
		return lastName;
	}
    
    /**
     * Auslesen der Straße der Person
     * @return street
     */
    public String getStreet() {
		return street;
	}
    
    /**
     * Auslesen der Postleitzahl der Person
     * @return zipcode
     */
    public int getZipcode() {
		return zipcode;
	}
    
    /**
     * Auslesen der Stadt der Person
     * @return city
     */
    public String getCity() {
		return city;
	}
    
    /**
     * Setzen des Vornamens der Person
     * @param vorname
     */
    public void setFirstName(String vorname) {
		this.firstName = vorname;
	}
    
    /**
     * Setzen des Nachnamens der Person
     * @param nachname
     */
    public void setLastName(String nachname) {
		this.lastName = nachname;
	}
    
    /**
     * Setzen der Straße der Person
     * @param street
     */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Setzen der Postleitzahl der Person
	 * @param zipcode
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	/**
	 * Setzen der Stadt der Person
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}