package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.worketplace.team06.shared.bo.PartnerProfile;
/**
 * Die Mapper-Klasse PartnerProfileMapper bildet PartnerProfile-Objekte als Datensätze
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
 * @see PersonMapper
 * @see ProjectMapper
 * @see PropertyMapper
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Strepp
 * @author Vocke
 * @author Thies
 */
public class PartnerProfileMapper {

	/**
     * Die Klasse PartnerProfileMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     */
	private static PartnerProfileMapper partnerProfileMapper = null;
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   */
	protected PartnerProfileMapper(){
		
	}
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der PartnerProfile-Klasse existiert. 
	 * PartnerProfileMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode genutzt werden.
	 * 
	 * @return PartnerProfileMapper
	 */
	
	public static PartnerProfileMapper partnerProfileMapper(){
		if (partnerProfileMapper == null){
			partnerProfileMapper = new PartnerProfileMapper();
		}
		return partnerProfileMapper; 
	}
	
	/**
	 * Auslesen eines PartnerProfil-Objekts anhand der übergebenen ID. Durch die Eindeutigkeit der ID
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param id
	 * @return Partnerprofile-Objekt, das der uebergebenen ID entspricht
	 */
	
	public PartnerProfile findById (Integer id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT id, created, last_edit "
    				+ "FROM partnerprofile WHERE id= " + id);
    		
    		if (rs.next()) {
    			PartnerProfile part = new PartnerProfile();
    			part.setID(rs.getInt("id"));
    			part.setLastEdit(rs.getTimestamp("last_edit"));
    			part.setCreated(rs.getTimestamp("created"));
    	
    			return part;
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
	
	/**
	 * Auslesen aller PartnerProfile-Objekte aus der Datenbank.
	 * 
	 * @return Vektor<PartnerProfile>
	 */
	
    public Vector<PartnerProfile> findAll() {
        Connection con = DBConnection.connection();
        Vector<PartnerProfile> result = new Vector<PartnerProfile>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM partnerprofile ORDER BY id");
 
        	if (rs.next()){
        		PartnerProfile part = new PartnerProfile();
        		part.setID(rs.getInt("id"));
        		part.setLastEdit(rs.getTimestamp("last_edit"));
        		part.setCreated(rs.getTimestamp("created"));
        		
        		
        		result.addElement(part);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
     * Einfuegen eines PartnerProfile-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
	 * des uebergebenen Objektes geprueft und ggf. berichtigt.
	 * 
     * @param part
     * @return partnerprofile
     */
    
    public PartnerProfile insert (PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	/*
			 *Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoeht und an an part vergeben
			*/
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerprofile ");
        	
        	if (rs.next()){
        	part.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
			// Einfuegeoption, damit das neue PartnerProfile-Tupel in die Datenbank eingefuegt werden kann.
        	stmt.executeUpdate("INSERT INTO partnerprofile (id, last_edit, created) " 
        	+ "VALUES (" 
        	+ part.getID() + ",'" 
        	+ part.getLastEdit() + "','"
        	+ part.getCreated() + "')");
        	}
        }
       catch (SQLException e){
    	e.printStackTrace();
    }
    
    return part;
    } 
    
    /**
     * Methode ermoeglicht, dass ein Person-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param part
     * @return PartnerProfile
     */
    public PartnerProfile update(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE partnerprofile SET last_edit='" + part.getLastEdit() 
        	+ "' WHERE id=" + part.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return part;
    }

    /**
     * Loeschen eines PartnerProfile-Objektes aus der Datenbank.
     * 
     * @param part
     */
    
    public void delete(PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM partnerprofile WHERE id=" + part.getID());
        	
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
}
