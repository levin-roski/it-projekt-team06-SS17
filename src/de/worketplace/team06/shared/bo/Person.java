package de.worketplace.team06.shared.bo;

import java.util.*;


public class Person extends OrgaUnit {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	 /**
     * Deklaration der Attribute.
     */
    
    private String firstName;
    
    private String lastName;
    
    private String street;
    
    private int zipcode;
    
    private String city;
    
    /**
     * Getter Methoden für die einzelnen Attribute. 
     */
    
    public String getFirstName() {
		return firstName;
	}
    
    public String getLastName() {
		return lastName;
	}
    
    public String getStreet() {
		return street;
	}
    
    public int getZipcode() {
		return zipcode;
	}
    
    public String getCity() {
		return city;
	}
    
    /**
     * Setter Methoden für die einzelnen Attribute.
     */
    
    public void setFirstName(String vorname) {
		this.firstName = vorname;
	}
    
    public void setLastName(String nachname) {
		this.lastName = nachname;
	}
    
	public void setStreet(String street) {
		this.street = street;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	/**
     * Default constructor
     */
    public Person() {
    }

}