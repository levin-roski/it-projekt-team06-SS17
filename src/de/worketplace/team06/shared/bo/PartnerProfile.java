package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class PartnerProfile extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public PartnerProfile() {
    }

    /**
     * Variable für das Datum der letzten Änderung
     */
    private Date lastEdit;
    
    private Vector<Property> propertyList;

    /**
     * Auslesen des Datums der letzten Änderung
     * @return lastEdit
     */
    public Date getLastedit() {
        return this.lastEdit;
    }

    /**
     * Auslesen der Liste der Eigenschaften
	 * @return propertyList
	 */
	public Vector<Property> getPropertyList() {
		return propertyList;
	}  
    
    /**
     * Setzen des Datums der letzten Änderung 
     * @param lastEdit
     */
    public void setLastedit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    /**
     * Setzen der Eigenschaften in einer Liste
	 * @param propertyList
	 */
	public void setPropertyList(Vector<Property> propertyList) {
		this.propertyList = propertyList;
	}      

}