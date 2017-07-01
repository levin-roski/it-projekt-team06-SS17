package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Rating;
/**
 * Die Mapper-Klasse RatingMapper bildet Rating-Objekte als Datensätze
 * in einer relationalen Datenbank ab. Durch die Bereitstellung verschiedener Methoden
 * können mit deren Hilfe Objekte erzeugt, verändert, gelöscht und ausgelesen werden.
 * Das Mapping erfolgt bidirektional: Objekte können in Datensätze und Datensätze in 
 * Objekte umgewandelt werden.
 * 
 * @see ApplicationMapper
 * @see CallMapper
 * @see EnrollmentMapper
 * @see MarketplaceMapper
 * @see OrganisationMapper
 * @see OrgaUnitMapper
 * @see PartnerProfileMapper
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see TeamMapper
 * 
 * @author Strepp
 * @author Vocke
 * @author Thies
 */


public class RatingMapper {
	
	/**
     * Die Klasse RatingMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     */

	private static RatingMapper ratingMapper = null;
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   */
	protected RatingMapper(){
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der RatingMapper-Klasse existiert. 
	 * RatingMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode ausgeführt werden.
	 * 
	 * @return RatingMapper
	 */
	
	public static RatingMapper ratingMapper(){
		if (ratingMapper == null){
			ratingMapper = new RatingMapper();
		}
		return ratingMapper; 
	}
	
	/**
	 * Auslesen einer bestimmten Bewertung. Durch die Eindeutigkeit der ID
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param r
	 * @return Rating-Objekt, das der übergebenen ID entspricht
	 */

	public Rating findById (Integer id){
    	Connection con = DBConnection.connection();
    	
    	try{    		
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, statement, created, rating FROM rating " + "WHERE id= " + id);
    		/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
    		if (rs.next()) {
    			Rating r = new Rating();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("statement"));
    			r.setCreated(rs.getTimestamp("created"));
    			r.setRating(rs.getFloat("rating"));
    			return r;
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
	
	/**
	 * Auslesen aller Bewertungen aus der Datenbank.
	 *  
	 * @return Vektor<Rating>
	 */
    public Vector<Rating> findAll() {
    	
        Connection con = DBConnection.connection();
        Vector<Rating> result = new Vector<Rating>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, statement, created, rating "+ "FROM rating ");
        	
        	while (rs.next()){
        		Rating r = new Rating();
        		r.setID(rs.getInt("id"));
        		r.setRatingStatement(rs.getString("statement"));
        		r.setCreated(rs.getTimestamp("created"));
        		r.setRating(rs.getFloat("rating"));
        		
        		result.addElement(r);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Einfuegen eines Rating-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
     * des uebergebenen Objektes geprueft und ggf. berichtigt.
     * 
     * @param r
     * @return Rating
     * @throws 
     */
    public Rating insert (Rating r) {
 
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	/*
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 * wird dann um 1 erhoeht und an an r vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM rating ");
        	
        	if (rs.next()){
        	r.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
			// Einfuegeoption, damit das neue Rating-Tupel in die Datenbank eingefuegt werden kann
        	stmt.executeUpdate("INSERT INTO rating (id, created, statement, rating) " 
        	+ "VALUES (" 
        		+ r.getID() + ", '"
        		+ r.getCreated() + "', '" 
        		+ r.getRatingStatement() + "', " 
        		+ r.getRating()
        		 +")");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return r;
    } 
    
    /**
     * Methode ermoeglicht, dass ein Rating-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param r 
     * @return Rating
     */
    
    public Rating update(Rating r) {

        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE rating " 
        	+ "SET statement=\"" + r.getRatingStatement() + "\", "
        	+ "created=\"" +  r.getCreated() + "\", " 
        	+ "rating=\"" +  r.getRating() + "\" " 
        	+ "WHERE id=" + r.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return r;
    }
    
    /**
     * Loeschen eines Rating-Objektes aus der Datenbank.
     * 
     * @param rating
     */

    public void delete(Rating r) {

        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM rating " + "WHERE id=" + r.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
    
    
    /**
	 * Auslesen eines Ratin-Objekts anhand der übergebenen ApplicationID.
	 * 
	 * @param ApplicationID
	 * @return Rating-Objekt 
	 */
    
    public Rating findRatingByApplicationID(Integer applicationID){

    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt = con.createStatement();
    		// Verbinden der Tabellen rating und application, da Rating-Objekt nach ApplicationID gesucht wird
    		ResultSet rs = stmt.executeQuery("SELECT * FROM rating INNER JOIN application " + 
    		"ON application.rating_id = rating.id WHERE application.id = " + applicationID);
    		
    		if (rs.next()){
    			Rating r = new Rating ();
    			r.setID(rs.getInt("id"));
    			r.setRatingStatement(rs.getString("statement"));
    			r.setRating(rs.getFloat("Rating"));
    			return r;
    		}
    	} 
    	catch (SQLException e2){
    		e2.printStackTrace();
    	}
    	return null;
    }
	
}
