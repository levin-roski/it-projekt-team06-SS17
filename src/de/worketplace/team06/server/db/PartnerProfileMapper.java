package de.worketplace.team06.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import de.worketplace.team06.shared.bo.PartnerProfile;
import de.worketplace.team06.shared.bo.Project;
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
 * @author Patrick
 */
public class PartnerProfileMapper {

	private static PartnerProfileMapper partnerProfileMapper = null;
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
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
	 * 
	 * 
	 * 
	 * @return Vektor<PartnerProfile>
	 */
	
    public Vector<PartnerProfile> findAll() {
        Connection con = DBConnection.connection();
        Vector<PartnerProfile> result = new Vector<PartnerProfile>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT * FROM partnerprofile ORDER BY id");
        	//noch vervollständigen 
 
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
    
    
    public PartnerProfile insert (PartnerProfile part) {
        Connection con = DBConnection.connection();
        
        try {
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM partnerprofile ");
        	
        	if (rs.next()){
        	part.setID(rs.getInt("maxid") + 1);
        	stmt = con.createStatement();
        	
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

//	public PartnerProfile findPartnerProfileByID(Integer partnerProfileID) {
//		 Connection con = DBConnection.connection();
//	        
//	        try {
//	        	Statement stmt = con.createStatement();
//	        	
//	        	stmt.executeUpdate();
//	        	
//	        }
//	        catch (SQLException e){
//	        	e.printStackTrace();
//	        }
//		return null;
//	}
	
}
