package de.worketplace.team06.shared.bo;

import java.sql.Date;
import java.sql.Timestamp;
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
    private Timestamp lastEdit;
    
    
    /**
     * Auslesen des Datums der letzten Änderung
     * @return lastEdit
     */
    public Timestamp getLastEdit() {
        return this.lastEdit;
    }

	
    /**
     * Setzen des Datums der letzten Änderung 
     * @param lastEdit
     */
    public void setLastEdit(Timestamp lastEdit) {
        this.lastEdit = lastEdit;
    }

  
}