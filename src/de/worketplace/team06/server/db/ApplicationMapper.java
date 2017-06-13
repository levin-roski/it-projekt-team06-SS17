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

	
	public Application findByID(int id) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, orgaunit_id FROM application " + "WHERE id = " + id);
			
			if (rs.next()) {
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("orgaunit_id"));
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
			
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id FROM application " + "ORDER BY id");
			
			while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				
				result.addElement(a);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Application> findByOrgaUnitID (int orgaUnitID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id "
					+ " FROM orgaunit WHERE orgaunit_id ='" + orgaUnitID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	public Vector<Application> findByCallID (int callID) {
		
		Connection con = DBConnection.connection();
		
		Vector<Application> result = new Vector<Application>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, created, text, call_id, orgaunit_id "
					+ " FROM call WHERE call_id ='" + callID + "'ORDER BY id");
			
				while (rs.next()) {
				
				Application a = new Application();
				a.setID(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallID(rs.getInt("call_id"));
				a.setOrgaUnitID(rs.getInt("organisation_id"));
				
				result.addElement(a);
			}
		}
		
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public Application insert(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxis " + "FROM application ");
			
			if (rs.next()) {
				
				a.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO application (id, created, text, call_id, orgaunit_id) " + "VALUES (" + a.getID() + "," + a.getCreated() + "," + a.getText() + "," + a.getCallID() + "," + a.getOrgaUnitID()+ ")");
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	
	public Application update(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE application " + "SET text=\"" + a.getText() + "\" " + "WHERE id=" + a.getID());
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return a;
	}
	
	
	public void delete(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM application " + "WHERE id=" + a.getID());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public Person getSourcePerson(Application a) {
		return PersonMapper.personMapper().findByID(a.getOrgaUnitID());
	}

	public Organisation getSourceOrganisation(Application a) {
		return OrganisationMapper.organisationMapper().findByID(a.getOrgaUnitID());
	}
	
	public Team getSourceTeam(Application a) {
		return TeamMapper.teamMapper().findByID(a.getOrgaUnitID());
	}

	public Call getSourceCall(Application a) {
		return CallMapper.callMapper().findByID(a.getCallID());
	}
	
	
}