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
				a.setCallID(rs.getCallID("call_id"));
				a.setOrganisationID(rs.getOrgaUnitID("orgaunit_id"));
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
				a.setCallID(rs.getCallID("call_id"));
				a.setOrganisationID(rs.getOrgaUnitID("organisation_id"));
				
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
				a.setCallID(rs.getCallID("call_id"));
				a.setOrganisationID(rs.getOrgaUnitID("organisation_id"));
				
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
	
	
	public Organisation getSourceOrgaUnit(Application a) {
		return OrgaUnitMapper.orgaUnitMapper().findByID(a.getOrgaUnitID());
	}

	public Call getSourceCall(Application a) {
		return CallMapper.callMapper().findbyID(a.getCallID());
	}
	
	
}
