package de.worketplace.team06.server.db;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import de.worketplace.team06.shared.bo.*;

public class EnrollmentMapper {
	
	private static EnrollmentMapper enrollmentMapper = null;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	protected EnrollmentMapper() {
		
	}

	public static EnrollmentMapper enrollmentMapper() {
		if (enrollmentMapper == null) {
			enrollmentMapper = new EnrollmentMapper();
		}
		
		return enrollmentMapper;
	}

	
	public Enrollment findByID(Integer id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE id = " + id);
			
			if (rs.next()) {
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				e.setStartDate(sdf.parse(rs.getString("start_date")));
				e.setWorkload(rs.getInt("period"));
				e.setEndDate(sdf.parse(rs.getString("end_date")));
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				e.setRatingID(rs.getInt("rating_id"));
				
				return e;
			}
		}
		catch (SQLException | ParseException e2) {
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
			
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "ORDER BY id");
			
			while (rs.next()) {
				
				Enrollment e = new Enrollment();
				e.setID(rs.getInt("id"));
				e.setCreated(rs.getTimestamp("created"));
				e.setStartDate(sdf.parse(rs.getString("start_date")));
				e.setWorkload(rs.getInt("period"));
				e.setEndDate(sdf.parse(rs.getString("end_date")));
				e.setOrgaUnitID(rs.getInt("orgaunit_id"));
				e.setProjectID(rs.getInt("project_id"));
				e.setRatingID(rs.getInt("rating_id"));
				
				result.addElement(e);
			}
		}
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Enrollment> findByOrgaUnitID (Integer orgaUnitID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					e.setWorkload(rs.getInt("period"));
					e.setEndDate(sdf.parse(rs.getString("end_date")));
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					e.setRatingID(rs.getInt("rating_id"));
				
				result.addElement(e);
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	public Vector<Enrollment> findByProjectID (Integer projectID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Enrollment> result = new Vector<Enrollment>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE project_id ='" + projectID + "'ORDER BY id");
			
				while (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					e.setWorkload(rs.getInt("period"));
					e.setEndDate(sdf.parse(rs.getString("start_date")));
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					e.setRatingID(rs.getInt("rating_id"));
				
				result.addElement(e);
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
public Enrollment findByRatingID (Integer rID) {
		
		Connection con = DBConnection.connection();
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select id, created, start_date, period, end_date, orgaunit_id, project_id, rating_id FROM enrollment " + "WHERE rating_id ='" + rID + "'ORDER BY id");
			
				if (rs.next()) {
				
					Enrollment e = new Enrollment();
					e.setID(rs.getInt("id"));
					e.setCreated(rs.getTimestamp("created"));
					e.setStartDate(sdf.parse(rs.getString("start_date")));
					e.setWorkload(rs.getInt("period"));
					e.setEndDate(sdf.parse(rs.getString("start_date")));
					e.setOrgaUnitID(rs.getInt("orgaunit_id"));
					e.setProjectID(rs.getInt("project_id"));
					e.setRatingID(rs.getInt("rating_id"));
				
				return e;
			}
		}
		
		catch (SQLException | ParseException e2) {
			e2.printStackTrace();
		}
		
		return null;
	}
	
	
	public Enrollment insert(Enrollment e) {
		Connection con = DBConnection.connection();
		
		try {
			
			/*
			 *  Start und Enddatum werden mit Hochkommas verknüpft, da im Falle einer Automatischen
			 *  Beteiligung das Start und Enddatum nicht gesetzt sind. "Null" darf nicht als String
			 *  in der Datenbank gespeichert werden, deshalb werden die Hochkommas direkt im startdatum und 
			 *  enddatum gesetzt. 
			 */
			
			String invertedcomma = "'";
			String startdate = null;
			String enddate = null;
			if (e.getStartDate() != null){
			startdate = sdf.format(e.getStartDate());
			startdate = invertedcomma.concat(startdate).concat(invertedcomma);
			}
			if (e.getEndDate() != null){
			enddate = sdf.format(e.getEndDate());
			enddate = invertedcomma.concat(enddate).concat(invertedcomma);
			}
        	
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM enrollment ");
			
			if (rs.next()) {
				
				e.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO enrollment (id, created, start_date, end_date, period,"
						+ " orgaunit_id, project_id, rating_id) " + "VALUES (" + e.getID() + ",'"
						+ e.getCreated() + "'," + startdate + "," + enddate + ","
				        + e.getWorkload() + "," + e.getOrgaUnitID() + "," + e.getProjectID() + ","
						+ e.getRatingID()+ ")");
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
			String startdate = sdf.format(e.getStartDate());
        	String enddate = sdf.format(e.getEndDate());
        	
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE enrollment SET start_date='" + startdate +
					"', end_date='" + enddate + "', orgaunit_id=" + e.getOrgaUnitID() + 
					", project_id=" + e.getProjectID() + ", rating_id=" + e.getRatingID() + ", period=" + e.getWorkload() +
					" WHERE id=" + e.getID());
			
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