package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Call extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Call() {
    }

    /**
     * Variable für den Titel der Ausschreibung
     */
    private String title;

    /**
     * Variable für die Beschreibung der Ausschreibung
     */
    private String description;

    /**
     * Datum für die Deadline einer Ausschreibung
     */
    private Date deadline;
    
    /**
     * ID für das Projekt
     */
    private int projectID;
    
    /**
     * ID für den Projektleiter
     */
    private int projectLeaderID;

    /**
     * ID für das PartnerProfil
     */
    private int partnerProfileID;

    /**
     * Auslesen der ID für den Projektleiter
     */
    public int getProjectLeaderID() {
        return this.projectLeaderID;
    }

    /**
     * Auslesen des Titels für die Ausschreibung
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Auslesen der Beschreibung für die Ausschreibung
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Auslesen des Datums für die Deadline
     */
    public Date getDeadline() {
        return this.deadline;
    }
    
    /**
     * auslesen der ID für das Projekt
     * @return projectID
     */
	public int getProjectID() {
		return projectID;
	}
    
	/**
	 * Auslesen der ID für das PartnerProfil
	 * @return the partnerProfileID
	 */
	public int getPartnerProfileID() {
		return partnerProfileID;
	}

    /**
     * Setzen der ID für den Projektleiter
     */
    public void setProjectLeaderID(int projectLeaderID) {
        this.projectLeaderID = projectLeaderID;
    }

    /**
     * Setzen des Titels für die Ausschreibung
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setzen der Beschreibung für die Ausschreibung
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setzen des Datums für die Deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

	/**
	 * Setzen der ID für das Projekt
	 * @param projectID
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
    
	/**
	 * Setzen der ID für das PartnerProfil
	 * @param partnerProfileID the partnerProfileID to set
	 */
	public void setPartnerProfileID(int partnerProfileID) {
		this.partnerProfileID = partnerProfileID;
	}


}