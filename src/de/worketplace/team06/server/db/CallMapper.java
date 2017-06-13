package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class CallMapper {
	
	private static CallMapper callMapper = null;
	
	protected CallMapper() {
		
	}

	public static CallMapper callMapper() {
		if (callMapper == null) {
			callMapper = new CallMapper();
		}
		
		return callMapper;
	}


	public Call update(Call c) {
    	Connection con = DBConnection.connection();
    	
    	try {
    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE call SET"
					+ " call.description='" + c.getDescription() +
					"', call.title= " + c.getPartnerProfileID() +
					", call.deadline= '" + c.getDeadline() +
					"', call.created= " + c.getCreated() + 
					" WHERE call.id= " + c.getID());
    	}
    	catch (SQLException e2) {
    		e2.printStackTrace();	
    	}
		return null;
		
	}

	
	public Call insert(Call c) {
		Connection con = DBConnection.connection();
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM call ");			
			if (rs.next()) {
				
				c.setID(rs.getInt("maxid") + 1);
		
				con.setAutoCommit(false);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO call (id, created, deadline, description, title) " + "VALUES (" + c.getID() + ",'" 
				+ c.getCreated() + "','" + c.getDeadline() +  "','" 
				+ c.getDescription() +  "','" + c.getTitle() + "')");
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

	
	public Vector<Call> findAll() {
		
		return null;
	}

	
	public Call findByProjectID(int id) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM call WHERE call.ProjectID = '" + projectID + "'");		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setCreated(rs.getTimestamp("created"));
				c.setDeadline(rs.getDate("deadline"));
				c.setDescription(rs.getString("description"));
				c.setProjectID(rs.getInt("projectID"));
				c.setTitle(rs.getString("title"));
				
				return c;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public Call findByID(int callID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM call WHERE call.id = " + callID);		
			
			if (rs.next()) {
				Call c = new Call();
				c.setID(rs.getInt("id"));
				c.setCreated(rs.getTimestamp("created"));
				c.setTitle(rs.getString("title"));
				c.setDescription(rs.getString("description"));
				c.setDeadline(rs.getDate("deadline"));
				
				return c;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	
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
