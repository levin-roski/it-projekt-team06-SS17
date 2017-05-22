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
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, person_id, team_id, organisation_id FROM application " + "WHERE id = " + id);
			
			if (rs.next()) {
				Application a = new Application();
				a.setId(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallId(rs.getCallId("call_id"));
				a.setPersonId(rs.getPersonId("person_id"));
				a.setTeamId(rs.getTeamId("team_id"));
				a.setOrganisationId(rs.getOrganisationId("organisation_id"));
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
			
			ResultSet rs = stmt.executeQuery("Select id, created, text, call_id, person_id, team_id, organisation_id FROM application " + "ORDER BY id");
			
			while (rs.next()) {
				
				Application a = new Application();
				a.setId(rs.getInt("id"));
				a.setCreated(rs.getTimestamp("created"));
				a.setText(rs.getString("text"));
				a.setCallId(rs.getCallId("call_id"));
				a.setPersonId(rs.getPersonId("person_id"));
				a.setTeamId(rs.getTeamId("team_id"));
				a.setOrganisationId(rs.getOrganisationId("organisation_id"));
				
				result.addElement(a);
			}
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}
	
	
	public Vector<Application> findByPersonApplicant (Person personApplicant) {
		
		return findByPersonApplicant (personApplicant.getId());
	}
	
	public Vector<Application> findByTeamApplicant (Team teamApplicant) {
		
		return findByTeamApplicant (teamApplicant.getId());
	}
	
	public Vector<Application> findByOrganisationApplicant (Team organisationApplicant) {
		
		return findByOrganisationApplicant (organisationApplicant.getId());
	}
	
	
	public Application insert(Application a) {
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxis " + "FROM application ");
			
			if (rs.next()) {
				
				a.setID(rs.getInt("maxid") + 1);
				
				stmt = con.createStatement();
				
				stmt.executeUpdate("INSERT INTO application (id, created, text, call_id, person_id, team_id, organisation_id) " + "VALUES (" + a.getId() + "," + a.getCreated() + "," + a.getText() + "," + a.getCallId() + "," + a.getPersonId() + "," + a.getTeamId() + "," + a.getOrganisationId()+ ")");
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
			
			stmt.executeUpdate("UPDATE application " + "SET text=\"" + a.getText() + "\" " + "WHERE id=" + a.getId());
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
			
			stmt.executeUpdate("DELETE FROM application " + "WHERE id=" + a.getId());
			
		}
		catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	
	
	public Person getSourcePerson(Application a) {
		return PersonMapper.personMapper().findById(a.getPersonId());
	}
	
	public Team getSourceTeam(Application a) {
		return TeamMapper.teamMapper().findById(a.getTeamId());
	}
	
	public Organisation getSourceOrganisation(Application a) {
		return OrganisationMapper.organisationMapper().findById(a.getOrganisationId());
	}

	public Call getSourceCall(Application a) {
		return CallMapper.callMapper().findbyId(a.getCallId());
	}
}