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
/*	
 * 
 * PATRICK
 * 
	public Call findByKey(int id)
	
	public Call findBy schauen 
	
	public Call insert(Call a)
	
	public Call update(Call a)
	
	public void delete(Call a)
*/

	public Call update(Call call) {
		// TODO Auto-generated method stub
		return null;
		
	}


}
