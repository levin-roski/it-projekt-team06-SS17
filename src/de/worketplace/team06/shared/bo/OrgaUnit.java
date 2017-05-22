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
     * Deklaration der Attribute.
     */
    private String googleID;  
    
    private String description;

    private int partnerprofileID;
    
    /**
     * Getter Methoden für die einzelnen Attribute setzen.
     */
    public String getGoogleID() {
        return this.googleID;
    }
    
    public String getDescription() {
        return this.description;
    }

    public int getPartnerProfileID() {
        return this.partnerprofileID;
    }

    /**
     * Setter Methoden für die einzelnen Attribute setzen. 
     */
    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setPartnerProfileID(int partnerProfileID) {
        this.partnerprofileID = partnerProfileID;
    }

}