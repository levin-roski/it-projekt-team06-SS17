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
	
	public Application findByKey(int id)
	
	public Application findBy schauen 
	
	public Application insert(Application a)
	
	public Application update(Application a)
	
	public void delete(Application a)
}
