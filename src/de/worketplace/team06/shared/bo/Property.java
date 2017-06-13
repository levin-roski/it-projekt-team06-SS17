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
     * Variable für den Namen der Eigenschaft 
     */
    private String name;

    /**
     * Variable für den Wert der Eigenschaft
     */
    private String value;
    
    private int partnerProfileID;
    
    /**
   	 * Auslesen des Namens der Eigenschaft
   	 * @return name
   	 */
   	public String getName() {
   		return name;
   	}
   	
   	/**
   	 * Auslesen des Wertes der Eigenschaft
   	 * @return value
   	 */
   	public String getValue() {
   		return value;
   	}
   	
   	/**
   	 * Auslesen des Wertes der partnerProfileID
   	 * @return partnerProfileID;
   	 */
   	public int getPartnerProfileID() {
		return partnerProfileID;
	}
   	
   	/**
   	 * Setzen des Namens der Eigenschaft
   	 * @param name
   	 */
   	public void setName(String name) {
   		this.name = name;
   	}

   	/**
   	 * Setzen des Werts der Eigenschaft
   	 * @param value
   	 */
   	public void setValue(String value) {
   		this.value = value;
   	}
   	
   	/**
   	 * Setzen des Werts der partnerProfileID
   	 * @param partnerProfileID
   	 */
	public void setPartnerProfileID(int partnerProfileID) {
		this.partnerProfileID = partnerProfileID;
	}

}