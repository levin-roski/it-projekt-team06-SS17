package de.worketplace.team06.server.db;

import java.util.*;

/**
 * 
 */
public class PropertyMapper {

	private static PropertyMapper propertyMapper = null;
	 /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected PropertyMapper(){
		
	}
	
	public static PropertyMapper propertyMapper(){
		if (propertyMapper == null){
			propertyMapper = new PropertyMapper();
		}
		return propertyMapper; 
	}

    /**
     * @param property 
     * @return
     */
    public Property insert(Property property) {
        // TODO implement here
        return null;
    }

    /**
     * @param property 
     * @return
     */
    public Property update(Property property) {
        // TODO implement here
        return null;
    }

    /**
     * @param property 
     * @return
     */
    public Property delete(void property) {
        // TODO implement here
        return null;
    }

	
}
