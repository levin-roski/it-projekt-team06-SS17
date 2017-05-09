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
/*
	public Application findByID(int id)
		
	
	
	public Vector<Application> findAll() {
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT appl_id, appl_created, appl_text, call_id, rat_id FROM application " + "ORDER BY id");
		}
	}
	
	
/*	public Application findBy schauen 
 * 
 * public Application insert(Application a)
	
	public Application update(Application a)
	
	public void delete(Application a)
*/
}
