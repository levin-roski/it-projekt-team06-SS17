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
	
	//AutoGeneratedBy Johannes
	public Enrollment insert(Enrollment e) {
		// TODO Auto-generated method stub
		return null;
	}

	//AutoGeneratedBy Johannes
	public void update(Enrollment enrollment) {
		// TODO Auto-generated method stub
		
	}
	
	//AutoGeneratedBy Johannes
	public Vector<Enrollment> findByProjectID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//AutoGeneratedBy Johannes
	public Vector<Enrollment> findByOrgaUnitID(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
