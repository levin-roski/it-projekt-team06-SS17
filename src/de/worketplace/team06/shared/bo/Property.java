package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 *  
 */
public class Property extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Property() {
    }

	/**
     *  Variable für den Namen. 
     */
    private String name;

    /**
     *  Variable für den Wert.
     */
    private String value;
    
    /**
   	 * Namen holen.
   	 */
   	public String getName() {
   		return name;
   	}

   	/**
   	 * Namen setzen. 
   	 */
   	public void setName(String name) {
   		this.name = name;
   	}


   	/**
   	 * Wert holen. 
   	 */
   	public String getValue() {
   		return value;
   	}


   	/**
   	 * Wert setzen.
   	 */
   	public void setValue(String value) {
   		this.value = value;
   	}


  

}