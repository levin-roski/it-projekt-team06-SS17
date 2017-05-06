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
     * Variable für das Datum der letzten Änderung.
     */
    private Date lastEdit;

    /**
     * Datum der letzten Änderung holen.
     */
    public Date getLastedit() {
        return this.lastEdit;
    }

    /**
     * Datum der letzten Änderung setzen. 
     */
    public void setLastedit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

}