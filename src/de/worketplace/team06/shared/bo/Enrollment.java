package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Enrollment extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Enrollment() {
    }

    /**
     * Variable für das Startdatum
     */
    private Date startDate;

    /**
     * Variable für das Enddatum
     */
    private Date endDate;

//    /**
//     * Variable für die Dauer der Beteiligung
//     */
//    private Integer period;
    
    /**
     * Variable für den geschätzen Workload in Stunden
     */
    public Integer workload;
    
    /**
     * ID für die Bewertung der Beteiligung
     */
    private Integer ratingID;
    
    /**
     * ID für das Projekt der Beteiligung
     */
    private Integer projectID;
    
    /**
     * ID für die OrganisationsEinheit für die Beteiligung
     */
    private Integer orgaUnitID;

    /**
     * Auslesen des Startdatums
     * @return startDate
     */
    public Date getStartDate() {
       return this.startDate;
    }

    /**
     * Auslesen des Enddatums
     * @return endDate
     */
    public Date getEndDate() {
        return this.endDate;
    }

//    /**
//     * Auslesen der Dauer der Beteiligung
//     */
//    public Integer getPeriod() {
//        return this.period;
//    }
    
    /**
     * Auslesen des Workloads in Tagen
     * @return workload
     */
    public Integer getWorkload() {
    	return this.workload;
    }
    
    /**
     * Auslesen der ID für die Organisationseinheit
     * @return ratingID
     */
	public Integer getRatingID() {
		return this.ratingID;
	}
	
	/**
	 * Auslesen der ID für das Projekt
	 * @return projectID
	 */
	public Integer getProjectID() {
		return this.projectID;
	}
	
	/**
	 * Auslesen der ID für die OrganisationsEinheit
	 * @return orgaUnitID
	 */
	public Integer getOrgaUnitID() {
		return this.orgaUnitID;
	}

    /**
     * Setzen des Startdatums
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Setzen des Enddatums
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

//	/**
//	 * Setzen der Dauer der Beteiligung. Die Periode wird automatisch anhand des Start- und Enddatums berechnet.
//	 */
//	public void setPeriod(Integer per) {
//	   this.period = per;
//	 }
    
//    /**
//     * Setzen der Dauer der Beteiligung. Die Periode wird automatisch anhand des Start- und Enddatums berechnet.
//     */
//    public void setPeriod() {
//                     
//      //Die Zeit in Millisekunden
//      long x = this.startDate.getTime();
//      long y = this.endDate.getTime();
//      
//      //Die Millisekunden in Tage umrechnen
//      long z = (x - y) / 1000 / 60 / 60 / 24;
//              
//      this.period = (Integer) z;
//    }
    
    /**
     * setzen des Workloads in Tagen
     * @param workload
     */
    public void setWorkload(Integer w) {
    	this.workload = w;
    }
    
    /**
     * Setzen der ID für die Bewertbung
     * @param ratingID
     */
	public void setRatingID(Integer ratingID) {
		this.ratingID = ratingID;
	}

	/**
	 * Setzen der ID für das Projekt
	 * @param projectID
	 */
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	/**
	 * Setzen der ID für die OrganisationsEinheit
	 * @param orgaUnitID
	 */
	public void setOrgaUnitID(Integer orgaUnitID) {
		this.orgaUnitID = orgaUnitID;
	}

}