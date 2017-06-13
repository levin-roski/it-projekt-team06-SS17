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

	
	public Enrollment findByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id FROM enrollment " + "WHERE id = " + id);
			
			if (rs.next()) {
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				e.setStartDate(rs.getTimestamp("start_date"));
				e.setPeriod(rs.getInt("period"));
				e.setEndDate(rs.getTimestamp("end_date"));
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				
				return e;
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	public Vector<Enrollment> findAll() {
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id FROM enrollment " + "ORDER BY id");
			
			while (rs.next()) {
				
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				e.setStartDate(rs.getTimestamp("start_date"));
				e.setPeriod(rs.getInt("period"));
				e.setEndDate(rs.getTimestamp("end_date"));
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				
				result.addElement(e);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Enrollment> findByOrgaUnitID (int orgaUnitID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id FROM enrollment " + "WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					e.setStartDate(rs.getTimestamp("start_date"));
					e.setPeriod(rs.getInt("period"));
					e.setEndDate(rs.getTimestamp("end_date"));
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
				
				result.addElement(e);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	public Vector<Enrollment> findByProjectID (int projectID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id FROM enrollment " + "WHERE project_id ='" + projectID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					e.setStartDate(rs.getTimestamp("start_date"));
					e.setPeriod(rs.getInt("period"));
					e.setEndDate(rs.getTimestamp("end_date"));
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
				
				result.addElement(e);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	public Enrollment insert(Enrollment e) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxis " + "FROM enrollment ");
			
			if (rs.next()) {
				
				e.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO enrollment (id, created, start_date, period, end_date, orgaunit_id, project_id) " + "VALUES (" + e.getID() + "," + e.getCreated() + "," + e.getStartDate() + "," + e.getPeriod() + "," + e.getEndDate() + "," + e.getOrgaUnitID() + "," + e.getProjectID() + ")");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return e;
	}
	
	
	public Enrollment update(Enrollment e) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE enrollment " + "SET start_date=\"" + e.getStartDate() + "\" " + "WHERE id=" + e.getID() + "," + "SET end_date=\"" + e.getEndDate() + "\" " + "WHERE id=" + e.getID() + "," + "SET period=\"" + e.getPeriod() + "\" " + "WHERE id=" + e.getID());
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return e;
	}
	
	
	public void delete(Enrollment e) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM enrollment " + "WHERE id=" + e.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public Person getSourcePerson(Enrollment e) {
		return PersonMapper.personMapper().findByID(e.getOrgaUnitID());
	}

	public Organisation getSourceOrganisation(Enrollment e) {
		return OrganisationMapper.organisationMapper().findByID(e.getOrgaUnitID());
	}
	
	public Team getSourceTeam(Enrollment e) {
		return TeamMapper.teamMapper().findByID(e.getOrgaUnitID());
	}
	
	public Project getSourceProject(Enrollment e) {
		return ProjectMapper.projectMapper().findByID(e.getProjectID());
	}
	
	
}