package de.worketplace.team06.server.db;

import java.util.*;

import de.worketplace.team06.shared.bo.Rating;
/**
 * 
 */
public class RatingMapper {

	private static RatingMapper ratingMapper = null;
	 /**
	   * Gesch�tzter Konstruktor - verhindert die M�glichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	protected RatingMapper(){
		
	}
	
	public static RatingMapper ratingMapper(){
		if (ratingMapper == null){
			ratingMapper = new RatingMapper();
		}
		return ratingMapper; 
	}

    /**
     * @param rating 
     * @return
     */
    public Rating insert(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * @param rating 
     * @return
     */
    public Rating update(Rating rating) {
        // TODO implement here
        return null;
    }

    /**
     * @param rating
     */
    public void delete(Rating rating) {
        // TODO implement here
    }

    /**
     * @return
     */
    public Vector<Rating> findAll() {
        // TODO implement here
        return null;
    }
    
    public Rating findById(int id){
    	// TODO implement here
    	return null;
    }
	
}
