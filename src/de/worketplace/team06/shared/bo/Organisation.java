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
     * Deklaration der Attribute.
     */
    private String name;
    
    private String street;
    
    private int zipcode;
    
    private String city;

	/**
     * Default constructor
     */
    public Organisation() {
    	
    }
    
    /**
     * Getter Methoden für die einzelnen Attribute. 
     */
    public String getName() {
		return name;
	}
    
    public String getStreet() {
        return this.street;
    }

    public int getPostalcode() {
        return this.zipcode;
    }

    public String getCity() {
        return this.city;
    }
    
    /**
     * Setter Methoden für die einzelnen Attribute. 
     */
    public void setName(String name) {
		this.name = name;
	}
    
    public void setStreet(String street) {
        this.street = street;
    }

    public void setPostalcode(int postalcode) {
        this.zipcode = postalcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

}