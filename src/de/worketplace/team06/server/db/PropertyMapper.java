package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.Rating;
import de.worketplace.team06.shared.bo.Project;
import de.worketplace.team06.shared.bo.Property;

public class PropertyMapper {
	/**
     * Die Klasse PropertyMapper wird nur einmal instantiiert. Man spricht hierbei
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
	 * Aufruf dieser statischen Methode.
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
	 * Suchen einer Property mit vorgegebener PropertyID. Duch die Eindeutigkeit der ID, 
	 * wird genau ein Objekt zurück gegeben. 
	 * 
	 * @param prop
	 * @return Property-Objekt, das der übergebenen ID entspricht
	 */
	public Property findById (int id){
    	Connection con = DBConnection.connection();
    	
    	try{
    		Statement stmt= con.createStatement();
    		ResultSet rs = stmt.executeQuery
    				("SELECT id, created, name, value, FROM Property " + "WHERE id= " + id);
    		/**
			 * es kann maximal nur ein Tupel zurueckgegeben werden, da ID Primaerschluessel ist, 
			 * dann wird geprueft, ob für diese ID ein Tupel in der DB vorhanden ist
			 */
    		if (rs.next()) {
    			Property prop = new Property();
    			prop.setID(rs.getInt("id"));
    			prop.setCreated(rs.getTimestamp("created"));
    			prop.setName(rs.getString("name"));
    			prop.setValue(rs.getDouble("value"));
    		}	
    	}
    	catch (SQLException e){
    		e.printStackTrace();
    	}
    	return null;
    }
  
    public Vector<Property> findAll() {
        Connection con = DBConnection.connection();
        Vector<Property> result = new Vector<Property>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, created, name, value "+ "FROM Property ");
        	
        	while (rs.next()){
        		Property prop = new Property();
        		prop.setID(rs.getInt("id"));
        		prop.setCreated(rs.getTimestamp("created"));
        		prop.setName(rs.getString("name"));
        		prop.setValue(rs.getDouble("value"));
        		
        		result.addElement(prop);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return result ;
    }
    /**
	 * Diese Methode findet ein Property-Objekt, anhand der übergebenen PartnerProfile-ID 
	 * 
	 * @param partnerProfileID 
	 * @return Property-Objekt 
	 */
	public Vector<Property> findByPartnerProfileID(int partnerProfileID) {
		
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
        		prop.setValue(rs.getDouble("value"));
        		prop.setPartnerProfileID(rs.getInt("partnerprofile_id"));
        		
        		result.add(prop);	
			}
        }
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
    		return result;
	}
    
	/**
     * Einfuegen eines Property-Objektes in die Datenbank. Dabei wird auch der Primaerschluessel
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
        	/** 
			 * Abfragen des hoechsten Primaerschluesselwertes, die aktuelle ID 
			 *wird dann um 1 erhoet und an an prop vergeben
			*/
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM property ");
        	
        	if (rs.next()){
        	prop.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	/**
			 * Einfuegeoption, damit das neue Team-Tupel in die Datenbank eingefuegt werden kann
			 */
        	stmt.executeUpdate("INSERT INTO rating (id, created, name, value) " 
        	+ "VALUES (" 
        		+ prop.getID() + ", "
        		+ prop.getCreated() + ", " 
        		+ prop.getName() + ", " 
        		+ prop.getValue() 
        		+ "','" +"')");
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
     */
    public Property update(Property prop) {
        Connection con = DBConnection.connection();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	stmt.executeUpdate("UPDATE property " 
        	+ "SET value=\"" + prop.getValue() + "\", "
        	+ "created=\"" +  prop.getCreated() + "\" " 
        	+ "name=\"" +  prop.getName() + "\" " 
        	+ "WHERE id=" + prop.getID());
        }
        
        catch (SQLException e){
        	e.printStackTrace();
        } 
        return prop;
    }
    /**
     * Loeschen eines Property-Objektes aus der Datenbank
     * @param prop
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
    
    public void deleteByPartnerProfileID(int id) {
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
