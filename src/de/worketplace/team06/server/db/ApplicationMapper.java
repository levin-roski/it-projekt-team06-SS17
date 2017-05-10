package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class ApplicationMapper {
	
	private static ApplicationMapper applicationMapper = null;
	
	protected ApplicationMapper() {
		
	}

	public static ApplicationMapper applicationMapper() {
		if (applicationMapper == null) {
			applicationMapper = new ApplicationMapper();
		}
		
		return applicationMapper;
	}

	public Application findById(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text FROM application " + "WHERE id = " + id);
			
			if (rs.next()) {
				Application a = new Application();
				a.setId(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				return a;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
			
	
	
	public Vector<Application> findAll() {
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text FROM application " + "ORDER BY id");
			
			while (rs.next()) {
				
				Application a = new Application();
				a.setId(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				
				result.addElement(a);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	
/*	public Application findBy schauen 
 * 
 * public Application insert(Application a)
	
	public Application update(Application a)
	
	public void delete(Application a)
*/
}
