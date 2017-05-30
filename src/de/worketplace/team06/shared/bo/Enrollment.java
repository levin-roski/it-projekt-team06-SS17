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
//    private int period;
    
    
    /**
     * Variable für den geschätzen Workload in Arbeitstagen
     */
    public int workload;
    
    /**
     * ID für die Bewertung der Beteiligung
     */
    private int ratingID;
    
    /**
     * ID für das Projekt der Beteiligung
     */
    private int projectID;
    
    /**
     * ID für die OrganisationsEinheit für die Beteiligung
     */
    private int orgaUnitID;

    /**
     * Auslesen des Startdatums
     */
    public Date getStartDate() {
       return this.startDate;
    }

    /**
     * Auslesen des Enddatums
     */
    public Date getEndDate() {
        return this.endDate;
    }

//    /**
//     * Auslesen der Dauer der Beteiligung
//     */
//    public int getPeriod() {
//        return this.period;
//    }
    
    /**
     * Auslesen des Workloads in Tagen
     * @return workload
     */
    public int getWorkload() {
    	return this.workload;
    }
    
    /**
     * Auslesen der ID für die Organisationseinheit
     * @return
     */
	public int getRatingID() {
		return this.ratingID;
	}
	
	/**
	 * Auslesen der ID für das Projekt
	 * @return
	 */
	public int getProjectID() {
		return this.projectID;
	}
	
	/**
	 * Auslesen der ID für die OrganisationsEinheit
	 * @return
	 */
	public int getOrgaUnitID() {
		return this.orgaUnitID;
	}

    /**
     * Setzen des Startdatums
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Setzen des Enddatums
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

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
//      this.period = (int) z;
//    }
    
    /**
     * setzen des Workloads in Tagen
     * @param workload
     */
    public void setWorkload(int w) {
    	this.workload = w;
    }
    
    /**
     * Setzen der ID für die Bewertbung
     * @param ratingID
     */
	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

	/**
	 * Setzen der ID für das Projekt
	 * @param projectID
	 */
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	/**
	 * Setzen der ID für die OrganisationsEinheit
	 * @param orgaUnitID
	 */
	public void setOrgaUnitID(int orgaUnitID) {
		this.orgaUnitID = orgaUnitID;
	}

}