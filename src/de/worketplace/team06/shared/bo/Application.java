package de.worketplace.team06.shared.bo;

import java.util.*;

/**
 * 
 */
public class Application extends BusinessObject {


	/**
	 * ID Zur Serialisierung und Prüfung der Version einer Klasse.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Default constructor
     */
    public Application() {
    	this.setStatus(0);
    }

    /**
     * Variable für das Speichern des Bewerbungstextes
     */
    private String applicationText;
    
    /**
     * Variable für den Status der Bewerbung
     * -1 abgelehnt
     * 0 laufend
     * 1 angenommen
     */
    private Integer status;

    /**
     * ID der dazgehörigen Ausschreibung
     */
    private Integer callID;
    
    /**
     * ID der dazgehörigen OrganisationsEinheit
     */
    private Integer orgaUnitID;
    
    /**
     * ID der zugehörigen ratingID
     */
    private Integer ratingID;
    
    /**
     * Auslesen des Bewerbungstextes
     * @return applicationText
     */
    public String getText() {
        return applicationText;
    }
    
    /**
     * Auslesen des Status für die Bewerbung
     * -1 abgelehnt
     * 0 laufend
     * 1 angenommen
     * 
     * @param status
     */
	public String getStatus() {
		
        String s = new String();
        
		/*
		 * Über eine Switch-Case Abfrage wird herausgefunden, welcher Status derzeit besteht
		 * und entsprechend als String zurückgegeben.
		 */
        switch(this.status){ 
        case -1: 
        	s = "Abgelehnt";
        	break;
		case 0: 
        	s = "Laufend";
        	break;
		case 1: 
        	s = "Angenommen";
        	break;
        }
		return s;
	}

    /**
     *  Auslesen der ID der Ausschreibung
     *  @return callID
     */
	public Integer getCallID() {
		return callID;
	}
	
    /**
     *  Auslesen der ID der OrganisationsEinheit
     *  @return orgaUnitID
     */
	public Integer getOrgaUnitID() {
		return orgaUnitID;
	}
	
	/**
	 * Auslesen der ID der zugehörigen Rating Instanz
	 * @return ratingID;
	 */
	public Integer getRatingID() {
		return ratingID;
	}
    
    /**
     * Setzen des Bewerbungstextes
     * @param applicationText
     */
    public void setText(String applicationText) {
        this.applicationText = applicationText;
    }
    
    /**
     * Setzen des Status für die Bewerbung
     * -1 abgelehnt
     * 0 laufend
     * 1 angenommen
     * 
     * @param status
     */
	public void setStatus(Integer status) {
		this.status = status;
	}
    
    /**
     *  Setzen der ID der Ausschreibung
     *  @param callID
     */
	public void setCallID(Integer id) {
		this.callID = id;
	}

    /**
     *  Setzen der ID der OrganisationsEinheit
     *  @param orgaUnitID
     */
	public void setOrgaUnitID(Integer id) {
		this.orgaUnitID = id;
	}

	/**
	 * Setzen der ID der zugehörigen Rating Instanz
	 * @param ratingID
	 */
	public void setRatingID(Integer ratingID) {
		this.ratingID = ratingID;
	}

}