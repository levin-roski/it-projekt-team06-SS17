package de.worketplace.team06.shared.bo;

import java.util.Date;

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
    	this.setStatus(0);
    	this.setMatchingCount(0);
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
     * Variable für den Status der Ausschreibung
     * 2 abgebrochen
     * 0 laufend
     * 1 erfolgreich besetzt
     */
    private Integer status;
    /**
     * Variable für das speichern, der übereinstimmenden Properties
     * mit einem PartnerProfile einer OrgaUnit
     */
    private Integer matchingCount;
    
    /**
     * ID für das Projekt
     */
    private Integer projectID;
    
    /**
     * ID des Ausschreibenden 
     */
    private Integer callerID;

    /**
     * ID für das PartnerProfil
     */
    private Integer partnerProfileID;

    /**
     * Auslesen der ID für den Projektleiter
     * @return projectLeaderID
     */
    public Integer getCallerID() {
        return this.callerID;
    }

    /**
     * Auslesen des Titels für die Ausschreibung
     * @return title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Auslesen der Beschreibung für die Ausschreibung
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Auslesen des Datums für die Deadline
     * @return deadline
     */
    public Date getDeadline() {
        return this.deadline;
    }
    
    /**
     * Auslesen des Status für die Ausschreibung
     * -1 abgebrochen
     * 0 laufend
     * 1 erfolgreich besetzt
     * 
     * @param status
     */
	public Integer getStatus() {
      return this.status;
	}
	
	  /**
     * Auslesen des Status für die Ausschreibung
     * 2 abgebrochen
     * 0 laufend
     * 1 erfolgreich besetzt
     * 
     * @param s
     */
	public String getStatusString() {
        String s = new String();
        
		/*
		 * Über eine Switch-Case Abfrage wird herausgefunden, welcher Status derzeit besteht
		 * und entsprechend als String zurückgegeben.
		 */
        switch(this.status){ 
        case 2: 
        	s = "Abgebrochen";
        	break;
		case 0: 
        	s = "Laufend";
        	break;
		case 1: 
        	s = "Erfolgreich";
        	break;
        }
		return s;
	}
    
    /**
     * auslesen der ID für das Projekt
     * @return projectID
     */
	public Integer getProjectID() {
		return projectID;
	}
    
	/**
	 * Auslesen der ID für das PartnerProfil
	 * @return the partnerProfileID
	 */
	public Integer getPartnerProfileID() {
		return partnerProfileID;
	}
	
	/**
	 * Auslesen des MatchingCounts
	 * @return matchingCount
	 */
	public Integer getMatchingCount() {
		return matchingCount;
	}
    /**
     * Setzen der ID für den Projektleiter
     * @param projectLeaderID
     */
    public void setCallerID(Integer projectLeaderID) {
        this.callerID = projectLeaderID;
    }

    /**
     * Setzen des Titels für die Ausschreibung
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setzen der Beschreibung für die Ausschreibung
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setzen des Datums für die Deadline
     * @param deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    
    /**
     * Setzen des Status für die Ausschreibung
     * -1 abgebrochen
     * 0 laufend
     * 1 erfolgreich besetzt
     * 
     * @param status
     */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Setzen der ID für das Projekt
	 * @param projectID
	 */
	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}
    
	/**
	 * Setzen der ID für das PartnerProfil
	 * @param partnerProfileID
	 */
	public void setPartnerProfileID(Integer partnerProfileID) {
		this.partnerProfileID = partnerProfileID;
	}

	/**
	 * Setzen des Matching Zählers
	 * @param matchingCount
	 */
	public void setMatchingCount(Integer matchingCount) {
		this.matchingCount = matchingCount;
	}
	
	
    public boolean equals(Call c) {
   	 /*
        * Abfragen, ob ein Objekt ungleich NULL ist und ob ein Objekt gecastet
        * werden kann, sind immer wichtig!
        */
       if (c != null && c instanceof Call) {
         try {
           if (c.getID() == this.getID())
             return true;
         }
         catch (IllegalArgumentException e) {
           /*
            * Wenn irgendetwas schief geht, dann geben wir sicherheitshalber false
            * zurück.
            */
           return false;
         }
       }
       /*
        * Wenn bislang keine Gleichheit bestimmt werden konnte, dann müssen
        * schließlich false zurückgeben.
        */
       return false;
   }

}