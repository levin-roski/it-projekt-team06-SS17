package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;
import de.worketplace.team06.shared.bo.*;

public class PersonMapper {
	
	private static PersonMapper personMapper = null;
	
	protected PersonMapper() {
		
	}

	public static PersonMapper personMapper() {
		if (personMapper == null) {
			personMapper = new PersonMapper();
		}
		
		return personMapper;
	}		
	
	public Person insert(Person p) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM orgaunit ");
			
			if (rs.next()) {
				
				p.setID(rs.getInt("maxid") + 1);
				
				con.setAutoCommit(false);
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','" 
									+ p.getGoogleID() +  "','" + p.getDescription() +  "','"
									+ p.getType() + "')");
				stmt.executeUpdate("INSERT INTO person (id, created, firstName, lastName, street, zipcode, city) "
									+ "VALUES (" + p.getID() + ",'" + p.getCreated() + "','"
									+ p.getFirstName() + "','" + p.getLastName() + "','" + p.getStreet() + "'," 
									+ p.getZipcode() + ",'" + p.getCity() + "')");
				con.commit();
			}
		}
		catch (SQLException e2) {
			try {
				System.out.println("Die SQL Transaktion konnte nicht vollständig ausgeführt werden. "
						+ "Es wird versucht die Transaktion rückgängig zu machen!");
				con.rollback();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  finally {
				 
				  e2.printStackTrace();
			}	
		}
		return p;
	}
	
	/**
	 * Auslesen einer Person mithilfe einer GoogleID.
	 */
	public Person findByGoogleID(String googleID) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.googleID = '" + googleID + "'");		
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(googleID);
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofileID"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	
	

	public void update(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Updaten einer Person :) 
	    	stmt.executeUpdate("UPDATE orgaunit, person SET"
	    						+ " orgaunit.description='" + p.getDescription() +
	    						"', orgaunit.partnerprofileID= " + p.getPartnerProfileID() +
	    						", person.firstName= '" + p.getFirstName() +
	    						"', person.lastName= '" + p.getLastName() + 
	    						"', person.street= '" + p.getStreet() + 
	    						"', person.city= '" + p.getCity() + 
	    						"', person.zipcode= " + p.getZipcode() + 
	    						" WHERE orgaunit.id= " + p.getID() + 
	    						" AND person.id= " + p.getID()); 
	    			
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}	
	
	
	
	public void delete(Person p) {
		Connection con = DBConnection.connection();

	    try {
	    	Statement stmt = con.createStatement();
	    	//Löschen der Person aus der Tabelle orgaunit und person.
	    	stmt.executeUpdate("DELETE orgaunit, person FROM orgaunit INNER JOIN person "
	    			+ "ON orgaunit.id = person.id WHERE orgaunit.id= " + p.getID());
	    	
	    }
	    catch (SQLException e2) {
				  e2.printStackTrace();		
	    }
		
	}

	public Person findByID(int ouid) {
		Connection con = DBConnection.connection();
		
		try {						
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM orgaunit INNER JOIN person "
											+ "ON orgaunit.id = person.id "
											+ "WHERE orgaunit.id = " + ouid);		
			
			if (rs.next()) {
				Person p = new Person();
				p.setID(rs.getInt("id"));
				p.setCreated(rs.getTimestamp("created"));
				p.setGoogleID(rs.getString("googleID"));
				p.setDescription(rs.getString("description"));
				p.setPartnerProfileID(rs.getInt("partnerprofileID"));
				p.setType(rs.getString("type"));
				
				p.setFirstName(rs.getString("firstName"));
				p.setLastName(rs.getString("lastName"));
				p.setStreet(rs.getString("street"));
				p.setZipcode(rs.getInt("zipcode"));
				p.setCity(rs.getString("city"));
				return p;
			}			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}	

}
