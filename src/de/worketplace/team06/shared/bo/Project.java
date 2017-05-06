package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Project extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Project() {
    }


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
    private int projectLeaderID;

    /**
     * 
     */
    private int projectOwnerID;

    /**
     * 
     */
    private Date startDate;

    /**
     * 
     */
    private Date endDate;
    
    /**
	 * Projekttitel holen.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Projekttitel setzen.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Projektbeschreibung holen.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Projektbeschreibung setzen.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ID des Projektleiter holen. 
	 */
	public int getProjectLeaderID() {
		return projectLeaderID;
	}

	/**
	 * Id des Projektleiters setzen.
	 */
	public void setProjectLeaderID(int projectLeaderID) {
		this.projectLeaderID = projectLeaderID;
	}

	/**
	 * Id des Projektbesitzers holen.
	 */
	public int getProjectOwnerID() {
		return projectOwnerID;
	}

	/**
	 * Id des Projektbesitzers setzen. 
	 */
	public void setProjectOwnerID(int projectOwnerID) {
		this.projectOwnerID = projectOwnerID;
	}

	/**
	 * Startdatum des Projekts holen.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Startdatum des Projekts setzen. 
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Enddatum des Projekts holen. 
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Enddatum des Projekts setzen. 
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    
}