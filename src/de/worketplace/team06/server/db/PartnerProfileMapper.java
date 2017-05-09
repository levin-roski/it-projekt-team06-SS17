package de.worketplace.team06.server.db;

import java.util.*;
/**
 * 
 */
public class PartnerProfileMapper {

	private static PartnerProfileMapper partnerProfileMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PartnerProfileMapper(){
		
	}
	
	public static PartnerProfileMapper partnerProfileMapper(){
		if (partnerProfileMapper == null){
			partnerProfileMapper = new PartnerProfileMapper();
		}
		return partnerProfileMapper; 
	}
	
	 /**
     * Default constructor
     */
  
    public PartnerProfile insert(PartnerProfile partnerProfile) {
        // TODO implement here
        return null;
    }

    /**
     * @param partnerProfile 
     * @return
     */
    public PartnerProfile update(PartnerProfile partnerProfile) {
        // TODO implement here
        return null;
    }

    /**
     * @param partnerProfile
     */
    public void delete(PartnerProfile partnerProfile) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Vector<PartnerProfile> findAll() {
        // TODO implement here
        return null;
    }

    /**
     * @param property 
     * @return
     */
    public Vector<PartnerProfile> findPartnerProfileByProperty(PropertyMapper property) {
        // TODO implement here
        return null;
    }
	
}
