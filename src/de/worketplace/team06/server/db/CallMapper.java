package de.worketplace.team06.server.db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class CallMapper {
	
	private static CallMapper callMapper = null;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	 /**
	   * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	
	protected CallMapper() {
		
	}

	public static CallMapper callMapper() {
		if (callMapper == null) {
			callMapper = new CallMapper();
		}
		
		return callMapper;
	}


	/**
	 * Ausschreibung aktualisieren
	 * @param c
	 * @return
	 */
	
	public void update(Call c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		String deadline = sdf.format(c.getDeadline());
    		
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE projektmarktplatz.`call` SET"
					+ " projektmarktplatz.`call`.title='" + c.getTitle() +
					"', projektmarktplatz.`call`.description= '" + c.getDescription() +
					"', projektmarktplatz.`call`.deadline= '" + deadline +
					"', projektmarktplatz.`call`.project_id= " + c.getProjectID() + 
					", projektmarktplatz.`call`.orgaunit_id= " + c.getCallerID() +
					", projektmarktplatz.`call`.partnerProfile_id= " + c.getPartnerProfileID() +
					", projektmarktplatz.`call`.status= " + c.getStatus() + 
					" WHERE projektmarktplatz.`call`.id= " + c.getID());
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();	
    	}
	}

	/**
	 * Ausschreibung in der Datenbank anlegen 
	 * @param c
	 * @return
	 */
	
	public Call insert(Call c) {
		Connection con = DBConnection.connection();
		
		try {
			String deadline = sdf.format(c.getDeadline());
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM projektmarktplatz.`call`");			
			if (rs.next()) {
				
				c.setID(rs.getInt("maxid") + 1);
		
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO projektmarktplatz.`call` (id, created, title, description, deadline, orgaunit_id, project_id, status) " 
				+ "VALUES (" + c.getID() + ",'" + c.getCreated() + "','" 
				+ c.getTitle() + "','" + c.getDescription() +  "','" 
				+ deadline + "'," + c.getCallerID() +  "," 
				+ c.getProjectID() + "," + c.getStatus() + ")");
			}
		}
		
		catch (SQLException e) {
			
				e.printStackTrace();	
		}
		return c;
	}

	/**
	 * Auslesen aller Ausschreibungen, die in der Datenbank gespeichert sind
	 * @return
	 */
	
	public Vector<Call> findAll() {
		
		Connection con = DBConnection.connection();
        Vector<Call> result = new Vector<Call>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, title, description, "
        			+ "deadline, project_id, projectleader_id, partnerprofile_id, status  "
        	+ "FROM projektmarktplatz.`call` ");
        	
        	while (rs.next()){
        		Call c = new Call();
        		c.setID(rs.getInt("id"));
        		c.setTitle(rs.getString("title"));
        		c.setDescription(rs.getString("description"));
        		c.setDeadline(sdf.parse(rs.getString("deadline")));
        		c.setProjectID(rs.getInt("projectID"));
        		c.setCallerID(rs.getInt("orgaunit_id"));
        		c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
        		c.setStatus(rs.getInt("status"));
        		
        		result.addElement(c);
        	}
        	
        }
        catch (SQLException | ParseException e){
        	e.printStackTrace();
        	return null;
        }
    return result;
	}

	/**
	 * Auslesen einer Ausschreibung mithilfe der ProjectID
	 * @param id
	 * @return
	 */
	
	public Vector<Call> findByProjectID(Integer projectID) {
		Connection con = DBConnection.connection();
		Vector<Call> result = new Vector<Call>();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.project_id = '" + projectID + "'");		
			
			while (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(sdf.parse(rs.getString("deadline")));
				c.setProjectID(rs.getInt("project_id"));
				c.setCallerID(rs.getInt("orgaunit_id"));
				c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				c.setStatus(rs.getInt("status"));
				
				result.addElement(c);
			}
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		return result;
	}
	
	/**
	 * bestimmte Ausschreibung auslesen mithilfe CallID
	 * @param callID
	 * @return
	 */
	
	public Call findByID(Integer callID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.id = " + callID);		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(sdf.parse(rs.getString("deadline")));
				c.setProjectID(rs.getInt("project_id"));
				c.setCallerID(rs.getInt("orgaunit_id"));
				c.setPartnerProfileID(rs.getInt("partnerprofile_id"));
				c.setStatus(rs.getInt("status"));
				return c;
			}			
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
			return null;
		}
		
	}

	/**
	 * loeschen einer Ausschreibung aus der Datenbank 
	 * @param c
	 */
	
	public void delete(Call c) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("DELETE projektmarktplatz.`call` FROM projektmarktplatz.`call` WHERE projektmarktplatz.`call`.id= " + c.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

}
