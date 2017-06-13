package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class CallMapper {
	
	private static CallMapper callMapper = null;
	
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
	
	public Call update(Call c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE call SET"
					+ " call.title='" + c.getTitle() +
					"', call.description= " + c.getDescription() +
					", call.deadline= '" + c.getDeadline() +
					"', call.projectID= " + c.getProjectID() + 
					"', call.projectLeaderID= " + c.getProjectLeaderID() +
					"', call.partnerProfileID= " + c.getPartnerProfileID() +
					" WHERE call.id= " + c.getID());
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();	
    	}
		return null;
		
	}

	/**
	 * Ausschreibung in der Datenbank anlegen 
	 * @param c
	 * @return
	 */
	
	public Call insert(Call c) {
		Connection con = DBConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM call ");			
			if (rs.next()) {
				
				c.setID(rs.getInt("maxid") + 1);
		
				con.setAutoCommit(false);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO call (id, title, description, deadline, projectID, projectLeaderID, partnerProfileID) " 
				+ "VALUES (" + c.getID() + ",'" 
				+ c.getTitle() + "','" + c.getDescription() +  "','" 
				+ c.getDeadline() + "','" + c.getProjectID() +  "','" 
				+ c.getProjectLeaderID() +  "','" + c.getPartnerProfileID() + "')");
			}
		}
		
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			  finally {
				  e2.printStackTrace();
			}	
		}
		return c;
	}

	/**
	 * Auslesen aller Ausschreibungen, die in der Datenbank gespeichert sind
	 * @return
	 */
	
	public Call findAll() {
		
		Connection con = DBConnection.connection();
        Vector<Call> result = new Vector<Call>();
        
        try{
        	Statement stmt = con.createStatement();
        	
        	ResultSet rs = stmt.executeQuery("SELECT id, title, description, "
        			+ "deadline, projectID, projectLeaderID, partnerProfileID,  "
        	+ "FROM Call ");
        	
        	while (rs.next()){
        		Call c = new Call();
        		c.setID(rs.getInt("id"));
        		c.setTitle(rs.getString("title"));
        		c.setDescription(rs.getString("description"));
        		c.setDeadline(rs.getDate("deadline"));
        		c.setProjectID(rs.getInt("projectID"));
        		c.setProjectLeaderID(rs.getInt("projectLeaderID"));
        		c.setPartnerProfileID(rs.getInt("endPartnerProfileID"));
        		
        		result.addElement(c);
        	}
        }
        catch (SQLException e){
        	e.printStackTrace();
        }
        return null;
	}

	/**
	 * Auslesen einer Ausschreibung mithilfe der ProjectID
	 * @param id
	 * @return
	 */
	
	public Vector<Call> findByProjectID(int projectID) {
		Connection con = DBConnection.connection();
		Vector<Call> result = new Vector<Call>();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM call WHERE call.ProjectID = '" + projectID + "'");		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(rs.getDate("deadline"));
				c.setProjectID(rs.getInt("projectID"));
				c.setProjectLeaderID(rs.getInt("ProjectLeaderID"));
				c.setPartnerProfileID(rs.getInt("PartnerProfileID"));
				
				result.addElement(c); ;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * bestimmte Ausschreibung auslesen mithilfe CallID
	 * @param callID
	 * @return
	 */
	
	public Call findByID(int callID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM call WHERE call.id = " + callID);		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(rs.getDate("deadline"));
				c.setProjectID(rs.getInt("ProjectID"));
				c.setProjectLeaderID(rs.getInt("ProjectLeaderID"));
				c.setPartnerProfileID(rs.getInt("PartnerProfileID"));
				
				return c;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * loeschen einer Ausschreibung aus der Datenbank 
	 * @param c
	 */
	
	public void delete(Call c) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	stmt.executeUpdate("DELETE call FROM call WHERE call.id= " + c.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

}
