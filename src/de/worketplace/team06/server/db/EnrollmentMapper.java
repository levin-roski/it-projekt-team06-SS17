package de.worketplace.team06.server.db;

import java.sql.*;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class EnrollmentMapper {
	
	private static EnrollmentMapper enrollmentMapper = null;
	
	protected EnrollmentMapper() {
		
	}

	public static EnrollmentMapper enrollmentMapper() {
		if (enrollmentMapper == null) {
			enrollmentMapper = new EnrollmentMapper();
		}
		
		return enrollmentMapper;
	}
/*	
 * 
 * PATRICK
 * 
	public Enrollment findByKey(int id)
	
	public Enrollment findBy schauen 
	
	public Enrollment insert(Enrollment a)
	
	public Enrollment update(Enrollment a)
	
	public void delete(Enrollment a)
*/
}
