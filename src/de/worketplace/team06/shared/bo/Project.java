package de.worketplace.team06.shared.bo;

import java.util.Date;

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
     * Variable für den Titel des Projektes
     */
    private String title;

    /**
     * Variable für die Beschreibung des Projektes
     */
    private String description;

    /**
     * Variable für die ID des Projektleiters
     */
    private Integer projectLeaderID;

    /**
     * Variable für die ID des Projektinhabers
     * 
     * Ausgeklammert. Wird nicht benutzt.
     */
//    private Integer projectOwnerID;
    
    /**
     * Variable für die ID des Marktplatzes
     */
    private Integer marketplaceID;

    /**
     * Variable für das Startdatum des Projektes
     */
    private Date startDate;

    /**
     * Variable für das Enddatum des Projektes
     */
    private Date endDate;
    
    /**
	 * Auslesen des Titels für das Projekt
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Auslesen der Beschreibung für das Projekt
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Auslesen der ID des Projektleiters
	 * @return projectLeaderID
	 */
	public Integer getProjectLeaderID() {
		return projectLeaderID;
	}

	/**
	 * Auslesen der ID des Projektleiters
	 * @return projectOwnerID
	 * 
	 * Ausgeklammert. Wird nicht benutzt.
	 */
//	public Integer getProjectOwnerID() {
//		return projectOwnerID;
//	}

	/**
	 * Auslesen des Startdatums für das Projekt
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Auslesen des Enddatums für das Projekt
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Auslesen der ID des Marktplatzes, zu welchem das Projekt gehört
	 * @return marketplaceID
	 */
	public Integer getMarketplaceID() {
		return marketplaceID;
	}
	
	/**
	 * Setzen des Titels für das Projekt
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Setzen der Beschreibung für das Projekt
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Setzen der ID des Projektleiters
	 * @param projectLeaderID
	 */
	public void setProjectLeaderID(Integer projectLeaderID) {
		this.projectLeaderID = projectLeaderID;
	}

	/**
	 * Setzen der ID des Projektinhabers
	 * @param projetOwnerID 
	 * 
	 * Ausgeklammert. Wird nicht benutzt.
	 */
//	public void setProjectOwnerID(Integer projectOwnerID) {
//		this.projectOwnerID = projectOwnerID;
//	}

	/**
	 * Sezen des Startdatums für das Projekt
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Setzen des Enddatums für das Projekt
	 * @param endDate 
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Setzen der ID des Marktplatzes
	 * @param marketplaceID
	 */
	public void setMarketplaceID(Integer marketplaceID) {
		this.marketplaceID = marketplaceID;
	}

    
}