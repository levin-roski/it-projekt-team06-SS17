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
		
				stmt = con.createStatement();
				stmt.executeUpdate("INSERT INTO orgaunit (id, created, googleID, description, type) " + "VALUES (" + p.getID() + ",'" + p.getCreated() + "','" + p.getGoogleID() +  "','" + p.getDescription() +  "','" + p.getType() + "')");
				stmt.executeUpdate("INSERT INTO person (id, created, firstName, lastName, street, zipcode, city) " + "VALUES (" + p.getID() + ",'" + p.getCreated() + "','" + p.getFirstName() + "','" + p.getLastName() + "','" + p.getStreet() + "'," + p.getZipcode() + ",'" + p.getCity() + "')");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return p;
	}
	
	
/*	
 * 
 * PATRICK
 * 
	public Person findByKey(int id)

	public Person findBy schauen 
	
	public Person insert(Person a)
	
	public Person update(Person a)
	
	public void delete(Person a)
*/

	public void update(Person person) {
		// TODO Auto-generated method stub
		
	}

	public Person findByGoogleID(String googleId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
