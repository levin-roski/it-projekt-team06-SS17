package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.worketplace.team06.shared.bo.Property;

/**
 * Die Mapper-Klasse PropertyMapper bildet Property-Objekte als Datensätze
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
 * @see RatingMapper
 * @see TeamMapper
 * 
 * @author Patrick
 * @author Theresa
 */

public class PropertyMapper {
	/**
     * Die Klasse PropertyMapper wird nur einmal instanziiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.
     * <p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal für
     * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     * 
     * @author Thies
     * @author Theresa
     */
	private static PropertyMapper propertyMapper = null;
	/**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   * 
	   * @author Thies 
	   */
	protected PropertyMapper(){
		
	}
	
	/**
	 * Diese Methode stellt sicher, dass die Singleton-Eigenschaft gegeben ist. Sie sorgt
	 * dafür, dass nur eine einzige Instanz der ProprtyMapper-Klasse existiert. 
	 * PropertyMapper sollte nicht über den New-Operator, sondern über den 
	 * Aufruf dieser statischen Methode aufgerufen werden.
	 * 
	 * @return PropertyMapper
	 * @author Thies
	 * @author Theresa
	 */
	public static PropertyMapper propertyMapper(){
		if (propertyMapper == null){
			propertyMapper = new PropertyMapper();
		}
		return propertyMapper; 
	}
	/**
	 * Auslesen eines Property-Objekts anhand übergebener PropertyID. Durch die Eindeutigkeit der ID
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param prop
	 * @return Property-Objekt, das der übergebenen ID entspricht
	 * @author Theresa
	 */
	public Property findById (Integer id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, created, name, value, partnerprofile_id FROM property " + "WHERE id= " + id);
    		/*
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
    		if (rs.next()) {
    			Property prop = new Property();
				prop.setID(rs.getInt("id"));
				prop.setName(rs.getString("name"));
				prop.setCreated(rs.getTimestamp("created"));
        		prop.setValue(rs.getString("value"));
        		prop.setPartnerProfileID(rs.getInt("partnerprofile_id"));
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
  
	/**
	 * Auslesen aller Eigenschaften aus der Datenbank
	 * 
	 * @return Vektor<Property>
	 * @author Theresa
	 */
    public Vector<Property> findAll() {
        Connection con = DBConnection.connection();
        Vector<Property> result = new Vector<Property>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, created, name, value, partnerprofile_id "+ "FROM Property ");
        	
        	while (rs.next()){
        		Property prop = new Property();
				prop.setID(rs.getInt("id"));
				prop.setName(rs.getString("name"));
				prop.setCreated(rs.getTimestamp("created"));
        		prop.setValue(rs.getString("value"));
        		prop.setPartnerProfileID(rs.getInt("partnerprofile_id"));
        		
        		result.addElement(prop);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    
    /**
	 * Auslesen eines Property-Objekts anhand der übergebenen PartnerProfile-ID.
	 * Die Eigenschaften werden aus dem jeweiligen Partnerprofil ausgelesen. 
	 * 
	 * @param partnerProfileID 
	 * @return Vektor<Property>
	 * @author Theresa
	 */
	public Vector<Property> findByPartnerProfileID(Integer partnerProfileID) {
		
		Connection con = DBConnection.connection();
		Vector<Property> result = new Vector<Property>();
		
        try {
        	Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, created, name, value, partnerprofile_id "
					+ " FROM property WHERE partnerprofile_id ='" + partnerProfileID + "'ORDER BY id");
			
			while (rs.next()){
				Property prop = new Property();
				prop.setID(rs.getInt("id"));
				prop.setName(rs.getString("name"));
				prop.setCreated(rs.getTimestamp("created"));
        		prop.setValue(rs.getString("value"));
        		prop.setPartnerProfileID(rs.getInt("partnerprofile_id"));
        		
        		result.add(prop);	
			}
			return result;
        }
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
    		return null;
	}
    
	/**
     * Einfuegen eines Property-Objekt in die Datenbank. Dabei wird auch der Primaerschluessel
     * des uebergebenen Objektes geprueft und ggf. berichtigt.
     * 
     * @param prop
     * @return Property 
     * @throws  
     * @author Thies 
     * @author Theresa
     */
	
    public Property insert (Property prop) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	/*
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoeht und an an prop vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM property ");
        	
        	if (rs.next()){
        	prop.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
			// Einfuegeoption, damit das neue Property-Tupel in die Datenbank eingefuegt werden kann.
        	stmt.executeUpdate("INSERT INTO property (id, created, name, value, partnerprofile_id) " 
        	+ "VALUES (" 
        		+ prop.getID() + ", '"
        		+ prop.getCreated() + "', '" 
        		+ prop.getName() + "', '" 
        		+ prop.getValue() + "', "
        		+ prop.getPartnerProfileID() +")");
        	}
        }
        catch (SQLException e){
    	e.printStackTrace();
        }
    
        return prop;
    } 
    /**
     * Methode ermoeglicht, dass ein Property-Objekt in der Datenbank aktualisiert werden kann.
     * 
     * @param prop
     * @return Property
     * @author Theresa
     */
    public Property update (Property prop) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE property " 
        	+ "SET value=\"" + prop.getValue() + "\", "
        	+ "created=\"" +  prop.getCreated() + "\", " 
        	+ "name=\"" +  prop.getName() + "\" " 
        	+ "WHERE id=" + prop.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return prop;
    }
    
    /**
     * Loeschen eines Property-Objektes aus der Datenbank.
     * 
     * @param prop
     * @author Theresa
     */
    public void delete(Property prop) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("DELETE FROM property " + "WHERE id=" + prop.getID());
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
    }
    
    /**
     * Loeschen der Eigenschaft anhand der übergebenen bzw. zugehörigen PartnerProfileID.
     * 
     * @param id
     * @author Theresa
     */
    
    public void deleteByPartnerProfileID(Integer id) {
    	 Connection con = DBConnection.connection();
    	 
    	 try{
    		Statement stmt = con.createStatement();
         	
         	stmt.executeUpdate("DELETE FROM property " + "WHERE partnerprofile_id=" + id);
    	 }
    	 catch (SQLException e){
         	e.printStackTrace();
         }
    	
    }
}
