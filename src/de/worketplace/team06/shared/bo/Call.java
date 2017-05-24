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
     * 
     */
    private int projectLeaderID;

    /**
     * 
     */
    private int partnerProfileID;

    /**
     *  
     */
    private String title;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Date deadline;

    /**
     *  "projectLeaderID" holen.
     */
    public int getProjectLeaderID() {
        return this.projectLeaderID;
    }

    /**
     * "title" holen.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * "description" holen.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * "deadline" Datum holen. 
     */
    public Date getDeadline() {
        return this.deadline;
    }

    /**
     * "projectLeaderID" setzen.
     */
    public void setProjektLeaderID(int projectLeaderID) {
        this.projectLeaderID = projectLeaderID;
    }

    /**
     * "title setzen.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * description setzen.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * deadline setzen.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

	/**
	 * @return the partnerProfileID
	 */
	public int getPartnerProfileID() {
		return partnerProfileID;
	}

	/**
	 * @param partnerProfileID the partnerProfileID to set
	 */
	public void setPartnerProfileID(int partnerProfileID) {
		this.partnerProfileID = partnerProfileID;
	}

}