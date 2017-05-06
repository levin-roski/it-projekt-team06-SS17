package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Participation extends BusinessObject {

    /**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Participation() {
    }

    /**
     *  Variable für das Startdatum.
     */
    private Date startDate;

    /**
     * Variable für das Enddatum.
     */
    private Date endDate;

    /**
     * Variable für die Dauer der Beteiligung
     */
    private int period;

    /**
     * Startdatum holen.
     */
    public Date getStartDate() {
       return this.startDate;
    }

    /**
     * Enddatum holen
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Dauer der Beteiligung holen.
     */
    public int getPeriod() {
        return this.period;
    }

    /**
     * Startdatum setzen
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Enddatum setzen. 
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Dauer der Beteiligung setzen.
     */
    public void setPeriod(int period) {
        this.period = period; 
    }

}